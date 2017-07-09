package me.corymiller.golinkserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.*;

public class GoLinkServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler servlet = new ServletContextHandler(server, "/");
        servlet.addServlet(GoLinkCreateServlet.class, "/create");
        servlet.addServlet(GoLinkDeleteServlet.class, "/delete");
        servlet.addServlet(GoLinkListServlet.class, "/list");
        servlet.addServlet(GoLinkResolverServlet.class, "/to/*");
        servlet.addServlet(GoLinkUpdateServlet.class, "/update");
        servlet.addServlet(GoLinkMainServlet.class, "/");
	server.start();
	server.join();
    }
}

