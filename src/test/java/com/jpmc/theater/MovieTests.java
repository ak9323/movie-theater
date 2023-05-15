package com.jpmc.theater;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.jpmc.theater.exception.TheaterException;
import com.jpmc.theater.main.Movie;
import java.time.Duration;

/**
 * Tests related to Movie
 * @updated March 28, 2023 by Andriy Kudryavtsev
 */
public class MovieTests {
	
    @Test
    void movieStartingIndex() {
    	Assertions.assertThrows(TheaterException.class, () ->
    		new Movie(0, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null));
    }
    
}
