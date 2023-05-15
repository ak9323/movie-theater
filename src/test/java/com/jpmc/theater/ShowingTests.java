package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.jpmc.theater.exception.TheaterException;
import com.jpmc.theater.main.Movie;
import com.jpmc.theater.main.Showing;


/**
 * Tests related to Showings
 * @author Andriy Kudryavtsev
 * @since March 28, 2023
 */
class ShowingTests {

	@Test
    void showingStartingIndex() {
    	Assertions.assertThrows(TheaterException.class, () -> new Showing(0,
    		new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null),
    		LocalTime.of(9, 45)));
    }

}
