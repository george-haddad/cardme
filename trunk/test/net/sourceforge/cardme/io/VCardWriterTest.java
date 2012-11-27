package net.sourceforge.cardme.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.TimeZone;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.arch.EncodingType;
import net.sourceforge.cardme.vcard.arch.LanguageType;
import net.sourceforge.cardme.vcard.arch.VCardVersion;
import net.sourceforge.cardme.vcard.exceptions.VCardBuildException;
import net.sourceforge.cardme.vcard.types.AdrType;
import net.sourceforge.cardme.vcard.types.BDayType;
import net.sourceforge.cardme.vcard.types.FNType;
import net.sourceforge.cardme.vcard.types.GeoType;
import net.sourceforge.cardme.vcard.types.KeyType;
import net.sourceforge.cardme.vcard.types.MailerType;
import net.sourceforge.cardme.vcard.types.NType;
import net.sourceforge.cardme.vcard.types.NameType;
import net.sourceforge.cardme.vcard.types.NoteType;
import net.sourceforge.cardme.vcard.types.OrgType;
import net.sourceforge.cardme.vcard.types.ProfileType;
import net.sourceforge.cardme.vcard.types.RevType;
import net.sourceforge.cardme.vcard.types.RoleType;
import net.sourceforge.cardme.vcard.types.SourceType;
import net.sourceforge.cardme.vcard.types.TitleType;
import net.sourceforge.cardme.vcard.types.TzType;
import net.sourceforge.cardme.vcard.types.UidType;
import net.sourceforge.cardme.vcard.types.UrlType;
import net.sourceforge.cardme.vcard.types.VersionType;
import net.sourceforge.cardme.vcard.types.media.KeyTextType;
import net.sourceforge.cardme.vcard.types.params.AdrParamType;
import net.sourceforge.cardme.vcard.types.params.BDayParamType;
import net.sourceforge.cardme.vcard.types.params.TzParamType;
import org.apache.commons.codec.net.QuotedPrintableCodec;
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
 * Oct 22, 2011
 *
 */
public class VCardWriterTest {

	@Test
	public void testBuildBeginType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("BEGIN:VCARD\r\n"));
	}
	
	@Test
	public void testBuildEndType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("END:VCARD\r\n"));
	}
	
	@Test
	public void testBuildVersionType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		vcard.setVersion(new VersionType(VCardVersion.V3_0));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("VERSION:3.0\r\n"));
	}
	
	@Test
	public void testBuildNType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		NType n = new NType();
		n.setFamilyName("Doe");
		n.setGivenName("John");
		n.addAdditionalName("Johny");
		n.addHonorificPrefix("Mr.");
		n.addHonorificSuffix("I");
		vcard.setN(n);
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("N:Doe;John;Johny;Mr.;I\r\n"));
	}
	
	@Test
	public void testBuildFNType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		FNType fn = new FNType("John \"Johny\" Doe");
		fn.setLanguage(LanguageType.EN);
		vcard.setFN(fn);
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("FN;LANGUAGE=en:John \"Johny\" Doe\r\n"));
	}
	
	@Test
	public void testBuildNameType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		vcard.setName(new NameType("VCard for John Doe"));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("NAME:VCard for John Doe\r\n"));
	}

	@Test
	public void testBuildProfileType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		vcard.setProfile(new ProfileType("VCard"));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("PROFILE:VCard\r\n"));
	}
	
	@Test
	public void testBuildSourceType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		vcard.setSource(new SourceType("Whatever"));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("SOURCE:Whatever\r\n"));
	}
	
	@Test
	public void testBuildTitleType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		vcard.setTitle(new TitleType("Generic Accountant"));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("TITLE:Generic Accountant\r\n"));
	}
	
	@Test
	public void testBuildRoleType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		vcard.setRole(new RoleType("Counting Money"));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("ROLE:Counting Money\r\n"));
	}
	
	@Test
	public void testBuildGeoType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		vcard.setGeo(new GeoType(-2.6d, 3.4d));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("GEO:3.400000;-2.600000\r\n"));
	}
	
	@Test
	public void testBuildOrgType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		vcard.setOrg(new OrgType().setOrgName("IBM").addOrgUnit("Dev"));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("ORG:IBM;Dev\r\n"));
	}
	
	@Test
	public void testBuildMailerFeature() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		vcard.setMailer(new MailerType("Mozilla Thunderbird"));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("MAILER:Mozilla Thunderbird\r\n"));
	}
	
	@Test
	public void testBuildTzTypeUTC_OFFSET() throws VCardBuildException {
		TzType tz = new TzType(TimeZone.getTimeZone("Asia/Beirut"));
		tz.setParamType(TzParamType.UTC_OFFSET);
		
		VCardImpl vcard = getSimpleVCard();
		vcard.setTz(tz);
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("TZ:+02:00\r\n"));
	}
	
	@Test
	public void testBuildTzType_TEXT() throws VCardBuildException {
		TzType tz = new TzType(TimeZone.getTimeZone("Asia/Beirut"));
		tz.setParamType(TzParamType.TEXT);
		
		VCardImpl vcard = getSimpleVCard();
		vcard.setTz(tz);
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("TZ;VALUE=TEXT:+02:00;;Eastern European Time\r\n"));
	}
	
	@Test
	public void testBuildUrlType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		vcard.addUrl(new UrlType("http://www.sun.com"));
		vcard.addUrl(new UrlType("this is free form text."));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		assertTrue(vcardStr.contains("URL:http://www.sun.com\r\n"));
		assertTrue(vcardStr.contains("URL:this is free form text."));
	}
	
	@Test
	public void testBuildRevType() throws VCardBuildException {
		Calendar rev = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		rev.clear();
		rev.set(Calendar.YEAR, 2011);
		rev.set(Calendar.MONTH, 11);
		rev.set(Calendar.DAY_OF_MONTH, 22);
		rev.set(Calendar.HOUR_OF_DAY, 14);
		rev.set(Calendar.MINUTE, 30);
		rev.set(Calendar.SECOND, 5);
		
		VCardImpl vcard = getSimpleVCard();
		vcard.setRev(new RevType(rev));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("REV:2011-12-22T14:30:05Z\r\n"));
	}
	
	@Test
	public void testBuildUidType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		vcard.setUid(new UidType("c0ff639f-9633-4e57-bcfd-55079cfd9d65"));
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("UID:c0ff639f-9633-4e57-bcfd-55079cfd9d65\r\n"));
	}
	
	@Test
	public void testBuildBDayType() throws VCardBuildException {
		Calendar bday = Calendar.getInstance();
		bday.set(Calendar.YEAR, 1980);
		bday.set(Calendar.MONTH, 4);
		bday.set(Calendar.DAY_OF_MONTH, 21);
		
		VCardImpl vcard = getSimpleVCard();
		BDayType bdayType = new BDayType(bday);
		bdayType.setParam(BDayParamType.DATE);
		vcard.setBDay(bdayType);
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("BDAY;VALUE=DATE:1980-05-21\r\n"));
	}
	
	@Test
	public void testBuildAdrType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		AdrType adr = new AdrType();
		adr.setPostOfficeBox("25334");
		adr.setStreetAddress("South cresent drive, Building 5, 3rd floor");
		adr.setLocality("New York");
		adr.setRegion("New York");
		adr.setPostalCode("NYC887");
		adr.setCountryName("U.S.A.");
		adr.addParam(AdrParamType.HOME).addParam(AdrParamType.PARCEL).addParam(AdrParamType.PREF);
		vcard.addAdr(adr);
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("ADR;TYPE=HOME,PARCEL,PREF:25334;;South cresent drive\\, Building 5\\, 3rd flo\r\n or;New York;New York;NYC887;U.S.A.\r\n"));
	}
	
	@Test
	public void testBuildKeyType() throws VCardBuildException {
		VCardImpl vcard = getSimpleVCard();
		
		KeyType keyPlain = new KeyType();
		keyPlain.setKeyTextType(KeyTextType.PGP);
		keyPlain.setKey("plain text key");
		vcard.addKey(keyPlain);
		
		KeyType keyBin = new KeyType();
		keyBin.setKeyTextType(KeyTextType.X509);
		keyBin.setEncodingType(EncodingType.BINARY);
		keyBin.setKey("binary data".getBytes());
		vcard.addKey(keyBin);
		
		VCardWriter vcardWriter = new VCardWriter();
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setVCard(vcard);
		
		String vcardStr = vcardWriter.buildVCardString();
		
		assertTrue(vcardStr.contains("KEY;TYPE=PGP:plain text key\r\n"));
		assertTrue(vcardStr.contains("KEY;ENCODING=B;TYPE=X509:YmluYXJ5IGRhdGE=\r\n"));
	}
	
	private VCardImpl getSimpleVCard()
	{
		VCardImpl vcard = new VCardImpl();
		vcard.setVersion(new VersionType(VCardVersion.V3_0));
		vcard.setN(new NType("Doe", "John"));
		vcard.setFN(new FNType("John \"Johny\" Doe"));
		return vcard;
	}
	
	@Test
	public void testQuotedPrintableEncoding_1() throws UnsupportedEncodingException {
		String text = "This is a string with no special characters.";
		QuotedPrintableCodec QPC = new QuotedPrintableCodec();
		String encodedText = QPC.encode(text, "UTF8");
		assertEquals(text, encodedText);
	}
	
	@Test
	public void testQuotedPrintableEncoding_2() throws UnsupportedEncodingException {
		String text = "This is a string with special characters 新中西里杨阿姨.";
		String expected = "This is a string with special characters =E6=96=B0=E4=B8=AD=E8=A5=BF=E9=87=8C=E6=9D=A8=E9=98=BF=E5=A7=A8.";
		
		QuotedPrintableCodec QPC = new QuotedPrintableCodec();
		String encodedText = QPC.encode(text, "UTF8");
		assertEquals(expected, encodedText);
	}
	
	@Test
	public void testForceEncodeQuotedPrintableSpaces_1() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		vcard.setVersion(new VersionType(VCardVersion.V3_0));
		vcard.setN(new NType("Doe", "John"));
		vcard.setFN(new FNType("John Doe"));
		
		NoteType note = new NoteType("This is a note with normal text.");
		note.setEncodingType(EncodingType.QUOTED_PRINTABLE);
		vcard.addNote(note);
		
		VCardWriter vcardWriter = new VCardWriter(); 
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setForceEncodeQuotedPrintableSpaces(true);
		vcardWriter.setVCard(vcard);
		String resultVCard = vcardWriter.buildVCardString();
		
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\r\n");
		sb.append("VERSION:3.0\r\n");
		sb.append("N:Doe;John;;;\r\n");
		sb.append("FN:John Doe\r\n");
		sb.append("NOTE;ENCODING=QUOTED-PRINTABLE:This=20is=20a=20note=20with=20normal=20text.\r\n");
		sb.append("END:VCARD\r\n");
		String expectedVCard = sb.toString();
		
		assertEquals(expectedVCard, resultVCard);
	}
	
	@Test
	public void testForceEncodeQuotedPrintableSpaces_2() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		vcard.setVersion(new VersionType(VCardVersion.V3_0));
		vcard.setN(new NType("Doe", "John"));
		vcard.setFN(new FNType("John Doe"));
		
		NoteType note = new NoteType("This is a note with normal text.");
		note.setEncodingType(EncodingType.QUOTED_PRINTABLE);
		vcard.addNote(note);
		
		VCardWriter vcardWriter = new VCardWriter(); 
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setForceEncodeQuotedPrintableSpaces(false);
		vcardWriter.setVCard(vcard);
		String resultVCard = vcardWriter.buildVCardString();
		
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\r\n");
		sb.append("VERSION:3.0\r\n");
		sb.append("N:Doe;John;;;\r\n");
		sb.append("FN:John Doe\r\n");
		sb.append("NOTE;ENCODING=QUOTED-PRINTABLE:This is a note with normal text.\r\n");
		sb.append("END:VCARD\r\n");
		String expectedVCard = sb.toString();
		
		assertEquals(expectedVCard, resultVCard);
	}
	
	@Test
	public void testForceEncodeQuotedPrintableSpaces_3() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		vcard.setVersion(new VersionType(VCardVersion.V3_0));
		vcard.setN(new NType("Doe", "John"));
		vcard.setFN(new FNType("John Doe"));
		
		NoteType note = new NoteType("新中西里杨阿姨 some spaces ");
		note.setEncodingType(EncodingType.QUOTED_PRINTABLE);
		vcard.addNote(note);
		
		VCardWriter vcardWriter = new VCardWriter(); 
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setForceEncodeQuotedPrintableSpaces(false);
		vcardWriter.setVCard(vcard);
		String resultVCard = vcardWriter.buildVCardString();
		
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\r\n");
		sb.append("VERSION:3.0\r\n");
		sb.append("N:Doe;John;;;\r\n");
		sb.append("FN:John Doe\r\n");
		sb.append("NOTE;ENCODING=QUOTED-PRINTABLE:=E6=96=B0=E4=B8=AD=E8=A5=BF=E9=87=8C=E6=9D==\r\n");
		sb.append(" A8=E9=98=BF=E5=A7=A8 some spaces \r\n");
		sb.append("END:VCARD\r\n");
		String expectedVCard = sb.toString();
		
		assertEquals(expectedVCard, resultVCard);
	}
	
	@Test
	public void testForceEncodeQuotedPrintableSpaces_4() throws VCardBuildException {
		VCardImpl vcard = new VCardImpl();
		vcard.setVersion(new VersionType(VCardVersion.V3_0));
		vcard.setN(new NType("Doe", "John"));
		vcard.setFN(new FNType("John Doe"));
		
		NoteType note = new NoteType("新中西里杨阿姨 some spaces ");
		note.setEncodingType(EncodingType.QUOTED_PRINTABLE);
		vcard.addNote(note);
		
		VCardWriter vcardWriter = new VCardWriter(); 
		vcardWriter.setOutputVersion(VCardVersion.V3_0);
		vcardWriter.setCompatibilityMode(CompatibilityMode.RFC2426);
		vcardWriter.setFoldingScheme(FoldingScheme.MIME_DIR);
		vcardWriter.setForceEncodeQuotedPrintableSpaces(true);
		vcardWriter.setVCard(vcard);
		String resultVCard = vcardWriter.buildVCardString();
		
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\r\n");
		sb.append("VERSION:3.0\r\n");
		sb.append("N:Doe;John;;;\r\n");
		sb.append("FN:John Doe\r\n");
		sb.append("NOTE;ENCODING=QUOTED-PRINTABLE:=E6=96=B0=E4=B8=AD=E8=A5=BF=E9=87=8C=E6=9D==\r\n");
		sb.append(" A8=E9=98=BF=E5=A7=A8=20some=20spaces=20\r\n");
		sb.append("END:VCARD\r\n");
		String expectedVCard = sb.toString();
		
		assertEquals(expectedVCard, resultVCard);
	}
	
	//TODO a lot more to code
	//Also incorporate CHARSET
}
