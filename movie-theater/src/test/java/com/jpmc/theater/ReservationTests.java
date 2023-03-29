package com.jpmc.theater;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.jpmc.theater.exception.TheaterException;
import com.jpmc.theater.main.Customer;
import com.jpmc.theater.main.Movie;
import com.jpmc.theater.main.MovieType;
import com.jpmc.theater.main.Reservation;
import com.jpmc.theater.main.Showing;
import com.jpmc.theater.main.Theater;
import com.jpmc.theater.main.TicketCounter;
import com.jpmc.theater.pricing.Discount;
import com.jpmc.theater.pricing.Discount11amTo4pm;
import com.jpmc.theater.pricing.Discount1stOfTheDay;
import com.jpmc.theater.pricing.Discount2ndOfTheDay;
import com.jpmc.theater.pricing.Discount7thOfTheDay;
import com.jpmc.theater.pricing.DiscountSpecial20pct;
import com.jpmc.theater.pricing.PricePolicy;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Reservation tests
 * @updated March 28, 2023 by Andriy Kudryavtsev
 */
public class ReservationTests {
	static List<Movie> movies;
	static List<Discount> discounts;
	static Theater theater;
	
	@BeforeAll
	public static void setUp() throws TheaterException {
		movies = List.of(
	            new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
	            new Movie(2, "Creed III", Duration.ofMinutes(116), null),
	            new Movie(3, "Scream VI", Duration.ofMinutes(123), null, MovieType.SPECIAL),
	            new Movie(4, "65", Duration.ofMinutes(93), null),
	            new Movie(5, "Ant-Man and the Wasp: Quantumania", Duration.ofMinutes(125), null, MovieType.SPECIAL),
	            new Movie(6, "Santiago: The Camino Within", Duration.ofMinutes(123), null)
	        );
		discounts = List.of(
				new DiscountSpecial20pct(),
				new Discount1stOfTheDay(),
				new Discount2ndOfTheDay(),
				new Discount11amTo4pm(),
				new Discount7thOfTheDay()
			);
		PricePolicy pricePolicy = new PricePolicy(15.00F);
		theater = new Theater(movies, pricePolicy);
	}

    @Test
    public void totalReservationFee() throws TheaterException {
    	TicketCounter tc = theater.getTicketCounter();
    	List<Showing> showings = theater.getSchedule();
    	Customer customer = new Customer(1, "James Doe");
    	Reservation reservation = tc.getReservation(showings.get(0), customer, 3);
        assertTrue(Math.abs(reservation.getTotalFee() - 45.00F) < 0.001F);
    }
    
    @Test
    public void overSeatingCapacityReservation() {
    	TicketCounter tc = theater.getTicketCounter();
    	List<Showing> showings = theater.getSchedule();
    	Customer customer = new Customer(1, "James Doe");
    	Assertions.assertThrows(TheaterException.class, () ->
    		tc.getReservation(showings.get(1), customer, theater.getSeatCapacity() + 1));
    }
    
}
