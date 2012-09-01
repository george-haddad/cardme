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
 * Aug 27, 2012
 *
 */
public class UIDTypeTest {

	private UidType uidType = null;
	
	@Before
	public void setUp() throws Exception {
		uidType = new UidType();
		uidType.setUid("123456");
	}
	
	@After
	public void tearDown() throws Exception {
		uidType = null;
	}
	
	@Test
	public void testGetUID() {
		assertEquals("123456", uidType.getUid());
	}
	
	@Test
	public void testSetUidSecure() {
		String uid = "Eudora";
		uidType.setUid(uid);
		
		String uidCopy = uidType.getUid();
		
		assertFalse(uid == uidCopy);
		assertTrue(uid.compareTo(uidCopy) == 0);
		assertTrue(uid.equals(uidCopy));
	}
	
	@Test
	public void testHasUID() {
		assertTrue(uidType.hasUid());
	}
	
	@Test
	public void testClearUid() {
		uidType.clearUid();
		assertFalse(uidType.hasUid());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.UID.getType(), uidType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		UidType uidType2 = new UidType("123456");
		assertTrue(uidType.equals(uidType2));
	}
	
	@Test
	public void testHashcode() {
		UidType uidType2 = new UidType("123456");
		
		int hcode1 = uidType.hashCode();
		int hcode2 = uidType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		UidType uidType2 = new UidType("123456");
		assertTrue(uidType.compareTo(uidType2) == 0);
	}
	
	@Test
	public void testClone() {
		UidType cloned = uidType.clone();
		assertEquals(cloned, uidType);
		assertTrue(uidType.equals(cloned));
		assertTrue(uidType.compareTo(cloned) == 0);
	}
}
