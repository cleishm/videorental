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

	private static final Customer customer = new Customer("John Smith");
	private static final Movie python = new Movie(
			"Monty Python and the Holy Grail", Movie.REGULAR);
	private static final Movie ran = new Movie("Ran", Movie.REGULAR);
	private static final Movie la = new Movie("LA Confidential", Movie.NEW_RELEASE);
	private static final Movie trek = new Movie("Star Trek 13.2", Movie.NEW_RELEASE);
	private static final Movie wallace = new Movie("Wallace and Gromit", Movie.CHILDRENS);
	private static final Set<Rental> EMPTY_RENTALS = Collections.emptySet();
	private static final Set<Rental> MIXED_RENTALS;

	static {
		final Set<Rental> rentals = new LinkedHashSet<Rental>();
		rentals.add(new Rental(customer, python, Period.of(LocalDate.today(), Duration.ofDays(3))));
		rentals.add(new Rental(customer, ran, Period.of(LocalDate.today(), Duration.ofDays(1))));
		rentals.add(new Rental(customer, la, Period.of(LocalDate.today(), Duration.ofDays(2))));
		rentals.add(new Rental(customer, trek, Period.of(LocalDate.today(), Duration.ofDays(1))));
		rentals.add(new Rental(customer, wallace, Period.of(LocalDate.today(), Duration.ofDays(6))));
		MIXED_RENTALS = Collections.unmodifiableSet(rentals);
	}

	@Before
	public void setUp() {
	}

	@Test
	public void testEmpty() throws Exception {
//		equalsFile("outputEmpty", customer.statement(new Transaction(customer, EMPTY_RENTALS)));
	}
//
//	@Test
//	public void testCustomer() throws Exception {
//		equalsFile("output1", customer.statement(new Transaction(customer, MIXED_RENTALS)));
//	}

	protected void equalsFile(String fileName, String actualValue) throws IOException {
		final BufferedReader file = new BufferedReader(new FileReader(RESOURCES_PATH + '/' + fileName));
		final BufferedReader actualStream = new BufferedReader(new StringReader(actualValue));
		
		String thisFileLine;
		while ((thisFileLine = file.readLine()) != null) {
			assertEquals("in file: " + fileName, thisFileLine, actualStream.readLine());
		}
	}

}
