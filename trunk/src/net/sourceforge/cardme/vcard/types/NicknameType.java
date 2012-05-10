package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.NicknameFeature;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
 * Feb 8, 2010
 *
 */
public class NicknameType extends Type implements NicknameFeature {

	private static final long serialVersionUID = -4213177039299977049L;
	
	private List<String> nicknames = null;
	
	public NicknameType() {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		nicknames = new ArrayList<String>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<String> getNicknames()
	{
		return nicknames.listIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addNickname(String nickname) throws NullPointerException {
		if(nickname == null) {
			throw new NullPointerException("Cannot add a null nickname.");
		}
		
		nicknames.add(nickname);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeNickname(String nickname) throws NullPointerException {
		if(nickname == null) {
			throw new NullPointerException("Cannot remove a null nickname.");
		}
		
		nicknames.remove(nickname);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addAllNicknames(Collection<String> nicknames) throws NullPointerException {
		if(nicknames == null) {
			throw new NullPointerException("Cannot add a null collection of nicknames.");
		}
		
		this.nicknames.addAll(nicknames);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsNickname(String nickname)
	{
		if(nickname == null) {
			return false;
		}
		else {
			return nicknames.contains(nickname);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasNicknames()
	{
		return !nicknames.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearNicknames() {
		nicknames.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.NICKNAME.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof NicknameType) {
				if(this == obj || ((NicknameType)obj).hashCode() == this.hashCode()) {
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
		
		if(!nicknames.isEmpty()) {
			for(int i = 0; i < nicknames.size(); i++) {
				sb.append(nicknames.get(i));
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
	public NicknameFeature clone()
	{
		NicknameType cloned = new NicknameType();
		
		if(!nicknames.isEmpty()) {
			for(int i = 0; i < nicknames.size(); i++) {
				cloned.addNickname(new String(nicknames.get(i)));
			}
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
