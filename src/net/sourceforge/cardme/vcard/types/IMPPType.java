package net.sourceforge.cardme.vcard.types;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.IMPPFeature;
import net.sourceforge.cardme.vcard.types.parameters.IMPPParameterType;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.types.parameters.XIMPPParameterType;

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
 * Jul 10, 2012
 *
 */
public class IMPPType extends Type implements IMPPFeature {

	private static final long serialVersionUID = -2442145247654294068L;
	
	private URI uri = null;
	private List<IMPPParameterType> imppParameterTypes = null;
	private List<XIMPPParameterType> xtendedImppParameterTypes = null;
	
	public IMPPType() {
		this((URI)null);
	}
	
	public IMPPType(String uri) throws URISyntaxException {
		this(new URI(uri));
	}
	
	public IMPPType(URI uri) {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		imppParameterTypes = new ArrayList<IMPPParameterType>();
		xtendedImppParameterTypes = new ArrayList<XIMPPParameterType>();
		setURI(uri);
	}
	
	public URI getURI()
	{
		return uri;
	}

	public void setURI(URI uri) {
		this.uri = uri;
	}

	public void clearURI() {
		uri = null;
	}

	public boolean hasURI()
	{
		return uri != null;
	}

	public Iterator<IMPPParameterType> getIMPPParameterTypes()
	{
		return imppParameterTypes.listIterator();
	}

	public List<IMPPParameterType> getIMPPParameterTypesList()
	{
		return Collections.unmodifiableList(imppParameterTypes);
	}

	public int getIMPPParameterSize()
	{
		return imppParameterTypes.size();
	}

	public void addIMPPParameterType(IMPPParameterType imppParameterType) {
		imppParameterTypes.add(imppParameterType);
	}

	public void removeIMPPParameterType(IMPPParameterType imppParameterType) {
		imppParameterTypes.remove(imppParameterType);
	}

	public boolean containsIMPPParameterType(IMPPParameterType imppParameterType)
	{
		return imppParameterTypes.contains(imppParameterType);
	}

	public boolean containsAllIMPPParameterTypes(List<IMPPParameterType> imppParameterTypes) {
		return this.imppParameterTypes.containsAll(imppParameterTypes);
	}

	public boolean hasIMPPParameterTypes()
	{
		return !imppParameterTypes.isEmpty();
	}

	public void clearIMPPParameterTypes() {
		imppParameterTypes.clear();
	}

	public Iterator<XIMPPParameterType> getExtendedIMPPParameterTypes()
	{
		return xtendedImppParameterTypes.listIterator();
	}

	public List<XIMPPParameterType> getExtendedIMPPParameterTypesList()
	{
		return Collections.unmodifiableList(xtendedImppParameterTypes);
	}

	public int getExtendedIMPPParameterSize()
	{
		return xtendedImppParameterTypes.size();
	}

	public void addExtendedIMPPParameterType(XIMPPParameterType xImppParameterType) {
		xtendedImppParameterTypes.add(xImppParameterType);
	}

	public void removeExtendedIMPPParameterType(XIMPPParameterType xImppParameterType) {
		xtendedImppParameterTypes.remove(xImppParameterType);
	}

	public boolean containsExtendedIMPPParameterType(XIMPPParameterType xImppParameterType)
	{
		return xtendedImppParameterTypes.contains(xImppParameterType);
	}

	public boolean containsAllExtendedIMPPParameterTypes(List<XIMPPParameterType> xImppParameterTypes)
	{
		return this.xtendedImppParameterTypes.containsAll(xImppParameterTypes);
	}

	public boolean hasExtendedIMPPParameterTypes()
	{
		return !xtendedImppParameterTypes.isEmpty();
	}

	public void clearExtendedIMPPParameterTypes() {
		xtendedImppParameterTypes.clear();
	}

	@Override
	public String getTypeString()
	{
		return VCardType.IMPP.getType();
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof IMPPType) {
				if(this == obj || ((IMPPType)obj).hashCode() == this.hashCode()) {
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

	@Override
	public int hashCode()
	{
		return Util.generateHashCode(toString());
	}

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
		
		if(uri != null) {
			sb.append(uri);
			sb.append(",");
		}
		
		if(!imppParameterTypes.isEmpty()) {
			for(int i = 0; i < imppParameterTypes.size(); i++) {
				sb.append(imppParameterTypes.get(i).getType());
				sb.append(",");
			}
		}
		
		if(!xtendedImppParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedImppParameterTypes.size(); i++) {
				sb.append(xtendedImppParameterTypes.get(i).getType());
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
	
	@Override
	public IMPPFeature clone()
	{
		IMPPType cloned = new IMPPType();
		
		if(uri != null) {
			try {
				cloned.setURI(new URI(uri.toString()));
			}
			catch(URISyntaxException e) {
				cloned.setURI(null);
			}
		}
		
		if(!imppParameterTypes.isEmpty()) {
			for(int i = 0; i < imppParameterTypes.size(); i++) {
				cloned.addIMPPParameterType(imppParameterTypes.get(i));
			}
		}
		
		if(!xtendedImppParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedImppParameterTypes.size(); i++) {
				cloned.addExtendedIMPPParameterType(xtendedImppParameterTypes.get(i));
			}
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
