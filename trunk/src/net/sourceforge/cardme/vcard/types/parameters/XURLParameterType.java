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
 * Mar 8, 2012
 *
 */
public class XURLParameterType extends XTendedParameterType {

	/**
	 * <p>Creates a new extended telephone parameter type with
	 * the extended name and value.</p>
	 * <p>Example: <code>URL;TYPE=X-EVOLUTION-SLOT=1;</code></p>
	 * 
	 * @param xtendedTypeName
	 * @param xtendedTypeValue
	 */
	public XURLParameterType(String xtendedTypeName, String xtendedTypeValue) {
		super(xtendedTypeName, xtendedTypeValue);
	}
	
	/**
	 * <p>Creates a new extended telephone parameter type with
	 * the extended name only.
	 * <p>Example: <code>URL;TYPE=X-COLOR-RED;</code></p>
	 * 
	 * @param xtendedTypeName
	 */
	public XURLParameterType(String xtendedTypeName) {
		super(xtendedTypeName);
	}
	
	@Override
	public String getType()
	{
		return URLParameterType.NON_STANDARD.getType();
	}
	
	@Override
	public String getDescription()
	{
		return URLParameterType.NON_STANDARD.getDescription();
	}
	
	@Override
	public VCardType getParentType()
	{
		return VCardType.URL;
	}
}
