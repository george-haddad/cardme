package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.GeographicPositionFeature;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;

/*
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
public class GeographicPositionType extends Type implements GeographicPositionFeature {

	private static final long serialVersionUID = 8844192367405540617L;
	
	public static final String regexValidator = "\\-?\\d{1,3}\\.\\d{1,6}";
	private double lon = 0.0d;
	private double lat = 0.0d;
	
	public GeographicPositionType() {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
	}
	
	public GeographicPositionType(double longitude, double latitude) {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		setLongitude(longitude);
		setLatitude(latitude);
	}
	
	public GeographicPositionType(String longitude, String latitude) {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		setLongitude(longitude);
		setLatitude(latitude);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public double getLatitude()
	{
		return lat;
	}

	/**
	 * {@inheritDoc}
	 */
	public double getLongitude()
	{
		return lon;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLatitude(double latitude) {
		this.lat = latitude;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLatitude(String latitude) throws IllegalArgumentException {
		if(latitude.matches(regexValidator)) {
			setLatitude(Double.parseDouble(latitude));
		}
		else {
			throw new IllegalArgumentException("Latitude is invalid, must have at least 1 digit and precise to a minimum of 1 or maximum of 6 decimal places.");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setLatitude(double degrees, double minutes, double seconds) {
		setLatitude(degrees + (minutes/60) + (seconds/3600));
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLongitude(double longitude) {
		this.lon = longitude;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLongitude(String longitude) {
		if(longitude.matches(regexValidator)) {
			setLongitude(Double.parseDouble(longitude));
		}
		else {
			throw new IllegalArgumentException("Longitude is invalid, must have at least 1 digit and precise to a minimum of 1 or maximum of 6 decimal places.");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setLongitude(double degrees, double minutes, double seconds) {
		setLongitude(degrees + (minutes/60) + (seconds/3600));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.GEO.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof GeographicPositionType) {
				if(this == obj || ((GeographicPositionType)obj).hashCode() == this.hashCode()) {
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
		
		sb.append(lon);
		sb.append(",");
		sb.append(lat);
		sb.append(",");

		if(super.id != null) {
			sb.append(super.id);
			sb.append(",");
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.append(" ]");
		return sb.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GeographicPositionFeature clone()
	{
		GeographicPositionType cloned = new GeographicPositionType();
		cloned.setLongitude(lon);
		cloned.setLatitude(lat);
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
