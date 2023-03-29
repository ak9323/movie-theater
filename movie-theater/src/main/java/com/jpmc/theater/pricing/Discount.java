package com.jpmc.theater.pricing;

import com.jpmc.theater.main.Showing;

/**
 * Discount holds one ore more rules related to a specific discount. Given
 * the great variety of possibilities each discount has to be uniquely implemented
 * to take appropriate references and to include unique logic.
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
public abstract class Discount {
	
	/**
	 * Expected to return a discounted price or regular price if discount does not apply.
	 * @param showing - reference to a showing.
	 * @param regularPrice - regular price is used to calculate new price.
	 * @return discounted price if discount applies to the showing or regular price otherwise.
	 */
	public abstract float getPrice(Showing showing, float regularPrice);
	
}
