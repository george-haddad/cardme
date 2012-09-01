package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.arch.VCardVersion;
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
 * Aug 28, 2012
 *
 */
public class VersionTypeTest {

	private VersionType versionType = null;
	
	@Before
	public void setUp() throws Exception {
		versionType = new VersionType();
		versionType.setVersion(VCardVersion.V3_0);
	}
	
	@After
	public void tearDown() throws Exception {
		versionType = null;
	}
	
	@Test
	public void testGetVersion() {
		assertEquals(VCardVersion.V3_0, versionType.getVersion());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.VERSION.getType(), versionType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		VersionType versionType2 = new VersionType(VCardVersion.V3_0);
		assertTrue(versionType.equals(versionType2));
	}
	
	@Test
	public void testHashcode() {
		VersionType versionType2 = new VersionType(VCardVersion.V3_0);
		
		int hcode1 = versionType.hashCode();
		int hcode2 = versionType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		VersionType versionType2 = new VersionType(VCardVersion.V3_0);
		assertTrue(versionType.compareTo(versionType2) == 0);
	}
	
	@Test
	public void testClone() {
		VersionType cloned = versionType.clone();
		assertEquals(versionType, cloned);
		assertTrue(versionType.equals(cloned));
		assertTrue(versionType.compareTo(cloned) == 0);
	}
}
