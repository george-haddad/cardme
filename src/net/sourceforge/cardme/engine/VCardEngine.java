package net.sourceforge.cardme.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.util.Base64Wrapper;
import net.sourceforge.cardme.util.ISOUtils;
import net.sourceforge.cardme.util.VCardUtils;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.VCardVersion;
import net.sourceforge.cardme.vcard.errors.ErrorSeverity;
import net.sourceforge.cardme.vcard.errors.VCardBuildException;
import net.sourceforge.cardme.vcard.errors.VCardError;
import net.sourceforge.cardme.vcard.errors.VCardException;
import net.sourceforge.cardme.vcard.features.AddressFeature;
import net.sourceforge.cardme.vcard.types.AddressType;
import net.sourceforge.cardme.vcard.types.BeginType;
import net.sourceforge.cardme.vcard.types.BirthdayType;
import net.sourceforge.cardme.vcard.types.CategoriesType;
import net.sourceforge.cardme.vcard.types.ClassType;
import net.sourceforge.cardme.vcard.types.DisplayableNameType;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.EndType;
import net.sourceforge.cardme.vcard.types.ExtendedType;
import net.sourceforge.cardme.vcard.types.FormattedNameType;
import net.sourceforge.cardme.vcard.types.GeographicPositionType;
import net.sourceforge.cardme.vcard.types.IMPPType;
import net.sourceforge.cardme.vcard.types.KeyType;
import net.sourceforge.cardme.vcard.types.LabelType;
import net.sourceforge.cardme.vcard.types.LogoType;
import net.sourceforge.cardme.vcard.types.MailerType;
import net.sourceforge.cardme.vcard.types.NameType;
import net.sourceforge.cardme.vcard.types.NicknameType;
import net.sourceforge.cardme.vcard.types.NoteType;
import net.sourceforge.cardme.vcard.types.OrganizationType;
import net.sourceforge.cardme.vcard.types.PhotoType;
import net.sourceforge.cardme.vcard.types.ProductIdType;
import net.sourceforge.cardme.vcard.types.ProfileType;
import net.sourceforge.cardme.vcard.types.RevisionType;
import net.sourceforge.cardme.vcard.types.RoleType;
import net.sourceforge.cardme.vcard.types.SortStringType;
import net.sourceforge.cardme.vcard.types.SoundType;
import net.sourceforge.cardme.vcard.types.SourceType;
import net.sourceforge.cardme.vcard.types.TelephoneType;
import net.sourceforge.cardme.vcard.types.TimeZoneType;
import net.sourceforge.cardme.vcard.types.TitleType;
import net.sourceforge.cardme.vcard.types.UIDType;
import net.sourceforge.cardme.vcard.types.URLType;
import net.sourceforge.cardme.vcard.types.VersionType;
import net.sourceforge.cardme.vcard.types.media.AudioMediaType;
import net.sourceforge.cardme.vcard.types.media.ImageMediaType;
import net.sourceforge.cardme.vcard.types.media.KeyTextType;
import net.sourceforge.cardme.vcard.types.parameters.AddressParameterType;
import net.sourceforge.cardme.vcard.types.parameters.BirthdayParameterType;
import net.sourceforge.cardme.vcard.types.parameters.EmailParameterType;
import net.sourceforge.cardme.vcard.types.parameters.ExtendedParameterType;
import net.sourceforge.cardme.vcard.types.parameters.IMPPParameterType;
import net.sourceforge.cardme.vcard.types.parameters.LabelParameterType;
import net.sourceforge.cardme.vcard.types.parameters.TelephoneParameterType;
import net.sourceforge.cardme.vcard.types.parameters.URLParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XAddressParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XEmailParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XIMPPParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XLabelParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XTelephoneParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XURLParameterType;
import org.apache.commons.codec.net.QuotedPrintableCodec;

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
 * May 6, 2009 - 11:29:02 AM
 * <p>
 * @author Michael Angstadt
 * <br/>
 * Jul 06, 2012
 * 
 */
public class VCardEngine {

	/**
	 * <p>Selects from a list of application compatibility modes
	 * to use when formatting the output of the vcard. Some applications
	 * expect a certain type of formatting or non-standard types.</p>
	 */
	private CompatibilityMode compatMode = null;
	
	/**
	 * <p>If set, this charset will override any charset definition
	 * when decoding strings from any type that defines "CHARSET".</p>
	 */
	private Charset forceCharset = null;
	
	/**
	 * <p>Create a VCard parsing engine and initialize it to
	 * use RFC2426 compatibility mode by default.</p>
	 */
	public VCardEngine() {
		compatMode = CompatibilityMode.RFC2426;
	}
	
	/**
	 * <p>Create a VCard parsing engine with a user
	 * specified compatibility mode.</p>
	 * 
	 * @param compatMode
	 */
	public VCardEngine(CompatibilityMode compatMode) {
		this.compatMode = compatMode;
	}
	
	/**
	 * <p>Sets a specified compatibility mode.</p>
	 *
	 * @see CompatibilityMode
	 * @param compatMode
	 */
	public void setCompatibilityMode(CompatibilityMode compatMode) {
		if(compatMode == null) {
			this.compatMode = CompatibilityMode.RFC2426;
		}
		else {
			this.compatMode = compatMode;
		}
	}
	
	/**
	 * <p>Returns the currently set compatibility mode.
	 * Null if not set.</p>
	 *
	 * @return {@link CompatibilityMode}
	 */
	public CompatibilityMode getCompatibilityMode()
	{
		return compatMode;
	}
	
	/**
	 * <p>Sets the charset to always be used when the
	 * "CHARSET" parameter is encountered.</p>
	 *
	 * @param charset
	 */
	public void setForcedCharset(String charset) {
		forceCharset = Charset.forName(charset);
	}
	
	/**
	 * <p>Sets the charset to always be used when the
	 * "CHARSET" parameter is encountered.</p>
	 *
	 * @param charset
	 */
	public void setForcedCharset(Charset charset) {
		forceCharset = charset;
	}
	
	/**
	 * <p>Returns the charset that has been set 
	 * to be forced on all types, null if not set.</p>
	 *
	 * @return {@link Charset}
	 */
	public Charset getForcedCharset()
	{
		return forceCharset;
	}
	
	/**
	 * <p>Returns true if the engine is enforcing a charset
	 * on all types.</p>
	 *
	 * @return boolean
	 */
	public boolean isCharsetForced()
	{
		return forceCharset != null;
	}
	
	/**
	 * <p>Convenient method to parse an array of VCard files in one go.
	 * This returns an array of VCard objects in the same order they were
	 * parsed.</p>
	 *
	 * @param vcardFiles
	 * @return {@link VCard}[]
	 * @throws IOException
	 */
	public VCard[] parse(File[] vcardFiles) throws IOException
	{
		VCard[] vcards = new VCard[vcardFiles.length];
		for (int i = 0; i < vcardFiles.length; i++) {
			vcards[i] = parse(vcardFiles[i]);
		}
		
		return vcards;
	}
	
	/**
	 * <p>Parses the specified VCard file by retrieving the contents
	 * of the file, unfolding it and then parsing it. The returned result
	 * is a VCard java object.</p>
	 *
	 * @param vcardFile
	 * @return {@link VCard}
	 * @throws IOException
	 */
	public VCard parse(File vcardFile) throws IOException
	{
		String vcardStr = getContentFromFile(vcardFile);
		String unfoldedVcardStr = VCardUtils.unfoldVCard(vcardStr);
		return parseVCard(unfoldedVcardStr);
	}
	
	/**
	 * <p>Convenient method to parse an array of VCard strings in one go.
	 * This returns an array of VCard objects in the same order they were
	 * parsed.</p>
	 *
	 * @param vcardStrings
	 * @return {@link VCard}[]
	 * @throws IOException
	 */
	public VCard[] parse(String[] vcardStrings) throws IOException
	{
		VCard[] vcards = new VCard[vcardStrings.length];
		for (int i = 0; i < vcards.length; i++) {
			vcards[i] = parse(vcardStrings[i]);
		}
		
		return vcards;
	}
	
	/**
	 * <p>Parses the specified VCard String by retrieving the contents
	 * of the file, unfolding it and then parsing it. The returned result
	 * is a VCard java object.</p>
	 *
	 * @param vcardString
	 * @return {@link VCard}
	 * @throws IOException
	 */
	public VCard parse(String vcardString) throws IOException
	{
		String vcardStr = getContentFromString(vcardString);
		String unfoldedVcardStr = VCardUtils.unfoldVCard(vcardStr);
		return parseVCard(unfoldedVcardStr);
	}
	
	/**
	 * <p>Returns an iterator of VCards given a path that can
	 * contain an arbitrary number of vcards inside it.</p>
	 * 
	 * @param vcardPath
	 * @return {@link List}&lt;VCard&gt;
	 * @throws IOException
	 */
	public List<VCard> parseMultiple(String vcardPath) throws IOException
	{
		return parseMultiple(new File(vcardPath));
	}
	
	/**
	 * <p>Returns an iterator of VCards given a file that can
	 * contain an arbitrary number of vcards inside it.</p>
	 *
	 * @param vcardFile
	 * @return {@link List}&lt;VCard&gt;
	 * @throws IOException
	 */
	public List<VCard> parseMultiple(File vcardFile) throws IOException
	{
		String vcardStr = getContentFromFile(vcardFile);
		String unfoldedVcardStr = VCardUtils.unfoldVCard(vcardStr);
		return parseManyInOneVCard(unfoldedVcardStr);
	}
	
	/**
	 * <p>Parses the specified VCard String and returns a VCard java object.
	 * Should errors occur during the parsing of the VCard they will be handled
	 * within the VCard error handling interface. These errors can be retrieved 
	 * via the <code>{@link VCardImpl}.getErrors()</code> method.<br/>
	 * 
	 * This method assumes the following:
	 * <ol>
	 * 	<li>The String has only \n end of line markers instead of \r\n</li>
	 * 	<li>All lines in the String are unfolded</li>
	 * </ol>
	 * </p>
	 *
	 * @param vcardStr
	 * @return {@link VCard}
	 */
	private VCard parseVCard(String vcardStr)
	{
		VCardImpl vcard = new VCardImpl();
		vcard.setThrowExceptions(false);
		
		String[] lines = vcardStr.split("\n");
		for (int i = 0; i < lines.length; i++) {
			String vLine = lines[i];
			VCardLine parsedLine = VCardLine.parse(vLine);
			
			if(parsedLine != null) {
				try {
					parseLine(parsedLine, vcard);
				}
				catch(VCardBuildException vbe) {
					if(vcard.isThrowExceptions()) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vcard, vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
			else {
				if(vcard.isThrowExceptions()) {
					throw new VCardException("Invalid data in VCard on line "+i);
				}
				else {
					handleError(vcard, "Invalid data in VCard on line "+i, null, ErrorSeverity.FATAL);
				}
			}
		}

		return vcard;
	}
	
	private List<VCard> parseManyInOneVCard(String vcardStr)
	{
		List<VCard> vcards = new ArrayList<VCard>();
		List<String> enumCards = enumerateVCards(vcardStr);
		
		for (String card : enumCards) {
			VCard vcard = parseVCard(card);
			vcards.add(vcard);
		}
		
		return vcards;
	}
	
	private List<String> enumerateVCards(String vcardString)
	{
		List<String> vcardStrings = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		String[] split = vcardString.split("\r?\n");
		boolean begin = false;
		boolean end = false;
		
		for (String s : split) {
			if(s.matches("\\p{Blank}*((BEGIN)|(begin))\\p{Blank}*:\\p{Blank}*((VCARD)|(vcard))\\p{Blank}*")) {
				begin = true;
			}
			
			if(s.matches("\\p{Blank}*((END)|(end))\\p{Blank}*:\\p{Blank}*((VCARD)|(vcard))\\p{Blank}*")) {
				end = true;
			}
			
			if(begin && !end) {
				sb.append(s);
				sb.append('\n');
			}
			
			if(end) {
				sb.append(s);
				sb.append('\n');
				begin = false;
				end = false;
				vcardStrings.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		
		return vcardStrings;
	}
	
	/**
	 * <p>Parses a specific line of text from the VCard. This line is partitioned into
	 * the following segments.
	 * <ul>
	 * 	<li><b>group</b> Optional grouping name for the type.</li>
	 * 	<li><b>type</b> The vcard type that identifies this line.</li>
	 * 	<li><b>paramTypes</b> Any parameters that follow the type.</li>
	 * 	<li><b>value</b> The actual value or data of the type.</li>
	 * 	<li><b>vcard</b> The vcard object to append the type to once parsed.</li>
	 * </ul>
	 * </p>
	 *
	 * @param parsedLine
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseLine(VCardLine parsedLine, VCardImpl vcard) throws VCardBuildException {
		String type = parsedLine.getTypeName().trim().toUpperCase();
		String value = parsedLine.getValue().trim();
		List<ParameterType> paramTypes = parseParamTypes(parsedLine.getParameters());
		String group = parsedLine.getGroup();
		
		VCardType vCardType = null;
		
		//Extended Types are a bit special since they only start with X- and end with anything.
		if(type.startsWith("X-")) {
			vCardType = VCardType.XTENDED;
		}
		else {
			try {
				//Enums do not like hyphens so replace it with an underscore.
				type = type.replaceAll("-", "_");
				vCardType = VCardType.valueOf(type);
			}
			catch(IllegalArgumentException iae) {
				if(vcard.isThrowExceptions()) {
					throw new VCardException(iae.getMessage(), iae);
				}
				else {
					handleError(vcard, "Unrecognizable type name \""+type+"\"", iae, ErrorSeverity.WARNING);
					return;
				}
			}
		}
		
		switch (vCardType)
		{
			case BEGIN:
			{
				parseBeginType(group, value, vcard);
				break;
			}
			
			case END:
			{
				parseEndType(group, value, vcard);
				break;
			}
			
			case VERSION:
			{
				parseVersionType(group, value, vcard);
				break;
			}
			
			case FN:
			{
				parseFnType(group, value, paramTypes, vcard);
				break;
			}
			
			case N:
			{
				parseNType(group, value, paramTypes, vcard);
				break;
			}
			
			case NICKNAME:
			{
				parseNicknameType(group, value, paramTypes, vcard);
				break;
			}
			
			case PHOTO:
			{
				parsePhotoType(group, value, paramTypes, vcard);
				break;
			}
			
			case BDAY:
			{
				parseBDayType(group, value, paramTypes, vcard);
				break;
			}
			
			case ADR:
			{
				parseAdrType(group, value, paramTypes, vcard);
				break;
			}
			
			case LABEL:
			{
				parseLabelType(group, value, paramTypes, vcard);
				break;
			}
			
			case TEL:
			{
				parseTelType(group, value, paramTypes, vcard);
				break;
			}
			
			case EMAIL:
			{
				parseEmailType(group, value, paramTypes, vcard);
				break;
			}
			
			case MAILER:
			{
				parseMailerType(group, value, paramTypes, vcard);
				break;
			}
			
			case TZ:
			{
				parseTzType(group, value, paramTypes, vcard);
				break;
			}
			
			case GEO:
			{
				parseGeoType(group, value, paramTypes, vcard);
				break;
			}
			
			case TITLE:
			{
				parseTitleType(group, value, paramTypes, vcard);
				break;
			}
			
			case ROLE:
			{
				parseRoleType(group, value, paramTypes, vcard);
				break;
			}
			
			case LOGO:
			{
				parseLogoType(group, value, paramTypes, vcard);
				break;
			}
			
			case AGENT:
			{
				//TODO	parseAgentType(group, value, vcard);
				break;
			}
			
			case ORG:
			{
				parseOrgType(group, value, paramTypes, vcard);
				break;
			}
			
			case CATEGORIES:
			{
				parseCategoriesType(group, value, paramTypes, vcard);
				break;
			}
			
			case NOTE:
			{
				parseNoteType(group, value, paramTypes, vcard);
				break;
			}
			
			case PRODID:
			{
				parseProdidType(group, value, paramTypes, vcard);
				break;
			}
			
			case REV:
			{
				parseRevType(group, value, paramTypes, vcard);
				break;
			}
			
			case SORT_STRING:
			{
				parseSortStringType(group, value, paramTypes, vcard);
				break;
			}
			
			case SOUND:
			{
				parseSoundType(group, value, paramTypes, vcard);
				break;
			}
			
			case UID:
			{
				parseUidType(group, value, paramTypes, vcard);
				break;
			}
			
			case URL:
			{
				parseUrlType(group, value, paramTypes, vcard);
				break;
			}
			
			case CLASS:
			{
				parseClassType(group, value, paramTypes, vcard);
				break;
			}
			
			case KEY:
			{
				parseKeyType(group, value, paramTypes, vcard);
				break;
			}
			
			case XTENDED:
			{
				parseXtendedType(group, value, type, paramTypes, vcard);
				break;
			}
			
			case NAME:
			{
				parseDisplayableNameType(group, value, paramTypes, vcard);
				break;
			}
			
			case PROFILE:
			{
				parseProfileType(group, value, paramTypes, vcard);
				break;
			}
			
			case SOURCE:
			{
				parseSourceType(group, value, paramTypes, vcard);
				break;
			}
			
			case IMPP:
			{
				parseImppType(group, value, paramTypes, vcard);
				break;
			}

			default:
			{
				throw new VCardBuildException("Unhandled VCard type \""+vCardType.getType()+"\"");
			}
		}
	}
	
	/**
	 * <p>Parses the BEGIN type.</p>
	 * 
	 * @param group
	 * @param value
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseBeginType(String group, String value, VCardImpl vcard) throws VCardBuildException {
		try {
			BeginType beginFeature = new BeginType();
			if(value.compareToIgnoreCase("VCARD") == 0) {
				if(group != null) {
					beginFeature.setGroup(group);
				}
				
				vcard.setBegin(beginFeature);
			}
			else {
				throw new VCardBuildException("Invalid value for \"BEGIN\" type. Must be \"VCARD\"");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("BeginType ("+VCardType.BEGIN.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the END type.</p>
	 *
	 * @param group
	 * @param value
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseEndType(String group, String value, VCardImpl vcard) throws VCardBuildException {
		try {
			EndType endFeature = new EndType();
			if (value.compareToIgnoreCase("VCARD") == 0) {
				if(group != null) {
					endFeature.setGroup(group);
				}
				
				vcard.setEnd(endFeature);
			}
			else {
				throw new VCardException("Invalid value for \"END\" type. Must be \"VCARD\"");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("EndType ("+VCardType.END.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the VERSION type.</p>
	 *
	 * @param group
	 * @param value
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseVersionType(String group, String value, VCardImpl vcard) throws VCardBuildException {
		try {
			VersionType versionFeature = new VersionType();
			if(value.compareTo(VCardVersion.V3_0.getVersion()) == 0) {
				versionFeature.setVersion(VCardVersion.V3_0);
			}
			else if(value.compareTo(VCardVersion.V2_1.getVersion()) == 0) {
				versionFeature.setVersion(VCardVersion.V2_1);
			}
			else {
				throw new VCardException("Invalid value for \"VERSION\" type. Must be [3.0, 2.1]");
			}
			
			if(group != null) {
				versionFeature.setGroup(group);
			}
			
			vcard.setVersion(versionFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("VersionType ("+VCardType.VERSION.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the FN type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseFnType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			FormattedNameType formattedNameFeature = new FormattedNameType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					formattedNameFeature.setCharset(pt.getValue());
					value = new String(value.getBytes(), formattedNameFeature.getCharset());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						formattedNameFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					formattedNameFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					formattedNameFeature.addExtendedParameter(parameter);
				}
			}
			
			if(formattedNameFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();
				
				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(formattedNameFeature.hasCharset()) {
						value = q.decode(value, formattedNameFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}

			formattedNameFeature.setFormattedName(VCardUtils.unescapeString(value));
			
			if(group != null) {
				formattedNameFeature.setGroup(group);
			}
			
			vcard.setFormattedName(formattedNameFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("FormattedNameType ("+VCardType.FN.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the N type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseNType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			NameType nameFeature = new NameType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					nameFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						nameFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					nameFeature.setLanguage(pt.getValue());
				}
				else {
					throw new VCardBuildException("Invalid parameter type: "+pt);
				}
			}
			
			if(nameFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();
				
				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(nameFeature.hasCharset()) {
						value = q.decode(value, nameFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			switch(compatMode)
			{
				case MS_OUTLOOK:
				{
					parseNTypeOutlook(nameFeature, value);
					break;
				}

				default:
				{
					parseNTypeRFC(nameFeature, value);
					break;
				}
			}
			
			if(group != null) {
				nameFeature.setGroup(group);
			}

			vcard.setName(nameFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("NameType ("+VCardType.N.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	private NameType parseNTypeOutlook(NameType nameFeature, String value)
	{
		String[] names = VCardUtils.parseStringWithEscappedDelimiter(value, ';');
		
		for(int i=0; i < names.length; i++) {
			switch(i)
			{
				case 0:
				{
					nameFeature.setFamilyName(VCardUtils.unescapeString(names[0]));					
					
					break;
				}
				
				case 1:
				{
					nameFeature.setGivenName(VCardUtils.unescapeString(names[1]));
					
					break;
				}
				
				case 2:
				{
					String[] addNames = VCardUtils.parseStringWithEscappedDelimiter(names[2], ',');
					for(int j = 0; j < addNames.length; j++) {
						nameFeature.addAdditionalName(VCardUtils.unescapeString(addNames[j]));
					}
					
					break;
				}
				
				case 3:
				{
					String[] prefixes = VCardUtils.parseStringWithEscappedDelimiter(names[3], ',');
					for(int j = 0; j < prefixes.length; j++) {
						nameFeature.addHonorificPrefix(VCardUtils.unescapeString(prefixes[j]));
					}
					
					break;
				}
				
				case 4:
				{
					String[] suffixes = VCardUtils.parseStringWithEscappedDelimiter(names[4], ',');
					for(int j = 0; j < suffixes.length; j++) {
						nameFeature.addHonorificSuffix(VCardUtils.unescapeString(suffixes[j]));
					}
					
					break;
				}
			}
		}
		
		return nameFeature;
	}
	
	private NameType parseNTypeRFC(NameType nameFeature, String value)
	{
		String[] names = VCardUtils.parseStringWithEscappedDelimiter(value, ';');
		
		int cur = 0;
		if(names.length > cur && names[cur] != null) {
			if(VCardUtils.needsUnEscaping(names[cur])) {
				nameFeature.setFamilyName(VCardUtils.unescapeString(names[cur]));					
			}
			else {
				nameFeature.setFamilyName(names[cur]);
			}
		}
		cur++;
		
		if(names.length > cur && names[cur] != null) {
			if(VCardUtils.needsUnEscaping(names[cur])) {
				nameFeature.setGivenName(VCardUtils.unescapeString(names[cur]));
			}
			else {
				nameFeature.setGivenName(names[cur]);
			}
		}
		cur++;
		
		if(names.length > cur && names[cur] != null) {
			String[] addNames = VCardUtils.parseStringWithEscappedDelimiter(names[cur], ',');
			for(int i = 0; i < addNames.length; i++) {
				if(VCardUtils.needsUnEscaping(addNames[i])) {
					nameFeature.addAdditionalName(VCardUtils.unescapeString(addNames[i]));
				}
				else {
					nameFeature.addAdditionalName(addNames[i]);
				}
			}
		}
		cur++;
		
		if(names.length > cur && names[cur] != null) {
			String[] prefixes = VCardUtils.parseStringWithEscappedDelimiter(names[cur], ',');
			for(int i = 0; i < prefixes.length; i++) {
				if(VCardUtils.needsUnEscaping(prefixes[i])) {
					nameFeature.addHonorificPrefix(VCardUtils.unescapeString(prefixes[i]));
				}
				else {
					nameFeature.addHonorificPrefix(prefixes[i]);
				}
			}
		}
		cur++;
		
		if(names.length > cur && names[cur] != null) {
			String[] suffixes = VCardUtils.parseStringWithEscappedDelimiter(names[cur], ',');
			for(int i = 0; i < suffixes.length; i++) {
				if(VCardUtils.needsUnEscaping(suffixes[i])) {
					nameFeature.addHonorificSuffix(VCardUtils.unescapeString(suffixes[i]));
				}
				else {
					nameFeature.addHonorificSuffix(suffixes[i]);
				}
			}
		}
		
		return nameFeature;
	}
	
	/**
	 * <p>Parses the Nickname type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseNicknameType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			NicknameType nicknameFeature = new NicknameType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					nicknameFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						nicknameFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					nicknameFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					nicknameFeature.addExtendedParameter(parameter);
				}
			}
			
			if(nicknameFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();
				
				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(nicknameFeature.hasCharset()) {
						value = q.decode(value, nicknameFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			String[] nicknames = VCardUtils.parseStringWithEscappedDelimiter(value, ',');
			for(int i = 0; i < nicknames.length; i++) {
				if(VCardUtils.needsUnEscaping(nicknames[i])) {
					nicknameFeature.addNickname(VCardUtils.unescapeString(nicknames[i]));
				}
				else {
					nicknameFeature.addNickname(nicknames[i]);
				}
			}
			
			if(group != null) {
				nicknameFeature.setGroup(group);
			}
			
			vcard.setNicknames(nicknameFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("NicknameType ("+VCardType.NICKNAME.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the PHOTO type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parsePhotoType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			PhotoType photoFeature = new PhotoType();
			boolean isBinary = false;
			
			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					photoFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().compareToIgnoreCase(EncodingType.BINARY.getType()) == 0) {
						photoFeature.setEncodingType(EncodingType.BINARY);
						isBinary = true;
					}
					else if(pt.getValue().compareToIgnoreCase(EncodingType.BASE64.getType()) == 0) {
						photoFeature.setEncodingType(EncodingType.BINARY);
						isBinary = true;
					}
					else {
						throw new VCardBuildException("PhotoType ("+VCardType.PHOTO.getType()+") Invalid encoding type \""+pt.getValue()+"\"");
					}
				}
				else if(pt.getName().equals("TYPE")) {
					ImageMediaType mediaType = ImageMediaType.valueOf(pt.getValue());
					if (mediaType == null){
						mediaType = new ImageMediaType(pt.getValue(), pt.getValue(), pt.getValue());
					}
					photoFeature.setImageMediaType(mediaType);
				}
				else if(pt.getName().equals("VALUE")) {
					if(pt.getValue().compareToIgnoreCase("URI") == 0) {
						photoFeature.setEncodingType(EncodingType.EIGHT_BIT);
						isBinary = false;
					}
					else {
						throw new VCardBuildException("PhotoType ("+VCardType.PHOTO.getType()+") Invalid value type \""+pt.getValue()+"\"");
					}
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					photoFeature.addExtendedParameter(parameter);
				}
			}
			
			if(isBinary) {
				byte[] photoBytes = Base64Wrapper.decode(value);
				photoFeature.setCompression(false);
				photoFeature.setPhoto(photoBytes);
			}
			else {
				URI photoUri = new URI(value);
				photoFeature.setPhotoURI(photoUri);
			}
			
			if(group != null) {
				photoFeature.setGroup(group);
			}
			
			vcard.addPhoto(photoFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("PhotoType ("+VCardType.PHOTO.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the BDAY type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseBDayType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			BirthdayType birthdayFeature = new BirthdayType();
			
			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					birthdayFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("VALUE")) {
					if(pt.getValue().equals("DATE")) {
						birthdayFeature.setBirthdayParameterType(BirthdayParameterType.DATE);
					}
					else if(pt.getValue().equals("DATE-TIME")) {
						birthdayFeature.setBirthdayParameterType(BirthdayParameterType.DATE_TIME);
					}
					else {
						throw new VCardBuildException("Invalid parameter type: "+pt);
					}
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					birthdayFeature.addExtendedParameter(parameter);
				}
			}
			
			Calendar cal = ISOUtils.parseISO8601Date(value);
			birthdayFeature.setBirthday(cal);

			if(group != null) {
				birthdayFeature.setGroup(group);
			}
			
			vcard.setBirthday(birthdayFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("BirthdayType ("+VCardType.BDAY.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the ADR type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseAdrType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			AddressType addressFeature = new AddressType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					addressFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						addressFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("TYPE")) {
					if(pt.getValue().indexOf(',') != -1) {
						String[] typeValueList = pt.getValue().split(",");
						for(String typeValue : typeValueList) {
							setAdrParameterType(addressFeature, typeValue);
						}
					}
					else {
						setAdrParameterType(addressFeature, pt.getValue());
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					addressFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					addressFeature.addExtendedParameter(parameter);
				}
			}
			
			if(addressFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();
				
				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(addressFeature.hasCharset()) {
						value = q.decode(value, addressFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			String[] address = VCardUtils.parseStringWithEscappedDelimiter(value, ';');
			int i = 0;
			String postOfficeBox = (address.length > i) ? address[i] : null;
			i++;
			String extendedAddress = (address.length > i) ? address[i] : null;
			i++;
			String streetAddress = (address.length > i) ? address[i] : null;
			i++;
			String locality = (address.length > i) ? address[i] : null;
			i++;
			String region = (address.length > i) ? address[i] : null;
			i++;
			String postalCode = (address.length > i) ? address[i] : null;
			i++;
			String countryName = (address.length > i) ? address[i] : null;
			
			if(postOfficeBox != null) {
				if(VCardUtils.needsUnEscaping(postOfficeBox)) {
					addressFeature.setPostOfficeBox(VCardUtils.unescapeString(postOfficeBox));
				}
				else {
					addressFeature.setPostOfficeBox(postOfficeBox);
				}
			}
			
			if(extendedAddress != null) {
				if(VCardUtils.needsUnEscaping(extendedAddress)) {
					addressFeature.setExtendedAddress(VCardUtils.unescapeString(extendedAddress));
				}
				else {
					addressFeature.setExtendedAddress(extendedAddress);
				}
			}
			
			if(streetAddress != null) {
				if(VCardUtils.needsUnEscaping(streetAddress)) {
					addressFeature.setStreetAddress(VCardUtils.unescapeString(streetAddress));
				}
				else {
					addressFeature.setStreetAddress(streetAddress);
				}
			}
			
			if(locality != null) {
				if(VCardUtils.needsUnEscaping(locality)) {
					addressFeature.setLocality(VCardUtils.unescapeString(locality));
				}
				else {
					addressFeature.setLocality(locality);
				}
			}
			
			if(region != null) {
				if(VCardUtils.needsUnEscaping(region)) {
					addressFeature.setRegion(VCardUtils.unescapeString(region));
				}
				else {
					addressFeature.setRegion(region);
				}
			}
			
			if(postalCode != null) {
				if(VCardUtils.needsUnEscaping(postalCode)) {
					addressFeature.setPostalCode(VCardUtils.unescapeString(postalCode));
				}
				else {
					addressFeature.setPostalCode(postalCode);
				}
			}
			
			if(countryName != null) {
				if(VCardUtils.needsUnEscaping(countryName)) {
					addressFeature.setCountryName(VCardUtils.unescapeString(countryName));
				}
				else {
					addressFeature.setCountryName(countryName);
				}
			}
			
			if(group != null) {
				addressFeature.setGroup(group);
			}
			
			vcard.addAddress(addressFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("AddressType ("+VCardType.ADR.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Helper method for the above.</p>
	 *
	 * @param adrType
	 * @param paramValue
	 */
	private void setAdrParameterType(AddressType adrType, String paramValue) {
		try {
			String enumParamValue = paramValue.replace("-", "_").toUpperCase();
			AddressParameterType adrParamType = AddressParameterType.valueOf(enumParamValue);
			adrType.addAddressParameterType(adrParamType);
		}
		catch(IllegalArgumentException iae) {
			XAddressParameterType xAdrType = null;
			if(paramValue.indexOf('=') != -1) {
				String[] pTmp = paramValue.split("=");
				xAdrType = new XAddressParameterType(pTmp[0], pTmp[1]);
			}
			else {
				xAdrType = new XAddressParameterType(paramValue);
			}
			
			adrType.addExtendedAddressParameterType(xAdrType);
		}
	}
	
	/**
	 * <p>Parses the LABEL type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseLabelType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			LabelType labelFeature = new LabelType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					labelFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						labelFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("TYPE")) {
					if(pt.getValue().indexOf(',') != -1) {
						String[] typeValueList = pt.getValue().split(",");
						for(String typeValue : typeValueList) {
							setLabelParameterType(labelFeature, typeValue);
						}
					}
					else {
						setLabelParameterType(labelFeature, pt.getValue());
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					labelFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					labelFeature.addExtendedParameter(parameter);
				}
			}
			
			if(labelFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();
				
				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(labelFeature.hasCharset()) {
						value = q.decode(value, labelFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			labelFeature.setLabel(VCardUtils.unescapeString(value));
			
			if(group != null) {
				labelFeature.setGroup(group);
			}
			
			boolean match = false;
			Iterator<AddressFeature> addrIter = vcard.getAddresses();
			while(addrIter.hasNext() && !match) {
				
				//Get address and all its parameter and extended parameter types
				AddressFeature addr = addrIter.next();
				List<AddressParameterType> aPrmList = addr.getAddressParameterTypesList();
				List<XAddressParameterType> aXPrmList = addr.getExtendedAddressParameterTypesList();
				
				Iterator<LabelParameterType> lPrmIter = labelFeature.getLabelParameterTypes();
				Iterator<XLabelParameterType> lXPrmIter = labelFeature.getExtendedLabelParameterTypes();
				
				//See how many address parameter types match each label parameter type
				int paramsMatched = 0;
				while(lPrmIter.hasNext()) {
					LabelParameterType labelParamType = lPrmIter.next();
					for(AddressParameterType aPrm : aPrmList) {
						if(aPrm.getType().equals(labelParamType.getType())) {
							paramsMatched++;
						}
					}
				}
				
				//See how many extended address parameter types match each extended label parameter type
				int xparamsMatched = 0;
				while(lXPrmIter.hasNext()) {
					XLabelParameterType xlabelParamType = lXPrmIter.next();
					for(XAddressParameterType aXPrm : aXPrmList) {
						if(aXPrm.getType().equals(xlabelParamType.getType())) {
							xparamsMatched++;
						}
					}
				}
				
				//If the number of matching parameter types match between the label
				//and the address then this label belongs to the respective address.
				if(paramsMatched == labelFeature.getLabelParameterSize() && xparamsMatched == labelFeature.getExtendedLabelParameterSize()) {
					//Only set the label on the address if it does not already have one 
					if(!vcard.hasLabel(addr)) {
						vcard.setLabel(labelFeature, addr);
					}
					else {
						vcard.addError("Label with duplicate parameter tpyes was detected and ignored. Label -> "+labelFeature.toString(), ErrorSeverity.WARNING, new VCardBuildException("Duplicate label"));
					}
					
					match = true;
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("LabelType ("+VCardType.LABEL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Helper method for the above.</p>
	 *
	 * @param labelType
	 * @param paramValue
	 */
	private void setLabelParameterType(LabelType labelType, String paramValue) {
		try {
			String enumParamValue = paramValue.replace("-", "_").toUpperCase();
			LabelParameterType adrParamType = LabelParameterType.valueOf(enumParamValue);
			labelType.addLabelParameterType(adrParamType);
		}
		catch(IllegalArgumentException iae) {
			XLabelParameterType xLabelType = null;
			if(paramValue.indexOf('=') != -1) {
				String[] pTmp = paramValue.split("=");
				xLabelType = new XLabelParameterType(pTmp[0], pTmp[1]);
			}
			else {
				xLabelType = new XLabelParameterType(paramValue);
			}
			
			labelType.addExtendedLabelParameterType(xLabelType);
		}
	}
	
	/**
	 * <p>Parses the TEL type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseTelType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			TelephoneType telephoneFeature = new TelephoneType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					telephoneFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						telephoneFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("TYPE")) {
					if(pt.getValue().indexOf(',') != -1) {
						String[] typeValueList = pt.getValue().split(",");
						for(String typeValue : typeValueList) {
							setTelParameterType(telephoneFeature, typeValue);
						}
					}
					else {
						setTelParameterType(telephoneFeature, pt.getValue());
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					telephoneFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					telephoneFeature.addExtendedParameter(parameter);
				}
			}
			
			if(telephoneFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(telephoneFeature.hasCharset()) {
						value = q.decode(value, telephoneFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			telephoneFeature.setTelephone(VCardUtils.unescapeString(value));
			if(group != null) {
				telephoneFeature.setGroup(group);
			}
			
			vcard.addTelephoneNumber(telephoneFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("TelephoneType ("+VCardType.TEL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Helper method for the above.</p>
	 *
	 * @param telType
	 * @param paramValue
	 */
	private void setTelParameterType(TelephoneType telType, String paramValue) {
		try {
			String enumParamValue = paramValue.replace("-", "_").toUpperCase();
			TelephoneParameterType telParamType = TelephoneParameterType.valueOf(enumParamValue);
			telType.addTelephoneParameterType(telParamType);
		}
		catch(IllegalArgumentException iae) {
			XTelephoneParameterType xTelType = null;
			if(paramValue.indexOf('=') != -1) {
				String[] pTmp = paramValue.split("=");
				xTelType = new XTelephoneParameterType(pTmp[0], pTmp[1]);
			}
			else {
				xTelType = new XTelephoneParameterType(paramValue);
			}
			
			telType.addExtendedTelephoneParameterType(xTelType);
		}
	}
	
	/**
	 * <p>Parses the EMAIL type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseEmailType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			EmailType emailFeature = new EmailType();
			boolean isBinary = false;
			
			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					emailFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("TYPE")) {
					if(pt.getValue().indexOf(',') != -1) {
						String[] typeValueList = pt.getValue().split(",");
						for(String typeValue : typeValueList) {
							setEmailParameterType(emailFeature, typeValue);
						}
					}
					else {
						setEmailParameterType(emailFeature, pt.getValue());
					}
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().compareToIgnoreCase(EncodingType.BINARY.getType()) == 0) {
						emailFeature.setEncodingType(EncodingType.BINARY);
						isBinary = true;
					}
					else if(pt.getValue().compareToIgnoreCase(EncodingType.BASE64.getType()) == 0) {
						emailFeature.setEncodingType(EncodingType.BINARY);
						isBinary = true;
					}
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						emailFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
					else {
						throw new VCardBuildException("EmailType ("+VCardType.EMAIL.getType()+") Invalid encoding type \""+pt.getValue()+"\"");
					}
					
				}
				else if(pt.getName().equals("LANGUAGE")) {
					emailFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					emailFeature.addExtendedParameter(parameter);
				}
			}
			
			if(isBinary) {
				byte[] emailBytes = Base64Wrapper.decode(value);
				emailFeature.setCompression(false);
				if(emailFeature.hasCharset()) {
					emailFeature.setEmail(new String(emailBytes, emailFeature.getCharset()));
				}
				else {
					emailFeature.setEmail(new String(emailBytes, Charset.defaultCharset()));
				}
			}
			else {
				if(emailFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
					QuotedPrintableCodec q = new QuotedPrintableCodec();
					value = q.decode(value);
				}
				
				emailFeature.setEmail(value);
			}
			
			if(group != null) {
				emailFeature.setGroup(group);
			}
			
			vcard.addEmail(emailFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("EmailType ("+VCardType.EMAIL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Helper method for the above.</p>
	 *
	 * @param emailType
	 * @param paramValue
	 */
	private void setEmailParameterType(EmailType emailType, String paramValue) {
		try {
			String enumParamValue = paramValue.replace("-", "_").toUpperCase();
			EmailParameterType emailParamType = EmailParameterType.valueOf(enumParamValue);
			emailType.addEmailParameterType(emailParamType);
		}
		catch(IllegalArgumentException iae) {
			XEmailParameterType xEmailType = null;
			if(paramValue.indexOf('=') != -1) {
				String[] pTmp = paramValue.split("=");
				xEmailType = new XEmailParameterType(pTmp[0], pTmp[1]);
			}
			else {
				xEmailType = new XEmailParameterType(paramValue);
			}
			
			emailType.addExtendedEmailParameterType(xEmailType);
		}
	}
	
	/**
	 * <p>Parses the MAILER type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseMailerType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			MailerType mailerFeature = new MailerType();
			
			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					mailerFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						mailerFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					mailerFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					mailerFeature.addExtendedParameter(parameter);
				}
			}
			
			if(mailerFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(mailerFeature.hasCharset()) {
						value = q.decode(value, mailerFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			mailerFeature.setMailer(VCardUtils.unescapeString(value));
			if(group != null) {
				mailerFeature.setGroup(group);
			}
			
			vcard.setMailer(mailerFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("MailerType ("+VCardType.MAILER.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the TZ type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseTzType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			TimeZoneType timeZoneFeature = new TimeZoneType();
				//VALUE=TEXT
				//-05:00; EST; Raleigh/North America
			String paramValue = null;
			for (ParameterType pt : paramTypeList){
				if (pt.getName().equalsIgnoreCase("VALUE")){
					paramValue = pt.getValue();
				}
			}
			
			if("TEXT".equalsIgnoreCase(paramValue)) {
				String split[] = VCardUtils.parseStringWithEscappedDelimiter(value, ';');
				int cur = 0;
				
				if (split.length > cur && split[cur].length() > 0){
					timeZoneFeature.parseTimeZoneOffset(VCardUtils.unescapeString(split[cur]));
				}
				
				cur++;
				if (split.length > cur && split[cur].length() > 0){
					timeZoneFeature.setShortText(VCardUtils.unescapeString(split[cur]));
				}
				
				cur++;
				if (split.length > cur && split[cur].length() > 0){
					timeZoneFeature.setLongText(VCardUtils.unescapeString(split[cur]));
				}
			}
			else {
				timeZoneFeature.parseTimeZoneOffset(value);
			}
			
			if(group != null) {
				timeZoneFeature.setGroup(group);
			}
			
			vcard.setTimeZone(timeZoneFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("TimeZoneType ("+VCardType.TZ.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the GEO type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseGeoType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			GeographicPositionType geographicPositionFeature = new GeographicPositionType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					geographicPositionFeature.setCharset(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					geographicPositionFeature.addExtendedParameter(parameter);
				}
			}
			
			if(value.matches("-?\\d{1,3}\\.\\d{1,6}\\;-?\\d{1,3}\\.\\d{1,6}")) {
				String[] geo = value.split(";");
				String lat = geo[0];
				String lon = geo[1];
				geographicPositionFeature.setLatitude(Float.parseFloat(lat));
				geographicPositionFeature.setLongitude(Float.parseFloat(lon));
				
				if(group != null) {
					geographicPositionFeature.setGroup(group);
				}
				
				vcard.setGeographicPosition(geographicPositionFeature);
			}
			else {
				throw new VCardBuildException("GeographicPositionType ("+VCardType.GEO.getType()+") GeographicPositionType is not valid.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("GeographicPositionType ("+VCardType.GEO.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the TITLE type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseTitleType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			TitleType titleFeature = new TitleType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					titleFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						titleFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					titleFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					titleFeature.addExtendedParameter(parameter);
				}
			}
			
			if(titleFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(titleFeature.hasCharset()) {
						value = q.decode(value, titleFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			titleFeature.setTitle(VCardUtils.unescapeString(value));
			if(group != null) {
				titleFeature.setGroup(group);
			}
			
			vcard.setTitle(titleFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("TitleType ("+VCardType.TITLE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the ROLE type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseRoleType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			RoleType roleFeature = new RoleType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					roleFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						roleFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					roleFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					roleFeature.addExtendedParameter(parameter);
				}
			}
			
			if(roleFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(roleFeature.hasCharset()) {
						value = q.decode(value, roleFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			roleFeature.setRole(VCardUtils.unescapeString(value));
			if(group != null) {
				roleFeature.setGroup(group);
			}
			
			vcard.setRole(roleFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("RoleType ("+VCardType.ROLE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the LOGO type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseLogoType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			LogoType logoFeature = new LogoType();
			boolean isBinary = false;

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					logoFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().compareToIgnoreCase(EncodingType.BINARY.getType()) == 0) {
						logoFeature.setEncodingType(EncodingType.BINARY);
						isBinary = true;
					}
					else if(pt.getValue().compareToIgnoreCase(EncodingType.BASE64.getType()) == 0) {
						logoFeature.setEncodingType(EncodingType.BINARY);
						isBinary = true;
					}
					else {
						throw new VCardBuildException("LogoType ("+VCardType.LOGO.getType()+") Invalid encoding type \""+pt.getValue()+"\"");
					}
				}
				else if(pt.getName().equals("TYPE")) {
					ImageMediaType mediaType = ImageMediaType.valueOf(pt.getValue());
					if (mediaType == null){
						mediaType = new ImageMediaType(pt.getValue(), pt.getValue(), pt.getValue());
					}
					logoFeature.setImageMediaType(mediaType);
				}
				else if(pt.getName().equals("VALUE")) {
					if(pt.getValue().compareToIgnoreCase("URI") == 0) {
						logoFeature.setEncodingType(EncodingType.EIGHT_BIT);
						isBinary = false;
					}
					else {
						throw new VCardBuildException("LogoType ("+VCardType.LOGO.getType()+") Invalid value type \""+pt.getValue()+"\"");
					}
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					logoFeature.addExtendedParameter(parameter);
				}
			}
			
			if(isBinary) {
				byte[] logoBytes = Base64Wrapper.decode(value);
				logoFeature.setCompression(false);
				logoFeature.setLogo(logoBytes);
			}
			else {
				URI logoUri = new URI(value);
				logoFeature.setLogoURI(logoUri);
			}
			
			if(group != null) {
				logoFeature.setGroup(group);
			}
			
			vcard.addLogo(logoFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("LogoType ("+VCardType.LOGO.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the ORG type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseOrgType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			OrganizationType organizationFeature = new OrganizationType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					organizationFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						organizationFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					organizationFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					organizationFeature.addExtendedParameter(parameter);
				}
			}
			
			if(organizationFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(organizationFeature.hasCharset()) {
						value = q.decode(value, organizationFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			String[] orgs = VCardUtils.parseStringWithEscappedDelimiter(value, ';');
			for(int i = 0; i < orgs.length; i++) {
				if(VCardUtils.needsUnEscaping(orgs[i])) {
					String unesc = VCardUtils.unescapeString(orgs[i]);
					organizationFeature.addOrganization(unesc);
				}
				else {
					organizationFeature.addOrganization(orgs[i]);
				}
			}
			
			if(group != null) {
				organizationFeature.setGroup(group);
			}
			
			vcard.setOrganizations(organizationFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("OrganizationType ("+VCardType.ORG.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the CATEGORIES type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseCategoriesType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			CategoriesType categoriesFeature = new CategoriesType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					categoriesFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						categoriesFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					categoriesFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					categoriesFeature.addExtendedParameter(parameter);
				}
			}
			
			if(categoriesFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(categoriesFeature.hasCharset()) {
						value = q.decode(value, categoriesFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			String[] categories = null;
			switch(compatMode)
			{
				case KDE_ADDRESS_BOOK:
				{
					categories = VCardUtils.unescapeString(value).split(",");
					break;
				}
				
				default:
				{
					categories = VCardUtils.parseStringWithEscappedDelimiter(value, ',');
					break;
				}
			}
			
			for(int i = 0; i < categories.length; i++) {
				categoriesFeature.addCategory(VCardUtils.unescapeString(categories[i]));
			}
			
			if(group != null) {
				categoriesFeature.setGroup(group);
			}
			
			vcard.setCategories(categoriesFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("CategoriesType ("+VCardType.CATEGORIES.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the NOTE type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseNoteType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			NoteType noteFeature = new NoteType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					noteFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						noteFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					noteFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					noteFeature.addExtendedParameter(parameter);
				}
			}
			
			if(noteFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(noteFeature.hasCharset()) {
						value = q.decode(value, noteFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			noteFeature.setNote(VCardUtils.unescapeString(value));
			
			if(group != null) {
				noteFeature.setGroup(group);
			}
			
			vcard.addNote(noteFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("NoteType ("+VCardType.NOTE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the PRODID type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseProdidType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			ProductIdType productIdFeature = new ProductIdType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					productIdFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						productIdFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					productIdFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					productIdFeature.addExtendedParameter(parameter);
				}
			}
			
			if(productIdFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(productIdFeature.hasCharset()) {
						value = q.decode(value, productIdFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			productIdFeature.setProductId(VCardUtils.unescapeString(value));
			
			if(group != null) {
				productIdFeature.setGroup(group);
			}
			
			vcard.setProductId(productIdFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("ProductIdType ("+VCardType.PRODID.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the REV type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseRevType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			RevisionType revisionFeature = new RevisionType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					revisionFeature.setCharset(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					revisionFeature.addExtendedParameter(parameter);
				}
			}
			
			Calendar cal = ISOUtils.parseISO8601Date(value);
			revisionFeature.setRevision(cal);
			
			if(group != null) {
				revisionFeature.setGroup(group);
			}
			
			vcard.setRevision(revisionFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("RevisionType ("+VCardType.REV.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the SORT-STRING type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseSortStringType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			SortStringType sortStringFeature = new SortStringType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					sortStringFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						sortStringFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					sortStringFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					sortStringFeature.addExtendedParameter(parameter);
				}
			}
			
			if(sortStringFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(sortStringFeature.hasCharset()) {
						value = q.decode(value, sortStringFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			sortStringFeature.setSortString(VCardUtils.unescapeString(value));
			
			if(group != null) {
				sortStringFeature.setGroup(group);
			}
			
			vcard.setSortString(sortStringFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("SortStringType ("+VCardType.SORT_STRING.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the SOUND type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseSoundType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			SoundType soundFeature = new SoundType();
			boolean isBinary = false;

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					soundFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().compareToIgnoreCase(EncodingType.BINARY.getType()) == 0) {
						soundFeature.setEncodingType(EncodingType.BINARY);
						isBinary = true;
					}
					else if(pt.getValue().compareToIgnoreCase(EncodingType.BASE64.getType()) == 0) {
						soundFeature.setEncodingType(EncodingType.BINARY);
						isBinary = true;
					}
					else {
						throw new VCardBuildException("SoundType ("+VCardType.SOUND.getType()+") Invalid encoding type \""+pt.getValue()+"\"");
					}
				}
				else if(pt.getName().equals("TYPE")) {
					AudioMediaType mediaType = AudioMediaType.valueOf(pt.getValue());
					if (mediaType == null){
						mediaType = new AudioMediaType(pt.getValue(), pt.getValue(), pt.getValue());
					}
					soundFeature.setAudioMediaType(mediaType);
				}
				else if(pt.getName().equals("VALUE")) {
					if(pt.getValue().compareToIgnoreCase("URI") == 0) {
						soundFeature.setEncodingType(EncodingType.EIGHT_BIT);
						isBinary = false;
					}
					else {
						throw new VCardBuildException("SoundType ("+VCardType.SOUND.getType()+") Invalid value type \""+pt.getValue()+"\"");
					}
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					soundFeature.addExtendedParameter(parameter);
				}
			}
		
			if(isBinary) {
				byte[] soundBytes = Base64Wrapper.decode(value);
				soundFeature.setCompression(false);
				soundFeature.setSound(soundBytes);
			}
			else {
				URI soundUri = new URI(value);
				soundFeature.setSoundURI(soundUri);
			}
			
			if(group != null) {
				soundFeature.setGroup(group);
			}
			
			vcard.addSound(soundFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("SoundType ("+VCardType.SOUND.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the UID type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseUidType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			UIDType uidFeature = new UIDType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					uidFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						uidFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					uidFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					uidFeature.addExtendedParameter(parameter);
				}
			}
			
			if(uidFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(uidFeature.hasCharset()) {
						value = q.decode(value, uidFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			uidFeature.setUID(VCardUtils.unescapeString(value));
			
			if(group != null) {
				uidFeature.setGroup(group);
			}
			
			vcard.setUID(uidFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("UIDType ("+VCardType.UID.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the URL type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseUrlType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			URLType urlFeature = new URLType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					urlFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						urlFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					urlFeature.setLanguage(pt.getValue());
				}
				else {
					switch(compatMode)
					{
						case MS_OUTLOOK:
						case I_PHONE:
						case GMAIL:
						case MAC_ADDRESS_BOOK:
			                        case EVOLUTION:
			                        case IOS_EXPORTER:
			                        case KDE_ADDRESS_BOOK:
			                        case RFC2426:
						{
							if(pt.getName().equals("TYPE")) {
								if(pt.getValue().indexOf(',') != -1) {
									String[] typeValueList = pt.getValue().split(",");
									for(String typeValue : typeValueList) {
										setUrlParameterType(urlFeature, typeValue);
									}
								}
								else {
									setUrlParameterType(urlFeature, pt.getValue());
								}
							}
							else {
									ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
									urlFeature.addExtendedParameter(parameter);
							}
							
							break;
						}
						default:
						{
							throw new VCardBuildException("Invalid parameter type: "+pt);
						}
					}
				}
			}
			
			if(urlFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(urlFeature.hasCharset()) {
						value = q.decode(value, urlFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			urlFeature.setRawURL(VCardUtils.unescapeString(value));
			
			if(group != null) {
				urlFeature.setGroup(group);
			}
			
			vcard.addURL(urlFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("URLType ("+VCardType.URL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Helper method for the above.</p>
	 *
	 * @param urlType
	 * @param paramValue
	 */
	private void setUrlParameterType(URLType urlType, String paramValue) {
		try {
			String enumParamValue = paramValue.replace("-", "_").toUpperCase();
			URLParameterType urlParamType = URLParameterType.valueOf(enumParamValue);
			urlType.addURLParameterType(urlParamType);
		}
		catch(IllegalArgumentException iae) {
			XURLParameterType xUrlType = null;
			if(paramValue.indexOf('=') != -1) {
				String[] pTmp = paramValue.split("=");
				xUrlType = new XURLParameterType(pTmp[0], pTmp[1]);
			}
			else {
				xUrlType = new XURLParameterType(paramValue);
			}
			
			urlType.addExtendedURLParameterType(xUrlType);
		}
	}
	
	/**
	 * <p>Parses the CLASS type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseClassType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			ClassType classFeature = new ClassType();
			
			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					classFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						classFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					classFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					classFeature.addExtendedParameter(parameter);
				}
			}
			
			if(classFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(classFeature.hasCharset()) {
						value = q.decode(value, classFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			classFeature.setSecurityClass(VCardUtils.unescapeString(value));
			
			if(group != null) {
				classFeature.setGroup(group);
			}
			
			vcard.setSecurityClass(classFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("ClassType ("+VCardType.CLASS.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the KEY type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseKeyType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			KeyType keyFeature = new KeyType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					keyFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().compareToIgnoreCase(EncodingType.BINARY.getType()) == 0) {
						keyFeature.setEncodingType(EncodingType.BINARY);
					}
					else if(pt.getValue().compareToIgnoreCase(EncodingType.BASE64.getType()) == 0) {
						keyFeature.setEncodingType(EncodingType.BINARY);
					}
					else {
						throw new VCardBuildException("KeyType ("+VCardType.KEY.getType()+") Invalid encoding type \""+pt.getValue()+"\"");
					}
				}
				else if(pt.getName().equals("TYPE")) {
					KeyTextType keyTextType = KeyTextType.valueOf(pt.getValue());
					if (keyTextType == null){
						keyTextType = new KeyTextType(pt.getValue(), pt.getValue(), pt.getValue());
					}
					keyFeature.setKeyTextType(keyTextType);
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					keyFeature.addExtendedParameter(parameter);
				}
			}
			
			byte[] keyBytes = Base64Wrapper.decode(value);
			keyFeature.setCompression(false);
			keyFeature.setKey(keyBytes);
			
			if(group != null) {
				keyFeature.setGroup(group);
			}
			
			vcard.addKey(keyFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("KeyType ("+VCardType.KEY.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the EXTENDED type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param typeName
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseXtendedType(String group, String value, String typeName, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			ExtendedType extendedFeature = new ExtendedType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					extendedFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						extendedFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					extendedFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());					    					    
					extendedFeature.addExtendedParameter(parameter);
				}
			}
			
			if(extendedFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(extendedFeature.hasCharset()) {
						value = q.decode(value, extendedFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			extendedFeature.setExtensionName(typeName);
			extendedFeature.setExtensionData(VCardUtils.unescapeString(value));
			
			if(group != null) {
				extendedFeature.setGroup(group);
			}
			
			vcard.addExtendedType(extendedFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("ExtendedType ("+VCardType.XTENDED.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the NAME type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseDisplayableNameType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			DisplayableNameType displayableNameFeature = new DisplayableNameType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					displayableNameFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						displayableNameFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					displayableNameFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					displayableNameFeature.addExtendedParameter(parameter);
				}
			}
			
			if(displayableNameFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(displayableNameFeature.hasCharset()) {
						value = q.decode(value, displayableNameFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			displayableNameFeature.setName(VCardUtils.unescapeString(value));
			
			if(group != null) {
				displayableNameFeature.setGroup(group);
			}
			
			vcard.setDisplayableNameFeature(displayableNameFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("DisplayableNameType ("+VCardType.NAME.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the PROFILE type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseProfileType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			ProfileType profileFeature = new ProfileType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("CHARSET")) {
					profileFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						profileFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					profileFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					profileFeature.addExtendedParameter(parameter);
				}
			}
			
			if(profileFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(profileFeature.hasCharset()) {
						value = q.decode(value, profileFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			profileFeature.setProfile(VCardUtils.unescapeString(value));
			
			if(group != null) {
				profileFeature.setGroup(group);
			}
			
			vcard.setProfile(profileFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("ProfileType ("+VCardType.PROFILE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the SOURCE type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseSourceType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			SourceType sourceFeature = new SourceType();

			for (ParameterType pt : paramTypeList) {
				if(pt.getName().equals("CHARSET")) {
					sourceFeature.setCharset(pt.getValue());
				}
				else if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						sourceFeature.setEncodingType(EncodingType.QUOTED_PRINTABLE);
					}
				}
				else if(pt.getName().equals("LANGUAGE")) {
					sourceFeature.setLanguage(pt.getValue());
				}
				else {
					ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
					sourceFeature.addExtendedParameter(parameter);
				}
			}
			
			if(sourceFeature.getEncodingType() == EncodingType.QUOTED_PRINTABLE) {
				QuotedPrintableCodec q = new QuotedPrintableCodec();

				if(isCharsetForced()) {
					value = q.decode(value, forceCharset.name());
				}
				else { 
					if(sourceFeature.hasCharset()) {
						value = q.decode(value, sourceFeature.getCharset().name());
					}
					else {
						value = q.decode(value);
					}
				}
			}
			
			sourceFeature.setSource(VCardUtils.unescapeString(value));
			
			if(group != null) {
				sourceFeature.setGroup(group);
			}
			
			vcard.setSource(sourceFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("SourceType ("+VCardType.SOURCE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Parses the IMPP type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypeList
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseImppType(String group, String value, List<ParameterType> paramTypeList, VCardImpl vcard) throws VCardBuildException {
		try {
			IMPPType imppFeature = new IMPPType();

			for (ParameterType pt : paramTypeList) {					
				if(pt.getName().equals("ENCODING")) {
					if(pt.getValue().equals(EncodingType.EIGHT_BIT.getType())) {
						imppFeature.setEncodingType(EncodingType.EIGHT_BIT);
					}
					else {
						throw new VCardBuildException("IMPP's encoding must be 8bit.");
					}
				}
				else {
					if(pt.getName().equals("TYPE")) {
						if(pt.getValue().indexOf(',') != -1) {
							String[] typeValueList = pt.getValue().split(",");
							for(String typeValue : typeValueList) {
								setImppParameterType(imppFeature, typeValue);
							}
						}
						else {
							setImppParameterType(imppFeature, pt.getValue());
						}
					}
					else {
							ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
							imppFeature.addExtendedParameter(parameter);
					}
				}
			}
			
			imppFeature.setURI(new URI(value));
			
			if(group != null) {
				imppFeature.setGroup(group);
			}
			
			vcard.addIMPP(imppFeature);
		}
		catch(Exception ex) {
			throw new VCardBuildException("IMPPType ("+VCardType.IMPP.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Helper method for the above.</p>
	 *
	 * @param imppType
	 * @param paramValue
	 */
	private void setImppParameterType(IMPPType imppType, String paramValue) {
		try {
			String enumParamValue = paramValue.replace("-", "_").toUpperCase();
			IMPPParameterType imppParamType = IMPPParameterType.valueOf(enumParamValue);
			imppType.addIMPPParameterType(imppParamType);
		}
		catch(IllegalArgumentException iae) {
			XIMPPParameterType xImppType = null;
			if(paramValue.indexOf('=') != -1) {
				String[] pTmp = paramValue.split("=");
				xImppType = new XIMPPParameterType(pTmp[0], pTmp[1]);
			}
			else {
				xImppType = new XIMPPParameterType(paramValue);
			}
			
			imppType.addExtendedIMPPParameterType(xImppType);
		}
	}
	
	
	/**
	 * <p>Creates a VCardError object and sets the specified error information
	 * in it and adds it to the VCard currently being parses.</p>
	 *
	 * @see VCardError
	 * @see ErrorSeverity
	 * 
	 * @param vcard
	 * @param errorMessage
	 * @param exception
	 * @param severity
	 */
	private void handleError(VCardImpl vcard, String errorMessage, Throwable exception, ErrorSeverity severity) {
		VCardError vError = new VCardError();
		vError.setErrorMessage(errorMessage);
		vError.setSeverity(severity);
		
		if(exception != null) {
			vError.setError(exception);
		}
		
		vcard.addError(vError);
	}
	
	/**
	 * <p>Parses the raw parameter names and values and returns
	 * a list of {@link ParameterType} objects.</p>
	 *
	 * @param paramTypes
	 * @return {@link List}&lt;ParameterType&gt;
	 * @throws VCardBuildException
	 */
	private List<ParameterType> parseParamTypes(List<String[]> paramTypes) throws VCardBuildException
	{
		List<ParameterType> parameterTypes = new ArrayList<ParameterType>();
		for(String[] param : paramTypes) {
			String paramName = param[0];
			String paramValue = param[1].toUpperCase().trim();
			if(paramName != null) {
				paramName = paramName.toUpperCase().trim();
				parameterTypes.add(new ParameterType(paramName, paramValue));
 			}
 			else {
 					//When the parameter types are missing we try to guess what they are.
 					//We really should not as it breaks RFC rules but some apps do broken exports.
 					
					if(paramValue.equals(EncodingType.BASE64.getType())) {
						parameterTypes.add(new ParameterType("ENCODING", paramValue));
 					}
					else if(paramValue.equals(EncodingType.BINARY.getType())) {
						parameterTypes.add(new ParameterType("ENCODING", paramValue));
 					}
					else if(paramValue.equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						parameterTypes.add(new ParameterType("ENCODING", paramValue));
 					}
					else if(paramValue.equals("URI")) {
						parameterTypes.add(new ParameterType("VALUE", paramValue));
 					}
					else if(Charset.isSupported(paramValue)) {
 						//Hell, it could even be a charset
						parameterTypes.add(new ParameterType("CHARSET", paramValue));
 					}
 					else {
 						/*
						 * Type special notes: The type can include the type parameter "TYPE" to
						 * specify the graphic image format type. The TYPE parameter values MUST
						 * be one of the IANA registered image formats or a non-standard image format.
						 */
						
						parameterTypes.add(new ParameterType("TYPE", paramValue));
 					}
 			}
		}
		
		return parameterTypes;
	}
	
	/**
	 * <p>Returns the contents of the vcard file where each line is delimited with
	 * the standard java EOL char '\n' This is still a folded vcard String.</p>
	 * 
	 * @param vcardFile
	 * @return {@link String}
	 * @throws IOException
	 */
	private String getContentFromFile(File vcardFile) throws IOException
	{
		return getContent(new FileReader(vcardFile));
	}
	
	/**
	 * <p>Returns the contents of the vcard String where each line is delimited with
	 * the standard java EOL char '\n' This is still a folded vcard String.</p>
	 *
	 * @param vcardString
	 * @return {@link String}
	 * @throws IOException
	 */
	private String getContentFromString(String vcardString) throws IOException
	{
		return getContent(new StringReader(vcardString));
	}
	
	/**
	 * <p>Reads the contents of a Reader where each line is delimited with
	 * the standard java EOL char '\n' This is still a folded vcard String.</p>
	 *
	 * @param reader
	 * @return {@link String}
	 * @throws IOException
	 */
	private String getContent(Reader reader) throws IOException
	{
		BufferedReader br = null;
		try	{
			br = new BufferedReader(reader);
			String line;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine()) != null) {
				// skips blank lines
				if(!line.matches("$")) {
					sb.append(line);
					sb.append("\n");
				}
			}
			return sb.toString();
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}
}
