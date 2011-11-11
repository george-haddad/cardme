package info.ineighborhood.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import info.ineighborhood.cardme.vcard.VCardType;
import info.ineighborhood.cardme.vcard.features.URLFeature;
import java.net.MalformedURLException;
import java.net.URL;
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
	
	@Before
	public void setUp() throws Exception {
		urlType1 = new URLType("http://sourceforge.net");
		urlType2 = new URLType(new URL("http://sourceforge.net"));
		urlType3 = new URLType();
	}
	
	@After
	public void tearDown() throws Exception {
		urlType1 = null;
		urlType2 = null;
		urlType3 = null;
	}
	
	@Test
	public void testGetURL() throws MalformedURLException {
		URL url = new URL("http://sourceforge.net");
		assertEquals(url, urlType1.getURL());
		assertEquals(url, urlType2.getURL());
		assertNull(urlType3.getURL());
	}
	
	@Test
	public void testHasURL() {
		assertTrue(urlType1.hasURL());
		assertTrue(urlType2.hasURL());
		assertFalse(urlType3.hasURL());
	}
	
	@Test
	public void testClearURL() {
		urlType1.clearURL();
		urlType2.clearURL();
		urlType3.clearURL();
		
		assertFalse(urlType1.hasURL());
		assertFalse(urlType2.hasURL());
		assertFalse(urlType3.hasURL());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(urlType1.getTypeString(), VCardType.URL.getType());
	}
	
	@Test
	public void testEquals() throws MalformedURLException {
		URLType urlType4 = new URLType("http://sourceforge.net");
		assertTrue(urlType1.equals(urlType4));
	}
	
	@Test
	public void testHashcode() throws MalformedURLException {
		URLType urlType4 = new URLType("http://sourceforge.net");
		
		int hcode1 = urlType1.hashCode();
		int hcode2 = urlType4.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		URLFeature cloned = urlType1.clone();
		assertEquals(cloned, urlType1);
		assertTrue(urlType1.equals(cloned));
	}
}
