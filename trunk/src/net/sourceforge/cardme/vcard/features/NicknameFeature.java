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
 * Aug 7, 2012
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.1.3 NICKNAME Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> NICKNAME</li>
 * 	<li><b>Type purpose:</b> To specify the text corresponding to the nickname of the object the vCard represents.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> One or more text values separated by a COMMA character (ASCII decimal 44).</li>
 * 	<li><b>Type special note:</b> The nickname is the descriptive name given instead of or in addition to the one belonging to a person, place, or thing. It can also be used to specify a familiar form of a proper name specified by the FN or N types.</li>
 * </ul>
 * </p>
 */
public interface NicknameFeature {
	
	/**
	 * <p>Retrieves the list of nicknames added.</p>
	 * 
	 * @return the list of nicknames added
	 */
	public List<String> getNicknames();
	
	/**
	 * <p>Adds a nickname.</p>
	 * 
	 * @param nickname - the nickname to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null nickname
	 */
	public NicknameFeature addNickname(String nickname) throws NullPointerException;
	
	/**
	 * <p>Adds a list of nicknames.</p>
	 * 
	 * @param nicknames - the list of nicknames to add
	 * @return a reference to itself
	 * @throws NullPointerException if adding a null list of nicknames
	 */
	public NicknameFeature addAllNicknames(List<String> nicknames) throws NullPointerException;
	
	/**
	 * <p>Removes the specified nickname.</p>
	 * 
	 * @param nickname - the nickname to remove
	 * @return a reference to itself
	 * @throws NullPointerException if removing a null nickname
	 */
	public NicknameFeature removeNickname(String nickname) throws NullPointerException;
	
	/**
	 * <p>Indicates if the specified nickname exists.</p>
	 * 
	 * @param nickname - the nickname to check
	 * @return true if the specified nickname exists or false otherwise
	 */
	public boolean containsNickname(String nickname);
	
	/**
	 * <p>Indicates whether all nicknames in the list exist.</p>
	 * 
	 * @param nicknames - the list of nicknames to check
	 * @return true if all nicknames in the list exist
	 */
	public boolean containsAllNicknames(List<String> nicknames);
	
	/**
	 * <p>Indicates if nicknames have been added.</p>
	 * 
	 * @return true if nicknames have been added
	 */
	public boolean hasNicknames();
	
	/**
	 * <p>Remvoes all nicknames.</p>
	 */
	public void clearNicknames();
}
