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
	
	/**
	 * <p>Retrieves the post office box.
	 * <pre>
	 * ADR: ; ; ; ; ; ;
	 * ....^
	 * </pre>
	 * </p>
	 * 
	 * @return the post office box value or null if not set
	 */
	public String getPostOfficeBox();
	
	/**
	 * <p>Sets the post office box.</p>
	 * 
	 * @param postOfficeBox - the post office box
	 */
	public void setPostOfficeBox(String postOfficeBox);
	
	/**
	 * <p>Indicates if the post office box has been set or not.</p>
	 * 
	 * @return true if the post office box has been set or false otherwise
	 */
	public boolean hasPostOfficebox();
	
	/**
	 * <p>Retrieves the extended address.
	 * <pre>
	 * ADR: ; ; ; ; ; ;
	 * ......^
	 * </pre>
	 * </p>
	 * 
	 * @return the extended address or null if not set
	 */
	public String getExtendedAddress();
	
	/**
	 * <p>Sets the extended address.</p>
	 * 
	 * @param extendedAddress - the extended address to set
	 */
	public void setExtendedAddress(String extendedAddress);
	
	/**
	 * <p>Indicates if an extended address was set.</p>
	 * 
	 * @return true if an extended address was set or false otherwise
	 */
	public boolean hasExtendedAddress();
	
	/**
	 * <p>Retrieves the street address.
	 * <pre>
	 * ADR: ; ; ; ; ; ;
	 * ........^
	 * </pre>
	 * </p>
	 * 
	 * @return the street address or null if not set
	 */
	public String getStreetAddress();
	
	/**
	 * <p>Sets the street address.</p>
	 * 
	 * @param streetAddress - the street address to be set
	 */
	public void setStreetAddress(String streetAddress);
	
	/**
	 * <p>Indicates if the street address was set.</p>
	 * 
	 * @return true if the street address was set or false otherwise
	 */
	public boolean hasStreetAddress();
	
	/**
	 * <p>Retrieves the locality.
	 * <pre>
	 * ADR: ; ; ; ; ; ;
	 * ..........^
	 * </pre>
	 * </p>
	 * 
	 * @return the locality or null if not set
	 */
	public String getLocality();
	
	/**
	 * <p>Sets the locality.</p>
	 * 
	 * @param locality - the locality to be set
	 */
	public void setLocality(String locality);
	
	/**
	 * <p>Indicates if the locality has been set.</p>
	 * 
	 * @return true if the locality has been set or false otherwise
	 */
	public boolean hasLocality();
	
	/**
	 * <p>Retrieves the region.
	 * <pre>
	 * ADR: ; ; ; ; ; ;
	 * ............^
	 * </pre>
	 * </p>
	 * 
	 * @return the region or null if not set
	 */
	public String getRegion();
	
	/**
	 * <p>Sets the region.</p>
	 * 
	 * @param region - the region to be set
	 */
	public void setRegion(String region);
	
	/**
	 * <p>Indicates if the region has been set.</p>
	 * 
	 * @return true if the region was set or false otherwise
	 */
	public boolean hasRegion();
	
	/**
	 * <p>Retrieves the postal code.
	 * <pre>
	 * ADR: ; ; ; ; ; ;
	 * ..............^
	 * </pre>
	 * </p>
	 * 
	 * @return the postal code or null if not set
	 */
	public String getPostalCode();
	
	/**
	 * <p>Sets the postal code.</p>
	 * 
	 * @param postalCode - the postal code to set
	 */
	public void setPostalCode(String postalCode);
	
	/**
	 * <p>Indicates if the postal code has been set.</p>
	 * 
	 * @return true if the postal code has been set or false otherwise
	 */
	public boolean hasPostalCode();
	
	/**
	 * <p>Retrieves the country name.
	 * <pre>
	 * ADR: ; ; ; ; ; ;
	 * ................^
	 * </pre>
	 * </p>
	 * 
	 * @return the country name or null if not set
	 */
	public String getCountryName();
	
	/**
	 * <p>Sets the country name.</p>
	 * 
	 * @param countryName - the country name to set
	 */
	public void setCountryName(String countryName);
	
	/**
	 * <p>Indicates if the country name has been set.</p>
	 * 
	 * @return true if the country name has been set or false otherwise
	 */
	public boolean hasCountryName();
	
	/**
	 * <p>Retrieves all address parameter types added.</p>
	 * 
	 * @return the list of address parameter types, empty if none exist
	 */
	public List<AdrParamType> getParams();
	
	/**
	 * <p>Adds an address parameter type.</p>
	 * 
	 * @param adrParamType - the address parameter type to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null address parameter type
	 */
	public AdrFeature addParam(AdrParamType adrParamType) throws NullPointerException;
	
	/**
	 * <p>Adds a list of address parameter types.</p>
	 * 
	 * @param adrParamTypes - the list of address parameter types to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null list
	 */
	public AdrFeature addAllParams(List<AdrParamType> adrParamTypes) throws NullPointerException;
	
	/**
	 * <p>Removes the specified address parameter type.</p>
	 * 
	 * @param adrParamType - the address parameter type to remove
	 * @return a reference to itself
	 * @throws NullPointerException if removing a null address parameter type
	 */
	public AdrFeature removeParam(AdrParamType adrParamType) throws NullPointerException;
	
	/**
	 * <p>Indicates if the specified address parameter type exists.</p>
	 * 
	 * @param adrParamType - the address parameter type to check
	 * @return true if the address parameter type exists
	 */
	public boolean containsParam(AdrParamType adrParamType);
	
	/**
	 * <p>Indicates if the specified list of address parameter types all exist.</p>
	 *  
	 * @param adrParamTypes - the list of address parameter types to check
	 * @return true if all address parameter types in the list exist
	 */
	public boolean containsAllParams(List<AdrParamType> adrParamTypes);
	
	/**
	 * <p>Retrieve the total number of address parameter types.</p>
	 * 
	 * @return the total number of address parameter types
	 */
	public int getParamSize();
	
	/**
	 * <p>Indicates if this feature has address parameter types.</p>
	 * 
	 * @return true if address parameter types exist or false otherwise
	 */
	public boolean hasParams();
	
	/**
	 * <p>Removes all address parameter types for this feature.</p>
	 */
	public void clearParams();
}
