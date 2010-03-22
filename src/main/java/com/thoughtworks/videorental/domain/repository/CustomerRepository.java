package com.thoughtworks.videorental.domain.repository;

import java.util.Set;

import com.thoughtworks.repository.NonUniqueObjectSelectedException;
import com.thoughtworks.repository.NullObjectAddedException;
import com.thoughtworks.specification.OrderComparator;
import com.thoughtworks.specification.Specification;
import com.thoughtworks.videorental.domain.Customer;

public interface CustomerRepository {
	void add(Customer customer) throws NullObjectAddedException;

	Set<Customer> selectAll();

	Set<Customer> selectAll(OrderComparator<Customer> comparator);

	Set<Customer> selectSatisfying(Specification<Customer> specification);

	Set<Customer> selectSatisfying(Specification<Customer> specification,
			OrderComparator<Customer> comparator);

	Customer selectUnique(Specification<Customer> specification)
			throws NonUniqueObjectSelectedException;
}