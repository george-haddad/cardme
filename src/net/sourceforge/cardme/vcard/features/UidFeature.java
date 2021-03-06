package net.sourceforge.cardme.vcard.features;

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
 * <b>3.6.7 UID Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> UID</li>
 * 	<li><b>Type purpose:</b> To specify a value that represents a globally unique identifier corresponding to the individual or resource associated with the vCard.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single text value.</li>
 * 	<li><b>Type special note:</b> The type is used to uniquely identify the object that the vCard represents.</li>
 * </ul>
 * </p>
 */
public interface UidFeature {

	/**
	 * <p>Retrieves the UID.</p>
	 * 
	 * @return the UID or null if not set
	 */
	public String getUid();
	
	/**
	 * <p>Sets the UID.</p>
	 * 
	 * @param uid - the UID to set
	 */
	public void setUid(String uid);
	
	/**
	 * <p>Removes the UID.</p>
	 */
	public void clearUid();
	
	/**
	 * <p>Indicates if the UID has been set.</p>
	 * 
	 * @return true if the UID has been set or false otherwise
	 */
	public boolean hasUid();
}
