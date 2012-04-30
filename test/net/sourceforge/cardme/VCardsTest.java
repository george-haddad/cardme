package net.sourceforge.cardme;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sourceforge.cardme.engine.VCardEngine;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.errors.VCardError;

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
}
