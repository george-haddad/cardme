package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.AgentFeature;
import net.sourceforge.cardme.vcard.types.parameters.AgentParameterType;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
public class AgentType extends Type implements AgentFeature {
	
	private static final long serialVersionUID = -1260393269571892216L;
	
	private URI agentUri = null;
	private VCard agent = null;
	private List<AgentParameterType> agentParameterTypes = null;
	
	public AgentType() {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		agentParameterTypes = new ArrayList<AgentParameterType>();
	}
	
	public AgentType(URI agentUri) {
		super(EncodingType.EIGHT_BIT);
		setAgentURI(agentUri);
		agentParameterTypes = new ArrayList<AgentParameterType>();
		
	}
	
	public AgentType(VCard agent) {
		super(EncodingType.EIGHT_BIT);
		setAgent(agent);
		agentParameterTypes = new ArrayList<AgentParameterType>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public VCard getAgent()
	{
		return agent;
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterator<AgentParameterType> getAgentParameterTypes()
	{
		return agentParameterTypes.listIterator();
	}

	/**
	 * {@inheritDoc}
	 */
	public URI getAgentURI()
	{
		return agentUri;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setAgent(VCard agent) {
		this.agent = agent;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setAgentURI(URI agentUri) {
		this.agentUri = agentUri;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasAgent()
	{
		return agentUri != null || agent != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isURI()
	{
		return agentUri != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isInline()
	{
		return agent != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addAgentParameterType(AgentParameterType agentParameterType) {
		agentParameterTypes.add(agentParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeAgentParameterType(AgentParameterType agentParameterType) {
		agentParameterTypes.remove(agentParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean containsAgentParameterType(AgentParameterType agentParameterType)
	{
		return agentParameterTypes.contains(agentParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasAgentParameterTypes()
	{
		return !agentParameterTypes.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearAgentParameterTypes() {
		agentParameterTypes.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.AGENT.getType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof AgentType) {
				if(this == obj || ((AgentType)obj).hashCode() == this.hashCode()) {
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
		
		if(agentUri != null) {
			sb.append(agentUri.getPath());
			sb.append(",");
		}
		
		if(agent != null) {
			sb.append(agent.toString());
			sb.append(",");
		}
		
		if(!agentParameterTypes.isEmpty()) {
			for(int i=0; i < agentParameterTypes.size(); i++) {
				sb.append(agentParameterTypes.get(i).getType());
				sb.append(",");
			}
		}

		if(super.id != null) {
			sb.append(super.id);
			sb.append(",");
		}
		
		sb.deleteCharAt(sb.length()-1);	//Remove last comma.
		sb.append(" ]");
		return sb.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AgentFeature clone()
	{
		AgentType cloned = new AgentType();
		
		if(agentUri != null) {
			try {
				cloned.setAgentURI(new URI(agentUri.toString()));
			}
			catch(URISyntaxException e) {
				cloned.setAgentURI(null);
			}
		}
		
		if(agent != null) {
			cloned.setAgent(agent.clone());
		}
		
		if(!agentParameterTypes.isEmpty()) {
			for(int i = 0; i < agentParameterTypes.size(); i++) {
				cloned.addAgentParameterType(agentParameterTypes.get(i));
			}
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
