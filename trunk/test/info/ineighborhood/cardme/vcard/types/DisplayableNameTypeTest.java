package info.ineighborhood.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import info.ineighborhood.cardme.vcard.VCardType;
import info.ineighborhood.cardme.vcard.features.DisplayableNameFeature;
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
 * Oct 1, 2011
 *
 */
public class DisplayableNameTypeTest {

	private DisplayableNameType displayableNameType = null;
	
	@Before
	public void setUp() throws Exception {
		displayableNameType = new DisplayableNameType();
		displayableNameType.setName("Whatever");
	}
	
	@After
	public void tearDown() throws Exception {
		displayableNameType = null;
	}
	
	@Test
	public void testGetName() {
		assertEquals("Whatever", displayableNameType.getName());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(displayableNameType.getTypeString(), VCardType.NAME.getType());
	}
	
	@Test
	public void testEquals() {
		DisplayableNameType displayableNameType2= new DisplayableNameType("Whatever");
		assertTrue(displayableNameType.equals(displayableNameType2));
	}
	
	@Test
	public void testHashcode() {
		DisplayableNameType displayableNameType2= new DisplayableNameType("Whatever");
		
		int hcode1 = displayableNameType.hashCode();
		int hcode2 = displayableNameType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		DisplayableNameFeature cloned = displayableNameType.clone();
		assertEquals(cloned, displayableNameType);
		assertTrue(displayableNameType.equals(cloned));
	}
}
