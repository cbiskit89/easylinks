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

public class EasyLinksCreateServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws IOException, ServletException {
                                response.addHeader("Access-Control-Allow-Origin", "*");
                Map<String, String[]> params = request.getParameterMap();

                Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
                Session session = cluster.connect("golinks");
                PreparedStatement ps = session.prepare("SELECT * FROM permlinks " + "WHERE source=?");
                BoundStatement bs = ps.bind(params.get("source")[0]);

                List<Row> rl = session.execute(bs).all();
                if (rl.size() > 0) {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "link already exists");
                } else {
                        PreparedStatement insertPs = session
                                        .prepare("INSERT INTO permlinks " + "(source, destination) VALUES (?, ?)");
                        BoundStatement insertBs = insertPs.bind(params.get("source")[0], params.get("dest")[0]);
                        String dest = EasyLinksUtils.provideAbsoluteLink(params.get("dest")[0]);
                        session.execute(insertBs);
                }
                response.setStatus(HttpServletResponse.SC_OK);
        }
}
