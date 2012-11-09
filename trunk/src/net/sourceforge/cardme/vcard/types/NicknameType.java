package net.sourceforge.cardme.vcard.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.NicknameFeature;
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
 * Aug 7, 2012
 *
 */
public class NicknameType extends AbstractVCardType implements Comparable<NicknameType>, Cloneable, NicknameFeature {

	private static final long serialVersionUID = 8057945727399194427L;
	
	private List<String> nicknames = null;
	
	public NicknameType() {
		super(VCardTypeName.NICKNAME);
		nicknames = new ArrayList<String>();
	}
	
	public NicknameType(String nickname) {
		super(VCardTypeName.NICKNAME);
		nicknames = new ArrayList<String>();
		addNickname(nickname);
		
	}
	
	public NicknameType(List<String> nicknames) {
		super(VCardTypeName.NICKNAME);
		nicknames = new ArrayList<String>();
		addAllNicknames(nicknames);
	}

	@Override
	public List<String> getNicknames()
	{
		return Collections.unmodifiableList(nicknames);
	}

	@Override
	public NicknameType addNickname(String nickname) throws NullPointerException {
		if(nickname == null) {
			throw new NullPointerException("Cannot add a null nickname.");
		}
		
		nicknames.add(nickname);
		return this;
	}

	@Override
	public NicknameType addAllNicknames(List<String> nicknames) throws NullPointerException {
		if(nicknames == null) {
			throw new NullPointerException("Cannot add a null nicknames list.");
		}
		
		this.nicknames.addAll(nicknames);
		return this;
	}

	@Override
	public NicknameType removeNickname(String nickname) throws NullPointerException {
		if(nickname == null) {
			throw new NullPointerException("Cannot remove a null nickname.");
		}
		
		nicknames.remove(nickname);
		return this;
	}

	@Override
	public boolean containsNickname(String nickname)
	{
		if(nickname != null) {
			return nicknames.contains(nickname);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsAllNicknames(List<String> nicknames)
	{
		if(nicknames != null) {
			return this.nicknames.containsAll(nicknames);
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasNicknames()
	{
		return !nicknames.isEmpty();
	}

	@Override
	public void clearNicknames() {
		nicknames.clear();
	}
	
	@Override
	public boolean hasParams()
	{
		return false;
	}
	
	@Override
	public NicknameType clone()
	{
		NicknameType cloned = new NicknameType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.addAllNicknames(nicknames);
		return cloned;
	}
	
	@Override
	public int compareTo(NicknameType obj)
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
		String[] contents = new String[8];
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
		
		if(!nicknames.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(String nickname : nicknames) {
				sb.append(nickname);
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[7] = sb.toString();
		}
		else {
			contents[7] = "";
		}
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof NicknameType) {
				return this.compareTo((NicknameType)obj) == 0;
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
