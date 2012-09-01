package net.sourceforge.cardme.vcard.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.NFeature;
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
public class NType extends AbstractVCardType implements Comparable<NType>, Cloneable, NFeature {
	
	private static final long serialVersionUID = 2478521904984956059L;
	
	private String familyName = null;
	private String givenName = null;
	private List<String> additionalNames = null;
	private List<String> honorificPrefixes = null;
	private List<String> honorificSuffixes = null;
	
	public NType() {
		this(null, null);
	}
	
	public NType(String familyName) {
		this(familyName, null);
		
	}
	
	public NType(String familyName, String givenName) {
		super(VCardTypeName.N);
		setFamilyName(familyName);
		setGivenName(givenName);
		additionalNames = new ArrayList<String>();
		honorificPrefixes = new ArrayList<String>();
		honorificSuffixes = new ArrayList<String>();
	}

	public String getFamilyName()
	{
		if(familyName != null) {
			return new String(familyName);
		}
		else {
			return null;
		}
	}

	public void setFamilyName(String familyName) {
		if(familyName != null) {
			this.familyName = new String(familyName);
		}
		else {
			this.familyName = null;
		}
	}

	public boolean hasFamilyName()
	{
		return familyName != null;
	}

	public String getGivenName()
	{
		if(givenName != null) {
			return new String(givenName);
		}
		else {
			return null;
		}
	}

	public void setGivenName(String givenName) {
		if(givenName != null) {
			this.givenName = new String(givenName);
		}
		else {
			this.givenName = null;
		}
	}

	public boolean hasGivenName()
	{
		return givenName != null;
	}

	public List<String> getAdditionalNames()
	{
		return Collections.unmodifiableList(additionalNames);
	}

	public void addAdditionalName(String additionalName) throws NullPointerException {
		if(additionalName == null) {
			throw new NullPointerException("Cannot add a null additionalName.");
		}
		
		additionalNames.add(additionalName);
	}
	
	public void addAllAdditionalNames(List<String> additionalNames) throws NullPointerException {
		if(additionalNames == null) {
			throw new NullPointerException("Cannot add a null additionalNames list.");
		}
		
		this.additionalNames.addAll(additionalNames);
	}
	
	public void removeAdditionalName(String additionalName) throws NullPointerException {
		if(additionalName == null) {
			throw new NullPointerException("Cannot remove a null additionalName.");
		}
		
		additionalNames.remove(additionalName);
	}

	public boolean containsAdditionalName(String additionalName)
	{
		if(additionalName != null) {
			return additionalNames.contains(additionalName);
		}
		else {
			return false;
		}
	}

	public boolean hasAdditionalNames()
	{
		return !additionalNames.isEmpty();
	}

	public void clearAdditionalNames() {
		additionalNames.clear();
	}

	public List<String> getHonorificPrefixes()
	{
		return Collections.unmodifiableList(honorificPrefixes);
	}

	public void addHonorificPrefix(String honorificPrefix) throws NullPointerException {
		if(honorificPrefix == null) {
			throw new NullPointerException("Cannot add a null honorificPrefix.");
		}
		
		honorificPrefixes.add(honorificPrefix);
	}
	
	public void addAllHonorificPrefixes(List<String> honorificPrefixes) throws NullPointerException {
		if(honorificPrefixes == null) {
			throw new NullPointerException("Cannot add a null honorificPrefixes list.");
		}
		
		this.honorificPrefixes.addAll(honorificPrefixes);
	}
	
	public void removeHonorificPrefix(String honorificPrefix) throws NullPointerException {
		if(honorificPrefix == null) {
			throw new NullPointerException("Cannot remove a null honorificPrefix.");
		}
		
		honorificPrefixes.remove(honorificPrefix);
	}

	public boolean containsHonorificPrefix(String honorificPrefix)
	{
		if(honorificPrefix != null) {
			return honorificPrefixes.contains(honorificPrefix);
		}
		else {
			return false;
		}
	}

	public boolean hasHonorificPrefixes()
	{
		return !honorificPrefixes.isEmpty();
	}

	public void clearHonorificPrefixes() {
		honorificPrefixes.clear();
	}

	public List<String> getHonorificSuffixes()
	{
		return Collections.unmodifiableList(honorificSuffixes);
	}

	public void addHonorificSuffix(String honorificSuffix) throws NullPointerException {
		if(honorificSuffix == null) {
			throw new NullPointerException("Cannot add a null honorificSuffix.");
		}
		
		honorificSuffixes.add(honorificSuffix);
	}
	
	public void addAllHonorificSuffixes(List<String> honorificSuffixes) throws NullPointerException {
		if(honorificSuffixes == null) {
			throw new NullPointerException("Cannot add a null honorificSuffixes list.");
		}
		
		this.honorificSuffixes.addAll(honorificSuffixes);
	}

	public void removeHonorificSuffix(String honorificSuffix) throws NullPointerException {
		if(honorificSuffix == null) {
			throw new NullPointerException("Cannot remove a null honorificSuffix.");
		}
		
		honorificSuffixes.remove(honorificSuffix);
	}

	public boolean containsHonorificSuffix(String honorificSuffix)
	{
		return honorificSuffixes.contains(honorificSuffix);
	}

	public boolean hasHonorificSuffixes()
	{
		return !honorificSuffixes.isEmpty();
	}

	public void clearHonorificSuffixes() {
		honorificSuffixes.clear();
	}
	
	public boolean hasParams()
	{
		return false;
	}
	
	@Override
	public NType clone()
	{
		NType cloned = new NType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.setFamilyName(familyName);
		cloned.setGivenName(givenName);
		
		if(!additionalNames.isEmpty()) {
			cloned.addAllAdditionalNames(additionalNames);
		}
		
		if(!honorificPrefixes.isEmpty()) {
			cloned.addAllHonorificPrefixes(honorificPrefixes);
		}

		if(!honorificSuffixes.isEmpty()) {
			cloned.addAllHonorificSuffixes(honorificSuffixes);
		}
		
		return cloned;
	}
	
	public int compareTo(NType obj)
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
		String[] contents = new String[12];
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
		
		if(!honorificPrefixes.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(String prefix: honorificPrefixes) {
				sb.append(prefix);
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[7] = sb.toString();
		}
		else {
			contents[7] = "";
		}
		
		if(familyName != null) {
			contents[8] = StringUtil.getString(familyName);
		}
		else {
			contents[8] = "";
		}
		
		if(givenName != null) {
			contents[9] = StringUtil.getString(givenName);
		}
		else {
			contents[9] = "";
		}
		
		if(!additionalNames.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(String name: additionalNames) {
				sb.append(name);
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[10] = sb.toString();
		}
		else {
			contents[10] = "";
		}
		
		if(!honorificSuffixes.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(String suffix: honorificSuffixes) {
				sb.append(suffix);
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[11] = sb.toString();
		}
		else {
			contents[11] = "";
		}
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof NType) {
				return this.compareTo((NType)obj) == 0;
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
