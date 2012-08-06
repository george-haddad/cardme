package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.SoundFeature;
import net.sourceforge.cardme.vcard.types.media.AudioMediaType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
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
 * Oct 3, 2011
 *
 */
public class SoundTypeTest {

	private SoundType soundTypeBin = null;
	private SoundType soundTypeUri = null;
	
	@Before
	public void setUp() throws Exception {
		soundTypeBin = new SoundType();
		soundTypeBin.setSound(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05});
		soundTypeBin.setEncodingType(EncodingType.BINARY);
		soundTypeBin.setCompression(false);
		soundTypeBin.setAudioMediaType(AudioMediaType.VORBIS);
		
		soundTypeUri = new SoundType();
		soundTypeUri.setSoundURI(new URI("file://C:/my_sounds/john.ogg"));
		soundTypeUri.setEncodingType(EncodingType.EIGHT_BIT);
	}
	
	@After
	public void tearDown() throws Exception {
		soundTypeBin = null;
		soundTypeUri = null;
	}
	
	@Test
	public void testGetPhoto() throws URISyntaxException {
		byte[] soundBytes = soundTypeBin.getSound();
		assertTrue(Arrays.equals(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05}, soundBytes));
		
		URI soundUri = soundTypeUri.getSoundURI();
		assertEquals(new URI("file://C:/my_sounds/john.ogg"), soundUri);
	}
	
	@Test
	public void testIsCompression() {
		assertFalse(soundTypeBin.isSetCompression());
		assertFalse(soundTypeUri.isSetCompression());
	}
	
	@Test
	public void testGetPhotoEncodingType() {
		assertEquals(EncodingType.BINARY, soundTypeBin.getEncodingType());
		assertEquals(EncodingType.EIGHT_BIT, soundTypeUri.getEncodingType());
	}
	
	@Test
	public void testIsInline() {
		assertTrue(soundTypeBin.isInline());
		assertFalse(soundTypeUri.isInline());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(soundTypeBin.getTypeString(), VCardType.SOUND.getType());
	}
	
	@Test
	public void testEquals() throws URISyntaxException {
		SoundType soundTypeBin2 = new SoundType();
		soundTypeBin2.setSound(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05});
		soundTypeBin2.setEncodingType(EncodingType.BINARY);
		soundTypeBin2.setCompression(false);
		soundTypeBin2.setAudioMediaType(AudioMediaType.VORBIS);
		
		SoundType soundTypeUri2 = new SoundType();
		soundTypeUri2.setSoundURI(new URI("file://C:/my_sounds/john.ogg"));
		soundTypeUri2.setEncodingType(EncodingType.EIGHT_BIT);
		
		assertTrue(soundTypeBin.equals(soundTypeBin2));
		assertTrue(soundTypeUri.equals(soundTypeUri2));
	}
	
	@Test
	public void testHashcode() throws URISyntaxException {
		SoundType soundTypeBin2 = new SoundType();
		soundTypeBin2.setSound(new byte[] {0x01, 0x02, 0x03, 0x04, 0x05});
		soundTypeBin2.setEncodingType(EncodingType.BINARY);
		soundTypeBin2.setCompression(false);
		soundTypeBin2.setAudioMediaType(AudioMediaType.VORBIS);
		
		SoundType soundTypeUri2 = new SoundType();
		soundTypeUri2.setSoundURI(new URI("file://C:/my_sounds/john.ogg"));
		soundTypeUri2.setEncodingType(EncodingType.EIGHT_BIT);
		
		int hcode1 = soundTypeBin.hashCode();
		int hcode2 = soundTypeBin2.hashCode();
		assertEquals(hcode1, hcode2);
		
		hcode1 = soundTypeUri.hashCode();
		hcode2 = soundTypeUri2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		SoundFeature cloned1 = soundTypeBin.clone(); 
		SoundFeature cloned2 = soundTypeUri.clone(); 
		
		assertEquals(cloned1, soundTypeBin);
		assertTrue(soundTypeBin.equals(cloned1));
		
		assertEquals(cloned2, soundTypeUri);
		assertTrue(soundTypeUri.equals(cloned2));
	}
}
