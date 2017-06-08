package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class GoLinkMainHandler extends AbstractHandler {
    public GoLinkMainHandler() {}

    public void handle(String target,
            Request baseRequest,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String template = "<h1>Main Page</h1>" +
            "<form action='/create'><input name='source' type='textbox' />" +
            "<input name='dest' type='textbox' />" +
            "<input name='submit' type='submit' /></form>";

        response.getWriter().println(template);

        baseRequest.setHandled(true);
    }
}

