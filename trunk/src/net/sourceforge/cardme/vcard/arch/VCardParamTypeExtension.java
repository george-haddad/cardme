package net.sourceforge.cardme.vcard.arch;

import java.util.List;
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
 * <p>Interface to define the functionalities of manipulating
 * extended parameter types in a VCard.</p>
 */
public interface VCardParamTypeExtension {

	/**
	 * <p>Retrieve the list of extended parameter types.</p>
	 * 
	 * @return list of extended parameter types
	 */
	public List<ExtendedParamType> getExtendedParams();
	
	/**
	 * <p>Retrieves the number of extended parameter types.</p>
	 * 
	 * @return the total number of extended parameter types.
	 */
	public int getExtendedParamSize();
	
	/**
	 * <p>Adds an extended parameter type.</p>
	 * 
	 * @param xtendedParam - the extended parameter type to add
	 * @return a reference to itself
	 * @throws NullPointerException if trying to add a null extended parameter type
	 */
	public VCardParamTypeExtension addExtendedParam(ExtendedParamType xtendedParam) throws NullPointerException;
	
	/**
	 * <p>Adds a list of extended parameter types to the current
	 * list of parameters.</p>
	 * 
	 * @param xtendedParams - the list of extended parameter types to add 
	 * @return a reference to itself
	 * @throws NullPointerException if trying to add a null list of extended parameter types
	 */
	public VCardParamTypeExtension addAllExtendedParams(List<ExtendedParamType> xtendedParams) throws NullPointerException;
	
	/**
	 * <p>Removes the specified extended parameter type.</p>
	 * 
	 * @param xtendedParam - the extended parameter type to remove
	 * @return a reference to itself
	 * @throws NullPointerException if trying to remove a null extended parameter type
	 */
	public VCardParamTypeExtension removeExtendedParam(ExtendedParamType xtendedParam) throws NullPointerException;
	
	/**
	 * <p>Checks if the specified extended parameter type exists.</p>
	 * 
	 * @param xtendedParam - the extended parameter type to check
	 * @return true if the specified extended parameter type exists and false otherwise
	 */
	public boolean containsExtendedParam(ExtendedParamType xtendedParam);
	
	/**
	 * <p>Checks if all the extended parameter types in the specified list exists.</p>
	 * 
	 * @param xtendedParams - the list of extended parameter types
	 * @return true if all extended parameter types in the list exist
	 */
	public boolean containsAllExtendedParams(List<ExtendedParamType> xtendedParams);
	
	/**
	 * <p>Indicates if there are at least 1 or more extended parameter types.</p>
	 * 
	 * @return true if there exists at least 1 or more extended parameter types
	 */
	public boolean hasExtendedParams();
	
	/**
	 * <p>Removes all extended parameter types.</p>
	 */
	public void clearExtendedParams();
}
