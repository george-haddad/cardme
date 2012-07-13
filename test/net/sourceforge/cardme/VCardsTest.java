package net.sourceforge.cardme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import net.sourceforge.cardme.engine.VCardEngine;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.VCardVersion;
import net.sourceforge.cardme.vcard.errors.VCardError;
import net.sourceforge.cardme.vcard.features.AddressFeature;
import net.sourceforge.cardme.vcard.features.BirthdayFeature;
import net.sourceforge.cardme.vcard.features.CategoriesFeature;
import net.sourceforge.cardme.vcard.features.EmailFeature;
import net.sourceforge.cardme.vcard.features.FormattedNameFeature;
import net.sourceforge.cardme.vcard.features.NameFeature;
import net.sourceforge.cardme.vcard.features.NicknameFeature;
import net.sourceforge.cardme.vcard.features.NoteFeature;
import net.sourceforge.cardme.vcard.features.OrganizationFeature;
import net.sourceforge.cardme.vcard.features.RevisionFeature;
import net.sourceforge.cardme.vcard.features.TelephoneFeature;
import net.sourceforge.cardme.vcard.features.TitleFeature;
import net.sourceforge.cardme.vcard.features.UIDFeature;
import net.sourceforge.cardme.vcard.features.URLFeature;
import net.sourceforge.cardme.vcard.types.parameters.AddressParameterType;
import net.sourceforge.cardme.vcard.types.parameters.EmailParameterType;
import net.sourceforge.cardme.vcard.types.parameters.ExtendedParameterType;
import net.sourceforge.cardme.vcard.types.parameters.TelephoneParameterType;

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
			assertEquals("Richter\\", it.next()); //FIXME does not properly unescape commas
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
		
		//TODO custom types
		
		
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
