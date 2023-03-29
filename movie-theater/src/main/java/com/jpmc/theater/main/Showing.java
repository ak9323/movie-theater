package com.jpmc.theater.main;

import java.time.LocalTime;
import java.util.Objects;

import com.jpmc.theater.exception.TheaterException;

/**
 * Showing is a movie starting at a certain time. It may seem inheritance is a
 * viable option, but since same movie can be shown at different times decorator
 * pattern with an aggregation design is a better option.
 * @updated March 28, 2023 by Andriy Kudryavtsev
 */
public class Showing {
	private final int sequenceNo;
	private final Movie movie;
    private final LocalTime startTime;
    
    /**
     * Default constructor taking reference to a movie object and start time as arguments.
     * @sequenceNo - sequence of the showing in the day, starting with 1.
     * @param movie - reference to a movie object
     * @param startTime - start time of the movie
     */
    public Showing(int sequenceNo, Movie movie, LocalTime startTime) throws TheaterException {
    	if (sequenceNo < 1) throw new TheaterException("Showing sequence can't be less than 1.");
    	this.sequenceNo = sequenceNo;
    	this.movie = movie;
        this.startTime = startTime;
    }
    
    public int getSequenceNo() {
    	return sequenceNo;
    }

    public Movie getMovie() {
        return movie;
    }
    
    public LocalTime getStartTime() {
    	return startTime;
    }
    
    public LocalTime getEndTime() {
    	return startTime.plusMinutes(movie.getRunningTime().toMinutes());
    }
    
    @Override
    public String toString() {
    	return String.format("Start Time: %tR; %s", startTime, movie);
    }
    
    public String toJson() {
    	return String.format("{\"starttime\": \"%tR\"; \"movie\": %s}", startTime, movie.toJson());
    }
    
    @Override
    public boolean equals(Object o) {
    	if (this == o) return true;
    	if (!(o instanceof Showing)) return false;
    	Showing showing = (Showing)o;
        return (this.movie.equals(showing.movie) && this.startTime.equals(showing.startTime));
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(movie, startTime);
    }
    
}
