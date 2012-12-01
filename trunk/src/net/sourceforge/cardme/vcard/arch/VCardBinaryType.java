package net.sourceforge.cardme.vcard.arch;

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
 * <p>Interface for implementing methods dealing with
 * binary data being stored in a vcard type.</p>
 */
public interface VCardBinaryType {

	/**
	 * <p>Indicates if the binary data is compressed or not.</p>
	 *
	 * @return true if the binary data is compressed
	 */
	public boolean isCompressed();
	
	/**
	 * <p>Sets whether the binary type is compressed or not.</p>
	 * 
	 * @param compressed - true if compression is used and false otherwise
	 */
	public void setCompression(boolean compressed);
	
	/**
	 * <p>Retrieve the binary data as an array of bytes.</p>
	 * 
	 * @return the binary data as an array of bytes
	 */
	public byte[] getBinaryData();
	
	/**
	 * <p>Sets the binary data as an array of bytes</p>
	 * 
	 * @param binaryData - the binary data to be set
	 */
	public void setBinaryData(byte[] binaryData);
	
	/**
	 * <p>Indicates whether the data is in binary format or not.
	 * There could be some cases where text is stored as an array.</p>
	 * 
	 * @return true if the data is binary data (non-textual) and false otherwise
	 */
	public boolean isBinary();
}
