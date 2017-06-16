package me.corymiller.golinkserver;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class GoLinkListHandler extends AbstractHandler {
    public GoLinkListHandler() {}

    public void handle(String target,
            Request baseRequest,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println("<a href='/'>Home</a>");
        
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.connect("golinks");
        for (Row row : session.execute("SELECT * FROM permlinks")) {
            response.getWriter().println("<li><a href='https://" + row.getString("destination") + "' target='_blank'>go/" + row.getString("source") + "<a/></li>");
        }

        baseRequest.setHandled(true);
    }
}

