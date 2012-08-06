package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.FormattedNameFeature;
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
public class FormattedNameTypeTest {

	private FormattedNameType formattedNameType = null;
	
	@Before
	public void setUp() throws Exception {
		formattedNameType = new FormattedNameType();
		formattedNameType.setFormattedName("John Doe");
	}
	
	@After
	public void tearDown() throws Exception {
		formattedNameType = null;
	}
	
	@Test
	public void testGetFormattedName() {
		assertEquals("John Doe", formattedNameType.getFormattedName());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(formattedNameType.getTypeString(), VCardType.FN.getType());
	}
	
	@Test
	public void testEquals() {
		FormattedNameType formattedNameType2 = new FormattedNameType("John Doe");
		assertTrue(formattedNameType.equals(formattedNameType2));
	}
	
	@Test
	public void testHashcode() {
		FormattedNameType formattedNameType2 = new FormattedNameType("John Doe");
		
		int hcode1 = formattedNameType.hashCode();
		int hcode2 = formattedNameType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		FormattedNameFeature cloned = formattedNameType.clone();
		assertEquals(cloned, formattedNameType);
		assertTrue(formattedNameType.equals(cloned));
	}
}
