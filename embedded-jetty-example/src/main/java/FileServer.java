//
//  ========================================================================
//  Copyright (c) 1995-2015 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//
 
 
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.webapp.WebAppContext;

/** 
 * Simple Jetty FileServer.
 * This is a simple example of Jetty configured as a FileServer. web.xml must be left blank
 */
public class FileServer {
    public static void main(String[] args) {
        System.out.println("Hello from ScalaSbt Web Project");
        Server server = new Server(8888);
        final WebAppContext root = new WebAppContext();
        
        root.setContextPath("/");
        root.setParentLoaderPriority(true);

        final String webappDirLocation = "src/main/webapp/";
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, root});
        server.setHandler(handlers);
        try {
            server.start();
            server.join();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}