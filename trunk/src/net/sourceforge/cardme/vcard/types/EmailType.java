package net.sourceforge.cardme.vcard.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.EmailFeature;
import net.sourceforge.cardme.vcard.types.params.EmailParamType;
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
 * Aug 8, 2012
 *
 */
public class EmailType extends AbstractVCardType implements Comparable<EmailType>, Cloneable, EmailFeature {

	private static final long serialVersionUID = -7906212797176603907L;
	
	private String email = null;
	private List<EmailParamType> emailParamTypes = null;
	
	public EmailType() {
		this(null);
	}
	
	public EmailType(String email) {
		super(VCardTypeName.EMAIL);
		emailParamTypes = new ArrayList<EmailParamType>();
		setEmail(email);
	}

	@Override
	public String getEmail()
	{
		if(email != null) {
			return new String(email);
		}
		else {
			return null;
		}
	}

	@Override
	public void setEmail(String email) {
		if(email != null) {
			this.email = new String(email);
		}
		else {
			this.email = null;
		}
	}
	
	@Override
	public boolean hasEmail()
	{
		return email != null;
	}

	@Override
	public List<EmailParamType> getParams()
	{
		return Collections.unmodifiableList(emailParamTypes);
	}

	@Override
	public int getParamSize()
	{
		return emailParamTypes.size();
	}

	@Override
	public EmailType addParam(EmailParamType emailParamType) throws NullPointerException {
		if(emailParamType == null) {
			throw new NullPointerException("Cannot add a null emailParamType.");
		}
		
		emailParamTypes.add(emailParamType);
		return this;
	}

	@Override
	public EmailType addAllParams(List<EmailParamType> emailParamTypes) throws NullPointerException {
		if(emailParamTypes == null) {
			throw new NullPointerException("Cannot add a null emailParamTypes list.");
		}
		
		this.emailParamTypes.addAll(emailParamTypes);
		return this;
	}

	@Override
	public EmailType removeParam(EmailParamType emailParamType) throws NullPointerException {
		if(emailParamType == null) {
			throw new NullPointerException("Cannot remove a null emailParamType.");
		}
		
		emailParamTypes.remove(emailParamType);
		return this;
	}

	@Override
	public boolean containsParam(EmailParamType emailParamType)
	{
		if(emailParamType != null) {
			return emailParamTypes.contains(emailParamType);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsAllParams(List<EmailParamType> emailParamTypes)
	{
		if(emailParamTypes != null) {
			return this.emailParamTypes.containsAll(emailParamTypes);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasParams()
	{
		return !emailParamTypes.isEmpty();
	}

	@Override
	public void clearParams() {
		emailParamTypes.clear();
	}
	
	@Override
	public EmailType clone()
	{
		EmailType cloned = new EmailType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.addAllParams(emailParamTypes);
		cloned.setEmail(email);
		return cloned;
	}
	
	@Override
	public int compareTo(EmailType obj)
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
		
		if(!emailParamTypes.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(EmailParamType emailParamType: emailParamTypes) {
				sb.append(emailParamType.getType());
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[7] = sb.toString();
		}
		else {
			contents[7] = "";
		}
		
		contents[8] = StringUtil.getString(email);
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof EmailType) {
				return this.compareTo((EmailType)obj) == 0;
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
