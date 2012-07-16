package net.sourceforge.cardme.vcard.features;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.cardme.vcard.types.parameters.IMPPParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XIMPPParameterType;

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
 * Jul 10, 2012
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
public interface IMPPFeature extends TypeTools {

	/**
	 * <p>Returns the IMPP URI.</p>
	 *
	 * @return {@link URI}
	 */
	public URI getURI();
	
	/**
	 * <p>Sets the IMPP URI.</p>
	 *
	 * @param url
	 */
	public void setURI(URI uri);
	
	/**
	 * <p>Clears the URI.</p>
	 */
	public void clearURI();
	
	/**
	 * <p>Returns true if there is an IMPP URI.</p>
	 *
	 * @return boolean
	 */
	public boolean hasURI();
	
	/**
	 * <p>Returns an iterator all parameter types.</p>
	 *
	 * @return {@link Iterator}&lt;IMPPParameterType&gt;
	 */
	public Iterator<IMPPParameterType> getIMPPParameterTypes();
	
	/**
	 * <p>Returns an unmodifiable list of parameter types.</p>
	 *
	 * @return {@link List}&lt;IMPPParameterType&gt;
	 */
	public List<IMPPParameterType> getIMPPParameterTypesList();
	
	/**
	 * <p>Returns the number of parameter types.</p>
	 *
	 * @return int
	 */
	public int getIMPPParameterSize();
	
	/**
	 * <p>Adds an IMPP parameter type.</p>
	 *
	 * @param imppParameterType
	 */
	public void addIMPPParameterType(IMPPParameterType imppParameterType);
	
	/**
	 * <p>Removes a parameter type.</p>
	 *
	 * @param imppParameterType
	 */
	public void removeIMPPParameterType(IMPPParameterType imppParameterType);
	
	/**
	 * <p>Returns true if the parameter type exists.</p>
	 *
	 * @param imppParameterType
	 * @return boolean
	 */
	public boolean containsIMPPParameterType(IMPPParameterType imppParameterType);
	
	/**
	 * <p>Returns true if all the parameter types exist.</p>
	 *
	 * @param imppParameterTypes
	 * @return boolean
	 */
	public boolean containsAllIMPPParameterTypes(List<IMPPParameterType> imppParameterTypes);
	
	
	/**
	 * <p>Returns true if IMPP parameter types exist.</p>
	 *
	 * @return boolean
	 */
	public boolean hasIMPPParameterTypes();
	
	/**
	 * <p>Removes all IMPP parameter types.</p>
	 */
	public void clearIMPPParameterTypes();
	
	/**
	 * <p>Returns an iterator all extended parameter types.</p>
	 *
	 * @return {@link Iterator}&lt;XIMPPParameterType&gt;
	 */
	public Iterator<XIMPPParameterType> getExtendedIMPPParameterTypes();
	
	/**
	 * <p>Returns an unmodifiable list of extended parameter types.</p>
	 *
	 * @return {@link List}&lt;XIMPPParameterType&gt;
	 */
	public List<XIMPPParameterType> getExtendedIMPPParameterTypesList();
	
	/**
	 * <p>Returns the number of extended parameter types.</p>
	 *
	 * @return int
	 */
	public int getExtendedIMPPParameterSize();
	
	/**
	 * <p>Adds an extended IMPP parameter type.</p>
	 *
	 * @param xImppParameterType
	 */
	public void addExtendedIMPPParameterType(XIMPPParameterType xImppParameterType);
	
	/**
	 * <p>Removes an extended parameter type.</p>
	 *
	 * @param xImppParameterType
	 */
	public void removeExtendedIMPPParameterType(XIMPPParameterType xImppParameterType);
	
	/**
	 * <p>Returns true if the extended parameter type exists.</p>
	 *
	 * @param xurlParameterType
	 * @return boolean
	 */
	public boolean containsExtendedIMPPParameterType(XIMPPParameterType xImppParameterType);
	
	/**
	 * <p>Returns true if all the extended parameter types exist.</p>
	 *
	 * @param xImppParameterTypes
	 * @return boolean
	 */
	public boolean containsAllExtendedIMPPParameterTypes(List<XIMPPParameterType> xImppParameterTypes);
	
	/**
	 * <p>Returns true if extended IMPP parameter types exist.</p>
	 *
	 * @return boolean
	 */
	public boolean hasExtendedIMPPParameterTypes();
	
	/**
	 * <p>Removes all extended IMPP parameter types.</p>
	 */
	public void clearExtendedIMPPParameterTypes();
	
	/**
	 * <p>Returns a full copy of this object.</p>
	 *
	 * @return {@link IMPPFeature}
	 */
	public IMPPFeature clone();
}
