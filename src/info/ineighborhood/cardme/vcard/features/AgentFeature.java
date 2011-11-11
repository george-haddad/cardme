package info.ineighborhood.cardme.vcard.features;

import info.ineighborhood.cardme.vcard.VCard;
import info.ineighborhood.cardme.vcard.types.parameters.AgentParameterType;
import java.net.URI;
import java.util.Iterator;

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
 * <p><b>RFC 2426</b></br>
 * <b>3.5.4 AGENT Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> AGENT</li>
 * 	<li><b>Type purpose:</b> To specify information about another person who will act on behalf of the individual or resource associated with the vCard.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> The default is a single vcard value. It can also be reset to either a single text or uri value. The text value can be used to specify textual information. The uri value can be used to specify information outside of this MIME entity.</li>
 * 	<li><b>Type special note:</b> This type typically is used to specify an area administrator, assistant, or secretary for the individual associated with the vCard. A key characteristic of the Agent type is that it represents somebody or something that is separately addressable.</li>
 * </ul>
 * </p>
 */
public interface AgentFeature extends TypeTools {
	
	/**
	 * <p>Returns the agent which is a VCard.</p>
	 *
	 * @return {@link VCard}
	 */
	public VCard getAgent();
	
	/**
	 * <p>Sets the agent which is a VCard.</p>
	 *
	 * @param agent
	 */
	public void setAgent(VCard agent);
	
	/**
	 * <p>Returns the agent URI.</p>
	 *
	 * @return {@link URI}
	 */
	public URI getAgentURI();
	
	/**
	 * <p>Returns true if the agent exists in the form
	 * of a VCard or a URI.</p>
	 *
	 * @return boolean
	 */
	public boolean hasAgent();
	
	/**
	 * <p>Returns true if the agent is in the form of a URI.</p>
	 *
	 * @return boolean
	 */
	public boolean isURI();
	
	/**
	 * <p>Returns true if the agent is in the form of in line data.
	 * This would mean an embedded VCard string.</p>
	 *
	 * @return boolean
	 */
	public boolean isInline();
	
	/**
	 * <p>Sets the agent URI.</p>
	 *
	 * @param agentUri
	 */
	public void setAgentURI(URI agentUri);
	
	/**
	 * <p>Returns all parameter types of this Agent.</p>
	 *
	 * @return {@link Iterator}&lt;AgentParameterType&gt;
	 */
	public Iterator<AgentParameterType> getAgentParameterTypes();
	
	/**
	 * <p>Adds a parameter type.</p>
	 *
	 * @param agentParameterType
	 */
	public void addAgentParameterType(AgentParameterType agentParameterType);
	
	/**
	 * <p>Removes a parameter type.</p>
	 *
	 * @param agentParameterType
	 */
	public void removeAgentParameterType(AgentParameterType agentParameterType);
	
	/**
	 * <p>Returns true if the parameter type exists in this Agent.</p>
	 *
	 * @param agentParameterType
	 * @return boolean
	 */
	public boolean containsAgentParameterType(AgentParameterType agentParameterType);
	
	/**
	 * <p>Returns true if this agent has parameter types.</p>
	 *
	 * @return boolean
	 */
	public boolean hasAgentParameterTypes();
	
	/**
	 * <p>Removes all agent parameter types.</p>
	 */
	public void clearAgentParameterTypes();
	
	/**
	 * <p>Returns a full copy of this object.</p>
	 *
	 * @return {@link AgentFeature}
	 */
	public AgentFeature clone();
}
