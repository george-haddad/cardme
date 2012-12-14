package net.sourceforge.cardme.vcard.features;

import java.util.List;
import net.sourceforge.cardme.vcard.types.params.EmailParamType;

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
 * <b>3.3.2 EMAIL Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> EMAIL</li>
 * 	<li><b>Type purpose:</b> To specify the electronic mail address for communication with the object the vCard represents.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single text value.</li>
 * 	<li><b>Type special note:</b> The type can include the type parameter "TYPE" to specify the format or preference of the electronic mail address. The TYPE parameter values can include: "internet" to indicate an Internet addressing type, "x400" to indicate a X.400 addressing type or "pref" to indicate a preferred-use email address when more than one is specified. Another IANA registered address type can also be specified. The default email type is "internet". A non-standard value can also be specified.</li>
 * </ul>
 * </p>
 */
public interface EmailFeature {

	/**
	 * <p>Retrieve the email address.</p>
	 * 
	 * @return the email address or null if not set
	 */
	public String getEmail();
	
	/**
	 * <p>Sets the email address.</p>
	 * 
	 * @param email - the email address to set
	 */
	public void setEmail(String email);
	
	/**
	 * <p>Indicates if the email address has been set.</p>
	 * 
	 * @return true if the email address was set or false otherwise
	 */
	public boolean hasEmail();
	
	/**
	 * <p>Retrieves the list of email parameter types.</p>
	 * 
	 * @return a list of email parameter types or empty list if none added
	 */
	public List<EmailParamType> getParams();
	
	/**
	 * <p>Retrieves the total number of email parameter types added.</p>
	 * 
	 * @return the total number of email parameter types added
	 */
	public int getParamSize();
	
	/**
	 * <p>Adds an email parameter type.</p>
	 * 
	 * @param emailParamType - the email parameter type to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null email parameter type
	 */
	public EmailFeature addParam(EmailParamType emailParamType) throws NullPointerException;
	
	/**
	 * <p>Adds a list of email parameter types.</p>
	 * 
	 * @param emailParamTypes - a list of email parameter types
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null list of email parameter types
	 */
	public EmailFeature addAllParams(List<EmailParamType> emailParamTypes) throws NullPointerException;
	
	/**
	 * <p>Removes the specified email parameter type.</p>
	 * 
	 * @param emailParamType - the email parameter type to remove
	 * @return a reference to itself
	 * @throws NullPointerException if removing a null email parameter type
	 */
	public EmailFeature removeParam(EmailParamType emailParamType) throws NullPointerException;
	
	/**
	 * <p>Indicates if the specified email parameter type exists.</p>
	 * 
	 * @param emailParamType - the email parameter type to check
	 * @return true if the specified email parameter type exists or false otherwise
	 */
	public boolean containsParam(EmailParamType emailParamType);
	
	/**
	 * <p>Indicates if at least one of the email parameter types in the list exists.</p>
	 * 
	 * @param emailParamTypes - the list of email parameter types to check
	 * @return true if at least one of the email parameter types in the list exists or false otherwise
	 */
	public boolean containsAllParams(List<EmailParamType> emailParamTypes);
	
	/**
	 * <p>Indicates if at least one email parameter types exist.</p>
	 * 
	 * @return true if at least one email parameter type exists or false otherwise
	 */
	public boolean hasParams();
	
	/**
	 * <p>Remove all email parameter types.</p>
	 */
	public void clearParams();
}
