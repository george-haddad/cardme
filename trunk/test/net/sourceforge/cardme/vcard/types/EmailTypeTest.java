package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.EmailFeature;
import net.sourceforge.cardme.vcard.types.parameters.EmailParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XEmailParameterType;
import java.util.Iterator;
import java.util.List;
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
 * Oct 1, 2011
 *
 */
public class EmailTypeTest {

	private EmailType emailType = null;
	
	@Before
	public void setUp() throws Exception {
		emailType = new EmailType();
		emailType.setEmail("george@company.com");
		emailType.addEmailParameterType(EmailParameterType.PREF);
		emailType.addEmailParameterType(EmailParameterType.INTERNET);
		emailType.addExtendedEmailParameterType(new XEmailParameterType("X-SLOT", "GROUP-1"));
		emailType.addExtendedEmailParameterType(new XEmailParameterType("X-COMPANY-ACCOUNT"));
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
	public void testGetEmailParameterTypes() {
		Iterator<EmailParameterType> iter = emailType.getEmailParameterTypes();
		assertEquals(EmailParameterType.PREF, iter.next());
		assertEquals(EmailParameterType.INTERNET, iter.next());
	}
	
	@Test
	public void testGetEmailParameterTypesList() {
		List<EmailParameterType> list = emailType.getEmailParameterTypesList();
		assertEquals(EmailParameterType.PREF, list.get(0));
		assertEquals(EmailParameterType.INTERNET, list.get(1));
	}
	
	@Test
	public void testGetEmailParameterSize() {
		assertEquals(2, emailType.getEmailParameterSize());
	}
	
	@Test
	public void testAddEmailParameterType() {
		emailType.addEmailParameterType(EmailParameterType.EWORLD);
		assertTrue(emailType.containsEmailParameterType(EmailParameterType.EWORLD));
	}
	
	@Test
	public void testRemoveEmailParameterType() {
		emailType.removeEmailParameterType(EmailParameterType.INTERNET);
		assertFalse(emailType.containsEmailParameterType(EmailParameterType.INTERNET));
	}
	
	@Test
	public void testContainsEmailParameterType() {
		assertTrue(emailType.containsEmailParameterType(EmailParameterType.PREF));
		assertTrue(emailType.containsEmailParameterType(EmailParameterType.INTERNET));
	}
	
	@Test
	public void testHasEmailParameterTypes() {
		assertTrue(emailType.hasEmailParameterTypes());
	}
	
	@Test
	public void testClearEmailParameterTypes() {
		emailType.clearEmailParameterTypes();
		assertFalse(emailType.hasEmailParameterTypes());
	}
	
	@Test
	public void testGetExtendedEmailParameterTypes() {
		Iterator<XEmailParameterType> iter = emailType.getExtendedEmailParameterTypes();
		assertEquals(new XEmailParameterType("X-SLOT", "GROUP-1"), iter.next());
		assertEquals(new XEmailParameterType("X-COMPANY-ACCOUNT"), iter.next()); 
	}
	
	@Test
	public void testGetExtendedEmailParameterTypesList() {
		List<XEmailParameterType> list = emailType.getExtendedEmailParameterTypesList();
		assertEquals(new XEmailParameterType("X-SLOT", "GROUP-1"), list.get(0));
		assertEquals(new XEmailParameterType("X-COMPANY-ACCOUNT"), list.get(1)); 
	}
	
	@Test
	public void testGetExtendedEmailParameterSize() {
		assertEquals(2, emailType.getExtendedEmailParameterSize());
	}
	
	@Test
	public void testAddExtendedEmailParameterType() {
		emailType.addExtendedEmailParameterType(new XEmailParameterType("TEST"));
		assertTrue(emailType.containsExtendedEmailParameterType(new XEmailParameterType("TEST")));
	}
	
	@Test
	public void testRemoveExtendedEmailParameterType() {
		emailType.removeExtendedEmailParameterType(new XEmailParameterType("X-COMPANY-ACCOUNT"));
		assertFalse(emailType.containsExtendedEmailParameterType(new XEmailParameterType("X-COMPANY-ACCOUNT")));
	}
	
	@Test
	public void testContainsExtendedEmailParameterType() {
		assertTrue(emailType.containsExtendedEmailParameterType(new XEmailParameterType("X-SLOT", "GROUP-1")));
		assertTrue(emailType.containsExtendedEmailParameterType(new XEmailParameterType("X-COMPANY-ACCOUNT")));
	}
	
	@Test
	public void testHasExtendedEmailParameterTypes() {
		assertTrue(emailType.hasExtendedEmailParameterTypes());
	}
	
	@Test
	public void testClearExtendedEmailParameterTypes() {
		emailType.clearExtendedEmailParameterTypes();
		assertFalse(emailType.hasExtendedEmailParameterTypes());
	}
	
	@Test
	public void testHasEmail() {
		assertTrue(emailType.hasEmail());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(emailType.getTypeString(), VCardType.EMAIL.getType());
	}
	
	@Test
	public void testEquals() {
		EmailType emailType2 = new EmailType();
		emailType2.setEmail("george@company.com");
		emailType2.addEmailParameterType(EmailParameterType.PREF);
		emailType2.addEmailParameterType(EmailParameterType.INTERNET);
		emailType2.addExtendedEmailParameterType(new XEmailParameterType("X-SLOT", "GROUP-1"));
		emailType2.addExtendedEmailParameterType(new XEmailParameterType("X-COMPANY-ACCOUNT"));
		
		assertTrue(emailType.equals(emailType2));
	}
	
	@Test
	public void testHashcode() {
		EmailType emailType2 = new EmailType();
		emailType2.setEmail("george@company.com");
		emailType2.addEmailParameterType(EmailParameterType.PREF);
		emailType2.addEmailParameterType(EmailParameterType.INTERNET);
		emailType2.addExtendedEmailParameterType(new XEmailParameterType("X-SLOT", "GROUP-1"));
		emailType2.addExtendedEmailParameterType(new XEmailParameterType("X-COMPANY-ACCOUNT"));
		
		int hcode1 = emailType.hashCode();
		int hcode2 = emailType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		EmailFeature cloned = emailType.clone();
		assertEquals(cloned, emailType);
		assertTrue(emailType.equals(cloned));
	}
}
