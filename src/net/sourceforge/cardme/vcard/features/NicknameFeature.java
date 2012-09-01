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

	public List<String> getNicknames();
	
	public NicknameFeature addNickname(String nickname) throws NullPointerException;
	
	public NicknameFeature addAllNicknames(List<String> nicknames) throws NullPointerException;
	
	public NicknameFeature removeNickname(String nickname) throws NullPointerException;
	
	public boolean containsNickname(String nickname);
	
	public boolean containsAllNicknames(List<String> nicknames);
	
	public boolean hasNicknames();
	
	public void clearNicknames();
}
