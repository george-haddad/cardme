package net.sourceforge.cardme.vcard.features;

import java.util.TimeZone;
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
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.4.1 TZ Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> TZ</li>
 * 	<li><b>Type purpose:</b> To specify information related to the time zone of the object the vCard represents.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> The default is a single utc-offset value. It can also be reset to a single text value.</li>
 * 	<li><b>Type special note:</b> The type value consists of a single value.</li>
 * </ul>
 * </p>
 */
public interface TzFeature {
	
	/**
	 * <p>Retrieves the time zone.</p>
	 * 
	 * @return the time zone or null if none set
	 */
	public TimeZone getTimeZone();
	
	/**
	 * <p>Sets the time zone as a {@link TimeZone} object.
	 * This method updates the minutes, hours, short and long texts.</p>
	 * 
	 * @param timeZone - the time zone to set
	 */
	public void setTimeZone(TimeZone timeZone);
	
	/**
	 * <p>Indicates if the time zone (specifically the
	 * internal {@link TimeZone} object) has been set.</p>
	 * 
	 * @return true if the time zone was set or false otherwise
	 */
	public boolean hasTimeZone();
	
	/**
	 * <p>Removes the time zone, minutes, hours, short
	 * and long texts.</p>
	 */
	public void clearTimeZone();
	
	/**
	 * <p>Retrieves the hour offset.</p>
	 * 
	 * @return the hour offset or zero if not set
	 */
	public int getHourOffset();
	
	/**
	 * <p>Retrieves the minute offset.</p>
	 * 
	 * @return the minute offset or zero if not set
	 */
	public int getMinuteOffset();
	
	/**
	 * <p>Sets the hour and minute offset. Performs a
	 * recalculation of the internal {@link TimeZone} object.</p>
	 * 
	 * @param hourOffset - the hour offset
	 * @param minuteOffset - the minute offset
	 * @throws IllegalArgumentException if the hour and minute offset are not positive integers and are not between 0 and 59
	 */
	public void setOffset(int hourOffset, int minuteOffset) throws IllegalArgumentException;
	
	/**
	 * <p>Retrieves the short text.</p>
	 * 
	 * @return the short text or null if not set
	 */
	public String getShortText();
	
	/**
	 * <p>Sets the short text.</p>
	 * 
	 * @param shortText - the short text to set
	 */
	public void setShortText(String shortText);
	
	/**
	 * <p>Retrieves the long text.</p>
	 * 
	 * @return the long text
	 */
	public String getLongText();
	
	/**
	 * <p>Sets the long text.</p>
	 * 
	 * @param longText - the long text to set
	 */
	public void setLongText(String longText);
	
	/**
	 * <p>Retrieves the extended ISO-8601 formatted time zone offset.</p>
	 * 
	 * @return the extended ISO-8601 formatted time zoneo offset
	 */
	public String getIso8601Offset();
	
	/**
	 * <p>Parses an ISO-8601 time zone offset and sets the time zone
	 * values accordingly.</p>
	 * 
	 * @param iso8601Offset - the ISO-8601 time zone offset matching regular expression <code>^([-\\+])?(\\d{1,2})(:?(\\d{2}))?$</code> 
	 * @throws IllegalArgumentException if string does not match against regular expression  <code>^([-\\+])?(\\d{1,2})(:?(\\d{2}))?$</code>
	 */
	public void parseTimeZoneOffset(String iso8601Offset) throws IllegalArgumentException;
	
	/**
	 * <p>Retrieves the time zone parameter type.</p>
	 * 
	 * @return the time zone parameter type or null if not set
	 */
	public TzParamType getParamType();
	
	/**
	 * <p>Sets the time zone parameter type.</p>
	 * 
	 * @param tzParamType - the time zone parameter type to set
	 */
	public void setParamType(TzParamType tzParamType);
	
	/**
	 * <p>Indicates if the time zone parameter type has been set.</p>
	 * 
	 * @return true if the time zone parameter type has been set or false otherwise
	 */
	public boolean hasParamType();
	
	/**
	 * <p>Indicates if the time zone parameter type has been 
	 * set to {@link TzParamType#UTC_OFFSET}.</p>
	 * 
	 * @return true if the time zone parameter type has been set to {@link TzParamType#UTC_OFFSET} or false otherwise
	 */
	public boolean isUtcOffset();
	
	/**
	 * <p>Indicates if the time zone parameter type has been 
	 * set to {@link TzParamType#TEXT}.</p>
	 * 
	 * @return true if the time zone parameter type has been set to {@link TzParamType#TEXT} or false otherwise
	 */
	public boolean isText();
}
