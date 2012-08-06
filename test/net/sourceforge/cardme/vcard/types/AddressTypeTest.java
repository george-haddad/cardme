package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.AddressFeature;
import net.sourceforge.cardme.vcard.types.parameters.AddressParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XAddressParameterType;

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
public class AddressTypeTest {

	private AddressType addressType = null;
	
	@Before
	public void setUp() throws Exception {
		addressType = new AddressType();
		addressType.setCountryName("USA");
		addressType.setRegion("New York");
		addressType.setLocality("Boorklyn");
		addressType.setPostalCode("12345");
		addressType.setPostOfficeBox("ABC567");
		addressType.setStreetAddress("25 Green Crecent Ave.");
		addressType.setExtendedAddress("Bla bla");
		addressType.addAddressParameterType(AddressParameterType.PREF);
		addressType.addAddressParameterType(AddressParameterType.HOME);
		addressType.addExtendedAddressParameterType(new XAddressParameterType("X-OFFICE"));
	}
	
	@After
	public void tearDown() throws Exception {
		addressType = null;
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(addressType.getTypeString(), VCardType.ADR.getType());
	}
	
	@Test
	public void testContainsAddressParameterType() {
		assertTrue(addressType.containsAddressParameterType(AddressParameterType.PREF));
		assertTrue(addressType.containsAddressParameterType(AddressParameterType.HOME));
		assertFalse(addressType.containsAddressParameterType(AddressParameterType.WORK));
	}
	
	@Test
	public void testContainsAllAddressParameterTypes() {
		List<AddressParameterType> types = new ArrayList<AddressParameterType>(3);
		types.add(AddressParameterType.PREF);
		types.add(AddressParameterType.HOME);
		
		assertTrue(addressType.containsAllAddressParameterTypes(types));
	}
	
	@Test
	public void testContainsExtendedAddressParameterType() {
		assertTrue(addressType.containsExtendedAddressParameterType(new XAddressParameterType("X-OFFICE")));
		assertFalse(addressType.containsExtendedAddressParameterType(new XAddressParameterType("X-NO-EXISTS")));
	}
	
	@Test
	public void testContainsAllExtendedAddressParameterTypes() {
		List<XAddressParameterType> types = new ArrayList<XAddressParameterType>(1);
		types.add(new XAddressParameterType("X-OFFICE"));
		
		assertTrue(addressType.containsAllExtendedAddressParameterTypes(types));
	}
	
	@Test
	public void testRemoveAddressParameterType() {
		addressType.addAddressParameterType(AddressParameterType.POSTAL);
		assertTrue(addressType.containsAddressParameterType(AddressParameterType.POSTAL));
		addressType.removeAddressParameterType(AddressParameterType.POSTAL);
		assertFalse(addressType.containsAddressParameterType(AddressParameterType.POSTAL));
	}
	
	@Test
	public void testRemoveExtendedAddressParameterType() {
		addressType.addExtendedAddressParameterType(new XAddressParameterType("X-REMOVEME"));
		assertTrue(addressType.containsExtendedAddressParameterType(new XAddressParameterType("X-REMOVEME")));
		addressType.removeExtendedAddressParameterType(new XAddressParameterType("X-REMOVEME"));
		assertFalse(addressType.containsExtendedAddressParameterType(new XAddressParameterType("X-REMOVEME")));
	}
	
	@Test
	public void testEquals() {
		AddressType addressType2 = new AddressType();
		addressType2 = new AddressType();
		addressType2.setCountryName("USA");
		addressType2.setRegion("New York");
		addressType2.setLocality("Boorklyn");
		addressType2.setPostalCode("12345");
		addressType2.setPostOfficeBox("ABC567");
		addressType2.setStreetAddress("25 Green Crecent Ave.");
		addressType2.setExtendedAddress("Bla bla");
		addressType2.addAddressParameterType(AddressParameterType.PREF);
		addressType2.addAddressParameterType(AddressParameterType.HOME);
		addressType2.addExtendedAddressParameterType(new XAddressParameterType("X-OFFICE"));
		assertTrue(addressType.equals(addressType2));
	}
	
	@Test
	public void testHashcode() {
		AddressType addressType2 = new AddressType();
		addressType2 = new AddressType();
		addressType2.setCountryName("USA");
		addressType2.setRegion("New York");
		addressType2.setLocality("Boorklyn");
		addressType2.setPostalCode("12345");
		addressType2.setPostOfficeBox("ABC567");
		addressType2.setStreetAddress("25 Green Crecent Ave.");
		addressType2.setExtendedAddress("Bla bla");
		addressType2.addAddressParameterType(AddressParameterType.PREF);
		addressType2.addAddressParameterType(AddressParameterType.HOME);
		addressType2.addExtendedAddressParameterType(new XAddressParameterType("X-OFFICE"));
		
		int hcode1 = addressType.hashCode();
		int hcode2 = addressType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		AddressFeature cloned = addressType.clone();
		assertEquals(cloned, addressType);
		assertTrue(addressType.equals(cloned));
	}
}
