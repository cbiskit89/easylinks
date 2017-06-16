package me.corymiller.golinkserver;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class GoLinkServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
       
        ContextHandler rootHandler = new ContextHandler("/");
        rootHandler.setContextPath("/");
        rootHandler.setHandler(new GoLinkMainHandler());

        ContextHandler createHandler = new ContextHandler("/create");
        createHandler.setContextPath("/create");
        createHandler.setHandler(new GoLinkCreateHandler());

        ContextHandler deleteHandler = new ContextHandler("/delete");
        deleteHandler.setContextPath("/delete");
        deleteHandler.setHandler(new GoLinkDeleteHandler());

        ContextHandler listHandler = new ContextHandler("/list");
        listHandler.setContextPath("/list");
        listHandler.setHandler(new GoLinkListHandler());

        ContextHandler resolverHandler = new ContextHandler("/to");
        resolverHandler.setContextPath("/to");
        resolverHandler.setHandler(new GoLinkResolverHandler());

        ContextHandler updateHandler = new ContextHandler("/update");
        updateHandler.setContextPath("/update");
        updateHandler.setHandler(new GoLinkUpdateHandler());

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { 
            rootHandler,
            createHandler,
            deleteHandler,
            listHandler,
            resolverHandler,
            updateHandler
        });

	server.setHandler(contexts);
	server.start();
	server.join();
    }
}

