package com.example;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class GoLinkCreateHandler extends AbstractHandler {
    final String goLinkName;

    public GoLinkCreateHandler() {
        this("No Link.");
    }

    public GoLinkCreateHandler(String goLink) {
        this.goLinkName = "Create Go Link: " + goLink;
    }

    public void handle(String target,
            Request baseRequest,
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
        if (rl.size() > 0) {
            response.getWriter().println("<h3 style='color:red'>Link already exists.</h3>");
        }
        else {
            PreparedStatement insertPs = session.prepare("INSERT INTO permlinks " +
                    "(source, destination) VALUES (?, ?)");
            BoundStatement insertBs = insertPs.bind(params.get("source")[0],
                    params.get("dest")[0]);
            session.execute(insertBs);
            response.getWriter().println("<h3 style='color:green'>Link create.<h3>");
            response.getWriter().println("<a href='http://" + params.get("dest")[0] +
                    "'>" + params.get("source")[0] + "</a>");
        }
        baseRequest.setHandled(true);
    }
}

