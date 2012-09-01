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
 * Aug 25, 2006
 * 
 * <p>General utility methods to help dealing with Strings.</p>
 */
public final class StringUtil {

	private static final char[] HEX_CHARS = {
		'0', '1', '2' ,'3', '4', '5', '6', '7', '8', '9',
		'a', 'b', 'c', 'd', 'e', 'f'
	};
	
	private StringUtil() {

	}

	/**
	 * <p>Counts how many times an instance of a specified character occurs in the
	 * target string.</p>
	 * 
	 * @param target
	 * @param instance
	 * @return int
	 */
	public static int countInstanceOf(String target, char instance)
	{
		return countInstanceOf(target, String.valueOf(instance));
	}

	/**
	 * <p>Counts how many times an instance of a specified substring occurs in the
	 * target string.</p>
	 * 
	 * @param target
	 * @param instance
	 * @return int
	 */
	public static int countInstanceOf(String target, String instance)
	{
		int fieldNum = 0;
		int pos = 0;
		int val = 0;
		boolean stop = false;

		while (!stop) {

			val = target.indexOf(instance, pos);

			if (val != -1) {
				fieldNum++;
				pos = val + 1;
			}
			else {
				stop = true;
			}
		}

		return fieldNum;
	}
	
	/**
	 * <p>Given an array of bytes, it will return the representation as a string of hex data.
	 * This is primarily useful when needing to display encrypted data as a readable string.</p>
	 * 
	 * @param bytes
	 * 	- the bytes to convert to hex
	 * @return {@link String}
	 */
	public static String toHexString(byte[] bytes)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			sb.append(new char[] {HEX_CHARS[(b >> 4) & 0x0f], HEX_CHARS[b & 0x0f]});
		}
		
		return sb.toString();
	}
	
	/**
	 * <p>Returns a formatted string in the java style exception format.</p>
	 * 
	 * @param ex
	 * 	- the exception to format
	 * @return {@link String}
	 */
	public static String formatException(Throwable ex)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(ex.getMessage());
		sb.append("\n");
		StackTraceElement[] trace = ex.getStackTrace();
		for(int i=0; i < trace.length; i++) {
			sb.append("\t");
			sb.append(trace[i]);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * <p>Given a String it will pad it to the specified length.</p>
	 *  
	 * @param string
	 * 	- the string to pad
	 * @param length
	 * 	- the amount of characters to pad
	 * @param charToPad
	 * 	- the pad character to use
	 * @return {@link String}
	 * 	- the padded string
	 */
	public static String padStringWith(String string, int length, char charToPad)
	{
		if(string.length() == length) {
			return string;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(string);
		int len = sb.length();
		
		for(int i=len; i < length; i++) {
			sb.insert(0, charToPad);
		}
		
		return sb.toString();
	}
	
	/**
	 * <p>Returns an empty string if the specified string is null.
	 * and returns the same string if it's not.</p>
	 * 
	 * @param str
	 * @return {@link String}
	 */
	public static final String getString(String str)
	{
		return getString(str, "");
	}
	
	/**
	 * <p>Returns a specified string <code>nullString</code> if the given string is null.
	 * and returns the same string if it's not.</p>
	 * 
	 * @param str
	 * @param nullString
	 * @return {@link String}
	 */
	public static final String getString(String str, String nullString)
	{
		if(str == null) {
			return nullString;
		}
		else {
			return str;
		}
	}
}
