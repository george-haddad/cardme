package net.sourceforge.cardme.vcard.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.AdrFeature;
import net.sourceforge.cardme.vcard.types.params.AdrParamType;
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
public class AdrType extends AbstractVCardType implements Comparable<AdrType>, Cloneable, AdrFeature {

	private static final long serialVersionUID = 8259837978275069812L;
	
	private String postOfficeBox = null;
	private String extendedAddress = null;
	private String streetAddress = null;
	private String locality = null;
	private String region = null;
	private String postalCode = null;
	private String countryName = null;
	private List<AdrParamType> adrParamTypes = null;
	private LabelType label = null;
	
	public AdrType() {
		this(null);
	}
	
	public AdrType(String streetAddress) {
		super(VCardTypeName.ADR);
		adrParamTypes = new ArrayList<AdrParamType>();
		setStreetAddress(streetAddress);
	}

	@Override
	public String getPostOfficeBox()
	{
		if(postOfficeBox != null) {
			return new String(postOfficeBox);
		}
		else {
			return null;
		}
	}

	@Override
	public void setPostOfficeBox(String postOfficeBox) {
		if(postOfficeBox != null) {
			this.postOfficeBox = new String(postOfficeBox);
		}
		else {
			this.postOfficeBox = null;
		}
	}

	@Override
	public boolean hasPostOfficebox()
	{
		return postOfficeBox != null;
	}

	@Override
	public String getExtendedAddress()
	{
		if(extendedAddress != null) {
			return new String(extendedAddress);
		}
		else {
			return null;
		}
	}

	@Override
	public void setExtendedAddress(String extendedAddress) {
		if(extendedAddress != null) {
			this.extendedAddress = new String(extendedAddress);
		}
		else {
			this.extendedAddress = null;
		}
	}

	@Override
	public boolean hasExtendedAddress()
	{
		return extendedAddress != null;
	}

	@Override
	public String getStreetAddress()
	{
		if(streetAddress != null) {
			return new String(streetAddress);
		}
		else {
			return null;
		}
	}

	@Override
	public void setStreetAddress(String streetAddress) {
		if(streetAddress != null) {
			this.streetAddress = new String(streetAddress);
		}
		else {
			this.streetAddress = null;
		}
	}

	@Override
	public boolean hasStreetAddress()
	{
		return streetAddress != null;
	}

	@Override
	public String getLocality()
	{
		if(locality != null) {
			return new String(locality);
		}
		else {
			return null;
		}
	}

	@Override
	public void setLocality(String locality) {
		if(locality != null) {
			this.locality = new String(locality);
		}
		else {
			this.locality = null;
		}
	}

	@Override
	public boolean hasLocality()
	{
		return locality != null;
	}

	@Override
	public String getRegion()
	{
		if(region != null) {
			return new String(region);
		}
		else {
			return null;
		}
	}

	@Override
	public void setRegion(String region) {
		if(region != null) {
			this.region = new String(region);
		}
		else {
			this.region = null;
		}
	}

	@Override
	public boolean hasRegion()
	{
		return region != null;
	}

	@Override
	public String getPostalCode()
	{
		if(postalCode != null) {
			return new String(postalCode);
		}
		else {
			return null;
		}
	}

	@Override
	public void setPostalCode(String postalCode) {
		if(postalCode != null) {
			this.postalCode = new String(postalCode);
		}
		else {
			this.postalCode = null;
		}
	}

	@Override
	public boolean hasPostalCode()
	{
		return postalCode != null;
	}

	@Override
	public String getCountryName()
	{
		if(countryName != null) {
			return new String(countryName);
		}
		else {
			return null;
		}
	}

	@Override
	public void setCountryName(String countryName) {
		if(countryName != null) {
			this.countryName = new String(countryName);
		}
		else {
			this.countryName = null;
		}
	}

	@Override
	public boolean hasCountryName()
	{
		return countryName != null;
	}
	
	public LabelType getLabel()
	{
		if(label != null) {
			return label;
		}
		else {
			return null;
		}
	}
	
	public void setLabel(LabelType label) {
		if(label != null) {
			this.label = label;
		}
		else {
			this.label = null;
		}
	}
	
	public boolean hasLabel()
	{
		return label != null;
	}
	
	public void clearLabel() {
		label = null;
	}
	
	@Override
	public List<AdrParamType> getParams()
	{
		return Collections.unmodifiableList(adrParamTypes);
	}

	@Override
	public AdrType addParam(AdrParamType adrParamType) throws NullPointerException {
		if(adrParamType == null) {
			throw new NullPointerException("Cannot add a null adrParamType.");
		}
		
		adrParamTypes.add(adrParamType);
		return this;
	}

	@Override
	public AdrType addAllParams(List<AdrParamType> adrParamTypes) throws NullPointerException {
		if(adrParamTypes == null) {
			throw new NullPointerException("Cannot add a null adrParamTypes list.");
		}
		
		this.adrParamTypes.addAll(adrParamTypes);
		return this;
	}

	@Override
	public AdrType removeParam(AdrParamType adrParamType) throws NullPointerException {
		if(adrParamType == null) {
			throw new NullPointerException("Cannot remove a null adrParamType.");
		}
		
		adrParamTypes.remove(adrParamType);
		return this;
	}

	@Override
	public boolean containsParam(AdrParamType adrParamType)
	{
		if(adrParamType != null) {
			return adrParamTypes.contains(adrParamType);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsAllParams(List<AdrParamType> adrParamTypes)
	{
		if(adrParamTypes != null) {
			return this.adrParamTypes.containsAll(adrParamTypes);
		}
		else {
			return false;
		}
	}

	@Override
	public int getParamSize()
	{
		return adrParamTypes.size();
	}

	@Override
	public boolean hasParams()
	{
		return !adrParamTypes.isEmpty();
	}

	@Override
	public void clearParams() {
		adrParamTypes.clear();
	}
	
	@Override
	public AdrType clone()
	{
		AdrType cloned = new AdrType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.setPostOfficeBox(postOfficeBox);
		cloned.setExtendedAddress(extendedAddress);
		cloned.setStreetAddress(streetAddress);
		cloned.setLocality(locality);
		cloned.setRegion(region);
		cloned.setPostalCode(postalCode);
		cloned.setCountryName(countryName);
		cloned.addAllParams(adrParamTypes);
		cloned.setLabel(label);
		return cloned;
	}

	@Override
	public int compareTo(AdrType obj)
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
		String[] contents = new String[16];
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
		
		contents[7] = StringUtil.getString(postOfficeBox);
		contents[8] = StringUtil.getString(extendedAddress);
		contents[9] = StringUtil.getString(streetAddress);
		contents[10] = StringUtil.getString(locality);
		contents[11] = StringUtil.getString(region);
		contents[12] = StringUtil.getString(postalCode);
		contents[13] = StringUtil.getString(countryName);
		
		if(!adrParamTypes.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(AdrParamType adrParamType: adrParamTypes) {
				sb.append(adrParamType.getType());
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[14] = sb.toString();
		}
		else {
			contents[14] = "";
		}
		
		contents[15] = (label != null ? label.toString() : "");
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof AdrType) {
				return this.compareTo((AdrType)obj) == 0;
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
