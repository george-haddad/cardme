package net.sourceforge.cardme.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
 * Feb 5, 2010
 * 
 * <p>General Java related utility class to make some things easier.</p>
 */
public final class Util {
	
	private Util() {
		
	}

	/**
	 * <p>Given an array of a particular type, it will return it
	 * as a List of the same type.</p>
	 * 
	 * @param array - the array to convert into a generic list
	 * @return a generic {@link List} of type <code>E</code>
	 */
	public static <E> List<E> asList(E[] array)
	{
		List<E> list = new ArrayList<E>(array.length);
		for(E e : array) {
			list.add(e);
		}
		
		return list;
	}
	
	/**
	 * <p>Given an indeterminate array of strings; a hashcode is generated and returned.</p>
	 * 
	 * @param args - the array of {@link String}s to produce a hashcode from
	 * @return the calculated haschode of <code>args</code>
	 */
	public static int generateHashCode(String ... args)
	{
		int length = 0;
		char[] cArray = null;
		if(args.length == 1) {
			length = args[0].length();
			cArray = args[0].toCharArray();
		}
		else {
			for(int i = 0; i < args.length; i++) {
				length += args[i].length();
			}
			
			cArray = new char[length];
			int incrementer = 0;
			for(int i = 0; i < args.length; i++) {
				String str = args[i];
				for(int j = 0; j < str.length(); j++) {
					cArray[incrementer] = str.charAt(j);
					++incrementer;
				}
			}
		}
		
		int h = 0;
		for (int i = 0; i < cArray.length; i++) {
			h = 31*h + cArray[i];
		}
		
		return h;
	}
	
	/**
	 * <p>Given a File object it will read and return the entire file
	 * as an array of bytes.</p>
	 *
	 * @param file - the file to retrieve as bytes
	 * @return an array of bytes read in from file <code>file</code>
	 * @throws IOException if any I/O errors occur
	 */
	public static byte[] getFileAsBytes(File file) throws IOException
	{
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			in.read(bytes);
			return bytes;
		}
		finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
