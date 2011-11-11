package info.ineighborhood.cardme.vcard.features;

import info.ineighborhood.cardme.vcard.EncodingType;
import info.ineighborhood.cardme.vcard.types.media.ImageMediaType;
import info.ineighborhood.cardme.vcard.types.parameters.PhotoParameterType;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
 * Feb 4, 2010
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
public interface PhotoFeature extends TypeTools, TypeData {
	
	/**
	 * <p>Returns the photo as an array of bytes.</p>
	 *
	 * @return byte[]
	 */
	public byte[] getPhoto();
	
	/**
	 * <p>Returns the photo URI.</p>
	 *
	 * @return {@link URI}
	 */
	public URI getPhotoURI();
	
	/**
	 * <p>Returns the photo's URI as a URL.</p>
	 *
	 * @return {@link URL}
	 * @throws MalformedURLException
	 */
	public URL getPhotoURL() throws MalformedURLException;
	
	/**
	 * <p>Returns the encoding type.</p>
	 *
	 * @see EncodingType
	 * @return {@link EncodingType}
	 */
	public EncodingType getEncodingType();
	
	/**
	 * <p>Returns the parameter type of the photo.</p>
	 *
	 * @return {@link PhotoParameterType}
	 */
	public PhotoParameterType getPhotoParameterType();
	
	/**
	 * <p>Returns the image format of the photo.</p>
	 *
	 * @return {@link ImageMediaType}
	 */
	public ImageMediaType getImageMediaType();
	
	/**
	 * <p>Sets the photo.</p>
	 *
	 * @param photoBytes
	 */
	public void setPhoto(byte[] photoBytes);
	
	/**
	 * <p>Sets the photo URI.</p>
	 *
	 * @param photoUri
	 */
	public void setPhotoURI(URI photoUri);
	
	/**
	 * <p>Sets the photo's URL.</p>
	 *
	 * @param photoUrl
	 * @throws URISyntaxException
	 */
	public void setPhotoURL(URL photoUrl) throws URISyntaxException;
	
	/**
	 * <p>Sets the encoding type.</p>
	 *
	 * @param encodingType
	 */
	public void setEncodingType(EncodingType encodingType);
	
	/**
	 * <p>Returns true if a photo exists, URI or in-line data.</p>
	 *
	 * @return boolean
	 */
	public boolean hasPhoto();
	
	/**
	 * <p>Returns true if the photo has a URI.</p>
	 *
	 * @return boolean
	 */
	public boolean isURI();
	
	/**
	 * <p>Returns true if the photo has in-line data.</p>
	 *
	 * @return boolean
	 */
	public boolean isInline();
	
	/**
	 * <p>Sets the parameter type.</p>
	 *
	 * @param photoParameterType
	 */
	public void setPhotoParameterType(PhotoParameterType photoParameterType);
	
	/**
	 * <p>Sets the image format type.</p>
	 *
	 * @param imageMediaType
	 */
	public void setImageMediaType(ImageMediaType imageMediaType);
	
	/**
	 * <p>Returns true if this photo has parameter types.</p>
	 *
	 * @return boolean
	 */
	public boolean hasPhotoParameterType();
	
	/**
	 * <p>Returns true if this photo has a format type.</p>
	 *
	 * @return boolean
	 */
	public boolean hasImageMediaType();
	
	/**
	 * <p>Returns a full copy of this object.</p>
	 *
	 * @return {@link PhotoFeature}
	 */
	public PhotoFeature clone();
}
