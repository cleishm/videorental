package com.thoughtworks.videorental.action;

import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.domain.specification.CustomersOrderedByNameComparator;

public class ViewHomeAction extends ActionSupport {

	private final CustomerRepository customerRepository;
	private final MovieRepository movieRepository;

	public ViewHomeAction(final CustomerRepository customerRepository,
			final MovieRepository movieRepository) {
		this.customerRepository = customerRepository;
		this.movieRepository = movieRepository;
	}

	public Set<Customer> getCustomers() {
		return customerRepository.selectAll(new CustomersOrderedByNameComparator());
	}

	public Set<Movie> getMovies() {
		return movieRepository.selectAll();
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
