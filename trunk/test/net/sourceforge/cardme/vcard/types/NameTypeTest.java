package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.NameFeature;
import java.util.Iterator;
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
 * Oct 3, 2011
 *
 */
public class NameTypeTest {

	private NameType nameType = null;
	
	@Before
	public void setUp() throws Exception {
		nameType = new NameType();
		nameType.setGivenName("John");
		nameType.setFamilyName("Doe");
		nameType.addHonorificPrefix("Mr");
		nameType.addHonorificPrefix("Dr");
		nameType.addHonorificSuffix("I");
		nameType.addHonorificSuffix("II");
		nameType.addAdditionalName("Johnny");
	}
	
	@After
	public void tearDown() throws Exception {
		nameType = null;
	}
	
	@Test
	public void testGetGivenName() {
		assertEquals("John", nameType.getGivenName());
	}
	
	@Test
	public void testGetFamilyName() {
		assertEquals("Doe", nameType.getFamilyName());
	}
	
	@Test
	public void testGetHonorificPrefixes() {
		Iterator<String> iter = nameType.getHonorificPrefixes();
		assertEquals("Mr", iter.next());
		assertEquals("Dr", iter.next());
	}
	
	@Test
	public void testGetHonorificSuffixes() {
		Iterator<String> iter = nameType.getHonorificSuffixes();
		assertEquals("I", iter.next());
		assertEquals("II", iter.next());
	}
	
	@Test
	public void testGetAdditionalNames() {
		Iterator<String> iter = nameType.getAdditionalNames();
		assertEquals("Johnny", iter.next());
	}
	
	@Test
	public void testAddHonorificPrefix() {
		nameType.addHonorificPrefix("Miss");
		assertTrue(nameType.containsHonorificPrefix("Miss"));
	}
	
	@Test
	public void testRemoveHonorifcPrefix() {
		nameType.removeHonorificPrefix("Mr");
		assertFalse(nameType.containsHonorificPrefix("Mr"));
	}
	
	@Test
	public void testContainsHonorificPrefix() {
		assertTrue(nameType.containsHonorificPrefix("Mr"));
		assertTrue(nameType.containsHonorificPrefix("Dr"));
	}
	
	@Test
	public void testHasHonorificPrefix() {
		assertTrue(nameType.hasHonorificPrefixes());
	}
	
	@Test
	public void testClearHonorificPrefixes() {
		nameType.clearHonorificPrefixes();
		assertFalse(nameType.hasHonorificPrefixes());
	}
	
	@Test
	public void testAddHonorificSuffix() {
		nameType.addHonorificSuffix("Esq");
		assertTrue(nameType.containsHonorificSuffix("Esq"));
	}
	
	@Test
	public void testRemoveHonorificSuffix() {
		nameType.removeHonorificSuffix("I");
		assertFalse(nameType.containsHonorificSuffix("I"));
	}
	
	@Test
	public void testContainsHonorificSuffix() {
		assertTrue(nameType.containsHonorificSuffix("I"));
		assertTrue(nameType.containsHonorificSuffix("II"));
	}
	
	@Test
	public void testHasHonorificSuffix() {
		assertTrue(nameType.hasHonorificSuffixes());
	}
	
	@Test
	public void testClearHonorificSuffixes() {
		nameType.clearHonorificSuffixes();
		assertFalse(nameType.hasHonorificSuffixes());
	}
	
	@Test
	public void testAddAdditionalName() {
		nameType.addAdditionalName("Joe");
		assertTrue(nameType.containsAdditionalName("Joe"));
	}
	
	@Test
	public void testRemoveAdditionalName() {
		nameType.removeAdditionalName("Johnny");
		assertFalse(nameType.containsAdditionalName("Johnny"));
	}
	
	@Test
	public void testContainsAdditionalName() {
		assertTrue(nameType.containsAdditionalName("Johnny"));
	}
	
	@Test
	public void testHasAdditionalName() {
		assertTrue(nameType.hasAdditionalNames());
	}
	
	@Test
	public void testClearAdditionalNames() {
		nameType.clearAdditionalNames();
		assertFalse(nameType.hasAdditionalNames());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(nameType.getTypeString(), VCardType.N.getType());
	}
	
	@Test
	public void testEquals() {
		NameType nameType2 = new NameType();
		nameType2.setGivenName("John");
		nameType2.setFamilyName("Doe");
		nameType2.addHonorificPrefix("Mr");
		nameType2.addHonorificPrefix("Dr");
		nameType2.addHonorificSuffix("I");
		nameType2.addHonorificSuffix("II");
		nameType2.addAdditionalName("Johnny");
		
		assertTrue(nameType.equals(nameType2));
	}
	
	@Test
	public void testHashcode() {
		NameType nameType2 = new NameType();
		nameType2.setGivenName("John");
		nameType2.setFamilyName("Doe");
		nameType2.addHonorificPrefix("Mr");
		nameType2.addHonorificPrefix("Dr");
		nameType2.addHonorificSuffix("I");
		nameType2.addHonorificSuffix("II");
		nameType2.addAdditionalName("Johnny");
		
		int hcode1 = nameType.hashCode();
		int hcode2 = nameType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		NameFeature cloned = nameType.clone();
		assertEquals(cloned, nameType);
		assertTrue(nameType.equals(cloned));
	}
}
