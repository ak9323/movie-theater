package com.jpmc.theater.main;

/**
 * Reservation class contains details of a reservation.
 * @updated March 28, 2023 by Andriy Kudryavtsev
 */
public class Reservation {
	private Showing showing;
	private Customer customer;
    private int ticketsCount;
    private float totalFee;

    public Reservation(Showing showing, Customer customer, int ticketsCount, float totalFee) {
    	this.showing = showing;
    	this.customer = customer;
    	this.ticketsCount = ticketsCount;
    	this.totalFee = totalFee;
    }

	public Showing getShowing() {
		return showing;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getTicketsCount() {
		return ticketsCount;
	}

	public float getTotalFee() {
		return totalFee;
	}
    
}
