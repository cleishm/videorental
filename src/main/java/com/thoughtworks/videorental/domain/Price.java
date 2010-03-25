package com.thoughtworks.videorental.domain;

public interface Price {
	int getPriceCode();
	double getCharge(int daysRented);
	int getFrequentRenterPoints(int daysRented);
}
