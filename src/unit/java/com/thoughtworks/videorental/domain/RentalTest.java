package com.thoughtworks.videorental.domain;

import static junit.framework.Assert.*;

import org.junit.Test;

public class RentalTest {
	
	private static final Movie REGULAR_MOVIE = new Movie("Regular", Movie.REGULAR);
	private static final Movie NEW_RELEASE_MOVIE = new Movie("NewRelease", Movie.NEW_RELEASE);
	private static final Movie CHILDRENS_MOVIE = new Movie("Childrens", Movie.CHILDRENS);
	
	@Test
	public void shouldCalculateCorrentFrequentRenterPointsForNonNewReleaseMovie() throws Exception {
    	assertEquals(1, new Rental(REGULAR_MOVIE, 1).getFrequentRenterPoints());
    	assertEquals(1, new Rental(REGULAR_MOVIE, 4).getFrequentRenterPoints());
    	assertEquals(1, new Rental(CHILDRENS_MOVIE, 1).getFrequentRenterPoints());
    	assertEquals(1, new Rental(CHILDRENS_MOVIE, 4).getFrequentRenterPoints());
	}
	
	@Test
	public void shouldCalculateCorrentFrequentRenterPointsForNewReleaseMovie() throws Exception {
    	assertEquals(1, new Rental(NEW_RELEASE_MOVIE, 1).getFrequentRenterPoints());
    	assertEquals(2, new Rental(NEW_RELEASE_MOVIE, 2).getFrequentRenterPoints());
    	assertEquals(2, new Rental(NEW_RELEASE_MOVIE, 3).getFrequentRenterPoints());
	}
}
