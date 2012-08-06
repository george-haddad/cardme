package net.sourceforge.cardme.vcard.features;

import java.util.Iterator;

/*
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
public interface CategoriesFeature extends TypeTools {
	
	/**
	 * <p>Returns the categories list.</p>
	 *
	 * @return {@link Iterator}&lt;String&gt;
	 */
	public Iterator<String> getCategories();
	
	/**
	 * <p>Adds a category to the list.</p>
	 *
	 * @param category
	 */
	public void addCategory(String category);
	
	/**
	 * <p>Removes a category from the list.</p>
	 *
	 * @param category
	 */
	public void removeCategory(String category);
	
	/**
	 * <p>Returns true if the category exists.</p>
	 *
	 * @param category
	 * @return boolean
	 */
	public boolean containsCategory(String category);
	
	/**
	 * <p>Returns true if this categories feature has categories.</p>
	 *
	 * @return boolean
	 */
	public boolean hasCategories();
	
	/**
	 * <p>Removes all categories.</p>
	 */
	public void clearCategories();
	
	/**
	 * <p>Returns a full copy of this object.</p>
	 *
	 * @return {@link CategoriesFeature}
	 */
	public CategoriesFeature clone();
}
