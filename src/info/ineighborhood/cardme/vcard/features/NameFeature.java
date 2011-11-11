package info.ineighborhood.cardme.vcard.features;

import java.util.Iterator;

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
 * <b>3.1.2 N Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> N</li>
 * 	<li><b>Type purpose:</b> To specify the components of the name of the object the vCard represents.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single structured text value. Each component can have multiple values.</li>
 * 	<li><b>Type special note:</b> The structured type value corresponds, in sequence, to the Family Name, Given Name, Additional Names, Honorific Prefixes, and Honorific Suffixes. The text components are separated by the SEMI-COLON character (ASCII decimal 59). Individual text components can include multiple text values (e.g., multiple Additional Names) separated by the COMMA character (ASCII decimal 44). This type is based on the semantics of the X.520 individual name attributes. The property MUST be present in the vCard object.</li>
 * </ul>
 * </p>
 */
public interface NameFeature extends TypeTools {
	
	/**
	 * <p>Returns the family name.</p>
	 * 
	 * @return {@link String}
	 */
	public String getFamilyName();
	
	/**
	 * <p>Returns the given name.</p>
	 * 
	 * @return {@link String}
	 */
	public String getGivenName();
	
	/**
	 * <p>Returns a list of additional names.</p>
	 * 
	 * @return {@link Iterator}&lt;String&gt;
	 */
	public Iterator<String> getAdditionalNames();
	
	/**
	 * <p>Returns a list of honorific prefixes.</p>
	 * 
	 * @return {@link Iterator}&lt;String&gt;
	 */
	public Iterator<String> getHonorificPrefixes();
	
	/**
	 * <p>Returns a list of honorific suffixes.</p>
	 * 
	 * @return {@link Iterator}&lt;String&gt;
	 */
	public Iterator<String> getHonorificSuffixes();
	
	/**
	 * <p>Sets the family name.</p>
	 * 
	 * @param familyName
	 */
	public void setFamilyName(String familyName);
	
	/**
	 * <p>Sets the given name.</p>
	 * 
	 * @param givenName
	 */
	public void setGivenName(String givenName);
	
	/**
	 * 
	 * <p>Adds an additional name.</p>
	 *
	 * @param additionalName
	 */
	public void addAdditionalName(String additionalName);
	
	/**
	 * <p>Removes an additional name.</p>
	 *
	 * @param additionalName
	 */
	public void removeAdditionalName(String additionalName);
	
	/**
	 * <p>Returns true if the specified additional name exists.</p>
	 *
	 * @param additionalName
	 * @return boolean
	 */
	public boolean containsAdditionalName(String additionalName);
	
	/**
	 * <p>Returns true if there are additional names.</p>
	 *
	 * @return boolean
	 */
	public boolean hasAdditionalNames();
	
	/**
	 * <p>Returns true if a family name exists.</p>
	 *
	 * @return boolean
	 */
	public boolean hasFamilyName();
	
	/**
	 * <p>Returns true if a given name exists.</p>
	 *
	 * @return boolean
	 */
	public boolean hasGivenName();
	
	/**
	 * <p>Removes all additional names.</p>
	 */
	public void clearAdditionalNames();
	
	/**
	 * <p>Adds a honorific prefix.</p>
	 *
	 * @param honorificPrefix
	 */
	public void addHonorificPrefix(String honorificPrefix);
	
	/**
	 * <p>Removes a honorific prefix.</p>
	 *
	 * @param honorificPrefix
	 */
	public void removeHonorificPrefix(String honorificPrefix);
	
	/**
	 * <p>Returns true if the specified honorific prefix exists.</p>
	 *
	 * @param honorificPrefix
	 * @return boolean
	 */
	public boolean containsHonorificPrefix(String honorificPrefix);
	
	/**
	 * <p>Returns true if there are honorific prefixes.</p>
	 *
	 * @return boolean
	 */
	public boolean hasHonorificPrefixes();
	
	/**
	 * <p>Removes all honorific prefixes.</p>
	 */
	public void clearHonorificPrefixes();
	
	/**
	 * <p>Adds a honorific suffix.</p>
	 *
	 * @param honorificSuffix
	 */
	public void addHonorificSuffix(String honorificSuffix);
	
	/**
	 * <p>Removes a honorific suffix.</p>
	 *
	 * @param honorificSuffix
	 */
	public void removeHonorificSuffix(String honorificSuffix);
	
	/**
	 * <p>Returns true if the specified honorific suffix exists.</p>
	 *
	 * @param honorificSuffix
	 * @return boolean
	 */
	public boolean containsHonorificSuffix(String honorificSuffix);
	
	/**
	 * <p>Returns true if there are honorific suffixes.</p>
	 *
	 * @return boolean
	 */
	public boolean hasHonorificSuffixes();
	
	/**
	 * <p>Removes all honorific suffixes.</p>
	 */
	public void clearHonorificSuffixes();
	
	/**
	 * <p>Returns a full copy of this object.</p>
	 *
	 * @return {@link NameFeature}
	 */
	public NameFeature clone();
}
