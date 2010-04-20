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
			final Integer rentalDays = rental.getPeriod().getDuration().getDays();

			result += "  " + rental.getMovie().getTitle() + "  -  $"
					+ String.valueOf(rental.getMovie().getPrice().getCharge(rentalDays)) + "\n";

			totalAmount += rental.getMovie().getPrice().getCharge(rentalDays);

			frequentRenterPoints += rental.getMovie().getPrice().getFrequentRenterPoints(rentalDays);
		}

		// add footer lines
		result += "Amount charged is $" + String.valueOf(totalAmount) + "\n";
		result += "You have a new total of " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
