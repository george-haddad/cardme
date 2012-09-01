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
public class FormattedNameTypeTest {

	private FNType fnType = null;
	
	@Before
	public void setUp() throws Exception {
		fnType = new FNType();
		fnType.setFormattedName("John Doe");
	}
	
	@After
	public void tearDown() throws Exception {
		fnType = null;
	}
	
	@Test
	public void testGetFormattedName() {
		assertEquals("John Doe", fnType.getFormattedName());
	}
	
	@Test
	public void testSetFormattedName() {
		fnType.setFormattedName("Test");
		assertEquals("Test", fnType.getFormattedName());
	}
	
	@Test
	public void testSetFormattedNameNull() {
		fnType.setFormattedName(null);
		assertEquals(null, fnType.getFormattedName());
	}
	
	@Test
	public void testSetFormattedNameSecure() {
		/*
		 * this test makes sure that when we set a value
		 * and retrieve it, it is not the same object and
		 * that we are getting a copy of it.
		 */
		
		String john = "John";
		fnType.setFormattedName(john);
		
		String johnCopy = fnType.getFormattedName();
		
		assertFalse(john == johnCopy);
		assertTrue(john.compareTo(johnCopy) == 0);
		assertTrue(john.equals(johnCopy));
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.FN.getType(), fnType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		FNType fnType2 = new FNType("John Doe");
		assertTrue(fnType.equals(fnType2));
	}
	
	@Test
	public void testCompareTo() {
		FNType fnType2 = new FNType("John Doe");
		assertTrue(fnType.compareTo(fnType2) == 0);
	}
	
	@Test
	public void testHashcode() {
		FNType fnType2 = new FNType("John Doe");
		
		int hcode1 = fnType.hashCode();
		int hcode2 = fnType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		FNType cloned = fnType.clone();
		assertEquals(cloned, fnType);
		assertTrue(fnType.equals(cloned));
		assertTrue(fnType.compareTo(cloned) == 0);
	}
}
