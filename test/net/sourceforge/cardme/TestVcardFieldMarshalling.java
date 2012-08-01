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
import net.sourceforge.cardme.vcard.types.NameType;
import net.sourceforge.cardme.vcard.types.OrganizationType;
import net.sourceforge.cardme.vcard.types.PhotoType;
import net.sourceforge.cardme.vcard.types.TelephoneType;
import net.sourceforge.cardme.vcard.types.URLType;
import net.sourceforge.cardme.vcard.types.media.ImageMediaType;
import net.sourceforge.cardme.vcard.types.parameters.TelephoneParameterType;
import org.junit.Test;

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
