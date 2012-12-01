package net.sourceforge.cardme.vcard.arch;

import java.nio.charset.Charset;

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
 * Aug 7, 2012
 * 
 * <p>Interface to define the common functions of a VCard type.</p>
 */
public interface VCardType {
	
	/**
	 * <p>Retrieve the VCard type name as a {@link VCardTypeName} enum object.</p>
	 * 
	 * @return the VCard type name
	 */
	public VCardTypeName getVCardTypeName();
	
	/**
	 * <p>Retrieve the encoding type, by default it
	 * is {@link EncodingType}.EIGHT_BIT.</p>
	 * 
	 * @return the encoding type used for this VCard type
	 */
	public EncodingType getEncodingType();
	
	/**
	 * <p>Sets the encoding type of this VCard type.</p>
	 * 
	 * @param encodingType - the encoding type to set, if not set then {@link EncodingType}.EIGHT_BIT is used by default
	 */
	public void setEncodingType(EncodingType encodingType);
	
	/**
	 * <p>Retrieves the group name of the type.</p>
	 * 
	 * @return the group name of the type
	 */
	public String getGroup();
	
	/**
	 * <p>Sets the group name of the type.</p>
	 * 
	 * @param group - the group name
	 */
	public void setGroup(String group);
	
	/**
	 * <p>Indicates if this VCard type is associated with a group or not.</p>
	 * 
	 * @return true if it has a group name
	 */
	public boolean hasGroup();
	
	/**
	 * <p>Returns the charset used for this VCard type,
	 * by default it is UTF-8 as specified in RFC-6350.</p>
	 * 
	 * @return the charset used in this VCard type, deafult is UTF-8
	 */
	public Charset getCharset();
	
	/**
	 * <p>Sets the charset to be used for this VCard type.</p>
	 * 
	 * @param charset - the charset to be used for this vcard type
	 */
	public void setCharset(String charset);
	
	/**
	 * <p>Sets the charset to be used for this VCard type.</p>
	 * 
	 * @param charset - the charset to be used for this vcard type
	 */
	public void setCharset(Charset charset);
	
	/**
	 * <p>Indicates if this VCard type uses a charset other than
	 * the default which is UTF-8.</p>
	 * 
	 * @return true if the charset is something other than the default of UTF-8
	 */
	public boolean hasCharset();
	
	/**
	 * <p>Retrieve the language type of this VCard type.</p>
	 * 
	 * @return the language type of this VCard type
	 */
	public LanguageType getLanguage();
	
	/**
	 * <p>Sets the language type of this VCard type.</p>
	 * 
	 * @param languageType - the language type to use for this VCard type
	 */
	public void setLanguage(LanguageType languageType);
	
	/**
	 * <p>Sets the language type of this VCard type.</p>
	 * 
	 * @param languageType - the language type to use for this VCard type
	 */
	public void setLanguage(String languageType);
	
	/**
	 * <p>Indicates if this VCard type has a language type or not.</p>
	 * 
	 * @return true if this VCard type has a language type and false otherwise
	 */
	public boolean hasLanguage();
	
	/**
	 * <p>Indicates if the value of this VCard type is Quoted-Printable encoded.</p>
	 * 
	 * @return true if the value is encoded using Quoted-Printable encoding
	 */
	public boolean isQuotedPrintable();
	
	/***
	 * <p>Indicates if this VCard type has parameter types.</p>
	 * 
	 * @return true if parameter types exist in this VCard type
	 */
	public boolean hasParams();
}
