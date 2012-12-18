package net.sourceforge.cardme.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

/*
 * Copyright 2011 George El-Haddad. All rights reserved.
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
 * THIS SOFTWARE IS PROVIDED BY GEORGE EL-HADDAD ''AS IS'' AND ANY EXPRESS OR IMPLIED
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
 * or implied, of George El-Haddad.
 */

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Sep 21, 2006
 * 
 * <p>Utility class to help with ISO related formatting.</p>
 */
public final class ISOUtils {

	private static final Pattern ISO8601_UTC_TIME_BASIC_REGEX = Pattern.compile("\\d{8}T\\d{6}Z");
	private static final Pattern ISO8601_UTC_TIME_EXTENDED_REGEX = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z");
	
	private static final Pattern ISO8601_TIME_BASIC_REGEX = Pattern.compile("\\d{8}T\\d{6}[-\\+]\\d{4}");
	private static final Pattern ISO8601_TIME_EXTENDED_REGEX = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[-\\+]\\d{2}:\\d{2}");
	
	private static final Pattern ISO8601_DATE_BASIC_REGEX = Pattern.compile("\\d{8}");
	private static final Pattern ISO8601_DATE_EXTENDED_REGEX = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
	
	private ISOUtils() {
		//hide the constructor
	}
	
	/**
	 * <p>
	 * Given a calendar object and a specific ISO format it will construct an
	 * ISO8601 calendar date or datetime string.
	 * </p>
	 * <p>
	 * If an {@link ISOFormat} of {@link ISOFormat.ISO8601_UTC_TIME_BASIC
	 * ISO8601_UTC_TIME_BASIC} or {@link ISOFormat.ISO8601_UTC_TIME_EXTENDED
	 * ISO8601_UTC_TIME_EXTENDED} is specified, then the timezone in the
	 * Calendar object will be converted to UTC.
	 * </p>
	 * <p>
	 * If any other {@link ISOFormat} is specified, then the date will be
	 * formatted according to the timezone in the Calendar object.
	 * </p>
	 * 
	 * @param - time the date/datetime
	 * @param - format the date format to use
	 * @return the formatted date
	 */
	public static String formatISO8601Date(Calendar time, ISOFormat format) {
		String formatStr;
		TimeZone timezone = time.getTimeZone();
		
		switch (format)
		{
			case ISO8601_BASIC:
			case ISO8601_DATE_BASIC:
			{
				formatStr = "yyyyMMdd";
				break;
			}
			
			case ISO8601_EXTENDED:
			case ISO8601_DATE_EXTENDED:
			{
				formatStr = "yyyy-MM-dd";
				break;
			}
			
			case ISO8601_TIME_BASIC:
			{
				formatStr = "yyyyMMdd'T'HHmmssZ";
				break;
			}
			
			case ISO8601_TIME_EXTENDED:
			{
				formatStr = "yyyy-MM-dd'T'HH:mm:ssZ";
				break;
			}
			
			case ISO8601_UTC_TIME_BASIC:
			{
				formatStr = "yyyyMMdd'T'HHmmss'Z'";
				timezone = TimeZone.getTimeZone("UTC");
				break;
			}
			
			case ISO8601_UTC_TIME_EXTENDED:
			{
				formatStr = "yyyy-MM-dd'T'HH:mm:ss'Z'";
				timezone = TimeZone.getTimeZone("UTC");
				break;
			}
			
			default:
			{
				throw new IllegalArgumentException("The given ISOFormat is not supported: " + format);
			}
		}

		DateFormat df = new SimpleDateFormat(formatStr);
		df.setTimeZone(timezone);
		String str = df.format(time.getTime());

		switch (format)
		{
			case ISO8601_TIME_EXTENDED:
			{
				//add a colon to the timezone (SimpleDateFormat cannot do this)
				//example: converts "2012-07-05T22:31:41-0400" to "2012-07-05T22:31:41-04:00"
				str = str.replaceAll("([-\\+]\\d{2})(\\d{2})$", "$1:$2");
				break;
			}
		}

		return str;
	}

	/**
	 * <p>
	 * Converts a TimeZone object to a string that's in ISO-8601 format. It can
	 * be either basic or extended format.
	 * </p>
	 * 
	 * @param - timeZone the timezone to format
	 * @param - extended true to use "extended" format, false not to. Extended
	 *  format will put a colon between the hour and minute.
	 * @return the formatted timezone (e.g. "+0530" or "+05:30")
	 */
	public static String formatISO8601TimeZone(TimeZone timeZone, boolean extended)
	{
		StringBuilder sb = new StringBuilder();
		boolean positive = timeZone.getRawOffset() >= 0;
		int hours = Math.abs(((timeZone.getRawOffset() / 1000) / 60) / 60);
		int minutes = Math.abs((timeZone.getRawOffset() / 1000) / 60) % 60;
		
		sb.append(positive ? '+' : '-');
		
		if (hours < 10){
			sb.append('0');
		}
		sb.append(hours);
		
		if (extended){
			sb.append(':');
		}
		
		if (minutes < 10){
			sb.append('0');
		}
		sb.append(minutes);
		
		return sb.toString();
	}
	
	/**
	 * <p>
	 * Parses a date that's in ISO8601 format.
	 * </p>
	 * 
	 * @param - dateStr the date string (e.g. "2012-07-01T10:31:22+02:00")
	 * @return the parsed date (converted to the local timezone)
	 * @throws ParseException if there's a problem parsing the date
	 * @throws IllegalArgumentException if the date string is not recognized as
	 * an ISO8601 date
	 */
	public static Calendar parseISO8601Date(String dateStr) throws ParseException {
		String format;
		if (ISO8601_DATE_BASIC_REGEX.matcher(dateStr).matches()) {
			//Example: 19960415
			format = "yyyyMMdd";
		}
		else if (ISO8601_DATE_EXTENDED_REGEX.matcher(dateStr).matches()) {
			//Example: 1996-04-15
			format = "yyyy-MM-dd";
		}
		else if (ISO8601_TIME_BASIC_REGEX.matcher(dateStr).matches()) {
			//Example: 19960415T231000-0600
			format = "yyyyMMdd'T'HHmmssZ";
		}
		else if (ISO8601_TIME_EXTENDED_REGEX.matcher(dateStr).matches()) {
			//Example: 1996-04-15T23:10:00-06:00

			//SimpleDateFormat doesn't recognize timezone offsets that have colons
			//so remove the colon from the timezone offset
			dateStr = dateStr.replaceAll("([-\\+]\\d{2}):(\\d{2})$", "$1$2");
			format = "yyyy-MM-dd'T'HH:mm:ssZ";
		}
		else if (ISO8601_UTC_TIME_BASIC_REGEX.matcher(dateStr).matches()) {
			//Example: 19960415T231000Z

			//SimpleDateFormat doesn't recognize "Z"
			dateStr = dateStr.replace("Z", "+0000");
			format = "yyyyMMdd'T'HHmmssZ";
		}
		else if (ISO8601_UTC_TIME_EXTENDED_REGEX.matcher(dateStr).matches()) {
			//Example: 1996-04-15T23:10:00Z

			//SimpleDateFormat doesn't recognize "Z"
			dateStr = dateStr.replace("Z", "+0000");
			format = "yyyy-MM-dd'T'HH:mm:ssZ";
		}
		else {
			throw new IllegalArgumentException("Date string is not in a valid ISO-8601 format.");
		}

		DateFormat df = new SimpleDateFormat(format);
		Date date = df.parse(dateStr);

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
}
