package net.sourceforge.cardme.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import net.sourceforge.cardme.io.BinaryFoldingScheme;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.io.FoldingScheme;

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
		'\n',
		'\r'
	};
	
	/**
	 * <p>List of characters that need un-escaping.</p>
	 */
	private static final String[] NEED_UNESCAPING = new String[] {
		"\\r",
		"\\n",
		"\\N",
		"\\\\",
		"\\,",
		"\\;",
		"\\:"
	};
	
	private static NumberFormat GEO_NUM_FORMATTER = NumberFormat.getNumberInstance(Locale.US);
	
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
		if (!needsUnEscaping(text)){
			return text;
		}
		
		String unescaped = text.replaceAll("\\\\n", "\n");
		unescaped = unescaped.replaceAll("\\\\r", "\r");
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
				
				case '\r':
				{
					sb.append('\\');
					sb.append('r');
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
		return unfoldVCard(vcardString, CompatibilityMode.RFC2426);
	}
	
	/**
	 * <p>First line unfolds all the labels
	 * Second line unfolds all the lines (if needed.) A compatibility mode
	 * parameter is sent to handle application specific quirks (such as VCards
	 * generated by Evolution mail client.)</p>
	 * 
	 * <p>This unfolding technique is according to the MIME-DIR specifications
	 * as described in section 5.8.1 "Line delimiting and folding."</p>
	 * 
	 * @param vcardString
	 * @param compatMode
	 * @return {@link String}
	 */
	public static String unfoldVCard(String vcardString, CompatibilityMode compatMode)
	{
		String unfold1 = "=\n([^\\p{Blank}]([^:\n]*))\n";
		String unfold2 = null;
		
		if(CompatibilityMode.EVOLUTION.equals(compatMode)) {
			unfold2 = "\n\\p{Blank}{1}";
		}
		else {
			unfold2 = "\n\\p{Blank}+";
		}
		
		String s1 = vcardString.replaceAll(unfold1, "$1");
		String s2 = s1.replaceAll(unfold2, "");
		return s2;
	}
	
	/**
	 * <p>Given a quoted-printable string and a folding scheme it will return
	 * a folded line according to MIME-DIR specifications.</p>
	 *
	 * @param line
	 * @param foldingScheme
	 * @return a folded quoted-printable line
	 */
	public static String foldQuotedPrintableLine(String line, FoldingScheme foldingScheme)
	{
		if(line.length() > foldingScheme.getMaxChars()) {
			int pos = 0;
			char[] string = line.toCharArray();
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < string.length; i++) {
				char c = string[i];
				pos++;
				
				if(pos == foldingScheme.getMaxChars()) {
					//Fold
					if(c == '=') {
						sb.append('=');
						sb.append('\r');
						sb.append('\n');
						sb.append(' ');
						sb.append(c);
						pos = 2; //includes the space and char c
					}
					else {
						if((i+1) < string.length) { 
							char c2 = string[i+1];
							if(c2 == '=') {
								sb.append('=');
								sb.append('\r');
								sb.append('\n');
								sb.append(' ');
								sb.append(c);
								pos = 2; //includes the space and char c
							}
							else {
								sb.append('=');
								sb.append('\r');
								sb.append('\n');
								sb.append(' ');
								sb.append(c);
								pos = 2; //includes the space and char c
							}
						}
					}
				}
				else {
					sb.append(c);
				}
			}
			
			return sb.toString();
		}
		else {
			return line;
		}
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

				builder.append(thisLine.substring(prev, crnt));
				//builder.append(thisLine.substring(prev, crnt).trim());
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
	
	private static final Pattern emptyLinePattern = Pattern.compile("$");
	private static final Pattern startQpLinePattern = Pattern.compile(".*;([ \\t]*ENCODING[ \\t]*=)?[ \\t]*QUOTED-PRINTABLE.*:.*=", Pattern.CASE_INSENSITIVE);
	private static final Pattern middleQpLinePattern = Pattern.compile("\\s*.+=?");
	private static final Pattern vcardTypePattern = Pattern.compile("^[ \\t]*\\p{ASCII}+:.*$");
	
	public static String unfoldQuotedPrintableLines(String qpString) throws IOException
	{
		BufferedReader br = null;
		String vcardStr = null;
		
		try {
			br = new BufferedReader(new StringReader(qpString));
			StringBuilder sb = new StringBuilder();
			String line = null;
			boolean prevFolded = false;
			boolean isQuotedPrintable = false;
			boolean isQuotedPrintableEnd = false;
			
			while((line = br.readLine()) != null) {
				if(!emptyLinePattern.matcher(line).matches()) {
					//non-empty line
					if(isQuotedPrintable && prevFolded && !isQuotedPrintableEnd) {
						//I am expecting more quoted printable lines
						
						//This is true if the current line is the start
						//of a VCard type, regardless of what was before it.
						if(vcardTypePattern.matcher(line).matches()) {
							isQuotedPrintableEnd = true;
							isQuotedPrintable = false;
							prevFolded = false;
							sb.append('\n');
						}
					}
					
					if(startQpLinePattern.matcher(line).matches()) {
						//A quoted-printable line that has a soft-line break fold 
						isQuotedPrintableEnd = false;
						isQuotedPrintable = true;
						prevFolded = true;
						line = line.trim();
						String s = line.substring(0, line.lastIndexOf('='));
						sb.append(s);
					}
					else if(middleQpLinePattern.matcher(line).matches() && isQuotedPrintable && prevFolded) {
						if(line.endsWith("=")) {
							//Line is folded and there is more
							line = line.trim();
							String s = line.substring(0, line.lastIndexOf('='));
							sb.append(s);
						}
						else {
							//This is the last line
							sb.append(line.trim());
							sb.append('\n');
							isQuotedPrintable = false;
							prevFolded = false;
							isQuotedPrintableEnd = true;
						}
					}
					else {
						sb.append(line);
						sb.append('\n');
					}
				}
			}
			
			vcardStr = sb.toString();
		}
		finally {
			if(br != null) {
				br.close();
			}
		}
		
		return vcardStr;
	}
}
