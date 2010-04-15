package com.thoughtworks.videorental.action;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class ViewAdminActionTest {

	@Test
	public void shouldShowAllUsers() {
		CustomerMother customerMother = new CustomerMother();
		
		ViewAdminAction action = new ViewAdminAction(customerMother.repository());
		assertThat(action.getUsers(), is(customerMother.defaultUsers()));
	}
	
}
