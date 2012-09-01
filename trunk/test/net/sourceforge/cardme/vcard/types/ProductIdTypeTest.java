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
public class ProductIdTypeTest {

	private ProdIdType prodIdType = null;
	
	@Before
	public void setUp() throws Exception {
		prodIdType = new ProdIdType();
		prodIdType.setProdId("1234-1234-1234");
	}
	
	@After
	public void tearDown() throws Exception {
		prodIdType = null;
	}
	
	@Test
	public void testGetProdId() {
		assertEquals("1234-1234-1234", prodIdType.getProdId());
	}
	
	@Test
	public void testSetProdIdSecure() {
		String prodId = "1234-1234-1234";
		prodIdType.setProdId(prodId);
		
		String prodIdCopy = prodIdType.getProdId();
		
		assertFalse(prodId == prodIdCopy);
		assertTrue(prodId.compareTo(prodIdCopy) == 0);
		assertTrue(prodId.equals(prodIdCopy));
	}
	
	@Test
	public void testHasProdId() {
		assertTrue(prodIdType.hasProdId());
	}
	
	@Test
	public void testClearProdId() {
		prodIdType.clearProdId();
		assertFalse(prodIdType.hasProdId());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.PRODID.getType(), prodIdType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		ProdIdType prodIdType2 = new ProdIdType("1234-1234-1234");
		assertTrue(prodIdType.equals(prodIdType2));
	}
	
	@Test
	public void testHashcode() {
		ProdIdType prodIdType2 = new ProdIdType("1234-1234-1234");
		
		int hcode1 = prodIdType.hashCode();
		int hcode2 = prodIdType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		ProdIdType prodIdType2 = new ProdIdType("1234-1234-1234");
		assertTrue(prodIdType.compareTo(prodIdType2) == 0);
	}
	
	@Test
	public void testClone() {
		ProdIdType cloned = prodIdType.clone();
		assertEquals(cloned, prodIdType);
		assertTrue(prodIdType.equals(cloned));
		assertTrue(prodIdType.compareTo(cloned) == 0);
	}
}
