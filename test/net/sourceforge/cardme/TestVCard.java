package net.sourceforge.cardme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Calendar;
import net.sourceforge.cardme.engine.VCardEngine;
import net.sourceforge.cardme.io.BinaryFoldingScheme;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.io.FoldingScheme;
import net.sourceforge.cardme.io.VCardWriter;
import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.arch.EncodingType;
import net.sourceforge.cardme.vcard.arch.LanguageType;
import net.sourceforge.cardme.vcard.arch.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.arch.VCardVersion;
import net.sourceforge.cardme.vcard.errors.VCardErrorHandler;
import net.sourceforge.cardme.vcard.types.AdrType;
import net.sourceforge.cardme.vcard.types.BDayType;
import net.sourceforge.cardme.vcard.types.CategoriesType;
import net.sourceforge.cardme.vcard.types.ClassType;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.ExtendedType;
import net.sourceforge.cardme.vcard.types.FNType;
import net.sourceforge.cardme.vcard.types.GeoType;
import net.sourceforge.cardme.vcard.types.ImppType;
import net.sourceforge.cardme.vcard.types.KeyType;
import net.sourceforge.cardme.vcard.types.LabelType;
import net.sourceforge.cardme.vcard.types.LogoType;
import net.sourceforge.cardme.vcard.types.MailerType;
import net.sourceforge.cardme.vcard.types.NType;
import net.sourceforge.cardme.vcard.types.NameType;
import net.sourceforge.cardme.vcard.types.NicknameType;
import net.sourceforge.cardme.vcard.types.NoteType;
import net.sourceforge.cardme.vcard.types.OrgType;
import net.sourceforge.cardme.vcard.types.PhotoType;
import net.sourceforge.cardme.vcard.types.ProdIdType;
import net.sourceforge.cardme.vcard.types.ProfileType;
import net.sourceforge.cardme.vcard.types.RevType;
import net.sourceforge.cardme.vcard.types.RoleType;
import net.sourceforge.cardme.vcard.types.SortStringType;
import net.sourceforge.cardme.vcard.types.SoundType;
import net.sourceforge.cardme.vcard.types.SourceType;
import net.sourceforge.cardme.vcard.types.TelType;
import net.sourceforge.cardme.vcard.types.TitleType;
import net.sourceforge.cardme.vcard.types.TzType;
import net.sourceforge.cardme.vcard.types.UidType;
import net.sourceforge.cardme.vcard.types.UrlType;
import net.sourceforge.cardme.vcard.types.VersionType;
import net.sourceforge.cardme.vcard.types.media.AudioMediaType;
import net.sourceforge.cardme.vcard.types.media.ImageMediaType;
import net.sourceforge.cardme.vcard.types.media.KeyTextType;
import net.sourceforge.cardme.vcard.types.params.AdrParamType;
import net.sourceforge.cardme.vcard.types.params.EmailParamType;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.ImppParamType;
import net.sourceforge.cardme.vcard.types.params.LabelParamType;
import net.sourceforge.cardme.vcard.types.params.TelParamType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
 * Feb 10, 2010
 */
public class TestVCard {

	private static VCardImpl vcard = null;
	private static VCardImpl vcardFull = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vcard = new VCardImpl();
		vcardFull = getFullVCardNoErrors();
	}
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		vcard = null;
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetBeginNullPointer() {
		vcard.setBegin(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetEndNullPointer() {
		vcard.setEnd(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetFNNullPointer() {
		vcard.setFN(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetNNullPointer() {
		vcard.setN(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddPhotoNullPointer() {
		vcard.addPhoto(null);
	}
	
	@Test
	public void testRemovePhotoNullPointer() {
		assertFalse(vcard.removePhoto(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllPhotosNullPointer() {
		vcard.addAllPhotos(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAdrNullPointer() {
		vcard.addAdr(null);
	}
	
	@Test
	public void testRemoveAdrNullPointer() {
		assertFalse(vcard.removeAdr(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllAdrsNullPointer() {
		vcard.addAllAdrs(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddTelNullPointer() {
		vcard.addTel(null);
	}
	
	@Test
	public void testRemoveTelNullPointer() {
		assertFalse(vcard.removeTel(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllTelsNullPointer() {
		vcard.addAllTels(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddEmailNullPointer() {
		vcard.addEmail(null);
	}
	
	@Test
	public void testRemoveEmailNullPointer() {
		assertFalse(vcard.removeEmail(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllEmailsNullPointer() {
		vcard.addAllEmails(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddLogoNullPointer() {
		vcard.addLogo(null);
	}
	
	@Test
	public void testRemoveLogoNullPointer() {
		assertFalse(vcard.removeLogo(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllLogosNullPointer() {
		vcard.addAllLogos(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAgentNullPointer() {
		vcard.addAgent(null);
	}
	
	@Test
	public void testRemoveAgentNullPointer() {
		assertFalse(vcard.removeAgent(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllAgentsNullPointer() {
		vcard.addAllAgents(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddNoteNullPointer() {
		vcard.addNote(null);
	}
	
	@Test
	public void testRemoveNoteNullPointer() {
		assertFalse(vcard.removeNote(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllNotesNullPointer() {
		vcard.addAllNotes(null);
	}

	@Test(expected=NullPointerException.class)
	public void testAddSoundNullPointer() {
		vcard.addSound(null);
	}
	
	@Test
	public void testRemoveSoundNullPointer() {
		assertFalse(vcard.removeSound(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllSoundsNullPointer() {
		vcard.addAllSounds(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddUrlNullPointer() {
		vcard.addUrl(null);
	}
	
	@Test
	public void testRemoveUrlNullPointer() {
		assertFalse(vcard.removeUrl(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllUrlsNullPointer() {
		vcard.addAllUrls(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetVersionNullPointer() {
		vcard.setVersion(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddKeyNullPointer() {
		vcard.addKey(null);
	}
	
	@Test
	public void testRemoveKeyNullPointer() {
		assertFalse(vcard.removeKey(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllKeysNullPointer() {
		vcard.addAllKeys(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddExtendedTypeNullPointer() {
		vcard.addExtendedType(null);
	}
	
	@Test
	public void testRemoveExtendedTypeNullPointer() {
		assertFalse(vcard.removeExtendedType(null));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllExtendedTypesNullPointer() {
		vcard.addAllExtendedTypes(null);
	}
	
	@Test
	public void testEquals() throws Exception {
		VCard vcardCopy = getFullVCardNoErrors();
		assertEquals(vcardCopy, vcardFull);
	}
	
	@Test
	public void testHashcode() throws Exception {
		VCard vcardCopy = getFullVCardNoErrors();
		
		int h1 = vcardFull.hashCode();
		int h2 = vcardCopy.hashCode();
		
		assertEquals(h1, h2);
	}
	
	@Test
	public void testClone() {
		VCard cloned = vcardFull.clone();
		assertEquals(vcardFull, cloned);
	}
	
	@Test
	public void testWriteVCardAndRead() throws Exception {
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setBinaryfoldingScheme(BinaryFoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcardFull);
		
		String vcardString = vcardWriter.buildVCardString();
		
		assertNotNull(vcardString);
		assertFalse(vcardWriter.hasErrors());
		
		VCardEngine vcardEngine = new VCardEngine();
		vcardEngine.setCompatibilityMode(CompatibilityMode.RFC2426);
		VCard _vcard = vcardEngine.parse(vcardString);
		
		assertNotNull(_vcard);
		assertFalse(((VCardErrorHandler)_vcard).hasErrors());
		
		//TODO the below tests fail.
		//
		// This means that if we write a vcard to disk and then
		// read it back in again it is no longer the "same" vcard
		// event though its contents have not changed and the equals
		// method just checks the contents.
		// 
		//assertEquals(vcardFull, _vcard);
		//assertEquals(vcardFull.hashCode(), _vcard.hashCode());
	}
	
	@Test
	public void testQuotedPrintableName() throws Exception {
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setVCard(vcardFull);
		
		String vcardString = vcardWriter.buildVCardString();
		
		assertNotNull(vcardString);
		assertFalse(vcardWriter.hasErrors());
		assertTrue("Got " + vcardString, (vcardString.indexOf("D=C3=96e") != -1));
		
		VCardEngine vcardEngine = new VCardEngine();
		vcardEngine.setCompatibilityMode(CompatibilityMode.RFC2426);
		VCard _vcard = vcardEngine.parse(vcardString);
		
		assertEquals("DÖe", _vcard.getN().getFamilyName());
	}
	
	private static VCardImpl getFullVCardNoErrors() throws IOException, URISyntaxException
	{
		VCardImpl vcard = new VCardImpl();
		vcard.setVersion(new VersionType(VCardVersion.V3_0));
		
		NameType name = new NameType();
		name.setName("VCard for John Doe");
		vcard.setName(name);
		
		ProfileType profile = new ProfileType();
		profile.setProfile("VCard");
		vcard.setProfile(profile);
		
		SourceType source = new SourceType();
		source.setSource("Whatever");
		vcard.setSource(source);
		
		NType n = new NType();
		n.setEncodingType(EncodingType.QUOTED_PRINTABLE);
		n.setCharset(Charset.forName("UTF-8"));
		n.setLanguage(LanguageType.EN);
		n.setFamilyName("DÖe");
//		n.setFamilyName("Doe");
		n.setGivenName("John");
		n.addHonorificPrefix("Mr.");
		n.addHonorificSuffix("I");
		n.addAdditionalName("Johny");
		vcard.setN(n);
		
		FNType fn = new FNType();
		fn.setFormattedName("John \"Johny\" Doe");
		fn.setCharset(Charset.forName("UTF-8"));
		fn.setLanguage(LanguageType.EN);
		vcard.setFN(fn);
		
		NicknameType nicknames = new NicknameType();
		nicknames.addNickname("Johny");
		nicknames.addNickname("JayJay");
		vcard.setNickname(nicknames);
		
		CategoriesType categories = new CategoriesType();
		categories.addCategory("Category 1");
		categories.addCategory("Category 2");
		categories.addCategory("Category 3");
		vcard.setCategories(categories);
		
		vcard.setSecurityClass(new ClassType("Public"));
		vcard.setProdId(new ProdIdType("31e78c0d-fb07-479d-b6af-95a9a3f2916f"));
		vcard.setSortString(new SortStringType("JOHN"));
		
		vcard.setMailer(new MailerType("Mozilla Thunderbird"));
		vcard.setTitle(new TitleType("Generic Accountant"));
		vcard.setRole(new RoleType("Counting Money"));
		
		OrgType organizations = new OrgType();
		organizations.setOrgName("IBM");
		organizations.addOrgUnit("SUN");
		vcard.setOrg(organizations);
		
		vcard.setUid(new UidType("c0ff639f-9633-4e57-bcfd-55079cfd9d65"));
		vcard.addUrl(new UrlType(new URL("http://www.sun.com")));
		vcard.setGeo(new GeoType(3.4f, -2.6f));

		Calendar birthday = Calendar.getInstance();
		birthday.clear();
		birthday.set(Calendar.YEAR, 1980);
		birthday.set(Calendar.MONTH, 4);
		birthday.set(Calendar.DAY_OF_MONTH, 21);
		vcard.setBDay(new BDayType(birthday));

		Calendar revision = Calendar.getInstance();
		revision.clear();
		revision.set(Calendar.YEAR, 2012);
		revision.set(Calendar.MONTH, 6);
		revision.set(Calendar.DAY_OF_MONTH, 8);
		vcard.setRev(new RevType(revision));
		
		vcard.setTz(new TzType(Calendar.getInstance().getTimeZone()));
		
		AdrType address1 = new AdrType();
		address1.setCharset("UTF-8");
		address1.setExtendedAddress("");
		address1.setCountryName("U.S.A.");
		address1.setLocality("New York");
		address1.setRegion("New York");
		address1.setPostalCode("NYC887");
		address1.setPostOfficeBox("25334");
		address1.setStreetAddress("South cresent drive, Building 5, 3rd floor");
		address1.addParam(AdrParamType.HOME)
		.addParam(AdrParamType.PARCEL)
		.addParam(AdrParamType.PREF)
		.addExtendedParam(new ExtendedParamType("CUSTOM-PARAM-TYPE", VCardTypeName.ADR))
		.addExtendedParam(new ExtendedParamType("CUSTOM-PARAM-TYPE", "WITH-CUSTOM-VALUE", VCardTypeName.ADR));
		

		LabelType labelForAddress1 = new LabelType();
		labelForAddress1.setCharset("UTF-8");
		labelForAddress1.addParam(LabelParamType.HOME)
		.addParam(LabelParamType.PARCEL)
		.addParam(LabelParamType.PREF)
		.setLabel("John Doe\nNew York, NewYork,\nSouth Crecent Drive,\nBuilding 5, floor 3,\nUSA");
		
		address1.setLabel(labelForAddress1);
		vcard.addAdr(address1);
		
		TelType telephone = new TelType();
		telephone.setCharset("UTF-8");
		telephone.setTelephone("+1 (212) 204-34456");
		telephone.addParam(TelParamType.CELL)
		.addParam(TelParamType.HOME)
		.setParameterTypeStyle(ParameterTypeStyle.PARAMETER_VALUE_LIST);
		vcard.addTel(telephone);
		
		TelType telephone2 = new TelType();
		telephone2.setTelephone("00-1-212-555-7777");
		telephone2.addParam(TelParamType.FAX)
		.addParam(TelParamType.WORK)
		.setParameterTypeStyle(ParameterTypeStyle.PARAMETER_LIST);
		vcard.addTel(telephone2);
		
		EmailType email = new EmailType();
		email.setEmail("john.doe@ibm.com");
		email.addParam(EmailParamType.IBMMAIL)
		.addParam(EmailParamType.INTERNET)
		.addParam(EmailParamType.PREF)
		.setCharset("UTF-8");
		vcard.addEmail(email);
		vcard.addEmail(new EmailType("billy_bob@gmail.com"));
		
		NoteType note = new NoteType();
		note.setNote("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\"\nAND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE\nIMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE\nARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE\nLIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR\nCONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF\nSUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS\nINTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN\nCONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)\nARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE\nPOSSIBILITY OF SUCH DAMAGE.");
		vcard.addNote(note);
		
		PhotoType photo1 = new PhotoType();
		photo1.setCompression(false);
		photo1.setEncodingType(EncodingType.BINARY);
		photo1.setImageMediaType(ImageMediaType.PNG);
		byte[] tuxPicture1 = Util.getFileAsBytes(new File("test/images/smallTux.png"));
		photo1.setPhoto(tuxPicture1);
		vcard.addPhoto(photo1);
		
		LogoType logo = new LogoType();
		logo.setCompression(true);
		logo.setEncodingType(EncodingType.BINARY);
		logo.setImageMediaType(ImageMediaType.PNG);
		byte[] tuxPicture2 = Util.getFileAsBytes(new File("test/images/smallTux.png"));
		logo.setLogo(tuxPicture2);
		vcard.addLogo(logo);
		
		SoundType sound = new SoundType();
		sound.setCompression(false);
		sound.setEncodingType(EncodingType.BINARY);
		sound.setAudioMediaType(AudioMediaType.OGG);
		sound.setSoundURI(new File("test/images/smallTux.png").toURI());
		vcard.addSound(sound);
		
		KeyType key = new KeyType();
		key.setKeyTextType(KeyTextType.GPG);
		key.setEncodingType(EncodingType.BINARY);
		key.setCompression(false);
		byte[] keyBytes = Util.getFileAsBytes(new File("test/images/smallTux.png"));
		key.setKey(keyBytes);
		vcard.addKey(key);
		
		ExtendedType xGenerator = new ExtendedType("X-GENERATOR", "Cardme Generator");
		xGenerator.setCharset("UTF-8");
		
		vcard.addExtendedType(xGenerator);
		vcard.addExtendedType(new ExtendedType("X-LONG-STRING", "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"));
		
		vcard.addImpp(new ImppType("im:alice@example.com"));
		vcard.addImpp(new ImppType(new URI("im:alice2@example.com")));
		
		ImppType impp = new ImppType();
		impp.setUri(new URI("im:alice3@example.com"));
		impp.addParam(ImppParamType.HOME)
		.addParam(ImppParamType.PREF)
		.addExtendedParam(new ExtendedParamType("X-BLA", "BLE", VCardTypeName.IMPP));
		vcard.addImpp(impp);
		
		((VCardErrorHandler)vcard).setThrowExceptions(false);
		
		return vcard;
	}
	
//	private static VCard getFullVCardAllErrors() throws Exception
//	{
//		VCard vcard = new VCardImpl();
//		vcard.setVersion(new VersionType(VCardVersion.V3_0));
//		
//		NameFeature name = new NameType();
//		name.setFamilyName("Doe");
//		name.setGivenName("John");
//		name.addHonorificPrefix("Mr.");
//		name.addHonorificSuffix("I");
//		name.addAdditionalName("Johny");
//		vcard.setName(name);
//		
//		FormattedNameFeature formattedName = new FormattedNameType();
//		formattedName.setFormattedName(null);
//		vcard.setFormattedName(formattedName);
//		
//		NicknameFeature nicknames = new NicknameType();
//		nicknames.addNickname("Johny");
//		nicknames.addNickname("JayJay");
//		vcard.setNicknames(nicknames);
//		
//		CategoriesFeature categories = new CategoriesType();
//		categories.addCategory("Category 1");
//		categories.addCategory("Category 2");
//		categories.addCategory("Category 3");
//		vcard.setCategories(categories);
//		
//		vcard.setSecurityClass(new ClassType());
//		vcard.setProductId(new ProductIdType());
//		vcard.setSortString(new SortStringType());
//		
//		vcard.setMailer(new MailerType());
//		vcard.setTitle(new TitleType());
//		vcard.setRole(new RoleType());
//		
//		OrganizationFeature organizations = new OrganizationType();
//		organizations.addOrganization("IBM");
//		organizations.addOrganization("SUN");
//		vcard.setOrganizations(organizations);
//		
//		vcard.setUID(new UIDType());
//		vcard.addURL(new URLType());
//		vcard.setGeographicPosition(new GeographicPositionType());
//		vcard.setBirthday(new BirthdayType());
//
//		vcard.setRevision(new RevisionType());
//		vcard.setTimeZone(new TimeZoneType());
//		
//		AddressFeature address1 = new AddressType();
//		vcard.addAddress(address1);
//
//		LabelFeature labelForAddress1 = new LabelType();
//		vcard.setLabel(labelForAddress1, address1);
//		
//		TelephoneFeature telephone = new TelephoneType();
//		vcard.addTelephoneNumber(telephone);
//		
//		TelephoneFeature telephone2 = new TelephoneType();
//		vcard.addTelephoneNumber(telephone2);
//		
//		EmailFeature email = new EmailType();
//		vcard.addEmail(email);
//		
//		NoteFeature note = new NoteType();
//		vcard.addNote(note);
//		
//		PhotoFeature photo1 = new PhotoType();
//		vcard.addPhoto(photo1);
//		
//		LogoFeature logo = new LogoType();
//		vcard.addLogo(logo);
//		
//		SoundFeature sound = new SoundType();
//		vcard.addSound(sound);
//		vcard.addExtendedType(new ExtendedType("X-MISC", "Something"));
//		
//		((VCardErrorHandling)vcard).setThrowExceptions(false);
//		
//		return vcard;
//	}
	
//	/**
//	 * test vcard interfaces
//	 * @throws Exception 
//	 */
//	@Test
//	public void testVcard() throws Exception {
//		
//		// Create VCard Writer
//		VCardWriter vcardWriter = new VCardWriter();
//		
//		// Set VCard Writer Parameters
//		vcardWriter.setOutputVersion(VCardVersion.V3_0);
//		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
//		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
//		
//		// Get a VCard
//		VCard fullNoErrorVCard = getFullVCardNoErrors();
//		VCard fullAllErrorVCard = getFullVCardAllErrors();
//		
//		// Set it on the Writer.
//		vcardWriter.setVCard(fullNoErrorVCard);
//		
//		// Write the VCard
//		String vcardString = vcardWriter.buildVCardString();
//		
//		System.out.println("Full VCard No Error");
//		System.out.println("----------------");
//		System.out.println(vcardString);
//		if(vcardWriter.hasErrors()) {
//			System.out.println("Errors\n----------------");
//			List<VCardError> errors = ((VCardErrorHandling)vcardWriter.getVCard()).getErrors();
//			for(int i = 0; i < errors.size(); i++) {
//				System.out.println(errors.get(i).getErrorMessage());
//			}
//		}
//		
//		System.out.println("----------------");
//		
//		
//		
//		// Write the VCard
//		vcardWriter.setVCard(fullAllErrorVCard);
//		vcardString = vcardWriter.buildVCardString();
//		
//		System.out.println("Full VCard With Errors");
//		System.out.println("----------------");
//		System.out.println(vcardString);
//		if(vcardWriter.hasErrors()) {
//			System.out.println("Errors\n----------------");
//			List<VCardError> errors = ((VCardErrorHandling)vcardWriter.getVCard()).getErrors();
//			for(int i = 0; i < errors.size(); i++) {
//				System.out.println(StringUtil.formatException(errors.get(i).getError()));
//			}
//		}
//		
//		System.out.println("----------------");
//	}
}
