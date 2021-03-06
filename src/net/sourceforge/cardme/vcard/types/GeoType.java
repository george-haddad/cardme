package net.sourceforge.cardme.vcard.types;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.GeoFeature;
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
 * Aug 8, 2012
 *
 */
public class GeoType extends AbstractVCardType implements Comparable<GeoType>, Cloneable, GeoFeature {

	private static final long serialVersionUID = 3031993699243673782L;
	
	private static final Pattern REGEX_VALIDATOR = Pattern.compile("\\-?\\d{1,3}\\.\\d{1,6}");
	private double lon = 0.0d;
	private double lat = 0.0d;
	
	public GeoType() {
		this(null, null);
	}
	
	public GeoType(String longitude, String latitude) {
		super(VCardTypeName.GEO);
		setLongitude(longitude);
		setLatitude(latitude);
	}
	
	public GeoType(double longitude, double latitude) {
		super(VCardTypeName.GEO);
		setLongitude(longitude);
		setLatitude(latitude);
	}

	@Override
	public double getLongitude()
	{
		return lon;
	}

	@Override
	public double getLatitude()
	{
		return lat;
	}

	@Override
	public void setLongitude(double longitude) {
		this.lon = longitude;
	}

	@Override
	public void setLongitude(String longitude) throws IllegalArgumentException {
		if(longitude == null) {
			setLongitude(0.0d);
		}
		else {
			if(REGEX_VALIDATOR.matcher(longitude).matches()) {
				setLongitude(Double.parseDouble(longitude));
			}
			else {
				throw new IllegalArgumentException("Longitude is invalid, must have at least 1 digit and precise to a minimum of 1 or maximum of 6 decimal places.");
			}
		}
	}

	@Override
	public void setLongitude(double degrees, double minutes, double seconds) {
		setLongitude(degrees + (minutes/60) + (seconds/3600));
	}

	@Override
	public void setLatitude(double latitude) {
		this.lat = latitude;
	}

	@Override
	public void setLatitude(String latitude) {
		if(latitude == null) {
			setLatitude(0.0d);
		}
		else {
			if(REGEX_VALIDATOR.matcher(latitude).matches()) {
				setLatitude(Double.parseDouble(latitude));
			}
			else {
				throw new IllegalArgumentException("Latitude is invalid, must have at least 1 digit and precise to a minimum of 1 or maximum of 6 decimal places.");
			}
		}
	}

	@Override
	public void setLatitude(double degrees, double minutes, double seconds) {
		setLatitude(degrees + (minutes/60) + (seconds/3600));
	}
	
	@Override
	public boolean hasParams()
	{
		return false;
	}
	
	@Override
	public GeoType clone()
	{
		GeoType cloned = new GeoType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.setLatitude(lat);
		cloned.setLongitude(lon);
		return cloned;
	}

	@Override
	public int compareTo(GeoType obj)
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
		String[] contents = new String[9];
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
		
		contents[7] = String.valueOf(lon);
		contents[8] = String.valueOf(lat);
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof GeoType) {
				return this.compareTo((GeoType)obj) == 0;
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
