package com.jpmc.theater.pricing;

import java.time.LocalTime;

import com.jpmc.theater.main.Showing;

/**
 * Discount for any movie showing starting between 11AM and 4PM.
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
public class Discount11amTo4pm extends Discount {
	
	/**
	 * 25% discount for any movie starting between 11AM and 4PM
	 */
	@Override
	public float getPrice(Showing showing, float regularPrice) {
		LocalTime start = LocalTime.of(10, 59);
		LocalTime end = LocalTime.of(16, 01);
		if (showing.getStartTime().isAfter(start) && showing.getStartTime().isBefore(end))
			return regularPrice * 0.75F;
		return regularPrice;
	}

}
