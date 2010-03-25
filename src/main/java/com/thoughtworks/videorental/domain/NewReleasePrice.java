package com.thoughtworks.videorental.domain;

public class NewReleasePrice extends Price {

	@Override
	public int getPriceCode() {
		return Movie.NEW_RELEASE;
	}

	public double getCharge(final int daysRented) {
		return daysRented * 3;
	}

}
