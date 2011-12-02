package net.sourceforge.cardme.vcard.features;

import java.nio.charset.Charset;

import net.sourceforge.cardme.db.Persistable;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.types.Type;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;

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
 * Feb 10, 2010
 *
 */
public interface TypeTools extends Persistable {
	
	/**
	 * <p>Returns the parameter type style.</p>
	 *
	 * @see Type#getParameterTypeStyle()
	 */
	public ParameterTypeStyle getParameterTypeStyle();
	
	/**
	 * <p>Sets the parameter type style.</p>
	 *
	 * @see Type#setParameterTypeStyle(ParameterTypeStyle)
	 * @param paramTypeStyle
	 */
	public void setParameterTypeStyle(ParameterTypeStyle paramTypeStyle);
	
	/**
	 * @see Type#getTypeString() 
	 */
	public String getTypeString();
	
	/**
	 * @see Type#setGroup(String)
	 */
	public void setGroup(String group);
	
	/**
	 * @see Type#getGroup()
	 */
	public String getGroup();
	
	/**
	 * @see Type#hasGroup()
	 */
	public boolean hasGroup();
	
	/**
	 * <p>Returns the charset of this type.</p>
	 *
	 * @return {@link Charset}
	 */
	public Charset getCharset();
	
	/**
	 * <p>Sets a specified charset for this type. Any charset
	 * that is null, empty or not supported will revert this
	 * type to the default charset supported by the java
	 * virtual machine that is currently running.</p>
	 *
	 * @param strCharset
	 */
	public void setCharset(String strCharset);
	
	/**
	 * <p>Sets a specified charset for this type. Should
	 * the charset be null then the default charset supported
	 * by the java virtual machine will be used instead.</p>
	 *
	 * @param charset
	 */
	public void setCharset(Charset charset);
	
	/**
	 * <p>Returns true if the charset is something other
	 * than the default charset. The default charset will
	 * be used anyways by default, so we are interested if
	 * there is something different.</p>
	 *
	 * @return boolean
	 */
	public boolean hasCharset();
	
	/**
	 * <p>Sets the encoding type.</p>
	 *
	 * @see EncodingType
	 * @param encodingType
	 */
	public void setEncodingType(EncodingType encodingType);
	
	/**
	 * <p>Returns the encoding type.</p>
	 * 
	 * @see EncodingType
	 * @return {@link EncodingType}
	 */
	public EncodingType getEncodingType();
	
	/**
	 * @see Type#equals(Object)
	 */
	public boolean equals(Object obj);
	
	/**
	 * @see Type#hashCode()
	 */
	public int hashCode();
	
	/**
	 * @see Type#toString()
	 */
	public String toString();
}
