package info.ineighborhood.cardme.vcard.types;

import info.ineighborhood.cardme.util.Util;
import info.ineighborhood.cardme.vcard.EncodingType;
import info.ineighborhood.cardme.vcard.VCardType;
import info.ineighborhood.cardme.vcard.features.TelephoneFeature;
import info.ineighborhood.cardme.vcard.types.parameters.ParameterTypeStyle;
import info.ineighborhood.cardme.vcard.types.parameters.TelephoneParameterType;
import info.ineighborhood.cardme.vcard.types.parameters.XTelephoneParameterType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
public class TelephoneType extends Type implements TelephoneFeature {

	private String telephone = null;
	private List<TelephoneParameterType> telephoneParameterTypes = null;
	private List<XTelephoneParameterType> xtendedTelephoneParameterTypes = null;
	
	public TelephoneType() {
		this(null);
	}
	
	public TelephoneType(String telephone) {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		setTelephone(telephone);
		telephoneParameterTypes = new ArrayList<TelephoneParameterType>();
		xtendedTelephoneParameterTypes = new ArrayList<XTelephoneParameterType>();
	}
	
	public TelephoneType(String telephone, TelephoneParameterType phoneType) {
		this(telephone);
		this.addTelephoneParameterType(phoneType);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getTelephone()
	{
		return telephone;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterator<TelephoneParameterType> getTelephoneParameterTypes()
	{
		return telephoneParameterTypes.listIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<TelephoneParameterType> getTelephoneParameterTypesList()
	{
		return Collections.unmodifiableList(telephoneParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getTelephoneParameterSize()
	{
		return telephoneParameterTypes.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public void addTelephoneParameterType(TelephoneParameterType telephoneParameterType) {
		telephoneParameterTypes.add(telephoneParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeTelephoneParameterType(TelephoneParameterType telephoneParameterType) {
		telephoneParameterTypes.remove(telephoneParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean containsTelephoneParameterType(TelephoneParameterType telephoneParameterType)
	{
		return telephoneParameterTypes.contains(telephoneParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasTelephoneParameterTypes()
	{
		return !telephoneParameterTypes.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearTelephoneParameterTypes() {
		telephoneParameterTypes.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<XTelephoneParameterType> getExtendedTelephoneParameterTypes()
	{
		return xtendedTelephoneParameterTypes.listIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<XTelephoneParameterType> getExtendedTelephoneParameterTypesList()
	{
		return Collections.unmodifiableList(xtendedTelephoneParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getExtendedTelephoneParameterSize()
	{
		return xtendedTelephoneParameterTypes.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addExtendedTelephoneParameterType(XTelephoneParameterType xtendedTelephoneParameterType) {
		xtendedTelephoneParameterTypes.add(xtendedTelephoneParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeExtendedTelephoneParameterType(XTelephoneParameterType xtendedTelephoneParameterType) {
		xtendedTelephoneParameterTypes.remove(xtendedTelephoneParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsExtendedTelephoneParameterType(XTelephoneParameterType xtendedTelephoneParameterType)
	{
		return xtendedTelephoneParameterTypes.contains(xtendedTelephoneParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasExtendedTelephoneParameterTypes()
	{
		return !xtendedTelephoneParameterTypes.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearExtendedTelephoneParameterTypes() {
		xtendedTelephoneParameterTypes.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasTelephone()
	{
		return telephone != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.TEL.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof TelephoneType) {
				if(this == obj || ((TelephoneType)obj).hashCode() == this.hashCode()) {
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
		
		if(telephone != null) {
			sb.append(telephone);
			sb.append(",");
		}
		
		if(!telephoneParameterTypes.isEmpty()) {
			for(int i = 0; i < telephoneParameterTypes.size(); i++) {
				sb.append(telephoneParameterTypes.get(i).getType());
				sb.append(",");
			}
		}
		
		if(!xtendedTelephoneParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedTelephoneParameterTypes.size(); i++) {
				sb.append(xtendedTelephoneParameterTypes.get(i).getType());
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
	public TelephoneFeature clone()
	{
		TelephoneType cloned = new TelephoneType();
		
		if(telephone != null) {
			cloned.setTelephone(new String(telephone));
		}
		
		if(!telephoneParameterTypes.isEmpty()) {
			for(int i = 0; i < telephoneParameterTypes.size(); i++) {
				cloned.addTelephoneParameterType(telephoneParameterTypes.get(i));
			}
		}
		
		if(!xtendedTelephoneParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedTelephoneParameterTypes.size(); i++) {
				cloned.addExtendedTelephoneParameterType(xtendedTelephoneParameterTypes.get(i));
			}
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
