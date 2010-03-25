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
			double thisAmount = rental.getCharge();

			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
		}
		double totalAmount = 0;
		for (Rental rental : rentalList) {
			double thisAmount = rental.getCharge();
			totalAmount += thisAmount;
		}
		int frequentRenterPoints = 0;
		for (Rental rental : rentalList) {
			frequentRenterPoints += rental.getFrequentRenterPoints();
		}

		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

}
