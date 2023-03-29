package com.jpmc.theater.main;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.jpmc.theater.exception.TheaterException;

/**
 * Schedules showings based on the list of movies. It is a simple scheduler in that it
 * will only fill up business hours of the theater with movies out of the list provided.
 * If the list is too short it will continue in a round-robin fashion. If the movies
 * list is too long it will stop as to prevent going over the business hours.
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 *
 */
public class SequentialScheduler implements Scheduler {
	private Theater theater;
	private List<Movie> movies;
	
	public SequentialScheduler(Theater theater, List<Movie> movies) throws TheaterException {
		if (movies.isEmpty()) throw new TheaterException("List of movies is empty. Must have at least one movie.");
		this.theater = theater;
		this.movies = movies;
	}
	
	/**
	 * Returns a list of showing schedule.
	 */
	@Override
	public List<Showing> getSchedule() throws TheaterException {
		List<Showing> schedule = new ArrayList<Showing>();
		int counter = 0;
		int sequenceNo = 0;
		while (counter < Integer.MAX_VALUE) {
			int index = counter % movies.size();
			if (schedule.isEmpty()) {
				schedule.add(new Showing(++sequenceNo, movies.get(index), theater.getOpensAt()));
			} else {
				Movie nextMovie = movies.get(index);
				Showing lastShowing = schedule.get(schedule.size() - 1);
				LocalTime expectedEndTime = lastShowing.getEndTime()
						.plusMinutes(theater.getIntermission().toMinutes())
						.plusMinutes(nextMovie.getRunningTime().toMinutes());
				if (expectedEndTime.compareTo(theater.getClosesAt()) > 0
						|| expectedEndTime.getHour() < theater.getOpensAt().getHour()) break;
				schedule.add(new Showing(++sequenceNo, nextMovie, lastShowing.getEndTime()
						.plusMinutes(theater.getIntermission().toMinutes())));
			}
			counter++;
		}
		return schedule;
	}

}
