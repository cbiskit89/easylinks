package me.corymiller.easylinks;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.*;

public class EasyLinksServer {
    public static void main(String[] args) throws Exception {
        EasyLinksServerProperties props = new EasyLinksServerProperties();
        int port = Integer.parseInt(props.appProps.getProperty("port", "8080"));

        Server server = new Server(port);
        ServletContextHandler servlet = new ServletContextHandler(server, "/");
        servlet.addServlet(EasyLinksCreateServlet.class, "/create");
        servlet.addServlet(EasyLinksDeleteServlet.class, "/delete");
        servlet.addServlet(EasyLinksListServlet.class, "/list");
        servlet.addServlet(EasyLinksUpdateServlet.class, "/update");
        servlet.addServlet(EasyLinksResolverServlet.class, "/*");
        server.start();
        server.join();
    }
}
