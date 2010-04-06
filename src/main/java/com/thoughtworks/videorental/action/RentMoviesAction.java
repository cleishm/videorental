package com.thoughtworks.videorental.action;

import java.util.LinkedHashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.util.Duration;
import com.thoughtworks.util.LocalDate;
import com.thoughtworks.util.Period;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.Rental;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.domain.repository.RentalRepository;
import com.thoughtworks.videorental.domain.specification.MovieWithTitleSpecification;
import com.thoughtworks.videorental.interceptor.CustomerAware;

public class RentMoviesAction extends ActionSupport implements CustomerAware {

	private final MovieRepository movieRepository;
	private final RentalRepository rentalRepository;

	private Customer customer;
	private String receipt;
	private String[] movieNames;
	private int rentalDuration;

	public RentMoviesAction(final MovieRepository movieRepository,
			final RentalRepository rentalRepository) {
		this.movieRepository = movieRepository;
		this.rentalRepository = rentalRepository;
	}
	
	@Override
	public void setCustomer(final Customer customer) {
		this.customer = customer;
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
		final Set<Movie> movies = movieRepository.selectSatisfying(new MovieWithTitleSpecification(movieNames));

		final Set<Rental> rentals = new LinkedHashSet<Rental>();
		for (final Movie movie : movies) {
			final Period rentalPeriod = Period.of(LocalDate.today(), Duration.ofDays(rentalDuration));
			final Rental rental = new Rental(customer, movie, rentalPeriod);
			rentals.add(rental);
		}
		
		rentalRepository.add(rentals);
		receipt = customer.statement(rentals);
		return SUCCESS;
	}

}
