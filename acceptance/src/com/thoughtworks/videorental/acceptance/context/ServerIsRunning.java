package com.thoughtworks.videorental.acceptance.context;

import com.thoughtworks.jetty.JettyServer;

import net.sf.sahi.client.Browser;

public class ServerIsRunning {

	private JettyServer jettyServer;

	public ServerIsRunning(Browser browser) {
	}

	public void setUp() throws Exception {
		jettyServer = new JettyServer("../src/main/webapp", 8081, "/");
		jettyServer.start();
	}

	public void tearDown() throws Exception {
		jettyServer.stop();
	}

}
