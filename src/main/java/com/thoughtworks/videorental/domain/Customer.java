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

		double totalAmount = 0;
		int frequentRenterPoints = 0;
		for (Rental rental : rentalList) {
			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t"
					+ String.valueOf(rental.getMovie().getPrice().getCharge(rental.getDaysRented())) + "\n";
			
			totalAmount += rental.getMovie().getPrice().getCharge(rental.getDaysRented());
			
			frequentRenterPoints += rental.getMovie().getPrice().getFrequentRenterPoints(rental.getDaysRented());
		}

		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

}
