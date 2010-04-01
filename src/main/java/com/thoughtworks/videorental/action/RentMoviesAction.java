package com.thoughtworks.videorental.action;

import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.Rental;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.domain.specification.CustomerWithNameSpecification;
import com.thoughtworks.videorental.domain.specification.MovieWithNameSpecification;

public class RentMoviesAction extends ActionSupport {

	private final CustomerRepository customerRepository;
	private final MovieRepository movieRepository;
	
	private String customerName;
	private String receipt;
	private String[] movieNames;
	private int rentalDuration;

	public RentMoviesAction(final CustomerRepository customerRepository, final MovieRepository movieRepository) {
		this.customerRepository = customerRepository;
		this.movieRepository = movieRepository;
	}
	
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}
	
	public void setMovieNames(final String[] movieNames) {
		this.movieNames = movieNames;
	}
	
	public void setRentalDuration(final int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public String getReceipt() {
		return receipt;
	}

	@Override
	public String execute() throws Exception {
		final Customer customer = customerRepository.selectUnique(new CustomerWithNameSpecification(customerName));
		final Set<Movie> movies = movieRepository.selectSatisfying(new MovieWithNameSpecification(movieNames));
		
		final Set<Rental> rentals = new HashSet<Rental>();
		for (final Movie movie : movies) {
			rentals.add(new Rental(movie, rentalDuration));
		}
		
		receipt = customer.statement(rentals);
		return SUCCESS;
	}
}
