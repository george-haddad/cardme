package net.sourceforge.cardme.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
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

	public static final String ISO8601_UTC_TIME_BASIC_REGEX = "\\d{8}T\\d{6}Z";
	public static final String ISO8601_UTC_TIME_EXTENDED_REGEX = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z";
	public static final String ISO8601_TIME_EXTENDED_REGEX = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[-\\+]\\d{2}:\\d{2}";
	
	public static final String ISO8601_DATE_BASIC_REGEX = "\\d{8}";
	public static final String ISO8601_DATE_EXTENDED_REGEX = "\\d{4}-\\d{2}-\\d{2}";
	
	private ISOUtils() {

	}

	/**
	 * <p>Builds a ISO-8601 UTC time formatted string. The format can either be
	 * basic or extended. Depending on the format parameter it will return it in
	 * basic or extended format.</p>
	 * 
	 * @param time
	 * @param format
	 * @return {@link String}
	 */
	public static String toISO8601_UTC_Time(Calendar time, ISOFormat format)
	{
		StringBuilder builder = new StringBuilder();
		switch (format)
		{
			case ISO8601_BASIC:
			{
				builder.append(toISO8601_Date(time, ISOFormat.ISO8601_DATE_BASIC));
				builder.append("T");
				paddTwoDigits(builder, time.get(Calendar.HOUR_OF_DAY));
				paddTwoDigits(builder, time.get(Calendar.MINUTE));
				paddTwoDigits(builder, time.get(Calendar.SECOND));
				break;
			}
			
			case ISO8601_UTC_TIME_BASIC:
			{
				builder.append(toISO8601_Date(time, ISOFormat.ISO8601_DATE_BASIC));
				builder.append("T");
				paddTwoDigits(builder, time.get(Calendar.HOUR_OF_DAY));
				paddTwoDigits(builder, time.get(Calendar.MINUTE));
				paddTwoDigits(builder, time.get(Calendar.SECOND));
				builder.append("Z");
				break;
			}
	
			case ISO8601_EXTENDED:
			case ISO8601_UTC_TIME_EXTENDED:
			{
				builder.append(toISO8601_Date(time, ISOFormat.ISO8601_DATE_EXTENDED));
				builder.append("T");
				paddTwoDigits(builder, time.get(Calendar.HOUR_OF_DAY));
				builder.append(":");
				paddTwoDigits(builder, time.get(Calendar.MINUTE));
				builder.append(":");
				paddTwoDigits(builder, time.get(Calendar.SECOND));
				builder.append("Z");
				break;
			}
			
			case ISO8601_TIME_EXTENDED:
			{
				builder.append(toISO8601_Date(time, ISOFormat.ISO8601_DATE_EXTENDED));
				builder.append("T");
				paddTwoDigits(builder, time.get(Calendar.HOUR_OF_DAY));
				builder.append(":");
				paddTwoDigits(builder, time.get(Calendar.MINUTE));
				builder.append(":");
				paddTwoDigits(builder, time.get(Calendar.SECOND));
				builder.append("Z");
				builder.append(toISO8601_TimeZone(time.getTimeZone(), ISOFormat.ISO8601_EXTENDED));
				break;
			}
		}

		return builder.toString();
	}

	/**
	 * <p>Given a calendar object and a specific ISO format it will construct an
	 * ISO8601 calendar date string. Depending on the format parameter it will
	 * return it in basic or extended format.</p>
	 * 
	 * @param date
	 * @param format
	 * @return {@link String}
	 */
	public static String toISO8601_Date(Calendar date, ISOFormat format)
	{
		StringBuilder builder = new StringBuilder();
		switch (format)
		{
			case ISO8601_BASIC:
			case ISO8601_DATE_BASIC: 
			{
				builder.append(date.get(Calendar.YEAR));
				paddTwoDigits(builder, date.get(Calendar.MONTH)+1);
				paddTwoDigits(builder, date.get(Calendar.DAY_OF_MONTH));
				break;
			}
			
			case ISO8601_EXTENDED:
			case ISO8601_DATE_EXTENDED:
			{
				builder.append(date.get(Calendar.YEAR));
				builder.append("-");
				paddTwoDigits(builder, date.get(Calendar.MONTH)+1);
				builder.append("-");
				paddTwoDigits(builder, date.get(Calendar.DAY_OF_MONTH));
				break;
			}
		}

		return builder.toString();
	}

	/**
	 * <p>Returns a TimeZone object as a ISO-8601 format. This can be either basic
	 * or extended format. Depending on the format parameter it will return it
	 * in basic or extended format.</p>
	 * 
	 * @param timeZone
	 * @param format
	 * @return {@link String}
	 */
	public static String toISO8601_TimeZone(TimeZone timeZone, ISOFormat format)
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
		
		if (format == ISOFormat.ISO8601_EXTENDED){
			sb.append(':');
		}
		
		if (minutes < 10){
			sb.append('0');
		}
		
		sb.append(minutes);
		return sb.toString();
	}
	
	/**
	 * <p>Pads a number with an extra zero if it is less than 10.</p>
	 * 
	 * @param sb
	 * @param number
	 */
	private static void paddTwoDigits(StringBuilder sb, int number) {
		if(number < 10) {
			sb.append("0");
			sb.append(number);
		}
		else {
			sb.append(number);
		}
	}
	
//	/**
//	 * <p>Un-comment this for testing purposes.</p>
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		
//		System.out.println("Test Current Date/Time");
//		System.out.println("ISO Date");
//		System.out.println(toISO8601_Date(Calendar.getInstance(), ISOFormat.ISO8601_BASIC));
//		System.out.println(toISO8601_Date(Calendar.getInstance(), ISOFormat.ISO8601_EXTENDED));
//		System.out.println();
//		
//		System.out.println("ISO UTC Time");
//		System.out.println(toISO8601_UTC_Time(Calendar.getInstance(), ISOFormat.ISO8601_BASIC));
//		System.out.println(toISO8601_UTC_Time(Calendar.getInstance(), ISOFormat.ISO8601_EXTENDED));
//		System.out.println(toISO8601_UTC_Time(Calendar.getInstance(), ISOFormat.ISO8601_UTC_TIME_BASIC));
//		System.out.println(toISO8601_UTC_Time(Calendar.getInstance(), ISOFormat.ISO8601_UTC_TIME_EXTENDED));
//		System.out.println(toISO8601_UTC_Time(Calendar.getInstance(), ISOFormat.ISO8601_TIME_EXTENDED));
//		System.out.println();
//		
//		TimeZone tz = TimeZone.getDefault();
//		tz.setRawOffset(((4*1000)*60)*60);	//Offset it four hours somewhere
//		
//		System.out.println("ISO Timezone");
//		System.out.println(toISO8601_TimeZone(tz, ISOFormat.ISO8601_BASIC));
//		System.out.println(toISO8601_TimeZone(tz, ISOFormat.ISO8601_EXTENDED));
//		System.out.println();
//		
//		System.out.println("ISO Timezone");
//		System.out.println(toISO8601_TimeZone(TimeZone.getDefault(), ISOFormat.ISO8601_BASIC));
//		System.out.println(toISO8601_TimeZone(TimeZone.getDefault(), ISOFormat.ISO8601_EXTENDED));
//		System.out.println();
//		
//		System.out.println("Test Single Digit Date/Time");
//		Calendar cal = Calendar.getInstance();
//		cal.clear();
//		cal.set(Calendar.YEAR, 2006);
//		cal.set(Calendar.MONTH, Calendar.JANUARY);
//		cal.set(Calendar.DAY_OF_MONTH, 1);
//		cal.set(Calendar.HOUR_OF_DAY, 1);
//		cal.set(Calendar.MINUTE, 1);
//		cal.set(Calendar.SECOND, 1);
//		
//		System.out.println("ISO Date");
//		System.out.println(toISO8601_Date(cal, ISOFormat.ISO8601_BASIC));
//		System.out.println(toISO8601_Date(cal, ISOFormat.ISO8601_EXTENDED));
//		System.out.println();
//		
//		System.out.println("ISO UTC Time");
//		System.out.println(toISO8601_UTC_Time(cal, ISOFormat.ISO8601_BASIC));
//		System.out.println(toISO8601_UTC_Time(cal, ISOFormat.ISO8601_EXTENDED));
//		System.out.println();
//	}
}
