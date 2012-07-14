package net.sourceforge.cardme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import net.sourceforge.cardme.engine.VCardEngine;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.util.ISOFormat;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.LanguageType;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.VCardVersion;
import net.sourceforge.cardme.vcard.errors.VCardError;
import net.sourceforge.cardme.vcard.features.AddressFeature;
import net.sourceforge.cardme.vcard.features.BirthdayFeature;
import net.sourceforge.cardme.vcard.features.CategoriesFeature;
import net.sourceforge.cardme.vcard.features.ClassFeature;
import net.sourceforge.cardme.vcard.features.DisplayableNameFeature;
import net.sourceforge.cardme.vcard.features.EmailFeature;
import net.sourceforge.cardme.vcard.features.ExtendedFeature;
import net.sourceforge.cardme.vcard.features.FormattedNameFeature;
import net.sourceforge.cardme.vcard.features.GeographicPositionFeature;
import net.sourceforge.cardme.vcard.features.LabelFeature;
import net.sourceforge.cardme.vcard.features.MailerFeature;
import net.sourceforge.cardme.vcard.features.NameFeature;
import net.sourceforge.cardme.vcard.features.NicknameFeature;
import net.sourceforge.cardme.vcard.features.NoteFeature;
import net.sourceforge.cardme.vcard.features.OrganizationFeature;
import net.sourceforge.cardme.vcard.features.PhotoFeature;
import net.sourceforge.cardme.vcard.features.ProductIdFeature;
import net.sourceforge.cardme.vcard.features.ProfileFeature;
import net.sourceforge.cardme.vcard.features.RevisionFeature;
import net.sourceforge.cardme.vcard.features.RoleFeature;
import net.sourceforge.cardme.vcard.features.SortStringFeature;
import net.sourceforge.cardme.vcard.features.SourceFeature;
import net.sourceforge.cardme.vcard.features.TelephoneFeature;
import net.sourceforge.cardme.vcard.features.TimeZoneFeature;
import net.sourceforge.cardme.vcard.features.TitleFeature;
import net.sourceforge.cardme.vcard.features.UIDFeature;
import net.sourceforge.cardme.vcard.features.URLFeature;
import net.sourceforge.cardme.vcard.types.media.ImageMediaType;
import net.sourceforge.cardme.vcard.types.parameters.AddressParameterType;
import net.sourceforge.cardme.vcard.types.parameters.EmailParameterType;
import net.sourceforge.cardme.vcard.types.parameters.ExtendedParameterType;
import net.sourceforge.cardme.vcard.types.parameters.LabelParameterType;
import net.sourceforge.cardme.vcard.types.parameters.TelephoneParameterType;
import net.sourceforge.cardme.vcard.types.parameters.URLParameterType;

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
	public void testEvolutionVCard() throws IOException {
		File evoCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_EVOLUTION.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.EVOLUTION);
		VCard vcard = engine.parse(evoCard);
		
		//VERSION
		assertEquals(VCardVersion.V3_0, vcard.getVersion().getVersion());
		
		//URL
		{
			Iterator<URLFeature> it = vcard.getURLs();
			URLFeature f = it.next();
			assertEquals("http://www.ibm.com", f.getURL().toString());
			
			List<ExtendedParameterType> xlist = f.getExtendedParametersList();
			assertEquals(1, xlist.size());
			assertEquals("X-COUCHDB-UUID", xlist.get(0).getXtendedTypeName());
			//FIXME double quotes are not removed
			//parameter values can be enclosed in double quotes to auto-escape special chars
			//see RFC 2426 p.29 -- "param-value = ptext / quoted-string"
			//assertEquals("0abc9b8d-0845-47d0-9a91-3db5bb74620d".toUpperCase(), xlist.get(0).getXtendedTypeValue());
			assertEquals("\"0abc9b8d-0845-47d0-9a91-3db5bb74620d\"".toUpperCase(), xlist.get(0).getXtendedTypeValue());
			
			assertFalse(it.hasNext());
		}
		
		//TEL
		{
			Iterator<TelephoneFeature> it = vcard.getTelephoneNumbers();
			TelephoneFeature f = it.next();
			assertEquals("905-666-1234", f.getTelephone());
			
			List<TelephoneParameterType> types = f.getTelephoneParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelephoneParameterType.CELL));
			
			List<ExtendedParameterType> xlist = f.getExtendedParametersList();
			assertEquals(1, xlist.size());
			assertEquals("X-COUCHDB-UUID", xlist.get(0).getXtendedTypeName());
			assertEquals("\"c2fa1caa-2926-4087-8971-609cfc7354ce\"".toUpperCase(), xlist.get(0).getXtendedTypeValue());
			
			f = it.next();
			assertEquals("905-555-1234", f.getTelephone());
			
			types = f.getTelephoneParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelephoneParameterType.WORK));
			assertTrue(types.contains(TelephoneParameterType.VOICE));
			
			xlist = f.getExtendedParametersList();
			assertEquals(1, xlist.size());
			assertEquals("X-COUCHDB-UUID", xlist.get(0).getXtendedTypeName());
			assertEquals("\"fbfb2722-4fd8-4dbf-9abd-eeb24072fd8e\"".toUpperCase(), xlist.get(0).getXtendedTypeValue());
			
			assertFalse(it.hasNext());
		}
		
		//UID
		{
			UIDFeature f = vcard.getUID();
			assertEquals("477343c8e6bf375a9bac1f96a5000837", f.getUID());
		}
		
		//N
		{
			NameFeature f = vcard.getName();
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			Iterator<String> it = f.getAdditionalNames();
			//FIXME escaped commas should not be considered delimiters
			//assertEquals("Richter, James");
			assertEquals("Richter\\", it.next());
			assertEquals(" James", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificPrefixes();
			assertEquals("Mr.", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificSuffixes();
			assertEquals("Sr.", it.next());
			assertFalse(it.hasNext());
		}
		
		//FN
		{
			FormattedNameFeature f = vcard.getFormattedName();
			assertEquals(f.getFormattedName(), "Mr. John Richter, James Doe Sr.");
		}
		
		//NICKNAME
		{
			NicknameFeature f = vcard.getNicknames();
			Iterator<String> it = f.getNicknames();
			assertEquals("Johny", it.next());
			assertFalse(it.hasNext());
		}
		
		//ORG
		{
			OrganizationFeature f = vcard.getOrganizations();
			Iterator<String> it = f.getOrganizations();
			assertEquals("IBM", it.next());
			assertEquals("Accounting", it.next());
			assertEquals("Dungeon", it.next());
			assertFalse(it.hasNext());
		}
		
		//TITLE
		{
			TitleFeature f = vcard.getTitle();
			assertEquals("Money Counter", f.getTitle());
		}
		
		//CATEGORIES
		{
			CategoriesFeature f = vcard.getCategories();
			Iterator<String> it = f.getCategories();
			assertEquals("VIP", it.next());
			assertFalse(it.hasNext());
		}
		
		//NOTE
		{
			Iterator<NoteFeature> it = vcard.getNotes();
			NoteFeature f = it.next();
			assertEquals("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.", f.getNote());
			assertFalse(it.hasNext());
		}
		
		//EMAIL
		{
			Iterator<EmailFeature> it = vcard.getEmails();
			EmailFeature f = it.next();
			assertEquals("john.doe@ibm.com", f.getEmail());
			
			List<EmailParameterType> types = f.getEmailParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(EmailParameterType.WORK));
			
			List<ExtendedParameterType> xlist = f.getExtendedParametersList();
			assertEquals(1, xlist.size());
			assertEquals("X-COUCHDB-UUID", xlist.get(0).getXtendedTypeName());
			assertEquals("\"83a75a5d-2777-45aa-bab5-76a4bd972490\"".toUpperCase(), xlist.get(0).getXtendedTypeValue());
			
			assertFalse(it.hasNext());
		}
		
		//ADR
		{
			Iterator<AddressFeature> it = vcard.getAddresses();
			AddressFeature f = it.next();
			assertEquals("ASB-123", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("15 Crescent moon drive", f.getStreetAddress());
			assertEquals("Albaney", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			//FIXME the space between "United" and "States" is lost because it was included with the folding character and ignored (see .vcf file)
			assertEquals("UnitedStates of America", f.getCountryName());
			
			List<AddressParameterType> types = f.getAddressParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(AddressParameterType.HOME));
			
			assertFalse(it.hasNext());
		}
		
		//BDAY
		{
			BirthdayFeature f = vcard.getBirthDay();
			assertEquals(1980, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.MARCH, f.getBirthday().get(Calendar.MONTH));
			assertEquals(22, f.getBirthday().get(Calendar.DAY_OF_MONTH));
		}
		
		//REV
		{
			RevisionFeature f = vcard.getRevision();
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
			Iterator<ExtendedFeature> it = vcard.getExtendedTypes();
			ExtendedFeature f = it.next();
			assertEquals("X-COUCHDB-APPLICATION-ANNOTATIONS", f.getExtensionName());
			assertEquals("{\"Evolution\":{\"revision\":\"2012-03-05T13:32:54Z\"}}", f.getExtensionData());
			
			f = it.next();
			assertEquals("X-AIM", f.getExtensionName());
			assertEquals("johnny5@aol.com", f.getExtensionData());
			List<ExtendedParameterType> xlist = f.getExtendedParametersList();
			assertEquals(2, xlist.size());
			assertEquals("TYPE", xlist.get(0).getXtendedTypeName());
			assertEquals("HOME".toUpperCase(), xlist.get(0).getXtendedTypeValue());
			assertEquals("X-COUCHDB-UUID", xlist.get(1).getXtendedTypeName());
			assertEquals("\"cb9e11fc-bb97-4222-9cd8-99820c1de454\"".toUpperCase(), xlist.get(1).getXtendedTypeValue());
			
			f = it.next();
			assertEquals("X-EVOLUTION-FILE-AS", f.getExtensionName());
			assertEquals("Doe, John", f.getExtensionData());
			
			f = it.next();
			assertEquals("X-EVOLUTION-SPOUSE", f.getExtensionName());
			assertEquals("Maria", f.getExtensionData());

			f = it.next();
			assertEquals("X-EVOLUTION-MANAGER", f.getExtensionName());
			assertEquals("Big Blue", f.getExtensionData());
			
			f = it.next();
			assertEquals("X-EVOLUTION-ASSISTANT", f.getExtensionName());
			assertEquals("Little Red", f.getExtensionData());
			
			f = it.next();
			assertEquals("X-EVOLUTION-ANNIVERSARY", f.getExtensionName());
			assertEquals("1980-03-22", f.getExtensionData());
			
			assertFalse(it.hasNext());
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
	public void testGmailVCard() throws IOException {
		File gmailCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_GMAIL.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.GMAIL);
		VCard vcard = engine.parse(gmailCard);
		
		//VERSION
		assertEquals(VCardVersion.V3_0, vcard.getVersion().getVersion());
		
		//FN
		{
			FormattedNameFeature f = vcard.getFormattedName();
			assertEquals(f.getFormattedName(), "Mr. John Richter, James Doe Sr.");
		}
		
		//N
		{
			NameFeature f = vcard.getName();
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			Iterator<String> it = f.getAdditionalNames();
			//FIXME escaped commas should not be considered delimiters
			//assertEquals("Richter, James", it.next());
			assertEquals("Richter\\", it.next());
			assertEquals(" James", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificPrefixes();
			assertEquals("Mr.", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificSuffixes();
			assertEquals("Sr.", it.next());
			assertFalse(it.hasNext());
		}
		
		//EMAIL
		{
			Iterator<EmailFeature> it = vcard.getEmails();
			EmailFeature f = it.next();
			assertEquals("john.doe@ibm.com", f.getEmail());
			
			List<EmailParameterType> types = f.getEmailParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(EmailParameterType.INTERNET));
			assertTrue(types.contains(EmailParameterType.HOME));
			
			assertFalse(it.hasNext());
		}
		
		//TEL
		{
			Iterator<TelephoneFeature> it = vcard.getTelephoneNumbers();
			
			TelephoneFeature f = it.next();
			assertEquals("905-555-1234", f.getTelephone());
			
			List<TelephoneParameterType> types = f.getTelephoneParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelephoneParameterType.CELL));

			f = it.next();
			assertEquals("905-666-1234", f.getTelephone());
			
			types = f.getTelephoneParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelephoneParameterType.HOME));

			assertFalse(it.hasNext());
		}
		
		//ADR
		{
			Iterator<AddressFeature> it = vcard.getAddresses();
			AddressFeature f = it.next();
			assertEquals("", f.getPostOfficeBox());
			assertEquals("Crescent moon drive\n555-asd\nNice Area, Albaney, New York12345\nUnited States of America", f.getExtendedAddress());
			assertEquals("", f.getStreetAddress());
			assertEquals("", f.getLocality());
			assertEquals("", f.getRegion());
			assertEquals("", f.getPostalCode());
			assertEquals("", f.getCountryName());
			
			List<AddressParameterType> types = f.getAddressParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(AddressParameterType.HOME));
			
			assertFalse(it.hasNext());
		}
		
		//ORG
		{
			OrganizationFeature f = vcard.getOrganizations();
			Iterator<String> it = f.getOrganizations();
			assertEquals("IBM", it.next());
			assertFalse(it.hasNext());
		}
		
		//TITLE
		{
			TitleFeature f = vcard.getTitle();
			assertEquals("Money Counter", f.getTitle());
		}
		
		//BDAY
		{
			BirthdayFeature f = vcard.getBirthDay();
			assertEquals(1980, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.MARCH, f.getBirthday().get(Calendar.MONTH));
			assertEquals(22, f.getBirthday().get(Calendar.DAY_OF_MONTH));
		}
		
		//URL
		{
			Iterator<URLFeature> it = vcard.getURLs();
			URLFeature f = it.next();
			assertEquals("http://www.ibm.com", f.getURL().toString());
			
			List<URLParameterType> types = f.getURLParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(URLParameterType.WORK));

			assertFalse(it.hasNext());
		}

		//NOTE
		{
			Iterator<NoteFeature> it = vcard.getNotes();
			NoteFeature f = it.next();
			assertEquals("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \\\"AS IS\\\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\nFavotire Color: Blue", f.getNote());
			assertFalse(it.hasNext());
		}
		
		//custom types
		{
			Iterator<ExtendedFeature> it = vcard.getExtendedTypes();
			
			ExtendedFeature f = it.next();
			assertEquals("X-PHONETIC-FIRST-NAME", f.getExtensionName());
			assertEquals("Jon", f.getExtensionData());
			
			f = it.next();
			assertEquals("X-PHONETIC-LAST-NAME", f.getExtensionName());
			assertEquals("Dow", f.getExtensionData());

			f = it.next();
			assertEquals("item1", f.getGroup());
			assertEquals("X-ABDATE", f.getExtensionName());
			assertEquals("1975-03-01", f.getExtensionData());
			
			f = it.next();
			assertEquals("item1", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtensionName());
			assertEquals("_$!<Anniversary>!$_", f.getExtensionData());

			f = it.next();
			assertEquals("item2", f.getGroup());
			assertEquals("X-ABRELATEDNAMES", f.getExtensionName());
			assertEquals("Jenny", f.getExtensionData());
			
			f = it.next();
			assertEquals("item2", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtensionName());
			assertEquals("_$!<Spouse>!$_", f.getExtensionData());
			
			assertFalse(it.hasNext());
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
	public void testIPhoneVCard() throws IOException {
		File iphoneCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_IPHONE.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.I_PHONE);
		VCard vcard = engine.parse(iphoneCard);
		
		//VERSION
		assertEquals(VCardVersion.V3_0, vcard.getVersion().getVersion());
		
		//PRODID
		{
			ProductIdFeature f = vcard.getProductId();
			assertEquals("-//Apple Inc.//iOS 5.0.1//EN", f.getProductId());
		}
		
		//N
		{
			NameFeature f = vcard.getName();
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			Iterator<String> it = f.getAdditionalNames();
			assertEquals("Richter", it.next());
			assertEquals("James", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificPrefixes();
			assertEquals("Mr.", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificSuffixes();
			assertEquals("Sr.", it.next());
			assertFalse(it.hasNext());
		}
		
		//FN
		{
			FormattedNameFeature f = vcard.getFormattedName();
			assertEquals(f.getFormattedName(), "Mr. John Richter James Doe Sr.");
		}
		
		//NICKNAME
		{
			NicknameFeature f = vcard.getNicknames();
			Iterator<String> it = f.getNicknames();
			assertEquals("Johny", it.next());
			assertFalse(it.hasNext());
		}
		
		//ORG
		{
			OrganizationFeature f = vcard.getOrganizations();
			Iterator<String> it = f.getOrganizations();
			assertEquals("IBM", it.next());
			assertEquals("Accounting", it.next());
			assertFalse(it.hasNext());
		}
		
		//TITLE
		{
			TitleFeature f = vcard.getTitle();
			assertEquals("Money Counter", f.getTitle());
		}
		
		//EMAIL
		{
			Iterator<EmailFeature> it = vcard.getEmails();
			EmailFeature f = it.next();
			assertEquals("item1", f.getGroup());
			assertEquals("john.doe@ibm.com", f.getEmail());
			
			List<EmailParameterType> types = f.getEmailParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(EmailParameterType.INTERNET));
			assertTrue(types.contains(EmailParameterType.PREF));

			assertFalse(it.hasNext());
		}
		
		//TEL
		{
			Iterator<TelephoneFeature> it = vcard.getTelephoneNumbers();
			TelephoneFeature f = it.next();
			assertEquals("905-555-1234", f.getTelephone());
			List<TelephoneParameterType> types = f.getTelephoneParameterTypesList();
			assertEquals(3, types.size());
			assertTrue(types.contains(TelephoneParameterType.CELL));
			assertTrue(types.contains(TelephoneParameterType.VOICE));
			assertTrue(types.contains(TelephoneParameterType.PREF));

			f = it.next();
			assertEquals("905-666-1234", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelephoneParameterType.HOME));
			assertTrue(types.contains(TelephoneParameterType.VOICE));
			
			f = it.next();
			assertEquals("905-777-1234", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelephoneParameterType.WORK));
			assertTrue(types.contains(TelephoneParameterType.VOICE));
			
			f = it.next();
			assertEquals("905-888-1234", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelephoneParameterType.HOME));
			assertTrue(types.contains(TelephoneParameterType.FAX));
				
			f = it.next();
			assertEquals("905-999-1234", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelephoneParameterType.WORK));
			assertTrue(types.contains(TelephoneParameterType.FAX));
			
			f = it.next();
			assertEquals("905-111-1234", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelephoneParameterType.PAGER));
			
			f = it.next();
			assertEquals("905-222-1234", f.getTelephone());
			assertEquals("item2", f.getGroup());
			types = f.getTelephoneParameterTypesList();
			assertEquals(0, types.size());
			
			assertFalse(it.hasNext());
		}
		
		//ADR
		{
			Iterator<AddressFeature> it = vcard.getAddresses();
			
			AddressFeature f = it.next();
			assertEquals("item3", f.getGroup());
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Silicon Alley 5,", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("United States of America", f.getCountryName());
			
			List<AddressParameterType> types = f.getAddressParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(AddressParameterType.HOME));
			assertTrue(types.contains(AddressParameterType.PREF));
			
			f = it.next();
			assertEquals("item4", f.getGroup());
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Street4\nBuilding 6\nFloor 8", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("USA", f.getCountryName());
			
			types = f.getAddressParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(AddressParameterType.WORK));
			
			assertFalse(it.hasNext());
		}
		
		//URL
		{
			Iterator<URLFeature> it = vcard.getURLs();
			URLFeature f = it.next();
			assertEquals("item5", f.getGroup());
			assertEquals("http://www.ibm.com", f.getURL().toString());
			
			List<URLParameterType> types = f.getURLParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(URLParameterType.PREF));
			
			assertFalse(it.hasNext());
		}
		
		//BDAY
		{
			BirthdayFeature f = vcard.getBirthDay();
			assertEquals(2012, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.JUNE, f.getBirthday().get(Calendar.MONTH));
			assertEquals(6, f.getBirthday().get(Calendar.DAY_OF_MONTH));
			assertEquals(ISOFormat.ISO8601_DATE_EXTENDED, f.getISO8601Format());
		}
		
		//PHOTO
		{
			Iterator<PhotoFeature> it = vcard.getPhotos();
			
			PhotoFeature f = it.next();
			assertEquals(EncodingType.BINARY, f.getEncodingType());
			assertEquals(ImageMediaType.JPEG, f.getImageMediaType());
			assertEquals(32531, f.getPhoto().length);
		}
		
		//custom types
		{
			Iterator<ExtendedFeature> it = vcard.getExtendedTypes();
			
			ExtendedFeature f = it.next();
			assertEquals("item2", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtensionName());
			assertEquals("_$!<AssistantPhone>!$_", f.getExtensionData());

			f = it.next();
			assertEquals("item3", f.getGroup());
			assertEquals("X-ABADR", f.getExtensionName());
			assertEquals("Silicon Alley", f.getExtensionData());
			
			f = it.next();
			assertEquals("item4", f.getGroup());
			assertEquals("X-ABADR", f.getExtensionName());
			assertEquals("Street 4, Building 6,\n Floor 8\nNew York\nUSA", f.getExtensionData());
			
			f = it.next();
			assertEquals("item5", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtensionName());
			assertEquals("_$!<HomePage>!$_", f.getExtensionData());
			
			assertFalse(it.hasNext());
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
	public void testLotusNotesVCard() throws IOException {
		File lotusNotesCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_LOTUS_NOTES.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.MAC_ADDRESS_BOOK);
		VCard vcard = engine.parse(lotusNotesCard);
		
		//VERSION
		assertEquals(VCardVersion.V3_0, vcard.getVersion().getVersion());
		
		//PRODID
		{
			ProductIdFeature f = vcard.getProductId();
			assertEquals("-//Apple Inc.//Address Book 6.1//EN", f.getProductId());
		}
		
		//N
		{
			NameFeature f = vcard.getName();
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			Iterator<String> it = f.getAdditionalNames();
			assertEquals("Johny", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificPrefixes();
			assertEquals("Mr.", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificSuffixes();
			assertEquals("I", it.next());
			assertFalse(it.hasNext());
		}
		
		//FN
		{
			FormattedNameFeature f = vcard.getFormattedName();
			assertEquals(f.getFormattedName(), "Mr. Doe John I Johny");
		}
		
		//NICKNAME
		{
			NicknameFeature f = vcard.getNicknames();
			Iterator<String> it = f.getNicknames();
			assertEquals("Johny,JayJay", it.next());
			assertFalse(it.hasNext());
		}
		
		//ORG
		{
			OrganizationFeature f = vcard.getOrganizations();
			Iterator<String> it = f.getOrganizations();
			assertEquals("IBM", it.next());
			assertEquals("SUN", it.next());
			assertFalse(it.hasNext());
		}
		
		//TITLE
		{
			TitleFeature f = vcard.getTitle();
			assertEquals("Generic Accountant", f.getTitle());
		}
		
		//EMAIL
		{
			Iterator<EmailFeature> it = vcard.getEmails();
			
			EmailFeature f = it.next();
			assertEquals("john.doe@ibm.com", f.getEmail());
			List<EmailParameterType> types = f.getEmailParameterTypesList();
			assertEquals(3, types.size());
			assertTrue(types.contains(EmailParameterType.INTERNET));
			assertTrue(types.contains(EmailParameterType.WORK));
			assertTrue(types.contains(EmailParameterType.PREF));
			
			f = it.next();
			assertEquals("billy_bob@gmail.com", f.getEmail());
			types = f.getEmailParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(EmailParameterType.INTERNET));
			assertTrue(types.contains(EmailParameterType.WORK));

			assertFalse(it.hasNext());
		}
		
		//TEL
		{
			Iterator<TelephoneFeature> it = vcard.getTelephoneNumbers();
			TelephoneFeature f = it.next();
			assertEquals("+1 (212) 204-34456", f.getTelephone());
			List<TelephoneParameterType> types = f.getTelephoneParameterTypesList();
			assertEquals(3, types.size());
			assertTrue(types.contains(TelephoneParameterType.CELL));
			assertTrue(types.contains(TelephoneParameterType.VOICE));
			assertTrue(types.contains(TelephoneParameterType.PREF));

			f = it.next();
			assertEquals("00-1-212-555-7777", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelephoneParameterType.WORK));
			assertTrue(types.contains(TelephoneParameterType.FAX));
			
			assertFalse(it.hasNext());
		}
		
		//ADR
		{
			Iterator<AddressFeature> it = vcard.getAddresses();
			
			AddressFeature f = it.next();
			assertEquals("item1", f.getGroup());
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("25334\nSouth cresent drive, Building 5, 3rd floo r", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("NYC887", f.getPostalCode());
			assertEquals("U.S.A.", f.getCountryName());
			
			List<AddressParameterType> types = f.getAddressParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(AddressParameterType.HOME));
			assertTrue(types.contains(AddressParameterType.PREF));
			
			assertFalse(it.hasNext());
		}
		
		//NOTE
		{
			Iterator<NoteFeature> it = vcard.getNotes();
			NoteFeature f = it.next();
			assertEquals("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\"\nAND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO , THE\nIMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR P URPOSE\nARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTOR S BE\nLIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR\nCONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF\n SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS \nINTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN\n CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)\nA RISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE\n POSSIBILITY OF SUCH DAMAGE.", f.getNote());
			assertFalse(it.hasNext());
		}
		
		//URL
		{
			Iterator<URLFeature> it = vcard.getURLs();
			URLFeature f = it.next();
			assertEquals("item2", f.getGroup());
			assertEquals("http://www.sun.com", f.getURL().toString());
			
			List<URLParameterType> types = f.getURLParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(URLParameterType.PREF));
			
			assertFalse(it.hasNext());
		}
		
		//BDAY
		{
			BirthdayFeature f = vcard.getBirthDay();
			assertEquals(1980, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.MAY, f.getBirthday().get(Calendar.MONTH));
			assertEquals(21, f.getBirthday().get(Calendar.DAY_OF_MONTH));
			assertEquals(ISOFormat.ISO8601_DATE_EXTENDED, f.getISO8601Format());
		}
		
		//PHOTO
		{
			Iterator<PhotoFeature> it = vcard.getPhotos();
			
			PhotoFeature f = it.next();
			assertEquals(EncodingType.BINARY, f.getEncodingType());
			assertEquals(ImageMediaType.JPEG, f.getImageMediaType());
			assertEquals(7957, f.getPhoto().length);
		}
		
		//UID
		{
			UIDFeature f = vcard.getUID();
			assertEquals("0e7602cc-443e-4b82-b4b1-90f62f99a199", f.getUID());
		}
		
		//GEO
		{
			GeographicPositionFeature f = vcard.getGeographicPosition();
			assertEquals(-2.6, f.getLatitude(), .01);
			assertEquals(3.4, f.getLongitude(), .01);
		}
		
		//CLASS
		{
			ClassFeature f = vcard.getSecurityClass();
			assertEquals("Public", f.getSecurityClass());
		}
		
		//PROFILE
		{
			ProfileFeature f = vcard.getProfile();
			assertEquals("VCard", f.getProfile());
		}
		
		//TZ
		{
			TimeZoneFeature f = vcard.getTimeZone();
			assertEquals(1, f.getHourOffset());
			assertEquals(0, f.getMinuteOffset());
		}
		
		//LABEL
		{
			Iterator<LabelFeature> it = vcard.getLables();
			
			//FIXME does not parse this LABEL
			/*
			LabelFeature f = it.next();
			assertEquals("John Doe\nNew York, NewYork,\nSouth Crecent Drive,\nBuilding 5, floor 3,\nUSA", f.getLabel());
			List<LabelParameterType> types = f.getLabelParameterTypesList();
			assertEquals(3, types.size());
			assertTrue(types.contains(LabelParameterType.HOME));
			assertTrue(types.contains(LabelParameterType.PARCEL));
			assertTrue(types.contains(LabelParameterType.PREF));
			*/
			
			assertFalse(it.hasNext());
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
			DisplayableNameFeature f = vcard.getDisplayableNameFeature();
			assertEquals("VCard for John Doe", f.getName());
		}
		
		//custom types
		{
			Iterator<ExtendedFeature> it = vcard.getExtendedTypes();
			
			ExtendedFeature f = it.next();
			assertEquals("item2", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtensionName());
			assertEquals("_$!<HomePage>!$_", f.getExtensionData());

			f = it.next();
			assertEquals("X-ABUID", f.getExtensionName());
			assertEquals("0E7602CC-443E-4B82-B4B1-90F62F99A199:ABPerson", f.getExtensionData());
			
			f = it.next();
			assertEquals("X-GENERATOR", f.getExtensionName());
			assertEquals("Cardme Generator", f.getExtensionData());
			
			f = it.next();
			assertEquals("X-LONG-STRING", f.getExtensionName());
			assertEquals("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", f.getExtensionData());
			
			assertFalse(it.hasNext());
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
	public void testMsOutlookVCard() throws IOException {
		File msOutlookCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_MS_OUTLOOK.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.MS_OUTLOOK);
		VCard vcard = engine.parse(msOutlookCard);
		
		//VERSION
		assertEquals(VCardVersion.V2_1, vcard.getVersion().getVersion());
		
		//N
		{
			NameFeature f = vcard.getName();
			assertEquals(LanguageType.EN_US, f.getLanguage());
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			Iterator<String> it = f.getAdditionalNames();
			assertEquals("Richter", it.next());
			assertEquals("James", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificPrefixes();
			assertEquals("Mr.", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificSuffixes();
			assertEquals("Sr.", it.next());
			assertFalse(it.hasNext());
		}
		
		//FN
		{
			FormattedNameFeature f = vcard.getFormattedName();
			assertEquals(f.getFormattedName(), "Mr. John Richter James Doe Sr.");
		}
		
		//NICKNAME
		{
			NicknameFeature f = vcard.getNicknames();
			Iterator<String> it = f.getNicknames();
			assertEquals("Johny", it.next());
			assertFalse(it.hasNext());
		}
		
		//ORG
		{
			OrganizationFeature f = vcard.getOrganizations();
			Iterator<String> it = f.getOrganizations();
			assertEquals("IBM", it.next());
			assertEquals("Accounting", it.next());
			assertFalse(it.hasNext());
		}
		
		//TITLE
		{
			TitleFeature f = vcard.getTitle();
			assertEquals("Money Counter", f.getTitle());
		}
		
		//NOTE
		{
			Iterator<NoteFeature> it = vcard.getNotes();
			NoteFeature f = it.next();
			assertEquals("THIS SOFTWARE IS PROVIDED BY GEORGE EL-HADDAD ''AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL GEORGE EL-HADDAD OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.", f.getNote());
			assertFalse(it.hasNext());
		}
		
		//TEL
		{
			Iterator<TelephoneFeature> it = vcard.getTelephoneNumbers();
			TelephoneFeature f = it.next();
			assertEquals("(905) 555-1234", f.getTelephone());
			List<TelephoneParameterType> types = f.getTelephoneParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelephoneParameterType.WORK));
			assertTrue(types.contains(TelephoneParameterType.VOICE));

			f = it.next();
			assertEquals("(905) 666-1234", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelephoneParameterType.HOME));
			assertTrue(types.contains(TelephoneParameterType.VOICE));

			assertFalse(it.hasNext());
		}
		
		//ADR
		{
			Iterator<AddressFeature> it = vcard.getAddresses();
			
			AddressFeature f = it.next();
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Cresent moon drive", f.getStreetAddress());
			assertEquals("Albaney", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("United States of America", f.getCountryName());
			
			List<AddressParameterType> types = f.getAddressParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(AddressParameterType.WORK));
			assertTrue(types.contains(AddressParameterType.PREF));
			
			//FIXME the second ADR type is not parsed (it is incorrectly included as part of the value of the LABEL above it)
			/*
			f = it.next();
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Silicon Alley 5,", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("United States of America", f.getCountryName());
			
			types = f.getAddressParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(AddressParameterType.HOME));
			*/
			
			assertFalse(it.hasNext());
		}
		
		//LABEL
		{
			Iterator<LabelFeature> it = vcard.getLables();

			LabelFeature f = it.next();
			//FIXME the ADR type that comes after this LABEL gets included in the LABEL's value
			//assertEquals("Cresent moon drive\r\nAlbaney, New York  1234", f.getLabel());
			assertEquals("Cresent moon drive\r\nAlbaney, New York  12345ADR;HOME:;;Silicon Alley 5,;New York;New York;12345;United States of America", f.getLabel());
			
			List<LabelParameterType> types = f.getLabelParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(LabelParameterType.WORK));
			assertTrue(types.contains(LabelParameterType.PREF));
			
			//FIXME the second LABEL is not parsed
			/*
			f = it.next();
			assertEquals("Silicon Alley 5,\r\nNew York, New York  12345", f.getLabel());
			types = f.getLabelParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(LabelParameterType.HOME));
			*/
			
			assertFalse(it.hasNext());
		}
		
		//URL
		{
			Iterator<URLFeature> it = vcard.getURLs();
			URLFeature f = it.next();
			assertEquals("http://www.ibm.com", f.getURL().toString());
			
			List<URLParameterType> types = f.getURLParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(URLParameterType.WORK));
			
			assertFalse(it.hasNext());
		}
		
		//ROLE
		{
			RoleFeature f = vcard.getRole();
			assertEquals("Counting Money", f.getRole());
		}
		
		//BDAY
		{
			BirthdayFeature f = vcard.getBirthDay();
			assertEquals(1980, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.MARCH, f.getBirthday().get(Calendar.MONTH));
			assertEquals(22, f.getBirthday().get(Calendar.DAY_OF_MONTH));
		}
		
		//EMAIL
		{
			Iterator<EmailFeature> it = vcard.getEmails();
			EmailFeature f = it.next();
			assertEquals("john.doe@ibm.cm", f.getEmail());
			
			List<EmailParameterType> types = f.getEmailParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(EmailParameterType.PREF));
			assertTrue(types.contains(EmailParameterType.INTERNET));

			assertFalse(it.hasNext());
		}

		//PHOTO
		{
			Iterator<PhotoFeature> it = vcard.getPhotos();
			
			PhotoFeature f = it.next();
			assertEquals(EncodingType.BINARY, f.getEncodingType());
			assertEquals(ImageMediaType.JPEG, f.getImageMediaType());
			FileOutputStream out = new FileOutputStream("temp.jpg");
			out.write(f.getPhoto());
			out.close();
			assertEquals(860, f.getPhoto().length);
		}
		
		//REV
		{
			RevisionFeature f = vcard.getRevision();
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
			Iterator<ExtendedFeature> it = vcard.getExtendedTypes();
			
			ExtendedFeature f;
			
			//FIXME The "X-MS-OL-DEFAULT-POSTAL-ADDRESS" type is not parsed (it is incorrectly included as part of the value of the LABEL above it)
			/*
			f = it.next();
			assertEquals("X-MS-OL-DEFAULT-POSTAL-ADDRESS", f.getExtensionName());
			assertEquals("2", f.getExtensionData());
			*/
			
			f = it.next();
			assertEquals("X-MS-ANNIVERSARY", f.getExtensionName());
			assertEquals("20110113", f.getExtensionData());
			
			f = it.next();
			assertEquals("X-MS-IMADDRESS", f.getExtensionName());
			assertEquals("johny5@aol.com", f.getExtensionData());

			f = it.next();
			assertEquals("X-MS-OL-DESIGN", f.getExtensionName());
			assertEquals("<card xmlns=\"http://schemas.microsoft.com/office/outlook/12/electronicbusinesscards\" ver=\"1.0\" layout=\"left\" bgcolor=\"ffffff\"><img xmlns=\"\" align=\"tleft\" area=\"32\" use=\"photo\"/><fld xmlns=\"\" prop=\"name\" align=\"left\" dir=\"ltr\" style=\"b\" color=\"000000\" size=\"10\"/><fld xmlns=\"\" prop=\"org\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"title\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"dept\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"telwork\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"><label align=\"right\" color=\"626262\">Work</label></fld><fld xmlns=\"\" prop=\"telhome\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"><label align=\"right\" color=\"626262\">Home</label></fld><fld xmlns=\"\" prop=\"email\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"addrwork\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"addrhome\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"webwork\" align=\"left\" dir=\"ltr\" color=\"000000\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/><fld xmlns=\"\" prop=\"blank\" size=\"8\"/></card>", f.getExtensionData());
			assertEquals(Charset.forName("UTF-8"), f.getCharset());
			
			f = it.next();
			assertEquals("X-MS-MANAGER", f.getExtensionName());
			assertEquals("Big Blue", f.getExtensionData());
			
			f = it.next();
			assertEquals("X-MS-ASSISTANT", f.getExtensionName());
			assertEquals("Jenny", f.getExtensionData());
			
			assertFalse(it.hasNext());
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
	public void testMacAddressBookVCard() throws IOException {
		File macAddressBookCard = new File(System.getProperty("user.dir")+File.separator+"test"+File.separator+"vcards"+File.separator+"John_Doe_MAC_ADDRESS_BOOK.vcf");
		
		VCardEngine engine = new VCardEngine();
		engine.setCompatibilityMode(CompatibilityMode.MAC_ADDRESS_BOOK);
		VCard vcard = engine.parse(macAddressBookCard);
		
		//VERSION
		assertEquals(VCardVersion.V3_0, vcard.getVersion().getVersion());
		
		//N
		{
			NameFeature f = vcard.getName();
			assertEquals("Doe", f.getFamilyName());
			assertEquals("John", f.getGivenName());
			Iterator<String> it = f.getAdditionalNames();
			//FIXME escaped commas should not be considered delimiters
			//assertEquals("Richter,James", it.next());
			assertEquals("Richter\\", it.next());
			assertEquals("James", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificPrefixes();
			assertEquals("Mr.", it.next());
			assertFalse(it.hasNext());
			it = f.getHonorificSuffixes();
			assertEquals("Sr.", it.next());
			assertFalse(it.hasNext());
		}
		
		//FN
		{
			FormattedNameFeature f = vcard.getFormattedName();
			assertEquals(f.getFormattedName(), "Mr. John Richter,James Doe Sr.");
		}
		
		//NICKNAME
		{
			NicknameFeature f = vcard.getNicknames();
			Iterator<String> it = f.getNicknames();
			assertEquals("Johny", it.next());
			assertFalse(it.hasNext());
		}
		
		//ORG
		{
			OrganizationFeature f = vcard.getOrganizations();
			Iterator<String> it = f.getOrganizations();
			assertEquals("IBM", it.next());
			assertEquals("Accounting", it.next());
			assertFalse(it.hasNext());
		}
		
		//TITLE
		{
			TitleFeature f = vcard.getTitle();
			assertEquals("Money Counter", f.getTitle());
		}
		
		//EMAIL
		{
			Iterator<EmailFeature> it = vcard.getEmails();
			EmailFeature f = it.next();
			assertEquals("john.doe@ibm.com", f.getEmail());
			
			List<EmailParameterType> types = f.getEmailParameterTypesList();
			assertEquals(3, types.size());
			assertTrue(types.contains(EmailParameterType.INTERNET));
			assertTrue(types.contains(EmailParameterType.WORK));
			assertTrue(types.contains(EmailParameterType.PREF));

			assertFalse(it.hasNext());
		}
		
		//TEL
		{
			Iterator<TelephoneFeature> it = vcard.getTelephoneNumbers();
			TelephoneFeature f = it.next();
			assertEquals("905-777-1234", f.getTelephone());
			List<TelephoneParameterType> types = f.getTelephoneParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelephoneParameterType.WORK));
			assertTrue(types.contains(TelephoneParameterType.PREF));

			f = it.next();
			assertEquals("905-666-1234", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelephoneParameterType.HOME));
			
			f = it.next();
			assertEquals("905-555-1234", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelephoneParameterType.CELL));
			
			f = it.next();
			assertEquals("905-888-1234", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelephoneParameterType.HOME));
			assertTrue(types.contains(TelephoneParameterType.FAX));
				
			f = it.next();
			assertEquals("905-999-1234", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(TelephoneParameterType.WORK));
			assertTrue(types.contains(TelephoneParameterType.FAX));
			
			f = it.next();
			assertEquals("905-111-1234", f.getTelephone());
			types = f.getTelephoneParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(TelephoneParameterType.PAGER));
			
			f = it.next();
			assertEquals("905-222-1234", f.getTelephone());
			assertEquals("item1", f.getGroup());
			types = f.getTelephoneParameterTypesList();
			assertEquals(0, types.size());
			
			assertFalse(it.hasNext());
		}
		
		//ADR
		{
			Iterator<AddressFeature> it = vcard.getAddresses();
			
			AddressFeature f = it.next();
			assertEquals("item2", f.getGroup());
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Silicon Alley 5,", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("New York", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("United States of America", f.getCountryName());
			
			List<AddressParameterType> types = f.getAddressParameterTypesList();
			assertEquals(2, types.size());
			assertTrue(types.contains(AddressParameterType.HOME));
			assertTrue(types.contains(AddressParameterType.PREF));
			
			f = it.next();
			assertEquals("item3", f.getGroup());
			assertEquals("", f.getPostOfficeBox());
			assertEquals("", f.getExtendedAddress());
			assertEquals("Street4\nBuilding 6\nFloor 8", f.getStreetAddress());
			assertEquals("New York", f.getLocality());
			assertEquals("", f.getRegion());
			assertEquals("12345", f.getPostalCode());
			assertEquals("USA", f.getCountryName());
			
			types = f.getAddressParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(AddressParameterType.WORK));
			
			assertFalse(it.hasNext());
		}
		
		//NOTE
		{
			Iterator<NoteFeature> it = vcard.getNotes();
			NoteFeature f = it.next();
			assertEquals("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \\\"AS IS\\\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\nFavotire Color: Blue", f.getNote());
			assertFalse(it.hasNext());
		}
		
		//URL
		{
			Iterator<URLFeature> it = vcard.getURLs();
			URLFeature f = it.next();
			assertEquals("item4", f.getGroup());
			assertEquals("http://www.ibm.com", f.getURL().toString());
			
			List<URLParameterType> types = f.getURLParameterTypesList();
			assertEquals(1, types.size());
			assertTrue(types.contains(URLParameterType.PREF));
			
			assertFalse(it.hasNext());
		}
		
		//BDAY
		{
			BirthdayFeature f = vcard.getBirthDay();
			assertEquals(2012, f.getBirthday().get(Calendar.YEAR));
			assertEquals(Calendar.JUNE, f.getBirthday().get(Calendar.MONTH));
			assertEquals(6, f.getBirthday().get(Calendar.DAY_OF_MONTH));
			assertEquals(ISOFormat.ISO8601_DATE_EXTENDED, f.getISO8601Format());
		}
		
		//PHOTO
		{
			Iterator<PhotoFeature> it = vcard.getPhotos();
			
			PhotoFeature f = it.next();
			assertEquals(EncodingType.BINARY, f.getEncodingType());
			assertEquals(null, f.getImageMediaType());
			assertEquals(18242, f.getPhoto().length);
		}
		
		//custom types
		{
			Iterator<ExtendedFeature> it = vcard.getExtendedTypes();
			
			ExtendedFeature f = it.next();
			assertEquals("X-PHONETIC-FIRST-NAME", f.getExtensionName());
			assertEquals("Jon", f.getExtensionData());
			
			f = it.next();
			assertEquals("X-PHONETIC-LAST-NAME", f.getExtensionName());
			assertEquals("Dow", f.getExtensionData());
			
			f = it.next();
			assertEquals("item1", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtensionName());
			assertEquals("AssistantPhone", f.getExtensionData());

			f = it.next();
			assertEquals("item2", f.getGroup());
			assertEquals("X-ABADR", f.getExtensionName());
			assertEquals("Silicon Alley", f.getExtensionData());
			
			f = it.next();
			assertEquals("item3", f.getGroup());
			assertEquals("X-ABADR", f.getExtensionName());
			assertEquals("Street 4, Building 6,\nFloor 8\nNew York\nUSA", f.getExtensionData());
			
			f = it.next();
			assertEquals("item4", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtensionName());
			assertEquals("_$!<HomePage>!$_", f.getExtensionData());
			
			f = it.next();
			assertEquals("item5", f.getGroup());
			assertEquals("X-ABRELATEDNAMES", f.getExtensionName());
			assertEquals("Jenny", f.getExtensionData());
			List<ExtendedParameterType> xlist = f.getExtendedParametersList();
			assertEquals(1, xlist.size());
			assertEquals("TYPE", xlist.get(0).getXtendedTypeName());
			assertEquals("PREF", xlist.get(0).getXtendedTypeValue());
			
			f = it.next();
			assertEquals("item5", f.getGroup());
			assertEquals("X-ABLABEL", f.getExtensionName());
			assertEquals("Spouse", f.getExtensionData());
			
			f = it.next();
			assertEquals("X-ABUID", f.getExtensionName());
			assertEquals("6B29A774-D124-4822-B8D0-2780EC117F60:ABPerson", f.getExtensionData());
			
			assertFalse(it.hasNext());
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
