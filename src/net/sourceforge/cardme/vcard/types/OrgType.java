package net.sourceforge.cardme.vcard.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.exceptions.VCardException;
import net.sourceforge.cardme.vcard.features.OrgFeature;
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
 * Aug 8, 2012
 *
 */
public class OrgType extends AbstractVCardType implements Comparable<OrgType>, Cloneable, OrgFeature {

	private static final long serialVersionUID = -5122875504318229778L;
	
	private String orgName = null;
	private List<String> orgUnits = null;
	
	public OrgType() {
		super(VCardTypeName.ORG);
		orgUnits = new ArrayList<String>();
	}
	
	public OrgType(String orgName) {
		super(VCardTypeName.ORG);
		orgUnits = new ArrayList<String>();
		setOrgName(orgName);
	}
	
	public OrgType(String orgName, List<String> orgUnits) {
		super(VCardTypeName.ORG);
		orgUnits = new ArrayList<String>();
		setOrgName(orgName);
		addAllOrgUnits(orgUnits);
	}

	@Override
	public String getOrgName()
	{
		if(orgName != null) {
			return new String(orgName);
		}
		else {
			return null;
		}
	}

	@Override
	public OrgType setOrgName(String orgName) {
		if(orgName != null) {
			this.orgName = new String(orgName);
		}
		else {
			this.orgName = null;
		}
		
		return this;
	}

	@Override
	public void clearOrg() {
		orgName = null;
		orgUnits.clear();
	}

	@Override
	public boolean hasOrgName()
	{
		return orgName != null;
	}

	@Override
	public OrgType addOrgUnit(String orgUnit) throws NullPointerException, VCardException {
		if(orgUnit == null) {
			throw new NullPointerException("Cannot add a null organizational unit.");
		}
		
		if(orgName == null) {
			throw new VCardException("Cannot add organizational units without first seting the organization name.");
		}
		
		orgUnits.add(orgUnit);
		return this;
	}

	@Override
	public OrgType addAllOrgUnits(List<String> orgUnits) throws NullPointerException, VCardException {
		if(orgUnits == null) {
			throw new NullPointerException("Cannot add a null organizational units list.");
		}
		
		if(orgName == null) {
			throw new VCardException("Cannot add all organizational units without first seting the organization name.");
		}
		
		this.orgUnits.addAll(orgUnits);
		return this;
	}

	@Override
	public OrgType removeOrgUnit(String orgUnit) throws NullPointerException {
		if(orgUnit == null) {
			throw new NullPointerException("Cannot remove a null organizational unit.");
		}
		
		orgUnits.remove(orgUnit);
		return this;
	}

	@Override
	public boolean containsOrgUnit(String orgUnit)
	{
		if(orgUnit != null) {
			return orgUnits.contains(orgUnit);
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean containsAllOrgUnits(List<String> orgUnits)
	{
		if(orgUnits != null) {
			return this.orgUnits.containsAll(orgUnits);
		}
		else {
			return false;
		}
	}

	@Override
	public List<String> getOrgUnits()
	{
		return Collections.unmodifiableList(orgUnits);
	}

	public void clearOrgUnits() {
		orgUnits.clear();
	}

	@Override
	public boolean hasOrgUnits()
	{
		return !orgUnits.isEmpty();
	}
	
	@Override
	public boolean hasParams()
	{
		return false;
	}
	
	@Override
	public OrgType clone()
	{
		OrgType cloned = new OrgType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.setOrgName(orgName);
		cloned.addAllOrgUnits(orgUnits);
		return cloned;
	}

	@Override
	public int compareTo(OrgType obj)
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
		String[] contents = new String[9];
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
		
		contents[7] = StringUtil.getString(orgName);
		
		if(!orgUnits.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(String orgUnit : orgUnits) {
				sb.append(orgUnit);
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[8] = sb.toString();
		}
		else {
			contents[8] = "";
		}
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof OrgType) {
				return this.compareTo((OrgType)obj) == 0;
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
