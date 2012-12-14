package net.sourceforge.cardme.vcard.features;

import java.net.URL;
import java.util.List;
import net.sourceforge.cardme.vcard.types.params.UrlParamType;

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
public interface UrlFeature {
	
	/**
	 * <p>Retrieves the URL.</p>
	 * 
	 * @return the URL or null if none was set
	 */
	public URL getUrl();
	
	/**
	 * <p>Retrieves the raw URL value as a {@link String}.</p>
	 * 
	 * @return the raw URL value as a {@link String}
	 */
	public String getRawUrl();
	
	/**
	 * <p>Sets the URL.</p>
	 * 
	 * @param url - the URL to set
	 */
	public void setUrl(URL url);
	
	/**
	 * <p>Sets the URL by using the raw URL value.</p>
	 * 
	 * @param rawUrl - the raw URL value
	 */
	public void setRawUrl(String rawUrl);
	
	/**
	 * <p>Removes the URL.</p>
	 */
	public void clearUrl();
	
	/**
	 * <p>Indicates if the URL has been set.</p>
	 * 
	 * @return true if the URL has been set or false otherwise
	 */
	public boolean hasUrl();
	
	/**
	 * <p>Indicates if the raw URL has been set.</p>
	 * 
	 * @return true if the raw URL has been set or false otherwise
	 */
	public boolean hasRawUrl();
	
	/**
	 * <p>Retrieves a list of URL parameter types.</p>
	 * 
	 * @return a list of URL parameter types
	 */
	public List<UrlParamType> getParams();
	
	/**
	 * <p>Retrieves the total number of URL parameter types added.</p>
	 * 
	 * @return the total number of URL parameter types added
	 */
	public int getParamSize();
	
	/**
	 * <p>Adds a URL parameter type.</p>
	 * 
	 * @param urlParamType - the URL parameter type to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null URL parameter type
	 */
	public UrlFeature addParam(UrlParamType urlParamType) throws NullPointerException;
	
	/**
	 * <p>Adds all URL parameter types in the list.</p>
	 * 
	 * @param urlParamTypes - the list of URL parameter types to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null list of parameter types
	 */
	public UrlFeature addAllParams(List<UrlParamType> urlParamTypes) throws NullPointerException;
	
	/**
	 * <p>Removes the specified URL parameter type.</p>
	 * 
	 * @param urlParamType - the URL parameter type to remove
	 * @return a reference to itself
	 * @throws NullPointerException if removing a null URL parameter type
	 */
	public UrlFeature removeParam(UrlParamType urlParamType) throws NullPointerException;
	
	/**
	 * <p>Indicates if the specified URL parameter type exists.</p>
	 * 
	 * @param urlParamType - the URL parameter type to check
	 * @return true if the specified URL parameter type exists
	 */
	public boolean containsParam(UrlParamType urlParamType);
	
	/**
	 * <p>Indicates if all URL parameter types in the list have been added.</p>
	 * 
	 * @param urlParamTypes - the list of URL parameter types to check
	 * @return true if all URL parameter types in the list have been added
	 */
	public boolean containsAllParams(List<UrlParamType> urlParamTypes);
	
	/**
	 * <p>Indicates if URL parameter types have been added.</p>
	 * 
	 * @return true if URL parameter types have been added or false otherwise
	 */
	public boolean hasParams();
	
	/**
	 * <p>Removes all URL parameter types.</p>
	 */
	public void clearParams();
}
