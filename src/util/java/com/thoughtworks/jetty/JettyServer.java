package com.thoughtworks.jetty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public final class JettyServer {
    private static final String USAGE = "Usage: jettyserver webapp-path port [context]\n";
    
	private final Server server;
	private final String webbapp;
	private final String context;

	public static void main(final String[] args) throws Exception {
        if (args.length < 2) {
        	System.err.print(USAGE);
        	System.exit(1);
        }

        final String webapp = args[0];
        final int port = Integer.parseInt(args[1]);
        final String context = (args.length < 3) ? "/" : args[2];

		final JettyServer jettyServer = new JettyServer(webapp, port, context);
		
		maybeStartMonitor(jettyServer);
		
		jettyServer.start();
		jettyServer.join();
	}

	private static void maybeStartMonitor(final JettyServer jettyServer) {
		final Integer stopPort = Integer.getInteger("STOP.PORT");
		if (stopPort != null) {
			new JettyMonitor(new Integer(System.getProperty("STOP.PORT")), jettyServer).start();
		}
	}

	public JettyServer(final String webbapp, final int port, final String context) {
		this.webbapp = webbapp;
		this.context = context;
		server = new Server(port);
	}

	public void start() throws Exception {
		final WebAppContext webapp = new WebAppContext(webbapp, context);
		server.addHandler(webapp);
		server.start();
	}

	public void stop() throws Exception {
		server.stop();
	}

	public void join() throws Exception {
		server.join();
	}
	
    private static class JettyMonitor extends Thread {

    	private final JettyServer jettyServer;
        private final ServerSocket socket;

        public JettyMonitor(final int stopPort, final JettyServer jettyServer) {
            this.jettyServer = jettyServer;
			setDaemon(true);
            setName("StopMonitor");
            try {
                this.socket = new ServerSocket(stopPort, 1, InetAddress.getByName("127.0.0.1"));
            } catch(final Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        @Override
        public void run() {
            System.out.println("*** running jetty 'stop' thread");
            Socket accept;
            try {
                accept = socket.accept();
                final BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                reader.readLine();
                System.out.println("*** stopping jetty embedded server");
                jettyServer.stop();
                accept.close();
                socket.close();
            } catch(final Exception e) {
                throw new RuntimeException(e);
            }
        }
        
    }
}
