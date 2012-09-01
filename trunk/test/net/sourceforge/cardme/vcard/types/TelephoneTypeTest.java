package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.TelParamType;
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
public class TelephoneTypeTest {

	private TelType telType = null;
	
	@Before
	public void setUp() throws Exception {
		telType = new TelType();
		telType.setTelephone("1234-4567-7890");
		telType.addParam(TelParamType.PREF).addParam(TelParamType.CELL).addParam(TelParamType.HOME)
		.addExtendedParam(new ExtendedParamType("X-3G", VCardTypeName.TEL))
		.addExtendedParam(new ExtendedParamType("X-TYPE", "SmartPhone", VCardTypeName.TEL));
	}
	
	@After
	public void tearDown() throws Exception {
		telType = null;
	}

	@Test
	public void testGetTelephone() {
		assertEquals("1234-4567-7890", telType.getTelephone());
	}

	@Test
	public void testGetParams() {
		List<TelParamType> list = telType.getParams();
		assertEquals(TelParamType.PREF, list.get(0));
		assertEquals(TelParamType.CELL, list.get(1));
		assertEquals(TelParamType.HOME, list.get(2));
		
	}

	@Test
	public void testGetParamSize() {
		assertEquals(3, telType.getParamSize());
	}

	@Test
	public void testAddParam() {
		telType.addParam(TelParamType.FAX);
		assertTrue(telType.containsParam(TelParamType.FAX));
	}

	@Test
	public void testRemoveParam() {
		telType.removeParam(TelParamType.HOME);
		assertFalse(telType.containsParam(TelParamType.HOME));
	}

	@Test
	public void testContainsParam() {
		assertTrue(telType.containsParam(TelParamType.HOME));
	}
	
	@Test
	public void testContainsAllParams() {
		List<TelParamType> types = new ArrayList<TelParamType>(3);
		types.add(TelParamType.CELL);
		types.add(TelParamType.PREF);
		types.add(TelParamType.HOME);
		
		assertTrue(telType.containsAllParams(types));
	}

	@Test
	public void testHasParams() {
		assertTrue(telType.hasParams());
	}

	@Test
	public void testClearParams() {
		telType.clearParams();
		assertFalse(telType.hasParams());
	}

	@Test
	public void testGetExtendedParams() {
		List<ExtendedParamType> list = telType.getExtendedParams();
		assertEquals(new ExtendedParamType("X-3G", VCardTypeName.TEL), list.get(0));
		assertEquals(new ExtendedParamType("X-TYPE", "SmartPhone", VCardTypeName.TEL), list.get(1));
	}

	@Test
	public void testGetExtendedParamSize() {
		assertEquals(2, telType.getExtendedParamSize());
	}

	@Test
	public void testAddExtendedParam() {
		telType.addExtendedParam(new ExtendedParamType("X-Test", VCardTypeName.TEL));
		assertTrue(telType.containsExtendedParam(new ExtendedParamType("X-Test", VCardTypeName.TEL)));
	}

	@Test
	public void testRemoveExtendedParam() {
		telType.removeExtendedParam(new ExtendedParamType("X-3G", VCardTypeName.TEL));
		assertFalse(telType.containsExtendedParam(new ExtendedParamType("X-3G", VCardTypeName.TEL)));
	}

	@Test
	public void testContainsExtendedParam() {
		assertTrue(telType.containsExtendedParam(new ExtendedParamType("X-3G", VCardTypeName.TEL)));
	}
	
	@Test
	public void testContainsAllExtendedParams() {
		List<ExtendedParamType> types = new ArrayList<ExtendedParamType>(2);
		types.add(new ExtendedParamType("X-3G", VCardTypeName.TEL));
		types.add(new ExtendedParamType("X-TYPE", "SmartPhone", VCardTypeName.TEL));
		
		assertTrue(telType.containsAllExtendedParams(types));
	}

	@Test
	public void testHasExtendedParams() {
		assertTrue(telType.hasExtendedParams());
	}

	@Test
	public void testClearExtendedParams() {
		telType.clearExtendedParams();
		assertFalse(telType.hasExtendedParams());
	}

	@Test
	public void testHasTelephone() {
		assertTrue(telType.hasTelephone());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.TEL.getType(), telType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		TelType telType2 = new TelType();
		telType2.setTelephone("1234-4567-7890");
		telType2.addParam(TelParamType.PREF).addParam(TelParamType.CELL).addParam(TelParamType.HOME)
		.addExtendedParam(new ExtendedParamType("X-3G", VCardTypeName.TEL))
		.addExtendedParam(new ExtendedParamType("X-TYPE", "SmartPhone", VCardTypeName.TEL));
		
		assertTrue(telType.equals(telType2));
	}
	
	@Test
	public void testHashcode() {
		TelType telType2 = new TelType();
		telType2.setTelephone("1234-4567-7890");
		telType2.addParam(TelParamType.PREF).addParam(TelParamType.CELL).addParam(TelParamType.HOME)
		.addExtendedParam(new ExtendedParamType("X-3G", VCardTypeName.TEL))
		.addExtendedParam(new ExtendedParamType("X-TYPE", "SmartPhone", VCardTypeName.TEL));
		
		int hcode1 = telType.hashCode();
		int hcode2 = telType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		TelType telType2 = new TelType();
		telType2.setTelephone("1234-4567-7890");
		telType2.addParam(TelParamType.PREF).addParam(TelParamType.CELL).addParam(TelParamType.HOME)
		.addExtendedParam(new ExtendedParamType("X-3G", VCardTypeName.TEL))
		.addExtendedParam(new ExtendedParamType("X-TYPE", "SmartPhone", VCardTypeName.TEL));
		
		assertTrue(telType.compareTo(telType2) == 0);
	}
	
	@Test
	public void testClone() {
		TelType cloned = telType.clone();
		assertEquals(cloned, telType);
		assertTrue(telType.equals(cloned));
		assertTrue(telType.compareTo(cloned) == 0);
	}
}
