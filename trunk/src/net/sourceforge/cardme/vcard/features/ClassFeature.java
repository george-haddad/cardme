package net.sourceforge.cardme.vcard.features;

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
 * <p><b>RFC 2426</b></br>
 * <b>3.7.1 CLASS Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> CLASS</li>
 * 	<li><b>Type purpose:</b> To specify the access classification for a vCard object.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single text value.</li>
 * 	<li><b>Type special note:</b> An access classification is only one component of the general security model for a directory service. The classification attribute provides a method of capturing the intent of the owner for general access to information described by the vCard object.</li>
 * </ul>
 * </p>
 */
public interface ClassFeature extends TypeTools {
	
	/**
	 * <p>Returns the security class.</p>
	 *
	 * @return {@link String}
	 */
	public String getSecurityClass();
	
	/**
	 * <p>Sets the security class.</p>
	 *
	 * @param securityClass
	 */
	public void setSecurityClass(String securityClass);
	
	/**
	 * <p>Returns true if a security class exists.</p>
	 *
	 * @return boolean
	 */
	public boolean hasSecurityClass();
	
	/**
	 * <p>Clears the security class.</p>
	 */
	public void clearSecurityClass();
	
	/**
	 * <p>Returns a full copy of this object.</p>
	 *
	 * @return {@link ClassFeature}
	 */
	public ClassFeature clone();
}
