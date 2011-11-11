package info.ineighborhood.cardme.db;

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
 * Feb 17, 2010
 * <p>
 * This is an interface that is useful when persisting VCards to a database.
 * It allows us to set an ID string for so that we can identify them in a
 * database. It also allows us to set a MarkType which is defined by <code>MarkType</code>.
 * </p>
 */
public interface Persistable {

	/**
	 * <p>Sets a unique id to be used for database persistence.</p>
	 *
	 * @param id
	 */
	public void setID(String id);
	
	/**
	 * <p>Returns the unique id assigned to this object for database persistence.</p>
	 *
	 * @return {@link String}
	 */
	public String getID();
	
	/**
	 * <p>Removed current marking and give value of Unmarked.</p>
	 */
	public void unmark();
	
	/**
	 * <p>Marks as <code>markType</code></p>
	 * 
	 * @param markType
	 */
	public void mark(MarkType markType);
	
	/**
	 * <p>Returns the mark type.</p>
	 * 
	 * @Return {@link MarkType}
	 */
	public MarkType getMarkType();
}
