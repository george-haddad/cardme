package net.sourceforge.cardme.vcard.types.parameters;

import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.VCardType;

/*
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
 * Aug 23, 2010
 *
 */
public abstract class XTendedParameterType {

	private String xtendedTypeName = null;
	private String xtendedTypeValue = null;
	
	public XTendedParameterType(String xtendedTypeName, String xtendedTypeValue) {
		this.xtendedTypeName = xtendedTypeName;
		this.xtendedTypeValue = xtendedTypeValue;
	}
	
	public XTendedParameterType(String xtendedTypeName) {
		this.xtendedTypeName = xtendedTypeName;
	}
	
	public String getXtendedTypeName()
	{
		return xtendedTypeName;
	}
	
	public String getXtendedTypeValue()
	{
		return xtendedTypeValue;
	}
	
	public boolean hasXtendedTypeValue()
	{
		return xtendedTypeValue != null;
	}
	
	protected void setXtendedTypeName(String xtendedTypeName) {
		this.xtendedTypeName = xtendedTypeName;
	}
	
	protected void setXtendedTypeValue(String xtendedTypeValue) {
		this.xtendedTypeValue = xtendedTypeValue;
	}
	
	public abstract String getType();
	
	public abstract String getDescription();
	
	public abstract VCardType getParentType();
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof XTendedParameterType) {
				if(this == obj || ((XTendedParameterType)obj).hashCode() == this.hashCode()) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return Util.generateHashCode(toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName());
		sb.append("[ ");
		if(xtendedTypeName != null) {
			sb.append(xtendedTypeName);
			sb.append(",");
		}
		
		if(xtendedTypeValue != null) {
			sb.append(xtendedTypeValue);
			sb.append(",");
		}
		
		sb.deleteCharAt(sb.length()-1);	//Remove last comma.
		sb.append(" ]");
		return sb.toString();
	}
}
