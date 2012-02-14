package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.EmailFeature;
import net.sourceforge.cardme.vcard.types.parameters.EmailParameterType;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.types.parameters.XEmailParameterType;
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
public class EmailType extends Type implements EmailFeature {

	private String email = null;
	private List<EmailParameterType> emailParameterTypes = null;
	private List<XEmailParameterType> xtendedEmailParameterTypes = null;
	private boolean isSetCompression = false;
	
	public EmailType() {
		this(null);
	}
	
	public EmailType(String email) {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		emailParameterTypes = new ArrayList<EmailParameterType>();
		xtendedEmailParameterTypes = new ArrayList<XEmailParameterType>();
		setEmail(email);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<EmailParameterType> getEmailParameterTypes()
	{
		return emailParameterTypes.listIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<EmailParameterType> getEmailParameterTypesList()
	{
		return Collections.unmodifiableList(emailParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getEmailParameterSize()
	{
		return emailParameterTypes.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addEmailParameterType(EmailParameterType emailParameterType) {
		emailParameterTypes.add(emailParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeEmailParameterType(EmailParameterType emailParameterType) {
		emailParameterTypes.remove(emailParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean containsEmailParameterType(EmailParameterType emailParameterType)
	{
		return emailParameterTypes.contains(emailParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsAllEmailParameterTypes(List<EmailParameterType> emailParameterTypes)
	{
		return this.emailParameterTypes.containsAll(emailParameterTypes);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasEmailParameterTypes()
	{
		return !emailParameterTypes.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearEmailParameterTypes() {
		emailParameterTypes.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<XEmailParameterType> getExtendedEmailParameterTypes()
	{
		return xtendedEmailParameterTypes.listIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<XEmailParameterType> getExtendedEmailParameterTypesList()
	{
		return Collections.unmodifiableList(xtendedEmailParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getExtendedEmailParameterSize()
	{
		return xtendedEmailParameterTypes.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addExtendedEmailParameterType(XEmailParameterType xtendedEmailParameterType) {
		xtendedEmailParameterTypes.add(xtendedEmailParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeExtendedEmailParameterType(XEmailParameterType xtendedEmailParameterType) {
		xtendedEmailParameterTypes.remove(xtendedEmailParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsExtendedEmailParameterType(XEmailParameterType xtendedEmailParameterType)
	{
		return xtendedEmailParameterTypes.contains(xtendedEmailParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsAllExtendedEmailParameterTypes(List<XEmailParameterType> xtendedEmailParameterTypes)
	{
		return this.xtendedEmailParameterTypes.containsAll(xtendedEmailParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasExtendedEmailParameterTypes()
	{
		return !xtendedEmailParameterTypes.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearExtendedEmailParameterTypes() {
		xtendedEmailParameterTypes.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasEmail()
	{
		return email != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.EMAIL.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setCompression(boolean compress) {
		isSetCompression = compress;
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isSetCompression()
	{
		return isSetCompression;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof EmailType) {
				if(this == obj || ((EmailType)obj).hashCode() == this.hashCode()) {
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
		
		if(email != null) {
			sb.append(email);
			sb.append(",");
		}
		
		if(!emailParameterTypes.isEmpty()) {
			for(int i = 0; i < emailParameterTypes.size(); i++) {
				sb.append(emailParameterTypes.get(i).getType());
				sb.append(",");
			}
		}
		
		if(!xtendedEmailParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedEmailParameterTypes.size(); i++) {
				sb.append(xtendedEmailParameterTypes.get(i).getType());
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
	public EmailFeature clone()
	{
		EmailType cloned = new EmailType();
		
		if(email != null) {
			cloned.setEmail(new String(email));
		}
		
		if(!emailParameterTypes.isEmpty()) {
			for(int i = 0; i < emailParameterTypes.size(); i++) {
				cloned.addEmailParameterType(emailParameterTypes.get(i));
			}
		}
		
		if(!xtendedEmailParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedEmailParameterTypes.size(); i++) {
				cloned.addExtendedEmailParameterType(xtendedEmailParameterTypes.get(i));
			}
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
