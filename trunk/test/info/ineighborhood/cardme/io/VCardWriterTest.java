package info.ineighborhood.cardme.io;

import static org.junit.Assert.assertTrue;
import info.ineighborhood.cardme.util.Util;
import info.ineighborhood.cardme.vcard.EncodingType;
import info.ineighborhood.cardme.vcard.VCard;
import info.ineighborhood.cardme.vcard.VCardImpl;
import info.ineighborhood.cardme.vcard.VCardVersion;
import info.ineighborhood.cardme.vcard.errors.VCardErrorHandling;
import info.ineighborhood.cardme.vcard.features.AddressFeature;
import info.ineighborhood.cardme.vcard.features.CategoriesFeature;
import info.ineighborhood.cardme.vcard.features.DisplayableNameFeature;
import info.ineighborhood.cardme.vcard.features.EmailFeature;
import info.ineighborhood.cardme.vcard.features.FormattedNameFeature;
import info.ineighborhood.cardme.vcard.features.LabelFeature;
import info.ineighborhood.cardme.vcard.features.LogoFeature;
import info.ineighborhood.cardme.vcard.features.NameFeature;
import info.ineighborhood.cardme.vcard.features.NicknameFeature;
import info.ineighborhood.cardme.vcard.features.NoteFeature;
import info.ineighborhood.cardme.vcard.features.OrganizationFeature;
import info.ineighborhood.cardme.vcard.features.PhotoFeature;
import info.ineighborhood.cardme.vcard.features.ProfileFeature;
import info.ineighborhood.cardme.vcard.features.SoundFeature;
import info.ineighborhood.cardme.vcard.features.SourceFeature;
import info.ineighborhood.cardme.vcard.features.TelephoneFeature;
import info.ineighborhood.cardme.vcard.types.AddressType;
import info.ineighborhood.cardme.vcard.types.BirthdayType;
import info.ineighborhood.cardme.vcard.types.CategoriesType;
import info.ineighborhood.cardme.vcard.types.ClassType;
import info.ineighborhood.cardme.vcard.types.DisplayableNameType;
import info.ineighborhood.cardme.vcard.types.EmailType;
import info.ineighborhood.cardme.vcard.types.ExtendedType;
import info.ineighborhood.cardme.vcard.types.FormattedNameType;
import info.ineighborhood.cardme.vcard.types.GeographicPositionType;
import info.ineighborhood.cardme.vcard.types.LabelType;
import info.ineighborhood.cardme.vcard.types.LogoType;
import info.ineighborhood.cardme.vcard.types.MailerType;
import info.ineighborhood.cardme.vcard.types.NameType;
import info.ineighborhood.cardme.vcard.types.NicknameType;
import info.ineighborhood.cardme.vcard.types.NoteType;
import info.ineighborhood.cardme.vcard.types.OrganizationType;
import info.ineighborhood.cardme.vcard.types.PhotoType;
import info.ineighborhood.cardme.vcard.types.ProductIdType;
import info.ineighborhood.cardme.vcard.types.ProfileType;
import info.ineighborhood.cardme.vcard.types.RevisionType;
import info.ineighborhood.cardme.vcard.types.RoleType;
import info.ineighborhood.cardme.vcard.types.SortStringType;
import info.ineighborhood.cardme.vcard.types.SoundType;
import info.ineighborhood.cardme.vcard.types.SourceType;
import info.ineighborhood.cardme.vcard.types.TelephoneType;
import info.ineighborhood.cardme.vcard.types.TimeZoneType;
import info.ineighborhood.cardme.vcard.types.TitleType;
import info.ineighborhood.cardme.vcard.types.UIDType;
import info.ineighborhood.cardme.vcard.types.URLType;
import info.ineighborhood.cardme.vcard.types.VersionType;
import info.ineighborhood.cardme.vcard.types.media.AudioMediaType;
import info.ineighborhood.cardme.vcard.types.media.ImageMediaType;
import info.ineighborhood.cardme.vcard.types.parameters.AddressParameterType;
import info.ineighborhood.cardme.vcard.types.parameters.EmailParameterType;
import info.ineighborhood.cardme.vcard.types.parameters.LabelParameterType;
import info.ineighborhood.cardme.vcard.types.parameters.ParameterTypeStyle;
import info.ineighborhood.cardme.vcard.types.parameters.TelephoneParameterType;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
 * Oct 22, 2011
 *
 */
public class VCardWriterTest {

//	private VCard vcard = null;
	private VCard fullVCard = null;
	private static VCardWriter vcardWriter = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vcardWriter = new VCardWriter();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		vcardWriter = null;
	}
	
	@Before
	public void setUp() throws Exception {
//		vcard = new VCardImpl();
		fullVCard = getFullVCardNoErrors();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(fullVCard);
	}
	
	@After
	public void tearDown() throws Exception {
		fullVCard = null;
//		vcard = null;
		vcardWriter.reset();
	}
	
	@Test
	public void testBuildBeginFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("BEGIN");
		int stopIndex = vcardStr.indexOf('\n')+1;
		String line = vcardStr.substring(startIndex, stopIndex); 
		
		assertTrue(line.compareTo("BEGIN:VCARD\r\n") == 0);
	}
	
	@Test
	public void testBuildEndFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("END");
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("END:VCARD\r\n") == 0);
	}
	
	@Test
	public void testBuildVersionFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nVERSION:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("VERSION:3.0\r\n") == 0);
	}
	
	@Test
	public void testBuildNameFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nN:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("N:Doe;John;Johny;Mr.;I\r\n") == 0);
	}
	
	@Test
	public void testBuildFormattedNameFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nFN:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("FN:John \"Johny\" Doe\r\n") == 0);
	}
	
	@Test
	public void testBuildDisplayableNameFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nNAME:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("NAME:VCard for John Doe\r\n") == 0);
	}

	@Test
	public void testBuildProfileFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nPROFILE:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("PROFILE:VCard\r\n") == 0);
	}
	
	@Test
	public void testBuildSourceFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nSOURCE:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("SOURCE:Whatever\r\n") == 0);
	}
	
	@Test
	public void testBuildTitleFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nTITLE:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("TITLE:Generic Accountant\r\n") == 0);
	}
	
	@Test
	public void testBuildRoleFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nROLE:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("ROLE:Counting Money\r\n") == 0);
	}
	
	@Test
	public void testBuildGeographicPositionFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nGEO:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("GEO:-2.600000;3.400000\r\n") == 0);
	}
	
	@Test
	public void testBuildOrganization() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nORG:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("ORG:IBM;SUN\r\n") == 0);
	}
	
	@Test
	public void testBuildMailerFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nMAILER:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("MAILER:Mozilla Thunderbird\r\n") == 0);
	}
	
	@Test
	public void testBuildTimeZoneFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nTZ:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("TZ:2:00\r\n") == 0);
	}
	
	@Test
	public void testBuildUrlFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nURL:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("URL:http\\://www.sun.com\r\n") == 0);
	}
	
	@Test
	public void testBuildRevisionFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nREV:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("REV:2011-12-22T16:30:05Z\r\n") == 0);
	}
	
	@Test
	public void testBuildUidFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nUID:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("UID:c0ff639f-9633-4e57-bcfd-55079cfd9d65\r\n") == 0);
	}
	
	@Test
	public void testBuildBirthdayFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nBDAY:")+2;
		int stopIndex = vcardStr.indexOf('\n', startIndex)+1;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("BDAY:1980-05-21\r\n") == 0);
	}
	
	@Test
	public void testBuildAddressFeature() {
		String vcardStr = vcardWriter.buildVCardString();
		
		int startIndex = vcardStr.indexOf("\r\nADR;")+2;
		int stopIndex = vcardStr.indexOf("\r\nLABEL;", startIndex)+2;
		String line = vcardStr.substring(startIndex, stopIndex);
		
		assertTrue(line.compareTo("ADR;TYPE=HOME,PARCEL,PREF:25334;;South cresent drive\\, Building 5\\, 3rd flo\r\n or;New York;New York;NYC887;U.S.A.\r\n") == 0);
	}
	
	//TODO a lot more to code
	
	private VCard getFullVCardNoErrors() throws IOException
	{
		VCard vcard = new VCardImpl();
		vcard.setVersion(new VersionType(VCardVersion.V3_0));
		
		DisplayableNameFeature displayableName = new DisplayableNameType();
		displayableName.setName("VCard for John Doe");
		vcard.setDisplayableNameFeature(displayableName);
		
		ProfileFeature profile = new ProfileType();
		profile.setProfile("VCard");
		vcard.setProfile(profile);
		
		SourceFeature source = new SourceType();
		source.setSource("Whatever");
		vcard.setSource(source);
		
		NameFeature name = new NameType();
		name.setFamilyName("Doe");
		name.setGivenName("John");
		name.addHonorificPrefix("Mr.");
		name.addHonorificSuffix("I");
		name.addAdditionalName("Johny");
		vcard.setName(name);
		
		FormattedNameFeature formattedName = new FormattedNameType();
		formattedName.setFormattedName("John \"Johny\" Doe");
		vcard.setFormattedName(formattedName);
		
		NicknameFeature nicknames = new NicknameType();
		nicknames.addNickname("Johny");
		nicknames.addNickname("JayJay");
		vcard.setNicknames(nicknames);
		
		CategoriesFeature categories = new CategoriesType();
		categories.addCategory("Category 1");
		categories.addCategory("Category 2");
		categories.addCategory("Category 3");
		vcard.setCategories(categories);
		
		vcard.setSecurityClass(new ClassType("Public"));
		vcard.setProductId(new ProductIdType("31e78c0d-fb07-479d-b6af-95a9a3f2916f"));
		vcard.setSortString(new SortStringType("JOHN"));
		
		vcard.setMailer(new MailerType("Mozilla Thunderbird"));
		vcard.setTitle(new TitleType("Generic Accountant"));
		vcard.setRole(new RoleType("Counting Money"));
		
		OrganizationFeature organizations = new OrganizationType();
		organizations.addOrganization("IBM");
		organizations.addOrganization("SUN");
		vcard.setOrganizations(organizations);
		
		vcard.setUID(new UIDType("c0ff639f-9633-4e57-bcfd-55079cfd9d65"));
		vcard.addURL(new URLType(new URL("http://www.sun.com")));
		vcard.setGeographicPosition(new GeographicPositionType(3.4f, -2.6f));

		Calendar birthday = Calendar.getInstance();
		birthday.clear();
		birthday.set(Calendar.YEAR, 1980);
		birthday.set(Calendar.MONTH, 4);
		birthday.set(Calendar.DAY_OF_MONTH, 21);
		vcard.setBirthday(new BirthdayType(birthday));
		
		Calendar revCal = Calendar.getInstance();
		revCal.clear();
		revCal.set(Calendar.YEAR, 2011);
		revCal.set(Calendar.MONTH, 11);
		revCal.set(Calendar.DAY_OF_MONTH, 22);
		revCal.set(Calendar.HOUR_OF_DAY, 16);
		revCal.set(Calendar.MINUTE, 30);
		revCal.set(Calendar.SECOND, 05);
		revCal.setTimeZone(TimeZone.getTimeZone("Asia/Beirut"));
		
		vcard.setRevision(new RevisionType(revCal));
		vcard.setTimeZone(new TimeZoneType(revCal.getTimeZone()));
		
		AddressFeature address1 = new AddressType();
		address1.setExtendedAddress("");
		address1.setCountryName("U.S.A.");
		address1.setLocality("New York");
		address1.setRegion("New York");
		address1.setPostalCode("NYC887");
		address1.setPostOfficeBox("25334");
		address1.setStreetAddress("South cresent drive, Building 5, 3rd floor");
		address1.addAddressParameterType(AddressParameterType.HOME);
		address1.addAddressParameterType(AddressParameterType.PARCEL);
		address1.addAddressParameterType(AddressParameterType.PREF);
		vcard.addAddress(address1);

		LabelFeature labelForAddress1 = new LabelType();
		labelForAddress1.addLabelParameterType(LabelParameterType.HOME);
		labelForAddress1.addLabelParameterType(LabelParameterType.PARCEL);
		labelForAddress1.addLabelParameterType(LabelParameterType.PREF);
		labelForAddress1.setLabel("John Doe\nNew York, NewYork,\nSouth Crecent Drive,\nBuilding 5, floor 3,\nUSA");
		vcard.setLabel(labelForAddress1, address1);
		
		TelephoneFeature telephone = new TelephoneType();
		telephone.setTelephone("+1 (212) 204-34456");
		telephone.addTelephoneParameterType(TelephoneParameterType.CELL);
		telephone.addTelephoneParameterType(TelephoneParameterType.HOME);
		telephone.setParameterTypeStyle(ParameterTypeStyle.PARAMETER_VALUE_LIST);
		vcard.addTelephoneNumber(telephone);
		
		TelephoneFeature telephone2 = new TelephoneType();
		telephone2.setTelephone("00-1-212-555-7777");
		telephone2.addTelephoneParameterType(TelephoneParameterType.FAX);
		telephone2.addTelephoneParameterType(TelephoneParameterType.WORK);
		telephone2.setParameterTypeStyle(ParameterTypeStyle.PARAMETER_LIST);
		vcard.addTelephoneNumber(telephone2);
		
		EmailFeature email = new EmailType();
		email.setEmail("john.doe@ibm.com");
		email.addEmailParameterType(EmailParameterType.IBMMAIL);
		email.addEmailParameterType(EmailParameterType.INTERNET);
		email.addEmailParameterType(EmailParameterType.PREF);
		vcard.addEmail(email);
		vcard.addEmail(new EmailType("billy_bob@gmail.com"));
		
		NoteFeature note = new NoteType();
		note.setNote("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\"\nAND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE\nIMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE\nARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE\nLIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR\nCONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF\nSUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS\nINTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN\nCONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)\nARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE\nPOSSIBILITY OF SUCH DAMAGE.");
		vcard.addNote(note);
		
		PhotoFeature photo1 = new PhotoType();
		photo1.setCompression(true);
		photo1.setEncodingType(EncodingType.BINARY);
		photo1.setImageMediaType(ImageMediaType.PNG);
		byte[] tuxPicture1 = Util.getFileAsBytes(new File("test/images/smallTux.png"));
		photo1.setPhoto(tuxPicture1);
		vcard.addPhoto(photo1);
		
		LogoFeature logo = new LogoType();
		logo.setCompression(false);
		logo.setEncodingType(EncodingType.BINARY);
		logo.setImageMediaType(ImageMediaType.PNG);
		byte[] tuxPicture2 = Util.getFileAsBytes(new File("test/images/smallTux.png"));
		logo.setLogo(tuxPicture2);
		vcard.addLogo(logo);
		
		SoundFeature sound = new SoundType();
		sound.setCompression(true);
		sound.setEncodingType(EncodingType.BINARY);
		sound.setAudioMediaType(AudioMediaType.OGG);
		sound.setSoundURI(new File("test/images/smallTux.png").toURI());
		vcard.addSound(sound);
		
		vcard.addExtendedType(new ExtendedType("X-GENERATOR", "Cardme Generator"));
		vcard.addExtendedType(new ExtendedType("X-LONG-STRING", "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"));
		
		((VCardErrorHandling)vcard).setThrowExceptions(false);
		
		return vcard;
	}
}