package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
 * Aug 24, 2012
 *
 */
public class DisplayableNameTypeTest {

	private NameType nameType = null;
	
	@Before
	public void setUp() throws Exception {
		nameType = new NameType();
		nameType.setName("Whatever");
	}
	
	@After
	public void tearDown() throws Exception {
		nameType = null;
	}
	
	@Test
	public void testGetName() {
		assertEquals("Whatever", nameType.getName());
	}
	
	@Test
	public void testSetName() {
		nameType.setName("Test");
		assertEquals("Test", nameType.getName());
		assertTrue(nameType.hasName());
	}
	
	@Test
	public void testSetNameNull() {
		nameType.setName(null);
		assertEquals(null, nameType.getName());
		assertFalse(nameType.hasName());
	}
	
	@Test
	public void testSetNameSecure() {
		String name = "Whatever";
		nameType.setName(name);
		
		String nameCopy = nameType.getName();
		
		assertFalse(name == nameCopy);
		assertTrue(name.compareTo(nameCopy) == 0);
		assertTrue(name.equals(nameCopy));
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.NAME.getType(), nameType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		NameType nameType2= new NameType("Whatever");
		assertTrue(nameType.equals(nameType2));
	}
	
	@Test
	public void testCompareTo() {
		NameType nameType2= new NameType("Whatever");
		assertTrue(nameType.compareTo(nameType2) == 0);
	}
	
	@Test
	public void testHashcode() {
		NameType nameType2= new NameType("Whatever");
		
		int hcode1 = nameType.hashCode();
		int hcode2 = nameType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		NameType cloned = nameType.clone();
		assertEquals(cloned, nameType);
		assertTrue(nameType.equals(cloned));
		assertTrue(nameType.compareTo(cloned) == 0);
	}
}
