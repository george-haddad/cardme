package net.sourceforge.cardme.vcard.features;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.cardme.vcard.types.parameters.URLParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XURLParameterType;

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
 * Feb 4, 2010
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.6.8 URL Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> URL</li>
 * 	<li><b>Type purpose:</b> To specify a uniform resource locator associated with the object that the vCard refers to.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single uri value.</li>
 * 	<li><b>Type special note:</b> None</li>
 * </ul>
 * </p>
 */
public interface URLFeature extends TypeTools {

	/**
	 * <p>Returns the URL.</p>
	 *
	 * @return {@link URL}
	 */
	public URL getURL();
	
	/**
	 * <p>Returns the raw URL string.</p>
	 *
	 * @return {@link String}
	 */
	public String getRawURL();
	
	/**
	 * <p>Sets the URL and extract the specs
	 * and set it as the raw url..</p>
	 *
	 * @param url
	 */
	public void setURL(URL url);
	
	/**
	 * <p>Sets the raw URL string, this will
	 * also attempt to set the URL object, if
	 * the raw URL string is malformed then the
	 * URL object will remain null.</p>
	 *
	 * @param rawURL
	 */
	public void setRawURL(String rawURL);
	
	/**
	 * <p>Clears the URL and raw URL.</p>
	 */
	public void clearURL();
	
	/**
	 * <p>Returns true if there is a URL.</p>
	 *
	 * @return boolean
	 */
	public boolean hasURL();
	
	/**
	 * <p>Returns true if there is a raw URL.</p>
	 *
	 * @return boolean
	 */
	public boolean hasRawURL();
	
	/**
	 * <p>Returns an iterator all parameter types.</p>
	 *
	 * @return {@link Iterator}&lt;URLParameterType&gt;
	 */
	public Iterator<URLParameterType> getURLParameterTypes();
	
	/**
	 * <p>Returns an unmodifiable list of parameter types.</p>
	 *
	 * @return {@link List}&lt;URLParameterType&gt;
	 */
	public List<URLParameterType> getURLParameterTypesList();
	
	/**
	 * <p>Returns the number of parameter types.</p>
	 *
	 * @return int
	 */
	public int getURLParameterSize();
	
	/**
	 * <p>Adds an URL parameter type.</p>
	 *
	 * @param urlParameterType
	 */
	public void addURLParameterType(URLParameterType urlParameterType);
	
	/**
	 * <p>Removes a parameter type.</p>
	 *
	 * @param urlParameterType
	 */
	public void removeURLParameterType(URLParameterType urlParameterType);
	
	/**
	 * <p>Returns true if the parameter type exists.</p>
	 *
	 * @param urlParameterType
	 * @return boolean
	 */
	public boolean containsURLParameterType(URLParameterType urlParameterType);
	
	/**
	 * <p>Returns true if all the parameter types exist.</p>
	 *
	 * @param urlParameterTypes
	 * @return boolean
	 */
	public boolean containsAllURLParameterTypes(List<URLParameterType> urlParameterTypes);
	
	
	/**
	 * <p>Returns true if URL parameter types exist.</p>
	 *
	 * @return boolean
	 */
	public boolean hasURLParameterTypes();
	
	/**
	 * <p>Removes all URL parameter types.</p>
	 */
	public void clearURLParameterTypes();
	
	/**
	 * <p>Returns an iterator all extended parameter types.</p>
	 *
	 * @return {@link Iterator}&lt;XURLParameterType&gt;
	 */
	public Iterator<XURLParameterType> getExtendedURLParameterTypes();
	
	/**
	 * <p>Returns an unmodifiable list of extended parameter types.</p>
	 *
	 * @return {@link List}&lt;XURLParameterType&gt;
	 */
	public List<XURLParameterType> getExtendedURLParameterTypesList();
	
	/**
	 * <p>Returns the number of extended parameter types.</p>
	 *
	 * @return int
	 */
	public int getExtendedURLParameterSize();
	
	/**
	 * <p>Adds an extended URL parameter type.</p>
	 *
	 * @param xurlParameterType
	 */
	public void addExtendedURLParameterType(XURLParameterType xurlParameterType);
	
	/**
	 * <p>Removes an extended parameter type.</p>
	 *
	 * @param xurlParameterType
	 */
	public void removeExtendedURLParameterType(XURLParameterType xurlParameterType);
	
	/**
	 * <p>Returns true if the extended parameter type exists.</p>
	 *
	 * @param xurlParameterType
	 * @return boolean
	 */
	public boolean containsExtendedURLParameterType(XURLParameterType xurlParameterType);
	
	/**
	 * <p>Returns true if all the extended parameter types exist.</p>
	 *
	 * @param xurlParameterTypes
	 * @return boolean
	 */
	public boolean containsAllExtendedURLParameterTypes(List<XURLParameterType> xurlParameterTypes);
	
	/**
	 * <p>Returns true if extended URL parameter types exist.</p>
	 *
	 * @return boolean
	 */
	public boolean hasExtendedURLParameterTypes();
	
	/**
	 * <p>Removes all extended URL parameter types.</p>
	 */
	public void clearExtendedURLParameterTypes();
	
	/**
	 * <p>Returns a full copy of this object.</p>
	 *
	 * @return {@link URLFeature}
	 */
	public URLFeature clone();
}
