package time;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {
	
	@Test
	void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not calculated properly", seconds==18305);
	}
	
	@Test
	void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, ()-> {Time.getTotalSeconds("10:00");});
	}
	
	@Test
	void testGetTotalSecondsBoundary() {
		int seconds = Time.getTotalSeconds("24:59:59");
		assertTrue("The seconds were not calculated properly", seconds==89999);
	}
	
	@Test
	void testGetSecondsGood() {
		int seconds = Time.getSeconds("24:00:30");
		assertTrue("The seconds were not calculated properly", seconds==30);
	}
	
	@Test
	void testGetSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, ()-> {Time.getSeconds("10:00");});
	}
	
	@Test
	void testGetSecondsBoundary() {
		int seconds = Time.getSeconds("24:59:59");
		assertTrue("The seconds were not calculated properly", seconds==59);
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "05:00:00", "09:00:59" })
	void testGetTotalMinutesGoodBoundary(String candidate) {
	int minutes = Time.getTotalMinutes(candidate);
	assertTrue("The minutes were not calculated properly", minutes==0);
	}

	@Test
	void testGetTotalMinutesBad() {
		assertThrows(NumberFormatException.class, ()-> {Time.getTotalMinutes("06:ab:00");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "05:00:00", "05:15:15", "05:59:59" })
	void testGetTotalHoursGoodBoundary(String candidate) {
	int hours = Time.getTotalHours(candidate);
	assertTrue("The hours were not calculated properly", hours==5);
	}

	@Test
	void testGetTotalHoursBad() {
		assertThrows(NumberFormatException.class, ()-> {Time.getTotalHours(" :00:00");});
	}

	@Test
	void testGetMilliSeconds() {
		int milli = Time.getMilliSeconds("10:05:05:07");
		assertTrue("milli not calculated properly", milli==7);
	}
}