package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.URLFeature;
import net.sourceforge.cardme.vcard.types.parameters.URLParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XURLParameterType;
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
 * Oct 4, 2011
 *
 */
public class URLTypeTest {

	private URLType urlType1 = null;
	private URLType urlType2 = null;
	private URLType urlType3 = null;
	private URLType urlType4 = null;
	
	@Before
	public void setUp() throws Exception {
		urlType1 = new URLType("http://sourceforge.net");
		urlType1.addURLParameterType(URLParameterType.WORK);
		urlType1.addURLParameterType(URLParameterType.PREF);
		urlType1.addExtendedURLParameterType(new XURLParameterType("X-PROTOCOL", "HTTPS"));
		urlType1.addExtendedURLParameterType(new XURLParameterType("X-SSL"));
		
		urlType2 = new URLType(new URL("http://sourceforge.net"));
		urlType3 = new URLType();
		urlType4 = new URLType("this should be ok.");
	}
	
	@After
	public void tearDown() throws Exception {
		urlType1 = null;
		urlType2 = null;
		urlType3 = null;
		urlType4 = null;
	}
	
	@Test
	public void testGetURL() throws MalformedURLException {
		URL url = new URL("http://sourceforge.net");
		assertEquals(url, urlType1.getURL());
		assertEquals(url, urlType2.getURL());
		assertNull(urlType3.getURL());
		assertNull(urlType4.getURL());
		assertTrue(urlType4.hasRawURL());
		assertEquals("this should be ok.", urlType4.getRawURL());
	}
	
	@Test
	public void testHasURL() {
		assertTrue(urlType1.hasURL());
		assertTrue(urlType2.hasURL());
		assertFalse(urlType3.hasURL());
		assertFalse(urlType4.hasURL());
		assertTrue(urlType4.hasRawURL());
	}
	
	@Test
	public void testClearURL() {
		urlType1.clearURL();
		urlType2.clearURL();
		urlType3.clearURL();
		urlType4.clearURL();
		
		assertFalse(urlType1.hasURL());
		assertFalse(urlType2.hasURL());
		assertFalse(urlType3.hasURL());
		assertFalse(urlType4.hasURL());
		assertFalse(urlType4.hasRawURL());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(urlType1.getTypeString(), VCardType.URL.getType());
	}
	
	@Test
	public void testContainsURLParameterType() {
		assertTrue(urlType1.containsURLParameterType(URLParameterType.PREF));
		assertTrue(urlType1.containsURLParameterType(URLParameterType.WORK));
		assertFalse(urlType1.containsURLParameterType(URLParameterType.HOME));
	}
	
	@Test
	public void testContainsAllURLParameterTypes() {
		List<URLParameterType> types = new ArrayList<URLParameterType>(2);
		types.add(URLParameterType.PREF);
		types.add(URLParameterType.WORK);
		
		assertTrue(urlType1.containsAllURLParameterTypes(types));
	}
	
	@Test
	public void testContainsExtendedURLParameterType() {
		assertTrue(urlType1.containsExtendedURLParameterType(new XURLParameterType("X-PROTOCOL", "HTTPS")));
		assertTrue(urlType1.containsExtendedURLParameterType(new XURLParameterType("X-SSL")));
		assertFalse(urlType1.containsExtendedURLParameterType(new XURLParameterType("X-NOT-EXISTS")));
	}
	
	@Test
	public void testContainsAllExtendedURLParameterTypes() {
		List<XURLParameterType> types = new ArrayList<XURLParameterType>(2);
		types.add(new XURLParameterType("X-PROTOCOL", "HTTPS"));
		types.add(new XURLParameterType("X-SSL"));
		
		assertTrue(urlType1.containsAllExtendedURLParameterTypes(types));
	}
	
	@Test
	public void testRemoveURLParameterType() {
		urlType1.addURLParameterType(URLParameterType.HOME);
		assertTrue(urlType1.containsURLParameterType(URLParameterType.HOME));
		urlType1.removeURLParameterType(URLParameterType.HOME);
		assertFalse(urlType1.containsURLParameterType(URLParameterType.HOME));
	}
	
	@Test
	public void testRemoveExtendedURLParameterType() {
		urlType1.addExtendedURLParameterType(new XURLParameterType("X-REMOVEME"));
		assertTrue(urlType1.containsExtendedURLParameterType(new XURLParameterType("X-REMOVEME")));
		urlType1.removeExtendedURLParameterType(new XURLParameterType("X-REMOVEME"));
		assertFalse(urlType1.containsExtendedURLParameterType(new XURLParameterType("X-REMOVEME")));
	}
	
	@Test
	public void testEquals() {
		URLType urlType5 = new URLType("http://sourceforge.net");
		urlType5.addURLParameterType(URLParameterType.WORK);
		urlType5.addURLParameterType(URLParameterType.PREF);
		urlType5.addExtendedURLParameterType(new XURLParameterType("X-PROTOCOL", "HTTPS"));
		urlType5.addExtendedURLParameterType(new XURLParameterType("X-SSL"));
		
		assertTrue(urlType1.equals(urlType5));
	}
	
	@Test
	public void testHashcode() {
		URLType urlType5 = new URLType("http://sourceforge.net");
		urlType5.addURLParameterType(URLParameterType.WORK);
		urlType5.addURLParameterType(URLParameterType.PREF);
		urlType5.addExtendedURLParameterType(new XURLParameterType("X-PROTOCOL", "HTTPS"));
		urlType5.addExtendedURLParameterType(new XURLParameterType("X-SSL"));
		
		int hcode1 = urlType1.hashCode();
		int hcode2 = urlType5.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		URLFeature cloned = urlType1.clone();
		assertEquals(cloned, urlType1);
		assertTrue(urlType1.equals(cloned));
		
		URLFeature cloned2 = urlType4.clone();
		assertEquals(cloned2, urlType4);
		assertTrue(urlType4.equals(cloned2));
	}
}
