package net.sourceforge.cardme.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.vcard.VCardImpl;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
 * Jul 28, 2012
 *
 */
public class VCardEngineTest {

	private static VCardEngine vCardEngine = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vCardEngine = new VCardEngine();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		vCardEngine = null;
	}
	
	@Before
	public void setUp() throws Exception {
		vCardEngine.setCompatibilityMode(CompatibilityMode.RFC2426);
	}
	
	@Test
	public void testInvalidVCard() throws Exception {
		VCardImpl vcard = (VCardImpl)vCardEngine.parse(getInvalidVCard());
		assertTrue(vcard.hasErrors());
		
		assertTrue(vcard.hasBegin());
		assertTrue(vcard.getVersion() != null);
		assertTrue(vcard.getName() != null);
		assertTrue(vcard.getFormattedName() != null);
		assertTrue(vcard.hasEnd());
		
		assertEquals("John", vcard.getName().getGivenName());
		assertEquals("Doe", vcard.getName().getFamilyName());
		assertEquals("John Doe", vcard.getFormattedName().getFormattedName());
	}
	
	private static String getInvalidVCard()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\r\n");
		sb.append("VERSION:3.0\r\n");
		sb.append("N:Doe;John;;;\r\n");
		sb.append("FN:John Doe\r\n");
		sb.append("mcg3480ucfwe<>ER<T>#$<%#>$<%^34=4wt\r\n");
		sb.append("END:VCARD\r\n");
		return sb.toString();
	}
}
