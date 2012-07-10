package net.sourceforge.cardme.vcard.types;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.features.IMPPFeature;
import net.sourceforge.cardme.vcard.types.parameters.IMPPParameterType;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.types.parameters.URLParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XIMPPParameterType;
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
 * Jul 10, 2012
 *
 */
public class IMPPType extends Type implements IMPPFeature {

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
	
	public URI getURI() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setURI(URI uri) {
		// TODO Auto-generated method stub
		
	}

	public void clearURI() {
		// TODO Auto-generated method stub
		
	}

	public boolean hasURI() {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<IMPPParameterType> getIMPPParameterTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IMPPParameterType> getIMPPParameterTypesList() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getIMPPParameterSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addIMPPParameterType(IMPPParameterType imppParameterType) {
		// TODO Auto-generated method stub
		
	}

	public void removeIMPPParameterType(IMPPParameterType imppParameterType) {
		// TODO Auto-generated method stub
		
	}

	public boolean containsIMPPParameterType(IMPPParameterType imppParameterType) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAllIMPPParameterTypes(
			List<IMPPParameterType> imppParameterTypes) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasIMPPParameterTypes() {
		// TODO Auto-generated method stub
		return false;
	}

	public void clearIMPPParameterTypes() {
		// TODO Auto-generated method stub
		
	}

	public Iterator<XIMPPParameterType> getExtendedIMPPParameterTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<XIMPPParameterType> getExtendedIMPPParameterTypesList() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getExtendedIMPPParameterSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addExtendedIMPPParameterType(
			XIMPPParameterType xImppParameterType) {
		// TODO Auto-generated method stub
		
	}

	public void removeExtendedIMPPParameterType(
			XIMPPParameterType xImppParameterType) {
		// TODO Auto-generated method stub
		
	}

	public boolean containsExtendedIMPPParameterType(
			XIMPPParameterType xImppParameterType) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAllExtendedIMPPParameterTypes(
			List<XIMPPParameterType> xImppParameterTypes) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasExtendedIMPPParameterTypes() {
		// TODO Auto-generated method stub
		return false;
	}

	public void clearExtendedIMPPParameterTypes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTypeString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public IMPPFeature clone()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
