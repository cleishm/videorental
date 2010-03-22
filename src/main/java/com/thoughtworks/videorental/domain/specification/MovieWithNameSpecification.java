package com.thoughtworks.videorental.domain.specification;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.hibernate.Criteria;

import com.thoughtworks.specification.Specification;
import com.thoughtworks.videorental.domain.Movie;

public class MovieWithNameSpecification implements Specification<Movie> {

	private Collection<String> movieNames;

	public MovieWithNameSpecification(final String[] movieNames) {
		this.movieNames = new HashSet<String>(Arrays.asList(movieNames));
	}

	@Override
	public boolean isSatisfiedBy(final Movie movie) {
		return movieNames.contains(movie.getTitle());
	}

	@Override
	public void populateCriteria(final Criteria criteria) {
		throw new UnsupportedOperationException("not implemented");
	}

}
