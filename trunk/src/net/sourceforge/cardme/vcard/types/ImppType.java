package net.sourceforge.cardme.vcard.types;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.ImppFeature;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.ImppParamType;

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
 * Aug 13, 2012
 *
 */
public class ImppType extends AbstractVCardType implements Comparable<ImppType>, Cloneable, ImppFeature {

	private static final long serialVersionUID = -5632979328135688577L;
	
	private URI imppUri = null;
	private List<ImppParamType> imppParamTypes = new ArrayList<ImppParamType>();
	
	public ImppType() {
		this((URI)null);
	}
	
	public ImppType(URI uri) {
		super(VCardTypeName.IMPP);
		setUri(uri);
	}
	
	public ImppType(String uri) throws URISyntaxException {
		super(VCardTypeName.IMPP);
		setUri(new URI(uri));
	}

	@Override
	public URI getUri()
	{
		return imppUri;
	}

	@Override
	public void setUri(URI uri) {
		this.imppUri = uri;
	}

	@Override
	public boolean hasUri()
	{
		return imppUri != null;
	}

	@Override
	public void clearUri() {
		imppUri = null;
	}

	@Override
	public List<ImppParamType> getParams()
	{
		return Collections.unmodifiableList(imppParamTypes);
	}

	@Override
	public ImppType addParam(ImppParamType imppParamType) throws NullPointerException {
		if(imppParamType == null) {
			throw new NullPointerException("Cannot add a null imppParamType.");
		}
		
		imppParamTypes.add(imppParamType);
		return this;
	}

	@Override
	public ImppType addAllParams(List<ImppParamType> imppParamTypes) throws NullPointerException {
		if(imppParamTypes == null) {
			throw new NullPointerException("Cannot add a null imppParamType list.");
		}
		
		this.imppParamTypes.addAll(imppParamTypes);
		return this;
	}

	@Override
	public ImppType removeParam(ImppParamType imppParamType) throws NullPointerException {
		if(imppParamType == null) {
			throw new NullPointerException("Cannot remove a null imppParamType.");
		}
		
		imppParamTypes.remove(imppParamType);
		return this;
	}

	@Override
	public boolean containsParam(ImppParamType imppParamType)
	{
		if(imppParamType != null) {
			return imppParamTypes.contains(imppParamType);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsAllParams(List<ImppParamType> imppParamTypes)
	{
		if(imppParamTypes != null) {
			return this.imppParamTypes.containsAll(imppParamTypes);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasParams()
	{
		return !imppParamTypes.isEmpty();
	}

	@Override
	public void clearParams() {
		imppParamTypes.clear();
	}
	
	@Override
	public ImppType clone()
	{
		ImppType cloned = new ImppType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.addAllParams(imppParamTypes);
		cloned.setUri(imppUri);
		return cloned;
	}

	@Override
	public int compareTo(ImppType obj)
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
		
		if(!imppParamTypes.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(ImppParamType imppParamType : imppParamTypes) {
				sb.append(imppParamType.getType());
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[7] = sb.toString();
		}
		else {
			contents[7] = "";
		}
		
		contents[8] = (imppUri != null ? imppUri.toString() : "");
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof ImppType) {
				return this.compareTo((ImppType)obj) == 0;
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
