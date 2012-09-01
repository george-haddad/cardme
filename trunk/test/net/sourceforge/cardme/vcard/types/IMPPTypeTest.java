package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.ImppParamType;
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
public class IMPPTypeTest {

	private ImppType imppType1 = null;
	private ImppType imppType2 = null;
	private ImppType imppType3 = null;
	
	@Before
	public void setUp() throws Exception {
		imppType1 = new ImppType("im:alice@example.com");
		imppType1.addParam(ImppParamType.WORK)
			  .addParam(ImppParamType.PREF)
			  .addExtendedParam(new ExtendedParamType("X-PARAM-1", "Something extra", VCardTypeName.IMPP))
			  .addExtendedParam(new ExtendedParamType("X-PARAM-2", VCardTypeName.IMPP));
		
		imppType2 = new ImppType(new URI("im:alice@example.com"));
		imppType3 = new ImppType();
	}
	
	@After
	public void tearDown() throws Exception {
		imppType1 = null;
		imppType2 = null;
		imppType3 = null;
	}
	
	@Test
	public void testGetURI() throws URISyntaxException {
		URI uri = new URI("im:alice@example.com");
		assertEquals(uri, imppType1.getUri());
		assertEquals(uri, imppType2.getUri());
		assertNull(imppType3.getUri());
	}
	
	@Test
	public void testHasURI() {
		assertTrue(imppType1.hasUri());
		assertTrue(imppType2.hasUri());
		assertFalse(imppType3.hasUri());
	}
	
	@Test
	public void testClearURI() {
		imppType1.clearUri();
		imppType2.clearUri();
		imppType3.clearUri();
		
		assertFalse(imppType1.hasUri());
		assertFalse(imppType2.hasUri());
		assertFalse(imppType3.hasUri());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.IMPP.getType(), imppType1.getVCardTypeName().getType());
		assertEquals(VCardTypeName.IMPP.getType(), imppType2.getVCardTypeName().getType());
		assertEquals(VCardTypeName.IMPP.getType(), imppType3.getVCardTypeName().getType());
	}
	
	@Test
	public void testContainsParam() {
		assertTrue(imppType1.containsParam(ImppParamType.PREF));
		assertTrue(imppType1.containsParam(ImppParamType.WORK));
		assertFalse(imppType1.containsParam(ImppParamType.HOME));
		assertFalse(imppType2.containsParam(ImppParamType.HOME));
		assertFalse(imppType3.containsParam(ImppParamType.HOME));
	}
	
	@Test
	public void testContainsAllParams() {
		List<ImppParamType> types = new ArrayList<ImppParamType>(2);
		types.add(ImppParamType.PREF);
		types.add(ImppParamType.WORK);
		
		assertTrue(imppType1.containsAllParams(types));
	}
	
	@Test
	public void testContainsExtendedParams() {
		assertTrue(imppType1.containsExtendedParam(new ExtendedParamType("X-PARAM-1", "Something extra", VCardTypeName.IMPP)));
		assertTrue(imppType1.containsExtendedParam(new ExtendedParamType("X-PARAM-2", VCardTypeName.IMPP)));
		assertFalse(imppType1.containsExtendedParam(new ExtendedParamType("X-NOT-EXISTS", VCardTypeName.IMPP)));
	}
	
	@Test
	public void testContainsAllExtendedParams() {
		List<ExtendedParamType> types = new ArrayList<ExtendedParamType>(2);
		types.add(new ExtendedParamType("X-PARAM-1", "Something extra", VCardTypeName.IMPP));
		types.add(new ExtendedParamType("X-PARAM-2", VCardTypeName.IMPP));
		
		assertTrue(imppType1.containsAllExtendedParams(types));
	}
	
	@Test
	public void testRemoveParam() {
		imppType1.addParam(ImppParamType.HOME);
		assertTrue(imppType1.containsParam(ImppParamType.HOME));
		imppType1.removeParam(ImppParamType.HOME);
		assertFalse(imppType1.containsParam(ImppParamType.HOME));
	}
	
	@Test
	public void testRemoveExtendedParam() {
		imppType1.addExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.IMPP));
		assertTrue(imppType1.containsExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.IMPP)));
		imppType1.removeExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.IMPP));
		assertFalse(imppType1.containsExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.IMPP)));
	}
	
	@Test
	public void testEquals() throws URISyntaxException {
		ImppType imppType4 = new ImppType("im:alice@example.com");
		imppType4.addParam(ImppParamType.WORK);
		imppType4.addParam(ImppParamType.PREF);
		imppType4.addExtendedParam(new ExtendedParamType("X-PARAM-1", "Something extra", VCardTypeName.IMPP));
		imppType4.addExtendedParam(new ExtendedParamType("X-PARAM-2", VCardTypeName.IMPP));
		
		assertTrue(imppType1.equals(imppType4));
	}
	
	@Test
	public void testCompareTo() throws URISyntaxException {
		ImppType imppType4 = new ImppType("im:alice@example.com");
		imppType4.addParam(ImppParamType.WORK);
		imppType4.addParam(ImppParamType.PREF);
		imppType4.addExtendedParam(new ExtendedParamType("X-PARAM-1", "Something extra", VCardTypeName.IMPP));
		imppType4.addExtendedParam(new ExtendedParamType("X-PARAM-2", VCardTypeName.IMPP));
		
		assertTrue(imppType1.compareTo(imppType4) == 0);
	}
	
	@Test
	public void testHashcode() throws URISyntaxException {
		ImppType imppType4 = new ImppType("im:alice@example.com");
		imppType4.addParam(ImppParamType.WORK);
		imppType4.addParam(ImppParamType.PREF);
		imppType4.addExtendedParam(new ExtendedParamType("X-PARAM-1", "Something extra", VCardTypeName.IMPP));
		imppType4.addExtendedParam(new ExtendedParamType("X-PARAM-2", VCardTypeName.IMPP));
		
		int hcode1 = imppType1.hashCode();
		int hcode2 = imppType4.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		ImppType cloned = imppType1.clone();
		assertEquals(cloned, imppType1);
		assertTrue(imppType1.equals(cloned));
		assertTrue(imppType1.compareTo(cloned) == 0);
	}
}
