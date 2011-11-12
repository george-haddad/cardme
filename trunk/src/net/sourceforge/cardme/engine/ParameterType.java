package net.sourceforge.cardme.engine;

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
 * Nov 12, 2011
 *
 */
public class ParameterType {

	private String name = null;
	private String value = null;
	
	public ParameterType() {
		
	}
	
	public ParameterType(String name, String value) {
		setName(name);
		setValue(value);
	}

	/**
	 * <p>Returns the parameter type name.</p>
	 *
	 * @return {@link String}
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * <p>Sets the parameter type name.</p>
	 *
	 * @param name
	 * @throws NullPointerException
	 */
	public void setName(String name) throws NullPointerException {
		if(name == null) {
			throw new NullPointerException("Parameter type name cannot be set to null.");
		}
		
		this.name = new String(name);
	}

	/**
	 * <p>Returns the parameter type value.</p>
	 *
	 * @return {@link String}
	 */
	public String getValue()
	{
		return value;
	}
	
	/**
	 * <p>Sets the parameter type value.</p>
	 *
	 * @param value
	 * @throws NullPointerException
	 */
	public void setValue(String value) throws NullPointerException {
		if(value == null) {
			throw new NullPointerException("Parameter type value cannot be set to null.");
		}
		
		this.value = new String(value);
	}
	
	/**
	 * <p>Returns true if the name is not null.</p>
	 *
	 * @return boolean
	 */
	public boolean hasName()
	{
		return name != null;
	}
	
	/**
	 * <p>Returns true if the value is not null.</p>
	 *
	 * @return boolean
	 */
	public boolean hasValue()
	{
		return value != null;
	}
	
	@Override
	public String toString()
	{
		return name+"="+value;
	}
}
