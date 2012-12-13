package net.sourceforge.cardme.vcard.features;

import java.util.List;
import net.sourceforge.cardme.vcard.exceptions.VCardException;

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
 * <b>3.5.5 ORG Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> ORG</li>
 * 	<li><b>Type purpose:</b> To specify the organizational name and units associated with the vCard.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single structured text value consisting of components separated the SEMI-COLON character (ASCII decimal 59).</li>
 * 	<li><b>Type special note:</b> The type is based on the X.520 Organization Name and Organization Unit attributes. The type value is a structured type consisting of the organization name, followed by one or more levels of organizational unit names.</li>
 * </ul>
 * </p>
 */
public interface OrgFeature {

	/**
	 * <p>Retrieves the organization name or null if not set.</p>
	 * 
	 * @return the organization name or null if not set
	 */
	public String getOrgName();
	
	/**
	 * <p>Sets the organization name.</p>
	 * 
	 * @param orgName - the organization name to set
	 * @return a reference to itself
	 */
	public OrgFeature setOrgName(String orgName);
	
	/**
	 * <p>Indicates if an organization name has been set.</p>
	 * 
	 * @return true if an organization name has been set or false otherwise
	 */
	public boolean hasOrgName();
	
	/**
	 * <p>Removes the organization name.
	 * Note that this does not clear organizational units.</p>
	 */
	public void clearOrg();
	
	/**
	 * <p>Adds an organizational unit.</p>
	 * 
	 * @param orgUnit - the organizational unit to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null organizational unit
	 * @throws VCardException if adding an organizational unit when the organization name has not been set
	 */
	public OrgFeature addOrgUnit(String orgUnit) throws NullPointerException, VCardException;
	
	/**
	 * <p>Adds a list of organizational units.</p>
	 *   
	 * @param orgUnits - the list of organizational units to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null list of organizational units
	 * @throws VCardException if adding an organizational unit when the organization name has not been set
	 */
	public OrgFeature addAllOrgUnits(List<String> orgUnits) throws NullPointerException, VCardException;
	
	/**
	 * <p>Removes the specified organizational unit.</p>
	 * 
	 * @param orgUnit - the organizational unit to remove
	 * @return a reference to itself
	 * @throws NullPointerException if removing a null organizational unit
	 */
	public OrgFeature removeOrgUnit(String orgUnit) throws NullPointerException;
	
	/**
	 * <p>Indicates if the specified organizational unit exists.</p>
	 * 
	 * @param orgUnit - the organizational unit to check
	 * @return true if the specified organizational unit exists or false otherwise
	 */
	public boolean containsOrgUnit(String orgUnit);
	
	/**
	 * <p>Indicates whether all organizational units in the list exist.</p>
	 * 
	 * @param orgUnits - the list of organizational units to check
	 * @return true if all organizational units in the list exist or false otherwise
	 */
	public boolean containsAllOrgUnits(List<String> orgUnits);
	
	/**
	 * <p>Retrieves the list of organizational units.</p>
	 * 
	 * @return the list of organizational units
	 */
	public List<String> getOrgUnits();
	
	/**
	 * <p>Indicates if organizational units have been added.</p>
	 * 
	 * @return true if organizational units have been added or false otherwise
	 */
	public boolean hasOrgUnits();
}
