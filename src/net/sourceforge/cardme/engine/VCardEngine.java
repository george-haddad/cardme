package net.sourceforge.cardme.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

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
import net.sourceforge.cardme.vcard.types.parameters.LabelParameterType;
import net.sourceforge.cardme.vcard.types.parameters.TelephoneParameterType;
import net.sourceforge.cardme.vcard.types.parameters.URLParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XAddressParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XEmailParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XLabelParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XTelephoneParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XURLParameterType;

import org.apache.commons.codec.net.QuotedPrintableCodec;

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
 * May 6, 2009 - 11:29:02 AM
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
	 * <p>Parses the specified VCard String and returns a VCard java object
	 * with {@link VCardImpl}.setThrowExceptions() set to false. This method
	 * assumes the following:
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
		
		List<String[]> arrayLines = splitLines(vcardStr);
		String[] vLine = null;
		for (int i = 0; i < arrayLines.size(); i++) {
			vLine = arrayLines.get(i);
			String type = vLine[0].trim();	//VCard Type
			String value = vLine[1].trim();	//VCard Value
			String paramTypes = null;
			String group = null;
			
			if(type.indexOf('.') != -1) {
				group = type.substring(0, type.indexOf('.'));
				type = type.substring(type.indexOf('.')+1);
			}
			
			if (type.indexOf(';') != -1) {
				paramTypes = type.substring(type.indexOf(';')+1).trim().toUpperCase();
				type = type.substring(0, type.indexOf(';')).trim();
			}
			
			try {
				parseLine(group, type.toUpperCase(), paramTypes, value, vcard);
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

		return vcard;
	}
	
	private List<VCard> parseManyInOneVCard(String vcardStr)
	{
		List<VCard> vcards = new ArrayList<VCard>();
		List<String> enumCards = enumerateVCards(vcardStr);
		
		for (int i = 0; i < enumCards.size(); i++) {
			String card = enumCards.get(i);
			VCard vcard = parseVCard(card);
			vcards.add(vcard);
		}
		
		return vcards;
	}
	
	private List<String> enumerateVCards(String vcardString)
	{
		List<String> vcardStrings = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		String[] s = vcardString.split("\r?\n");
		boolean begin = false;
		boolean end = false;
		
		for (int i = 0; i < s.length; i++) {
			if(s[i].matches("\\p{Blank}*((BEGIN)|(begin))\\p{Blank}*:\\p{Blank}*((VCARD)|(vcard))\\p{Blank}*")) {
				begin = true;
			}
			
			if(s[i].matches("\\p{Blank}*((END)|(end))\\p{Blank}*:\\p{Blank}*((VCARD)|(vcard))\\p{Blank}*")) {
				end = true;
			}
			
			if(begin && !end) {
				sb.append(s[i]);
				sb.append('\n');
			}
			
			if(end) {
				sb.append(s[i]);
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
	 * @param group
	 * @param type
	 * @param paramTypes
	 * @param value
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseLine(String group, String type, String paramTypes, String value, VCardImpl vcard) throws VCardBuildException {
		
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseFnType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			FormattedNameType formattedNameFeature = new FormattedNameType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				formattedNameFeature.setFormattedName(VCardUtils.unescapeString(value));
			}
			else {
				formattedNameFeature.setFormattedName(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseNType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			NameType nameFeature = new NameType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
					if(VCardUtils.needsUnEscaping(names[0])) {
						nameFeature.setFamilyName(VCardUtils.unescapeString(names[0]));					
					}
					else {
						nameFeature.setFamilyName(names[0]);
					}
					
					break;
				}
				
				case 1:
				{
					if(VCardUtils.needsUnEscaping(names[1])) {
						nameFeature.setGivenName(VCardUtils.unescapeString(names[1]));
					}
					else {
						nameFeature.setGivenName(names[1]);
					}
					
					break;
				}
				
				case 2:
				{
					String[] addNames = names[2].split(",");
					for(int j = 0; j < addNames.length; j++) {
						if(VCardUtils.needsUnEscaping(addNames[j])) {
							nameFeature.addAdditionalName(VCardUtils.unescapeString(addNames[j]));
						}
						else {
							nameFeature.addAdditionalName(addNames[j]);
						}
					}
					
					break;
				}
				
				case 3:
				{
					String[] prefixes = names[3].split(",");
					for(int j = 0; j < prefixes.length; j++) {
						if(VCardUtils.needsUnEscaping(prefixes[j])) {
							nameFeature.addHonorificPrefix(VCardUtils.unescapeString(prefixes[j]));
						}
						else {
							nameFeature.addHonorificPrefix(prefixes[j]);
						}
					}
					
					break;
				}
				
				case 4:
				{
					String[] suffixes = names[4].split(",");
					for(int j = 0; j < suffixes.length; j++) {
						if(VCardUtils.needsUnEscaping(suffixes[j])) {
							nameFeature.addHonorificSuffix(VCardUtils.unescapeString(suffixes[j]));
						}
						else {
							nameFeature.addHonorificSuffix(suffixes[j]);
						}
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
		
		if(names[0] != null) {
			if(VCardUtils.needsUnEscaping(names[0])) {
				nameFeature.setFamilyName(VCardUtils.unescapeString(names[0]));					
			}
			else {
				nameFeature.setFamilyName(names[0]);
			}
		}
		
		if(names[1] != null) {
			if(VCardUtils.needsUnEscaping(names[1])) {
				nameFeature.setGivenName(VCardUtils.unescapeString(names[1]));
			}
			else {
				nameFeature.setGivenName(names[1]);
			}
		}
		
		if(names[2] != null) {
			String[] addNames = names[2].split(",");
			for(int i = 0; i < addNames.length; i++) {
				if(VCardUtils.needsUnEscaping(addNames[i])) {
					nameFeature.addAdditionalName(VCardUtils.unescapeString(addNames[i]));
				}
				else {
					nameFeature.addAdditionalName(addNames[i]);
				}
			}
		}
		
		if(names[3] != null) {
			String[] prefixes = names[3].split(",");
			for(int i = 0; i < prefixes.length; i++) {
				if(VCardUtils.needsUnEscaping(prefixes[i])) {
					nameFeature.addHonorificPrefix(VCardUtils.unescapeString(prefixes[i]));
				}
				else {
					nameFeature.addHonorificPrefix(prefixes[i]);
				}
			}
		}
		
		if(names[4] != null) {
			String[] suffixes = names[4].split(",");
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseNicknameType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			NicknameType nicknameFeature = new NicknameType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			String[] nicknames = value.split(",");
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parsePhotoType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			PhotoType photoFeature = new PhotoType();
			boolean isBinary = false;
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
						ImageMediaType mediaType = null;
						try {
							mediaType = ImageMediaType.valueOf(pt.getValue());
							photoFeature.setImageMediaType(mediaType);
						}
						catch(IllegalArgumentException iae) {
							mediaType = ImageMediaType.NON_STANDARD;
							mediaType.setTypeName(pt.getValue().trim());
							mediaType.setIanaRegisteredName(pt.getValue().trim());
							mediaType.setExtension(pt.getValue().trim());
						}
						finally {
							photoFeature.setImageMediaType(mediaType);
						}
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
				
				paramTypeList = null;
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseBDayType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			BirthdayType birthdayFeature = new BirthdayType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
			}
			
			if(birthdayFeature.getBirthdayParameterType() != null) {
				//Match specific formats according to parameter type.
				
				switch(birthdayFeature.getBirthdayParameterType())
				{
					case DATE:
					{
						if(value.matches(ISOUtils.ISO8601_DATE_EXTENDED_REGEX)) {
							//Example: 1996-04-15
							String[] date = value.split("-");
							Calendar cal = Calendar.getInstance();
							cal.clear();
							cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
							cal.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
							cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
							birthdayFeature.setBirthday(cal);
						}
						else if(value.matches(ISOUtils.ISO8601_DATE_BASIC_REGEX)) {
							//Example: 19960415
							String year = value.substring(0, 4);
							String month = value.substring(4,6);
							String day = value.substring(6);
							
							Calendar cal = Calendar.getInstance();
							cal.clear();
							cal.set(Calendar.YEAR, Integer.parseInt(year));
							cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
							cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
							birthdayFeature.setBirthday(cal);
						}
						else {
							throw new VCardBuildException("BirthdayType ("+VCardType.BDAY.getType()+") Birthday value is \"DATE\" but value is not in ISO-8601 date only format.");
						}
						
						break;
					}
					
					case DATE_TIME:
					{
						if(value.matches(ISOUtils.ISO8601_UTC_TIME_BASIC_REGEX)) {
							//Example: 19960415T231000Z
							String year = value.substring(0, 4);
							String month = value.substring(4, 6);
							String day = value.substring(6, 8);
							String hour = value.substring(9, 11);
							String minute = value.substring(11, 13);
							String seconds = value.substring(13, 15);
							
							Calendar cal = Calendar.getInstance();
							cal.clear();
							cal.set(Calendar.YEAR, Integer.parseInt(year));
							cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
							cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
							cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
							cal.set(Calendar.MINUTE, Integer.parseInt(minute));
							cal.set(Calendar.SECOND, Integer.parseInt(seconds));
							birthdayFeature.setBirthday(cal);
						}
						else if(value.matches(ISOUtils.ISO8601_UTC_TIME_EXTENDED_REGEX)) {
							//Example: 1996-04-15T23:10:00Z
							String[] split = value.toUpperCase().substring(0, value.indexOf('Z')).split("T");
							String[] date = split[0].split("-");
							String[] time = split[1].split(":");
							
							Calendar cal = Calendar.getInstance();
							cal.clear();
							cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
							cal.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
							cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
							cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
							cal.set(Calendar.MINUTE, Integer.parseInt(time[1]));
							cal.set(Calendar.SECOND, Integer.parseInt(time[2]));
							birthdayFeature.setBirthday(cal);
						}
						else if(value.matches(ISOUtils.ISO8601_TIME_EXTENDED_REGEX)) {
							//Example: 1996-04-15T23:10:00-06:00
							String[] split = value.toUpperCase().split("T");
							String[] date = split[0].split("-");
							String time = split[1];
							
							//23:10:00-06:00
							String hour = time.substring(0, 2);
							String minute = time.substring(3, 5);
							String seconds = time.substring(6, 8);
							String operator = time.substring(8, 9);
							String offsHour = time.substring(9, 11);
							String offsMinute = time.substring(12);
							
							
							Calendar cal = Calendar.getInstance();
							cal.clear();
							cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
							cal.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
							cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
							cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
							cal.set(Calendar.MINUTE, Integer.parseInt(minute));
							cal.set(Calendar.SECOND, Integer.parseInt(seconds));
							
							if(operator.compareTo("-") == 0) {
								offsHour = "-"+offsHour;
							}
							
							int offsetMillis = Integer.parseInt(offsHour) + (Integer.parseInt(offsMinute) / 10);
							offsetMillis = (((offsetMillis * 60) * 60) * 1000);
							
							cal.set(Calendar.ZONE_OFFSET, offsetMillis);
							birthdayFeature.setBirthday(cal);
						}
						else {
							throw new VCardBuildException("BirthdayType ("+VCardType.BDAY.getType()+") Birthday value is \"DATE-TIME\" but value is not in ISO-8601 date-time only format.");
						}
						
						break;
					}
				}
			}
			else {
				//Match all
				
				if(value.matches(ISOUtils.ISO8601_DATE_EXTENDED_REGEX)) {
					//Example: 1996-04-15
					String[] date = value.split("-");
					Calendar cal = Calendar.getInstance();
					cal.clear();
					cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
					cal.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
					cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
					birthdayFeature.setBirthday(cal);
				}
				else if(value.matches(ISOUtils.ISO8601_DATE_BASIC_REGEX)) {
					//Example: 19960415
					String year = value.substring(0, 4);
					String month = value.substring(4,6);
					String day = value.substring(6);
					
					Calendar cal = Calendar.getInstance();
					cal.clear();
					cal.set(Calendar.YEAR, Integer.parseInt(year));
					cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
					cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
					birthdayFeature.setBirthday(cal);
				}
				else if(value.matches(ISOUtils.ISO8601_UTC_TIME_BASIC_REGEX)) {
					//Example: 19960415T231000Z
					String year = value.substring(0, 4);
					String month = value.substring(4, 6);
					String day = value.substring(6, 8);
					String hour = value.substring(9, 11);
					String minute = value.substring(11, 13);
					String seconds = value.substring(13, 15);
					
					Calendar cal = Calendar.getInstance();
					cal.clear();
					cal.set(Calendar.YEAR, Integer.parseInt(year));
					cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
					cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
					cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
					cal.set(Calendar.MINUTE, Integer.parseInt(minute));
					cal.set(Calendar.SECOND, Integer.parseInt(seconds));
					birthdayFeature.setBirthday(cal);
				}
				else if(value.matches(ISOUtils.ISO8601_UTC_TIME_EXTENDED_REGEX)) {
					//Example: 1996-04-15T23:10:00Z
					String[] split = value.toUpperCase().substring(0, value.indexOf('Z')).split("T");
					String[] date = split[0].split("-");
					String[] time = split[1].split(":");
					
					Calendar cal = Calendar.getInstance();
					cal.clear();
					cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
					cal.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
					cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
					cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
					cal.set(Calendar.MINUTE, Integer.parseInt(time[1]));
					cal.set(Calendar.SECOND, Integer.parseInt(time[2]));
					birthdayFeature.setBirthday(cal);
				}
				else if(value.matches(ISOUtils.ISO8601_TIME_EXTENDED_REGEX)) {
					//Example: 1996-04-15T23:10:00-06:00
					String[] split = value.toUpperCase().split("T");
					String[] date = split[0].split("-");
					String time = split[1];
					
					//23:10:00-06:00
					String hour = time.substring(0, 2);
					String minute = time.substring(3, 5);
					String seconds = time.substring(6, 8);
					String operator = time.substring(8, 9);
					String offsHour = time.substring(9, 11);
					String offsMinute = time.substring(12);
					
					
					Calendar cal = Calendar.getInstance();
					cal.clear();
					cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
					cal.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
					cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
					cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
					cal.set(Calendar.MINUTE, Integer.parseInt(minute));
					cal.set(Calendar.SECOND, Integer.parseInt(seconds));
					
					if(operator.compareTo("-") == 0) {
						offsHour = "-"+offsHour;
					}
					
					int offsetMillis = Integer.parseInt(offsHour) + (Integer.parseInt(offsMinute) / 10);
					offsetMillis = (((offsetMillis * 60) * 60) * 1000);
					
					cal.set(Calendar.ZONE_OFFSET, offsetMillis);
					birthdayFeature.setBirthday(cal);
				}
				else {
					throw new VCardBuildException("BirthdayType ("+VCardType.BDAY.getType()+") Birthday value is not a valid ISO-8601 text.");
				}
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseAdrType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			AddressType addressFeature = new AddressType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
							for(int j = 0; j < typeValueList.length; j++) {
								setAdrParameterType(addressFeature, typeValueList[j]);
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
				
				paramTypeList = null;
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
			
			String[] address = value.split(";",7);
			String postOfficeBox = address[0];
			String extendedAddress = address[1];
			String streetAddress = address[2];
			String locality = address[3];
			String region = address[4];
			String postalCode = address[5];
			String countryName = address[6];
			
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
			AddressParameterType adrParamType = AddressParameterType.valueOf(paramValue);
			adrType.addAddressParameterType(adrParamType);
		}
		catch(IllegalArgumentException iae) {
			XAddressParameterType xAdrType = null;
			if(paramValue.indexOf('=') != -1) {
				String[] pTmp = paramValue.split("=");
				xAdrType = new XAddressParameterType(pTmp[0], pTmp[1]);
				pTmp[0] = null;
				pTmp[1] = null;
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseLabelType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			LabelType labelFeature = new LabelType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
							for(int j = 0; j < typeValueList.length; j++) {
								setLabelParameterType(labelFeature, typeValueList[j]);
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				labelFeature.setLabel(VCardUtils.unescapeString(value));
			}
			else {
				labelFeature.setLabel(value);
			}
			
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
					for(int i = 0; i < aPrmList.size(); i++) {
						if(aPrmList.get(i).getType().equals(labelParamType.getType())) {
							paramsMatched++;
						}
					}
				}
				
				//See how many extended address parameter types match each extended label parameter type
				int xparamsMatched = 0;
				while(lXPrmIter.hasNext()) {
					XLabelParameterType xlabelParamType = lXPrmIter.next();
					for(int i = 0; i < aXPrmList.size(); i++) {
						if(aXPrmList.get(i).getType().equals(xlabelParamType.getType())) {
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
			LabelParameterType adrParamType = LabelParameterType.valueOf(paramValue);
			labelType.addLabelParameterType(adrParamType);
		}
		catch(IllegalArgumentException iae) {
			XLabelParameterType xLabelType = null;
			if(paramValue.indexOf('=') != -1) {
				String[] pTmp = paramValue.split("=");
				xLabelType = new XLabelParameterType(pTmp[0], pTmp[1]);
				pTmp[0] = null;
				pTmp[1] = null;
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseTelType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			TelephoneType telephoneFeature = new TelephoneType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
							for(int j = 0; j < typeValueList.length; j++) {
								setTelParameterType(telephoneFeature, typeValueList[j]);
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				telephoneFeature.setTelephone(VCardUtils.unescapeString(value));
			}
			else {
				telephoneFeature.setTelephone(value);
			}
			
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
			TelephoneParameterType telParamType = TelephoneParameterType.valueOf(paramValue);
			telType.addTelephoneParameterType(telParamType);
		}
		catch(IllegalArgumentException iae) {
			XTelephoneParameterType xTelType = null;
			if(paramValue.indexOf('=') != -1) {
				String[] pTmp = paramValue.split("=");
				xTelType = new XTelephoneParameterType(pTmp[0], pTmp[1]);
				pTmp[0] = null;
				pTmp[1] = null;
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseEmailType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			EmailType emailFeature = new EmailType();
			boolean isBinary = false;
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
					if(pt.getName().equals("CHARSET")) {
						emailFeature.setCharset(pt.getValue());
					}
					else if(pt.getName().equals("TYPE")) {
						if(pt.getValue().indexOf(',') != -1) {
							String[] typeValueList = pt.getValue().split(",");
							for(int j = 0; j < typeValueList.length; j++) {
								setEmailParameterType(emailFeature, typeValueList[j]);
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
				
				paramTypeList = null;
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
			EmailParameterType emailParamType = EmailParameterType.valueOf(paramValue);
			emailType.addEmailParameterType(emailParamType);
		}
		catch(IllegalArgumentException iae) {
			XEmailParameterType xEmailType = null;
			if(paramValue.indexOf('=') != -1) {
				String[] pTmp = paramValue.split("=");
				xEmailType = new XEmailParameterType(pTmp[0], pTmp[1]);
				pTmp[0] = null;
				pTmp[1] = null;
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseMailerType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			MailerType mailerFeature = new MailerType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				mailerFeature.setMailer(VCardUtils.unescapeString(value));
			}
			else {
				mailerFeature.setMailer(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseTzType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			TimeZoneType timeZoneFeature = new TimeZoneType();
			if(paramTypes != null) {
				//VALUE=TEXT
				//-05:00; EST; Raleigh/North America
				String paramValue = paramTypes.substring(paramTypes.indexOf('=')+1);
				if(paramValue.compareToIgnoreCase("TEXT") == 0) {
					timeZoneFeature.setTextValue(value);
				}
				else {
					setTzType(timeZoneFeature, value);
				}
			}
			else {
				setTzType(timeZoneFeature, value);
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
	 * <p>Helper method for the above.</p>
	 *
	 * @param timeZoneType
	 * @param value
	 * @throws VCardBuildException
	 */
	private void setTzType(TimeZoneType timeZoneType, String value) throws VCardBuildException {
		if(value.matches(ISOUtils.ISO8601_TIMEZONE_BASIC_REGEX)) {
			//-500 or -0500
			if(value.startsWith("-")) {
				String hour = null;
				String minute = null;
				if(value.length() == 4) {
					hour = value.substring(0, 2);
					minute = value.substring(2);
				}
				else if(value.length() == 5) {
					hour = value.substring(0, 3);
					minute = value.substring(3);
				}
				else {
					throw new VCardBuildException("TimeZoneType ("+VCardType.TZ.getType()+") Timezone value is not a valid ISO-8601 text.");
				}
					
				int offsetMillis = Integer.parseInt(hour) + (Integer.parseInt(minute) / 10);
				offsetMillis = (((offsetMillis * 60) * 60) * 1000);
				
				TimeZone tz = TimeZone.getDefault();
				tz.setRawOffset(offsetMillis);
				timeZoneType.setTimeZone(tz);
				
			}
			else {
				//500 or 0500
				String hour = null;
				String minute = null;
				if(value.length() == 3) {
					hour = value.substring(0, 1);
					minute = value.substring(1);
				}
				else if(value.length() == 4) {
					hour = value.substring(0, 2);
					minute = value.substring(2);
				}
				else {
					throw new VCardBuildException("TimeZoneType ("+VCardType.TZ.getType()+") Timezone value is not a valid ISO-8601 text.");
				}
				
				int offsetMillis = Integer.parseInt(hour) + (Integer.parseInt(minute) / 10);
				offsetMillis = (((offsetMillis * 60) * 60) * 1000);
				
				TimeZone tz = TimeZone.getDefault();
				tz.setRawOffset(offsetMillis);
				timeZoneType.setTimeZone(tz);
			}
		}
		else if(value.matches(ISOUtils.ISO8601_TIMEZONE_EXTENDED_REGEX)) {
			//-5:00 or -05:00 or 5:00 or 05:00
			String[] split = value.split(":");
			String hour = split[0];
			String minute = split[1];
			
			int offsetMillis = Integer.parseInt(hour) + (Integer.parseInt(minute) / 10);
			offsetMillis = (((offsetMillis * 60) * 60) * 1000);
			
			TimeZone tz = TimeZone.getDefault();
			tz.setRawOffset(offsetMillis);
			timeZoneType.setTimeZone(tz);
		}
		else {
			throw new VCardBuildException("TimeZoneType ("+VCardType.TZ.getType()+") Timezone value is not a valid ISO-8601 text.");
		}
	}
	
	/**
	 * <p>Parses the GEO type.</p>
	 *
	 * @param group
	 * @param value
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseGeoType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			GeographicPositionType geographicPositionFeature = new GeographicPositionType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
					if(pt.getName().equals("CHARSET")) {
						geographicPositionFeature.setCharset(pt.getValue());
					}
					else {
						ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
						geographicPositionFeature.addExtendedParameter(parameter);
					}
				}
				
				paramTypeList = null;
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseTitleType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			TitleType titleFeature = new TitleType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				titleFeature.setTitle(VCardUtils.unescapeString(value));
			}
			else {
				titleFeature.setTitle(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseRoleType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			RoleType roleFeature = new RoleType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				roleFeature.setRole(VCardUtils.unescapeString(value));
			}
			else {
				roleFeature.setRole(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseLogoType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			LogoType logoFeature = new LogoType();
			boolean isBinary = false;
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
						ImageMediaType mediaType = null;
						try {
							mediaType = ImageMediaType.valueOf(pt.getValue());
							logoFeature.setImageMediaType(mediaType);
						}
						catch(IllegalArgumentException iae) {
							mediaType = ImageMediaType.NON_STANDARD;
							mediaType.setTypeName(pt.getValue().trim());
							mediaType.setIanaRegisteredName(pt.getValue().trim());
							mediaType.setExtension(pt.getValue().trim());
						}
						finally {
							logoFeature.setImageMediaType(mediaType);
						}
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
				
				paramTypeList = null;
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseOrgType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			OrganizationType organizationFeature = new OrganizationType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			/*
			 * If escaped semi-colons exist in the list then replace them
			 * with this temporary sequence. Then after splitting put it
			 * back in.
			 */
			if(value.contains("\\;")) {
				value = value.replaceAll("\\\\;", "!SEMI!");
			}
			
			String[] orgs = value.split(";");
			for(int i = 0; i < orgs.length; i++) {
				if(VCardUtils.needsUnEscaping(orgs[i])) {
					String unesc = VCardUtils.unescapeString(orgs[i]);
					organizationFeature.addOrganization(unesc.replaceAll("\\!SEMI\\!", ";"));
				}
				else {
					organizationFeature.addOrganization(orgs[i].replaceAll("\\!SEMI\\!", ";"));
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseCategoriesType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			CategoriesType categoriesFeature = new CategoriesType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
					if(VCardUtils.needsUnEscaping(value)) {
						categories = VCardUtils.unescapeString(value).split(",");
					}
					else {
						categories = value.split(",");
					}
					break;
				}
				
				default:
				{
					if(value.contains("\\,")) {
						value = value.replaceAll("\\\\,", " ");
					}
					
					categories = value.split(",");
					break;
				}
			}
			
			for(int i = 0; i < categories.length; i++) {
				if(VCardUtils.needsUnEscaping(categories[i])) {
					categoriesFeature.addCategory(VCardUtils.unescapeString(categories[i]));
				}
				else {
					categoriesFeature.addCategory(categories[i]);
				}
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseNoteType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			NoteType noteFeature = new NoteType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				noteFeature.setNote(VCardUtils.unescapeString(value));
			}
			else {
				noteFeature.setNote(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseProdidType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			ProductIdType productIdFeature = new ProductIdType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				productIdFeature.setProductId(VCardUtils.unescapeString(value));
			}
			else {
				productIdFeature.setProductId(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseRevType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			RevisionType revisionFeature = new RevisionType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
					if(pt.getName().equals("CHARSET")) {
						revisionFeature.setCharset(pt.getValue());
					}
					else {
						ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
						revisionFeature.addExtendedParameter(parameter);
					}
				}
				
				paramTypeList = null;
			}
			
			if(value.matches(ISOUtils.ISO8601_DATE_EXTENDED_REGEX)) {
				//Example: 1996-04-15
				String[] date = value.split("-");
				Calendar cal = Calendar.getInstance();
				cal.clear();
				cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
				cal.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
				revisionFeature.setRevision(cal);
			}
			else if(value.matches(ISOUtils.ISO8601_DATE_BASIC_REGEX)) {
				//Example: 19960415
				String year = value.substring(0, 4);
				String month = value.substring(4,6);
				String day = value.substring(6);
				
				Calendar cal = Calendar.getInstance();
				cal.clear();
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
				revisionFeature.setRevision(cal);
			}
			else if(value.matches(ISOUtils.ISO8601_UTC_TIME_BASIC_REGEX)) {
				//Example: 19960415T231000Z
				String year = value.substring(0, 4);
				String month = value.substring(4, 6);
				String day = value.substring(6, 8);
				String hour = value.substring(9, 11);
				String minute = value.substring(11, 13);
				String seconds = value.substring(13, 15);
				
				Calendar cal = Calendar.getInstance();
				cal.clear();
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
				cal.set(Calendar.MINUTE, Integer.parseInt(minute));
				cal.set(Calendar.SECOND, Integer.parseInt(seconds));
				revisionFeature.setRevision(cal);
			}
			else if(value.matches(ISOUtils.ISO8601_UTC_TIME_EXTENDED_REGEX)) {
				//Example: 1996-04-15T23:10:00Z
				String[] split = value.toUpperCase().substring(0, value.indexOf('Z')).split("T");
				String[] date = split[0].split("-");
				String[] time = split[1].split(":");
				
				Calendar cal = Calendar.getInstance();
				cal.clear();
				cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
				cal.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
				cal.set(Calendar.MINUTE, Integer.parseInt(time[1]));
				cal.set(Calendar.SECOND, Integer.parseInt(time[2]));
				revisionFeature.setRevision(cal);
			}
			else if(value.matches(ISOUtils.ISO8601_TIME_EXTENDED_REGEX)) {
				//Example: 1996-04-15T23:10:00-06:00
				String[] split = value.toUpperCase().split("T");
				String[] date = split[0].split("-");
				String time = split[1];
				
				//23:10:00-06:00
				String hour = time.substring(0, 2);
				String minute = time.substring(3, 5);
				String seconds = time.substring(6, 8);
				String operator = time.substring(8, 9);
				String offsHour = time.substring(9, 11);
				String offsMinute = time.substring(12);
				
				
				Calendar cal = Calendar.getInstance();
				cal.clear();
				cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
				cal.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
				cal.set(Calendar.MINUTE, Integer.parseInt(minute));
				cal.set(Calendar.SECOND, Integer.parseInt(seconds));
				
				if(operator.compareTo("-") == 0) {
					offsHour = "-"+offsHour;
				}
				
				int offsetMillis = Integer.parseInt(offsHour) + (Integer.parseInt(offsMinute) / 10);
				offsetMillis = (((offsetMillis * 60) * 60) * 1000);
				
				cal.set(Calendar.ZONE_OFFSET, offsetMillis);
				revisionFeature.setRevision(cal);
			}
			else {
				throw new VCardBuildException("RevisionType ("+VCardType.REV.getType()+") Revision value is not a valid ISO-8601 text.");
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseSortStringType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			SortStringType sortStringFeature = new SortStringType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				sortStringFeature.setSortString(VCardUtils.unescapeString(value));
			}
			else {
				sortStringFeature.setSortString(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseSoundType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			SoundType soundFeature = new SoundType();
			boolean isBinary = false;
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
						AudioMediaType mediaType = null;
						try {
							mediaType = AudioMediaType.valueOf(pt.getValue().trim());
							soundFeature.setAudioMediaType(mediaType);
						}
						catch(IllegalArgumentException iae) {
							mediaType = AudioMediaType.NON_STANDARD;
							mediaType.setTypeName(pt.getValue().trim());
							mediaType.setIanaRegisteredName(pt.getValue().trim());
							mediaType.setExtension(pt.getValue().trim());
						}
						finally {
							soundFeature.setAudioMediaType(mediaType);
						}
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
				
				paramTypeList = null;
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseUidType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			UIDType uidFeature = new UIDType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				uidFeature.setUID(VCardUtils.unescapeString(value));
			}
			else {
				uidFeature.setUID(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseUrlType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			URLType urlFeature = new URLType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
										for(int j = 0; j < typeValueList.length; j++) {
											setUrlParameterType(urlFeature, typeValueList[j]);
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				urlFeature.setURL(new URL(VCardUtils.unescapeString(value)));
			}
			else {
				urlFeature.setURL(new URL(value));
			}
			
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
			URLParameterType urlParamType = URLParameterType.valueOf(paramValue);
			urlType.addURLParameterType(urlParamType);
		}
		catch(IllegalArgumentException iae) {
			XURLParameterType xUrlType = null;
			if(paramValue.indexOf('=') != -1) {
				String[] pTmp = paramValue.split("=");
				xUrlType = new XURLParameterType(pTmp[0], pTmp[1]);
				pTmp[0] = null;
				pTmp[1] = null;
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseClassType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			ClassType classFeature = new ClassType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				classFeature.setSecurityClass(VCardUtils.unescapeString(value));
			}
			else {
				classFeature.setSecurityClass(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseKeyType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			KeyType keyFeature = new KeyType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
						KeyTextType keyTextType = null;
						try {
							keyTextType = KeyTextType.valueOf(pt.getValue().trim());
							keyFeature.setKeyTextType(keyTextType);
						}
						catch(IllegalArgumentException iae) {
							keyTextType = KeyTextType.NON_STANDARD;
							keyTextType.setTypeName(pt.getValue().trim());
							keyTextType.setIanaRegisteredName(pt.getValue().trim());
							keyTextType.setExtension(pt.getValue().trim());
						}
						finally {
							keyFeature.setKeyTextType(keyTextType);
						}
					}
					else {
						ExtendedParameterType parameter = new ExtendedParameterType(pt.getName(), pt.getValue());                                               
						keyFeature.addExtendedParameter(parameter);
					}
				}
				
				paramTypeList = null;
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
	 * @param paramTypes
	 * @param typeName
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseXtendedType(String group, String value, String typeName, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			ExtendedType extendedFeature = new ExtendedType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				extendedFeature.setExtensionName(typeName);
				extendedFeature.setExtensionData(VCardUtils.unescapeString(value));
			}
			else {
				extendedFeature.setExtensionName(typeName);
				extendedFeature.setExtensionData(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseDisplayableNameType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			DisplayableNameType displayableNameFeature = new DisplayableNameType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				displayableNameFeature.setName(VCardUtils.unescapeString(value));
			}
			else {
				displayableNameFeature.setName(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseProfileType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			ProfileType profileFeature = new ProfileType();
			
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				profileFeature.setProfile(VCardUtils.unescapeString(value));
			}
			else {
				profileFeature.setProfile(value);
			}
			
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
	 * @param paramTypes
	 * @param vcard
	 * @throws VCardBuildException
	 */
	private void parseSourceType(String group, String value, String paramTypes, VCardImpl vcard) throws VCardBuildException {
		try {
			SourceType sourceFeature = new SourceType();
			
			if(paramTypes != null) {
				List<ParameterType> paramTypeList = parseParamTypes(paramTypes);
				for (int i = 0; i < paramTypeList.size(); i++) {
					ParameterType pt = paramTypeList.get(i);
					
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
				
				paramTypeList = null;
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
			
			if(VCardUtils.needsUnEscaping(value)) {
				sourceFeature.setSource(VCardUtils.unescapeString(value));
			}
			else {
				sourceFeature.setSource(value);
			}
			
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
	 * <p>Parses the specified string of parameter types and returns
	 * a list of <code>ParamterType</code> objects. Parameter types
	 * are expected to be delimited by a semi-colon.</p>
	 *
	 * @param paramTypes
	 * @return {@link List}&lt;ParameterType&gt;
	 * @throws VCardBuildException
	 */
	private List<ParameterType> parseParamTypes(String paramTypes) throws VCardBuildException
	{
		List<ParameterType> parameterTypes = new ArrayList<ParameterType>();
		String[] params = paramTypes.split(";");
		for(int i = 0; i < params.length; i++) {
			if(params[i].contains("=")) {
				String[] paramType = params[i].trim().split("=");
				parameterTypes.add(new ParameterType(paramType[0], paramType[1]));
			}
			else {
					//When the parameter types are missing we try to guess what they are.
					//We really should not as it breaks RFC rules but some apps do broken exports.
					
					if(params[i].trim().equals(EncodingType.BASE64.getType())) {
						parameterTypes.add(new ParameterType("ENCODING", params[i].trim()));
					}
					else if(params[i].trim().equals(EncodingType.BINARY.getType())) {
						parameterTypes.add(new ParameterType("ENCODING", params[i].trim()));
					}
					else if(params[i].trim().equals(EncodingType.QUOTED_PRINTABLE.getType())) {
						parameterTypes.add(new ParameterType("ENCODING", params[i].trim()));
					}
					else if(params[i].trim().equals("URI")) {
						parameterTypes.add(new ParameterType("VALUE", params[i].trim()));
					}
					else if(Charset.isSupported(params[i].trim())) {
						//Hell, it could even be a charset
						parameterTypes.add(new ParameterType("CHARSET", params[i].trim()));
					}
					else {
						
						/*
						 * Type special notes: The type can include the type parameter "TYPE" to
						 * specify the graphic image format type. The TYPE parameter values MUST
						 * be one of the IANA registered image formats or a non-standard image format.
						 */
						
						parameterTypes.add(new ParameterType("TYPE", params[i].trim()));
					}
			}
		}
		
		return parameterTypes;
	}
	
	/**
	 * <p>Splits each line of the vcard into an array of 2 cells.
	 * The first cell contains the VCard type (may or may not include
	 * parameter types.) The second cell contains the Type values (may
	 * or may not contains comma or semicolon delimited lists.)</p>
	 * 
	 * @param vcardString
	 * @return List&lt;String[]&gt;
	 */
	private List<String[]> splitLines(String vcardString)
	{
		String[] strArray = vcardString.split("\n");
		List<String[]> arrayLines = new ArrayList<String[]>(strArray.length);
		String line = null;

		for (int i = 0; i < strArray.length; i++) {
			line = strArray[i];
			String[] subLine = line.split(":", 2);
			arrayLines.add(subLine);
		}

		return arrayLines;
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
		BufferedReader br = new BufferedReader(new FileReader(vcardFile));
		String line = "";
		StringBuilder sb = new StringBuilder();

		while((line = br.readLine()) != null) {
			// skips blank lines
			if(!line.matches("$")) {
				sb.append(line);
				sb.append("\n");
			}
		}

		br.close();
		return sb.toString();
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
		BufferedReader br = new BufferedReader(new StringReader(vcardString));
		String line = "";
		StringBuilder sb = new StringBuilder();

		while((line = br.readLine()) != null) {
			// skips blank lines
			if(!line.matches("$")) {
				sb.append(line);
				sb.append("\n");
			}
		}

		br.close();
		return sb.toString();
	}
}
