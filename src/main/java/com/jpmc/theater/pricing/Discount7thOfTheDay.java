package com.jpmc.theater.pricing;

import com.jpmc.theater.main.Showing;

/**
 * Discount for the movie showing 7th of the day.
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
public class Discount7thOfTheDay extends Discount {
	
	/**
	 * $1 discount for the movie showing 7th of the day.
	 */
	@Override
	public float getPrice(Showing showing, float regularPrice) {
		if (showing.getSequenceNo() == 7) {
			return (regularPrice < 1F) ? 0F : regularPrice - 1F;
		}
		return regularPrice;
	}

}
