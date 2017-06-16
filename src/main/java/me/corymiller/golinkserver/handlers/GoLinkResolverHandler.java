package me.corymiller.golinkserver;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class GoLinkResolverHandler extends AbstractHandler {
    public GoLinkResolverHandler() {}

    public void handle(String target,
            Request baseRequest,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.connect("golinks");

        PreparedStatement ps = session.prepare("SELECT * FROM permlinks " +
                "WHERE source=?");
        BoundStatement bs = ps.bind(request.getRequestURI().substring(
                    request.getContextPath().length() + 1));

        for (Row row : session.execute(bs)) {
            response.sendRedirect("https://" + row.getString("destination"));
        }

        baseRequest.setHandled(true);
    }
}

