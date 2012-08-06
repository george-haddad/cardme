package net.sourceforge.cardme.vcard.features;

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
 * <b>3.8 Extended Types</b>
 * <ul>
 * 	<li><b>Type name:</b> X-</li>
 * 	<li><b>Type purpose:</b> The types defined by this document can be extended with private types using the non-standard, private values mechanism defined in [RFC 2045]. Non-standard, private types with a name starting with "X-" may be defined bilaterally between two cooperating agents without outside registration or standardization.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single line of text.</li>
 * 	<li><b>Type special note:</b> None</li>
 * </ul>
 * </p>
 */
public interface ExtendedFeature extends TypeTools {
	
	/**
	 * <p>Sets an extension name.</p>
	 *
	 * @param extension
	 */
	public void setExtensionName(String extension);
	
	/**
	 * <p>Returns the extension name.</p>
	 *
	 * @return {@link String}
	 */
	public String getExtensionName();
	
	/**
	 * <p>Sets the extension data.</p>
	 *
	 * @param extensionData
	 */
	public void setExtensionData(String extensionData);
	
	/**
	 * <p>Returns the extension data.</p>
	 *
	 * @return {@link String}
	 */
	public String getExtensionData();
	
	/**
	 * <p>Clears the extension.</p>
	 */
	public void clearExtension();
	
	/**
	 * <p>Returns true if the extension exists.</p>
	 *
	 * @return boolean
	 */
	public boolean hasExtension();
	
	/**
	 * <p>Returns a full copy of this object.</p>
	 *
	 * @return {@link ExtendedFeature}
	 */
	public ExtendedFeature clone();
}
