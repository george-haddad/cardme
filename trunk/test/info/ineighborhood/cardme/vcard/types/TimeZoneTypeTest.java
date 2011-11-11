package info.ineighborhood.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import info.ineighborhood.cardme.vcard.VCardType;
import info.ineighborhood.cardme.vcard.features.TimeZoneFeature;
import java.util.TimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
 * Oct 4, 2011
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
	public void testGetTimeZone() {
		assertEquals(TimeZone.getTimeZone("UTC"), timeZoneType.getTimeZone());
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
