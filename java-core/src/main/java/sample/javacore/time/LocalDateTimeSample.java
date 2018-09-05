package sample.javacore.time;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class LocalDateTimeSample {
	@Test
	public void until() {
		LocalDateTime endExclusive = LocalDateTime.of(2018, 8,16,1,0,0);
		LocalDateTime dt = LocalDateTime.now();
		long days = dt.until(endExclusive, ChronoUnit.DAYS);
		System.out.println(days);
	}
}
