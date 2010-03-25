package com.thoughtworks.videorental.domain;

import java.util.ArrayList;

public class Customer {
	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentalList) {
			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t"
					+ String.valueOf(rental.getCharge()) + "\n";
		}
		double totalAmount = getTotalCharge();
		int frequentRenterPoints = getTotalFrequentRenterPoints();

		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	private int getTotalFrequentRenterPoints() {
		int frequentRenterPoints = 0;
		for (Rental rental : rentalList) {
			frequentRenterPoints += rental.getFrequentRenterPoints();
		}
		return frequentRenterPoints;
	}

	private double getTotalCharge() {
		double totalAmount = 0;
		for (Rental rental : rentalList) {
			totalAmount += rental.getCharge();
		}
		return totalAmount;
	}

}
