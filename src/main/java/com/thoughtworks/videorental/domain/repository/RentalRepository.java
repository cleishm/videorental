package com.thoughtworks.videorental.domain.repository;

import java.util.Collection;
import java.util.Set;

import com.thoughtworks.repository.NonUniqueObjectSelectedException;
import com.thoughtworks.repository.NullObjectAddedException;
import com.thoughtworks.specification.OrderComparator;
import com.thoughtworks.specification.Specification;
import com.thoughtworks.videorental.domain.Rental;

public interface RentalRepository {
	void add(Rental entity) throws NullObjectAddedException;

	void add(Collection<Rental> entities) throws NullObjectAddedException;

	Set<Rental> selectAll();

	Set<Rental> selectAll(OrderComparator<Rental> comparator);

	Set<Rental> selectSatisfying(Specification<Rental> specification);

	Set<Rental> selectSatisfying(Specification<Rental> specification,
			OrderComparator<Rental> comparator);

	Rental selectUnique(Specification<Rental> specification)
			throws NonUniqueObjectSelectedException;
}