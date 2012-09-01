package net.sourceforge.cardme.vcard.types;

import java.util.Arrays;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.ExtendedFeature;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;

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
 * Aug 11, 2012
 *
 */
public class ExtendedType extends AbstractVCardType implements Comparable<ExtendedType>, Cloneable, ExtendedFeature {
	
	private static final long serialVersionUID = 7521366589218639708L;
	
	private String name = null;
	private String value = null;
	
	public ExtendedType() {
		this(null, null);
	}
	
	public ExtendedType(String name, String value) {
		super(VCardTypeName.XTENDED);
		setExtendedName(name);
		setExtendedValue(value);
	}

	public String getExtendedName()
	{
		if(name != null) {
			return new String(name);
		}
		else {
			return null;
		}
	}

	public void setExtendedName(String name) throws IllegalArgumentException {
		if(name != null) {
			if(name.startsWith("X-")) {
				this.name = new String(name);
			}
			else {
				throw new IllegalArgumentException("Extended type name must start with X-");
			}
		}
		else {
			this.name = null;
		}
	}

	public String getExtendedValue()
	{
		if(value != null) {
			return new String(value);
		}
		else {
			return null;
		}
	}

	public void setExtendedValue(String value) {
		if(value != null) {
			this.value = new String(value);
		}
		else {
			this.value = null;
		}
	}

	public boolean hasExtendedValue()
	{
		return name != null && value != null;
	}

	public void clearExtension() {
		name = null;
		value = null;
	}
	
	public boolean hasParams()
	{
		return false;
	}
	
	@Override
	public ExtendedType clone()
	{
		ExtendedType cloned = new ExtendedType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.setExtendedName(name);
		cloned.setExtendedValue(value);
		return cloned;
	}
	
	public int compareTo(ExtendedType obj)
	{
		if(obj != null) {
			String[] contents = obj.getContents();
			String[] myContents = getContents();
			if(Arrays.equals(myContents, contents)) {
				return 0;
			}
			else {
				return 1;
			}
		}
		else {
			return -1;
		}
	}

	@Override
	protected String[] getContents()
	{
		String[] contents = new String[9];
		contents[0] = getVCardTypeName().getType();
		contents[1] = getEncodingType().getType();
		contents[2] = StringUtil.getString(getGroup());
		contents[3] = (getCharset() != null ? getCharset().name() : "");
		contents[4] = (getLanguage() != null ? getLanguage().getLanguageCode() : "");
		contents[5] = getParameterTypeStyle().toString();
		
		if(hasExtendedParams()) {
			List<ExtendedParamType> xParams = getExtendedParams();
			StringBuilder sb = new StringBuilder();
			for(ExtendedParamType xParamType : xParams) {
				sb.append(xParamType.toString());
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[6] = sb.toString();
		}
		else {
			contents[6] = "";
		}
		
		contents[7] = StringUtil.getString(name);
		contents[8] = StringUtil.getString(value);
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof ExtendedType) {
				return this.compareTo((ExtendedType)obj) == 0;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
