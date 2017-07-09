package me.corymiller.golinkserver;

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

public class GoLinkDeleteServlet extends HttpServlet {

    @Override
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println("<a href='/'>Home</a><br />");

        Map<String, String[]> params = request.getParameterMap();

        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.connect("golinks");
        PreparedStatement ps = session.prepare("SELECT * FROM permlinks " +
                "WHERE source=?");
        BoundStatement bs = ps.bind(params.get("source")[0]);
 
        List<Row> rl = session.execute(bs).all();
        if (rl.size() == 0) {
            response.getWriter().println("<h3 style='color:red'>Nothing to delete.</h3>");
        }
        else {
            PreparedStatement deletePs = session.prepare("DELETE FROM permlinks " +
                    "WHERE source=?");
            BoundStatement deleteBs = deletePs.bind(params.get("source")[0]);
            session.execute(deleteBs);
            response.getWriter().println("<h3 style='color:green'>Link deleted.<h3>");
        }
    }
}

