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

	public String getEmail();
	
	public void setEmail(String email);
	
	public boolean hasEmail();
	
	public List<EmailParamType> getParams();
	public int getParamSize();
	public EmailFeature addParam(EmailParamType emailParamType) throws NullPointerException;
	public EmailFeature addAllParams(List<EmailParamType> emailParamTypes) throws NullPointerException;
	public EmailFeature removeParam(EmailParamType emailParamType) throws NullPointerException;
	public boolean containsParam(EmailParamType emailParamType);
	public boolean containsAllParams(List<EmailParamType> emailParamTypes);
	public boolean hasParams();
	public void clearParams();
}
