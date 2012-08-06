package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.IMPPFeature;
import net.sourceforge.cardme.vcard.types.parameters.IMPPParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XIMPPParameterType;

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
 * Jul 16, 2012
 *
 */
public class IMPPTypeTest {

	private IMPPType imppType1 = null;
	private IMPPType imppType2 = null;
	private IMPPType imppType3 = null;
	
	@Before
	public void setUp() throws Exception {
		imppType1 = new IMPPType("im:alice@example.com");
		imppType1.addIMPPParameterType(IMPPParameterType.WORK);
		imppType1.addIMPPParameterType(IMPPParameterType.PREF);
		imppType1.addExtendedIMPPParameterType(new XIMPPParameterType("X-PARAM-1", "Something extra"));
		imppType1.addExtendedIMPPParameterType(new XIMPPParameterType("X-PARAM-2"));
		
		imppType2 = new IMPPType(new URI("im:alice@example.com"));
		imppType3 = new IMPPType();
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
		assertEquals(uri, imppType1.getURI());
		assertEquals(uri, imppType2.getURI());
		assertNull(imppType3.getURI());
	}
	
	@Test
	public void testHasURI() {
		assertTrue(imppType1.hasURI());
		assertTrue(imppType2.hasURI());
		assertFalse(imppType3.hasURI());
	}
	
	@Test
	public void testClearURL() {
		imppType1.clearURI();
		imppType2.clearURI();
		imppType3.clearURI();
		
		assertFalse(imppType1.hasURI());
		assertFalse(imppType2.hasURI());
		assertFalse(imppType3.hasURI());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(imppType1.getTypeString(), VCardType.IMPP.getType());
	}
	
	@Test
	public void testContainsIMPPParameterType() {
		assertTrue(imppType1.containsIMPPParameterType(IMPPParameterType.PREF));
		assertTrue(imppType1.containsIMPPParameterType(IMPPParameterType.WORK));
		assertFalse(imppType1.containsIMPPParameterType(IMPPParameterType.HOME));
	}
	
	@Test
	public void testContainsAllIMPPParameterTypes() {
		List<IMPPParameterType> types = new ArrayList<IMPPParameterType>(2);
		types.add(IMPPParameterType.PREF);
		types.add(IMPPParameterType.WORK);
		
		assertTrue(imppType1.containsAllIMPPParameterTypes(types));
	}
	
	@Test
	public void testContainsExtendedIMPPParameterType() {
		assertTrue(imppType1.containsExtendedIMPPParameterType(new XIMPPParameterType("X-PARAM-1", "Something extra")));
		assertTrue(imppType1.containsExtendedIMPPParameterType(new XIMPPParameterType("X-PARAM-2")));
		assertFalse(imppType1.containsExtendedIMPPParameterType(new XIMPPParameterType("X-NOT-EXISTS")));
	}
	
	@Test
	public void testContainsAllExtendedIMPPParameterTypes() {
		List<XIMPPParameterType> types = new ArrayList<XIMPPParameterType>(2);
		types.add(new XIMPPParameterType("X-PARAM-1", "Something extra"));
		types.add(new XIMPPParameterType("X-PARAM-2"));
		
		assertTrue(imppType1.containsAllExtendedIMPPParameterTypes(types));
	}
	
	@Test
	public void testRemoveIMPPParameterType() {
		imppType1.addIMPPParameterType(IMPPParameterType.HOME);
		assertTrue(imppType1.containsIMPPParameterType(IMPPParameterType.HOME));
		imppType1.removeIMPPParameterType(IMPPParameterType.HOME);
		assertFalse(imppType1.containsIMPPParameterType(IMPPParameterType.HOME));
	}
	
	@Test
	public void testRemoveExtendedIMPPParameterType() {
		imppType1.addExtendedIMPPParameterType(new XIMPPParameterType("X-REMOVEME"));
		assertTrue(imppType1.containsExtendedIMPPParameterType(new XIMPPParameterType("X-REMOVEME")));
		imppType1.removeExtendedIMPPParameterType(new XIMPPParameterType("X-REMOVEME"));
		assertFalse(imppType1.containsExtendedIMPPParameterType(new XIMPPParameterType("X-REMOVEME")));
	}
	
	@Test
	public void testEquals() throws URISyntaxException {
		IMPPType imppType4 = new IMPPType("im:alice@example.com");
		imppType4.addIMPPParameterType(IMPPParameterType.WORK);
		imppType4.addIMPPParameterType(IMPPParameterType.PREF);
		imppType4.addExtendedIMPPParameterType(new XIMPPParameterType("X-PARAM-1", "Something extra"));
		imppType4.addExtendedIMPPParameterType(new XIMPPParameterType("X-PARAM-2"));
		
		assertTrue(imppType1.equals(imppType4));
	}
	
	@Test
	public void testHashcode() throws URISyntaxException {
		IMPPType imppType4 = new IMPPType("im:alice@example.com");
		imppType4.addIMPPParameterType(IMPPParameterType.WORK);
		imppType4.addIMPPParameterType(IMPPParameterType.PREF);
		imppType4.addExtendedIMPPParameterType(new XIMPPParameterType("X-PARAM-1", "Something extra"));
		imppType4.addExtendedIMPPParameterType(new XIMPPParameterType("X-PARAM-2"));
		
		int hcode1 = imppType1.hashCode();
		int hcode2 = imppType4.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		IMPPFeature cloned = imppType1.clone();
		assertEquals(cloned, imppType1);
		assertTrue(imppType1.equals(cloned));
	}
}
