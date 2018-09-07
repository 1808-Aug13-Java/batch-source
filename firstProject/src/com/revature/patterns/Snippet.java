package com.revature.patterns;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.*;
import java.util.*;

public class Snippet {
	/*
	 * 11574 days, 1 hr, 46 mins, and 40 seconds is equal to one giga second
	 *
	 */
	public static void main(String[] args) {

		System.out.println(getGigasecondDate(LocalDateTime.of(2015, Month.JANUARY, 24, 22, 0, 0)));

		System.out.println("expected:  " + LocalDateTime.of(2046, Month.OCTOBER, 2, 23, 46, 40));

	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds. one gigasecond (1 Gs) -31 years
	 * 8 months 15 days, 1 hr, 46 mins, and 40 seconds
	 * 
	 * @param given
	 * @return
	 */
	public static Temporal getGigasecondDate(Temporal given) {

		LocalDateTime updated;

		if (!given.isSupported(ChronoField.HOUR_OF_DAY)) {
			updated = (LocalDateTime.of(given.get(ChronoField.YEAR), given.get(ChronoField.MONTH_OF_YEAR),
					given.get(ChronoField.DAY_OF_MONTH), 0, 0, 0));

			return updated.plusSeconds(1_000_000_000);
		}

		updated = (LocalDateTime) given;
		return updated.plusSeconds(1_000_000_000);

	}

}
