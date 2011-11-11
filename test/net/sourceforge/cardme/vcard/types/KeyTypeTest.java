package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.KeyFeature;
import net.sourceforge.cardme.vcard.types.media.KeyTextType;
import java.util.Arrays;
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
public class KeyTypeTest {

	private KeyType keyType = null;
	
	@Before
	public void setUp() throws Exception {
		keyType = new KeyType();
		keyType.setCompression(false);
		keyType.setKeyTextType(KeyTextType.B);
		keyType.setKey(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05});
	}
	
	@After
	public void tearDown() throws Exception {
		keyType = null;
	}
	
	@Test
	public void testGetKey() {
		byte[] keyBytes = keyType.getKey();
		assertTrue(Arrays.equals(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05}, keyBytes));
	}
	
	@Test
	public void testIsCompression() {
		assertFalse(keyType.isSetCompression());
	}
	
	@Test
	public void testGetKeyTextType() {
		assertEquals(KeyTextType.B, keyType.getKeyTextType());
	}
	
	@Test
	public void testIsInline() {
		assertTrue(keyType.isInline());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(keyType.getTypeString(), VCardType.KEY.getType());
	}
	
	@Test
	public void testEquals() {
		KeyType keyType2 = new KeyType();
		keyType2.setCompression(false);
		keyType2.setKeyTextType(KeyTextType.B);
		keyType2.setKey(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05});
		
		assertTrue(keyType.equals(keyType2));
	}
	
	@Test
	public void testHashcode() {
		KeyType keyType2 = new KeyType();
		keyType2.setCompression(false);
		keyType2.setKeyTextType(KeyTextType.B);
		keyType2.setKey(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05});
		
		int hcode1 = keyType.hashCode();
		int hcode2 = keyType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		KeyFeature cloned = keyType.clone();
		assertEquals(cloned, keyType);
		assertTrue(keyType.equals(cloned));
	}
}
