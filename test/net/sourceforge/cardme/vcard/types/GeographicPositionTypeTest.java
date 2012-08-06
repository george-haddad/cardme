package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.GeographicPositionFeature;
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
 * Oct 1, 2011
 *
 */
public class GeographicPositionTypeTest {

	private GeographicPositionType geoPositionType = null;
	
	@Before
	public void setUp() throws Exception {
		geoPositionType = new GeographicPositionType();
		geoPositionType.setLatitude(33.123456);
		geoPositionType.setLongitude(22.123456);
	}
	
	@After
	public void tearDown() throws Exception {
		geoPositionType = null;
	}
	
	@Test
	public void testGetLatitude() {
		assertEquals(Double.valueOf(33.123456), Double.valueOf(geoPositionType.getLatitude()));
	}
	
	@Test
	public void testGetLongitude() {
		assertEquals(Double.valueOf(22.123456), Double.valueOf(geoPositionType.getLongitude()));
	}
	
	@Test
	public void testSetLatitude() {
		geoPositionType.setLatitude("11.123456");
		assertEquals(Double.valueOf(11.123456), Double.valueOf(geoPositionType.getLatitude()));
	}
	
	@Test
	public void testSetLongitude() {
		geoPositionType.setLongitude("11.123456");
		assertEquals(Double.valueOf(11.123456), Double.valueOf(geoPositionType.getLongitude()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetLatitudeIllegalArgument() {
		geoPositionType.setLatitude("1234.1234567");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetLongitudeIllegalArgument() {
		geoPositionType.setLongitude("1234.1234567");
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(geoPositionType.getTypeString(), VCardType.GEO.getType());
	}
	
	@Test
	public void testEquals() {
		GeographicPositionType geoPositionType2 = new GeographicPositionType(22.123456, 33.123456);
		assertTrue(geoPositionType.equals(geoPositionType2));
	}
	
	@Test
	public void testHashcode() {
		GeographicPositionType geoPositionType2 = new GeographicPositionType(22.123456, 33.123456);
		
		int hcode1 = geoPositionType.hashCode();
		int hcode2 = geoPositionType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		GeographicPositionFeature cloned = geoPositionType.clone();
		assertEquals(cloned, geoPositionType);
		assertTrue(geoPositionType.equals(cloned));
	}
}
