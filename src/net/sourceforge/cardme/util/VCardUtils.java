package net.sourceforge.cardme.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.cardme.io.BinaryFoldingScheme;
import net.sourceforge.cardme.io.FoldingScheme;

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
 * Sep 21, 2006
 * 
 * <p>Utility methods to help with VCard parsing and reading.</p>
 */
public final class VCardUtils {

	/**
	 * <p>Carriage Return character. CR</p>
	 */
	public static final String CR = "\r";

	/**
	 * <p>Line Feed character. LF</p>
	 */
	public static final String LF = "\n";

	/**
	 * <p>Horizontal Tab character. HT</p>
	 */
	public static final String HT = "\t";

	/**
	 * <p>Space character. SP</p>
	 */
	public static final String SP = " ";

	/**
	 * <p>Internet Standard "END OF LINE" mark. CRLF</p>
	 */
	public static final String CRLF = CR + LF;

	/**
	 * <p>The "END OF LINE" delimiter used for folding labels</p>
	 */
	public static final String LABEL_DELIMETER = "=";
	
	/**
	 * <p>List of characters that need escaping.</p>
	 */
	private static final char[] NEED_ESCAPING = new char[] {
		',',
		';',
		':',
		'\\',
		'\n'
	};
	
	/**
	 * <p>List of characters that need un-escaping.</p>
	 */
	private static final String[] NEED_UNESCAPING = new String[] {
		"\\n",
		"\\N",
		"\\\\",
		"\\,",
		"\\;",
		"\\:"
	};
	
	private static NumberFormat GEO_NUM_FORMATTER = NumberFormat.getNumberInstance();
	
	static {
		GEO_NUM_FORMATTER.setMinimumFractionDigits(6);
		GEO_NUM_FORMATTER.setMaximumFractionDigits(6);
		GEO_NUM_FORMATTER.setMaximumIntegerDigits(6);
		GEO_NUM_FORMATTER.setMinimumIntegerDigits(1);
		GEO_NUM_FORMATTER.setGroupingUsed(false);
	}
	
	/**
	 * <p>Private constructor.</p>
	 */
	private VCardUtils() {
		
	}
	
	/**
	 * <p>Returns true if the specified textual string contains any
	 * one of the following characters defined in the constant char[]
	 * of {@link #NEED_ESCAPING}.</p>
	 *
	 * @param text
	 * @return boolean
	 */
	public static boolean needsEscaping(String text)
	{
		boolean needs = false;
		for(int i = 0; i < NEED_ESCAPING.length; i++) {
			if(text.indexOf(NEED_ESCAPING[i]) != -1) {
				needs = true;
				break;
			}
		}
		
		return needs;
	}
	
	/**
	 * <p>Returns true if the specified textual string contains any
	 * one of the following characters defined in the constant String[]
	 * of {@link #NEED_UNESCAPING}.</p>
	 *
	 * @param text
	 * @return boolean
	 */
	public static boolean needsUnEscaping(String text)
	{
		boolean needs = false;
		for(int i = 0; i < NEED_UNESCAPING.length; i++) {
			if(text.indexOf(NEED_UNESCAPING[i]) != -1) {
				needs = true;
				break;
			}
		}
		
		return needs;
	}
	
	/**
	 * <p>Effectively un-escapes a string, performs the reverse of {@link #escapeString(String)}.</p>
	 *
	 * @param text
	 * @return {@link String}
	 */
	public static String unescapeString(String text)
	{
		String unescaped = text.replaceAll("\\\\n", "\n");
		unescaped = unescaped.replaceAll("\\\\N", "\n");
		unescaped = unescaped.replaceAll("\\\\\\\\", "\\\\");
		unescaped = unescaped.replaceAll("\\\\,", ",");
		unescaped = unescaped.replaceAll("\\\\;", ";");
		unescaped = unescaped.replaceAll("\\\\:", ":");
		return unescaped;
	}
	
	/**
	 * <p>Escapes special characters in a string to be suitable for SQL.
	 * Characters that are escaped are the following:
	 * <ul>
	 * 	<li><b>EOL</b> -> \n or \N</li> 
	 * 	<li><b>\</b> -> \\</li>
	 * 	<li><b>,</b> -> \,</li>
	 * 	<li><b>;</b> -> \;</li>
	 * </p>
	 * 
	 * @param text
	 * @return {@link String}
	 */
	public static String escapeString(String text)
	{
		if(!needsEscaping(text)) {
			return text;
		}
		
		StringBuilder sb = new StringBuilder(text.length());
		for(int i = 0; i < text.length(); ++i) {
			char c = text.charAt(i);
			switch (c)
			{
				case '\n':
				{
					sb.append('\\');
					sb.append('n');
					break;
				}
				
				case '\\':
				{
					sb.append('\\');
					sb.append('\\');
					break;
				}
				
				case ',':
				{
					sb.append('\\');
					sb.append(',');
					break;
				}
				
				case ';':
				{
					sb.append('\\');
					sb.append(';');
					break;
				}
				
				case ':':
				{
					sb.append('\\');
					sb.append(':');
					break;
				}
				
				default:
				{
					sb.append(c);
				}
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * <p>Returns true if the given String <code>line</code> is greater than the maximum allowed
	 * characters needed for folding (75 chars per line excluding CRLF.)</p>
	 * 
	 * @param line
	 * @return boolean
	 */
	public static boolean needsFolding(String line)
	{
		return needsFolding(line, FoldingScheme.MIME_DIR);
	}
	
	/**
	 * <p>Returns true if the given String is greater than the maximum allowed
	 * characters needed for folding as specified by <code>foldingScheme</code> excluding
	 * the CRLF at the end.</p>
	 *
	 * @param line
	 * @param foldingScheme
	 * @return boolean
	 */
	public static boolean needsFolding(String line, FoldingScheme foldingScheme)
	{
		return needsFolding(line, foldingScheme.getMaxChars());
	}
	
	/**
	 * <p>Returns true if the given String is greater than the maximum allowed
	 * characters needed for folding as specified by <code>foldingScheme</code> excluding
	 * the CRLF at the end.</p>
	 *
	 * @param line
	 * @param maxChars
	 * @return boolean
	 */
	public static boolean needsFolding(String line, int maxChars)
	{
		if(maxChars < 0) {
			return false;
		}
		else {
			return line.length() > maxChars;
		}
	}
	
	/**
	 * <p>First line unfolds all the labels
	 * Second line unfolds all the lines (if needed.)</p>
	 * 
	 * <p>This unfolding technique is according to the MIME-DIR specifications
	 * as described in section 5.8.1 "Line delimiting and folding."</p>
	 * 
	 * @param vcardString
	 * @return {@link String}
	 */
	public static String unfoldVCard(String vcardString)
	{
		//Reformat quoted printable soft line break that violates RFC-822 (if present)
		
		String unfold1 = "=\n(\\p{Blank}*(=))";
		String unfold2 = "=\n([^\\p{Blank}]([^:\n]*))\n";
		String unfold3 = "\\p{Blank}+=";
		String unfold4 = "\n\\p{Blank}+";
		
		String s1 = vcardString.replaceAll(unfold1, "$1");
		String s2 = s1.replaceAll(unfold2, "$1");
		String s3 = s2.replaceAll(unfold4, "");
		String s4 = s3.replaceAll(unfold3, "=");
		
		return s4;
	}
	
	/**
	 * <p>Takes a single line and folds it according to the MIME-DIR specification.
	 * A line is folded its length exceeds 75 characters. For every 75
	 * characters a CRLF is appended to the end, the following line will begin
	 * with a SP character and then followed by the usual next 75 chars + CRLF
	 * until the end.</p>
	 * 
	 * <p>This method will not check the length of the String before folding, use
	 * the VCardUtils.needsFolding(String) method before invoking this one. This
	 * method will start the folding procedure right away. Running a line that
	 * doesn't need folding through here won't affect the outcome, but creates
	 * extra over head.</p>
	 * 
	 * @param thisLine
	 * @return {@link String}
	 */
	public static String foldLine(String thisLine)
	{
		return foldLine(thisLine, FoldingScheme.MIME_DIR);
	}
	
	/**
	 * <p>Takes a single line and folds it according to the MIME-DIR specification.
	 * The line is folded if its length exceeds <code>maxChars</code> characters.
	 * For every <code>maxChars</code> characters a CRLF is appended to the end,
	 * the following line will begin with a SP character and then followed by the
	 * usual next <code>maxChars</code> chars + CRLF until the end.</p>
	 * 
	 * <p>Strings coming out of here will always terminate with CRLF.</p>
	 * 
	 * <p>This method will not check the length of the String before folding, use
	 * the VCardUtils.needsFolding(String) method before invoking this one. This
	 * method will start the folding procedure right away. Running a line that
	 * doesn't need folding through here won't affect the outcome, but creates
	 * extra over head.</p>
	 * 
	 * @param thisLine
	 * @param foldingScheme
	 * @return {@link String}
	 */
	public static String foldLine(String thisLine, FoldingScheme foldingScheme)
	{
		return foldLine(thisLine, VCardUtils.CRLF, foldingScheme);
	}
	
	/**
	 * <p>Takes a single line and folds it according to the MIME-DIR specification.
	 * The line is folded if its length exceeds <code>maxChars</code> characters.
	 * For every <code>maxChars</code> characters a CRLF is appended to the end,
	 * the following line will begin with a SP character and then followed by the
	 * usual next <code>maxChars</code> chars + CRLF until the end.</p>
	 * 
	 * <p>Strings coming out of here will always terminate with CRLF.</p>
	 * 
	 * <p>This method will not check the length of the String before folding, use
	 * the VCardUtils.needsFolding(String) method before invoking this one. This
	 * method will start the folding procedure right away. Running a line that
	 * doesn't need folding through here won't affect the outcome, but creates
	 * extra over head.</p>
	 * 
	 * @param thisLine
	 * @param binaryFoldingScheme
	 * @return {@link String}
	 */
	public static String foldLine(String thisLine, BinaryFoldingScheme binaryFoldingScheme)
	{
		return foldLine(thisLine, VCardUtils.CRLF, binaryFoldingScheme.getMaxChars(), binaryFoldingScheme.getIndent());
	}
	
	/**
	 *  <p>Does the same job as {@link #foldLine(String)} but allows you
	 * to specify a String to be append when the line folds. By default
	 * it is CRLF. Here you can specify what you want. Useful when folding
	 * Quoted-Printable labels. Though a trailing delimiter may appear.</p>
	 *
	 * @param thisLine
	 * @param eolDelimeter
	 * @param binaryFoldingScheme
	 * @return {@link String}
	 */
	public static String foldLine(String thisLine, String eolDelimeter, BinaryFoldingScheme binaryFoldingScheme)
	{
		return foldLine(thisLine, eolDelimeter, binaryFoldingScheme.getMaxChars(), binaryFoldingScheme.getIndent());
	}
	
	/**
	 * <p>Does the same job as {@link #foldLine(String)} but allows you
	 * to specify a String to be append when the line folds. By default
	 * it is CRLF. Here you can specify what you want. Useful when folding
	 * Quoted-Printable labels. Though a trailing delimiter may appear.</p>
	 * 
	 * @param thisLine
	 * @param eolDelimeter
	 * @param foldingScheme
	 * @return {@link String}
	 */
	public static String foldLine(String thisLine, String eolDelimeter, FoldingScheme foldingScheme)
	{
		return foldLine(thisLine, eolDelimeter, foldingScheme.getMaxChars(),foldingScheme.getIndent());
	}
	
	/**
	 * <p>Does the same job as {@link #foldLine(String)} but allows you
	 * to specify a String to be append when the line folds. By default
	 * it is CRLF. Here you can specify what you want. Useful when folding
	 * Quoted-Printable labels. Though a trailing delimiter may appear.</p>
	 *
	 * @param thisLine
	 * @param eolDelimeter
	 * @param maxChars
	 * @param indent
	 * @return {@link String}
	 */
	public static String foldLine(String thisLine, String eolDelimeter, int maxChars, String indent)
	{
		if(!needsFolding(thisLine, maxChars)) {
			return thisLine;
		}
		
		boolean loop = true;
		boolean first = true;
		int crnt = 0;
		int prev = 0;

		StringBuilder builder = new StringBuilder();
		while (loop) {
			prev = crnt;
			crnt = crnt + maxChars;

			if (crnt > thisLine.length()) {
				// Append any extra characters at the end
				if (prev < thisLine.length()) {
					if (!first) {
						builder.append(indent); // on some rare occasions
					}

					builder.append(thisLine.substring(prev).trim());
					if(eolDelimeter != null) 
						builder.append(eolDelimeter);
				}

				loop = false;
			}
			else {
				if (!first) {
					builder.append(indent);

				}
				else {
					first = false;
				}

				builder.append(thisLine.substring(prev, crnt).trim());
				if(eolDelimeter != null) {
					builder.append(eolDelimeter);
				}
			}
		}
		
		String finalStr = builder.toString();
		if(finalStr.endsWith(eolDelimeter)) {
			finalStr = finalStr.substring(0, finalStr.lastIndexOf(eolDelimeter));
		}
		
		return finalStr.trim(); // removes any extra white-space at the end
	}
	
	/**
	 * <p>Returns the number formatter for the geographical position feature.
	 * This will format the floating value to the 6th decimal place.</p>
	 *
	 * @return {@link NumberFormat}
	 */
	public static NumberFormat getGeographicPositionFormatter()
	{
		return GEO_NUM_FORMATTER;
	}
	
	
	/**
	 * <p>Parses the specified {@link String} <code>str</code> with the given
	 * delimiter <code>delim</code> and returns an array of {@link String}
	 * delimited by the said delimiter. Note that this method takes into
	 * account characters escaped by a backslash.</p>
	 *
	 * @param str
	 * @param delim
	 * @return {@link String}[]
	 */
	public static String[] parseStringWithEscappedDelimiter(String str, char delim)
	{
		int i = 0;
		int state = 0;
		char c;
		int prev = 0;
		boolean firstTime = true;
		char[] testStringarr = str.toCharArray();
		List<String> strList = new ArrayList<String>(5);

		for(int len = testStringarr.length; i < len; i++) {
			c = testStringarr[i];

			if(state == 0) {
				if(c == '\\') {
					state++;
				}
				else if (c == delim) {
					if(firstTime) {
						strList.add(str.substring(prev, i));
					}
					else {
						strList.add(str.substring(prev+1, i));
					}

					if(prev == 0) {
						firstTime = false;
					}

					prev = i;
				}
			}
			else {
				state--;
			}
		}
		
		if(firstTime) {
			strList.add(str.substring(prev));
		}
		else {
			strList.add(str.substring(prev+1));
		}
		
		return strList.toArray(new String[strList.size()]);
	}
}
