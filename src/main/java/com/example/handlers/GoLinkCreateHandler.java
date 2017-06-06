package com.example;

import java.io.IOException;

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
        response.getWriter().println(this.goLinkName);

        baseRequest.setHandled(true);
    }
}

