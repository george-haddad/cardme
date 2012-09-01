package net.sourceforge.cardme.vcard.features;

import java.util.List;
import net.sourceforge.cardme.vcard.types.params.AdrParamType;

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
 * <b>3.2.1 ADR Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> ADR</li>
 * 	<li><b>Type purpose:</b> To specify the components of the delivery address for the vCard object.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single structured text value, separated by the SEMI-COLON character (ASCII decimal 59).</li>
 * 	<li><b>Type special note:</b> The structured type value consists of a sequence of address components. The component values MUST be specified in their corresponding position. The structured type value corresponds, in sequence, to the post office box; the extended address; the street address; the locality (e.g., city); the region (e.g., state or province); the postal code; the country name. When a component value is missing, the associated component separator MUST still be specified.</li>
 * </ul>
 * </p>
 */
public interface AdrFeature {

	public String getPostOfficeBox();
	public void setPostOfficeBox(String postOfficeBox);
	public boolean hasPostOfficebox();
	
	public String getExtendedAddress();
	public void setExtendedAddress(String extendedAddress);
	public boolean hasExtendedAddress();
	
	public String getStreetAddress();
	public void setStreetAddress(String streetAddress);
	public boolean hasStreetAddress();
	
	public String getLocality();
	public void setLocality(String locality);
	public boolean hasLocality();
	
	public String getRegion();
	public void setRegion(String region);
	public boolean hasRegion();
	
	public String getPostalCode();
	public void setPostalCode(String postalCode);
	public boolean hasPostalCode();
	
	public String getCountryName();
	public void setCountryName(String countryName);
	public boolean hasCountryName();
	
	public List<AdrParamType> getParams();
	public AdrFeature addParam(AdrParamType adrParamType) throws NullPointerException;
	public AdrFeature addAllParams(List<AdrParamType> adrParamTypes) throws NullPointerException;
	public AdrFeature removeParam(AdrParamType adrParamType) throws NullPointerException;
	public boolean containsParam(AdrParamType adrParamType);
	public boolean containsAllParams(List<AdrParamType> AdrParamTypes);
	public int getParamSize();
	public boolean hasParams();
	public void clearParams();
}
