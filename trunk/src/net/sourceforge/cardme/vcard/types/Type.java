package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.db.MarkType;
import net.sourceforge.cardme.db.Persistable;
import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import java.io.Serializable;

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
public abstract class Type implements Persistable, Cloneable, Serializable {
	
	protected String id = null;
	protected MarkType markType = MarkType.UNMARKED;
	protected EncodingType encodingType = null;
	protected ParameterTypeStyle paramTypeStyle = null;
	protected String group = null;
	
	public Type() {
		
	}
	
	public Type(EncodingType encodingType) {
		this(encodingType, ParameterTypeStyle.PARAMETER_VALUE_LIST);
	}
	
	public Type(EncodingType encodingType, ParameterTypeStyle paramTypeStyle) {
		setEncodingType(encodingType);
		setParameterTypeStyle(paramTypeStyle);
	}
	
	/**
	 * <p>Returns the type name as a string.</p>
	 *
	 * @return {@link String}
	 */
	public abstract String getTypeString();
	
	/**
	 * <p>Sets the encoding type.</p>
	 *
	 * @see EncodingType
	 * @param encodingType
	 */
	public void setEncodingType(EncodingType encodingType) {
		this.encodingType = encodingType;
	}
	
	/**
	 * <p>Returns the encoding type.</p>
	 * 
	 * @see EncodingType
	 * @return {@link EncodingType}
	 */
	public EncodingType getEncodingType()
	{
		return encodingType;
	}
	
	/**
	 * <p>Sets the parameter type format style.</p>
	 * 
	 * @see ParameterTypeStyle
	 * @param paramTypeStyle
	 */
	public void setParameterTypeStyle(ParameterTypeStyle paramTypeStyle) {
		this.paramTypeStyle = paramTypeStyle;
	}
	
	/**
	 * <p>Returns the parameter type format style.</p>
	 *
	 * @see ParameterTypeStyle
	 * @return {@link ParameterTypeStyle}
	 */
	public ParameterTypeStyle getParameterTypeStyle()
	{
		return paramTypeStyle;
	}
	
	/**
	 * <p>Sets the group name for this type.</p>
	 *
	 * @param group
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	
	/**
	 * <p>Returns the group name</p>
	 *
	 * @return {@link String}
	 */
	public String getGroup()
	{
		return group;
	}
	
	/**
	 * <p>Returns true if this type has a group name.</p>
	 *
	 * @return boolean
	 */
	public boolean hasGroup()
	{
		return group != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setID(String id) {
		this.id = id;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getID()
	{
		return id;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public MarkType getMarkType()
	{
		return markType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void mark(MarkType markType) {
		this.markType = markType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void unmark() {
		markType = MarkType.UNMARKED;
	}
	
	/**
	 * <p>Performs a java style equality with one extra bit of checking.
	 * In the end we check if the hash codes of both objects are equal.
	 * The hash code is determined by the overridden hash code function.</p>
	 * 
	 * @param obj
	 * @return boolean
	 */
	@Override
	public abstract boolean equals(Object obj);
	
	/**
	 * <p>Generates a unique hash code based on all the data
	 * contained within in the object.</p>
	 * 
	 * @see Util#generateHashCode(String...)
	 * @return int
	 */
	@Override
	public abstract int hashCode();
	
	/**
	 * <p>Concatenates all data types in the object and returns it.</p>
	 * 
	 * @return {@link String}
	 */
	@Override
	public abstract String toString();
}
