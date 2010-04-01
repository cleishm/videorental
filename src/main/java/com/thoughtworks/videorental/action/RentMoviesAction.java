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
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.domain.repository.RentalRepository;
import com.thoughtworks.videorental.domain.specification.CustomerWithNameSpecification;
import com.thoughtworks.videorental.domain.specification.MovieWithNameSpecification;

public class RentMoviesAction extends ActionSupport {

	private final CustomerRepository customerRepository;
	private final MovieRepository movieRepository;
	private final RentalRepository rentalRepository;

	private String customerName;
	private String receipt;
	private String[] movieNames;
	private int rentalDuration;

	public RentMoviesAction(final CustomerRepository customerRepository,
			final MovieRepository movieRepository,
			final RentalRepository rentalRepository) {
		this.customerRepository = customerRepository;
		this.movieRepository = movieRepository;
		this.rentalRepository = rentalRepository;
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

		final Set<Rental> rentals = new LinkedHashSet<Rental>();
		for (final Movie movie : movies) {
			final Period rentalPeriod = Period.of(LocalDate.today(), Duration.ofDays(rentalDuration));
			final Rental rental = new Rental(movie, rentalPeriod);
			rentals.add(rental);
		}
		
		rentalRepository.add(rentals);
		receipt = customer.statement(rentals);
		return SUCCESS;
	}
}
