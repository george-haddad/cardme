package net.sourceforge.cardme.vcard.features;

import net.sourceforge.cardme.vcard.types.media.KeyTextType;

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
 * Aug 11, 2012
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.7.2 KEY Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> KEY</li>
 * 	<li><b>Type purpose:</b> To specify a public key or authentication certificate associated with the object that the vCard represents.</li>
 * 	<li><b>Type encoding:</b> The encoding MUST be reset to "b" using the ENCODING parameter in order to specify inline, encoded binary data. If the value is a text value, then the default encoding of 8bit is used and no explicit ENCODING parameter is needed.</li>
 * 	<li><b>Type value:</b> A single value. The default is binary. It can also be reset to text value. The text value can be used to specify a text key.</li>
 * 	<li><b>Type special note:</b> The type can also include the type parameter TYPE to specify the public key or authentication certificate format. The parameter type should specify an IANA registered public key or authentication certificate format. The parameter type can also specify a non-standard format.</li>
 * </ul>
 * </p>
 */
public interface KeyFeature {
	
	public byte[] getKey();
	
	public void setKey(byte[] keyBytes);
	
	public void setKey(String keyText);
	
	public boolean hasKey();
	
	public void clearKey();
	
	public KeyTextType getKeyTextType();
	
	public void setKeyTextType(KeyTextType keyTextType);
	
	public boolean hasKeyTextType();
}
