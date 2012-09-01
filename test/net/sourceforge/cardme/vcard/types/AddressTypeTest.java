package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.types.params.AdrParamType;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
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
 * Aug 24, 2012
 *
 */
public class AddressTypeTest {

	private AdrType adrType = null;
	
	@Before
	public void setUp() throws Exception {
		adrType = new AdrType();
		adrType.setCountryName("USA");
		adrType.setRegion("New York");
		adrType.setLocality("Brooklyn");
		adrType.setPostalCode("12345");
		adrType.setPostOfficeBox("ABC567");
		adrType.setStreetAddress("25 Green Crecent Ave.");
		adrType.setExtendedAddress("Bla bla");
		adrType.addParam(AdrParamType.PREF)
			.addParam(AdrParamType.HOME)
			.addExtendedParam(new ExtendedParamType("X-OFFICE", VCardTypeName.ADR));
	}
	
	@After
	public void tearDown() throws Exception {
		adrType = null;
	}
	
	@Test
	public void testGetAddress() {
		assertEquals("USA", adrType.getCountryName());
		assertEquals("New York", adrType.getRegion());
		assertEquals("Brooklyn", adrType.getLocality());
		assertEquals("12345", adrType.getPostalCode());
		assertEquals("ABC567", adrType.getPostOfficeBox());
		assertEquals("25 Green Crecent Ave.", adrType.getStreetAddress());
		assertEquals("Bla bla", adrType.getExtendedAddress());
	}
	
	@Test
	public void testSetAddressNull() {
		adrType.setCountryName(null);
		adrType.setRegion(null);
		adrType.setLocality(null);
		adrType.setPostalCode(null);
		adrType.setPostOfficeBox(null);
		adrType.setStreetAddress(null);
		adrType.setExtendedAddress(null);
		
		assertEquals(null, adrType.getCountryName());
		assertEquals(null, adrType.getRegion());
		assertEquals(null, adrType.getLocality());
		assertEquals(null, adrType.getPostalCode());
		assertEquals(null, adrType.getPostOfficeBox());
		assertEquals(null, adrType.getStreetAddress());
		assertEquals(null, adrType.getExtendedAddress());
	}
	
	@Test
	public void testSetAddressSecure() {
		adrType.setCountryName("USA");
		adrType.setRegion("New York");
		adrType.setLocality("Brooklyn");
		adrType.setPostalCode("12345");
		adrType.setPostOfficeBox("ABC567");
		adrType.setStreetAddress("25 Green Crecent Ave.");
		adrType.setExtendedAddress("Bla bla");
		
		assertFalse("USA" == adrType.getCountryName());
		assertTrue("USA".equals(adrType.getCountryName()));
		assertTrue("USA".compareTo(adrType.getCountryName()) == 0);
		
		assertFalse("New York" == adrType.getRegion());
		assertTrue("New York".equals(adrType.getRegion()));
		assertTrue("New York".compareTo(adrType.getRegion()) == 0);
		
		assertFalse("Brooklyn" == adrType.getLocality());
		assertTrue("Brooklyn".equals(adrType.getLocality()));
		assertTrue("Brooklyn".compareTo(adrType.getLocality()) == 0);
		
		assertFalse("12345" == adrType.getPostalCode());
		assertTrue("12345".equals(adrType.getPostalCode()));
		assertTrue("12345".compareTo(adrType.getPostalCode()) == 0);
		
		assertFalse("ABC567" == adrType.getPostOfficeBox());
		assertTrue("ABC567".equals(adrType.getPostOfficeBox()));
		assertTrue("ABC567".compareTo(adrType.getPostOfficeBox()) == 0);
		
		assertFalse("25 Green Crecent Ave." == adrType.getStreetAddress());
		assertTrue("25 Green Crecent Ave.".equals(adrType.getStreetAddress()));
		assertTrue("25 Green Crecent Ave.".compareTo(adrType.getStreetAddress()) == 0);
		
		assertFalse("Bla bla" == adrType.getExtendedAddress());
		assertTrue("Bla bla".equals(adrType.getExtendedAddress()));
		assertTrue("Bla bla".compareTo(adrType.getExtendedAddress()) == 0);
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.ADR.getType(), adrType.getVCardTypeName().getType());
	}
	
	@Test
	public void testContainParam() {
		assertTrue(adrType.containsParam(AdrParamType.PREF));
		assertTrue(adrType.containsParam(AdrParamType.HOME));
		assertFalse(adrType.containsParam(AdrParamType.WORK));
	}
	
	@Test
	public void testContainsAllParams() {
		List<AdrParamType> types = new ArrayList<AdrParamType>(2);
		types.add(AdrParamType.PREF);
		types.add(AdrParamType.HOME);
		
		assertTrue(adrType.containsAllParams(types));
	}
	
	@Test
	public void testGetParamSize() {
		assertEquals(2, adrType.getParamSize());
	}
	
	@Test
	public void testHasParams() {
		assertTrue(adrType.hasParams());
	}
	
	@Test
	public void testContainsExtendedParam() {
		assertTrue(adrType.containsExtendedParam(new ExtendedParamType("X-OFFICE", VCardTypeName.ADR)));
		assertFalse(adrType.containsExtendedParam(new ExtendedParamType("X-NO-EXISTS", VCardTypeName.ADR)));
		assertFalse(adrType.containsExtendedParam(new ExtendedParamType("X-NO-EXISTS", VCardTypeName.TEL)));
	}
	
	@Test
	public void testContainsAllExtendedParams() {
		List<ExtendedParamType> types = new ArrayList<ExtendedParamType>(1);
		types.add(new ExtendedParamType("X-OFFICE", VCardTypeName.ADR));
		
		assertTrue(adrType.containsAllExtendedParams(types));
	}
	
	@Test
	public void testRemoveParam() {
		adrType.addParam(AdrParamType.POSTAL);
		assertTrue(adrType.containsParam(AdrParamType.POSTAL));
		adrType.removeParam(AdrParamType.POSTAL);
		assertFalse(adrType.containsParam(AdrParamType.POSTAL));
	}
	
	@Test
	public void testRemoveExtendedParam() {
		adrType.addExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.ADR));
		assertTrue(adrType.containsExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.ADR)));
		adrType.removeExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.ADR));
		assertFalse(adrType.containsExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.ADR)));
	}
	
	@Test
	public void testGetExtendedParamSize() {
		assertEquals(1, adrType.getExtendedParamSize());
	}
	
	@Test
	public void testHasExtendedParams() {
		assertTrue(adrType.hasExtendedParams());
	}
	
	@Test
	public void testEquals() {
		AdrType addressType2 = new AdrType();
		addressType2.setCountryName("USA");
		addressType2.setRegion("New York");
		addressType2.setLocality("Brooklyn");
		addressType2.setPostalCode("12345");
		addressType2.setPostOfficeBox("ABC567");
		addressType2.setStreetAddress("25 Green Crecent Ave.");
		addressType2.setExtendedAddress("Bla bla");
		addressType2.addParam(AdrParamType.PREF)
			     .addParam(AdrParamType.HOME)
			     .addExtendedParam(new ExtendedParamType("X-OFFICE", VCardTypeName.ADR));
		
		assertTrue(adrType.equals(addressType2));
	}
	
	@Test
	public void testCompareTo() {
		AdrType addressType2 = new AdrType();
		addressType2.setCountryName("USA");
		addressType2.setRegion("New York");
		addressType2.setLocality("Brooklyn");
		addressType2.setPostalCode("12345");
		addressType2.setPostOfficeBox("ABC567");
		addressType2.setStreetAddress("25 Green Crecent Ave.");
		addressType2.setExtendedAddress("Bla bla");
		addressType2.addParam(AdrParamType.PREF)
			     .addParam(AdrParamType.HOME)
			     .addExtendedParam(new ExtendedParamType("X-OFFICE", VCardTypeName.ADR));
		
		assertTrue(adrType.compareTo(addressType2) == 0);
	}
	
	@Test
	public void testHashcode() {
		AdrType addressType2 = new AdrType();
		addressType2.setCountryName("USA");
		addressType2.setRegion("New York");
		addressType2.setLocality("Brooklyn");
		addressType2.setPostalCode("12345");
		addressType2.setPostOfficeBox("ABC567");
		addressType2.setStreetAddress("25 Green Crecent Ave.");
		addressType2.setExtendedAddress("Bla bla");
		addressType2.addParam(AdrParamType.PREF)
			     .addParam(AdrParamType.HOME)
			     .addExtendedParam(new ExtendedParamType("X-OFFICE", VCardTypeName.ADR));
		
		int hcode1 = adrType.hashCode();
		int hcode2 = addressType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		AdrType cloned = adrType.clone();
		assertEquals(cloned, adrType);
		assertTrue(adrType.equals(cloned));
		assertTrue(adrType.compareTo(cloned) == 0);
	}
}
