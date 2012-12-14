package net.sourceforge.cardme.vcard.features;

import java.util.List;
import net.sourceforge.cardme.vcard.types.params.TelParamType;

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
public interface TelFeature {
	
	/**
	 * <p>Retrieves the telephone number.</p>
	 * 
	 * @return the telephone number or null if not set
	 */
	public String getTelephone();
	
	/**
	 * <p>Sets the telephone number.</p>
	 * 
	 * @param telephone - the telephone number to set
	 */
	public void setTelephone(String telephone);
	
	/**
	 * <p>Indicates if the telephone number has been set.</p>
	 * 
	 * @return true if the telephone number has been set
	 */
	public boolean hasTelephone();
	
	/**
	 * <p>Retrieves a list of telephone parameter types.</p>
	 * 
	 * @return the list of telephone parameter types
	 */
	public List<TelParamType> getParams();
	
	/**
	 * <p>Retrieves the total number of telephone parameter types.</p>
	 * 
	 * @return the total number of telephone parameter types
	 */
	public int getParamSize();
	
	/**
	 * <p>Adds a telephone parameter type.</p>
	 *  
	 * @param telParamType - the telephone parameter type to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null telephone parameter type
	 */
	public TelFeature addParam(TelParamType telParamType) throws NullPointerException;
	
	/**
	 * <p>Adds all telephone parameter types from the list.</p>
	 * 
	 * @param telParamTypes - the list of telephone parameter types to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null list of telephone parameter types
	 */
	public TelFeature addAllParams(List<TelParamType> telParamTypes) throws NullPointerException;
	
	/**
	 * <p>Remove the specified telephone parameter type.</p>
	 * 
	 * @param telParamType - the telephone parameter type to remove
	 * @return a reference to itself
	 * @throws NullPointerException if removing a null telephone parameter type
	 */
	public TelFeature removeParam(TelParamType telParamType) throws NullPointerException;
	
	/**
	 * <p>Indicates if the specified telephone parameter type exists.</p>
	 * 
	 * @param telParamType - the telephone parameter type to check
	 * @return true if the telephone parameter type exists or false otherwise
	 */
	public boolean containsParam(TelParamType telParamType);
	
	/**
	 * <p>Indicates whether all telephone parameter types in the list exists.</p>
	 * 
	 * @param telParamTypes - the list of telephone parameter types to check
	 * @return true if all telephone parameter types in the list exists or false otherwise
	 */
	public boolean containsAllParams(List<TelParamType> telParamTypes);
	
	/**
	 * <p>Indicates if telephone parameter types have been added.</p>
	 * 
	 * @return true if telephone parameter types have been added
	 */
	public boolean hasParams();
	
	/**
	 * <p>Removes all telephone parameter types.</p>
	 */
	public void clearParams();
}
