package net.sourceforge.cardme.vcard.types;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.UrlFeature;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.UrlParamType;

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
 * Aug 9, 2012
 *
 */
public class UrlType extends AbstractVCardType implements Comparable<UrlType>, Cloneable, UrlFeature {

	private static final long serialVersionUID = 7848004813463733276L;
	
	private URL url = null;
	private String rawUrl = null;
	private List<UrlParamType> urlParamTypes = new ArrayList<UrlParamType>();
	
	public UrlType() {
		this((String)null);
	}
	
	public UrlType(URL url) {
		super(VCardTypeName.URL);
		setUrl(url);
	}
	
	public UrlType(String rawUrl) {
		super(VCardTypeName.URL);
		setRawUrl(rawUrl);
	}

	@Override
	public URL getUrl()
	{
		return url;
	}

	@Override
	public String getRawUrl()
	{
		if(rawUrl != null) {
			return new String(rawUrl);
		}
		else {
			return null;
		}
	}

	@Override
	public void setUrl(URL url) {
		this.url = url;
		
		if(url != null) {
			rawUrl = url.toString();
		}
	}

	@Override
	public void setRawUrl(String rawUrl) {
		if(rawUrl != null) {
			this.rawUrl = new String(rawUrl);
			
			try {
				this.url = new URL(rawUrl);
			}
			catch(MalformedURLException ex) {
				this.url = null;
			}
		}
		else {
			this.rawUrl = null;
		}
	}

	@Override
	public void clearUrl() {
		url = null;
		rawUrl = null;
	}

	@Override
	public boolean hasUrl()
	{
		return url != null;
	}

	@Override
	public boolean hasRawUrl()
	{
		return rawUrl != null;
	}

	@Override
	public List<UrlParamType> getParams()
	{
		return Collections.unmodifiableList(urlParamTypes);
	}

	@Override
	public int getParamSize()
	{
		return urlParamTypes.size();
	}

	@Override
	public UrlType addParam(UrlParamType urlParamType) throws NullPointerException {
		if(urlParamType == null) {
			throw new NullPointerException("Cannot add a null urlParamType.");
		}
		
		urlParamTypes.add(urlParamType);
		return this;
	}

	@Override
	public UrlType addAllParams(List<UrlParamType> urlParamTypes) throws NullPointerException {
		if(urlParamTypes == null) {
			throw new NullPointerException("Cannot add a null urlParamTypes list.");
		}
		
		this.urlParamTypes.addAll(urlParamTypes);
		return this;
	}

	@Override
	public UrlType removeParam(UrlParamType urlParamType) throws NullPointerException {
		if(urlParamType == null) {
			throw new NullPointerException("Cannot add a null urlParamType.");
		}
		
		urlParamTypes.remove(urlParamType);
		return this;
	}

	@Override
	public boolean containsParam(UrlParamType urlParamType)
	{
		if(urlParamType != null) {
			return urlParamTypes.contains(urlParamType);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsAllParams(List<UrlParamType> urlParamTypes)
	{
		if(urlParamTypes != null) {
			return urlParamTypes.containsAll(urlParamTypes);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasParams()
	{
		return !urlParamTypes.isEmpty();
	}

	@Override
	public void clearParams() {
		urlParamTypes.clear();
	}
	
	@Override
	public UrlType clone()
	{
		UrlType cloned = new UrlType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.addAllParams(urlParamTypes);
		
		//This will set the URL as well,
		//if it can't then it will stay null.
		if(rawUrl != null) {
			cloned.setRawUrl(rawUrl);
		}
		
		return cloned;
	}
	
	@Override
	public int compareTo(UrlType obj)
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
		String[] contents = new String[10];
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
		
		if(!urlParamTypes.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(UrlParamType urlParamType : urlParamTypes) {
				sb.append(urlParamType.getType());
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[7] = sb.toString();
		}
		else {
			contents[7] = "";
		}
		
		contents[8] = StringUtil.getString(rawUrl);
		contents[9] = (url != null ? url.toString() : "");
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof UrlType) {
				return this.compareTo((UrlType)obj) == 0;
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
