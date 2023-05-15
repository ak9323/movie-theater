package com.jpmc.theater;

import java.time.Duration;
import java.util.List;

import com.jpmc.theater.main.Customer;
import com.jpmc.theater.main.Movie;
import com.jpmc.theater.main.MovieType;
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


/**
 * Theater bootstrapping example, plus basic functionality examples.
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
public class TheaterExamples {

	public static void main(String[] args) throws Exception {
		
		List<Movie> movies = List.of(
	            new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
	            new Movie(2, "Creed III", Duration.ofMinutes(116), null),
	            new Movie(3, "Scream VI", Duration.ofMinutes(123), null, MovieType.SPECIAL),
	            new Movie(4, "65", Duration.ofMinutes(93), null),
	            new Movie(5, "Ant-Man and the Wasp: Quantumania", Duration.ofMinutes(125), null, MovieType.SPECIAL),
	            new Movie(6, "Santiago: The Camino Within", Duration.ofMinutes(123), null)
	        );
		
		List<Discount> discounts = List.of(
				new DiscountSpecial20pct(),
				new Discount1stOfTheDay(),
				new Discount2ndOfTheDay(),
				new Discount11amTo4pm(),
				new Discount7thOfTheDay()
			);
		
		
		PricePolicy pricePolicy = new PricePolicy(15.00F, discounts);
		
		Theater theater = new Theater(movies, pricePolicy);
		System.out.println("================ TEXT SCHEDULE ================");
		theater.printScheduleText(System.out);
		System.out.println("================ JSON SCHEDULE ================");
		theater.printScheduleJson(System.out);
		
		TicketCounter tc = theater.getTicketCounter();
		List<Showing> showings = theater.getSchedule();
		Customer customer = new Customer(1, "James Doe");
		
		tc.getReservation(showings.get(2), customer, 1);
		
	}

}
