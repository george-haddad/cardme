package net.sourceforge.cardme.util;

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
 * Aug 23, 2006
 * 
 */
public final class Base64Wrapper {
	
	/**
	 * @author George El-Haddad
	 * <br/>
	 * Feb 10, 2010
	 * 
	 * <p>Base64 options.</p>
	 */
	public enum OPTIONS {
		/**
		 * <p>Compress the bytes to GZIP compression before encoding them
		 * to base64. This significantly reduces the size of the base64.
		 * Other applications that will decode must be aware that the encoding
		 * is compressed.</p>
		 */
		GZIP_COMPRESSION,
		
		/**
		 * <p>Do not compress the bytes before encoding to base64. This is
		 * the default unless compression option is explicitly stated.</p>
		 */
		NO_COMPRESSION;
	}
	
	private Base64Wrapper() {

	}

	/**
	 * <p>Encodes the specified bytes into a base64 String
	 * without using GZIP compression as the default.</p>
	 *
	 * @see Base64
	 * @see OPTIONS
	 * 
	 * @param bytes
	 * @return {@link String}
	 */
	public static String encode(byte[] bytes)
	{
		return encode(bytes, OPTIONS.NO_COMPRESSION);
	}
	
	/**
	 * <p>Encodes the specified bytes into a base64 String with the given
	 * options to use GZIP compression or not.</p>
	 *
	 * @see Base64
	 * @see OPTIONS
	 * 
	 * @param bytes
	 * @param options
	 * @return {@link String}
	 */
	public static String encode(byte[] bytes, OPTIONS options)
	{
		switch(options)
		{
			case GZIP_COMPRESSION:
			{
				return Base64.encodeBytes(bytes, Base64.GZIP);
			}

			case NO_COMPRESSION:
			{
				return Base64.encodeBytes(bytes, Base64.NO_OPTIONS);
			}

			default:
			{
				return Base64.encodeBytes(bytes,Base64.NO_OPTIONS);
			}
		}
	}
	
	/**
	 * <p>Decodes a base64 String into an array of bytes. GZIP compression
	 * is automatically detected and decompressed.</p>
	 *
	 * @see Base64
	 * 
	 * @param base64String
	 * @return byte[]
	 */
	public static byte[] decode(String base64String)
	{
		return Base64.decode(base64String, Base64.NO_OPTIONS);
	}
}
