package me.corymiller.easylinks;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.corymiller.easylinks.EasyLinksUtils;

public class EasyLinksUpdateServlet extends HttpServlet {

        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws IOException, ServletException {
                response.addHeader("Access-Control-Allow-Origin", "*");
                Map<String, String[]> params = request.getParameterMap();

                if (!params.containsKey("currentSource")) {
                        response.sendError(
                                HttpServletResponse.SC_BAD_REQUEST,
                                "must specify an Easy Link to update");
                        return;
                }
                String source = params.get("currentSource")[0];

                if (!params.containsKey("newSource") && !params.containsKey("newDest")) {
                        response.setStatus(HttpServletResponse.SC_OK);
                        return;
                }

                Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
                Session session = cluster.connect("golinks");
                PreparedStatement ps = session.prepare(
                        "SELECT * FROM permlinks " + "WHERE source=?");
                BoundStatement bs = ps.bind(source);

                List<Row> rl = session.execute(bs).all();
                if (rl.size() == 0) {
                        response.sendError(
                                HttpServletResponse.SC_BAD_REQUEST,
                                "link does not exist");
                        return;
                }

                if (params.containsKey("newDest")) {
                        updateDestination(session, source, params.get("newDest")[0]);
                }
                if (params.containsKey("newSource")) {
                        updateSource(session, source, params.get("newSource")[0]);
                }
                response.setStatus(HttpServletResponse.SC_OK);
        }

        void updateDestination(Session session, String source, String newDestination) {
                String updateStatement = "UPDATE permlinks SET destination=:newDest ";
                PreparedStatement updatePs = session.prepare(updateStatement + "WHERE source=:currentSource");
                BoundStatement updateBs = updatePs.bind()
                        .setString("currentSource", source)
                        .setString("newDest", newDestination);
                session.execute(updateBs);
        }

        void updateSource(Session session, String currentSource, String newSource) {
                PreparedStatement getPs = session.prepare(
                        "SELECT * FROM permlinks " + "WHERE source=?");
                BoundStatement bs = getPs.bind(currentSource);

                List<Row> rl = session.execute(bs).all();
                String currentDest = rl.get(0).getString("destination");

                PreparedStatement deletePs = session.prepare("DELETE FROM permlinks " + "WHERE source=?");
                BoundStatement deleteBs = deletePs.bind(currentSource);
                session.execute(deleteBs);

                PreparedStatement insertPs = session.prepare(
                        "INSERT INTO permlinks " + "(source, destination) VALUES (?, ?)");
                BoundStatement insertBs = insertPs.bind(newSource, currentDest);
                session.execute(insertBs);
        }
}
