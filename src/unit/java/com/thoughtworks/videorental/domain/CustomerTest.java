package com.thoughtworks.videorental.domain;

import static junit.framework.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.Rental;

public class CustomerTest {

	private static final String RESOURCES_PATH = "src/unit/resources";

    private Customer dinsdale = new Customer("Dinsdale Pirhana");

    private Movie python = new Movie("Monty Python and the Holy Grail", Movie.REGULAR);
	private Movie ran = new Movie("Ran", Movie.REGULAR);
	private Movie la = new Movie("LA Confidential", Movie.NEW_RELEASE);
	private Movie trek = new Movie("Star Trek 13.2", Movie.NEW_RELEASE);
	private Movie wallace = new Movie("Wallace and Gromit", Movie.CHILDRENS);

	@Before
    public void setUp() {
       dinsdale.addRental(new Rental(python, 3));
       dinsdale.addRental(new Rental(ran, 1));
       dinsdale.addRental(new Rental(la, 2));
       dinsdale.addRental(new Rental(trek, 1));
       dinsdale.addRental(new Rental(wallace, 6));
   }

	@Test
    public void testEmpty() throws Exception {
    	dinsdale = new Customer("Dinsdale Pirhana");
        equalsFile("1st Output", "outputEmpty", dinsdale.statement());
    }
	
	@Test
    public void testCustomer() throws Exception {
        equalsFile("1st Output", "output1", dinsdale.statement());
    }

	@Test
    public void testChange() throws Exception {
    	la.setPriceCode(Movie.REGULAR);
        equalsFile("1st Output", "outputChange", dinsdale.statement());
    }
    	
    protected void equalsFile(String message, String fileName, String actualValue) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(RESOURCES_PATH + '/' + fileName));
        BufferedReader actualStream = new BufferedReader(new StringReader(actualValue));
        String thisFileLine = null;
        while ((thisFileLine = file.readLine()) != null) {
            assertEquals("in file: " + fileName, thisFileLine, actualStream.readLine());
        }
    }

}
