package com.thoughtworks.videorental.acceptance;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class RentingAMovie {

	private Browser browser;

	public RentingAMovie(Browser browser) {
		this.browser = browser;
	}

	public void chooseMovie(String title) throws Exception {
		browser.checkbox(title).click();
	}

	public void clickNext() throws Exception {
		browser.button("Next >").click();
	}

	public void clickDone() throws Exception {
		browser.submit("Done").click();
	}

	public void verifyThatReceiptContains(String receiptContents) throws Exception {
		assertTrue(browser.preformatted(0).text()
				.contains(receiptContents));
	}

	public void chooseDays(Integer numberOfDays) throws Exception {
		browser.select("rentalDuration").choose(numberOfDays.toString());
	
	}


}
