package net.sourceforge.cardme.vcard.features;

import java.net.URI;
import java.util.List;
import net.sourceforge.cardme.vcard.types.params.ImppParamType;

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
 * Aug 13, 2012
 * 
 * <p><b>RFC 4770</b><br/>
 * <b>2 IMPP Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> IMPP</li>
 * 	<li><b>Type purpose:</b> To specify the URI for instant messaging and presence protocol communications with the object the vCard represents.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single URI.  The type of the URI indicates the protocol that can be used for this contact.</li>
 * 	<li><b>Type special note:</b> The type may include the type parameter "TYPE" to specify an intended use for the URI.  The TYPE parameter values include one or more of the following:
 *      <ul>
 *           <li>An indication of the type of communication for which this URI is appropriate.  This can be a value of PERSONAL or BUSINESS.</li>
 *           <li>An indication of the location of a device associated with this URI.  Values can be HOME, WORK, or MOBILE.</li>
 *           <li>The value PREF indicates this is a preferred address and has the same semantics as the PREF value in a TEL type.</li>
 *      </ul>
 *  </li>
 * </ul>
 * </p>
 * 
 * <p>
 * The IMPP feature will not contain any specific methods to set the protocol of
 * the URI that is set. The protocol will be embedded inside the URI such as &quot;
 * im:alice@example.net&quot; Further more, the RFC-4770 states <strong>&quot;this
 * document does not define any of the types&quot;</strong> so it only makes sense
 * that cardme will not check nor enforce any protocols.
 * </p>
 */
public interface ImppFeature {

	/**
	 * <p>Retrieves the {@link URI} of the IMPP address.</p>
	 * 
	 * @return the {@link URI} of the IMPP address or null if not set</p>
	 */
	public URI getUri();
	
	/**
	 * <p>Sets the {@link URI} of the IMPP address.</p>
	 * 
	 * @param uri - the {@link URI} of the IMPP address
	 */
	public void setUri(URI uri);
	
	/**
	 * <p>Indicates if the {@link URI} has been set.</p>
	 * 
	 * @return true if the {@link URI} has been set and false otherwise
	 */
	public boolean hasUri();
	
	/**
	 * <p>Removes the {@link URI} address.</p>
	 */
	public void clearUri();
	
	/**
	 * <p>Retrieves a list of IMPP parameter types.</p>
	 * 
	 * @return a list of IMPP parameter types or empty list if none added
	 */
	public List<ImppParamType> getParams();
	
	/**
	 * <p>Adds the specified IMPP parameter type.</p>
	 * 
	 * @param imppParamType - the IMPP parameter type to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null impp parameter type
	 */
	public ImppFeature addParam(ImppParamType imppParamType) throws NullPointerException;
	
	/**
	 * <p>Add a list of IMPP parameter types.</p>
	 * 
	 * @param imppParamTypes - the list of IMPP parameter types to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding null list of IMPP parameter types
	 */
	public ImppFeature addAllParams(List<ImppParamType> imppParamTypes) throws NullPointerException;
	
	/**
	 * <p>Removes the specified IMPP parameter type.</p>
	 * 
	 * @param imppParamType - the IMPP parameter type to remove
	 * @return a reference to itself
	 * @throws NullPointerException if removing a null IMPP parameter type
	 */
	public ImppFeature removeParam(ImppParamType imppParamType) throws NullPointerException;
	
	/**
	 * <p>Indicates if the specified IMPP parameter type exists.</p>
	 * 
	 * @param imppParamType - the IMPP parameter type to check
	 * @return true if the specified IMPP parameter type exists and false otherwise
	 */
	public boolean containsParam(ImppParamType imppParamType);
	
	/**
	 * <p>Indicates if all of the IMPP parameter types in the list exists.</p>
	 * 
	 * @param imppParamTypes - the list of IMPP parameter types to check
	 * @return true if all the IMPP parameter types in the list exist
	 */
	public boolean containsAllParams(List<ImppParamType> imppParamTypes);
	
	/**
	 * <p>Indicates if IMPP parameter types have been added.</p>
	 * 
	 * @return true if IMPP parameter types exist and false otherwise
	 */
	public boolean hasParams();
	
	/**
	 * <p>Removes all IMPP parameter types.</p>
	 */
	public void clearParams();
}
