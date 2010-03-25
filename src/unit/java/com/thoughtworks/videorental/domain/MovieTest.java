package com.thoughtworks.videorental.domain;

import static junit.framework.Assert.*;

import org.junit.Test;

public class MovieTest {
	
	private static final Movie REGULAR_MOVIE = new Movie("Regular", Movie.REGULAR);
	private static final Movie NEW_RELEASE_MOVIE = new Movie("NewRelease", Movie.NEW_RELEASE);
	private static final Movie CHILDRENS_MOVIE = new Movie("Childrens", Movie.CHILDRENS);

	@Test
    public void shouldCalculateCorrectChargeForRegularMovie() throws Exception {
    	assertEquals(2.0, REGULAR_MOVIE.getCharge(1));
    	assertEquals(2.0, REGULAR_MOVIE.getCharge(2));
    	assertEquals(3.5, REGULAR_MOVIE.getCharge(3));
    	assertEquals(5.0, REGULAR_MOVIE.getCharge(4));
    }
	
	@Test
    public void shouldCalculateCorrectChargeForNewReleaseMovie() throws Exception {
    	assertEquals(3.0, NEW_RELEASE_MOVIE.getCharge(1));
    	assertEquals(6.0, NEW_RELEASE_MOVIE.getCharge(2));
    	assertEquals(9.0, NEW_RELEASE_MOVIE.getCharge(3));
    }
	
	@Test
	public void shouldCalculateCorrectChargeForChildrensMovie() throws Exception {
		assertEquals(1.5, CHILDRENS_MOVIE.getCharge(1));
		assertEquals(1.5, CHILDRENS_MOVIE.getCharge(2));
		assertEquals(1.5, CHILDRENS_MOVIE.getCharge(3));
		assertEquals(3.0, CHILDRENS_MOVIE.getCharge(4));
		assertEquals(4.5, CHILDRENS_MOVIE.getCharge(5));
	}
	
	@Test
	public void shouldCalculateCorrentFrequentRenterPointsForNonNewReleaseMovie() throws Exception {
    	assertEquals(1, REGULAR_MOVIE.getFrequentRenterPoints(1));
    	assertEquals(1, REGULAR_MOVIE.getFrequentRenterPoints(4));
    	assertEquals(1, CHILDRENS_MOVIE.getFrequentRenterPoints(1));
    	assertEquals(1, CHILDRENS_MOVIE.getFrequentRenterPoints(4));
	}
	
	@Test
	public void shouldCalculateCorrentFrequentRenterPointsForNewReleaseMovie() throws Exception {
    	assertEquals(1, NEW_RELEASE_MOVIE.getFrequentRenterPoints(1));
    	assertEquals(2, NEW_RELEASE_MOVIE.getFrequentRenterPoints(2));
    	assertEquals(2, NEW_RELEASE_MOVIE.getFrequentRenterPoints(3));
	}
}
