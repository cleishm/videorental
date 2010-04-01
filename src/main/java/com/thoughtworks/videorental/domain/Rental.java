package com.thoughtworks.videorental.domain;

import com.thoughtworks.util.Period;

public class Rental {
	private Movie movie;
	private Period period;

	public Rental(Movie movie, Period period) {
		this.movie = movie;
		this.period = period;
	}

	public Period getPeriod() {
		return period;
	}

	public Movie getMovie() {
		return movie;
	}
}