package info.ineighborhood.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import info.ineighborhood.cardme.vcard.VCardType;
import info.ineighborhood.cardme.vcard.features.TelephoneFeature;
import info.ineighborhood.cardme.vcard.types.parameters.TelephoneParameterType;
import info.ineighborhood.cardme.vcard.types.parameters.XTelephoneParameterType;
import java.util.Iterator;
import java.util.List;
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
public class TelephoneTypeTest {

	private TelephoneType telephoneType = null;
	
	@Before
	public void setUp() throws Exception {
		telephoneType = new TelephoneType();
		telephoneType.setTelephone("1234-4567-7890");
		telephoneType.addTelephoneParameterType(TelephoneParameterType.PREF);
		telephoneType.addTelephoneParameterType(TelephoneParameterType.CELL);
		telephoneType.addTelephoneParameterType(TelephoneParameterType.HOME);
		telephoneType.addExtendedTelephoneParameterType(new XTelephoneParameterType("X-3G"));
		telephoneType.addExtendedTelephoneParameterType(new XTelephoneParameterType("X-TYPE", "SmartPhone"));
	}
	
	@After
	public void tearDown() throws Exception {
		telephoneType = null;
	}

	@Test
	public void testGetTelephone() {
		assertEquals("1234-4567-7890", telephoneType.getTelephone());
	}

	@Test
	public void testGetTelephoneParameterTypes() {
		Iterator<TelephoneParameterType> iter = telephoneType.getTelephoneParameterTypes();
		assertEquals(TelephoneParameterType.PREF, iter.next());
		assertEquals(TelephoneParameterType.CELL, iter.next());
		assertEquals(TelephoneParameterType.HOME, iter.next());
		
	}

	@Test
	public void testGetTelephoneParameterTypesList() {
		List<TelephoneParameterType> list = telephoneType.getTelephoneParameterTypesList();
		assertEquals(TelephoneParameterType.PREF, list.get(0));
		assertEquals(TelephoneParameterType.CELL, list.get(1));
		assertEquals(TelephoneParameterType.HOME, list.get(2));
	}

	@Test
	public void testGetTelephoneParameterSize() {
		assertEquals(3, telephoneType.getTelephoneParameterSize());
	}

	@Test
	public void testAddTelephoneParameterType() {
		telephoneType.addTelephoneParameterType(TelephoneParameterType.FAX);
		assertTrue(telephoneType.containsTelephoneParameterType(TelephoneParameterType.FAX));
	}

	@Test
	public void testRemoveTelephoneParameterType() {
		telephoneType.removeTelephoneParameterType(TelephoneParameterType.HOME);
		assertFalse(telephoneType.containsTelephoneParameterType(TelephoneParameterType.HOME));
	}

	@Test
	public void testContainsTelephoneParameterType() {
		assertTrue(telephoneType.containsTelephoneParameterType(TelephoneParameterType.HOME));
	}

	@Test
	public void testHasTelephoneParameterTypes() {
		assertTrue(telephoneType.hasTelephoneParameterTypes());
	}

	@Test
	public void testClearTelephoneParameterTypes() {
		telephoneType.clearTelephoneParameterTypes();
		assertFalse(telephoneType.hasTelephoneParameterTypes());
	}

	@Test
	public void testGetExtendedTelephoneParameterTypes() {
		Iterator<XTelephoneParameterType> iter = telephoneType.getExtendedTelephoneParameterTypes();
		assertEquals(new XTelephoneParameterType("X-3G"), iter.next());
		assertEquals(new XTelephoneParameterType("X-TYPE", "SmartPhone"), iter.next());
	}

	@Test
	public void testGetExtendedTelephoneParameterTypesList() {
		List<XTelephoneParameterType> list = telephoneType.getExtendedTelephoneParameterTypesList();
		assertEquals(new XTelephoneParameterType("X-3G"), list.get(0));
		assertEquals(new XTelephoneParameterType("X-TYPE", "SmartPhone"), list.get(1));
	}

	@Test
	public void testGetExtendedTelephoneParameterSize() {
		assertEquals(2, telephoneType.getExtendedTelephoneParameterSize());
	}

	@Test
	public void testAddExtendedTelephoneParameterType() {
		telephoneType.addExtendedTelephoneParameterType(new XTelephoneParameterType("X-Test"));
		assertTrue(telephoneType.containsExtendedTelephoneParameterType(new XTelephoneParameterType("X-Test")));
	}

	@Test
	public void testRemoveExtendedTelephoneParameterType() {
		telephoneType.removeExtendedTelephoneParameterType(new XTelephoneParameterType("X-3G"));
		assertFalse(telephoneType.containsExtendedTelephoneParameterType(new XTelephoneParameterType("X-3G")));
	}

	@Test
	public void testContainsExtendedTelephoneParameterType() {
		assertTrue(telephoneType.containsExtendedTelephoneParameterType(new XTelephoneParameterType("X-3G")));
	}

	@Test
	public void testHasExtendedTelephoneParameterTypes() {
		assertTrue(telephoneType.hasExtendedTelephoneParameterTypes());
	}

	@Test
	public void testClearExtendedTelephoneParameterTypes() {
		telephoneType.clearExtendedTelephoneParameterTypes();
		assertFalse(telephoneType.hasExtendedTelephoneParameterTypes());
	}

	@Test
	public void testHasTelephone() {
		assertTrue(telephoneType.hasTelephone());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(telephoneType.getTypeString(), VCardType.TEL.getType());
	}
	
	@Test
	public void testEquals() {
		TelephoneType telephoneType2 = new TelephoneType();
		telephoneType2.setTelephone("1234-4567-7890");
		telephoneType2.addTelephoneParameterType(TelephoneParameterType.PREF);
		telephoneType2.addTelephoneParameterType(TelephoneParameterType.CELL);
		telephoneType2.addTelephoneParameterType(TelephoneParameterType.HOME);
		telephoneType2.addExtendedTelephoneParameterType(new XTelephoneParameterType("X-3G"));
		telephoneType2.addExtendedTelephoneParameterType(new XTelephoneParameterType("X-TYPE", "SmartPhone"));
		
		assertTrue(telephoneType.equals(telephoneType2));
	}
	
	@Test
	public void testHashcode() {
		TelephoneType telephoneType2 = new TelephoneType();
		telephoneType2.setTelephone("1234-4567-7890");
		telephoneType2.addTelephoneParameterType(TelephoneParameterType.PREF);
		telephoneType2.addTelephoneParameterType(TelephoneParameterType.CELL);
		telephoneType2.addTelephoneParameterType(TelephoneParameterType.HOME);
		telephoneType2.addExtendedTelephoneParameterType(new XTelephoneParameterType("X-3G"));
		telephoneType2.addExtendedTelephoneParameterType(new XTelephoneParameterType("X-TYPE", "SmartPhone"));
		
		int hcode1 = telephoneType.hashCode();
		int hcode2 = telephoneType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		TelephoneFeature cloned = telephoneType.clone();
		assertEquals(cloned, telephoneType);
		assertTrue(telephoneType.equals(cloned));
	}
}
