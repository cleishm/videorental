package com.thoughtworks.videorental.acceptance.context;

import com.thoughtworks.jetty.JettyLauncher;

import net.sf.sahi.client.Browser;

public class ServerIsRunning {

	private Browser browser;

	public ServerIsRunning(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		JettyLauncher.start(8081, "../src/main/webapp");
	}

	public void tearDown() throws Exception {
		JettyLauncher.stop();
	}

}
