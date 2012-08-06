package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.AddressFeature;
import net.sourceforge.cardme.vcard.types.parameters.AddressParameterType;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.types.parameters.XAddressParameterType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
 * Feb 4, 2010
 *
 */
public class AddressType extends Type implements AddressFeature {

	private static final long serialVersionUID = 1841762380980580662L;
	
	private String postOfficeBox = null;
	private String extendedAddress = null;
	private String streetAddress = null;
	private String locality = null;
	private String region = null;
	private String postalCode = null;
	private String countryName = null;
	private List<AddressParameterType> addressParameterTypes = null;
	private List<XAddressParameterType> xtendedAddressParameterTypes = null;
	
	public AddressType() {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		addressParameterTypes = new ArrayList<AddressParameterType>();
		xtendedAddressParameterTypes = new ArrayList<XAddressParameterType>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getPostOfficeBox()
	{
		return postOfficeBox;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getExtendedAddress()
	{
		return extendedAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getStreetAddress()
	{
		return streetAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getLocality()
	{
		return locality;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getRegion()
	{
		return region;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getPostalCode()
	{
		return postalCode;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getCountryName()
	{
		return countryName;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setStreetAddress(String streetAddress)
	{
		this.streetAddress = streetAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPostOfficeBox(String postOfficeBox) {
		this.postOfficeBox = postOfficeBox;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setExtendedAddress(String extendedAddress) {
		this.extendedAddress = extendedAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterator<AddressParameterType> getAddressParameterTypes()
	{
		return addressParameterTypes.listIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<AddressParameterType> getAddressParameterTypesList()
	{
		return Collections.unmodifiableList(addressParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getAddressParameterSize()
	{
		return addressParameterTypes.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public void addAddressParameterType(AddressParameterType addressParameterType) {
		addressParameterTypes.add(addressParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeAddressParameterType(AddressParameterType addressParameterType) {
		addressParameterTypes.remove(addressParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean containsAddressParameterType(AddressParameterType addressParameterType)
	{
		return addressParameterTypes.contains(addressParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsAllAddressParameterTypes(List<AddressParameterType> addressParameterTypes)
	{
		return this.addressParameterTypes.containsAll(addressParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearAddressParameterTypes() {
		addressParameterTypes.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<XAddressParameterType> getExtendedAddressParameterTypes()
	{
		return xtendedAddressParameterTypes.listIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<XAddressParameterType> getExtendedAddressParameterTypesList()
	{
		return Collections.unmodifiableList(xtendedAddressParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getExtendedAddressParameterSize()
	{
		return xtendedAddressParameterTypes.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addExtendedAddressParameterType(XAddressParameterType xtendedAddressParameterType) {
		xtendedAddressParameterTypes.add(xtendedAddressParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeExtendedAddressParameterType(XAddressParameterType xtendedAddressParameterType) {
		xtendedAddressParameterTypes.remove(xtendedAddressParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsExtendedAddressParameterType(XAddressParameterType xtendedAddressParameterType)
	{
		return xtendedAddressParameterTypes.contains(xtendedAddressParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsAllExtendedAddressParameterTypes(List<XAddressParameterType> xtendedAddressParameterTypes)
	{
		return this.xtendedAddressParameterTypes.containsAll(xtendedAddressParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasExtendedAddressParameterTypes()
	{
		return !xtendedAddressParameterTypes.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasPostOfficebox()
	{
		return postOfficeBox != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasExtendedAddress()
	{
		return extendedAddress != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasLocality()
	{
		return locality != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasRegion()
	{
		return region != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasPostalCode()
	{
		return postalCode != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasCountryName()
	{
		return countryName != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasStreetAddress()
	{
		return streetAddress != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasAddressParameterTypes()
	{
		return !addressParameterTypes.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.ADR.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof AddressType) {
				if(this == obj || ((AddressType)obj).hashCode() == this.hashCode()) {
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
		
		if(postOfficeBox != null) {
			sb.append(postOfficeBox);
			sb.append(",");
		}
		
		if(extendedAddress != null) {
			sb.append(extendedAddress);
			sb.append(",");
		}
		
		if(streetAddress != null) {
			sb.append(streetAddress);
			sb.append(",");
		}
		
		if(locality != null) {
			sb.append(locality);
			sb.append(",");
		}
		
		if(region != null) {
			sb.append(region);
			sb.append(",");
		}
		
		if(postalCode != null) {
			sb.append(postalCode);
			sb.append(",");
		}
		
		if(countryName != null) {
			sb.append(countryName);
			sb.append(",");
		}
		
		if(!addressParameterTypes.isEmpty()) {
			for(int i = 0; i < addressParameterTypes.size(); i++) {
				sb.append(addressParameterTypes.get(i).getType());
				sb.append(",");
			}
		}
		
		if(!xtendedAddressParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedAddressParameterTypes.size(); i++) {
				sb.append(xtendedAddressParameterTypes.get(i).getType());
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
	public AddressFeature clone()
	{
		AddressType cloned = new AddressType();
		
		if(postOfficeBox != null) {
			cloned.setPostOfficeBox(new String(postOfficeBox));
		}
		
		if(extendedAddress != null) {
			cloned.setExtendedAddress(new String(extendedAddress));
		}
		
		if(streetAddress != null) {
			cloned.setStreetAddress(new String(streetAddress));
		}
		
		if(locality != null) {
			cloned.setLocality(new String(locality));
		}
		
		if(region != null) {
			cloned.setRegion(new String(region));
		}
		
		if(postalCode != null) {
			cloned.setPostalCode(new String(postalCode));
		}
		
		if(countryName != null) {
			cloned.setCountryName(new String(countryName));
		}
		
		if(!addressParameterTypes.isEmpty()) {
			for(int i = 0; i < addressParameterTypes.size(); i++) {
				cloned.addAddressParameterType(addressParameterTypes.get(i));
			}
		}
		
		if(!xtendedAddressParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedAddressParameterTypes.size(); i++) {
				cloned.addExtendedAddressParameterType(xtendedAddressParameterTypes.get(i));
			}
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
