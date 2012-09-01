package net.sourceforge.cardme;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.io.VCardWriter;
import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.arch.EncodingType;
import net.sourceforge.cardme.vcard.exceptions.VCardBuildException;
import net.sourceforge.cardme.vcard.types.AdrType;
import net.sourceforge.cardme.vcard.types.CategoriesType;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.FNType;
import net.sourceforge.cardme.vcard.types.KeyType;
import net.sourceforge.cardme.vcard.types.NType;
import net.sourceforge.cardme.vcard.types.OrgType;
import net.sourceforge.cardme.vcard.types.PhotoType;
import net.sourceforge.cardme.vcard.types.TelType;
import net.sourceforge.cardme.vcard.types.UrlType;
import net.sourceforge.cardme.vcard.types.media.ImageMediaType;
import net.sourceforge.cardme.vcard.types.media.KeyTextType;
import net.sourceforge.cardme.vcard.types.params.TelParamType;
import org.junit.Test;

/*
 * Copyright 2011 Michael Rimov. All rights reserved.
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
 * THIS SOFTWARE IS PROVIDED BY MICHAEL RIMOV ''AS IS'' AND ANY EXPRESS OR IMPLIED
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
 * or implied, of Michael Rimov.
 */

/**
 * 
 * @author rimovm
 * <br/>
 * Nov 11, 2011
 *
 */
public class TestVcardFieldMarshalling {

	@Test
	public void testVCardName() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		String result = getSerializedString(vcard);
		assertNotNull(result);
		assertTrue(result.contains("BEGIN:VCARD"));
		assertTrue(result.contains("VERSION:3.0"));
		assertTrue(result.contains("N:User;Example;;;"));
		assertTrue(result.contains("FN:User\\, Example"));
		assertTrue(result.contains("END:VCARD"));
	}
	
	@Test
	public void testCategory() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		vcard.setCategories(new CategoriesType("work"));
		String result = getSerializedString(vcard);
		assertTrue(result.contains("CATEGORIES:work"));
	}
	
	@Test
	public void testOrganization() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		vcard.setOrg(new OrgType("Acme, Inc."));
		String result = getSerializedString(vcard);
		assertTrue(result.contains("ORG:Acme\\, Inc."));		
	}
	
	@Test
	public void testAddress() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		AdrType address = new AdrType();
		address.setGroup("work");
		address.setCountryName("US");
		address.setPostalCode("55555-0123");
		address.setLocality("Anytown");
		address.setStreetAddress("555 Some Street Address");
		
		vcard.addAdr(address);
		String result = getSerializedString(vcard);
		assertTrue(result.contains("work.ADR:;;555 Some Street Address;Anytown;;55555-0123;US"));		
	}
	
	@Test
	public void testEmailAddress() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		vcard.addEmail(new EmailType("joe@example.org"));
		String result = getSerializedString(vcard);
		assertTrue(result.contains("EMAIL:joe@example.org"));		
	}
	
	@Test
	public void testURL() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		vcard.addUrl(new UrlType("http://www.example.org/"));
		String result = getSerializedString(vcard);
		assertTrue(result.contains("URL:http://www.example.org/"));
		
		vcard.addUrl(new UrlType("ftp://ftp.example.org/"));
		result = getSerializedString(vcard);
		assertTrue(result.contains("URL:ftp://ftp.example.org/"));
		
		vcard.addUrl(new UrlType("this is free form text"));
		result = getSerializedString(vcard);
		assertTrue(result.contains("URL:this is free form text"));
	}
	
	@Test
	public void testPhoneNumber() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		
		vcard.addTel(new TelType("+1 (555) 555-1232").addParam(TelParamType.WORK));
		vcard.addTel(new TelType("+1 (555) 555-1233").addParam(TelParamType.FAX));
		vcard.addTel(new TelType("+1 (555) 555-1234").addParam(TelParamType.CELL));
		vcard.addTel(new TelType("+1 (555) 555-1234").addParam(TelParamType.HOME));
		
		String result = getSerializedString(vcard);
		assertTrue(result.contains("TEL;TYPE=WORK:+1 (555) 555-1232"));	
		assertTrue(result.contains("TEL;TYPE=FAX:+1 (555) 555-1233"));	
		assertTrue(result.contains("TEL;TYPE=CELL:+1 (555) 555-1234"));	
		assertTrue(result.contains("TEL;TYPE=HOME:+1 (555) 555-1234"));	
	}
	
	@Test
	public void testLinkedProfileImage() throws URISyntaxException, VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		
		PhotoType photo = new PhotoType();
		photo.setPhotoURI(new URI("http://www.google.com/intl/en_com/images/srpr/logo3w.png"));
		photo.setImageMediaType(ImageMediaType.PNG);
		vcard.addPhoto(photo);
		String result = getSerializedString(vcard);
		
		//Changed path to full URI as per http://www.ietf.org/rfc/rfc2426.txt, section 3.1.4
		assertTrue(result.contains("PHOTO;VALUE=URI:http://www.google.com/intl/en_com/images/srpr/logo3w.png"));
	}
	
	@Test
	public void testProfileImage() throws IOException, VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		
		PhotoType photo = new PhotoType();
		photo.setImageMediaType(ImageMediaType.PNG);
		photo.setEncodingType(EncodingType.BASE64);
		photo.setCompression(true);

		byte[] tuxPicture1 = Util.getFileAsBytes(new File("test/images/smallTux.png"));
		photo.setPhoto(tuxPicture1);
		
		vcard.addPhoto(photo);
		String result = getSerializedString(vcard);
		assertTrue(result.contains("PHOTO;ENCODING=BASE64;TYPE=PNG:"));
	}
	
	@Test
	public void testKey() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		
		KeyType key = new KeyType("plain text key", KeyTextType.PGP);
		vcard.addKey(key);
		key = new KeyType("binary data".getBytes(), KeyTextType.X509);
		vcard.addKey(key);

		String result = getSerializedString(vcard);
		assertTrue(result.contains("KEY;TYPE=PGP:plain text key"));
		assertTrue(result.contains("KEY;ENCODING=B;TYPE=X509:YmluYXJ5IGRhdGE="));
	}
	

	protected String getSerializedString(VCardImpl vcard) throws VCardBuildException {
		VCardWriter writer = new VCardWriter();
		writer.setCompatibilityMode(CompatibilityMode.RFC2426);
		writer.setVCard(vcard);
		String result = writer.buildVCardString();
		return result;
	}

	protected void appyBasicName(VCardImpl vcard) {
		vcard.setN(new NType("User", "Example"));
		vcard.setFN(new FNType("User, Example"));
	}
}
