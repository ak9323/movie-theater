package com.jpmc.theater.pricing;

import com.jpmc.theater.main.MovieType;
import com.jpmc.theater.main.Showing;

/**
 * Implementation of the 20% discount for the special movie.
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
public class DiscountSpecial20pct extends Discount {
	
	/**
	 * Applies 20% discount to the regular price if the movie type is special.
	 */
	@Override
	public float getPrice(Showing showing, float regularPrice) {
		if (showing.getMovie().getMovieType() == MovieType.SPECIAL) return regularPrice * 0.8F;
		return regularPrice;
	}

}
