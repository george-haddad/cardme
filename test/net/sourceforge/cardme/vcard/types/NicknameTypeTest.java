package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.NicknameFeature;
import java.util.ArrayList;
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
 * Oct 3, 2011
 *
 */
public class NicknameTypeTest {

	private NicknameType nicknameType = null;
	
	@Before
	public void setUp() throws Exception {
		nicknameType = new NicknameType();
		nicknameType.addNickname("AAA");
		nicknameType.addNickname("BBB");
		nicknameType.addNickname("CCC");
	}
	
	@After
	public void tearDown() throws Exception {
		nicknameType = null;
	}
	
	@Test
	public void testGetNicknames() {
		Iterator<String> iter = nicknameType.getNicknames();
		assertEquals("AAA", iter.next());
		assertEquals("BBB", iter.next());
		assertEquals("CCC", iter.next());
	}
	
	@Test
	public void testAddNickname() {
		nicknameType.addNickname("DDD");
		assertTrue(nicknameType.containsNickname("DDD"));
	}
	
	@Test
	public void testAddAllNicknames() {
		List<String> moreNicks = new ArrayList<String>();
		moreNicks.add("XXX");
		moreNicks.add("YYY");
		moreNicks.add("ZZZ");
		
		nicknameType.addAllNicknames(moreNicks);
		
		Iterator<String> iter = nicknameType.getNicknames();
		assertEquals("AAA", iter.next());
		assertEquals("BBB", iter.next());
		assertEquals("CCC", iter.next());
		assertEquals("XXX", iter.next());
		assertEquals("YYY", iter.next());
		assertEquals("ZZZ", iter.next());
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
		assertEquals(nicknameType.getTypeString(), VCardType.NICKNAME.getType());
	}
	
	@Test
	public void testEquals() {
		NicknameType nicknameType2 = new NicknameType();
		nicknameType2.addNickname("AAA");
		nicknameType2.addNickname("BBB");
		nicknameType2.addNickname("CCC");
		assertTrue(nicknameType.equals(nicknameType2));
	}
	
	@Test
	public void testHashcode() {
		NicknameType nicknameType2 = new NicknameType();
		nicknameType2.addNickname("AAA");
		nicknameType2.addNickname("BBB");
		nicknameType2.addNickname("CCC");
		
		int hcode1 = nicknameType.hashCode();
		int hcode2 = nicknameType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		NicknameFeature cloned = nicknameType.clone();
		assertEquals(cloned, nicknameType);
		assertTrue(nicknameType.equals(cloned));
	}
}
