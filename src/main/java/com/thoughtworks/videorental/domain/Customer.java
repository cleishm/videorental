package com.thoughtworks.videorental.domain;

import java.util.Set;

public class Customer {
	private String name;
	private int frequentRenterPoints = 0;

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String statement(final Set<Rental> newRentals) {
		String result = "Rental Record for " + getName() + "\n";

		double totalAmount = 0;
		for (Rental rental : newRentals) {
			// show figures for this rental
			result += "  " + rental.getMovie().getTitle() + "  -  $"
					+ String.valueOf(rental.getMovie().getPrice().getCharge(rental.getDaysRented())) + "\n";
			
			totalAmount += rental.getMovie().getPrice().getCharge(rental.getDaysRented());
			
			frequentRenterPoints += rental.getMovie().getPrice().getFrequentRenterPoints(rental.getDaysRented());
		}

		// add footer lines
		result += "Amount charged is $" + String.valueOf(totalAmount) + "\n";
		result += "You have a new total of " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

}
