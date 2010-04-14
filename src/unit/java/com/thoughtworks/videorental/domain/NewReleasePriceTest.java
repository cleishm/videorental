package com.thoughtworks.videorental.domain;

import static junit.framework.Assert.*;

import org.junit.Test;

public class NewReleasePriceTest {
	private static final Price NEW_RELEASE_PRICE = new NewReleasePrice();

	@Test
    public void shouldCalculateCorrectChargeForNewReleaseMovieRentedLessThanAWeek() throws Exception {
    	assertEquals(3.0, NEW_RELEASE_PRICE.getCharge(1));
    	assertEquals(6.0, NEW_RELEASE_PRICE.getCharge(2));
    	assertEquals(9.0, NEW_RELEASE_PRICE.getCharge(3));
    }
	
	@Test
	public void shouldGiveDiscountOfOneDayPerWeekRented() throws Exception {
		assertEquals(18.0, NEW_RELEASE_PRICE.getCharge(6));
		assertEquals(18.0, NEW_RELEASE_PRICE.getCharge(7));
		assertEquals(36.0, NEW_RELEASE_PRICE.getCharge(14));
		assertEquals(54.0, NEW_RELEASE_PRICE.getCharge(21));
	}

	@Test
	public void shouldGiveDiscountOfOneDayEachWeekForMultipleWeeks() throws Exception {
		assertEquals(36.0, NEW_RELEASE_PRICE.getCharge(13));
		assertEquals(36.0, NEW_RELEASE_PRICE.getCharge(14));
		assertEquals(54.0, NEW_RELEASE_PRICE.getCharge(20));
		assertEquals(54.0, NEW_RELEASE_PRICE.getCharge(21));
	}
}
