package net.sourceforge.cardme.vcard.features;

import java.util.List;

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
 * Aug 9, 2012
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.6.1 CATEGORIES Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> CATEGORIES</li>
 * 	<li><b>Type purpose:</b> To specify application category information about the vCard.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> One or more text values separated by a COMMA character (ASCII decimal 44).</li>
 * 	<li><b>Type special note:</b> </li>
 * </ul>
 */
public interface CategoriesFeature {
	
	/**
	 * <p>Retrieves a list of categories.</p>
	 * 
	 * @return a list of categories or empty list if none have been added
	 */
	public List<String> getCategories();
	
	/**
	 * <p>Adds a category.</p>
	 * 
	 * @param category - the category to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null category
	 */
	public CategoriesFeature addCategory(String category) throws NullPointerException;
	
	/**
	 * <p>Adds all categories from a specified list.</p>
	 * 
	 * @param categories - the list of categories to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null list
	 */
	public CategoriesFeature addAllCategories(List<String> categories) throws NullPointerException;
	
	/**
	 * <p>Removes the specified category.</p>
	 * 
	 * @param category - the category to remove
	 * @return a reference to itself
	 * @throws NullPointerException if removing a null category
	 */
	public CategoriesFeature removeCategory(String category) throws NullPointerException;
	
	/**
	 * <p>Indicates if the specified category exists.</p>
	 * 
	 * @param category - the category to check
	 * @return true if the specified category exists and false otherwise
	 */
	public boolean containsCategory(String category);
	
	/**
	 * <p>Indicates if the list of categories exists.</p>
	 * 
	 * @param categories - the list of categories to check
	 * @return true if all categories in the list exist and false otherwise
	 */
	public boolean containsAllCategories(List<String> categories);
	
	/**
	 * <p>Indicates if there are at least one or more categories.</p>
	 * 
	 * @return true if one of more categories have been added
	 */
	public boolean hasCategories();
	
	/**
	 * <p>Removes all categories.</p>
	 */
	public void clearCategories();
}
