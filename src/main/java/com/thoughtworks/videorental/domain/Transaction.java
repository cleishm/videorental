package com.thoughtworks.videorental.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Transaction {
	private final Customer customer;
	private final Set<Rental> rentals;

	public Transaction(final Customer customer, final Set<Rental> rentals) {
		for (final Rental rental : rentals) {
			if (rental.getCustomer() != customer) {
				throw new IllegalArgumentException("Rentals must be for the same customer");
			}
		}
		this.customer = customer;
		this.rentals = Collections.unmodifiableSet(new HashSet<Rental>(rentals));
	}

	public Customer getCustomer() {
		return customer;
	}

	public Set<Rental> getRentals() {
		return rentals;
	}
}
