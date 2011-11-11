package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.OrganizationFeature;
import java.util.Iterator;
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
public class OrganizationTypeTest {

	private OrganizationType orgType = null;
	
	@Before
	public void setUp() throws Exception {
		orgType = new OrganizationType();
		orgType.addOrganization("Acme Inc.");
		orgType.addOrganization("Big Widget Inc.");
	}
	
	@After
	public void tearDown() throws Exception {
		orgType = null;
	}
	
	@Test
	public void testGetOrganizations() {
		Iterator<String> iter = orgType.getOrganizations();
		assertEquals("Acme Inc.", iter.next());
		assertEquals("Big Widget Inc.", iter.next());
	}
	
	@Test
	public void testAddOrganization() {
		orgType.addOrganization("BLA BLA");
		assertTrue(orgType.containsOrganization("BLA BLA"));
	}
	
	@Test
	public void testRemoveOrganization() {
		orgType.removeOrganization("Acme Inc.");
		assertFalse(orgType.containsOrganization("Acme Inc."));
	}
	
	@Test
	public void testContainsOrganization() {
		assertTrue(orgType.containsOrganization("Acme Inc."));
		assertTrue(orgType.containsOrganization("Big Widget Inc."));
	}
	
	@Test
	public void testHasOrganizations() {
		assertTrue(orgType.hasOrganizations());
	}
	
	@Test
	public void testClearOrganizations() {
		orgType.clearOrganizations();
		assertFalse(orgType.hasOrganizations());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(orgType.getTypeString(), VCardType.ORG.getType());
	}
	
	@Test
	public void testEquals() {
		OrganizationType orgType2 = new OrganizationType();
		orgType2.addOrganization("Acme Inc.");
		orgType2.addOrganization("Big Widget Inc.");
		
		assertTrue(orgType.equals(orgType2));
	}
	
	@Test
	public void testHashcode() {
		OrganizationType orgType2 = new OrganizationType();
		orgType2.addOrganization("Acme Inc.");
		orgType2.addOrganization("Big Widget Inc.");
		
		int hcode1 = orgType.hashCode();
		int hcode2 = orgType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		OrganizationFeature cloned = orgType.clone();
		assertEquals(cloned, orgType);
		assertTrue(orgType.equals(cloned));
	}
}
