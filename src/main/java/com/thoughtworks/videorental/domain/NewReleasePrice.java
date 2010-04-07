package com.thoughtworks.videorental.domain;

public class NewReleasePrice implements Price {

	public double getCharge(final int daysRented) {
		return ((int)(daysRented / 7) * 6 + (daysRented % 7)) * 3;
	}

	public int getFrequentRenterPoints(final int daysRented) {
		// add bonus for a two day new release rental
		if (daysRented > 1)
			return 2;
		else
			return 1;
	}

}
