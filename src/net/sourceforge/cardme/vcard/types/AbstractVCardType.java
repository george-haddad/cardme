package net.sourceforge.cardme.vcard.types;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.arch.EncodingType;
import net.sourceforge.cardme.vcard.arch.LanguageType;
import net.sourceforge.cardme.vcard.arch.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.arch.VCardParamTypeExtension;
import net.sourceforge.cardme.vcard.arch.VCardType;
import net.sourceforge.cardme.vcard.arch.VCardTypeFormatter;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
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
 * Aug 7, 2012
 *
 */
public abstract class AbstractVCardType implements VCardType, VCardTypeFormatter, VCardParamTypeExtension, Serializable {
	
	private static final long serialVersionUID = -8514383172753523898L;

	protected VCardTypeName vcardTypeName = null;
	
	private EncodingType encodingType = null;
	private String group = null;
	private Charset charset = Charset.forName("UTF-8");
	private LanguageType languageType = null;
	
	private List<ExtendedParamType> extendedParameters = null;
	private ParameterTypeStyle paramTypeStyle = null;
	
	public AbstractVCardType(VCardTypeName vcardTypeName) {
		this(vcardTypeName, ParameterTypeStyle.PARAMETER_VALUE_LIST);
	}
	
	public AbstractVCardType(VCardTypeName vcardTypeName, ParameterTypeStyle paramTypeStyle) {
		this.vcardTypeName = vcardTypeName;
		setParameterTypeStyle(paramTypeStyle);
		extendedParameters = new ArrayList<ExtendedParamType>();
		encodingType = EncodingType.EIGHT_BIT;
	}
	
	@Override
	public VCardTypeName getVCardTypeName()
	{
		return vcardTypeName;
	}

	protected final void setVCardTypeName(VCardTypeName vcardTypeName) throws NullPointerException {
		if(vcardTypeName == null) {
			throw new NullPointerException("vcardTypeName cannot be set to null.");
		}
		
		this.vcardTypeName = vcardTypeName;
	}

	@Override
	public final EncodingType getEncodingType()
	{
		return encodingType;
	}

	@Override
	public final void setEncodingType(EncodingType encodingType) throws NullPointerException {
		if(encodingType == null) {
			throw new NullPointerException("encodingType cannot be set to null.");
		}
		
		this.encodingType = encodingType;
	}

	@Override
	public final String getGroup()
	{
		if(group != null) {
			return new String(group);
		}
		else {
			return null;
		}
	}

	@Override
	public final void setGroup(String group) {
		if(group != null) {
			this.group = new String(group);
		}
		else {
			this.group = null;
		}
	}

	@Override
	public final boolean hasGroup()
	{
		return group != null;
	}

	@Override
	public final Charset getCharset()
	{
		return charset;
	}

	@Override
	public final void setCharset(String strCharset) {
		if(strCharset == null) {
			charset = Charset.forName("UTF-8");
		}
		else if(strCharset.isEmpty()) {
			charset = Charset.forName("UTF-8");
		}
		else {
			if(Charset.isSupported(strCharset)) {
				charset = Charset.forName(strCharset);
			}
			else {
				charset = Charset.forName("UTF-8");
			}
		}
	}

	@Override
	public final void setCharset(Charset charset) {
		if(charset == null) {
			this.charset = Charset.forName("UTF-8");
		}
		else {
			this.charset = charset;
		}
	}

	@Override
	public final boolean hasCharset()
	{
		if (charset == null || "UTF-8".equalsIgnoreCase(charset.name())) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public final LanguageType getLanguage()
	{
		return languageType;
	}

	@Override
	public final void setLanguage(LanguageType languageType) {
		if(languageType != null) {
			this.languageType = languageType;
		}
		else {
			this.languageType = null;
		}
	}

	@Override
	public final void setLanguage(String languageType) {
		if(languageType != null) {
			try {
				this.languageType = LanguageType.valueOf(languageType.toUpperCase().replaceAll("-", "_"));
			}
			catch(Exception ex) {
				this.languageType = null;
			}
		}
		else {
			this.languageType = null;
		}
	}

	@Override
	public final boolean hasLanguage()
	{
		return languageType != null;
	}

	@Override
	public final boolean isQuotedPrintable()
	{
		return EncodingType.QUOTED_PRINTABLE.equals(encodingType);
	}

	// -----------------------------------------------------
	
	@Override
	public final ParameterTypeStyle getParameterTypeStyle()
	{
		return paramTypeStyle;
	}

	@Override
	public final void setParameterTypeStyle(ParameterTypeStyle paramTypeStyle) throws NullPointerException {
		if(paramTypeStyle == null) {
			throw new NullPointerException("paramTypeStyle cannot be set to null.");
		}
		
		this.paramTypeStyle = paramTypeStyle;
	}
	
	// -----------------------------------------------------
	
	@Override
	public final List<ExtendedParamType> getExtendedParams()
	{
		return Collections.unmodifiableList(extendedParameters);
	}

	@Override
	public final int getExtendedParamSize()
	{
		return extendedParameters.size();
	}

	@Override
	public final VCardParamTypeExtension addExtendedParam(ExtendedParamType xtendedParam) throws NullPointerException {
		if(xtendedParam == null) {
			throw new NullPointerException("Cannot add a null extended parameter.");
		}
		
		extendedParameters.add(xtendedParam);
		return this;
	}
	
	@Override
	public final VCardParamTypeExtension addAllExtendedParams(List<ExtendedParamType> xtendedParams) throws NullPointerException {
		if(xtendedParams == null) {
			throw new NullPointerException("Cannot add a null extended parameters list.");
		}
		
		extendedParameters.addAll(xtendedParams);
		return this;
	}
	
	@Override
	public final VCardParamTypeExtension removeExtendedParam(ExtendedParamType xtendedParam) throws NullPointerException {
		if(xtendedParam == null) {
			throw new NullPointerException("Cannot remove a null extended parameter.");
		}
		
		extendedParameters.remove(xtendedParam);
		return this;
	}

	@Override
	public final boolean containsExtendedParam(ExtendedParamType xtendedParam)
	{
		if(xtendedParam != null) {
			return extendedParameters.contains(xtendedParam);
		}
		else {
			return false;
		}
	}

	@Override
	public final boolean containsAllExtendedParams(List<ExtendedParamType> xtendedParams)
	{
		if(xtendedParams != null) {
			return extendedParameters.containsAll(xtendedParams);
		}
		else {
			return false;
		}
	}

	@Override
	public final boolean hasExtendedParams()
	{
		return !extendedParameters.isEmpty();
	}
	
	@Override
	public final void clearExtendedParams() {
		extendedParameters.clear();
	}
	
	// -----------------------------------------------------
	
	@Override
	public int hashCode()
	{
		return Util.generateHashCode(getContents());
	}
	
	@Override
	public String toString()
	{
		String[] args = getContents();
		StringBuilder sb = new StringBuilder();
		sb.append(vcardTypeName.getType());
		sb.append(" [");
		
		for(int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			sb.append(",");
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
	
	protected abstract String[] getContents();
	
	@Override
	public abstract boolean equals(Object obj);
}
