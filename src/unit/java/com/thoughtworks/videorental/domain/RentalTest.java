package com.thoughtworks.videorental.domain;

import static junit.framework.Assert.*;

import org.junit.Test;

public class RentalTest {
	
	private static final Movie REGULAR_MOVIE = new Movie("Regular", Movie.REGULAR);
	private static final Movie NEW_RELEASE_MOVIE = new Movie("NewRelease", Movie.NEW_RELEASE);
	private static final Movie CHILDRENS_MOVIE = new Movie("Childrens", Movie.CHILDRENS);

	@Test
    public void shouldCalculateCorrectChargeForRegularMovie() throws Exception {
    	assertEquals(2.0, new Rental(REGULAR_MOVIE, 1).getCharge());
    	assertEquals(2.0, new Rental(REGULAR_MOVIE, 2).getCharge());
    	assertEquals(3.5, new Rental(REGULAR_MOVIE, 3).getCharge());
    	assertEquals(5.0, new Rental(REGULAR_MOVIE, 4).getCharge());
    }
	
	@Test
    public void shouldCalculateCorrectChargeForNewReleaseMovie() throws Exception {
    	assertEquals(3.0, new Rental(NEW_RELEASE_MOVIE, 1).getCharge());
    	assertEquals(6.0, new Rental(NEW_RELEASE_MOVIE, 2).getCharge());
    	assertEquals(9.0, new Rental(NEW_RELEASE_MOVIE, 3).getCharge());
    }
	
	@Test
	public void shouldCalculateCorrectChargeForChildrensMovie() throws Exception {
		assertEquals(1.5, new Rental(CHILDRENS_MOVIE, 1).getCharge());
		assertEquals(1.5, new Rental(CHILDRENS_MOVIE, 2).getCharge());
		assertEquals(1.5, new Rental(CHILDRENS_MOVIE, 3).getCharge());
		assertEquals(3.0, new Rental(CHILDRENS_MOVIE, 4).getCharge());
		assertEquals(4.5, new Rental(CHILDRENS_MOVIE, 5).getCharge());
	}
}
