package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.BirthdayFeature;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import java.util.Calendar;
import java.util.Date;

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
public class BirthdayType extends Type implements BirthdayFeature {

	private Calendar birthday = null;
	
	public BirthdayType() {
		this((Calendar)null);
	}
	
	public BirthdayType(Calendar birthday) {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		setBirthday(birthday);
		
	}
	
	public BirthdayType(Date birthday) {
		super(EncodingType.EIGHT_BIT);
		setBirthday(birthday);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Calendar getBirthday()
	{
		return birthday;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setBirthday(Date date) {
		birthday.setTime(date);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.BDAY.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof BirthdayType) {
				if(this == obj || ((BirthdayType)obj).hashCode() == this.hashCode()) {
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
		
		if(birthday != null) {
			sb.append(birthday.getTime().toString());
			sb.append(",");
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
	public BirthdayFeature clone()
	{
		BirthdayType cloned = new BirthdayType();
		
		if(birthday != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(birthday.getTimeInMillis());
			cloned.setBirthday(cal);
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
