package com.jpmc.theater.pricing;

import java.util.ArrayList;
import java.util.List;

/**
 * PricePolicy is a collection of discounts which are being applied to a
 * regular price held by this class.
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
public class PricePolicy {
	private float regularPrice;
	private List<Discount> discounts;
	
	
	public PricePolicy(float regularPrice) {
		this.regularPrice = regularPrice;
		discounts = new ArrayList<Discount>();
	}
	
	public PricePolicy(float regularPrice, List<Discount> discounts) {
		this.regularPrice = regularPrice;
		this.discounts = discounts;
	}
	
	public float getRugularPrice() {
		return regularPrice;
	}
	
	public void addDiscount(Discount discount) {
		discounts.add(discount);
	}
	
	public List<Discount> getDiscounts() {
		return discounts;
	}
	
}
