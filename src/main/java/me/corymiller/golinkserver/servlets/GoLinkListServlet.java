package me.corymiller.golinkserver;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.corymiller.golinks.GoLinkUtils;

public class GoLinkListServlet extends HttpServlet {

    @Override
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println("<a href='/'>Home</a>");
        
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.connect("golinks");
        response.getWriter().println("<style>table, th, td {border: 1px solid black;}</style>");
        response.getWriter().println("<table><tr><th>Go Link Name</th><th>Destination Address</th><th>Edit</th><th>Delete</th></tr>");
        for (Row row : session.execute("SELECT * FROM permlinks")) {
            String dest = GoLinkUtils.provideAbsoluteLink(row.getString("destination"));
            response.getWriter().println(
                    "<tr><td><a href='" + dest + "' target='_blank'>go/" +
                    row.getString("source") + "</a></td><td>" + dest + "</td>" + "<td>TODO(cmiller)</td>" +
                    "<td><a href='/delete?source=" + row.getString("source") + "'>" +
                    row.getString("source") + "</a></td></tr>");
        }
        response.getWriter().println("</table>");
    }
}

