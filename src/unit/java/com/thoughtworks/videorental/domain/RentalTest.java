package com.thoughtworks.videorental.domain;

import static junit.framework.Assert.*;

import org.junit.Test;

public class RentalTest {
	
	private static final Movie REGULAR_MOVIE = new Movie("Regular", Movie.REGULAR);

	@Test
    public void shouldCalculateCorrectChargeForRegularMovie() throws Exception {
    	assertEquals(2.0, new Rental(REGULAR_MOVIE, 1).getCharge());
    	assertEquals(2.0, new Rental(REGULAR_MOVIE, 2).getCharge());
    	assertEquals(3.5, new Rental(REGULAR_MOVIE, 3).getCharge());
    	assertEquals(5.0, new Rental(REGULAR_MOVIE, 4).getCharge());
    }
	
}
