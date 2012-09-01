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
 * Aug 8, 2012
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.5.3 LOGO Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> LOGO</li>
 * 	<li><b>Type purpose:</b> To specify a graphic image of a logo associated with the object the vCard represents.</li>
 * 	<li><b>Type encoding:</b> The encoding MUST be reset to "b" using the ENCODING parameter in order to specify inline, encoded binary data. If the value is referenced by a URI value, then the default encoding of 8bit is used and no explicit ENCODING parameter is needed.</li>
 * 	<li><b>Type value:</b> A single value. The default is binary value. It can also be reset to uri value. The uri value can be used to specify a value outside of this MIME entity.</li>
 * 	<li><b>Type special note:</b> The type can include the type parameter "TYPE" to specify the graphic image format type. The TYPE parameter values MUST be one of the IANA registered image formats or a non-standard image format.</li>
 * </ul>
 * </p>
 */
public interface LogoFeature {
	
	public byte[] getLogo();
	
	public void setLogo(byte[] logo);
	
	public boolean hasLogo();
	
	public void clearLogo();
	
	public URI getLogoURI();
	
	public void setLogoURI(URI logoUri);
	
	public boolean isURI();
	
	public void setLogoURL(URL logoUrl) throws URISyntaxException;
	
	public URL getLogoURL() throws MalformedURLException;
	
	public ImageMediaType getImageMediaType();
	
	public void setImageMediaType(ImageMediaType imageMediaType);
	
	public boolean hasImageMediaType();
}
