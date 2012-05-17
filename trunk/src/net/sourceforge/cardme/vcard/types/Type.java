package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.db.MarkType;
import net.sourceforge.cardme.db.Persistable;
import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.LanguageType;
import net.sourceforge.cardme.vcard.types.parameters.ExtendedParameterType;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import java.io.Serializable;
import java.nio.charset.Charset;
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
public abstract class Type implements Persistable, Cloneable, Serializable {
	
	private static final long serialVersionUID = 1780437071417330296L;
	
	protected String id = null;
	protected MarkType markType = MarkType.UNMARKED;
	protected EncodingType encodingType = null;
	protected ParameterTypeStyle paramTypeStyle = ParameterTypeStyle.PARAMETER_VALUE_LIST;
	protected String group = null;
	protected Charset charset = null;
	protected LanguageType languageType = null;
	private List<ExtendedParameterType> extendedParameters = null;
	
	public Type() {
	    extendedParameters = new ArrayList<ExtendedParameterType>();
	}
	
	public Type(EncodingType encodingType) {
		this(encodingType, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		extendedParameters = new ArrayList<ExtendedParameterType>();		
	}
	
	public Type(EncodingType encodingType, ParameterTypeStyle paramTypeStyle) {
		setEncodingType(encodingType);
		setParameterTypeStyle(paramTypeStyle);
		extendedParameters = new ArrayList<ExtendedParameterType>();
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
	 * <p>Returns the charset of this type.</p>
	 *
	 * @return {@link Charset}
	 */
	public Charset getCharset()
	{
		return charset;
	}
	
	/**
	 * <p>Sets a specified charset for this type. Any charset
	 * that is null, empty or not supported will revert this
	 * type to the default charset supported by the java
	 * virtual machine that is currently running.</p>
	 *
	 * @param strCharset
	 */
	public void setCharset(String strCharset) {
		if(strCharset == null) {
			charset = Charset.defaultCharset();
		}
		else if(strCharset.isEmpty()) {
			charset = Charset.defaultCharset();
		}
		else {
			if(Charset.isSupported(strCharset)) {
				charset = Charset.forName(strCharset);
			}
			else {
				charset = Charset.defaultCharset();
			}
		}
	}
	
	/**
	 * <p>Sets a specified charset for this type. Should
	 * the charset be null then the default charset supported
	 * by the java virtual machine will be used instead.</p>
	 *
	 * @param charset
	 */
	public void setCharset(Charset charset) {
		if(charset == null) {
			this.charset = Charset.defaultCharset();
		}
		else {
			this.charset = charset;
		}
	}
	
	/**
	 * <p>Returns true if the charset is not null.</p>
	 *
	 * @return boolean
	 */
	public boolean hasCharset()
	{
		return charset != null;
	}
	
	/**
	 * <p>Returns the current language type, or null if there are none.</p>
	 *
	 * @return {@link LanguageType}
	 */
	public LanguageType getLanguage()
	{
		return languageType;
	}
	
	/**
	 * <p>Sets the language type.</p>
	 *
	 * @param languageType
	 */
	public void setLanguage(LanguageType languageType) {
		setLanguage(languageType.getLanguageCode());
	}
	
	/**
	 * <p>Sets the language type via parsing a String. Should
	 * the string be invalid or null then the current language
	 * type is assigned to null.</p>
	 *
	 * @param languageType
	 */
	public void setLanguage(String languageType) {
		if(languageType != null) {
			try {
				this.languageType = LanguageType.valueOf(languageType.toUpperCase().replaceAll("-", "_"));
			}
			catch(Exception ex) {
				this.languageType = null;
			}
		}
		else {
			this.languageType = null;
		}
	}
	
	/**
	 * <p>Returns true if this type has a language type.</p>
	 *
	 * @return boolean
	 */
	public boolean hasLanguage()
	{
		return languageType != null;
	}
	
	/**
	 * <p>Returns true if the encoding type is QUOTED-PRINTABLE.</p>
	 *
	 * @return boolean
	 */
	public boolean isQuotedPrintable()
	{
		return encodingType.equals(EncodingType.QUOTED_PRINTABLE);
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
	
    /**
     * <p>Returns an iterator of extended address parameters.</p>
    * 
    * @return {@link Iterator}&lt;ExtendedParameterType&gt;
    */
    public Iterator<ExtendedParameterType> getExtendedParameters() {
        return extendedParameters.listIterator();
    }

    /**
     * <p>Returns an unmodifiable list of extended parameters.</p>
     *
     * @return {@link List}&lt;ExtendedParameterType&gt;
     */
    public List<ExtendedParameterType> getExtendedParametersList() {
        return Collections.unmodifiableList(extendedParameters);
    }

    /**
     * <p>Returns the number of extended parameters.</p>
     *
     * @return int
     */
    public int getExtendedParameterSize() {
        return extendedParameters.size();
    }

    /**
     * <p>Adds an extended parameter.</p>
     * 
     * @param extendedParameter
     */
    public void addExtendedParameter(ExtendedParameterType extendedParameter) {
        extendedParameters.add(extendedParameter);   
    }

    /**
     * <p>Removes the specified extended parameter.</p>
     * 
     * @param xtendedParameter
     */
    public void removeExtendedParameters(ExtendedParameterType xtendedParameter) {
        extendedParameters.remove(xtendedParameter);
    }

    /**
     * <p>Returns true if the specified extended address parameters exists.</p>
     * 
     * @param xtendedParameters
     * @return boolean
     */
    public boolean containsExtendedsParameters(ExtendedParameterType xtendedParameters) {
        return extendedParameters.contains(xtendedParameters);
    }

    /**
     * <p>Returns true if all the extended parameters exist.</p>
     *
     * @param xtendedParameters
     * @return boolean
     */
    public boolean containsAllExtendedParameters(List<ExtendedParameterType> xtendedParameters) {
        return extendedParameters.containsAll(xtendedParameters);
    }

    /**
     * <p>Returns true if this extension has extended parameters.</p>
     * 
     * @return boolean
     */
    public boolean hasExtendedParameters() {
        return !extendedParameters.isEmpty();
    }

}
