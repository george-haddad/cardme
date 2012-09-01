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
	
	public TimeZone getTimeZone();
	public void setTimeZone(TimeZone timeZone);
	public boolean hasTimeZone();
	public void clearTimeZone();
	
	public int getHourOffset();
	public int getMinuteOffset();
	public void setOffset(int hourOffset, int minuteOffset) throws IllegalArgumentException;
	
	public String getShortText();
	public void setShortText(String shortText);
	
	public String getLongText();
	public void setLongText(String longText);
	
	public String getIso8601Offset();
	public void parseTimeZoneOffset(String iso8601Offset) throws IllegalArgumentException;
	
	public TzParamType getParamType();
	public void setParamType(TzParamType tzParamType);
	public boolean hasParamType();
	
	public boolean isUtcOffset();
	public boolean isText();
}
