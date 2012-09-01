package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.UrlParamType;
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
public class URLTypeTest {

	private UrlType urlType1 = null;
	private UrlType urlType2 = null;
	private UrlType urlType3 = null;
	private UrlType urlType4 = null;
	
	@Before
	public void setUp() throws Exception {
		urlType1 = new UrlType("http://sourceforge.net");
		urlType1.addParam(UrlParamType.WORK)
			.addParam(UrlParamType.PREF)
			.addExtendedParam(new ExtendedParamType("X-PROTOCOL", "HTTPS", VCardTypeName.URL))
			.addExtendedParam(new ExtendedParamType("X-SSL", VCardTypeName.URL));
		
		urlType2 = new UrlType(new URL("http://sourceforge.net"));
		urlType3 = new UrlType();
		urlType4 = new UrlType("this should be ok.");
	}
	
	@After
	public void tearDown() throws Exception {
		urlType1 = null;
		urlType2 = null;
		urlType3 = null;
		urlType4 = null;
	}
	
	@Test
	public void testGetUrl() throws MalformedURLException {
		URL url = new URL("http://sourceforge.net");
		assertEquals(url, urlType1.getUrl());
		assertEquals(url, urlType2.getUrl());
		assertNull(urlType3.getUrl());
		assertNull(urlType4.getUrl());
		assertTrue(urlType4.hasRawUrl());
		assertEquals("this should be ok.", urlType4.getRawUrl());
	}
	
	@Test
	public void testHasUrl() {
		assertTrue(urlType1.hasUrl());
		assertTrue(urlType2.hasUrl());
		assertFalse(urlType3.hasUrl());
		assertFalse(urlType4.hasUrl());
		assertTrue(urlType4.hasRawUrl());
	}
	
	@Test
	public void testClearURL() {
		urlType1.clearUrl();
		urlType2.clearUrl();
		urlType3.clearUrl();
		urlType4.clearUrl();
		
		assertFalse(urlType1.hasUrl());
		assertFalse(urlType2.hasUrl());
		assertFalse(urlType3.hasUrl());
		assertFalse(urlType4.hasUrl());
		assertFalse(urlType4.hasRawUrl());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.URL.getType(), urlType1.getVCardTypeName().getType());
	}
	
	@Test
	public void testContainsParam() {
		assertTrue(urlType1.containsParam(UrlParamType.PREF));
		assertTrue(urlType1.containsParam(UrlParamType.WORK));
		assertFalse(urlType1.containsParam(UrlParamType.HOME));
	}
	
	@Test
	public void testContainsParams() {
		List<UrlParamType> types = new ArrayList<UrlParamType>(2);
		types.add(UrlParamType.PREF);
		types.add(UrlParamType.WORK);
		
		assertTrue(urlType1.containsAllParams(types));
	}
	
	@Test
	public void testContainsExtendedParam() {
		assertTrue(urlType1.containsExtendedParam(new ExtendedParamType("X-PROTOCOL", "HTTPS", VCardTypeName.URL)));
		assertTrue(urlType1.containsExtendedParam(new ExtendedParamType("X-SSL", VCardTypeName.URL)));
		assertFalse(urlType1.containsExtendedParam(new ExtendedParamType("X-NOT-EXISTS", VCardTypeName.URL)));
	}
	
	@Test
	public void testContainsAllExtendedParams() {
		List<ExtendedParamType> types = new ArrayList<ExtendedParamType>(2);
		types.add(new ExtendedParamType("X-PROTOCOL", "HTTPS", VCardTypeName.URL));
		types.add(new ExtendedParamType("X-SSL", VCardTypeName.URL));
		
		assertTrue(urlType1.containsAllExtendedParams(types));
	}
	
	@Test
	public void testRemoveParam() {
		urlType1.addParam(UrlParamType.HOME);
		assertTrue(urlType1.containsParam(UrlParamType.HOME));
		urlType1.removeParam(UrlParamType.HOME);
		assertFalse(urlType1.containsParam(UrlParamType.HOME));
	}
	
	@Test
	public void testRemoveExtendedParam() {
		urlType1.addExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.URL));
		assertTrue(urlType1.containsExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.URL)));
		urlType1.removeExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.URL));
		assertFalse(urlType1.containsExtendedParam(new ExtendedParamType("X-REMOVEME", VCardTypeName.URL)));
	}
	
	@Test
	public void testEquals() {
		UrlType urlType5 = new UrlType("http://sourceforge.net");
		urlType5.addParam(UrlParamType.WORK)
			.addParam(UrlParamType.PREF)
			.addExtendedParam(new ExtendedParamType("X-PROTOCOL", "HTTPS", VCardTypeName.URL))
			.addExtendedParam(new ExtendedParamType("X-SSL", VCardTypeName.URL));
		
		assertTrue(urlType1.equals(urlType5));
	}
	
	@Test
	public void testHashcode() {
		UrlType urlType5 = new UrlType("http://sourceforge.net");
		urlType5.addParam(UrlParamType.WORK)
			.addParam(UrlParamType.PREF)
			.addExtendedParam(new ExtendedParamType("X-PROTOCOL", "HTTPS", VCardTypeName.URL))
			.addExtendedParam(new ExtendedParamType("X-SSL", VCardTypeName.URL));
		
		int hcode1 = urlType1.hashCode();
		int hcode2 = urlType5.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		UrlType urlType5 = new UrlType("http://sourceforge.net");
		urlType5.addParam(UrlParamType.WORK)
			.addParam(UrlParamType.PREF)
			.addExtendedParam(new ExtendedParamType("X-PROTOCOL", "HTTPS", VCardTypeName.URL))
			.addExtendedParam(new ExtendedParamType("X-SSL", VCardTypeName.URL));
		
		assertTrue(urlType1.compareTo(urlType5) == 0);
	}
	
	@Test
	public void testClone() {
		UrlType cloned = urlType1.clone();
		assertEquals(urlType1, cloned);
		assertTrue(urlType1.equals(cloned));
		assertTrue(urlType1.compareTo(cloned) == 0);
		
		UrlType cloned2 = urlType4.clone();
		assertEquals(urlType4, cloned2);
		assertTrue(urlType4.equals(cloned2));
		assertTrue(urlType4.compareTo(cloned2) == 0);
	}
}
