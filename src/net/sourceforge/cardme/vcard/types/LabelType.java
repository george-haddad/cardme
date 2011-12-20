package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.LabelFeature;
import net.sourceforge.cardme.vcard.types.parameters.LabelParameterType;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.types.parameters.XLabelParameterType;
import java.util.ArrayList;
import java.util.Collections;
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
public class LabelType extends Type implements LabelFeature {

	private String label = null;
	private List<LabelParameterType> labelParameterTypes = null;
	private List<XLabelParameterType> xtendedLabelParameterTypes = null;
	
	public LabelType() {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		labelParameterTypes = new ArrayList<LabelParameterType>();
		xtendedLabelParameterTypes = new ArrayList<XLabelParameterType>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getLabel()
	{
		return label;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<LabelParameterType> getLabelParameterTypes()
	{
		return labelParameterTypes.listIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<LabelParameterType> getLabelParameterTypesList()
	{
		return Collections.unmodifiableList(labelParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getLabelParameterSize()
	{
		return labelParameterTypes.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public void addLabelParameterType(LabelParameterType labelParameterType) {
		labelParameterTypes.add(labelParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeLabelParameterType(LabelParameterType labelParameterType) {
		labelParameterTypes.remove(labelParameterType);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean containsLabelParameterType(LabelParameterType labelParameterType)
	{
		return labelParameterTypes.contains(labelParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean conatinsAllLabelParameterTypes(List<LabelParameterType> parameterTypes)
	{
		return labelParameterTypes.containsAll(parameterTypes);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasLabelParameterTypes()
	{
		return !labelParameterTypes.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearLabelParameterTypes() {
		labelParameterTypes.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Iterator<XLabelParameterType> getExtendedLabelParameterTypes()
	{
		return xtendedLabelParameterTypes.listIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<XLabelParameterType> getExtendedLabelParameterTypesList()
	{
		return Collections.unmodifiableList(xtendedLabelParameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getExtendedLabelParameterSize()
	{
		return xtendedLabelParameterTypes.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addExtendedLabelParameterType(XLabelParameterType xtendedLabelParameterType) {
		xtendedLabelParameterTypes.add(xtendedLabelParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeExtendedLabelParameterType(XLabelParameterType xtendedLabelParameterType) {
		xtendedLabelParameterTypes.remove(xtendedLabelParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsExtendedLabelParameterType(XLabelParameterType xtendedLabelParameterType)
	{
		return xtendedLabelParameterTypes.contains(xtendedLabelParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean containsAllExtendedLabelParameterTypes(List<XLabelParameterType> parameterTypes)
	{
		return xtendedLabelParameterTypes.containsAll(parameterTypes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasExtendedLabelParameterTypes()
	{
		return !xtendedLabelParameterTypes.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void clearExtendedLabelParameterTypes() {
		xtendedLabelParameterTypes.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasLabel()
	{
		return label != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.LABEL.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof LabelType) {
				if(this == obj || ((LabelType)obj).hashCode() == this.hashCode()) {
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
		
		if(label != null) {
			sb.append(label);
			sb.append(",");
		}
		
		if(!labelParameterTypes.isEmpty()) {
			for(int i=0; i < labelParameterTypes.size(); i++) {
				sb.append(labelParameterTypes.get(i).getType());
				sb.append(",");
			}
		}
		
		if(!xtendedLabelParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedLabelParameterTypes.size(); i++) {
				sb.append(xtendedLabelParameterTypes.get(i).getType());
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
	public LabelFeature clone()
	{
		LabelType cloned = new LabelType();
		
		if(label != null) {
			cloned.setLabel(new String(label));
		}
		
		if(!labelParameterTypes.isEmpty()) {
			for(int i = 0; i < labelParameterTypes.size(); i++) {
				cloned.addLabelParameterType(labelParameterTypes.get(i));
			}
		}
		
		if(!xtendedLabelParameterTypes.isEmpty()) {
			for(int i = 0; i < xtendedLabelParameterTypes.size(); i++) {
				cloned.addExtendedLabelParameterType(xtendedLabelParameterTypes.get(i));
			}
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
