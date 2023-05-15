package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import com.jpmc.theater.exception.TheaterException;
import com.jpmc.theater.main.Movie;
import com.jpmc.theater.main.MovieType;
import com.jpmc.theater.main.Showing;
import com.jpmc.theater.pricing.Discount;
import com.jpmc.theater.pricing.Discount11amTo4pm;
import com.jpmc.theater.pricing.Discount1stOfTheDay;
import com.jpmc.theater.pricing.Discount2ndOfTheDay;
import com.jpmc.theater.pricing.Discount7thOfTheDay;
import com.jpmc.theater.pricing.DiscountSpecial20pct;

/**
 * Tests associated with pricing and discounts
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
class PricingTests {

	@Test
	public void DiscountSpecial20pct() throws TheaterException {
		float regularPrice = 15F;
		Showing showing = new Showing(1,
				new Movie(3, "Scream VI", Duration.ofMinutes(123), null, MovieType.SPECIAL),
				LocalTime.of(11, 45));
		Discount discount = new DiscountSpecial20pct();
		float newPrice = discount.getPrice(showing, regularPrice);
		assertTrue(Math.abs(regularPrice * 0.8F - newPrice) < 0.001F);
	}
	
	@Test
	public void Discount1stOfTheDay() throws TheaterException {
		float regularPrice = 15F;
		Showing showing = new Showing(1,
				new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
				LocalTime.of(8, 00));
		Discount discount = new Discount1stOfTheDay();
		float newPrice = discount.getPrice(showing, regularPrice);
		assertTrue(Math.abs(regularPrice - 3F - newPrice) < 0.001F);
	}
	
	@Test
	public void Discount2ndOfTheDay() throws TheaterException {
		float regularPrice = 15F;
		Showing showing = new Showing(2,
				new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
				LocalTime.of(9, 45));
		Discount discount = new Discount2ndOfTheDay();
		float newPrice = discount.getPrice(showing, regularPrice);
		assertTrue(Math.abs(regularPrice - 2F - newPrice) < 0.001F);
	}
	
	@Test
	public void Discount11amTo4pm() throws TheaterException {
		float regularPrice = 15F;
		Movie movie = new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null);
		Showing showing1 = new Showing(1, movie, LocalTime.of(10, 59));
		Showing showing2 = new Showing(2, movie, LocalTime.of(11, 00));
		Showing showing3 = new Showing(3, movie, LocalTime.of(16, 00));
		Showing showing4 = new Showing(4, movie, LocalTime.of(16, 01));
		Discount discount = new Discount11amTo4pm();
		float newPrice1 = discount.getPrice(showing1, regularPrice);
		float newPrice2 = discount.getPrice(showing2, regularPrice);
		float newPrice3 = discount.getPrice(showing3, regularPrice);
		float newPrice4 = discount.getPrice(showing4, regularPrice);
		assertTrue(Math.abs(regularPrice - newPrice1) < 0.001F);
		assertTrue(Math.abs(regularPrice * 0.75F - newPrice2) < 0.001F);
		assertTrue(Math.abs(regularPrice * 0.75F - newPrice3) < 0.001F);
		assertTrue(Math.abs(regularPrice - newPrice4) < 0.001F);
	}
	
	@Test
	public void Discount7thOfTheDay() throws TheaterException {
		float regularPrice = 15F;
		Showing showing = new Showing(7,
				new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
				LocalTime.of(16, 00));
		Discount discount = new Discount7thOfTheDay();
		float newPrice = discount.getPrice(showing, regularPrice);
		assertTrue(Math.abs(regularPrice - 1F - newPrice) < 0.001F);
	}
	
	@Test
	public void Discount1stOfTheDayZero() throws TheaterException {
		float regularPrice = 3F;
		Showing showing = new Showing(1,
				new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
				LocalTime.of(8, 00));
		Discount discount = new Discount1stOfTheDay();
		float newPrice = discount.getPrice(showing, regularPrice);
		assertTrue(Math.abs(0F - newPrice) < 0.001F);
	}
	
	@Test
	public void Discount2ndOfTheDayZero() throws TheaterException {
		float regularPrice = 2F;
		Showing showing = new Showing(2,
				new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
				LocalTime.of(9, 45));
		Discount discount = new Discount2ndOfTheDay();
		float newPrice = discount.getPrice(showing, regularPrice);
		assertTrue(Math.abs(0F - newPrice) < 0.001F);
	}
	
	@Test
	public void Discount7thOfTheDayZero() throws TheaterException {
		float regularPrice = 1F;
		Showing showing = new Showing(7,
				new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
				LocalTime.of(16, 00));
		Discount discount = new Discount7thOfTheDay();
		float newPrice = discount.getPrice(showing, regularPrice);
		assertTrue(Math.abs(0F - newPrice) < 0.001F);
	}
	
	@Test
	public void Discount1stOfTheDayNegative() throws TheaterException {
		float regularPrice = 0.5F;
		Showing showing = new Showing(1,
				new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
				LocalTime.of(8, 00));
		Discount discount = new Discount1stOfTheDay();
		float newPrice = discount.getPrice(showing, regularPrice);
		assertTrue(Math.abs(0F - newPrice) < 0.001F);
	}
	
	@Test
	public void Discount2ndOfTheDayNegative() throws TheaterException {
		float regularPrice = 0.5F;
		Showing showing = new Showing(2,
				new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
				LocalTime.of(9, 45));
		Discount discount = new Discount2ndOfTheDay();
		float newPrice = discount.getPrice(showing, regularPrice);
		assertTrue(Math.abs(0F - newPrice) < 0.001F);
	}
	
	@Test
	public void Discount7thOfTheDayNegative() throws TheaterException {
		float regularPrice = 0.5F;
		Showing showing = new Showing(7,
				new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
				LocalTime.of(16, 00));
		Discount discount = new Discount7thOfTheDay();
		float newPrice = discount.getPrice(showing, regularPrice);
		assertTrue(Math.abs(0F - newPrice) < 0.001F);
	}

	@Test
	public void Discount7thOfTheDayNegative2() throws TheaterException {
		//Copy of preceding method to test git functionality.
		float regularPrice = 0.5F;
		Showing showing = new Showing(7,
				new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
				LocalTime.of(16, 00));
		Discount discount = new Discount7thOfTheDay();
		float newPrice = discount.getPrice(showing, regularPrice);
		assertTrue(Math.abs(0F - newPrice) < 0.001F);
	}
	
}
