package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.UIDFeature;
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
 * Oct 4, 2011
 *
 */
public class UIDTypeTest {

	private UIDType uidType = null;
	
	@Before
	public void setUp() throws Exception {
		uidType = new UIDType();
		uidType.setUID("123456");
	}
	
	@After
	public void tearDown() throws Exception {
		uidType = null;
	}
	
	@Test
	public void testGetUID() {
		assertEquals("123456", uidType.getUID());
	}
	
	@Test
	public void testHasUID() {
		assertTrue(uidType.hasUID());
	}
	
	@Test
	public void testClearUID() {
		uidType.clearUID();
		assertFalse(uidType.hasUID());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(uidType.getTypeString(), VCardType.UID.getType());
	}
	
	@Test
	public void testEquals() {
		UIDType uidType2 = new UIDType("123456");
		assertTrue(uidType.equals(uidType2));
	}
	
	@Test
	public void testHashcode() {
		UIDType uidType2 = new UIDType("123456");
		
		int hcode1 = uidType.hashCode();
		int hcode2 = uidType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		UIDFeature cloned = uidType.clone();
		assertEquals(cloned, uidType);
		assertTrue(uidType.equals(cloned));
	}
}
