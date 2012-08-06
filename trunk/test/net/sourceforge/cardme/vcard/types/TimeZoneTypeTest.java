package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.TimeZone;

import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.TimeZoneFeature;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
 * Oct 4, 2011
 * <p>
 * @author Michael Angstadt
 * <br/>
 * Jul 06, 2012
 *
 */
public class TimeZoneTypeTest {

	private TimeZoneType timeZoneType = null;
	
	@Before
	public void setUp() throws Exception {
		timeZoneType = new TimeZoneType();
		timeZoneType.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	@After
	public void tearDown() throws Exception {
		timeZoneType = null;
	}
	
	@Test
	public void testParseTimeZoneOffset(){
		timeZoneType.parseTimeZoneOffset("5");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("+5");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("-5");
		assertEquals(-5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("05");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("+05");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("-05");
		assertEquals(-5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("500");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("+500");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("-500");
		assertEquals(-5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("530");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("+530");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("-530");
		assertEquals(-5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("5:00");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("+5:00");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("-5:00");
		assertEquals(-5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("5:30");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("+5:30");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("-5:30");
		assertEquals(-5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("0500");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("+0500");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("-0500");
		assertEquals(-5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("0530");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("+0530");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("-0530");
		assertEquals(-5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("05:00");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("+05:00");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("-05:00");
		assertEquals(-5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("05:30");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("+05:30");
		assertEquals(5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
		
		timeZoneType.parseTimeZoneOffset("-05:30");
		assertEquals(-5, timeZoneType.getHourOffset());
		assertEquals(30, timeZoneType.getMinuteOffset());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseTimeZoneOffsetIllegalArgument_1() {
		timeZoneType.parseTimeZoneOffset("-05:60");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseTimeZoneOffsetIllegalArgument_2() {
		timeZoneType.parseTimeZoneOffset("99:99");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseTimeZoneOffsetIllegalArgument_3() {
		timeZoneType.parseTimeZoneOffset("random string");
	}
	
	@Test
	public void testGetIso8601Offset(){
		assertEquals("+00:00", timeZoneType.getIso8601Offset());
		
		timeZoneType.setOffset(5, 0);
		assertEquals("+05:00", timeZoneType.getIso8601Offset());
		
		timeZoneType.setOffset(-5, 0);
		assertEquals("-05:00", timeZoneType.getIso8601Offset());
		
		timeZoneType.setOffset(5, 30);
		assertEquals("+05:30", timeZoneType.getIso8601Offset());
	
		timeZoneType.setOffset(-5, 30);
		assertEquals("-05:30", timeZoneType.getIso8601Offset());
	}
	
	@Test
	public void testGetMinuteOffset(){
		assertEquals(0, timeZoneType.getMinuteOffset());
	}
	
	@Test
	public void testGetHourOffset(){
		assertEquals(0, timeZoneType.getHourOffset());
	}
	
	@Test
	public void testSetOffset(){
		timeZoneType.setOffset(1, 0);
		assertEquals(1*60*60*1000, timeZoneType.getTimeZone().getRawOffset());
		
		timeZoneType.setOffset(-1, 0);
		assertEquals(-1*60*60*1000, timeZoneType.getTimeZone().getRawOffset());
		
		timeZoneType.setOffset(1, 30);
		assertEquals(1*60*60*1000+30*60*1000, timeZoneType.getTimeZone().getRawOffset());
		
		timeZoneType.setOffset(-1, 30);
		assertEquals(-(1*60*60*1000+30*60*1000), timeZoneType.getTimeZone().getRawOffset());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetOffsetIllegalArgument_1() {
		//minute cannot be less than 0
		timeZoneType.setOffset(1, -30);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetOffsetIllegalArgument_2() {
		//minute cannot be greater than 59
		timeZoneType.setOffset(1, 60);
	}

	@Test
	public void testGetTimeZone() {
		assertEquals(TimeZone.getTimeZone("UTC"), timeZoneType.getTimeZone());
	}
	
	@Test
	public void testSetTimeZone(){
		TimeZone tz = TimeZone.getTimeZone("America/New_York");
		timeZoneType.setTimeZone(tz);
		
		assertEquals(-5, timeZoneType.getHourOffset());
		assertEquals(0, timeZoneType.getMinuteOffset());
		assertEquals(null, timeZoneType.getShortText());
		assertEquals(tz.getDisplayName(), timeZoneType.getLongText());
	}

	@Test
	public void testGetTypeString() {
		assertEquals(timeZoneType.getTypeString(), VCardType.TZ.getType());
	}
	
	@Test
	public void testEquals() {
		TimeZoneType timeZoneType2 = new TimeZoneType();
		timeZoneType2.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		assertTrue(timeZoneType.equals(timeZoneType2));
	}
	
	@Test
	public void testHashcode() {
		TimeZoneType timeZoneType2 = new TimeZoneType();
		timeZoneType2.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		int hcode1 = timeZoneType2.hashCode();
		int hcode2 = timeZoneType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		TimeZoneFeature cloned = timeZoneType.clone();
		assertEquals(cloned, timeZoneType);
		assertTrue(timeZoneType.equals(cloned));
	}
}
