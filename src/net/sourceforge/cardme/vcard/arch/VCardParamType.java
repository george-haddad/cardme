package net.sourceforge.cardme.vcard.arch;

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
 * <p>Interface used for implementing parameter type functionality
 * in a vcard type.</p>
 */
public interface VCardParamType {

	/**
	 * <p>Retrieve the type name.</p>
	 * 
	 * @return the type name
	 */
	public String getTypeName();
	
	/**
	 * <p>Set the type name.</p>
	 * 
	 * @param typeName - the type name as {@link String}
	 */
	public void setTypeName(String typeName);
	
	/**
	 * <p>Retrieve the value of the type.</p>
	 * 
	 * @return the type value
	 */
	public String getTypeValue();
	
	/**
	 * <p>Set the value of the type.</p>
	 * 
	 * @param typeValue - the type value as {@link String}
	 */
	public void setTypeValue(String typeValue);
	
	/**
	 * <p>Indicates if the type name has a value of not.</p>
	 * 
	 * @return true if the type has a value and false otherwise
	 */
	public boolean hasTypeValue();
	
	/**
	 * <p>Retrieve the parent type name of this parameter type.
	 * Example, calling this method from the EmailType will return
	 * the VCardTypeName enum of {@link VCardTypeName}.EMAIL</p>
	 * 
	 * @return the parent VCard type the parameter type belongs to
	 */
	public VCardTypeName getParentVCardTypeName();
}
