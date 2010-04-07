package com.thoughtworks.videorental.action;

import static junit.framework.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.datetime.Duration;
import com.thoughtworks.datetime.LocalDate;
import com.thoughtworks.datetime.LocalDateTime;
import com.thoughtworks.datetime.Period;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.Rental;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.domain.repository.RentalRepository;
import com.thoughtworks.videorental.repository.SetBasedMovieRepository;

public class RentMoviesActionTest {
	private static final Movie THE_GODFATHER = new Movie("The Godfather", Movie.REGULAR);
	private static final Movie PULP_FICTION = new Movie("Pulp Fiction", Movie.REGULAR);;
	
	private MovieRepository movieRepository;
	private RentalRepository rentalRepository;
	private RentMoviesAction rentMoviesAction;
	private Customer customer;

	@Before
	public void setUp() throws Exception {
		movieRepository = new SetBasedMovieRepository();
		movieRepository.add(THE_GODFATHER);
		movieRepository.add(PULP_FICTION);

		rentalRepository = mock(RentalRepository.class);
		rentMoviesAction = new RentMoviesAction(movieRepository,
				rentalRepository);
		customer = mock(Customer.class);
		rentMoviesAction.setCustomer(customer);
	}
	
	@Before
	public void fixDate() {
		LocalDateTime.setSystemDateTime(LocalDateTime.now());
	}
	
	@After
	public void resetDate() {
		LocalDateTime.resetSystemDateTime();
	}

	@Test
	public void shouldCreateRentalForEachMovie() throws Exception {
		rentMoviesAction.setMovieNames(new String[] { THE_GODFATHER.getTitle(), PULP_FICTION.getTitle() });
		final int days = 1;
		rentMoviesAction.setRentalDuration(days);
		rentMoviesAction.execute();
		
		verify(rentalRepository).add(argThat(isRentalsForDurationAndOf(days, THE_GODFATHER, PULP_FICTION)));
	}
	
	@Test
	public void shouldRetrieveCustomerStatement() throws Exception {
		rentMoviesAction.setMovieNames(new String[] { THE_GODFATHER.getTitle(), PULP_FICTION.getTitle() });
		final int days = 1;
		rentMoviesAction.setRentalDuration(days);
		
		final String receipt = "my receipt";
		when(customer.statement(argThat(isRentalsForDurationAndOf(1, THE_GODFATHER, PULP_FICTION)))).thenReturn(receipt);
		rentMoviesAction.execute();
		
		assertEquals(receipt, rentMoviesAction.getReceipt());
	}

	@SuppressWarnings("unchecked")
	private Matcher<Set<Rental>> isRentalsForDurationAndOf(final int days, final Movie firstMovie, final Movie... movies) {
		final Period period = Period.of(LocalDate.today(), Duration.ofDays(days));

		final List rentalMatchers = new ArrayList();
		rentalMatchers.add(hasSize(movies.length + 1));
		rentalMatchers.add(hasItem(allOf(hasProperty("period", equalTo(period)), hasProperty("movie", sameInstance(firstMovie)))));
		for (final Movie movie : movies) {
			rentalMatchers.add(hasItem(allOf(hasProperty("period", equalTo(period)), hasProperty("movie", sameInstance(movie)))));
		}

		return allOf((Iterable) rentalMatchers);
	}

}
