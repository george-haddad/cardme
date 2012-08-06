package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.LogoFeature;
import net.sourceforge.cardme.vcard.types.media.ImageMediaType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
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
 * Oct 3, 2011
 *
 */
public class LogoTypeTest {

	private LogoType logoTypeBin = null;
	private LogoType logoTypeUri = null;
	
	@Before
	public void setUp() throws Exception {
		logoTypeBin = new LogoType();
		logoTypeBin.setLogo(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05});
		logoTypeBin.setEncodingType(EncodingType.BINARY);
		logoTypeBin.setCompression(false);
		logoTypeBin.setImageMediaType(ImageMediaType.JPEG);
		
		logoTypeUri = new LogoType();
		logoTypeUri.setLogoURI(new URI("file://C:/my_logos/john.jpg"));
		logoTypeUri.setEncodingType(EncodingType.EIGHT_BIT);
	}
	
	@After
	public void tearDown() throws Exception {
		logoTypeBin = null;
		logoTypeUri = null;
	}
	
	@Test
	public void testGetLogo() throws URISyntaxException {
		byte[] logoBytes = logoTypeBin.getLogo();
		assertTrue(Arrays.equals(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05}, logoBytes));
		
		URI logoUri = logoTypeUri.getLogoURI();
		assertEquals(new URI("file://C:/my_logos/john.jpg"), logoUri);
	}
	
	@Test
	public void testIsCompression() {
		assertFalse(logoTypeBin.isSetCompression());
		assertFalse(logoTypeUri.isSetCompression());
	}
	
	@Test
	public void testGetLogoEncodingType() {
		assertEquals(EncodingType.BINARY, logoTypeBin.getEncodingType());
		assertEquals(EncodingType.EIGHT_BIT, logoTypeUri.getEncodingType());
	}
	
	@Test
	public void testIsInline() {
		assertTrue(logoTypeBin.isInline());
		assertFalse(logoTypeUri.isInline());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(logoTypeBin.getTypeString(), VCardType.LOGO.getType());
	}
	
	@Test
	public void testEquals() throws URISyntaxException {
		LogoType logoTypeBin2 = new LogoType();
		logoTypeBin2.setLogo(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05});
		logoTypeBin2.setEncodingType(EncodingType.BINARY);
		logoTypeBin2.setCompression(false);
		logoTypeBin2.setImageMediaType(ImageMediaType.JPEG);
		
		LogoType logoTypeUri2 = new LogoType();
		logoTypeUri2.setLogoURI(new URI("file://C:/my_logos/john.jpg"));
		logoTypeUri2.setEncodingType(EncodingType.EIGHT_BIT);
		
		assertTrue(logoTypeBin.equals(logoTypeBin2));
		assertTrue(logoTypeUri.equals(logoTypeUri2));
	}
	
	@Test
	public void testHashcode() throws URISyntaxException {
		LogoType logoTypeBin2 = new LogoType();
		logoTypeBin2.setLogo(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05});
		logoTypeBin2.setEncodingType(EncodingType.BINARY);
		logoTypeBin2.setCompression(false);
		logoTypeBin2.setImageMediaType(ImageMediaType.JPEG);
		
		LogoType logoTypeUri2 = new LogoType();
		logoTypeUri2.setLogoURI(new URI("file://C:/my_logos/john.jpg"));
		logoTypeUri2.setEncodingType(EncodingType.EIGHT_BIT);
		
		int hcode1 = logoTypeBin.hashCode();
		int hcode2 = logoTypeBin2.hashCode();
		assertEquals(hcode1, hcode2);
		
		hcode1 = logoTypeUri.hashCode();
		hcode2 = logoTypeUri2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		LogoFeature cloned1 = logoTypeBin.clone(); 
		LogoFeature cloned2 = logoTypeUri.clone(); 
		
		assertEquals(cloned1, logoTypeBin);
		assertTrue(logoTypeBin.equals(cloned1));
		
		assertEquals(cloned2, logoTypeUri);
		assertTrue(logoTypeUri.equals(cloned2));
	}
}
