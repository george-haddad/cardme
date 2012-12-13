package net.sourceforge.cardme.vcard.features;

import java.util.List;
import net.sourceforge.cardme.vcard.types.params.LabelParamType;

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
public interface LabelFeature {
	
	/**
	 * <p>Retrieves the label.</p>
	 * 
	 * @return the label that was set or null if none was set
	 */
	public String getLabel();
	
	/**
	 * <p>Sets the label.</p>
	 * 
	 * @param label - the label to set
	 */
	public void setLabel(String label);
	
	/**
	 * <p>Indicates whether a label has been set or not.</p>
	 * 
	 * @return true if a label was set or null otherwise
	 */
	public boolean hasLabel();
	
	/**
	 * <p>Retrieves a list of label parameter types,
	 * an empty list will indicate no parameter types were set.</p>
	 * 
	 * @return a list of label parameter types
	 */
	public List<LabelParamType> getParams();
	
	/**
	 * <p>Retrieves the number of label parameter types added.</p>
	 * 
	 * @return the total number of label parameter types added
	 */
	public int getParamSize();
	
	/**
	 * <p>Adds a label parameter type.</p>
	 *  
	 * @param labelParamType - the label parameter type
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null label parameter type
	 */
	public LabelFeature addParam(LabelParamType labelParamType) throws NullPointerException;
	
	/**
	 * <p>Adds a list of label parameter types.</p>
	 * 
	 * @param labelParamTypes - the list of label parameter types
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null list if label parameter types
	 */
	public LabelFeature addAllParams(List<LabelParamType> labelParamTypes) throws NullPointerException;
	
	/**
	 * <p>Removes a specified label parameter type.</p>
	 * 
	 * @param labelParamType - the label parameter type to remove
	 * @return a reference to itself
	 * @throws NullPointerException if removing a null label parameter type
	 */
	public LabelFeature removeParam(LabelParamType labelParamType) throws NullPointerException;
	
	/**
	 * <p>Indicates whether the specified label parameter type exists.</p>
	 * 
	 * @param labelParamType - the label parameter type to check
	 * @return true if the label parameter type exist or false otherwise
	 */
	public boolean containsParam(LabelParamType labelParamType);
	
	/**
	 * <p>Indicates whether all label parameter types defined in the list exists.</p>
	 * 
	 * @param labelParamTypes - the list of label parameter types
	 * @return true if all label parameter types in the list exists
	 */
	public boolean containsAllParams(List<LabelParamType> labelParamTypes);
	
	/**
	 * <p>Indicates whether label parameter types have been added or not.</p>
	 * 
	 * @return true if label parameter types have been added or false otherwise
	 */
	public boolean hasParams();
	
	/**
	 * <p>Removes all label parameter types.</p>
	 */
	public void clearParams();
}
