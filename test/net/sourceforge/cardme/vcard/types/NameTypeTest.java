package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
public class NameTypeTest {

	private NType nType = null;
	
	@Before
	public void setUp() throws Exception {
		nType = new NType();
		nType.setGivenName("John");
		nType.setFamilyName("Doe");
		nType.addHonorificPrefix("Mr");
		nType.addHonorificPrefix("Dr");
		nType.addHonorificSuffix("I");
		nType.addHonorificSuffix("II");
		nType.addAdditionalName("Johnny");
	}
	
	@After
	public void tearDown() throws Exception {
		nType = null;
	}
	
	@Test
	public void testGetGivenName() {
		assertEquals("John", nType.getGivenName());
	}
	
	@Test
	public void testGetFamilyName() {
		assertEquals("Doe", nType.getFamilyName());
	}
	
	@Test
	public void testSetGivenNameSecure() {
		String givenName = "Robert";
		nType.setGivenName(givenName);
		
		String giveNameCopy = nType.getGivenName();
		
		assertFalse(givenName == giveNameCopy);
		assertTrue(givenName.compareTo(giveNameCopy) == 0);
		assertTrue(givenName.equals(giveNameCopy));
	}
	
	@Test
	public void testSetFamilyNameSecure() {
		String familyName = "Sanders";
		nType.setFamilyName(familyName);
		
		String familyNameCopy = nType.getFamilyName();
		
		assertFalse(familyName == familyNameCopy);
		assertTrue(familyName.compareTo(familyNameCopy) == 0);
		assertTrue(familyName.equals(familyNameCopy));
	}
	
	@Test
	public void testGetHonorificPrefixes() {
		List<String> list = nType.getHonorificPrefixes();
		assertEquals("Mr", list.get(0));
		assertEquals("Dr", list.get(1));
	}
	
	@Test
	public void testGetHonorificSuffixes() {
		List<String> list = nType.getHonorificSuffixes();
		assertEquals("I", list.get(0));
		assertEquals("II", list.get(1));
	}
	
	@Test
	public void testGetAdditionalNames() {
		List<String> list = nType.getAdditionalNames();
		assertEquals("Johnny", list.get(0));
	}
	
	@Test
	public void testAddHonorificPrefix() {
		nType.addHonorificPrefix("Miss");
		assertTrue(nType.containsHonorificPrefix("Miss"));
	}
	
	@Test
	public void testRemoveHonorifcPrefix() {
		nType.removeHonorificPrefix("Mr");
		assertFalse(nType.containsHonorificPrefix("Mr"));
	}
	
	@Test
	public void testContainsHonorificPrefix() {
		assertTrue(nType.containsHonorificPrefix("Mr"));
		assertTrue(nType.containsHonorificPrefix("Dr"));
	}
	
	@Test
	public void testHasHonorificPrefix() {
		assertTrue(nType.hasHonorificPrefixes());
	}
	
	@Test
	public void testClearHonorificPrefixes() {
		nType.clearHonorificPrefixes();
		assertFalse(nType.hasHonorificPrefixes());
	}
	
	@Test
	public void testAddHonorificSuffix() {
		nType.addHonorificSuffix("Esq");
		assertTrue(nType.containsHonorificSuffix("Esq"));
	}
	
	@Test
	public void testRemoveHonorificSuffix() {
		nType.removeHonorificSuffix("I");
		assertFalse(nType.containsHonorificSuffix("I"));
	}
	
	@Test
	public void testContainsHonorificSuffix() {
		assertTrue(nType.containsHonorificSuffix("I"));
		assertTrue(nType.containsHonorificSuffix("II"));
	}
	
	@Test
	public void testHasHonorificSuffix() {
		assertTrue(nType.hasHonorificSuffixes());
	}
	
	@Test
	public void testClearHonorificSuffixes() {
		nType.clearHonorificSuffixes();
		assertFalse(nType.hasHonorificSuffixes());
	}
	
	@Test
	public void testAddAdditionalName() {
		nType.addAdditionalName("Joe");
		assertTrue(nType.containsAdditionalName("Joe"));
	}
	
	@Test
	public void testRemoveAdditionalName() {
		nType.removeAdditionalName("Johnny");
		assertFalse(nType.containsAdditionalName("Johnny"));
	}
	
	@Test
	public void testContainsAdditionalName() {
		assertTrue(nType.containsAdditionalName("Johnny"));
	}
	
	@Test
	public void testHasAdditionalName() {
		assertTrue(nType.hasAdditionalNames());
	}
	
	@Test
	public void testClearAdditionalNames() {
		nType.clearAdditionalNames();
		assertFalse(nType.hasAdditionalNames());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.N.getType(), nType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		NType nType2 = new NType();
		nType2.setGivenName("John");
		nType2.setFamilyName("Doe");
		nType2.addHonorificPrefix("Mr");
		nType2.addHonorificPrefix("Dr");
		nType2.addHonorificSuffix("I");
		nType2.addHonorificSuffix("II");
		nType2.addAdditionalName("Johnny");
		
		assertTrue(nType.equals(nType2));
	}
	
	@Test
	public void testHashcode() {
		NType nType2 = new NType();
		nType2.setGivenName("John");
		nType2.setFamilyName("Doe");
		nType2.addHonorificPrefix("Mr");
		nType2.addHonorificPrefix("Dr");
		nType2.addHonorificSuffix("I");
		nType2.addHonorificSuffix("II");
		nType2.addAdditionalName("Johnny");
		
		int hcode1 = nType.hashCode();
		int hcode2 = nType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		NType nType2 = new NType();
		nType2.setGivenName("John");
		nType2.setFamilyName("Doe");
		nType2.addHonorificPrefix("Mr");
		nType2.addHonorificPrefix("Dr");
		nType2.addHonorificSuffix("I");
		nType2.addHonorificSuffix("II");
		nType2.addAdditionalName("Johnny");
		
		assertTrue(nType.compareTo(nType2) == 0);
	}
	
	@Test
	public void testClone() {
		NType cloned = nType.clone();
		assertEquals(cloned, nType);
		assertTrue(nType.equals(cloned));
		assertTrue(nType.compareTo(cloned) == 0);
	}
}
