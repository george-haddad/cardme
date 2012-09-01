package net.sourceforge.cardme.db;

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
 * Jul 6, 2009 - 12:06:27 PM
 * <p>
 * This is the MarkType that is useful in persisting VCards to a database.
 * There are 4 types of Mark values that assist us in saving VCards to a
 * database. This mark type is available to any class that implements the
 * <code>Persistable</code> interface. So in your DB handling code you can
 * check the mark type of any object and handle it appropriately.
 * </p>
 */
@Deprecated
public enum MarkType {
	
	/**
	 * <p>Marked for database insertion.</p>
	 */
	INSERT("INSERT"),
	
	/**
	 * <p>Marked for database update.</p>
	 */
	UPDATE("UPDATE"),
	
	/**
	 * <p>Marked for database deletion.</p>
	 */
	DELETE("DELETE"),
	
	/**
	 * <p>Default unmarked.</p>
	 */
	UNMARKED("UNMARKED");
	
	private String type = null;
	MarkType(String t)
	{
		type = t;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(MarkType mt) {
		type = mt.toString();
	}
	
	@Override
	public String toString()
	{
		return new String(type);
	}
}
