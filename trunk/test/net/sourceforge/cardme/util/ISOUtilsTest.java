package net.sourceforge.cardme.util;

import static org.junit.Assert.assertEquals;

import java.util.TimeZone;

import org.junit.Test;

/**
 * 
 * @author Michael Angstadt
 * <br/>
 * Jul 7, 2012
 *
 */
public class ISOUtilsTest {
	
	@Test
	public void toISO8601_TimeZone() {
		//positive
		TimeZone tz = TimeZone.getTimeZone("Asia/Beirut");

		String expected = "+0200";
		String actual = ISOUtils.toISO8601_TimeZone(tz, ISOFormat.ISO8601_BASIC);
		assertEquals(expected, actual);

		expected = "+02:00";
		actual = ISOUtils.toISO8601_TimeZone(tz, ISOFormat.ISO8601_EXTENDED);
		assertEquals(expected, actual);

		//negative
		tz = TimeZone.getTimeZone("America/New_York");

		expected = "-0500";
		actual = ISOUtils.toISO8601_TimeZone(tz, ISOFormat.ISO8601_BASIC);
		assertEquals(expected, actual);

		expected = "-05:00";
		actual = ISOUtils.toISO8601_TimeZone(tz, ISOFormat.ISO8601_EXTENDED);
		assertEquals(expected, actual);

		//zero
		tz = TimeZone.getTimeZone("UTC");

		expected = "+0000";
		actual = ISOUtils.toISO8601_TimeZone(tz, ISOFormat.ISO8601_BASIC);
		assertEquals(expected, actual);

		expected = "+00:00";
		actual = ISOUtils.toISO8601_TimeZone(tz, ISOFormat.ISO8601_EXTENDED);
		assertEquals(expected, actual);
	}
}
