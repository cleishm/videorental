package com.thoughtworks.videorental.domain.repository;

import java.util.Set;

import com.thoughtworks.repository.NonUniqueObjectSelectedException;
import com.thoughtworks.repository.NullObjectAddedException;
import com.thoughtworks.specification.OrderComparator;
import com.thoughtworks.specification.Specification;
import com.thoughtworks.videorental.domain.Movie;

public interface MovieRepository {
	void add(Movie movie) throws NullObjectAddedException;

	Set<Movie> selectAll();
	
	Set<Movie> selectAll(OrderComparator<Movie> comparator);

	Set<Movie> selectSatisfying(Specification<Movie> specification);

	Set<Movie> selectSatisfying(Specification<Movie> specification,
			OrderComparator<Movie> comparator);

	Movie selectUnique(Specification<Movie> specification)
			throws NonUniqueObjectSelectedException;
}