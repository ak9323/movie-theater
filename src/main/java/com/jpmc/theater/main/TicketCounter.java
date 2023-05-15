package com.jpmc.theater.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmc.theater.exception.TheaterException;
import com.jpmc.theater.pricing.Discount;
import com.jpmc.theater.pricing.PricePolicy;

/**
 * TicketCounter is responsible for reservations and pricing based on
 * price policy. It is the place for customer to buy tickets.
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
public class TicketCounter {
	private Theater theater;
	private PricePolicy pricePolicy;
	private Map<Showing, Integer> capacity;
	private List<Reservation> reservations;
	
	/**
	 * Base constructor, taking Theater and PricePolicy as a reference.
	 * @param theater - reference to Theater object to validate showing and capacity
	 * during reservation process
	 * @param pricePolicy - reference to PricePolicy for regular pricing and discounts;
	 */
	public TicketCounter(Theater theater, PricePolicy pricePolicy) {
		this.theater = theater;
		this.pricePolicy = pricePolicy;
		capacity = new HashMap<Showing, Integer>();
		reservations = new ArrayList<Reservation>();
	}
	
	/**
	 * Returns a new reservation for a specific showing, for a customer with the number of tickets
	 * specified.
	 * @param showing - reference to a showing.
	 * @param customer - reference to a customer.
	 * @param ticketsCount - number of tickets requested by the customer.
	 * @return Reservation object reference to which is also stored internally.
	 * @throws TheaterException thrown if showing is not at the theater or reservations for the showing
	 * is out of capacity.
	 */
	public Reservation getReservation(Showing showing, Customer customer, int ticketsCount) throws TheaterException {
		if (!theater.isValidShowing(showing))
			throw new TheaterException("No such showing at this theater: " + showing.toString());
		int seatsAvailable = getSeatsAvailable(showing);
		if (seatsAvailable < ticketsCount)
			throw new TheaterException("Out of capacity. Buying " + ticketsCount + " tickets while only "
					+ seatsAvailable + " seats available.");
		float ticketPrice = getTicketPrice(showing);
		Reservation reservation = new Reservation(showing, customer, ticketsCount, ticketPrice * ticketsCount);
		capacity.put(showing, seatsAvailable - ticketsCount);
		reservations.add(reservation);
		return reservation;
	}
	
	/**
	 * Returns ticket price for the showing after applying all possible discounts.
	 * @param showing - reference to the showing.
	 * @return lowest price after trying all discounts available.
	 */
	private float getTicketPrice(Showing showing) {
		float regularPrice = pricePolicy.getRugularPrice();
		float ticketPrice = regularPrice;
		for (Discount discount : pricePolicy.getDiscounts()) {
			float newPrice = discount.getPrice(showing, regularPrice);
			if (newPrice < ticketPrice) ticketPrice = newPrice; 	
		}
		return ticketPrice;
	}
	
	/**
	 * Checks seat availability
	 * @param showing - reference to a showing
	 * @return number of seats available for that showing
	 */
	private int getSeatsAvailable(Showing showing) {
		Integer seatsAvailable = capacity.get(showing);
		if (seatsAvailable == null) {
			capacity.put(showing, theater.getSeatCapacity());
			return theater.getSeatCapacity();
		} else {
			return seatsAvailable;
		}
	}
	
}
