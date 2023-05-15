package com.jpmc.theater.main;

import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import com.jpmc.theater.exception.TheaterException;
import com.jpmc.theater.pricing.PricePolicy;

/**
 * Theater is the center class of the application. It is an aggregation of
 * helper classes and it is being referenced by external classes performing
 * application related tasks.
 * @updated March 28, 2023 by Andriy Kudryavtsev
 */
public class Theater {
	private final int seatCapacity;
	private final LocalTime opensAt, closesAt;
	private final Duration intermission;
	private Scheduler scheduler;
	private List<Showing> schedule;
	private TicketCounter ticketCounter;
    
    
    private Theater() {
    	seatCapacity = 50;
    	opensAt = LocalTime.of(8, 0);
    	closesAt = LocalTime.of(23, 0);
    	intermission = Duration.ofMinutes(15);
    }
    
    public Theater(List<Movie> movies, PricePolicy pricePolicy) throws TheaterException {
    	this();
    	scheduler = new SequentialScheduler(this, movies);
    	schedule = scheduler.getSchedule();
    	ticketCounter = new TicketCounter(this, pricePolicy);
    }
    
    public int getSeatCapacity() {
    	return seatCapacity;
    }
    
    public LocalTime getOpensAt() {
    	return opensAt;
    }
    
    public LocalTime getClosesAt() {
    	return closesAt;
    }
    
    public Duration getIntermission() {
    	return intermission;
    }
    
    public List<Showing> getSchedule() {
    	return schedule;
    }
    
    public TicketCounter getTicketCounter() {
    	return ticketCounter;
    }
    
    /**
     * Checks if the showing matches the listing at this theater. The comparison is
     * done based on movie and start time. The showing parameter doesn't need to be
     * the same object in memory.
     * @param showing - reference to a showing
     * @return true if the showing is part of the schedule.
     */
    public boolean isValidShowing(Showing showing) {
    	for (Showing s : schedule) {
    		if (s.equals(showing)) return true;
    	}
    	return false;
    }
    
    /**
     * Prints showings schedule in a form of a text to a print stream.
     * @param printStream - reference to a PrintStream where schedule should be printed.
     */
    public void printScheduleText(PrintStream printStream) {
    	schedule.forEach(s -> printStream.print(s + "\n"));
    }
    
    /**
     * Prints showings schedule to print stream in a json format.
     * @param printStream - reference to a PrintStream where json should be printed.
     */
    public void printScheduleJson(PrintStream printStream) {
    	printStream.print("[");
    	for (int i = 0; i < schedule.size(); i++) {
    		printStream.print(schedule.get(i).toJson() + ((i + 1) == schedule.size()? "" : ",\n"));
    	}
    	printStream.println("]");
    }
    
}
