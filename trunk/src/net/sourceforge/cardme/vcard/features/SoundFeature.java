package net.sourceforge.cardme.vcard.features;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import net.sourceforge.cardme.vcard.types.media.AudioMediaType;

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
 * Aug 9, 2012
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.6.6 SOUND Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> SOUND</li>
 * 	<li><b>Type purpose:</b> To specify a digital sound content information that annotates some aspect of the vCard. By default this type is used to specify the proper pronunciation of the name type value of the vCard.</li>
 * 	<li><b>Type encoding:</b> The encoding MUST be reset to "b" using the ENCODING parameter in order to specify inline, encoded binary data. If the value is referenced by a URI value, then the default encoding of 8bit is used and no explicit ENCODING parameter is needed.</li>
 * 	<li><b>Type value:</b> A single value. The default is binary value. It can also be reset to uri value. The uri value can be used to specify a value outside of this MIME entity.</li>
 * 	<li><b>Type special note:</b> The type can include the type parameter "TYPE" to specify the audio format type. The TYPE parameter values MUST be one of the IANA registered audio formats or a non-standard audio format.</li>
 * </ul>
 * </p>
 */
public interface SoundFeature {
	
	/**
	 * <p>Retrieves the sound as an array of bytes.</p>
	 * 
	 * @return the sound as an array of bytes or null if none was set
	 */
	public byte[] getSound();
	
	/**
	 * <p>Sets the sound as an array of bytes.</p>
	 * 
	 * @param sound - the sound to be set
	 */
	public void setSound(byte[] sound);
	
	/**
	 * <p>Indicates whether a sound has been set; either
	 * as an array of bytes or as a {@link URI}.</p>
	 * 
	 * @return true if a sound was set or false otherwise
	 */
	public boolean hasSound();
	
	/**
	 * <p>This will remove the sound (array of bytes and the URI).</p>
	 */
	public void clearSound();
	
	/**
	 * <p>Retrieves the sound as a {@link URI} if one was set.</p>
	 * 
	 * @return the {@link URI} of the sound if one was set
	 */
	public URI getSoundURI();
	
	/**
	 * <p>Sets the {@link URI} of the sound.</p>
	 * 
	 * @param soundUri - the {@link URI} of the sound
	 */
	public void setSoundURI(URI soundUri);
	
	/**
	 * <p>Indicates if the sound is represented by a {@link URI}.
	 * Note that if you set the sound as bytes and as a URI then
	 * this will return true. It is recommended to set the sound
	 * as one or the other unless you know what you are doing.</p>
	 *
	 * @return true if the {@link URI} of the sound has been set
	 */
	public boolean isURI();
	
	/**
	 * <p>Sets the {@link URL} of the sound. This eventually
	 * just calls {@link URL#toURI()} internally.</p>
	 * 
	 * @param soundUrl - the {@link URL} of the sound
	 * @throws URISyntaxException if there is a syntax error in the {@link URL}
	 */
	public void setSoundURL(URL soundUrl) throws URISyntaxException;
	
	/**
	 * <p>Retrieves the {@link URL} of the sound. This eventually
	 * just calls {@link URI#toURL()} internally.</p>
	 * 
	 * @return the {@link URL} of the sound
	 * @throws MalformedURLException if the URL is malformed
	 */
	public URL getSoundURL() throws MalformedURLException;
	
	/**
	 * <p>Retrieves the IANA registered media type of this sound.</p>
	 *  
	 * @return the IANA registered media type
	 */
	public AudioMediaType getAudioMediaType();
	
	/**
	 * <p>Sets the IANA registered media type for this sound.</p>
	 * 
	 * @param imageMediaType - the IANA registered media type
	 */
	public void setAudioMediaType(AudioMediaType audioMediaType);
	
	/**
	 * <p>Indicates whether an IANA registered media type has been set.</p>
	 * 
	 * @return true if an IANA registered media type was set or false otherwise
	 */
	public boolean hasAudioMediaType();
}
