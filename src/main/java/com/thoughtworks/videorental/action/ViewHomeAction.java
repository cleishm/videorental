package com.thoughtworks.videorental.action;

import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.interceptor.CustomerAware;

public class ViewHomeAction extends ActionSupport implements CustomerAware {

	private final MovieRepository movieRepository;
	private Customer customer;

	public ViewHomeAction(final MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public Set<Movie> getMovies() {
		Set<Movie> availableMovies = movieRepository.selectAll();
		
		/*
		customer.currentRentals()
		available.removeAll()
		 */
		
		return availableMovies;
	}
	
	@Override
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}
	
	public int getFrequentRenterPoints() {
		return customer.getFrequentRenterPoints();
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
