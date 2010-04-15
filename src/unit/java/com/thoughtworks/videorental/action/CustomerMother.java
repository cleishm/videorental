package com.thoughtworks.videorental.action;

import java.util.LinkedHashSet;
import java.util.Set;

import org.hamcrest.Matcher;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.repository.SetBasedCustomerRepository;

public class CustomerMother {

	public Set<Customer> defaultUsers() {
		Set<Customer> users = new LinkedHashSet<Customer>();
		users.add(new Customer("John Doe"));
		return users;
	}
	
	public CustomerRepository repository() {
		return new SetBasedCustomerRepository(defaultUsers());
	}

}
