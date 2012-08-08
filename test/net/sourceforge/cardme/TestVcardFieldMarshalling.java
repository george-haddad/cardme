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
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.types.AddressType;
import net.sourceforge.cardme.vcard.types.CategoriesType;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.FormattedNameType;
import net.sourceforge.cardme.vcard.types.KeyType;
import net.sourceforge.cardme.vcard.types.NameType;
import net.sourceforge.cardme.vcard.types.OrganizationType;
import net.sourceforge.cardme.vcard.types.PhotoType;
import net.sourceforge.cardme.vcard.types.TelephoneType;
import net.sourceforge.cardme.vcard.types.URLType;
import net.sourceforge.cardme.vcard.types.media.ImageMediaType;
import net.sourceforge.cardme.vcard.types.media.KeyTextType;
import net.sourceforge.cardme.vcard.types.parameters.TelephoneParameterType;
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
	public void testVCardName() {
		VCardImpl vcard = new VCardImpl();
		vcard.setID("test");
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
	public void testCategory() {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		vcard.setCategories(new CategoriesType("work"));
		String result = getSerializedString(vcard);
		assertTrue(result.contains("CATEGORIES:work"));
	}
	
	@Test
	public void testOrganization() {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		vcard.setOrganizations(new OrganizationType("Acme, Inc."));
		String result = getSerializedString(vcard);
		assertTrue(result.contains("ORG:Acme\\, Inc."));		
	}
	
	@Test
	public void testAddress() {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		AddressType address = new AddressType();
		address.setGroup("work");
		address.setCountryName("US");
		address.setPostalCode("55555-0123");
		address.setLocality("Anytown");
		address.setStreetAddress("555 Some Street Address");
		
		vcard.addAddress(address);
		String result = getSerializedString(vcard);
		assertTrue(result.contains("work.ADR:;;555 Some Street Address;Anytown;;55555-0123;US"));		
	}
	
	@Test
	public void testEmailAddress() {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		vcard.addEmail(new EmailType("joe@example.org"));
		String result = getSerializedString(vcard);
		assertTrue(result.contains("EMAIL:joe@example.org"));		
	}
	
	@Test
	public void testURL() {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		vcard.addURL(new URLType("http://www.example.org/"));
		String result = getSerializedString(vcard);
		assertTrue(result.contains("URL:http://www.example.org/"));
		
		vcard.addURL(new URLType("ftp://ftp.example.org/"));
		result = getSerializedString(vcard);
		assertTrue(result.contains("URL:ftp://ftp.example.org/"));
		
		vcard.addURL(new URLType("this is free form text"));
		result = getSerializedString(vcard);
		assertTrue(result.contains("URL:this is free form text"));
	}
	
	@Test
	public void testPhoneNumber() {
		VCardImpl vcard = new VCardImpl();
		appyBasicName(vcard);
		
		vcard.addTelephoneNumber(new TelephoneType("+1 (555) 555-1232", TelephoneParameterType.WORK));
		vcard.addTelephoneNumber(new TelephoneType("+1 (555) 555-1233", TelephoneParameterType.FAX));
		vcard.addTelephoneNumber(new TelephoneType("+1 (555) 555-1234", TelephoneParameterType.CELL));
		vcard.addTelephoneNumber(new TelephoneType("+1 (555) 555-1234", TelephoneParameterType.HOME));
		
		String result = getSerializedString(vcard);
		assertTrue(result.contains("TEL;TYPE=WORK:+1 (555) 555-1232"));	
		assertTrue(result.contains("TEL;TYPE=FAX:+1 (555) 555-1233"));	
		assertTrue(result.contains("TEL;TYPE=CELL:+1 (555) 555-1234"));	
		assertTrue(result.contains("TEL;TYPE=HOME:+1 (555) 555-1234"));	
	}
	
	@Test
	public void testLinkedProfileImage() throws URISyntaxException {
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
	public void testProfileImage() throws IOException {
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
	public void testKey() {
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
	

	protected String getSerializedString(VCardImpl vcard) {
		VCardWriter writer = new VCardWriter();
		writer.setCompatibilityMode(CompatibilityMode.RFC2426);
		writer.setVCard(vcard);
		String result = writer.buildVCardString();
		return result;
	}

	protected void appyBasicName(VCardImpl vcard) {
		vcard.setName(new NameType("User", "Example"));
		vcard.setFormattedName(new FormattedNameType("User, Example"));
	}
}
