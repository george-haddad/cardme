package net.sourceforge.cardme.vcard.types;

import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.cardme.util.ISOFormat;
import net.sourceforge.cardme.util.ISOUtils;
import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.TimeZoneFeature;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.types.parameters.TimeZoneParameterType;

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
 * <p>
 * @author Michael Angstadt
 * <br/>
 * Jul 06, 2012
 *
 */
public class TimeZoneType extends Type implements TimeZoneFeature {

	private static final long serialVersionUID = 6773373072976797733L;
	
	private TimeZone timeZone = null;
	private int hourOffset = 0;
	private int minuteOffset = 0;
	private String shortText;
	private String longText;
	private TimeZoneParameterType timeZoneParameterType = null;
	
	public TimeZoneType() {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
	}
	
	public TimeZoneType(TimeZone timeZone) {
		this();
		setTimeZone(timeZone);
	}
	
	public TimeZoneType(int hourOffset, int minuteOffset) {
		this(hourOffset, minuteOffset, null, null);
	}
	
	public TimeZoneType(int hourOffset, int minuteOffset, String shortText, String longText) {
		this();
		this.hourOffset = hourOffset;
		this.minuteOffset = minuteOffset;
		this.shortText = shortText;
		this.longText = longText;
		calculateTimeZone();
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
	public String getShortText()
	{
		return shortText;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getLongText()
	{
		return longText;
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
	public TimeZoneParameterType getTimeZoneParameterType()
	{
		return timeZoneParameterType;
	}
	
	public void parseTimeZoneOffset(String iso8601Offset) {
		Pattern p = Pattern.compile("^([-\\+])?(\\d{1,2})(:?(\\d{2}))?$");
		Matcher m = p.matcher(iso8601Offset);
		
		if (!m.find()){
			throw new IllegalArgumentException("Offset string is not in ISO8610 format: " + iso8601Offset);
		}
		
		boolean positive;
		String sign = m.group(1);
		
		if ("+".equals(sign)){
			positive = true;
		}
		else if ("-".equals(sign)){
			positive = false;
		}
		else {
			positive = true;
		}
		
		String hourStr = m.group(2);
		hourOffset = Integer.parseInt(hourStr);
		
		if (!positive){
			hourOffset *= -1;
		}
		
		String minuteStr = m.group(4);
		
		if(minuteStr == null) {
			minuteOffset  = 0;
		}
		else {
			minuteOffset = Integer.parseInt(minuteStr);
		}
		
		
		calculateTimeZone();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTimeZone(TimeZone timeZone) {
		int offsetMillis = timeZone.getRawOffset();
		hourOffset = offsetMillis / 1000 / 60 / 60;
		minuteOffset = Math.abs((offsetMillis / 1000 / 60) % 60); //minute value must be positive
		shortText = null;
		longText = timeZone.getDisplayName();
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
		if (minuteOffset < 0 || minuteOffset > 59){
			throw new IllegalArgumentException("Minute offset must be a positive value between 0 and 59.");
		}

		this.minuteOffset = minuteOffset;
		calculateTimeZone();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setShortText(String shortText){
		this.shortText = shortText;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setLongText(String longText){
		this.longText = longText;
	}

	/**
	 * <p>Re-builds the TimeZone field based on the offset fields in this class.</p>
	 */
	private void calculateTimeZone() {
		int offsetMillis = hourOffset * 1000 * 60 * 60;
		int offsetMinuteMillis = minuteOffset * 1000 * 60;
		
		if (hourOffset < 0){
			offsetMinuteMillis *= -1; //minuteOffset is always positive, so negate this if hourOffset is negative
		}
		
		offsetMillis += offsetMinuteMillis;
		timeZone = new SimpleTimeZone(offsetMillis, "");
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
		
		if (timeZone != null){
			cloned.timeZone = (TimeZone)timeZone.clone();
		}
		
		cloned.shortText = shortText;
		cloned.longText = longText;
		cloned.hourOffset = hourOffset;
		cloned.minuteOffset = minuteOffset;
		cloned.setTimeZoneParameterType(timeZoneParameterType);
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
