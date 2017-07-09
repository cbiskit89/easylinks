package me.corymiller.golinkserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoLinkMainServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String template = "<h1>Main Page</h1>" +
            "<h2>Create</h2><br />" +
            "<form action='/create'>" +
            "<input name='source' type='textbox' placeholder='Go Link Name' />" +
            "<input name='dest' type='textbox' placeholder='Destination Link' /> " +
            "<input name='submit' type='submit' /></form>" +
            "<br /><h2>Update</h2><br />" +
            "<form action='/update'>" +
            "<input name='source' type='textbox' placeholder='Go Link Name' />" +
            "<input name='dest' type='textbox' placeholder='Destination Link' /> " +
            "<input name='update' type='submit' /></form>" +
            "<br /><h2>Delete</h2><br />" +
            "<form action='/delete'>" +
            "<input name='source' type='textbox' placeholder='Go Link Name' />" +
            "<input name='delete' type='submit' /></form>" +
            "<a href='/list'>List All</a>";

        response.getWriter().println(template);
    }
}

