package net.sourceforge.cardme.vcard.types;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.URLFeature;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.types.parameters.URLParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XURLParameterType;

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
 * Feb 4, 2010
 *
 */
public class URLType extends Type implements URLFeature {

	private static final long serialVersionUID = 4110983706732337413L;
	
	private URL url = null;
	private List<URLParameterType> urlParameterTypes = null;
	private List<XURLParameterType> xtendedUrlParameterTypes = null;
	
	public URLType() {
		this((URL)null);
	}
	
	public URLType(String url) throws MalformedURLException {
		this(new URL(url));
	}
	
	public URLType(URL url) {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		urlParameterTypes = new ArrayList<URLParameterType>();
		xtendedUrlParameterTypes = new ArrayList<XURLParameterType>();
		setURL(url);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public URL getURL()
	{
		return url;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setURL(URL url) {
		this.url = url;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearURL() {
		url = null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasURL()
	{
		return url != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.URL.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<URLParameterType> getURLParameterTypes()
	{
		return urlParameterTypes.listIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<URLParameterType> getURLParameterTypesList()
	{
		return Collections.unmodifiableList(urlParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getURLParameterSize()
	{
		return urlParameterTypes.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public void addURLParameterType(URLParameterType urlParameterType) {
		urlParameterTypes.add(urlParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeURLParameterType(URLParameterType urlParameterType) {
		urlParameterTypes.remove(urlParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean containsURLParameterType(URLParameterType urlParameterType)
	{
		return urlParameterTypes.contains(urlParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsAllURLParameterTypes(List<URLParameterType> urlParameterTypes)
	{
		return this.urlParameterTypes.containsAll(urlParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasURLParameterTypes()
	{
		return !urlParameterTypes.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearURLParameterTypes() {
		urlParameterTypes.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<XURLParameterType> getExtendedURLParameterTypes()
	{
		return xtendedUrlParameterTypes.listIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<XURLParameterType> getExtendedURLParameterTypesList()
	{
		return Collections.unmodifiableList(xtendedUrlParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getExtendedURLParameterSize()
	{
		return xtendedUrlParameterTypes.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addExtendedURLParameterType(XURLParameterType xtendedUrlParameterType) {
		xtendedUrlParameterTypes.add(xtendedUrlParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeExtendedURLParameterType(XURLParameterType xtendedUrlParameterType) {
		xtendedUrlParameterTypes.remove(xtendedUrlParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsExtendedURLParameterType(XURLParameterType xtendedUrlParameterType)
	{
		return xtendedUrlParameterTypes.contains(xtendedUrlParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsAllExtendedURLParameterTypes(List<XURLParameterType> xtendedUrlParameterTypes)
	{
		return this.xtendedUrlParameterTypes.containsAll(xtendedUrlParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasExtendedURLParameterTypes()
	{
		return !xtendedUrlParameterTypes.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearExtendedURLParameterTypes() {
		xtendedUrlParameterTypes.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof URLType) {
				if(this == obj || ((URLType)obj).hashCode() == this.hashCode()) {
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
		if(encodingType != null) {
			sb.append(encodingType.getType());
			sb.append(",");
		}
		
		if(url != null) {
			sb.append(url);
			sb.append(",");
		}
		
		if(!urlParameterTypes.isEmpty()) {
			for(int i = 0; i < urlParameterTypes.size(); i++) {
				sb.append(urlParameterTypes.get(i).getType());
				sb.append(",");
			}
		}
		
		if(!xtendedUrlParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedUrlParameterTypes.size(); i++) {
				sb.append(xtendedUrlParameterTypes.get(i).getType());
				sb.append(",");
			}
		}

		if(super.id != null) {
			sb.append(super.id);
			sb.append(",");
		}
		
		sb.deleteCharAt(sb.length()-1);	//Remove last comma.
		sb.append(" ]");
		return sb.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public URLFeature clone()
	{
		URLType cloned = new URLType();
		
		if(url != null) {
			try {
				cloned.setURL(new URL(url.toString()));
			}
			catch(MalformedURLException e) {
				cloned.setURL(null);
			}
		}
		
		if(!urlParameterTypes.isEmpty()) {
			for(int i = 0; i < urlParameterTypes.size(); i++) {
				cloned.addURLParameterType(urlParameterTypes.get(i));
			}
		}
		
		if(!xtendedUrlParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedUrlParameterTypes.size(); i++) {
				cloned.addExtendedURLParameterType(xtendedUrlParameterTypes.get(i));
			}
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
