package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.util.ISOFormat;
import net.sourceforge.cardme.util.ISOUtils;
import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.TimeZoneFeature;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.types.parameters.TimeZoneParameterType;
import java.util.TimeZone;

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
public class TimeZoneType extends Type implements TimeZoneFeature {

	private static final long serialVersionUID = -6582710679559128155L;
	
	private TimeZone timeZone = null;
	private int hourOffset = 0;
	private int minuteOffset = 0;
	private String textValue = null;
	private TimeZoneParameterType timeZoneParameterType = null;
	
	public TimeZoneType() {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
	}
	
	public TimeZoneType(TimeZone timeZone) {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		setTimeZone(timeZone);
	}
	
	public TimeZoneType(int hourOffset, int minuteOffset) {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		setHourOffset(hourOffset);
		setMinuteOffset(minuteOffset);
	}
	
	public TimeZoneType(String textValue) {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		setTextValue(textValue);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TimeZone getTimeZone()
	{
		return timeZone;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getHourOffset()
	{
		return hourOffset;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getMinuteOffset()
	{
		return minuteOffset;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getIso8601Offset()
	{
		return ISOUtils.toISO8601_TimeZone(timeZone, ISOFormat.ISO8601_EXTENDED);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getTextValue()
	{
		return textValue;
	}

	/**
	 * {@inheritDoc}
	 */
	public TimeZoneParameterType getTimeZoneParameterType()
	{
		return timeZoneParameterType;
	}
	
	public void parseTimeZoneOffset(String iso8601Offset) {
		String[] tz = iso8601Offset.split(":");
		hourOffset = Integer.parseInt(tz[0]);
		minuteOffset = Integer.parseInt(tz[1]);
		calculateTimeZone();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTimeZoneParameterType(TimeZoneParameterType timeZoneParameterType) {
		this.timeZoneParameterType = timeZoneParameterType;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setHourOffset(int hourOffset) {
		this.hourOffset = hourOffset;
		calculateTimeZone();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setMinuteOffset(int minuteOffset) {
		this.minuteOffset = minuteOffset;
		calculateTimeZone();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTextValue(String textValue) {
		this.textValue = textValue;
		hourOffset = 0;
		minuteOffset = 0;
		timeZone = null;
	}

	/**
	 * {@inheritDoc}
	 */
	private void calculateTimeZone() {
		int offsetMillis = hourOffset + (minuteOffset / 10);
		offsetMillis = (((offsetMillis * 60) * 60) * 1000);
		timeZone.setRawOffset(offsetMillis);
		textValue = ISOUtils.toISO8601_TimeZone(timeZone, ISOFormat.ISO8601_EXTENDED);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.TZ.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof TimeZoneType) {
				if(this == obj || ((TimeZoneType)obj).hashCode() == this.hashCode()) {
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
		
		if(timeZone != null) {
			sb.append(timeZone.getRawOffset());
			sb.append(",");
		}
		
		if(timeZoneParameterType != null) {
			sb.append(timeZoneParameterType.getType());
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
	public TimeZoneFeature clone()
	{
		TimeZoneType cloned = new TimeZoneType();
		
		if(timeZone != null) {
			TimeZone tz = TimeZone.getDefault();
			tz.setRawOffset(timeZone.getRawOffset());
			cloned.setTimeZone(tz);
		}
		else if(textValue != null) {
			cloned.setTextValue(new String(textValue));
		}
		else {
			cloned.setTextValue(null);
			cloned.setTimeZone(null);
		}
		
		if(timeZoneParameterType != null) {
			cloned.setTimeZoneParameterType(timeZoneParameterType);
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
