package net.sourceforge.cardme.vcard.types;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sourceforge.cardme.util.ISOFormat;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.BDayFeature;
import net.sourceforge.cardme.vcard.types.params.BDayParamType;
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
public class BDayType extends AbstractVCardType implements Comparable<BDayType>, Cloneable, BDayFeature {

	private static final long serialVersionUID = 3802012476318451978L;
	
	private Calendar birthday = null;
	private BDayParamType bdayParamType = null;
	private ISOFormat dateTimeFormat = ISOFormat.ISO8601_DATE_EXTENDED;
	
	public BDayType() {
		this((Calendar)null);
	}
	
	public BDayType(Date bdayDate) {
		super(VCardTypeName.BDAY);
		setBirthday(bdayDate);
	}
	
	public BDayType(Calendar bdayCal) {
		super(VCardTypeName.BDAY);
		setBirthday(bdayCal);
	}

	public Calendar getBirthday()
	{
		if(birthday != null) {
			return birthday;
		}
		else {
			return null;
		}
	}

	public void setBirthday(Calendar bdayCal) {
		if(bdayCal != null) {
			birthday = bdayCal;
		}
		else {
			birthday = null;
		}
	}

	public void setBirthday(Date bdayDate) {
		if(bdayDate != null) {
			birthday = Calendar.getInstance();
			birthday.setTime(bdayDate);
		}
		else {
			birthday = null;
		}
	}

	public void setParam(BDayParamType bdayParamType) {
		if(bdayParamType != null) {
			this.bdayParamType = bdayParamType;
		}
		else {
			this.bdayParamType = null;
		}
	}

	public BDayParamType getParam()
	{
		if(bdayParamType != null) {
			return bdayParamType;
		}
		else {
			return null;
		}
	}

	public void clearParam() {
		bdayParamType = null;
	}

	public boolean hasParam()
	{
		return bdayParamType != null;
	}

	public void setISO8601Format(ISOFormat dateTimeFormat) {
		if(dateTimeFormat != null) {
			this.dateTimeFormat = dateTimeFormat;
		}
		else {
			dateTimeFormat = ISOFormat.ISO8601_DATE_EXTENDED;
		}
	}

	public ISOFormat getISO8601Format()
	{
		if(bdayParamType != null) {
			switch(bdayParamType)
			{
				case DATE:
				{
					switch(dateTimeFormat)
					{
						case ISO8601_DATE_BASIC:
						case ISO8601_DATE_EXTENDED:
						{
							return dateTimeFormat;
						}
						
						default:
						{
							return ISOFormat.ISO8601_DATE_EXTENDED;
						}
					}
				}
				
				case DATE_TIME:
				{
					switch(dateTimeFormat)
					{
						case ISO8601_TIME_EXTENDED:
						case ISO8601_UTC_TIME_BASIC:
						case ISO8601_UTC_TIME_EXTENDED:
						{
							return dateTimeFormat;
						}
						
						default:
						{
							return ISOFormat.ISO8601_UTC_TIME_EXTENDED;
						}
					}
				}
				
				default:
				{
					return ISOFormat.ISO8601_UTC_TIME_EXTENDED;
				}
			}
		}
		else {
			return ISOFormat.ISO8601_UTC_TIME_EXTENDED;
		}
	}
	
	public boolean hasParams()
	{
		return false;
	}
	
	@Override
	public BDayType clone()
	{
		BDayType cloned = new BDayType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.setParam(bdayParamType);
		cloned.setBirthday(birthday);
		cloned.setISO8601Format(dateTimeFormat);
		return cloned;
	}
	
	public int compareTo(BDayType obj) 
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
		
		contents[7] = (birthday != null ? getBirthday().getTime().toString() : "");
		contents[8] = (bdayParamType != null ? getParam().getTypeName() : "");
		contents[9] = (dateTimeFormat != null ? dateTimeFormat.toString() : ISOFormat.ISO8601_DATE_EXTENDED.toString());
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof BDayType) {
				return this.compareTo((BDayType)obj) == 0;
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
