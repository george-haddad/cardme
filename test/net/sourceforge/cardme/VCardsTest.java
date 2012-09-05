package net.sourceforge.cardme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import net.sourceforge.cardme.engine.VCardEngine;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.util.ISOFormat;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.arch.EncodingType;
import net.sourceforge.cardme.vcard.arch.LanguageType;
import net.sourceforge.cardme.vcard.arch.VCardVersion;
import net.sourceforge.cardme.vcard.errors.VCardError;
import net.sourceforge.cardme.vcard.exceptions.VCardParseException;
import net.sourceforge.cardme.vcard.features.MailerFeature;
import net.sourceforge.cardme.vcard.features.NoteFeature;
import net.sourceforge.cardme.vcard.features.RoleFeature;
import net.sourceforge.cardme.vcard.features.SortStringFeature;
import net.sourceforge.cardme.vcard.features.SourceFeature;
import net.sourceforge.cardme.vcard.types.AdrType;
import net.sourceforge.cardme.vcard.types.BDayType;
import net.sourceforge.cardme.vcard.types.CategoriesType;
import net.sourceforge.cardme.vcard.types.ClassType;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.ExtendedType;
import net.sourceforge.cardme.vcard.types.FNType;
import net.sourceforge.cardme.vcard.types.GeoType;
import net.sourceforge.cardme.vcard.types.LabelType;
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
import net.sourceforge.cardme.vcard.types.TelType;
import net.sourceforge.cardme.vcard.types.TitleType;
import net.sourceforge.cardme.vcard.types.TzType;
import net.sourceforge.cardme.vcard.types.UidType;
import net.sourceforge.cardme.vcard.types.UrlType;
import net.sourceforge.cardme.vcard.types.media.ImageMediaType;
import net.sourceforge.cardme.vcard.types.params.AdrParamType;
import net.sourceforge.cardme.vcard.types.params.EmailParamType;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.LabelParamType;
import net.sourceforge.cardme.vcard.types.params.TelParamType;
import net.sourceforge.cardme.vcard.types.params.UrlParamType;
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
 * Mar 8, 2012
 *
 */
public class VCardsTest {
	

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testEvolutionVCard() throws IOException, VCardParseException {
		File evoCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_EVOLUTION.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.EVOLUTION);
		VCard vcard = engine.parse(evoCard);
		
		//VERSION
		assertEquals(VCardVersion.V3_0, vcard.getVersion().getVersion());
		
		//URL
		{
			List<UrlType> it = vcard.getUrls();
			assertEquals(1, it.size());
			
			UrlType f = it.get(0);
			assertEquals("http://www.ibm.com", f.getRawUrl().toString());
			
			List<ExtendedParamType> xlist = f.getExtendedParams();
			assertEquals(1, xlist.size());
			assertEquals("X-COUCHDB-UUID", xlist.get(0).getTypeName());
			assertEquals("0abc9b8d-0845-47d0-9a91-3db5bb74620d".toUpperCase(), xlist.get(0).getTypeValue());
		}
		
		//TEL
		{
			List<TelType> it = vcard.getTels();
			assertEquals(2, it.size());
			
			TelType f = it.get(0);
			assertEquals("905-666-1234", f.getTelephone());
			
			List<TelParamType> types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelParamType.CELL));
			
			List<ExtendedParamType> xlist = f.getExtendedParams();
			assertEquals(1, xlist.size());
			assertEquals("X-COUCHDB-UUID", xlist.get(0).getTypeName());
			assertEquals("c2fa1caa-2926-4087-8971-609cfc7354ce".toUpperCase(), xlist.get(0).getTypeValue());
			
			f = it.get(1);
			assertEquals("905-555-1234", f.getTelephone());
			
			types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelParamType.WORK));
			assertTrue(types.contains(TelParamType.VOICE));
			
			xlist = f.getExtendedParams();
			assertEquals(1, xlist.size());
			assertEquals("X-COUCHDB-UUID", xlist.get(0).getTypeName());
			assertEquals("fbfb2722-4fd8-4dbf-9abd-eeb24072fd8e".toUpperCase(), xlist.get(0).getTypeValue());
		}
		
		//UID
		{
			UidType f = vcard.getUid();
			assertEquals("477343c8e6bf375a9bac1f96a5000837", f.getUid());
		}
		
		//N
		{
			NType f = vcard.getN();
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			
			List<String> it = f.getAdditionalNames();
			assertEquals(1, it.size());
			assertEquals("Richter, James", it.get(0));
			
			it = f.getHonorificPrefixes();
			assertEquals(1, it.size());
			assertEquals("Mr.", it.get(0));
			
			it = f.getHonorificSuffixes();
			assertEquals(1, it.size());
			assertEquals("Sr.", it.get(0));
		}
		
		//FN
		{
			FNType f = vcard.getFN();
			assertEquals("Mr. John Richter, James Doe Sr.", f.getFormattedName());
		}
		
		//NICKNAME
		{
			NicknameType f = vcard.getNicknames();
			List<String> it = f.getNicknames();
			assertEquals(1, it.size());
			assertEquals("Johny", it.get(0));
		}
		
		//ORG
		{
			OrgType f = vcard.getOrg();
			assertEquals("IBM", f.getOrgName());
			
			List<String> it = f.getOrgUnits();
			assertEquals(2, it.size());
			assertEquals("Accounting", it.get(0));
			assertEquals("Dungeon", it.get(1));
		}
		
		//TITLE
		{
			TitleType f = vcard.getTitle();
			assertEquals("Money Counter", f.getTitle());
		}
		
		//CATEGORIES
		{
			CategoriesType f = vcard.getCategories();
			List<String> it = f.getCategories();
			assertEquals(1, it.size());
			assertEquals("VIP", it.get(0));
		}
		
		//NOTE
		{
			List<NoteType> it = vcard.getNotes();
			assertEquals(1, it.size());
			NoteFeature f = it.get(0);
			assertEquals("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.", f.getNote());
		}
		
		//EMAIL
		{
			List<EmailType> it = vcard.getEmails();
			assertEquals(1, it.size());
			
			EmailType f = it.get(0);
			assertEquals("john.doe@ibm.com", f.getEmail());
			
			List<EmailParamType> types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(EmailParamType.WORK));
			
			List<ExtendedParamType> xlist = f.getExtendedParams();
			assertEquals(1, xlist.size());
			assertEquals("X-COUCHDB-UUID", xlist.get(0).getTypeName());
			assertEquals("83a75a5d-2777-45aa-bab5-76a4bd972490".toUpperCase(), xlist.get(0).getTypeValue());
		}
		
		//ADR
		{
			List<AdrType> it = vcard.getAdrs();
			assertEquals(1, it.size());
			
			AdrType f = it.get(0);
			assertEquals("ASB-123", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("15 Crescent moon drive", f.getStreetAddress());
			assertEquals("Albaney", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			
			//FIXME the space between "United" and "States" is lost because it was included with the folding character and ignored (see .vcf file)
			assertEquals("UnitedStates of America", f.getCountryName());
			
			List<AdrParamType> types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(AdrParamType.HOME));
		}
		
		//BDAY
		{
			BDayType f = vcard.getBDay();
			assertEquals(1980, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.MARCH, f.getBirthday().get(Calendar.MONTH));
			assertEquals(22, f.getBirthday().get(Calendar.DAY_OF_MONTH));
		}
		
		//REV
		{
			RevType f = vcard.getRev();
			Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			c.clear();
			c.set(Calendar.YEAR, 2012);
			c.set(Calendar.MONTH, Calendar.MARCH);
			c.set(Calendar.DAY_OF_MONTH, 5);
			c.set(Calendar.HOUR_OF_DAY, 13);
			c.set(Calendar.MINUTE, 32);
			c.set(Calendar.SECOND, 54);
			Calendar actual = f.getRevision();
			assertEquals(c.getTime(), actual.getTime());
		}
		
		//custom types
		{
			List<ExtendedType> it = vcard.getExtendedTypes();
			assertEquals(7, it.size());
			
			ExtendedType f = it.get(0);
			assertEquals("X-COUCHDB-APPLICATION-ANNOTATIONS", f.getExtendedName());
			assertEquals("{\"Evolution\":{\"revision\":\"2012-03-05T13:32:54Z\"}}", f.getExtendedValue());
			
			f = it.get(1);
			assertEquals("X-AIM", f.getExtendedName());
			assertEquals("johnny5@aol.com", f.getExtendedValue());
			
			List<ExtendedParamType> xParamTypes = f.getExtendedParams();
			assertEquals(2, xParamTypes.size());
			
			ExtendedParamType xParamType = xParamTypes.get(0);
			assertEquals("TYPE", xParamType.getTypeName());
			assertEquals("HOME", xParamType.getTypeValue());
			
			xParamType = xParamTypes.get(1);
			assertEquals("X-COUCHDB-UUID",xParamType.getTypeName());
			assertEquals("cb9e11fc-bb97-4222-9cd8-99820c1de454".toUpperCase(), xParamType.getTypeValue());
			
			f = it.get(2);
			assertEquals("X-EVOLUTION-FILE-AS", f.getExtendedName());
			assertEquals("Doe, John", f.getExtendedValue());
			
			f = it.get(3);
			assertEquals("X-EVOLUTION-SPOUSE", f.getExtendedName());
			assertEquals("Maria", f.getExtendedValue());

			f = it.get(4);
			assertEquals("X-EVOLUTION-MANAGER", f.getExtendedName());
			assertEquals("Big Blue", f.getExtendedValue());
			
			f = it.get(5);
			assertEquals("X-EVOLUTION-ASSISTANT", f.getExtendedName());
			assertEquals("Little Red", f.getExtendedValue());
			
			f = it.get(6);
			assertEquals("X-EVOLUTION-ANNIVERSARY", f.getExtendedName());
			assertEquals("1980-03-22", f.getExtendedValue());
		}
		
		VCardImpl vcard2 = (VCardImpl)vcard;
		
		if(vcard2.hasErrors()) {
			List<VCardError> errors = vcard2.getErrors();
			for(VCardError error : errors) {
				System.out.println(error.getErrorMessage());
				System.out.println(error.getSeverity());
				System.out.println(StringUtil.formatException(error.getError()));
			}
		}
	}
	
	@Test
	public void testGmailVCard() throws IOException, VCardParseException {
		File gmailCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_GMAIL.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.GMAIL);
		VCard vcard = engine.parse(gmailCard);
		
		//VERSION
		assertEquals(VCardVersion.V3_0, vcard.getVersion().getVersion());
		
		//FN
		{
			FNType f = vcard.getFN();
			assertEquals("Mr. John Richter, James Doe Sr.", f.getFormattedName());
		}
		
		//N
		{
			NType f = vcard.getN();
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			
			List<String> it = f.getAdditionalNames();
			assertEquals(1, it.size());
			assertEquals("Richter, James", it.get(0));
			
			it = f.getHonorificPrefixes();
			assertEquals(1, it.size());
			assertEquals("Mr.", it.get(0));
			
			it = f.getHonorificSuffixes();
			assertEquals(1, it.size());
			assertEquals("Sr.", it.get(0));
		}
		
		//EMAIL
		{
			List<EmailType> it = vcard.getEmails();
			assertEquals(1, it.size());
			
			EmailType f = it.get(0);
			assertEquals("john.doe@ibm.com", f.getEmail());
			
			List<EmailParamType> types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(EmailParamType.INTERNET));
			assertTrue(types.contains(EmailParamType.HOME));
		}
		
		//TEL
		{
			List<TelType> it = vcard.getTels();
			assertEquals(2, it.size());
			
			TelType f = it.get(0);
			assertEquals("905-555-1234", f.getTelephone());
			
			List<TelParamType> types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelParamType.CELL));

			f = it.get(1);
			assertEquals("905-666-1234", f.getTelephone());
			
			types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelParamType.HOME));
		}
		
		//ADR
		{
			List<AdrType> it = vcard.getAdrs();
			assertEquals(1, it.size());
			
			AdrType f = it.get(0);
			assertEquals("", f.getPostOfficeBox());
			assertEquals("Crescent moon drive\n555-asd\nNice Area, Albaney, New York12345\nUnited States of America", f.getExtendedAddress());
			assertEquals("", f.getStreetAddress());
			assertEquals("", f.getLocality());
			assertEquals("", f.getRegion());
			assertEquals("", f.getPostalCode());
			assertEquals("", f.getCountryName());
			
			List<AdrParamType> types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(AdrParamType.HOME));
		}
		
		//ORG
		{
			OrgType f = vcard.getOrg();
			assertEquals("IBM", f.getOrgName());
		}
		
		//TITLE
		{
			TitleType f = vcard.getTitle();
			assertEquals("Money Counter", f.getTitle());
		}
		
		//BDAY
		{
			BDayType f = vcard.getBDay();
			assertEquals(1980, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.MARCH, f.getBirthday().get(Calendar.MONTH));
			assertEquals(22, f.getBirthday().get(Calendar.DAY_OF_MONTH));
		}
		
		//URL
		{
			List<UrlType> it = vcard.getUrls();
			assertEquals(1, it.size());
			
			UrlType f = it.get(0);
			assertEquals("http://www.ibm.com", f.getUrl().toString());
			assertEquals("http://www.ibm.com", f.getRawUrl());
			
			List<UrlParamType> types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(UrlParamType.WORK));
		}

		//NOTE
		{
			List<NoteType> it = vcard.getNotes();
			assertEquals(1, it.size());
			NoteType f = it.get(0);
			assertEquals("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \\\"AS IS\\\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\nFavotire Color: Blue", f.getNote());
		}
		
		//custom types
		{
			List<ExtendedType> it = vcard.getExtendedTypes();
			assertEquals(6, it.size());
			
			ExtendedType f = it.get(0);
			assertEquals("X-PHONETIC-FIRST-NAME", f.getExtendedName());
			assertEquals("Jon", f.getExtendedValue());
			
			f = it.get(1);
			assertEquals("X-PHONETIC-LAST-NAME", f.getExtendedName());
			assertEquals("Dow", f.getExtendedValue());

			f = it.get(2);
			assertEquals("item1", f.getGroup());
			assertEquals("X-ABDATE", f.getExtendedName());
			assertEquals("1975-03-01", f.getExtendedValue());
			
			f = it.get(3);
			assertEquals("item1", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtendedName());
			assertEquals("_$!<Anniversary>!$_", f.getExtendedValue());

			f = it.get(4);
			assertEquals("item2", f.getGroup());
			assertEquals("X-ABRELATEDNAMES", f.getExtendedName());
			assertEquals("Jenny", f.getExtendedValue());
			
			f = it.get(5);
			assertEquals("item2", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtendedName());
			assertEquals("_$!<Spouse>!$_", f.getExtendedValue());
		}
		
		VCardImpl vcard2 = (VCardImpl)vcard;
		
		if(vcard2.hasErrors()) {
			List<VCardError> errors = vcard2.getErrors();
			for(int j = 0; j < errors.size(); j++) {
				System.out.println(errors.get(j).getErrorMessage());
				System.out.println(errors.get(j).getSeverity());
				System.out.println(StringUtil.formatException(errors.get(j).getError()));
			}
		}
	}
	
	@Test
	public void testIPhoneVCard() throws IOException, VCardParseException {
		File iphoneCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_IPHONE.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.I_PHONE);
		VCard vcard = engine.parse(iphoneCard);
		
		//VERSION
		assertEquals(VCardVersion.V3_0, vcard.getVersion().getVersion());
		
		//PRODID
		{
			ProdIdType f = vcard.getProdId();
			assertEquals("-//Apple Inc.//iOS 5.0.1//EN", f.getProdId());
		}
		
		//N
		{
			NType f = vcard.getN();
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			
			List<String> it = f.getAdditionalNames();
			assertEquals(2, it.size());
			assertEquals("Richter", it.get(0));
			assertEquals("James", it.get(1));
			
			it = f.getHonorificPrefixes();
			assertEquals(1, it.size());
			assertEquals("Mr.", it.get(0));
			
			it = f.getHonorificSuffixes();
			assertEquals(1, it.size());
			assertEquals("Sr.", it.get(0));
		}
		
		//FN
		{
			FNType f = vcard.getFN();
			assertEquals("Mr. John Richter James Doe Sr.", f.getFormattedName());
		}
		
		//NICKNAME
		{
			NicknameType f = vcard.getNicknames();
			List<String> it = f.getNicknames();
			assertEquals("Johny", it.get(0));
		}
		
		//ORG
		{
			OrgType f = vcard.getOrg();
			assertEquals("IBM", f.getOrgName());
			
			List<String> it = f.getOrgUnits();
			assertEquals(1, it.size());
			assertEquals("Accounting", it.get(0));
		}
		
		//TITLE
		{
			TitleType f = vcard.getTitle();
			assertEquals("Money Counter", f.getTitle());
		}
		
		//EMAIL
		{
			List<EmailType> it = vcard.getEmails();
			assertEquals(1, it.size());
			
			EmailType f = it.get(0);
			assertEquals("item1", f.getGroup());
			assertEquals("john.doe@ibm.com", f.getEmail());
			
			List<EmailParamType> types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(EmailParamType.INTERNET));
			assertTrue(types.contains(EmailParamType.PREF));
		}
		
		//TEL
		{
			List<TelType> it = vcard.getTels();
			assertEquals(7, it.size());
			
			TelType f = it.get(0);
			assertEquals("905-555-1234", f.getTelephone());
			List<TelParamType> paramsTypes = f.getParams();
			assertEquals(3, paramsTypes.size());
			assertTrue(paramsTypes.contains(TelParamType.CELL));
			assertTrue(paramsTypes.contains(TelParamType.VOICE));
			assertTrue(paramsTypes.contains(TelParamType.PREF));

			f = it.get(1);
			assertEquals("905-666-1234", f.getTelephone());
			paramsTypes = f.getParams();
			assertEquals(2, paramsTypes.size());
			assertTrue(paramsTypes.contains(TelParamType.HOME));
			assertTrue(paramsTypes.contains(TelParamType.VOICE));
			
			f = it.get(2);
			assertEquals("905-777-1234", f.getTelephone());
			paramsTypes = f.getParams();
			assertEquals(2, paramsTypes.size());
			assertTrue(paramsTypes.contains(TelParamType.WORK));
			assertTrue(paramsTypes.contains(TelParamType.VOICE));
			
			f = it.get(3);
			assertEquals("905-888-1234", f.getTelephone());
			paramsTypes = f.getParams();
			assertEquals(2, paramsTypes.size());
			assertTrue(paramsTypes.contains(TelParamType.HOME));
			assertTrue(paramsTypes.contains(TelParamType.FAX));
				
			f = it.get(4);
			assertEquals("905-999-1234", f.getTelephone());
			paramsTypes = f.getParams();
			assertEquals(2, paramsTypes.size());
			assertTrue(paramsTypes.contains(TelParamType.WORK));
			assertTrue(paramsTypes.contains(TelParamType.FAX));
			
			f = it.get(5);
			assertEquals("905-111-1234", f.getTelephone());
			paramsTypes = f.getParams();
			assertEquals(1, paramsTypes.size());
			assertTrue(paramsTypes.contains(TelParamType.PAGER));
			
			f = it.get(6);
			assertEquals("905-222-1234", f.getTelephone());
			assertEquals("item2", f.getGroup());
			paramsTypes = f.getParams();
			assertEquals(0, paramsTypes.size());
		}
		
		//ADR
		{
			List<AdrType> it = vcard.getAdrs();
			assertEquals(2, it.size());
			
			AdrType f = it.get(0);
			assertEquals("item3", f.getGroup());
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Silicon Alley 5,", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("United States of America", f.getCountryName());
			
			List<AdrParamType> paramTypes = f.getParams();
			assertEquals(2, paramTypes.size());
			assertTrue(paramTypes.contains(AdrParamType.HOME));
			assertTrue(paramTypes.contains(AdrParamType.PREF));
			
			f = it.get(1);
			assertEquals("item4", f.getGroup());
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Street4\nBuilding 6\nFloor 8", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("USA", f.getCountryName());
			
			paramTypes = f.getParams();
			assertEquals(1, paramTypes.size());
			assertTrue(paramTypes.contains(AdrParamType.WORK));
		}
		
		//URL
		{
			List<UrlType> it = vcard.getUrls();
			assertEquals(1, it.size());
			
			UrlType f = it.get(0);
			assertEquals("item5", f.getGroup());
			assertEquals("http://www.ibm.com", f.getRawUrl());
			
			List<UrlParamType> types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(UrlParamType.PREF));
		}
		
		//BDAY
		{
			BDayType f = vcard.getBDay();
			assertEquals(2012, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.JUNE, f.getBirthday().get(Calendar.MONTH));
			assertEquals(6, f.getBirthday().get(Calendar.DAY_OF_MONTH));
			assertEquals(ISOFormat.ISO8601_DATE_EXTENDED, f.getISO8601Format());
		}
		
		//PHOTO
		{
			List<PhotoType> it = vcard.getPhotos();
			assertEquals(1, it.size());
			
			PhotoType f = it.get(0);
			assertEquals(EncodingType.BINARY, f.getEncodingType());
			assertEquals(ImageMediaType.JPEG, f.getImageMediaType());
			assertEquals(32531, f.getPhoto().length);
		}
		
		//custom types
		{
			List<ExtendedType> it = vcard.getExtendedTypes();
			assertEquals(4, it.size());
			
			ExtendedType f = it.get(0);
			assertEquals("item2", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtendedName());
			assertEquals("_$!<AssistantPhone>!$_", f.getExtendedValue());

			f = it.get(1);
			assertEquals("item3", f.getGroup());
			assertEquals("X-ABADR", f.getExtendedName());
			assertEquals("Silicon Alley", f.getExtendedValue());
			
			f = it.get(2);
			assertEquals("item4", f.getGroup());
			assertEquals("X-ABADR", f.getExtendedName());
			assertEquals("Street 4, Building 6,\n Floor 8\nNew York\nUSA", f.getExtendedValue());
			
			f = it.get(3);
			assertEquals("item5", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtendedName());
			assertEquals("_$!<HomePage>!$_", f.getExtendedValue());
		}
		
		VCardImpl vcard2 = (VCardImpl)vcard;
		
		if(vcard2.hasErrors()) {
			List<VCardError> errors = vcard2.getErrors();
			for(int j = 0; j < errors.size(); j++) {
				System.out.println(errors.get(j).getErrorMessage());
				System.out.println(errors.get(j).getSeverity());
				System.out.println(StringUtil.formatException(errors.get(j).getError()));
			}
		}
	}
	
	@Test
	public void testLotusNotesVCard() throws IOException, VCardParseException {
		//TODO the entire lotus vcard is inconsistent.
		//1: Folding non-binary text is using 2 spaces instead of 1
		//2: The folded LABEL is 1 character more than the folded binary
		//3: NOTE and ADR are not folded but there is a space in the word "floor"
		//we need some more proper samples.
		
		File lotusNotesCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_LOTUS_NOTES.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.MAC_ADDRESS_BOOK);
		VCard vcard = engine.parse(lotusNotesCard);
		
		//VERSION
		assertEquals(VCardVersion.V3_0, vcard.getVersion().getVersion());
		
		//PRODID
		{
			ProdIdType f = vcard.getProdId();
			assertEquals("-//Apple Inc.//Address Book 6.1//EN", f.getProdId());
		}
		
		//N
		{
			NType f = vcard.getN();
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			
			List<String> it = f.getAdditionalNames();
			assertEquals(1, it.size());
			assertEquals("Johny", it.get(0));
			
			it = f.getHonorificPrefixes();
			assertEquals(1, it.size());
			assertEquals("Mr.", it.get(0));
			
			it = f.getHonorificSuffixes();
			assertEquals(1, it.size());
			assertEquals("I", it.get(0));
		}
		
		//FN
		{
			FNType f = vcard.getFN();
			assertEquals("Mr. Doe John I Johny", f.getFormattedName());
		}
		
		//NICKNAME
		{
			NicknameType f = vcard.getNicknames();
			List<String> it = f.getNicknames();
			assertEquals(1, it.size());
			assertEquals("Johny,JayJay", it.get(0));
		}
		
		//ORG
		{
			OrgType f = vcard.getOrg();
			assertEquals("IBM", f.getOrgName());
			
			List<String> it = f.getOrgUnits();
			assertEquals(1, it.size());
			assertEquals("SUN", it.get(0));
		}
		
		//TITLE
		{
			TitleType f = vcard.getTitle();
			assertEquals("Generic Accountant", f.getTitle());
		}
		
		//EMAIL
		{
			List<EmailType> it = vcard.getEmails();
			assertEquals(2, it.size());
			
			EmailType f = it.get(0);
			assertEquals("john.doe@ibm.com", f.getEmail());
			
			List<EmailParamType> types = f.getParams();
			assertEquals(3, types.size());
			assertTrue(types.contains(EmailParamType.INTERNET));
			assertTrue(types.contains(EmailParamType.WORK));
			assertTrue(types.contains(EmailParamType.PREF));
			
			f = it.get(1);
			assertEquals("billy_bob@gmail.com", f.getEmail());
			types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(EmailParamType.INTERNET));
			assertTrue(types.contains(EmailParamType.WORK));
		}
		
		//TEL
		{
			List<TelType> it = vcard.getTels();
			assertEquals(2, it.size());
			
			TelType f = it.get(0);
			assertEquals("+1 (212) 204-34456", f.getTelephone());
			List<TelParamType> types = f.getParams();
			assertEquals(3, types.size());
			assertTrue(types.contains(TelParamType.CELL));
			assertTrue(types.contains(TelParamType.VOICE));
			assertTrue(types.contains(TelParamType.PREF));

			f = it.get(1);
			assertEquals("00-1-212-555-7777", f.getTelephone());
			types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelParamType.WORK));
			assertTrue(types.contains(TelParamType.FAX));
		}
		
		//ADR
		{
			List<AdrType> it = vcard.getAdrs();
			assertEquals(1, it.size());
			
			AdrType f = it.get(0);
			assertEquals("item1", f.getGroup());
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("25334\nSouth cresent drive, Building 5, 3rd floo r", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("NYC887", f.getPostalCode());
			assertEquals("U.S.A.", f.getCountryName());
			
			List<AdrParamType> types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(AdrParamType.HOME));
			assertTrue(types.contains(AdrParamType.PREF));
		}
		
		//NOTE
		{
			List<NoteType> it = vcard.getNotes();
			assertEquals(1, it.size());
			NoteType f = it.get(0);
			assertEquals("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\"\nAND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO , THE\nIMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR P URPOSE\nARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTOR S BE\nLIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR\nCONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF\n SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS \nINTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN\n CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)\nA RISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE\n POSSIBILITY OF SUCH DAMAGE.", f.getNote());
		}
		
		//URL
		{
			List<UrlType> it = vcard.getUrls();
			assertEquals(1, it.size());
			
			UrlType f = it.get(0);
			assertEquals("item2", f.getGroup());
			assertEquals("http://www.sun.com", f.getRawUrl());
			
			List<UrlParamType> types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(UrlParamType.PREF));
		}
		
		//BDAY
		{
			BDayType f = vcard.getBDay();
			assertEquals(1980, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.MAY, f.getBirthday().get(Calendar.MONTH));
			assertEquals(21, f.getBirthday().get(Calendar.DAY_OF_MONTH));
			assertEquals(ISOFormat.ISO8601_DATE_EXTENDED, f.getISO8601Format());
		}
		
		//PHOTO
		{
			List<PhotoType> it = vcard.getPhotos();
			assertEquals(1, it.size());
			
			PhotoType f = it.get(0);
			assertEquals(EncodingType.BINARY, f.getEncodingType());
			assertEquals(ImageMediaType.JPEG, f.getImageMediaType());
			assertEquals(7957, f.getPhoto().length);
		}
		
		//UID
		{
			UidType f = vcard.getUid();
			assertEquals("0e7602cc-443e-4b82-b4b1-90f62f99a199", f.getUid());
		}
		
		//GEO
		{
			GeoType f = vcard.getGeo();
			assertEquals(-2.6, f.getLatitude(), .01);
			assertEquals(3.4, f.getLongitude(), .01);
		}
		
		//CLASS
		{
			ClassType f = vcard.getSecurityClass();
			assertEquals("Public", f.getSecurityClass());
		}
		
		//PROFILE
		{
			ProfileType f = vcard.getProfile();
			assertEquals("VCard", f.getProfile());
		}
		
		//TZ
		{
			TzType f = vcard.getTz();
			assertEquals(1, f.getHourOffset());
			assertEquals(0, f.getMinuteOffset());
		}
		
		//LABEL
		{
			//This label does not get parsed because its parameter types
			//do not match any of the parameter types of any of the existing
			//addresses. In this case the parameter type PARCEL is extra from
			//the address.
			//
			//This is an issue with the way Labels and Addresses are defined in
			//the VCard RFC. There are 2 ways to go about it:
			//
			//1: An ADR may have only 1 LABEL associated to it, the LABEL directly under it
			//2: An ADR may have only 1 LABEL associated to it by parameter types. Only
			//   one ADR and LABEL without zero parameter types may hold an association.
			//
			// Cardme takes approach 2.
			
			/*
			List<LabelType> it = vcard.getLables();
			assertEquals(1, it.size());
			
			LabelType f = it.get(0);
			assertEquals("John Doe\nNew York, NewYork,\nSouth Crecent Drive,\nBuilding 5, floor 3,\nUSA", f.getLabel());
			List<LabelParamType> types = f.getParams();
			assertEquals(3, types.size());
			assertTrue(types.contains(LabelParamType.HOME));
			assertTrue(types.contains(LabelParamType.PARCEL));
			assertTrue(types.contains(LabelParamType.PREF));
			*/
			
		}
		
		//SORT-STRING
		{
			SortStringFeature f = vcard.getSortString();
			assertEquals("JOHN", f.getSortString());
		}
		
		//ROLE
		{
			RoleFeature f = vcard.getRole();
			assertEquals("Counting Money", f.getRole());
		}
		
		//SOURCE
		{
			SourceFeature f = vcard.getSource();
			assertEquals("Whatever", f.getSource());
		}
		
		//MAILER
		{
			MailerFeature f = vcard.getMailer();
			assertEquals("Mozilla Thunderbird", f.getMailer());
		}
		
		//NAME
		{
			NameType f = vcard.getName();
			assertEquals("VCard for John Doe", f.getName());
		}
		
		//custom types
		{
			List<ExtendedType> it = vcard.getExtendedTypes();
			assertEquals(4, it.size());
			
			ExtendedType f = it.get(0);
			assertEquals("item2", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtendedName());
			assertEquals("_$!<HomePage>!$_", f.getExtendedValue());

			f = it.get(1);
			assertEquals("X-ABUID", f.getExtendedName());
			assertEquals("0E7602CC-443E-4B82-B4B1-90F62F99A199:ABPerson", f.getExtendedValue());
			
			f = it.get(2);
			assertEquals("X-GENERATOR", f.getExtendedName());
			assertEquals("Cardme Generator", f.getExtendedValue());
			
			f = it.get(3);
			assertEquals("X-LONG-STRING", f.getExtendedName());
			assertEquals("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", f.getExtendedValue());
		}
		
		VCardImpl vcard2 = (VCardImpl)vcard;
		
		if(vcard2.hasErrors()) {
			List<VCardError> errors = vcard2.getErrors();
			for(int j = 0; j < errors.size(); j++) {
				System.out.println(errors.get(j).getErrorMessage());
				System.out.println(errors.get(j).getSeverity());
				System.out.println(StringUtil.formatException(errors.get(j).getError()));
			}
		}
	}
	
	@Test
	public void testMsOutlookVCard() throws IOException, VCardParseException {
		File msOutlookCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_MS_OUTLOOK.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.MS_OUTLOOK);
		VCard vcard = engine.parse(msOutlookCard);
		
		//VERSION
		assertEquals(VCardVersion.V2_1, vcard.getVersion().getVersion());
		
		//N
		{
			NType f = vcard.getN();
			assertEquals(LanguageType.EN_US, f.getLanguage());
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			
			List<String> it = f.getAdditionalNames();
			assertEquals(2, it.size());
			assertEquals("Richter", it.get(0));
			assertEquals("James", it.get(1));
			
			it = f.getHonorificPrefixes();
			assertEquals(1, it.size());
			assertEquals("Mr.", it.get(0));
			
			it = f.getHonorificSuffixes();
			assertEquals(1, it.size());
			assertEquals("Sr.", it.get(0));
		}
		
		//FN
		{
			FNType f = vcard.getFN();
			assertEquals("Mr. John Richter James Doe Sr.", f.getFormattedName());
		}
		
		//NICKNAME
		{
			NicknameType f = vcard.getNicknames();
			List<String> it = f.getNicknames();
			assertEquals(1, it.size());
			assertEquals("Johny", it.get(0));
		}
		
		//ORG
		{
			OrgType f = vcard.getOrg();
			assertEquals("IBM", f.getOrgName());
			
			List<String> it = f.getOrgUnits();
			assertEquals(1, it.size());
			assertEquals("Accounting", it.get(0));
		}
		
		//TITLE
		{
			TitleType f = vcard.getTitle();
			assertEquals("Money Counter", f.getTitle());
		}
		
		//NOTE
		{
			List<NoteType> it = vcard.getNotes();
			assertEquals(1, it.size());
			
			NoteType f = it.get(0);
			assertEquals("THIS SOFTWARE IS PROVIDED BY GEORGE EL-HADDAD ''AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL GEORGE EL-HADDAD OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.", f.getNote());
		}
		
		//TEL
		{
			List<TelType> it = vcard.getTels();
			assertEquals(2, it.size());
			
			TelType f = it.get(0);
			assertEquals("(905) 555-1234", f.getTelephone());
			List<TelParamType> types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelParamType.WORK));
			assertTrue(types.contains(TelParamType.VOICE));

			f = it.get(1);
			assertEquals("(905) 666-1234", f.getTelephone());
			types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelParamType.HOME));
			assertTrue(types.contains(TelParamType.VOICE));
		}
		
		//ADR
		{
			List<AdrType> it = vcard.getAdrs();
			assertEquals(2, it.size());
			
			AdrType f = it.get(0);
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Cresent moon drive", f.getStreetAddress());
			assertEquals("Albaney", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("United States of America", f.getCountryName());
			
			List<AdrParamType> types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(AdrParamType.WORK));
			assertTrue(types.contains(AdrParamType.PREF));
			
			f = it.get(1);
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Silicon Alley 5,", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("United States of America", f.getCountryName());
			
			types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(AdrParamType.HOME));
		}
		
		//LABEL
		{
			List<LabelType> it = vcard.getLables();
			assertEquals(2, it.size());
			
			LabelType f = it.get(0);
			assertEquals("Cresent moon drive\r\nAlbaney, New York  12345", f.getLabel());
			
			List<LabelParamType> types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(LabelParamType.WORK));
			assertTrue(types.contains(LabelParamType.PREF));
			
			f = it.get(1);
			assertEquals("Silicon Alley 5,\r\nNew York, New York  12345", f.getLabel());
			types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(LabelParamType.HOME));
		}
		
		//URL
		{
			List<UrlType> it = vcard.getUrls();
			assertEquals(1, it.size());
			
			UrlType f = it.get(0);
			assertEquals("http://www.ibm.com", f.getRawUrl());
			
			List<UrlParamType> types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(UrlParamType.WORK));
		}
		
		//ROLE
		{
			RoleType f = vcard.getRole();
			assertEquals("Counting Money", f.getRole());
		}
		
		//BDAY
		{
			BDayType f = vcard.getBDay();
			assertEquals(1980, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.MARCH, f.getBirthday().get(Calendar.MONTH));
			assertEquals(22, f.getBirthday().get(Calendar.DAY_OF_MONTH));
		}
		
		//EMAIL
		{
			List<EmailType> it = vcard.getEmails();
			assertEquals(1, it.size());
			
			EmailType f = it.get(0);
			assertEquals("john.doe@ibm.cm", f.getEmail());
			
			List<EmailParamType> types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(EmailParamType.PREF));
			assertTrue(types.contains(EmailParamType.INTERNET));
		}

		//PHOTO
		{
			List<PhotoType> it = vcard.getPhotos();
			assertEquals(1, it.size());
			
			PhotoType f = it.get(0);
			assertEquals(EncodingType.BINARY, f.getEncodingType());
			assertEquals(ImageMediaType.JPEG, f.getImageMediaType());
			assertEquals(860, f.getPhoto().length);
		}
		
		//REV
		{
			RevType f = vcard.getRev();
			Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			c.clear();
			c.set(Calendar.YEAR, 2012);
			c.set(Calendar.MONTH, Calendar.MARCH);
			c.set(Calendar.DAY_OF_MONTH, 5);
			c.set(Calendar.HOUR_OF_DAY, 13);
			c.set(Calendar.MINUTE, 19);
			c.set(Calendar.SECOND, 33);
			Calendar actual = f.getRevision();
			assertEquals(c.getTime(), actual.getTime());
		}
		
		//custom types
		{
			List<ExtendedType> it = vcard.getExtendedTypes();
			assertEquals(6, it.size());
			
			ExtendedType f = it.get(0);
			assertEquals("X-MS-OL-DEFAULT-POSTAL-ADDRESS", f.getExtendedName());
			assertEquals("2", f.getExtendedValue());
			
			f = it.get(1);
			assertEquals("X-MS-ANNIVERSARY", f.getExtendedName());
			assertEquals("20110113", f.getExtendedValue());
			
			f = it.get(2);
			assertEquals("X-MS-IMADDRESS", f.getExtendedName());
			assertEquals("johny5@aol.com", f.getExtendedValue());

			f = it.get(3);
			assertEquals("X-MS-OL-DESIGN", f.getExtendedName());
			assertEquals("<card xmlns=\"http://schemas.microsoft.com/office/outlook/12/electronicbusinesscards\" ver=\"1.0\" layout=\"left\" bgcolor=\"ffffff\"><img xmlns=\"\" align=\"tleft\" area=\"32\" use=\"photo\"/><fld xmlns=\"\" prop=\"name\" align=\"left\" dir=\"ltr\" style=\"b\" color=\"000000\" size=\"10\"/><fld xmlns=\"\" prop=\"org\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"title\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"dept\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"telwork\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"><label align=\"right\" color=\"626262\">Work</label></fld><fld xmlns=\"\" prop=\"telhome\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"><label align=\"right\" color=\"626262\">Home</label></fld><fld xmlns=\"\" prop=\"email\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"addrwork\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"addrhome\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"webwork\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/></card>", f.getExtendedValue());
			assertEquals(Charset.forName("UTF-8"), f.getCharset());
			
			f = it.get(4);
			assertEquals("X-MS-MANAGER", f.getExtendedName());
			assertEquals("Big Blue", f.getExtendedValue());
			
			f = it.get(5);
			assertEquals("X-MS-ASSISTANT", f.getExtendedName());
			assertEquals("Jenny", f.getExtendedValue());
		}
		
		VCardImpl vcard2 = (VCardImpl)vcard;
		
		if(vcard2.hasErrors()) {
			List<VCardError> errors = vcard2.getErrors();
			for(int j = 0; j < errors.size(); j++) {
				System.out.println(errors.get(j).getErrorMessage());
				System.out.println(errors.get(j).getSeverity());
				System.out.println(StringUtil.formatException(errors.get(j).getError()));
			}
		}
	}
	
	@Test
	public void testMacAddressBookVCard() throws IOException, VCardParseException {
		File macAddressBookCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_MAC_ADDRESS_BOOK.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.MAC_ADDRESS_BOOK);
		VCard vcard = engine.parse(macAddressBookCard);
		
		//VERSION
		assertEquals(VCardVersion.V3_0, vcard.getVersion().getVersion());
		
		//N
		{
			NType f = vcard.getN();
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			
			List<String> it = f.getAdditionalNames();
			assertEquals(1, it.size());
			assertEquals("Richter,James", it.get(0));
			
			it = f.getHonorificPrefixes();
			assertEquals(1, it.size());
			assertEquals("Mr.", it.get(0));
			
			it = f.getHonorificSuffixes();
			assertEquals(1, it.size());
			assertEquals("Sr.", it.get(0));
		}
		
		//FN
		{
			FNType f = vcard.getFN();
			assertEquals("Mr. John Richter,James Doe Sr.", f.getFormattedName());
		}
		
		//NICKNAME
		{
			NicknameType f = vcard.getNicknames();
			List<String> it = f.getNicknames();
			assertEquals(1, it.size());
			assertEquals("Johny", it.get(0));
		}
		
		//ORG
		{
			OrgType f = vcard.getOrg();
			assertEquals("IBM", f.getOrgName());
			
			List<String> it = f.getOrgUnits();
			assertEquals(1, it.size());
			assertEquals("Accounting", it.get(0));
		}
		
		//TITLE
		{
			TitleType f = vcard.getTitle();
			assertEquals("Money Counter", f.getTitle());
		}
		
		//EMAIL
		{
			List<EmailType> it = vcard.getEmails();
			assertEquals(1, it.size());
			
			EmailType f = it.get(0);
			assertEquals("john.doe@ibm.com", f.getEmail());
			
			List<EmailParamType> types = f.getParams();
			assertEquals(3, types.size());
			assertTrue(types.contains(EmailParamType.INTERNET));
			assertTrue(types.contains(EmailParamType.WORK));
			assertTrue(types.contains(EmailParamType.PREF));
		}
		
		//TEL
		{
			List<TelType> it = vcard.getTels();
			assertEquals(7, it.size());
			
			TelType f = it.get(0);
			assertEquals("905-777-1234", f.getTelephone());
			List<TelParamType> types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelParamType.WORK));
			assertTrue(types.contains(TelParamType.PREF));

			f = it.get(1);
			assertEquals("905-666-1234", f.getTelephone());
			types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelParamType.HOME));
			
			f = it.get(2);
			assertEquals("905-555-1234", f.getTelephone());
			types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelParamType.CELL));
			
			f = it.get(3);
			assertEquals("905-888-1234", f.getTelephone());
			types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelParamType.HOME));
			assertTrue(types.contains(TelParamType.FAX));
				
			f = it.get(4);
			assertEquals("905-999-1234", f.getTelephone());
			types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelParamType.WORK));
			assertTrue(types.contains(TelParamType.FAX));
			
			f = it.get(5);
			assertEquals("905-111-1234", f.getTelephone());
			types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelParamType.PAGER));
			
			f = it.get(6);
			assertEquals("905-222-1234", f.getTelephone());
			assertEquals("item1", f.getGroup());
			types = f.getParams();
			assertEquals(0, types.size());
		}
		
		//ADR
		{
			List<AdrType> it = vcard.getAdrs();
			assertEquals(2, it.size());
			
			AdrType f = it.get(0);
			assertEquals("item2", f.getGroup());
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Silicon Alley 5,", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("United States of America", f.getCountryName());
			
			List<AdrParamType> types = f.getParams();
			assertEquals(2, types.size());
			assertTrue(types.contains(AdrParamType.HOME));
			assertTrue(types.contains(AdrParamType.PREF));
			
			f = it.get(1);
			assertEquals("item3", f.getGroup());
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Street4\nBuilding 6\nFloor 8", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("USA", f.getCountryName());
			
			types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(AdrParamType.WORK));
		}
		
		//NOTE
		{
			List<NoteType> it = vcard.getNotes();
			assertEquals(1, it.size());
			
			NoteType f = it.get(0);
			assertEquals("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \\\"AS IS\\\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\nFavotire Color: Blue", f.getNote());
		}
		
		//URL
		{
			List<UrlType> it = vcard.getUrls();
			assertEquals(1, it.size());
			
			UrlType f = it.get(0);
			assertEquals("item4", f.getGroup());
			assertEquals("http://www.ibm.com", f.getRawUrl());
			
			List<UrlParamType> types = f.getParams();
			assertEquals(1, types.size());
			assertTrue(types.contains(UrlParamType.PREF));
		}
		
		//BDAY
		{
			BDayType f = vcard.getBDay();
			assertEquals(2012, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.JUNE, f.getBirthday().get(Calendar.MONTH));
			assertEquals(6, f.getBirthday().get(Calendar.DAY_OF_MONTH));
			assertEquals(ISOFormat.ISO8601_DATE_EXTENDED, f.getISO8601Format());
		}
		
		//PHOTO
		{
			List<PhotoType> it = vcard.getPhotos();
			assertEquals(1, it.size());
			
			PhotoType f = it.get(0);
			assertEquals(EncodingType.BINARY, f.getEncodingType());
			assertEquals(null, f.getImageMediaType());
			assertEquals(18242, f.getPhoto().length);
		}
		
		//custom types
		{
			List<ExtendedType> it = vcard.getExtendedTypes();
			assertEquals(9, it.size());
			
			ExtendedType f = it.get(0);
			assertEquals("X-PHONETIC-FIRST-NAME", f.getExtendedName());
			assertEquals("Jon", f.getExtendedValue());
			
			f = it.get(1);
			assertEquals("X-PHONETIC-LAST-NAME", f.getExtendedName());
			assertEquals("Dow", f.getExtendedValue());
			
			f = it.get(2);
			assertEquals("item1", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtendedName());
			assertEquals("AssistantPhone", f.getExtendedValue());

			f = it.get(3);
			assertEquals("item2", f.getGroup());
			assertEquals("X-ABADR", f.getExtendedName());
			assertEquals("Silicon Alley", f.getExtendedValue());
			
			f = it.get(4);
			assertEquals("item3", f.getGroup());
			assertEquals("X-ABADR", f.getExtendedName());
			assertEquals("Street 4, Building 6,\nFloor 8\nNew York\nUSA", f.getExtendedValue());
			
			f = it.get(5);
			assertEquals("item4", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtendedName());
			assertEquals("_$!<HomePage>!$_", f.getExtendedValue());
			
			f = it.get(6);
			assertEquals("item5", f.getGroup());
			assertEquals("X-ABRELATEDNAMES", f.getExtendedName());
			assertEquals("Jenny", f.getExtendedValue());
			
			List<ExtendedParamType> xlist = f.getExtendedParams();
			assertEquals(1, xlist.size());
			assertEquals("TYPE", xlist.get(0).getTypeName());
			assertEquals("PREF", xlist.get(0).getTypeValue());
			
			f = it.get(7);
			assertEquals("item5", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtendedName());
			assertEquals("Spouse", f.getExtendedValue());
			
			f = it.get(8);
			assertEquals("X-ABUID", f.getExtendedName());
			assertEquals("6B29A774-D124-4822-B8D0-2780EC117F60:ABPerson", f.getExtendedValue());
		}
		
		VCardImpl vcard2 = (VCardImpl)vcard;
		
		if(vcard2.hasErrors()) {
			List<VCardError> errors = vcard2.getErrors();
			for(int j = 0; j < errors.size(); j++) {
				System.out.println(errors.get(j).getErrorMessage());
				System.out.println(errors.get(j).getSeverity());
				System.out.println(StringUtil.formatException(errors.get(j).getError()));
			}
		}
	}
}
