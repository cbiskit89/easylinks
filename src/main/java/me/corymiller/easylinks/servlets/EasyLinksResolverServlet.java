package me.corymiller.easylinks;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.corymiller.easylinks.EasyLinksUtils;

public class EasyLinksResolverServlet extends HttpServlet {

    @Override
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
                response.addHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(HttpServletResponse.SC_OK);

        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.connect("golinks");

        PreparedStatement ps = session.prepare("SELECT * FROM permlinks " +
                "WHERE source=?");
        BoundStatement bs = ps.bind(request.getPathInfo().substring(1));

        for (Row row : session.execute(bs)) {
            String dest = EasyLinksUtils.provideAbsoluteLink(row.getString("destination"));
            response.sendRedirect(dest);
        }
    }
}
