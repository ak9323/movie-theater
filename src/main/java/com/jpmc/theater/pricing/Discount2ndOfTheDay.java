package com.jpmc.theater.pricing;

import com.jpmc.theater.main.Showing;

/**
 * Discount for the movie showing 2nd of the day.
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
public class Discount2ndOfTheDay extends Discount {
	
	/**
	 * $2 discount for the movie showing 2nd of the day.
	 */
	@Override
	public float getPrice(Showing showing, float regularPrice) {
		if (showing.getSequenceNo() == 2) {
			return (regularPrice < 2F) ? 0F : regularPrice - 2F;
		}
		return regularPrice;
	}

}
