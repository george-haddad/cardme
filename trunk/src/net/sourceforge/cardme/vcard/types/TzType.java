package net.sourceforge.cardme.vcard.types;

import java.util.Arrays;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sourceforge.cardme.util.ISOUtils;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.TzFeature;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.TzParamType;

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
 * Aug 8, 2012
 * <p>
 * @author Michael Angstadt
 * <br/>
 * Jul 06, 2012
 *
 */
public class TzType extends AbstractVCardType implements Comparable<TzType>, Cloneable, TzFeature {

	private static final long serialVersionUID = 9196257817130809348L;
	
	private TimeZone timeZone = null;
	private int hourOffset = 0;
	private int minuteOffset = 0;
	private String shortText = null;
	private String longText = null;
	private TzParamType tzParamType = null;
	
	public TzType() {
		this(null);
	}
	
	public TzType(TimeZone timeZone) {
		super(VCardTypeName.TZ);
		setTimeZone(timeZone);
	}
	
	public TzType(int hourOffset, int minuteOffset) {
		this(hourOffset, minuteOffset, null, null);
	}
	
	public TzType(int hourOffset, int minuteOffset, String shortText, String longText) {
		super(VCardTypeName.TZ);
		setOffset(hourOffset, minuteOffset);
		setShortText(shortText);
		setLongText(longText);
	}

	public TimeZone getTimeZone()
	{
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		if(timeZone != null) {
			int offsetMillis = timeZone.getRawOffset();
			hourOffset = offsetMillis / 1000 / 60 / 60;
			minuteOffset = Math.abs((offsetMillis / 1000 / 60) % 60); //minute value must be positive
			shortText = null;
			longText = timeZone.getDisplayName();
			this.timeZone = timeZone;
		}
		else {
			this.timeZone = null;
		}
	}

	public boolean hasTimeZone()
	{
		return timeZone != null;
	}

	public void clearTimeZone() {
		timeZone = null;
		hourOffset = 0;
		minuteOffset = 0;
		shortText = null;
		longText = null;
	}

	public int getHourOffset()
	{
		return hourOffset;
	}

	public int getMinuteOffset()
	{
		return minuteOffset;
	}

	public void setOffset(int hourOffset, int minuteOffset) throws IllegalArgumentException {
		if (minuteOffset < 0 || minuteOffset > 59){
			throw new IllegalArgumentException("Minute offset must be a positive value between 0 and 59.");
		}
		
		this.hourOffset = hourOffset;
		this.minuteOffset = minuteOffset;
		calculateTimeZone();
	}
	
	/**
	 * <p>Re-builds the TimeZone field based on the offset fields in this class.</p>
	 */
	private void calculateTimeZone() {
		int offsetMillis = hourOffset * 1000 * 60 * 60;
		int offsetMinuteMillis = minuteOffset * 1000 * 60;
		
		if (hourOffset < 0){
			//minuteOffset is always positive, so negate this if hourOffset is negative
			offsetMinuteMillis *= -1;
		}
		
		offsetMillis += offsetMinuteMillis;
		timeZone = new SimpleTimeZone(offsetMillis, "");
	}

	public String getShortText()
	{
		if(shortText != null) {
			return new String(shortText);
		}
		else {
			return null;
		}
	}

	public void setShortText(String shortText) {
		if(shortText != null) {
			this.shortText = new String(shortText);
		}
		else {
			this.shortText = null;
		}
	}

	public String getLongText()
	{
		if(longText != null) {
			return new String(longText);
		}
		else {
			return null;
		}
	}

	public void setLongText(String longText) {
		if(longText != null) {
			this.longText = new String(longText);
		}
		else {
			this.longText = null;
		}
	}

	public String getIso8601Offset()
	{
		if(timeZone != null) {
			return ISOUtils.formatISO8601TimeZone(timeZone, true);
		}
		else {
			return null;
		}
	}

	public void parseTimeZoneOffset(String iso8601Offset) throws IllegalArgumentException {
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
		int hourOffset = Integer.parseInt(hourStr);
		if (!positive){
			hourOffset *= -1;
		}
		
		String minuteStr = m.group(4);
		int minuteOffset = (minuteStr == null) ? 0 : Integer.parseInt(minuteStr);
		
		setOffset(hourOffset, minuteOffset);
	}

	public TzParamType getParamType()
	{
		return tzParamType;
	}

	public void setParamType(TzParamType tzParamType) {
		this.tzParamType = tzParamType;
	}

	public boolean hasParamType()
	{
		return tzParamType != null;
	}
	
	public boolean isUtcOffset()
	{
		return TzParamType.UTC_OFFSET.equals(tzParamType);
	}
	
	public boolean isText()
	{
		return TzParamType.TEXT.equals(tzParamType);
	}
	
	public boolean hasParams()
	{
		return false;
	}

	@Override
	public TzType clone()
	{
		TzType cloned = new TzType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.setParamType(tzParamType);
		cloned.setShortText(shortText);
		cloned.setLongText(longText);
		cloned.hourOffset = hourOffset;
		cloned.minuteOffset = minuteOffset;
		
		if(timeZone != null){
			cloned.setTimeZone((TimeZone)timeZone.clone());
		}
		else {
			cloned.setOffset(hourOffset, minuteOffset);
		}
		
		return cloned;
	}
	
	public int compareTo(TzType obj)
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
		String[] contents = new String[13];
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
		
		contents[7] = (tzParamType != null ? tzParamType.getType() : "");
		contents[8] = StringUtil.getString(shortText);
		contents[9] = StringUtil.getString(longText);
		contents[10] = String.valueOf(minuteOffset);
		contents[11] = String.valueOf(hourOffset);
		contents[12] = (timeZone != null ? String.valueOf(timeZone.getRawOffset()) : "");
		return contents;
		
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof TzType) {
				return this.compareTo((TzType)obj) == 0;
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
