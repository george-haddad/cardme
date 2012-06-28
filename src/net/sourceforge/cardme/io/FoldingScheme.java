package net.sourceforge.cardme.io;

import net.sourceforge.cardme.util.VCardUtils;

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
 * Feb 8, 2010
 *
 */
public enum FoldingScheme {

	/**
	 * <p>Folds lines at 75 characters (not including CRLF) and uses 1 space as indentation.</p>
	 */
	MIME_DIR(75, VCardUtils.SP),
	
	/**
	 * <p>Folds lines at 72 characters (not including CRLF) and uses 1 space as indentation.</p>
	 */
	MS_OUTLOOK(72, VCardUtils.SP),
	
	/**
	 * <p>Folds lines at 76 characters (not including CRLF) and uses 2 spaces as indentation.</p>
	 */
	MAC_ADDRESS_BOOK(76, VCardUtils.SP+VCardUtils.SP),
	
	/**
	 * <p>No folding is done.</p>
	 */
	NONE(-1, "");
	
	private int maxChars = -1;
	private String indent = "";
	FoldingScheme(int _maxChars, String _indent) {
		maxChars = _maxChars;
		indent = _indent;
	}
	
	/**
	 * <p>Returns the maximum number of characters that can
	 * exist on a line before needing to be folded.</p>
	 *
	 * @return int
	 */
	public int getMaxChars()
	{
		return maxChars;
	}
	
	/**
	 * <p>Returns the string that is used to indent the folded line.</p>
	 *
	 * @return {@link String}
	 */
	public String getIndent()
	{
		return indent;
	}
}
