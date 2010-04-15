package com.thoughtworks.videorental.action;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;


public class AddUserActionTest {

	@Test
	public void shouldAddUserToRepository() throws Exception {
		CustomerMother customerMother = new CustomerMother();
		CustomerRepository repository = customerMother.repository();
		
		AddUserAction action = new AddUserAction(repository);
		
		action.setName("A New User");
		assertThat(action.execute(), Matchers.is(ActionSupport.SUCCESS));
		
		assertThat(repository.selectAll(), hasItem(new Customer("A New User")));
	}
	
}
