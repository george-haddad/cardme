package info.ineighborhood.cardme.vcard.features;

import info.ineighborhood.cardme.vcard.types.parameters.TimeZoneParameterType;
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
public interface TimeZoneFeature extends TypeTools {
	
	/**
	 * <p>Returns the hour offset.</p>
	 *
	 * @return int
	 */
	public int getHourOffset();
	
	/**
	 * <p>Returns the minute offset.</p>
	 *
	 * @return int
	 */
	public int getMinuteOffset();
	
	/**
	 * <p>Returns the time zone.</p>
	 *
	 * @return {@link TimeZone}
	 */
	public TimeZone getTimeZone();
	
	/**
	 * <p>Returns the time zone in ISO-8601 format.</p>
	 *
	 * @return {@link String}
	 */
	public String getIso8601Offset();
	
	/**
	 * <p>Returns the time zone as a text value.</p>
	 *
	 * @return {@link String}
	 */
	public String getTextValue();
	
	/**
	 * <p>Returns the parameter type.</p>
	 *
	 * @return {@link TimeZoneParameterType}
	 */
	public TimeZoneParameterType getTimeZoneParameterType();
	
	/**
	 * <p>Sets the hour offset.</p>
	 *
	 * @param hourOffset
	 */
	public void setHourOffset(int hourOffset);
	
	/**
	 * <p>Sets the minute offset.</p>
	 *
	 * @param minuteOffset
	 */
	public void setMinuteOffset(int minuteOffset);
	
	/**
	 * <p>Sets the time zone</p>
	 *
	 * @param timeZone
	 */
	public void setTimeZone(TimeZone timeZone);
	
	/**
	 * <p>Sets the parameter type.</p>
	 *
	 * @param timeZoneParameterType
	 */
	public void setTimeZoneParameterType(TimeZoneParameterType timeZoneParameterType);
	
	/**
	 * <p>Parses a time zone in ISO-8601 format.</p>
	 *
	 * @param iso8601Offset
	 */
	public void parseTimeZoneOffset(String iso8601Offset);
	
	/**
	 * <p>Sets the time zone as a text value.</p>
	 *
	 * @param textValue
	 */
	public void setTextValue(String textValue);
	
	/**
	 * <p>Returns a full copy of this object.</p>
	 *
	 * @return {@link TimeZoneFeature}
	 */
	public TimeZoneFeature clone();
}