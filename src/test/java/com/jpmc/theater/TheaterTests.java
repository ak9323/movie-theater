package com.jpmc.theater;

import org.junit.jupiter.api.Test;
import com.jpmc.theater.exception.TheaterException;
import com.jpmc.theater.main.Movie;
import com.jpmc.theater.main.Theater;
import com.jpmc.theater.pricing.Discount;
import com.jpmc.theater.pricing.Discount11amTo4pm;
import com.jpmc.theater.pricing.Discount1stOfTheDay;
import com.jpmc.theater.pricing.Discount2ndOfTheDay;
import com.jpmc.theater.pricing.Discount7thOfTheDay;
import com.jpmc.theater.pricing.DiscountSpecial20pct;
import com.jpmc.theater.pricing.PricePolicy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;

/**
 * Theater tests
 * @updated March 28, 2023 Andriy Kudryavtsev
 */
public class TheaterTests {
	static List<Movie> movies;
	static List<Discount> discounts;
	static Theater theater;
	
	@BeforeAll
	public static void setUp() throws TheaterException {
		movies = List.of(
	            new Movie(1, "Shazam! Fury Of The Gods", Duration.ofMinutes(130), null)
	        );
		discounts = List.of(
				new DiscountSpecial20pct(),
				new Discount1stOfTheDay(),
				new Discount2ndOfTheDay(),
				new Discount11amTo4pm(),
				new Discount7thOfTheDay()
			);
		PricePolicy pricePolicy = new PricePolicy(15.00F, discounts);
		theater = new Theater(movies, pricePolicy);
	}
	
	
    @Test
    public void textOutput() throws TheaterException {
    	ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    	PrintStream printStream = new PrintStream(byteArray);
    	String expectedText = new String("Start Time: 08:00; Title: Shazam! Fury Of The Gods; Duration: 130 min; Movie Type: REGULAR; Description: No Description\n"
    			+ "Start Time: 10:25; Title: Shazam! Fury Of The Gods; Duration: 130 min; Movie Type: REGULAR; Description: No Description\n"
    			+ "Start Time: 12:50; Title: Shazam! Fury Of The Gods; Duration: 130 min; Movie Type: REGULAR; Description: No Description\n"
    			+ "Start Time: 15:15; Title: Shazam! Fury Of The Gods; Duration: 130 min; Movie Type: REGULAR; Description: No Description\n"
    			+ "Start Time: 17:40; Title: Shazam! Fury Of The Gods; Duration: 130 min; Movie Type: REGULAR; Description: No Description\n"
    			+ "Start Time: 20:05; Title: Shazam! Fury Of The Gods; Duration: 130 min; Movie Type: REGULAR; Description: No Description");
    	theater.printScheduleText(printStream);
    	assertEquals(expectedText, byteArray.toString().trim());
    }
    
    
    @Test
    public void jsonOutput() {
    	ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    	PrintStream printStream = new PrintStream(byteArray);
    	String expectedJson = new String("[{\"starttime\": \"08:00\"; \"movie\": {\"title\": \"Shazam! Fury Of The Gods\", \"durationmin\": 130, \"movietype\": \"REGULAR\", \"description\": \"No Description\"}},\n"
    			+ "{\"starttime\": \"10:25\"; \"movie\": {\"title\": \"Shazam! Fury Of The Gods\", \"durationmin\": 130, \"movietype\": \"REGULAR\", \"description\": \"No Description\"}},\n"
    			+ "{\"starttime\": \"12:50\"; \"movie\": {\"title\": \"Shazam! Fury Of The Gods\", \"durationmin\": 130, \"movietype\": \"REGULAR\", \"description\": \"No Description\"}},\n"
    			+ "{\"starttime\": \"15:15\"; \"movie\": {\"title\": \"Shazam! Fury Of The Gods\", \"durationmin\": 130, \"movietype\": \"REGULAR\", \"description\": \"No Description\"}},\n"
    			+ "{\"starttime\": \"17:40\"; \"movie\": {\"title\": \"Shazam! Fury Of The Gods\", \"durationmin\": 130, \"movietype\": \"REGULAR\", \"description\": \"No Description\"}},\n"
    			+ "{\"starttime\": \"20:05\"; \"movie\": {\"title\": \"Shazam! Fury Of The Gods\", \"durationmin\": 130, \"movietype\": \"REGULAR\", \"description\": \"No Description\"}}]");
    	theater.printScheduleJson(printStream);
    	assertEquals(expectedJson, byteArray.toString().trim());
    }

}
