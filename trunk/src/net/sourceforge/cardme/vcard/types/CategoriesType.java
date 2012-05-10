package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.CategoriesFeature;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import java.util.ArrayList;
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
 * Feb 4, 2010
 *
 */
public class CategoriesType extends Type implements CategoriesFeature {

	private static final long serialVersionUID = -4808509971616993206L;
	
	private List<String> categories = null;
	
	public CategoriesType() {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		categories = new ArrayList<String>();
	}
	
	public CategoriesType(String category) {
		this();
		addCategory(category);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addCategory(String category) throws NullPointerException {
		if(category == null) {
			throw new NullPointerException("Cannot add a null category.");
		}
		
		categories.add(category);
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterator<String> getCategories()
	{
		return categories.listIterator();
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeCategory(String category) throws NullPointerException {
		if(category == null) {
			throw new NullPointerException("Cannot remove a null category.");
		}
		
		categories.remove(category);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean containsCategory(String category)
	{
		if(category == null) {
			return false;
		}
		else {
			return categories.contains(category);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasCategories()
	{
		return !categories.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearCategories() {
		categories.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.CATEGORIES.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof CategoriesType) {
				if(this == obj || ((CategoriesType)obj).hashCode() == this.hashCode()) {
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
		
		if(!categories.isEmpty()) {
			for(int i=0; i < categories.size(); i++) {
				sb.append(categories.get(i));
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
	public CategoriesFeature clone()
	{
		CategoriesType cloned = new CategoriesType();
		
		if(!categories.isEmpty()) {
			for(int i = 0; i < categories.size(); i++) {
				cloned.addCategory(new String(categories.get(i)));
			}
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
