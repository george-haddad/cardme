package net.sourceforge.cardme.engine;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.util.Base64Wrapper;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.arch.EncodingType;
import net.sourceforge.cardme.vcard.types.KeyType;
import net.sourceforge.cardme.vcard.types.media.KeyTextType;
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
	public void testParseKeyType() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\r\n");
		sb.append("VERSION:2.1\r\n");
		sb.append("KEY;PGP:plain text key\r\n");
		sb.append("KEY;GPG;ENCODING=QUOTED-PRINTABLE:quoted printable=0D=0A=\r\n");
		sb.append(" key\r\n");
		sb.append("KEY;ENCODING=BASE64;X509:YmluYXJ5IGRhdGE=\r\n");
		sb.append("END:VCARD\r\n");

		VCardImpl vcard = (VCardImpl)vCardEngine.parse(sb.toString());

		List<KeyType> list = vcard.getKeys();

		KeyType f = list.get(0);
		assertEquals(KeyTextType.PGP, f.getKeyTextType());
		assertArrayEquals("plain text key".getBytes(), f.getKey());
		assertEquals(EncodingType.EIGHT_BIT, f.getEncodingType());

		f = list.get(1);
		assertEquals(KeyTextType.GPG, f.getKeyTextType());
		assertArrayEquals("quoted printable\r\nkey".getBytes(), f.getKey());
		assertEquals(EncodingType.EIGHT_BIT, f.getEncodingType());

		f = list.get(2);
		assertEquals(KeyTextType.X509, f.getKeyTextType());
		assertArrayEquals(Base64Wrapper.decode("YmluYXJ5IGRhdGE="), f.getKey());
		assertEquals(EncodingType.BINARY, f.getEncodingType());
	}
	
	@Test
	public void testInvalidVCard() throws Exception {
		VCardImpl vcard = (VCardImpl)vCardEngine.parse(getInvalidVCard());
		assertTrue(vcard.hasErrors());
		
		assertTrue(vcard.getBegin() != null);
		assertTrue(vcard.getVersion() != null);
		assertTrue(vcard.getN() != null);
		assertTrue(vcard.getFN() != null);
		assertTrue(vcard.getEnd() != null);
		
		assertEquals("John", vcard.getN().getGivenName());
		assertEquals("Doe", vcard.getN().getFamilyName());
		assertEquals("John Doe", vcard.getFN().getFormattedName());
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
