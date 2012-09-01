package net.sourceforge.cardme.vcard.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.LabelFeature;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.LabelParamType;

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
public class LabelType extends AbstractVCardType implements Comparable<LabelType>, Cloneable, LabelFeature {

	private static final long serialVersionUID = 8576427090236222231L;
	
	private String label = null;
	private List<LabelParamType> labelParamTypes = null;
	
	public LabelType() {
		this(null);
	}
	
	public LabelType(String label) {
		super(VCardTypeName.LABEL);
		labelParamTypes = new ArrayList<LabelParamType>();
		setLabel(label);
	}

	public String getLabel()
	{
		if(label != null) {
			return new String(label);
		}
		else {
			return null;
		}
	}

	public void setLabel(String label) {
		if(label != null) {
			this.label = new String(label);
		}
		else {
			this.label = null;
		}
	}

	public boolean hasLabel()
	{
		return label != null;
	}

	public List<LabelParamType> getParams()
	{
		return Collections.unmodifiableList(labelParamTypes);
	}

	public int getParamSize()
	{
		return labelParamTypes.size();
	}

	public LabelType addParam(LabelParamType labelParamType) throws NullPointerException {
		if(labelParamType == null) {
			throw new NullPointerException("Cannot add a null labelParamType.");
		}
		
		labelParamTypes.add(labelParamType);
		return this;
	}

	public LabelType addAllParams(List<LabelParamType> labelParamTypes) throws NullPointerException {
		if(labelParamTypes == null) {
			throw new NullPointerException("Cannot add a null labelParamTypes list.");
		}
		
		this.labelParamTypes.addAll(labelParamTypes);
		return this;
	}

	public LabelType removeParam(LabelParamType labelParamType) throws NullPointerException {
		if(labelParamType == null) {
			throw new NullPointerException("Cannot remove a null labelParamType.");
		}
		
		labelParamTypes.remove(labelParamType);
		return this;
	}

	public boolean containsParam(LabelParamType labelParamType)
	{
		if(labelParamType != null) {
			return labelParamTypes.contains(labelParamType);
		}
		else {
			return false;
		}
	}

	public boolean containsAllParams(List<LabelParamType> labelParamTypes)
	{
		if(labelParamTypes != null) {
			return this.labelParamTypes.containsAll(labelParamTypes);
		}
		else {
			return false;
		}
	}

	public boolean hasParams()
	{
		return !labelParamTypes.isEmpty();
	}

	public void clearParams() {
		labelParamTypes.clear();
	}
	
	@Override
	public LabelType clone()
	{
		LabelType cloned = new LabelType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.setLabel(label);
		cloned.addAllParams(labelParamTypes);
		return cloned;
	}
	
	public int compareTo(LabelType obj)
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
		
		if(!labelParamTypes.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(LabelParamType labelParamType: labelParamTypes) {
				sb.append(labelParamType.getType());
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[7] = sb.toString();
		}
		else {
			contents[7] = "";
		}
		
		contents[8] = StringUtil.getString(label);
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof LabelType) {
				return this.compareTo((LabelType)obj) == 0;
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
