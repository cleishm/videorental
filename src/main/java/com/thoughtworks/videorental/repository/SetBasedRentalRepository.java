package com.thoughtworks.videorental.repository;

import java.util.Comparator;
import java.util.Set;

import com.thoughtworks.repository.SetBasedRepository;
import com.thoughtworks.specification.OrderComparator;
import com.thoughtworks.specification.Specification;
import com.thoughtworks.videorental.domain.Rental;
import com.thoughtworks.videorental.domain.repository.RentalRepository;

public class SetBasedRentalRepository extends SetBasedRepository<Rental>
		implements RentalRepository {

	public SetBasedRentalRepository() {
		super();
	}

	@Override
	public Set<Rental> selectAll(OrderComparator<Rental> comparator) {
		return selectAll((Comparator<Rental>) comparator);
	}

	@Override
	public Set<Rental> selectSatisfying(
			final Specification<Rental> specification,
			final OrderComparator<Rental> comparator) {
		return selectSatisfying(specification, (Comparator<Rental>) comparator);
	}
}
