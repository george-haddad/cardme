package net.sourceforge.cardme.vcard.features;

import java.net.URI;
import net.sourceforge.cardme.vcard.VCard;

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
public interface AgentFeature {
	
	/**
	 * <p>Retrieve the agent as a VCard object that is
	 * embedded in the parent VCard.</p>
	 * 
	 * @return the VCard representing the agent
	 */
	public VCard getAgent();
	
	/**
	 * <p>Embed the agent VCard.</p>
	 *  
	 * @param agent - the agent VCard to embed 
	 */
	public void setAgent(VCard agent);
	
	/**
	 * <p>Retrieve the URI indicating the remote location
	 * of the agent's VCard.</p>
	 * 
	 * @return the {@link URI} of the agent's VCard or null if not set
	 */
	public URI getAgentURI();
	
	/**
	 * <p>Indicates if the agent VCard has been set via
	 * {@link URI} or VCard.</p>
	 * 
	 * @return true if the agent VCard has been set or false otherwise
	 */
	public boolean hasAgent();
	
	/**
	 * <p>Indicates if the agent's VCard is a {@link URI} value or not.</p>
	 * 
	 * @return true if the agent's VCard is a {@link URI} value or false otherwise
	 */
	public boolean isURI();
	
	/**
	 * <p>Sets the agent's VCard as a {@link URI} object.</p>
	 * 
	 * @param agentUri - the {@link URI} object to set
	 */
	public void setAgentURI(URI agentUri);
}
