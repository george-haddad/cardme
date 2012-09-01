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
public class TitleTypeTest {

	private TitleType titleType = null;
	
	@Before
	public void setUp() throws Exception {
		titleType = new TitleType();
		titleType.setTitle("Master of the known universe");
	}
	
	@After
	public void tearDown() throws Exception {
		titleType = null;
	}
	
	@Test
	public void testGetTitle() {
		assertEquals("Master of the known universe", titleType.getTitle());
	}
	
	@Test
	public void testSetTitleSecure() {
		String title = "Master of the known universe";
		titleType.setTitle(title);
		
		String titleCopy = titleType.getTitle();
		
		assertFalse(title == titleCopy);
		assertTrue(title.compareTo(titleCopy) == 0);
		assertTrue(title.equals(titleCopy));
	}
	
	@Test
	public void testHasTitle() {
		assertTrue(titleType.hasTitle());
	}
	
	@Test
	public void testClearTitle() {
		titleType.clearTitle();
		assertFalse(titleType.hasTitle());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.TITLE.getType(), titleType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		TitleType titleType2 = new TitleType("Master of the known universe");
		assertTrue(titleType.equals(titleType2));
	}
	
	@Test
	public void testHashcode() {
		TitleType titleType2 = new TitleType("Master of the known universe");
		
		int hcode1 = titleType.hashCode();
		int hcode2 = titleType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		TitleType titleType2 = new TitleType("Master of the known universe");
		assertTrue(titleType.compareTo(titleType2) == 0);
	}
	
	@Test
	public void testClone() {
		TitleType cloned = titleType.clone();
		assertEquals(cloned, titleType);
		assertTrue(titleType.equals(cloned));
		assertTrue(titleType.compareTo(cloned) == 0);
	}
}
