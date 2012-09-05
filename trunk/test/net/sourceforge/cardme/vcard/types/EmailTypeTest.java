package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.types.params.EmailParamType;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * Copyright 2012 George El-Haddad. All rights reserved.
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
 * Aug 24, 2012
 *
 */
public class EmailTypeTest {

	private EmailType emailType = null;
	
	@Before
	public void setUp() throws Exception {
		emailType = new EmailType();
		emailType.setEmail("george@company.com");
		emailType.addParam(EmailParamType.PREF).addParam(EmailParamType.INTERNET)
			  .addExtendedParam(new ExtendedParamType("X-SLOT", "GROUP-1", VCardTypeName.EMAIL))
			  .addExtendedParam(new ExtendedParamType("X-COMPANY-ACCOUNT", VCardTypeName.EMAIL));
	}
	
	@After
	public void tearDown() throws Exception {
		emailType = null;
	}
	
	@Test
	public void testGetEmail() {
		assertEquals("george@company.com", emailType.getEmail());
	}
	
	@Test
	public void testSetEmail() {
		emailType.setEmail("Test");
		assertEquals("Test", emailType.getEmail());
		assertTrue(emailType.hasEmail());
	}
	
	@Test
	public void testSetEmailNull() {
		emailType.setEmail(null);
		assertEquals(null, emailType.getEmail());
		assertFalse(emailType.hasEmail());
	}
	
	@Test
	public void testSetEmailSecure() {
		String email = "george@company.com";
		emailType.setEmail(email);
		
		String emailCopy = emailType.getEmail();
		
		assertFalse(email == emailCopy);
		assertTrue(email.compareTo(emailCopy) == 0);
		assertTrue(email.equals(emailCopy));
	}
	
	@Test
	public void testGetParams() {
		List<EmailParamType> list = emailType.getParams();
		assertEquals(EmailParamType.PREF, list.get(0));
		assertEquals(EmailParamType.INTERNET, list.get(1));
	}
	
	@Test
	public void testGetParamSize() {
		assertEquals(2, emailType.getParamSize());
	}
	
	@Test
	public void testAddParam() {
		assertFalse(emailType.containsParam(EmailParamType.EWORLD));
		emailType.addParam(EmailParamType.EWORLD);
		assertTrue(emailType.containsParam(EmailParamType.EWORLD));
	}
	
	@Test
	public void testRemoveParam() {
		assertTrue(emailType.containsParam(EmailParamType.INTERNET));
		emailType.removeParam(EmailParamType.INTERNET);
		assertFalse(emailType.containsParam(EmailParamType.INTERNET));
	}
	
	@Test
	public void testContainsParam() {
		assertTrue(emailType.containsParam(EmailParamType.PREF));
		assertTrue(emailType.containsParam(EmailParamType.INTERNET));
	}
	
	@Test
	public void testContainsAllParams() {
		List<EmailParamType> types = new ArrayList<EmailParamType>(2);
		types.add(EmailParamType.PREF);
		types.add(EmailParamType.INTERNET);
		
		assertTrue(emailType.containsAllParams(types));
	}
	
	@Test
	public void testHasParams() {
		assertTrue(emailType.hasParams());
	}
	
	@Test
	public void testClearParams() {
		assertTrue(emailType.hasParams());
		emailType.clearParams();
		assertFalse(emailType.hasParams());
	}
	
	@Test
	public void testGetExtendedParams() {
		List<ExtendedParamType> list = emailType.getExtendedParams();
		assertEquals(new ExtendedParamType("X-SLOT", "GROUP-1", VCardTypeName.EMAIL), list.get(0));
		assertEquals(new ExtendedParamType("X-COMPANY-ACCOUNT", VCardTypeName.EMAIL), list.get(1)); 
	}
	
	@Test
	public void testGetExtendedParamSize() {
		assertEquals(2, emailType.getExtendedParamSize());
	}
	
	@Test
	public void testAddExtendedParam() {
		emailType.addExtendedParam(new ExtendedParamType("TEST", VCardTypeName.EMAIL));
		assertTrue(emailType.containsExtendedParam(new ExtendedParamType("TEST", VCardTypeName.EMAIL)));
	}
	
	@Test
	public void testRemoveExtendedParam() {
		emailType.removeExtendedParam(new ExtendedParamType("X-COMPANY-ACCOUNT", VCardTypeName.EMAIL));
		assertFalse(emailType.containsExtendedParam(new ExtendedParamType("X-COMPANY-ACCOUNT", VCardTypeName.EMAIL)));
	}
	
	@Test
	public void testContainsExtendedParam() {
		assertTrue(emailType.containsExtendedParam(new ExtendedParamType("X-SLOT", "GROUP-1", VCardTypeName.EMAIL)));
		assertTrue(emailType.containsExtendedParam(new ExtendedParamType("X-COMPANY-ACCOUNT", VCardTypeName.EMAIL)));
	}
	
	@Test
	public void testContainsAllExtendedParams() {
		List<ExtendedParamType> types = new ArrayList<ExtendedParamType>(2);
		types.add(new ExtendedParamType("X-SLOT", "GROUP-1", VCardTypeName.EMAIL));
		types.add(new ExtendedParamType("X-COMPANY-ACCOUNT", VCardTypeName.EMAIL));
		
		assertTrue(emailType.containsAllExtendedParams(types));
	}
	
	@Test
	public void testHasExtendedParams() {
		assertTrue(emailType.hasExtendedParams());
	}
	
	@Test
	public void testClearExtendedParams() {
		assertTrue(emailType.hasExtendedParams());
		emailType.clearExtendedParams();
		assertFalse(emailType.hasExtendedParams());
	}
	
	@Test
	public void testHasEmail() {
		assertTrue(emailType.hasEmail());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.EMAIL.getType(), emailType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		EmailType emailType2 = new EmailType();
		emailType2.setEmail("george@company.com");
		emailType2.addParam(EmailParamType.PREF).addParam(EmailParamType.INTERNET)
			  .addExtendedParam(new ExtendedParamType("X-SLOT", "GROUP-1", VCardTypeName.EMAIL))
			  .addExtendedParam(new ExtendedParamType("X-COMPANY-ACCOUNT", VCardTypeName.EMAIL));
		
		assertTrue(emailType.equals(emailType2));
	}
	
	@Test
	public void testCompareTo() {
		EmailType emailType2 = new EmailType();
		emailType2.setEmail("george@company.com");
		emailType2.addParam(EmailParamType.PREF).addParam(EmailParamType.INTERNET)
			  .addExtendedParam(new ExtendedParamType("X-SLOT", "GROUP-1", VCardTypeName.EMAIL))
			  .addExtendedParam(new ExtendedParamType("X-COMPANY-ACCOUNT", VCardTypeName.EMAIL));
		
		assertTrue(emailType.compareTo(emailType2) == 0);
	}
	
	@Test
	public void testHashcode() {
		EmailType emailType2 = new EmailType();
		emailType2.setEmail("george@company.com");
		emailType2.addParam(EmailParamType.PREF).addParam(EmailParamType.INTERNET)
			  .addExtendedParam(new ExtendedParamType("X-SLOT", "GROUP-1", VCardTypeName.EMAIL))
			  .addExtendedParam(new ExtendedParamType("X-COMPANY-ACCOUNT", VCardTypeName.EMAIL));
		
		int hcode1 = emailType.hashCode();
		int hcode2 = emailType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		EmailType cloned = emailType.clone();
		assertEquals(cloned, emailType);
		assertTrue(emailType.equals(cloned));
		assertTrue(emailType.compareTo(cloned) == 0);
	}
}
