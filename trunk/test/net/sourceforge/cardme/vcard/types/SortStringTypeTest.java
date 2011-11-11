package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.SortStringFeature;
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
public class SortStringTypeTest {

	private SortStringType sortStringType = null;
	
	@Before
	public void setUp() throws Exception {
		sortStringType = new SortStringType();
		sortStringType.setSortString("A");
	}
	
	@After
	public void tearDown() throws Exception {
		sortStringType = null;
	}
	
	@Test
	public void testGetSortString() {
		assertEquals("A", sortStringType.getSortString());
	}
	
	@Test
	public void testHasSortString() {
		assertTrue(sortStringType.hasSortString());
	}
	
	@Test
	public void testClearSortString() {
		sortStringType.clearSortString();
		assertFalse(sortStringType.hasSortString());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(sortStringType.getTypeString(), VCardType.SORT_STRING.getType());
	}
	
	@Test
	public void testEquals() {
		SortStringType sortStringType2 = new SortStringType("A");
		assertTrue(sortStringType.equals(sortStringType2));
	}
	
	@Test
	public void testHashcode() {
		SortStringType sortStringType2 = new SortStringType("A");
		
		int hcode1 = sortStringType.hashCode();
		int hcode2 = sortStringType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		SortStringFeature cloned = sortStringType.clone();
		assertEquals(cloned, sortStringType);
		assertTrue(sortStringType.equals(cloned));
	}
}
