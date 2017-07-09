package me.corymiller.golinkserver;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
//import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.corymiller.golinks.GoLinkUtils;

public class GoLinkUpdateServlet extends HttpServlet {

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
            response.getWriter().println("<h3 style='color:red'>Link does not exist.</h3>");
        }
        else {
            PreparedStatement updatePs = session.prepare("UPDATE permlinks " +
                    "SET destination=? WHERE source=?");
            BoundStatement updateBs = updatePs.bind(params.get("dest")[0],
                    params.get("source")[0]);
            String dest = GoLinkUtils.provideAbsoluteLink(params.get("dest")[0]);
            session.execute(updateBs);
            response.getWriter().println("<h3 style='color:green'>Link updated.<h3>");
            response.getWriter().println("<a target='_blank' href='" + dest +
                    "'>" + params.get("source")[0] + "</a>");
        }
    }
}

