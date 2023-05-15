package com.jpmc.theater.main;

import java.util.List;

import com.jpmc.theater.exception.TheaterException;

/**
 * Scheduler is an interface that may have various implementations for a
 * purpose of scheduling movie showings based on theater business hours, duration
 * of intermission between movies and possibly other parameters.
 * @author Andriy Kudryavtsev
 * @since March 28, 2028
 */
public interface Scheduler {
	public abstract List<Showing> getSchedule() throws TheaterException;
}
