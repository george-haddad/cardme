package net.sourceforge.cardme.vcard.features;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import net.sourceforge.cardme.vcard.types.media.ImageMediaType;

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
 * Aug 7, 2012
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.1.4 PHOTO Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> PHOTO</li>
 * 	<li><b>Type purpose:</b> To specify an image or photograph information that annotates some aspect of the object the vCard represents.</li>
 * 	<li><b>Type encoding:</b> The encoding MUST be reset to "b" using the ENCODING parameter in order to specify inline, encoded binary data. If the value is referenced by a URI value, then the default encoding of 8bit is used and no explicit ENCODING parameter is needed.</li>
 * 	<li><b>Type value:</b> A single value. The default is binary value. It can also be reset to uri value. The uri value can be used to specify a value outside of this MIME entity.</li>
 * 	<li><b>Type special note:</b> The type can include the type parameter "TYPE" to specify the graphic image format type. The TYPE parameter values MUST be one of the IANA registered image formats or a non-standard image format.</li>
 * </ul>
 * </p>
 */
public interface PhotoFeature {
	
	/**
	 * <p>Retrieves the photo as an array of bytes.</p>
	 * 
	 * @return the photo as an array of bytes or null if none was set
	 */
	public byte[] getPhoto();
	
	/**
	 * <p>Sets the photo as an array of bytes.</p>
	 * 
	 * @param photo - the photo to be set
	 */
	public void setPhoto(byte[] photo);
	
	/**
	 * <p>Indicates whether a photo has been set; either
	 * as an array of bytes or as a {@link URI}.</p>
	 * 
	 * @return true if a photo was set or false otherwise
	 */
	public boolean hasPhoto();
	
	/**
	 * <p>This will remove the photo (array of bytes and the URI).</p>
	 */
	public void clearPhoto();
	
	/**
	 * <p>Retrieves the photo as a {@link URI} if one was set.</p>
	 * 
	 * @return the {@link URI} of the photo if one was set
	 */
	public URI getPhotoURI();
	
	/**
	 * <p>Sets the {@link URI} of the photo.</p>
	 * 
	 * @param photoUri - the {@link URI} of the photo
	 */
	public void setPhotoURI(URI photoUri);
	
	/**
	 * <p>Indicates if the photo is represented by a {@link URI}.
	 * Note that if you set the photo as bytes and as a URI then
	 * this will return true. It is recommended to set the photo
	 * as one or the other unless you know what you are doing.</p>
	 *
	 * @return true if the {@link URI} of the photo has been set
	 */
	public boolean isURI();
	
	/**
	 * <p>Sets the {@link URL} of the photo. This eventually
	 * just calls {@link URL#toURI()} internally.</p>
	 * 
	 * @param photoUrl - the {@link URL} of the photo
	 * @throws URISyntaxException if there is a syntax error in the {@link URL}
	 */
	public void setPhotoURL(URL photoUrl) throws URISyntaxException;
	
	/**
	 * <p>Retrieves the {@link URL} of the photo. This eventually
	 * just calls {@link URI#toURL()} internally.</p>
	 * 
	 * @return the {@link URL} of the photo
	 * @throws MalformedURLException if the URL is malformed
	 */
	public URL getPhotoURL() throws MalformedURLException;
	
	/**
	 * <p>Retrieves the IANA registered media type of this photo.</p>
	 *  
	 * @return the IANA registered media type
	 */
	public ImageMediaType getImageMediaType();
	
	/**
	 * <p>Sets the IANA registered media type for this photo.</p>
	 * 
	 * @param imageMediaType - the IANA registered media type
	 */
	public void setImageMediaType(ImageMediaType imageMediaType);
	
	/**
	 * <p>Indicates whether an IANA registered media type has been set.</p>
	 * 
	 * @return true if an IANA registered media type was set or false otherwise
	 */
	public boolean hasImageMediaType();
}
