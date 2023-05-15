package com.jpmc.theater.main;

import java.time.Duration;
import com.jpmc.theater.exception.TheaterException;


/**
 * Movie class holds movie information.
 * @updated March 28, 2023 by Andriy Kudryavtsev
 */
public class Movie {
	private final int id;
	private final String title;
	private final Duration runningTime;
	private final String description;
    private final MovieType movieType;
    
    /**
     * Constructor method. Creates movie of a regular type.
     * @param id - unique global id of the movie.
     * @param title - movie title
     * @param runningTime - duration of the movie running time
     * @param description - description of the movie
     */
    public Movie(int id, String title, Duration runningTime, String description) throws TheaterException {
    	this(id, title, runningTime, description, MovieType.REGULAR);
    }
    
    /**
     * Movie constructor which allows to specify movie type based on the MoveType enum.
     * @param id - unique global id of the movie starting with value 1.
     * @param title - movie title
     * @param runningTime - duration of the movie running time
     * @param description - description of the movie
     * @param movieType - movie type driven by MovieType enum
     */
    public Movie(int id, String title, Duration runningTime, String description,
    		MovieType movieType) throws TheaterException {
    	if (id < 1) throw new TheaterException("Movie id can't be less than 1");
    	this.id = id;
        this.title = title;
        this.runningTime = runningTime;
        this.description = (description == null) ? "No Description" : description;
        this.movieType = movieType;
    }
    
    public int getId() {
    	return id;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public MovieType getMovieType() {
    	return movieType;
    }
    
    @Override
    public String toString() {
    	return String.format("Title: %s; Duration: %d min; Movie Type: %s; Description: %s",
    			title, runningTime.toMinutes(), movieType, description);
    }
    
    public String toJson() {
    	return String.format("{\"title\": \"%s\", \"durationmin\": %d, \"movietype\": \"%s\", \"description\": \"%s\"}",
    			title, runningTime.toMinutes(), movieType, description);
    }
    
    @Override
    public boolean equals(Object o) {
    	if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        return (id == ((Movie)o).id);
    }

    @Override
    public int hashCode() {
        return id;
    }

}
