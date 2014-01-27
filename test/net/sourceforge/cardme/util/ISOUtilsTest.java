package net.sourceforge.cardme.util;

import static org.junit.Assert.assertEquals;
import java.util.Calendar;
import java.util.TimeZone;
import org.junit.Test;

/*
 * Copyright 2012 Michael Angstadt.
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 * 
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 * 
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY MICHAEL ANGSTADT ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL GEORGE EL-HADDAD OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of Michael Angstadt.
 */

/**
 * 
 * @author Michael Angstadt <br/>
 * Jul 7, 2012
 * 
 */
public class ISOUtilsTest {
	@Test
	public void formatISO8601Date() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Beirut"));
		cal.clear();
		cal.set(Calendar.YEAR, 2006);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 2);
		cal.set(Calendar.HOUR_OF_DAY, 10);
		cal.set(Calendar.MINUTE, 20);
		cal.set(Calendar.SECOND, 30);

		assertEquals("20060102", ISOUtils.formatISO8601Date(cal, ISOFormat.ISO8601_BASIC));
		assertEquals("20060102", ISOUtils.formatISO8601Date(cal, ISOFormat.ISO8601_DATE_BASIC));
		assertEquals("2006-01-02", ISOUtils.formatISO8601Date(cal, ISOFormat.ISO8601_EXTENDED));
		assertEquals("2006-01-02", ISOUtils.formatISO8601Date(cal, ISOFormat.ISO8601_DATE_EXTENDED));
		assertEquals("20060102T102030+0200", ISOUtils.formatISO8601Date(cal, ISOFormat.ISO8601_TIME_BASIC));
		assertEquals("2006-01-02T10:20:30+02:00", ISOUtils.formatISO8601Date(cal, ISOFormat.ISO8601_TIME_EXTENDED));
		assertEquals("20060102T082030Z", ISOUtils.formatISO8601Date(cal, ISOFormat.ISO8601_UTC_TIME_BASIC));
		assertEquals("2006-01-02T08:20:30Z", ISOUtils.formatISO8601Date(cal, ISOFormat.ISO8601_UTC_TIME_EXTENDED));
	}

	@Test
	public void formatISO8601TimeZone() {
		//positive
		TimeZone tz = TimeZone.getTimeZone("Asia/Beirut");

		String expected = "+0200";
		String actual = ISOUtils.formatISO8601TimeZone(tz, false);
		assertEquals(expected, actual);

		expected = "+02:00";
		actual = ISOUtils.formatISO8601TimeZone(tz, true);
		assertEquals(expected, actual);

		//negative
		tz = TimeZone.getTimeZone("America/New_York");

		expected = "-0500";
		actual = ISOUtils.formatISO8601TimeZone(tz, false);
		assertEquals(expected, actual);

		expected = "-05:00";
		actual = ISOUtils.formatISO8601TimeZone(tz, true);
		assertEquals(expected, actual);

		//with minutes
		tz = TimeZone.getTimeZone("America/New_York");
		tz.setRawOffset(tz.getRawOffset() - 30 * 60 * 1000);

		expected = "-0530";
		actual = ISOUtils.formatISO8601TimeZone(tz, false);
		assertEquals(expected, actual);

		expected = "-05:30";
		actual = ISOUtils.formatISO8601TimeZone(tz, true);
		assertEquals(expected, actual);

		//>= 10
		tz = TimeZone.getTimeZone("Australia/Sydney");

		expected = "+1000";
		actual = ISOUtils.formatISO8601TimeZone(tz, false);
		assertEquals(expected, actual);

		expected = "+10:00";
		actual = ISOUtils.formatISO8601TimeZone(tz, true);
		assertEquals(expected, actual);

		//zero
		tz = TimeZone.getTimeZone("UTC");

		expected = "+0000";
		actual = ISOUtils.formatISO8601TimeZone(tz, false);
		assertEquals(expected, actual);

		expected = "+00:00";
		actual = ISOUtils.formatISO8601TimeZone(tz, true);
		assertEquals(expected, actual);
	}

	@Test
	public void parseISO8601Date() throws Exception {
		Calendar expected = null;
		Calendar actual = null;

		//test date
		expected = Calendar.getInstance();
		expected.clear();
		expected.set(Calendar.YEAR, 2012);
		expected.set(Calendar.MONTH, Calendar.JULY);
		expected.set(Calendar.DAY_OF_MONTH, 1);
		actual = ISOUtils.parseISO8601Date("20120701");
		assertEquals(expected, actual);

		actual = ISOUtils.parseISO8601Date("2012-07-01");
		assertEquals(expected, actual);

		//test date-time
		expected.clear();
		expected.set(Calendar.YEAR, 2012);
		expected.set(Calendar.MONTH, Calendar.JULY);
		expected.set(Calendar.DAY_OF_MONTH, 1);
		expected.set(Calendar.HOUR_OF_DAY, 8);
		expected.set(Calendar.MINUTE, 1);
		expected.set(Calendar.SECOND, 30);
		expected.setTimeZone(TimeZone.getTimeZone("GMT"));
		actual = ISOUtils.parseISO8601Date("20120701T080130Z");
		actual.setTimeZone(TimeZone.getTimeZone("GMT"));
		assertEquals(expected, actual);

		actual = ISOUtils.parseISO8601Date("2012-07-01T08:01:30Z");
		actual.setTimeZone(TimeZone.getTimeZone("GMT"));
		assertEquals(expected, actual);

		actual = ISOUtils.parseISO8601Date("2012-07-01T11:01:30+03:00");
		actual.setTimeZone(TimeZone.getTimeZone("GMT"));
		assertEquals(expected, actual);
		
		//Test with decimals before Z (Android 4.3 export format)
		//                                  1978-01-21T08:00:00.000Z
		actual = ISOUtils.parseISO8601Date("2012-07-01T08:01:30.000Z");
		actual.setTimeZone(TimeZone.getTimeZone("GMT"));
		assertEquals(expected, actual);
	}
}
