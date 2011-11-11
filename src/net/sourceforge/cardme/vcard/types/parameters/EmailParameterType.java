package net.sourceforge.cardme.vcard.types.parameters;

import net.sourceforge.cardme.vcard.VCardType;

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
 * Feb 25, 2010
 *
 */
public enum EmailParameterType {

	INTERNET("INTERNET", "Internet Email"),
	X_400("X_400", "X.400"),
	AOL("AOL", "AOL"),
	APPLELINK("APPLELINK", "Apple Link"),
	ATTMAIL("ATTMAIL", "AT&T Mail"),
	CIS("CIS", "Compuserv Information Service"),
	EWORLD("EWORLD", "eWorld"),
	IBMMAIL("IBMMAIL", "IBM Mail"),
	MCIMAIL("MCIMAIL", "MCI Mail"),
	POWERSHARE("POWERSHARE", "Powershare"),
	PRODIGY("PRODIGY", "Prodigy Information Service"),
	TLX("TLX", "Telex Number"),
	HOME("HOME", "Home"),
	WORK("WORK", "Work"),
	OTHER("OTHER", "Other"),
	PREF("PREF", "Preferred"),
	TYPE("TYPE", "type"),
	NON_STANDARD("NON_STANDARD","Non-Standard");
	
	private String type;
	private String desc;
	EmailParameterType(String t, String d) {
		type = t;
		desc = d;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getDescription()
	{
		return desc;
	}
	
	public VCardType getParentType()
	{
		return VCardType.EMAIL;
	}
	
	public void setType(String type) {
		this.type = type.toUpperCase();
	}
	
	public void setDescription(String desc) {
		this.desc = desc;
	}
}
