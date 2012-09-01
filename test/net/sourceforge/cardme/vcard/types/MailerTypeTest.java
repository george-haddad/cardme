package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.MailerFeature;
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
 * Aug 27, 2012
 *
 */
public class MailerTypeTest {

	private MailerType mailerType = null;
	
	@Before
	public void setUp() throws Exception {
		mailerType = new MailerType();
		mailerType.setMailer("X-MAILER-THUNDER-BIRD");
	}
	
	@After
	public void tearDown() throws Exception {
		mailerType = null;
	}
	
	@Test
	public void testGetMailer() {
		assertEquals("X-MAILER-THUNDER-BIRD", mailerType.getMailer());
	}
	
	@Test
	public void testSetMailer() {
		mailerType.setMailer("Eudora");
		assertEquals("Eudora", mailerType.getMailer());
	}
	
	@Test
	public void testSetMailerSecure() {
		String mailer = "Eudora";
		mailerType.setMailer(mailer);
		
		String mailerCopy = mailerType.getMailer();
		
		assertFalse(mailer == mailerCopy);
		assertTrue(mailer.compareTo(mailerCopy) == 0);
		assertTrue(mailer.equals(mailerCopy));
	}
	
	@Test
	public void testHasMailer() {
		assertTrue(mailerType.hasMailer());
	}
	
	@Test
	public void testClearMailer() {
		mailerType.clearMailer();
		assertFalse(mailerType.hasMailer());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.MAILER.getType(), mailerType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		MailerType mailerType2 = new MailerType("X-MAILER-THUNDER-BIRD");
		assertTrue(mailerType.equals(mailerType2));
	}
	
	@Test
	public void testHashcode() {
		MailerType mailerType2 = new MailerType("X-MAILER-THUNDER-BIRD");
		
		int hcode1 = mailerType.hashCode();
		int hcode2 = mailerType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		MailerType mailerType2 = new MailerType("X-MAILER-THUNDER-BIRD");
		assertTrue(mailerType.compareTo(mailerType2) == 0);
	}
	
	@Test
	public void testClone() {
		MailerFeature cloned = mailerType.clone();
		assertEquals(cloned, mailerType);
		assertTrue(mailerType.equals(cloned));
	}
}
