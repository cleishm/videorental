package com.thoughtworks.jetty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class JettyLauncher {

    private static final String STOP_COMMAND = "\r\n";
    private static final String USAGE = "Usage: jettylauncher options\n" +
            "-start start-port stop-port war-path\n" +
            "-stop stop-port";

    private static Server server;

    public static void main(String[] args) throws Exception {
        if (args.length >= 0) {
            if (args.length == 4 && args[0].equals("-start")) {
                int startPort = Integer.parseInt(args[1]);
                int stopPort = Integer.parseInt(args[2]);
                String warPath = args[3];
                startJetty(startPort, stopPort, warPath);
                return;
            }
            if (args.length == 2 && args[0].equals("-stop")) {
                int stopPort = Integer.parseInt(args[1]);
                stopJetty(stopPort);
                return;
            }
        }

        System.out.println(USAGE);
        System.exit(1);
    }

    private static void startJetty(int startPort, int stopPort, String warPath) throws Exception {
    System.out.println("Starting jetty with " + warPath + " on " + startPort);
        Thread monitor = new MonitorThread(stopPort);

        server = new Server(startPort);

        server.setConnectors(new Connector[] { CreateConnector(startPort) });

        WebAppContext context = new WebAppContext(warPath, "/");
        server.addHandler(context);

        monitor.start();
        server.start();
        server.join();
    }

    private static void stopJetty(int stopPort) throws Exception {
        Socket s = new Socket(InetAddress.getByName("127.0.0.1"), stopPort);
        OutputStream out = s.getOutputStream();
        System.out.println("*** sending jetty stop request");
        out.write(STOP_COMMAND.getBytes());
        out.flush();
        s.close();
    }

    private static SocketConnector CreateConnector(int startPort) {
        SocketConnector connector = new SocketConnector();
        connector.setPort(startPort);
        return connector;
    }

    private static class MonitorThread extends Thread {

        private ServerSocket socket;

        public MonitorThread(int stopPort) {
            setDaemon(true);
            setName("StopMonitor");
            try {
                socket = new ServerSocket(stopPort, 1, InetAddress.getByName("127.0.0.1"));
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            System.out.println("*** running jetty 'stop' thread");
            Socket accept;
            try {
                accept = socket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                reader.readLine();
                System.out.println("*** stopping jetty embedded server");
                server.stop();
                accept.close();
                socket.close();
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
