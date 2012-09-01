package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * Copyright 2012 George El-Haddad. All rights reserved.
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
 * Aug 27, 2012
 *
 */
public class GeographicPositionTypeTest {

	private GeoType geoType = null;
	
	@Before
	public void setUp() throws Exception {
		geoType = new GeoType();
		geoType.setLatitude(33.123456);
		geoType.setLongitude(22.123456);
	}
	
	@After
	public void tearDown() throws Exception {
		geoType = null;
	}
	
	@Test
	public void testGetLatitude() {
		assertEquals(Double.valueOf(33.123456), Double.valueOf(geoType.getLatitude()));
	}
	
	@Test
	public void testGetLongitude() {
		assertEquals(Double.valueOf(22.123456), Double.valueOf(geoType.getLongitude()));
	}
	
	@Test
	public void testSetLatitude() {
		geoType.setLatitude("11.123456");
		assertEquals(Double.valueOf(11.123456), Double.valueOf(geoType.getLatitude()));
	}
	
	@Test
	public void testSetLongitude() {
		geoType.setLongitude("11.123456");
		assertEquals(Double.valueOf(11.123456), Double.valueOf(geoType.getLongitude()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetLatitudeIllegalArgument() {
		geoType.setLatitude("1234.1234567");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetLongitudeIllegalArgument() {
		geoType.setLongitude("1234.1234567");
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.GEO.getType(), geoType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		GeoType geoType2 = new GeoType(22.123456, 33.123456);
		assertTrue(geoType.equals(geoType2));
	}
	
	@Test
	public void testHashcode() {
		GeoType geoType2 = new GeoType(22.123456, 33.123456);
		
		int hcode1 = geoType.hashCode();
		int hcode2 = geoType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		GeoType geoType2 = new GeoType(22.123456, 33.123456);
		assertTrue(geoType.compareTo(geoType2) == 0);
	}
	
	@Test
	public void testClone() {
		GeoType cloned = geoType.clone();
		assertEquals(cloned, geoType);
		assertTrue(geoType.equals(cloned));
		assertTrue(geoType.compareTo(cloned) == 0);
	}
}
