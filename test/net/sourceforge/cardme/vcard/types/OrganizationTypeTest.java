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
public class OrganizationTypeTest {

	private OrgType orgType = null;
	
	@Before
	public void setUp() throws Exception {
		orgType = new OrgType();
		orgType.setOrgName("Acme Inc.").addOrgUnit("Big Widget Inc.");
	}
	
	@After
	public void tearDown() throws Exception {
		orgType = null;
	}
	
	@Test
	public void testGetOrgName() {
		assertEquals("Acme Inc.", orgType.getOrgName());
	}
	
	@Test
	public void testGetOrgUnits() {
		List<String> list = orgType.getOrgUnits();
		assertEquals("Big Widget Inc.", list.get(0));
	}
	
	@Test
	public void testAddOrgUnit() {
		orgType.addOrgUnit("BLA BLA");
		assertTrue(orgType.containsOrgUnit("BLA BLA"));
	}
	
	@Test
	public void testRemoveOrgUnit() {
		orgType.removeOrgUnit("Big Widget Inc.");
		assertFalse(orgType.containsOrgUnit("Big Widget Inc."));
	}
	
	@Test
	public void testContainsOrgUnit() {
		assertTrue(orgType.containsOrgUnit("Big Widget Inc."));
	}
	
	@Test
	public void testHasOrganizations() {
		assertTrue(orgType.hasOrgUnits());
	}
	
	@Test
	public void testClearOrganizations() {
		orgType.clearOrgUnits();
		assertFalse(orgType.hasOrgUnits());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.ORG.getType(), orgType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		OrgType orgType2 = new OrgType();
		orgType2.setOrgName("Acme Inc.").addOrgUnit("Big Widget Inc.");
		
		assertTrue(orgType.equals(orgType2));
	}
	
	@Test
	public void testHashcode() {
		OrgType orgType2 = new OrgType();
		orgType2.setOrgName("Acme Inc.").addOrgUnit("Big Widget Inc.");
		
		int hcode1 = orgType.hashCode();
		int hcode2 = orgType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		OrgType orgType2 = new OrgType();
		orgType2.setOrgName("Acme Inc.").addOrgUnit("Big Widget Inc.");
		
		assertTrue(orgType.compareTo(orgType2) == 0);
	}
	
	@Test
	public void testClone() {
		OrgType cloned = orgType.clone();
		assertEquals(cloned, orgType);
		assertTrue(orgType.equals(cloned));
		assertTrue(orgType.compareTo(cloned) == 0);
	}
}
