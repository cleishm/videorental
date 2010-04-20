package com.thoughtworks.videorental.domain;

import static junit.framework.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.datetime.Duration;
import com.thoughtworks.datetime.LocalDate;
import com.thoughtworks.datetime.Period;

public class CustomerTest {

	private static final String RESOURCES_PATH = "src/unit/resources";

	private static final Movie python = new Movie(
			"Monty Python and the Holy Grail", Movie.REGULAR);
	private Movie ran = new Movie("Ran", Movie.REGULAR);
	private Movie la = new Movie("LA Confidential", Movie.NEW_RELEASE);
	private Movie trek = new Movie("Star Trek 13.2", Movie.NEW_RELEASE);
	private Movie wallace = new Movie("Wallace and Gromit", Movie.CHILDRENS);
	
	private Set<Rental> noRentals = Collections.emptySet();
	private Set<Rental> rentals;

	private Customer customer;

	@Before
	public void setUp() {

		customer = new Customer("John Smith");
		
		rentals = new LinkedHashSet<Rental>();
		rentals.add(new Rental(customer, python, Period.of(LocalDate.today(), Duration.ofDays(3))));
		rentals.add(new Rental(customer, ran, Period.of(LocalDate.today(), Duration.ofDays(1))));
		rentals.add(new Rental(customer, la, Period.of(LocalDate.today(), Duration.ofDays(2))));
		rentals.add(new Rental(customer, trek, Period.of(LocalDate.today(), Duration.ofDays(1))));
		rentals.add(new Rental(customer, wallace, Period.of(LocalDate.today(), Duration.ofDays(6))));
		
	}

	@Test
	public void testEmpty() throws Exception {
		String noRentalsStatement = 
			"Rental Record for John Smith\n"
			+ "Amount charged is $0.0\n" 
			+ "You have a new total of 0 frequent renter points";
		assertEquals(noRentalsStatement, customer.statement(noRentals));
	}

	@Test
	public void testCustomer() throws Exception {
		String expected = 
				"Rental Record for John Smith\n" 
				+ "  Monty Python and the Holy Grail  -  $3.5\n"
				+ "  Ran  -  $2.0\n"
				+ "  LA Confidential  -  $6.0\n"
				+ "  Star Trek 13.2  -  $3.0\n"
				+ "  Wallace and Gromit  -  $6.0\n"
				+ "Amount charged is $20.5\n"
				+ "You have a new total of 6 frequent renter points";
		assertEquals(expected, customer.statement(rentals));
	}

}
