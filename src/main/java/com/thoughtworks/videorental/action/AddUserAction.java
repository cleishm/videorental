package com.thoughtworks.videorental.action;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;

public class AddUserAction extends ActionSupport {

	private final CustomerRepository repository;
	private String name;

	public AddUserAction(CustomerRepository repository) {
		this.repository = repository;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String execute() throws Exception {
		repository.add(new Customer(name));
		return SUCCESS;
	}
	
	

}
