package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
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
public class NicknameTypeTest {

	private NicknameType nicknameType = null;
	
	@Before
	public void setUp() throws Exception {
		nicknameType = new NicknameType();
		nicknameType.addNickname("AAA").addNickname("BBB").addNickname("CCC");
	}
	
	@After
	public void tearDown() throws Exception {
		nicknameType = null;
	}
	
	@Test
	public void testGetNicknames() {
		List<String> list = nicknameType.getNicknames();
		assertEquals("AAA", list.get(0));
		assertEquals("BBB", list.get(1));
		assertEquals("CCC", list.get(2));
	}
	
	@Test
	public void testAddNickname() {
		nicknameType.addNickname("DDD");
		assertTrue(nicknameType.containsNickname("DDD"));
	}
	
	@Test
	public void testAddAllNicknames() {
		List<String> moreNicks = new ArrayList<String>(3);
		moreNicks.add("XXX");
		moreNicks.add("YYY");
		moreNicks.add("ZZZ");
		
		nicknameType.addAllNicknames(moreNicks);
		
		List<String> list = nicknameType.getNicknames();
		assertEquals("AAA", list.get(0));
		assertEquals("BBB", list.get(1));
		assertEquals("CCC", list.get(2));
		assertEquals("XXX", list.get(3));
		assertEquals("YYY", list.get(4));
		assertEquals("ZZZ", list.get(5));
	}
	
	@Test
	public void testRemoveNickname() {
		nicknameType.removeNickname("AAA");
		assertFalse(nicknameType.containsNickname("AAA"));
	}
	
	@Test
	public void testContainsNickname() {
		assertTrue(nicknameType.containsNickname("AAA"));
		assertTrue(nicknameType.containsNickname("BBB"));
		assertTrue(nicknameType.containsNickname("CCC"));
	}
	
	@Test
	public void testHasNicknames() {
		assertTrue(nicknameType.hasNicknames());
	}
	
	@Test
	public void testClearNicknames() {
		nicknameType.clearNicknames();
		assertFalse(nicknameType.hasNicknames());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.NICKNAME.getType(), nicknameType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		NicknameType nicknameType2 = new NicknameType();
		nicknameType2.addNickname("AAA").addNickname("BBB").addNickname("CCC");
		assertTrue(nicknameType.equals(nicknameType2));
	}
	
	@Test
	public void testHashcode() {
		NicknameType nicknameType2 = new NicknameType();
		nicknameType2.addNickname("AAA").addNickname("BBB").addNickname("CCC");
		
		int hcode1 = nicknameType.hashCode();
		int hcode2 = nicknameType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		NicknameType nicknameType2 = new NicknameType();
		nicknameType2.addNickname("AAA").addNickname("BBB").addNickname("CCC");
		
		assertTrue(nicknameType.compareTo(nicknameType2) == 0);
	}
	
	@Test
	public void testClone() {
		NicknameType cloned = nicknameType.clone();
		assertEquals(cloned, nicknameType);
		assertTrue(nicknameType.equals(cloned));
		assertTrue(nicknameType.compareTo(cloned) == 0);
	}
}
