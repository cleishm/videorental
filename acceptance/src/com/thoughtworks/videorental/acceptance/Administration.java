package com.thoughtworks.videorental.acceptance;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

public class Administration {

	private Browser browser;

	public Administration(Browser browser) {
		this.browser = browser;
	}

	public void addAUserNamed(String string1) throws Exception {
		browser.navigateTo("http://localhost:8081/admin");
		browser.textbox("name").setValue(string1);
		browser.submit("Add").click();
	}

}
