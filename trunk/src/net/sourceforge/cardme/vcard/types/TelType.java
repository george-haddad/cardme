package net.sourceforge.cardme.vcard.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.TelFeature;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.TelParamType;

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
public class TelType extends AbstractVCardType implements Comparable<TelType>, Cloneable, TelFeature {

	private static final long serialVersionUID = -3285997833468976023L;
	
	private String telephone = null;
	private List<TelParamType> telParamTypes = null;
	
	public TelType() {
		this(null);
	}
	
	public TelType(String telephone) {
		super(VCardTypeName.TEL);
		telParamTypes = new ArrayList<TelParamType>();
		setTelephone(telephone);
	}

	@Override
	public String getTelephone()
	{
		if(telephone != null) {
			return new String(telephone);
		}
		else {
			return null;
		}
	}

	@Override
	public void setTelephone(String telephone) {
		if(telephone != null) {
			this.telephone = new String(telephone);
		}
		else {
			this.telephone = null;
		}
	}

	@Override
	public boolean hasTelephone() 
	{
		return telephone != null;
	}

	@Override
	public List<TelParamType> getParams()
	{
		return Collections.unmodifiableList(telParamTypes);
	}

	@Override
	public int getParamSize()
	{
		return telParamTypes.size();
	}

	@Override
	public TelType addParam(TelParamType telParamType) throws NullPointerException {
		if(telParamType == null) {
			throw new NullPointerException("Cannot add a null telParamType.");
		}
		
		telParamTypes.add(telParamType);
		return this;
	}

	@Override
	public TelType addAllParams(List<TelParamType> telParamTypes) throws NullPointerException {
		if(telParamTypes == null) {
			throw new NullPointerException("Cannot add a null telParamTypes list.");
		}
		
		this.telParamTypes.addAll(telParamTypes);
		return this;
	}

	@Override
	public TelType removeParam(TelParamType telParamType) throws NullPointerException {
		if(telParamType == null) {
			throw new NullPointerException("Cannot remove a null telParamType.");
		}
		
		telParamTypes.remove(telParamType);
		return this;
	}

	@Override
	public boolean containsParam(TelParamType telParamType)
	{
		if(telParamType != null) {
			return telParamTypes.contains(telParamType);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsAllParams(List<TelParamType> telParamTypes)
	{
		if(telParamTypes != null) {
			return this.telParamTypes.containsAll(telParamTypes);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasParams()
	{
		return !telParamTypes.isEmpty();
	}

	@Override
	public void clearParams() {
		telParamTypes.clear();
	}
	
	
	@Override
	public TelType clone()
	{
		TelType cloned = new TelType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.setTelephone(telephone);
		cloned.addAllParams(telParamTypes);
		return cloned;
	}
	
	@Override
	public int compareTo(TelType obj)
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
		
		if(!telParamTypes.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(TelParamType telParamType: telParamTypes) {
				sb.append(telParamType.getType());
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[7] = sb.toString();
		}
		else {
			contents[7] = "";
		}
		
		contents[8] = StringUtil.getString(telephone);
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof TelType) {
				return this.compareTo((TelType)obj) == 0;
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
