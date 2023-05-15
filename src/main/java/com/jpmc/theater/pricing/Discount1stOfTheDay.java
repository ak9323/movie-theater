package com.jpmc.theater.pricing;

import com.jpmc.theater.main.Showing;

/**
 * Discount for the movie showing 1st of the day.
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
public class Discount1stOfTheDay extends Discount {
	
	/**
	 * $3 discount for the movie showing 1st of the day.
	 */
	@Override
	public float getPrice(Showing showing, float regularPrice) {
		if (showing.getSequenceNo() == 1) {
			return (regularPrice < 3F) ? 0F : regularPrice - 3F;
		}
		return regularPrice;
	}

}
