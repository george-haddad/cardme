package net.sourceforge.cardme.vcard.features;

import java.util.Calendar;
import java.util.Date;
import net.sourceforge.cardme.util.ISOFormat;
import net.sourceforge.cardme.vcard.types.params.BDayParamType;

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
 * <p><b>RFC 2426</b><br/>
 * <b>3.1.5 BDAY Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> BDAY</li>
 * 	<li><b>Type purpose:</b> To specify the birth date of the object the vCard represents.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> The default is a single date value. It can also be reset to a single date-time value.</li>
 * 	<li><b>Type special note:</b> None</li>
 * </ul>
 * </p>
 */
public interface BDayFeature {
	
	/**
	 * <p>Retrieves the birth date as a {@link Calendar} object.</p>
	 * 
	 * @return the birth date as a {@link Calendar} object or null if not set
	 */
	public Calendar getBirthday();
	
	/**
	 * <p>Set the birth date from a {@link Calendar} object.</p>
	 * 
	 * @param bdayCal - the birth date to set
	 */
	public void setBirthday(Calendar bdayCal);
	
	/**
	 * <p>Set the birth date from a {@link Date} object.</p>
	 * 
	 * @param bdayDate - the birth date to set
	 */
	public void setBirthday(Date bdayDate);
	
	/**
	 * <p>Set the birth date parameter type.</p>
	 * 
	 * @param bdayParamType - the birth date parameter type
	 */
	public void setParam(BDayParamType bdayParamType);
	
	/**
	 * <p>Retrieves the birth date parameter type.</p>
	 * 
	 * @return the birth date parameter type or null if not set
	 */
	public BDayParamType getParam();
	
	/**
	 * <p>Removes the birth date parameter type.</p>
	 */
	public void clearParam();
	
	/**
	 * <p>Indicates if the birth date parameter type has been set.</p>
	 * 
	 * @return true if the birth date parameter type was set or false otherwise
	 */
	public boolean hasParam();
	
	/**
	 * <p>Set the ISO-8601 format for the output of the birthday.
	 * The default is {@link ISOFormat#ISO8601_UTC_TIME_EXTENDED}.</p>
	 * 
	 * @see ISOFormat
	 */
	public void setISO8601Format(ISOFormat dateTimeFormat);
	
	/**
	 * <p>Retrieves the ISO-8601 date time format according
	 * to the birth date parameter type. If the parameter type
	 * is not set then the default {@link ISOFormat#ISO8601_DATE_EXTENDED}
	 * will be returned for date values and {@link ISOFormat#ISO8601_UTC_TIME_EXTENDED}
	 * will be returned for date-time values. If nothing is set
	 * then the default returned will be {@link ISOFormat#ISO8601_UTC_TIME_EXTENDED}
	 * 
	 * @return the ISO 8601 formatting type
	 */
	public ISOFormat getISO8601Format();
}
