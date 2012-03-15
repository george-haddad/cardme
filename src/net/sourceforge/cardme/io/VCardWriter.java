package net.sourceforge.cardme.io;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;

import net.sourceforge.cardme.util.Base64Wrapper;
import net.sourceforge.cardme.util.ISOFormat;
import net.sourceforge.cardme.util.ISOUtils;
import net.sourceforge.cardme.util.VCardUtils;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.VCardVersion;
import net.sourceforge.cardme.vcard.errors.ErrorSeverity;
import net.sourceforge.cardme.vcard.errors.VCardBuildException;
import net.sourceforge.cardme.vcard.errors.VCardError;
import net.sourceforge.cardme.vcard.errors.VCardErrorHandling;
import net.sourceforge.cardme.vcard.errors.VCardException;
import net.sourceforge.cardme.vcard.features.AddressFeature;
import net.sourceforge.cardme.vcard.features.AgentFeature;
import net.sourceforge.cardme.vcard.features.BeginFeature;
import net.sourceforge.cardme.vcard.features.BirthdayFeature;
import net.sourceforge.cardme.vcard.features.CategoriesFeature;
import net.sourceforge.cardme.vcard.features.ClassFeature;
import net.sourceforge.cardme.vcard.features.DisplayableNameFeature;
import net.sourceforge.cardme.vcard.features.EmailFeature;
import net.sourceforge.cardme.vcard.features.EndFeature;
import net.sourceforge.cardme.vcard.features.ExtendedFeature;
import net.sourceforge.cardme.vcard.features.FormattedNameFeature;
import net.sourceforge.cardme.vcard.features.GeographicPositionFeature;
import net.sourceforge.cardme.vcard.features.KeyFeature;
import net.sourceforge.cardme.vcard.features.LabelFeature;
import net.sourceforge.cardme.vcard.features.LogoFeature;
import net.sourceforge.cardme.vcard.features.MailerFeature;
import net.sourceforge.cardme.vcard.features.NameFeature;
import net.sourceforge.cardme.vcard.features.NicknameFeature;
import net.sourceforge.cardme.vcard.features.NoteFeature;
import net.sourceforge.cardme.vcard.features.OrganizationFeature;
import net.sourceforge.cardme.vcard.features.PhotoFeature;
import net.sourceforge.cardme.vcard.features.ProductIdFeature;
import net.sourceforge.cardme.vcard.features.ProfileFeature;
import net.sourceforge.cardme.vcard.features.RevisionFeature;
import net.sourceforge.cardme.vcard.features.RoleFeature;
import net.sourceforge.cardme.vcard.features.SortStringFeature;
import net.sourceforge.cardme.vcard.features.SoundFeature;
import net.sourceforge.cardme.vcard.features.SourceFeature;
import net.sourceforge.cardme.vcard.features.TelephoneFeature;
import net.sourceforge.cardme.vcard.features.TimeZoneFeature;
import net.sourceforge.cardme.vcard.features.TitleFeature;
import net.sourceforge.cardme.vcard.features.UIDFeature;
import net.sourceforge.cardme.vcard.features.URLFeature;
import net.sourceforge.cardme.vcard.features.VersionFeature;
import net.sourceforge.cardme.vcard.types.parameters.AddressParameterType;
import net.sourceforge.cardme.vcard.types.parameters.BirthdayParameterType;
import net.sourceforge.cardme.vcard.types.parameters.EmailParameterType;
import net.sourceforge.cardme.vcard.types.parameters.LabelParameterType;
import net.sourceforge.cardme.vcard.types.parameters.TelephoneParameterType;
import net.sourceforge.cardme.vcard.types.parameters.TimeZoneParameterType;
import net.sourceforge.cardme.vcard.types.parameters.URLParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XAddressParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XEmailParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XLabelParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XTelephoneParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XURLParameterType;

import org.apache.commons.codec.EncoderException;
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
 * Jun 23, 2006
 * <br/>
 * <br/>
 * 
 * @author Wolfgang Fahl
 * <br/>
 * Dec 01, 2009
 * 
 */
public class VCardWriter {
	
	/**
	 * <p>The VCard we set so that we can output it
	 * into a textual .VCF formatted string.</p>
	 */
	private VCard vcard = null;
	
	/**
	 * <p>The output version of the vcard. This is
	 * where we specify which version VCard we want
	 * to output.</p>
	 */
	private VCardVersion outputVersion = null;
	
	/**
	 * <p>Selects which line/data folding scheme should
	 * be used. The default is to use the standard RFC-2426
	 * folding scheme of 75 max characters per line. Some
	 * applications use less ... or more.</p>
	 */
	private FoldingScheme foldingScheme = null;
	
	/**
	 * <p>Selects which data folding scheme should
	 * be used. The default is to use the standard RFC-2426
	 * folding scheme of 75 max characters per line. Some
	 * applications use less ... or more.</p>
	 */
	private BinaryFoldingScheme binaryFoldingScheme = null;
	
	/**
	 * <p>Selects from a list of application compatibility modes
	 * to use when formatting the output of the vcard. Some applications
	 * expect a certain type of formatting or non-standard types.</p>
	 */
	private CompatibilityMode compatMode = null;
	
	/**
	 * <p>If set true then any exception that occurs will be thrown
	 * when using this class. The alternative is to store each exception
	 * inside the VCard object. These errors can be retrieved after
	 * the writing process.</p>
	 */
	private boolean isThrowsExceptions = true;
	
	/**
	 * <p>Quoted Printable codec provided by apache commons-codec.
	 * This will created and used only where there is at least one
	 * type that needs it.</p>
	 */
	private QuotedPrintableCodec qpCodec = null;
	
	/**
	 * <p>The end of line character to use. By default and by
	 * RFC and MIME-DIR standards the EOL character must be
	 * CR+LF (\r\n) but we can customize it by setting this
	 * variable.</p>
	 */
	private String eol = VCardUtils.CRLF;
	
	/**
	 * <p>Creates a new VCardWriter with default parameters.</p>
	 * 
	 * @throws VCardException
	 */
	public VCardWriter() throws VCardException {
		this(null, null, null, null);
	}
	
	/**
	 * <p>Creates a new VCardWriter with a specified version.</p>
	 * 
	 * @see VCardVersion
	 * 
	 * @param outputVersion
	 * @throws VCardException
	 */
	public VCardWriter(VCardVersion outputVersion) throws VCardException {
		this(outputVersion, null, null, null);
	}
	
	/**
	 * <p>Creates a new VCardWriter with a specified version and folding scheme.</p>
	 * 
	 * @see VCardVersion
	 * @see FoldingScheme
	 * @see BinaryFoldingScheme
	 *  
	 * @param outputVersion
	 * @param foldingScheme
	 * @param binaryFoldingScheme
	 * @throws VCardException
	 */
	public VCardWriter(VCardVersion outputVersion, FoldingScheme foldingScheme, BinaryFoldingScheme binaryFoldingScheme) throws VCardException {
		this(outputVersion, foldingScheme, binaryFoldingScheme, null);
	}
	
	/**
	 * <p>Creates a new VCardWriter with a specified version and compatibility mode.</p>
	 * 
	 * @see VCardVersion
	 * @see CompatibilityMode
	 * 
	 * @param outputVersion
	 * @param compatMode
	 * @throws VCardException
	 */
	public VCardWriter(VCardVersion outputVersion, CompatibilityMode compatMode) throws VCardException {
		this(outputVersion, null, null, compatMode);
	}
	
	/**
	 * <p>Creates a new VCardWriter with a specified version, folding scheme and compatibility mode.</p>
	 * 
	 * @see VCardVersion
	 * @see FoldingScheme
	 * @see BinaryFoldingScheme
	 * @see CompatibilityMode
	 * 
	 * @param outputVersion
	 * @param foldingScheme
	 * @param binaryFoldingScheme
	 * @param compatMode
	 * @throws VCardException
	 */
	public VCardWriter(VCardVersion outputVersion, FoldingScheme foldingScheme, BinaryFoldingScheme binaryFoldingScheme, CompatibilityMode compatMode) throws VCardException {
		setOutputVersion(outputVersion);
		setFoldingScheme(foldingScheme);
		setBinaryfoldingScheme(binaryFoldingScheme);
		setCompatibilityMode(compatMode);
	}
	
	/**
	 * <p>Returns the VCard that was set.
	 * Null if no vcard was set.</p>
	 *
	 * @return {@link VCard}
	 */
	public VCard getVCard()
	{
		return vcard;
	}
	
	/**
	 * <p>Sets the vcard to be outputted.</p>
	 *
	 * @param vcard
	 * @throws VCardException
	 */
	public void setVCard(VCard vcard) throws VCardException {
		if(vcard == null) {
			throw new VCardException("Cannot set a null vcard.");
		}
		
		this.vcard = vcard;
		
		if(vcard instanceof VCardErrorHandling) {
			isThrowsExceptions = ((VCardErrorHandling)vcard).isThrowExceptions();
		}
	}
	
	/**
	 * <p>Sets the VCard format to output to.
	 * If null {@link VCardVersion}.V3_0 is used.</p>
	 *
	 * @param outputVersion
	 */
	public void setOutputVersion(VCardVersion outputVersion) throws VCardException {
		if(outputVersion == null) {
			this.outputVersion = VCardVersion.V3_0;
		}
		else {
			if(outputVersion == VCardVersion.V2_1 || outputVersion == VCardVersion.V4_0) {
				throw new VCardException("Version "+outputVersion+" not supported.");
			}
			
			this.outputVersion = outputVersion;
		}
	}
	
	/**
	 * <p>Sets the folding scheme to use when folding long lines.
	 * If null {@link FoldingScheme}.MIME_DIR is used.</p>
	 *
	 * @param foldingScheme
	 */
	public void setFoldingScheme(FoldingScheme foldingScheme) {
		if(foldingScheme == null) {
			this.foldingScheme = FoldingScheme.MIME_DIR;
		}
		else {
			this.foldingScheme = foldingScheme;
		}
	}
	
	/**
	 * <p>Sets the binary folding scheme to use when folding long
	 * lines of data. If null {@link BinaryFoldingScheme}.MIME_DIR is used.</p>
	 *
	 * @param binaryFoldingScheme
	 */
	public void setBinaryfoldingScheme(BinaryFoldingScheme binaryFoldingScheme) {
		if(binaryFoldingScheme == null) {
			this.binaryFoldingScheme = BinaryFoldingScheme.MIME_DIR;
		}
		else {
			this.binaryFoldingScheme = binaryFoldingScheme;
		}
	}
	
	/**
	 * <p>Sets the compatibility mode for outputting the vcard.
	 * If null {@link CompatibilityMode}.RFC2426 is used.</p>
	 *
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
	 * <p>Sets the EOL character to use when building the VCard.
	 * If not set then the default CRLF is used. See below list
	 * for compatible types:
	 * <ul>
	 * 		<li><strong>LF or <code>\n</code></strong>: Unix, GNU/Linux, AIX, Xenix, Mac OS X, FreeBSD, BeOS, Amiga, and RISC OS</li>
	 *  	<li><strong>CR or <code>\r</code></strong>:  Commodore 8-bit machines, Acorn BBC, TRS-80, Apple II family, Mac OS up to version 9 and OS-9</li>
	 *  	<li><strong>CRLF or <code>\r\n</code></strong>:  Microsoft Windows, DEC TOPS-10, RT-11, CP/M, MP/M, DOS (MS-DOS, PC-DOS, etc.), Atari TOS, OS/2, Symbian OS, Palm OS</li>
	 * </ul>
	 * See <a href="http://en.wikipedia.org/wiki/Newline">wikipedia</a> for more info.
	 * </p>
	 *
	 * @param eol
	 */
	public void setEOL(String eol) {
		if(eol == null) {
			this.eol = VCardUtils.CRLF;
		}
		else {
			this.eol = new String(eol);
		}
	}
	
	/**
	 * <p>Constructs a String in a suitable format to be written out
	 * as a .VCF file. This method will throw {@link VCardException}s only
	 * if the given VCard is set to throw exceptions. If not then exceptions
	 * are silently collected.</p>
	 *
	 * @return {@link String}
	 */
	public String buildVCardString()
	{
		if(vcard == null) {
			throw new VCardException("Cannot build a null VCard.");
		}
		
		StringBuilder sb = new StringBuilder();
		
		/*
		 * Begin
		 * Must be present.
		 */
		try {
			buildBeginFeature(sb, vcard.getBegin());
		}
		catch(VCardBuildException vbe) {
			if(isThrowsExceptions) {
				throw new VCardException(vbe.getMessage(), vbe);
			}
			else {
				handleError(vbe.getMessage(), vbe, ErrorSeverity.FATAL);
			}
		}
		
		/*
		 * Version
		 * Must be present.
		 */
		try {
			buildVersionFeature(sb, vcard.getVersion());
		}
		catch(VCardBuildException vbe) {
			if(isThrowsExceptions) {
				throw new VCardException(vbe.getMessage(), vbe);
			}
			else {
				handleError(vbe.getMessage(), vbe, ErrorSeverity.FATAL);
			}
		}
		
		/*
		 * Name
		 * Must be present.
		 */
		try {
			buildNameFeature(sb, vcard.getName());
		}
		catch(VCardBuildException vbe) {
			if(isThrowsExceptions) {
				throw new VCardException(vbe.getMessage(), vbe);
			}
			else {
				handleError(vbe.getMessage(), vbe, ErrorSeverity.FATAL);
			}
		}
		
		/*
		 * Formatted Name
		 * Must be present.
		 */
		try {
			buildFormattedNameFeature(sb, vcard.getFormattedName());
		}
		catch(VCardBuildException vbe) {
			if(isThrowsExceptions) {
				throw new VCardException(vbe.getMessage(), vbe);
			}
			else {
				handleError(vbe.getMessage(), vbe, ErrorSeverity.FATAL);
			}
		}
		
		/*
		 * Displayable Name
		 */
		if(vcard.hasDisplayableNameFeature()) {
			try {
				buildDisplayableNameFeature(sb, vcard.getDisplayableNameFeature());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.FATAL);
				}
			}
		}
		
		/*
		 * Profile
		 */
		if(vcard.hasProfile()) {
			try {
				buildProfileFeature(sb, vcard.getProfile());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.FATAL);
				}
			}
		}
		
		/*
		 * Source
		 */
		if(vcard.hasSource()) {
			try {
				buildSourceFeature(sb, vcard.getSource());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.FATAL);
				}
			}
		}
		
		/*
		 * Nicknames
		 * TODO nicknames are present in Outlook mode with V2.1
		 */
		if(vcard.hasNicknames()) {
			try {
				buildNicknames(sb, vcard.getNicknames());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Title
		 */
		if(vcard.hasTitle()) {
			try {
				buildTitleFeature(sb, vcard.getTitle());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Role
		 */
		if(vcard.hasRole()) {
			try {
				buildRoleFeature(sb, vcard.getRole());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Geographic Position
		 */
		if(vcard.hasGeographicPosition()) {
			try {
				buildGeographicPositionFeature(sb, vcard.getGeographicPosition());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Organizations
		 */
		if(vcard.hasOrganizations()) {
			try {
				buildOrganization(sb, vcard.getOrganizations());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Mailer
		 */
		if(vcard.hasMailer()) {
			try {
				buildMailerFeature(sb, vcard.getMailer());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Time Zone
		 */
		if(vcard.hasTimeZone()) {
			try {
				buildTimeZoneFeature(sb, vcard.getTimeZone());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * URL
		 */
		if(vcard.hasURLs()) {
			Iterator<URLFeature> urls = vcard.getURLs();
			while(urls.hasNext()) {
				URLFeature urlFeature = urls.next();
				
				try {
					buildUrlFeature(sb, urlFeature);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
		}
		
		/*
		 * Revision
		 */
		if(vcard.hasRevision()) {
			try {
				buildRevisionFeature(sb, vcard.getRevision());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * UID
		 */
		if(vcard.hasUID()) {
			try {
				buildUidFeature(sb, vcard.getUID());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Birthday
		 */
		if(vcard.hasBirthday()) {
			try {
				buildBirthdayFeature(sb, vcard.getBirthDay());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Addresses & Labels
		 */
		if(vcard.hasAddresses()) {
			Iterator<AddressFeature> addresses = vcard.getAddresses();
			while(addresses.hasNext()) {
				AddressFeature address = addresses.next();
				try {
					buildAddressFeature(sb, address);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
				
				if(vcard.hasLabel(address)) {
					LabelFeature label = vcard.getLabelFor(address);
					try {
						buildLabelFeature(sb, label);
					}
					catch(VCardBuildException vbe) {
						if(isThrowsExceptions) {
							throw new VCardException(vbe.getMessage(), vbe);
						}
						else {
							handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
						}
					}
				}
			}
		}
		
		/*
		 * Telephone Numbers
		 */
		if(vcard.hasTelephoneNumbers()) {
			Iterator<TelephoneFeature> telephones = vcard.getTelephoneNumbers();
			while(telephones.hasNext()) {
				TelephoneFeature telephone = telephones.next();
				try {
					buildTelephoneFeature(sb, telephone);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
		}
		
		/*
		 * Email
		 */
		if(vcard.hasEmails()) {
			Iterator<EmailFeature> emails = vcard.getEmails();
			while(emails.hasNext()) {
				EmailFeature email = emails.next();
				try {
					buildEmailFeature(sb, email);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
		}
		
		/*
		 * Notes
		 */
		if(vcard.hasNotes()) {
			Iterator<NoteFeature> notes = vcard.getNotes();
			while(notes.hasNext()) {
				NoteFeature note = notes.next();
				try {
					buildNoteFeature(sb, note);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
		}
		
		/*
		 * Categories
		 */
		if(vcard.hasCategories()) {
			try {
				buildCategoriesFeature(sb, vcard.getCategories());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Security Class
		 */
		if(vcard.hasSecurityClass()) {
			try {
				buildClassFeature(sb, vcard.getSecurityClass());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Product ID
		 */
		if(vcard.hasProductId()) {
			try {
				buildProductIdFeature(sb, vcard.getProductId());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Sort String
		 */
		if(vcard.hasSortString()) {
			try {
				buildSortStringFeature(sb, vcard.getSortString());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Keys
		 */
		if(vcard.hasKeys()) {
			Iterator<KeyFeature> keys = vcard.getKeys();
			while(keys.hasNext()) {
				KeyFeature key = keys.next();
				try {
					buildKeyFeature(sb, key);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
		}
		
		/*
		 * Photos
		 */
		if(vcard.hasPhotos()) {
			Iterator<PhotoFeature> photos = vcard.getPhotos();
			while(photos.hasNext()) {
				PhotoFeature photo = photos.next();
				try {
					buildPhotoFeature(sb, photo);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
		}
		
		/*
		 * Logos
		 */
		if(vcard.hasLogos()) {
			Iterator<LogoFeature> logos = vcard.getLogos();
			while(logos.hasNext()) {
				LogoFeature logo = logos.next();
				try {
					buildLogoFeature(sb, logo);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
		}
		
		/*
		 * Sounds
		 */
		if(vcard.hasSounds()) {
			Iterator<SoundFeature> sounds = vcard.getSounds();
			while(sounds.hasNext()) {
				SoundFeature sound = sounds.next();
				try {
					buildSoundFeature(sb, sound);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
		}
		
		/*
		 * Agents
		 */
		if(vcard.hasAgents()) {
			Iterator<AgentFeature> agents = vcard.getAgents();
			while(agents.hasNext()) {
				AgentFeature agent = agents.next();
				try {
					buildAgentFeature(sb, agent);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
		}
		
		/*
		 * Extensions
		 */
		if(vcard.hasExtendedTypes()) {
			Iterator<ExtendedFeature> extensions = vcard.getExtendedTypes();
			while(extensions.hasNext()) {
				ExtendedFeature extension = extensions.next();
				try {
					buildExtendedFeature(sb, extension);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
		}
		
		
		/*
		 * End
		 * Must be present.
		 */
		try {
			buildEndFeature(sb, vcard.getEnd());
		}
		catch(VCardBuildException vbe) {
			if(isThrowsExceptions) {
				throw new VCardException(vbe.getMessage(), vbe);
			}
			else {
				handleError(vbe.getMessage(), vbe, ErrorSeverity.FATAL);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * <p>Builds the begin feature as a String.</p>
	 *
	 * @param sb
	 * 	- StringBuilder to append to
	 * @param beginFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when begin feature is null
	 */
	private void buildBeginFeature(StringBuilder sb, BeginFeature beginFeature) throws VCardBuildException {
		try {
			if(beginFeature != null) {
				if(beginFeature.hasGroup()) {
					sb.append(beginFeature.getGroup());
					sb.append(".");
				}
				
				sb.append(beginFeature.getTypeString());
				sb.append(":VCARD");
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("Cannot continue because BeginFeature ("+VCardType.BEGIN.getType()+") is null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("BeginFeature ("+VCardType.BEGIN.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the end feature as a String.</p>
	 *
	 * @param sb
	 * 	- StringBuilder to append to
	 * @param beginFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when end feature is null
	 */
	private void buildEndFeature(StringBuilder sb, EndFeature endFeature) throws VCardBuildException {
		try {
			if(endFeature != null) {
				if(endFeature.hasGroup()) {
					sb.append(endFeature.getGroup());
					sb.append(".");
				}
				
				sb.append(endFeature.getTypeString());
				sb.append(":VCARD");
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("Cannot continue because EndFeature ("+VCardType.END.getType()+") is null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("EndFeature ("+VCardType.END.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the version feature as a String.</p>
	 *
	 * @param sb
	 * 	- StringBuilder to append to
	 * @param versionFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when version feature is null
	 */
	private void buildVersionFeature(StringBuilder sb, VersionFeature versionFeature) throws VCardBuildException {
		try {
			if(versionFeature != null) {
				if(versionFeature.hasGroup()) {
					sb.append(versionFeature.getGroup());
					sb.append(".");
				}
				
				if(versionFeature.getVersion() == VCardVersion.V2_1) {
					//We do not support writing in v2.1, so if it has
					//been set before, force it to v3.0
					versionFeature.setVersion(VCardVersion.V3_0);
				}
				
				sb.append(versionFeature.getTypeString());
				sb.append(":");
				sb.append(versionFeature.getVersion().getVersion());
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("Cannot continue because VersionFeature ("+VCardType.VERSION.getType()+") is null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("VersionFeature ("+VCardType.VERSION.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the name feature as a String. The order in which the name feature is built
	 * is as follows: Family Name, Given Name, Additional Names, Honorific Prefixes, and Honorific Suffixes.
	 * More details of the exact syntax are described in the {@link NameFeature} class itself.</p>
	 *
	 * @param sb
	 * 	- StringBuilder to append to
	 * @param nameFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the name feature is null
	 */
	private void buildNameFeature(StringBuilder sb, NameFeature nameFeature) throws VCardBuildException {
		try {
			if(nameFeature != null) {
				boolean isQuotedPrintable = nameFeature.isQuotedPrintable();
				StringBuilder tmpSb = new StringBuilder();
				
				if(nameFeature.hasGroup()) {
					tmpSb.append(nameFeature.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(nameFeature.getTypeString());
				
				if(nameFeature.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(nameFeature.getCharset().name());
				}
				
				if(nameFeature.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(nameFeature.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				tmpSb.append(":");
				
				if(nameFeature.hasFamilyName()) {
					tmpSb.append(escapeAndEncode(nameFeature.getFamilyName(), isQuotedPrintable, nameFeature.getCharset()));
				}

				tmpSb.append(";");

				if(nameFeature.hasGivenName()) {
					tmpSb.append(escapeAndEncode(nameFeature.getGivenName(), isQuotedPrintable, nameFeature.getCharset()));
				}

				tmpSb.append(";");

				if(nameFeature.hasAdditionalNames()) {
					Iterator<String> additionalNames = nameFeature.getAdditionalNames();
					while(additionalNames.hasNext()) {
						String addName = additionalNames.next();
						tmpSb.append(escapeAndEncode(addName, isQuotedPrintable, nameFeature.getCharset()));
						tmpSb.append(",");
					}

					tmpSb.deleteCharAt(tmpSb.length()-1);	// remove trailing comma
				}

				tmpSb.append(";");

				if(nameFeature.hasHonorificPrefixes()) {
					Iterator<String> prefixes = nameFeature.getHonorificPrefixes();
					while(prefixes.hasNext()) {
						String prefix = prefixes.next();
						tmpSb.append(escapeAndEncode(prefix, isQuotedPrintable, nameFeature.getCharset()));
						tmpSb.append(",");
					}

					tmpSb.deleteCharAt(tmpSb.length()-1);
				}

				tmpSb.append(";");

				if(nameFeature.hasHonorificSuffixes()) {
					Iterator<String> suffixes = nameFeature.getHonorificSuffixes();
					while(suffixes.hasNext()) {
						String suffix = suffixes.next();
						tmpSb.append(escapeAndEncode(suffix, isQuotedPrintable, nameFeature.getCharset()));
						tmpSb.append(",");
					}

					tmpSb.deleteCharAt(tmpSb.length()-1);
				}

				String tmpNameLine = tmpSb.toString();
				String foldedNameLine = VCardUtils.foldLine(tmpNameLine, eol, foldingScheme);
				sb.append(foldedNameLine);
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("NameFeature ("+VCardType.N.getType()+") cannot be left null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("NameFeature ("+VCardType.N.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the formatted name feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param formattedNameFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the formatted name feature is null
	 */
	private void buildFormattedNameFeature(StringBuilder sb, FormattedNameFeature formattedNameFeature) throws VCardBuildException {
		try {
			if(formattedNameFeature != null) {
				boolean isQuotedPrintable = formattedNameFeature.isQuotedPrintable();
				String formattedName = formattedNameFeature.getFormattedName();
				StringBuilder tmpSb = new StringBuilder();
				
				if(formattedNameFeature.hasGroup()) {
					tmpSb.append(formattedNameFeature.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(formattedNameFeature.getTypeString());
				
				if(formattedNameFeature.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(formattedNameFeature.getCharset().name());
				}
				
				if(formattedNameFeature.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(formattedNameFeature.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				tmpSb.append(":");
				tmpSb.append(escapeAndEncode(formattedName, isQuotedPrintable, formattedNameFeature.getCharset()));
				
				String tmpFormattedNameLine = tmpSb.toString();
				String foldedFormattedNameLine = VCardUtils.foldLine(tmpFormattedNameLine, eol, foldingScheme);
				sb.append(foldedFormattedNameLine);
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("FormattedNameFeature ("+VCardType.FN.getType()+") cannot be left null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("FormattedNameFeature ("+VCardType.FN.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the displayable name as a String</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param displayableNameFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the displayable name feature is null
	 */
	private void buildDisplayableNameFeature(StringBuilder sb, DisplayableNameFeature displayableNameFeature) throws VCardBuildException {
		try {
			if(displayableNameFeature != null) {
				boolean isQuotedPrintable = displayableNameFeature.isQuotedPrintable();
				String displayableName = displayableNameFeature.getName();
				StringBuilder tmpSb = new StringBuilder();
				
				if(displayableNameFeature.hasGroup()) {
					tmpSb.append(displayableNameFeature.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(displayableNameFeature.getTypeString());
				
				if(displayableNameFeature.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(displayableNameFeature.getCharset().name());
				}
				
				if(displayableNameFeature.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(displayableNameFeature.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				tmpSb.append(":");
				tmpSb.append(escapeAndEncode(displayableName, isQuotedPrintable, displayableNameFeature.getCharset()));
				
				String tmpDisplayableNameLine = tmpSb.toString();
				String foldedDisplayableNameLine = VCardUtils.foldLine(tmpDisplayableNameLine, eol, foldingScheme);
				sb.append(foldedDisplayableNameLine);
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("DisplayableNameFeature ("+VCardType.NAME.getType()+") cannot be left null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("DisplayableNameFeature ("+VCardType.NAME.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the profile as a String</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param profileFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the profile feature is null
	 */
	private void buildProfileFeature(StringBuilder sb, ProfileFeature profileFeature) throws VCardBuildException {
		try {
			if(profileFeature != null) {
				boolean isQuotedPrintable = profileFeature.isQuotedPrintable();
				String profile = profileFeature.getProfile();
				StringBuilder tmpSb = new StringBuilder();
				
				if(profileFeature.hasGroup()) {
					tmpSb.append(profileFeature.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(profileFeature.getTypeString());
				
				if(profileFeature.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(profileFeature.getCharset().name());
				}
				
				if(profileFeature.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(profileFeature.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				tmpSb.append(":");
				tmpSb.append(escapeAndEncode(profile, isQuotedPrintable, profileFeature.getCharset()));
				
				String tmpProfileLine = tmpSb.toString();
				String foldedProfileLine = VCardUtils.foldLine(tmpProfileLine, eol, foldingScheme);
				sb.append(foldedProfileLine);
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("ProfileFeature ("+VCardType.PROFILE.getType()+") cannot be left null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("ProfileFeature ("+VCardType.PROFILE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the source as a String</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param sourceFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the source feature is null
	 */
	private void buildSourceFeature(StringBuilder sb, SourceFeature sourceFeature) throws VCardBuildException {
		try {
			if(sourceFeature != null) {
				boolean isQuotedPrintable = sourceFeature.isQuotedPrintable();
				String source = sourceFeature.getSource();
				StringBuilder tmpSb = new StringBuilder();
				
				if(sourceFeature.hasGroup()) {
					tmpSb.append(sourceFeature.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(sourceFeature.getTypeString());
				
				if(sourceFeature.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(sourceFeature.getCharset().name());
				}
				
				if(sourceFeature.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(sourceFeature.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				tmpSb.append(":");
				tmpSb.append(escapeAndEncode(source, isQuotedPrintable, sourceFeature.getCharset()));
				
				String tmpSourceLine = tmpSb.toString();
				String foldedSourceLine = VCardUtils.foldLine(tmpSourceLine, eol, foldingScheme);
				sb.append(foldedSourceLine);
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("SourceFeature ("+VCardType.SOURCE.getType()+") cannot be left null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("SourceFeature ("+VCardType.SOURCE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the title feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param titleFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the title feature is null
	 */
	private void buildTitleFeature(StringBuilder sb, TitleFeature titleFeature) throws VCardBuildException {
		try {
			if(titleFeature != null) {
				if(titleFeature.hasTitle()) {
					boolean isQuotedPrintable = titleFeature.isQuotedPrintable();
					String title = titleFeature.getTitle();
					StringBuilder tmpSb = new StringBuilder();
					
					if(titleFeature.hasGroup()) {
						tmpSb.append(titleFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(titleFeature.getTypeString());
					
					if(titleFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(titleFeature.getCharset().name());
					}
					
					if(titleFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(titleFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(title, isQuotedPrintable, titleFeature.getCharset()));
					
					String tmpTitleLine = tmpSb.toString();
					String foldedTitleLine = VCardUtils.foldLine(tmpTitleLine, eol, foldingScheme);
					sb.append(foldedTitleLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("TitleFeature ("+VCardType.TITLE.getType()+") exists but is emtpy.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("TitleFeature ("+VCardType.TITLE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the role feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param roleFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the role feature is null
	 */
	private void buildRoleFeature(StringBuilder sb, RoleFeature roleFeature) throws VCardBuildException {
		try {
			if(roleFeature != null) {
				if(roleFeature.hasRole()) {
					boolean isQuotedPrintable = roleFeature.isQuotedPrintable();
					String role = roleFeature.getRole();
					StringBuilder tmpSb = new StringBuilder();
					
					if(roleFeature.hasGroup()) {
						tmpSb.append(roleFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(roleFeature.getTypeString());
					
					if(roleFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(roleFeature.getCharset().name());
					}
					
					if(roleFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(roleFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(role, isQuotedPrintable, roleFeature.getCharset()));
					
					String tmpRoleLine = tmpSb.toString();
					String foldedRoleLine = VCardUtils.foldLine(tmpRoleLine, eol, foldingScheme);
					sb.append(foldedRoleLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("RoleFeature ("+VCardType.ROLE.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("RoleFeature ("+VCardType.ROLE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the geographic position feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param geographicPositionFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the geographic position feature is null
	 */
	private void buildGeographicPositionFeature(StringBuilder sb, GeographicPositionFeature geographicPositionFeature) throws VCardBuildException {
		try {
			if(geographicPositionFeature != null) {
				StringBuilder tmpSb = new StringBuilder();
				
				if(geographicPositionFeature.hasGroup()) {
					tmpSb.append(geographicPositionFeature.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(geographicPositionFeature.getTypeString());
				
				if(geographicPositionFeature.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(geographicPositionFeature.getCharset().name());
				}
				
				tmpSb.append(":");
				tmpSb.append(VCardUtils.getGeographicPositionFormatter().format(geographicPositionFeature.getLatitude()));
				tmpSb.append(";");
				tmpSb.append(VCardUtils.getGeographicPositionFormatter().format(geographicPositionFeature.getLongitude()));
				
				String tmpGeoLine = tmpSb.toString();
				String foldedGeoLine = VCardUtils.foldLine(tmpGeoLine, eol, foldingScheme);
				sb.append(foldedGeoLine);
				sb.append(eol);
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("GeographicPositionFeature ("+VCardType.GEO.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the organization feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param organizationFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the organization feature is null
	 */
	private void buildOrganization(StringBuilder sb, OrganizationFeature organizationFeature) throws VCardBuildException {
		try {
			if(organizationFeature != null) {
				if(organizationFeature.hasOrganizations()) {
					boolean isQuotedPrintable = organizationFeature.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(organizationFeature.hasGroup()) {
						tmpSb.append(organizationFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(organizationFeature.getTypeString());
					
					if(organizationFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(organizationFeature.getCharset().name());
					}
					
					if(organizationFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(organizationFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					
					Iterator<String> orgs = organizationFeature.getOrganizations();
					while(orgs.hasNext()) {
						String org = orgs.next();
						tmpSb.append(escapeAndEncode(org, isQuotedPrintable, organizationFeature.getCharset()));
						tmpSb.append(";");
					}
					
					tmpSb.deleteCharAt(tmpSb.length()-1);
					String tmpOrgLine = tmpSb.toString();
					String foldedOrgLine = VCardUtils.foldLine(tmpOrgLine, eol, foldingScheme);
					sb.append(foldedOrgLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("OrganizationFeature ("+VCardType.ORG.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("OrganizationFeature ("+VCardType.ORG.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the mailer feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param mailerFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the mailer feature is null
	 */
	private void buildMailerFeature(StringBuilder sb, MailerFeature mailerFeature) throws VCardBuildException {
		try {
			if(mailerFeature != null) {
				if(mailerFeature.hasMailer()) {
					boolean isQuotedPrintable = mailerFeature.isQuotedPrintable();
					String mailer = mailerFeature.getMailer();
					StringBuilder tmpSb = new StringBuilder();
					
					if(mailerFeature.hasGroup()) {
						tmpSb.append(mailerFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(mailerFeature.getTypeString());
					
					if(mailerFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(mailerFeature.getCharset().name());
					}
					
					if(mailerFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(mailerFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(mailer, isQuotedPrintable, mailerFeature.getCharset()));
					
					String tmpMailerLine = tmpSb.toString();
					String foldedMailerLine = VCardUtils.foldLine(tmpMailerLine, eol, foldingScheme);
					sb.append(foldedMailerLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("MailerFeature ("+VCardType.MAILER.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("MailerFeature ("+VCardType.MAILER.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the time zone feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param timeZoneFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the time zone feature is null
	 */
	private void buildTimeZoneFeature(StringBuilder sb, TimeZoneFeature timeZoneFeature) throws VCardBuildException {
		try {
			if(timeZoneFeature != null) {
				StringBuilder tmpSb = new StringBuilder();
				
				if(timeZoneFeature.hasGroup()) {
					tmpSb.append(timeZoneFeature.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(timeZoneFeature.getTypeString());

				if(timeZoneFeature.getTimeZone() == null && timeZoneFeature.getTextValue() != null) {
					tmpSb.append(";");
					tmpSb.append(TimeZoneParameterType.VALUE.getType());
					tmpSb.append("=TEXT:");
					tmpSb.append(timeZoneFeature.getTextValue());
				}
				else {
					tmpSb.append(":");
					tmpSb.append(timeZoneFeature.getIso8601Offset());
				}

				String tmpTimeZoneLine = tmpSb.toString();
				String foldedTimeZoneLine = VCardUtils.foldLine(tmpTimeZoneLine, eol, foldingScheme);
				sb.append(foldedTimeZoneLine);
				sb.append(eol);
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("TimeZoneFeature ("+VCardType.TZ.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the URL feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param urlFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the URL feature is null
	 */
	private void buildUrlFeature(StringBuilder sb, URLFeature urlFeature) throws VCardBuildException {
		try {
			if(urlFeature != null) {
				if(urlFeature.hasURL()) {
					boolean isQuotedPrintable = urlFeature.isQuotedPrintable();
					String url = urlFeature.getURL().toString();
					StringBuilder tmpSb = new StringBuilder();
					
					if(urlFeature.hasGroup()) {
						tmpSb.append(urlFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(urlFeature.getTypeString());
					
					switch(compatMode)
					{
						case MS_OUTLOOK:
						case I_PHONE:
						case GMAIL:
						case MAC_ADDRESS_BOOK:
						{
							if(urlFeature.hasURLParameterTypes()) {
								tmpSb.append(";");
								Iterator<URLParameterType> paramTypes = urlFeature.getURLParameterTypes();
								switch(urlFeature.getParameterTypeStyle())
								{
									case PARAMETER_LIST:
									{
										while(paramTypes.hasNext()) {
											URLParameterType urlType = paramTypes.next();
											tmpSb.append("TYPE=");
											tmpSb.append(urlType.getType());
											tmpSb.append(";");
										}

										break;
									}

									case PARAMETER_VALUE_LIST:
									{
										tmpSb.append("TYPE=");
										while(paramTypes.hasNext()) {
											URLParameterType urlType = paramTypes.next();
											tmpSb.append(urlType.getType());
											tmpSb.append(",");
										}

										break;
									}
								}

								tmpSb.deleteCharAt(tmpSb.length()-1);
							}
							
							if(urlFeature.hasExtendedURLParameterTypes()) {
								Iterator<XURLParameterType> xParamTypes = urlFeature.getExtendedURLParameterTypes();
								switch(urlFeature.getParameterTypeStyle())
								{
									case PARAMETER_LIST:
									{
										tmpSb.append(";");
										
										while(xParamTypes.hasNext()) {
											XURLParameterType xUrlType = xParamTypes.next();
											tmpSb.append("TYPE=");
											tmpSb.append(xUrlType.getXtendedTypeName());
											if(xUrlType.hasXtendedTypeValue()) {
												tmpSb.append("=");
												tmpSb.append(xUrlType.getXtendedTypeValue());
											}
											
											tmpSb.append(";");
										}

										break;
									}

									case PARAMETER_VALUE_LIST:
									{
										if(urlFeature.hasURLParameterTypes()) {
											//Continue from the list
											tmpSb.append(",");
										}
										else {
											//Start a new
											tmpSb.append(";TYPE=");
										}
										
										while(xParamTypes.hasNext()) {
											XURLParameterType xUrlType = xParamTypes.next();
											tmpSb.append(xUrlType.getXtendedTypeName());
											if(xUrlType.hasXtendedTypeValue()) {
												tmpSb.append("=");
												tmpSb.append(xUrlType.getXtendedTypeValue());
											}
											
											tmpSb.append(",");
										}

										break;
									}
								}

								tmpSb.deleteCharAt(tmpSb.length()-1);
							}
							
							break;
						}
					}
					
					if(urlFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(urlFeature.getCharset().name());
					}
					
					if(urlFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(urlFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(url, isQuotedPrintable, urlFeature.getCharset()));
					
					String tmpUrlLine = tmpSb.toString();
					String foldedUrlLine = VCardUtils.foldLine(tmpUrlLine, eol, foldingScheme);
					sb.append(foldedUrlLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("URLFeature ("+VCardType.URL.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("URLFeature ("+VCardType.URL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the revision feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param revisionFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the revision feature is null
	 */
	private void buildRevisionFeature(StringBuilder sb, RevisionFeature revisionFeature) throws VCardBuildException {
		try {
			if(revisionFeature != null) {
				if(revisionFeature.hasRevision()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(revisionFeature.hasGroup()) {
						tmpSb.append(revisionFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(revisionFeature.getTypeString());
					
					if(revisionFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(revisionFeature.getCharset().name());
					}
					
					tmpSb.append(":");
					tmpSb.append(ISOUtils.toISO8601_UTC_Time(revisionFeature.getRevision(), ISOFormat.ISO8601_EXTENDED));
					
					String tmpRevisionLine = tmpSb.toString();
					String foldedRevisionLine = VCardUtils.foldLine(tmpRevisionLine, eol, foldingScheme);
					sb.append(foldedRevisionLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("RevisionFeature ("+VCardType.REV.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("RevisionFeature ("+VCardType.REV.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the UID feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param uidFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the uid feature is null
	 */
	private void buildUidFeature(StringBuilder sb, UIDFeature uidFeature) throws VCardBuildException {
		try {
			if(uidFeature != null) {
				if(uidFeature.hasUID()) {
					boolean isQuotedPrintable = uidFeature.isQuotedPrintable();
					String uid = uidFeature.getUID();
					StringBuilder tmpSb = new StringBuilder();
					
					if(uidFeature.hasGroup()) {
						tmpSb.append(uidFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(uidFeature.getTypeString());
					
					if(uidFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(uidFeature.getCharset().name());
					}
					
					if(uidFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(uidFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(uid, isQuotedPrintable, uidFeature.getCharset()));
					
					String tmpUidLine = tmpSb.toString();
					String foldedUidLine = VCardUtils.foldLine(tmpUidLine, eol, foldingScheme);
					sb.append(foldedUidLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("UIDFeature ("+VCardType.UID.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("UIDFeature ("+VCardType.UID.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the birthday feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param birthdayFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the birthday feature is null
	 */
	private void buildBirthdayFeature(StringBuilder sb, BirthdayFeature birthdayFeature) throws VCardBuildException {
		try {
			if(birthdayFeature != null) {
				StringBuilder tmpSb = new StringBuilder();
				
				if(birthdayFeature.hasGroup()) {
					tmpSb.append(birthdayFeature.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(birthdayFeature.getTypeString());
				
				if(birthdayFeature.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(birthdayFeature.getCharset().name());
				}
				
				if(birthdayFeature.hasBirthdayParameterType()) {
					BirthdayParameterType ptype = birthdayFeature.getBirthdayParameterType();
					tmpSb.append(";VALUE=");
					tmpSb.append(ptype.getTypeName());
				}
				
				ISOFormat isoFormat = birthdayFeature.getISO8601Format();
				switch(isoFormat)
				{
					case ISO8601_DATE_BASIC:
					case ISO8601_DATE_EXTENDED:
					{
						tmpSb.append(":");
						tmpSb.append(ISOUtils.toISO8601_Date(birthdayFeature.getBirthday(), isoFormat));
						break;
					}
					
					case ISO8601_EXTENDED:
					case ISO8601_UTC_TIME_BASIC:
					case ISO8601_UTC_TIME_EXTENDED:
					{
						tmpSb.append(":");
						tmpSb.append(ISOUtils.toISO8601_UTC_Time(birthdayFeature.getBirthday(), isoFormat));
						break;
					}
				}
				
				String tmpBdayLine = tmpSb.toString();
				String foldedBdayLine = VCardUtils.foldLine(tmpBdayLine, eol, foldingScheme);
				sb.append(foldedBdayLine);
				sb.append(eol);
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("BirthdayFeature ("+VCardType.BDAY.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}

	/**
	 * <p>Builds the address feature as a String. The order of the address is built as follows:
	 * post office box; the extended address; the street address; the locality; the region; the postal code; the country name.
	 * The street address can contain multiple values separated by a comma.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param addressFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the address feature is null
	 */
	private void buildAddressFeature(StringBuilder sb, AddressFeature addressFeature) throws VCardBuildException {
		try {
			if(addressFeature != null) {
				boolean isQuotedPrintable = addressFeature.isQuotedPrintable();
				StringBuilder tmpSb = new StringBuilder();
				
				if(addressFeature.hasGroup()) {
					tmpSb.append(addressFeature.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(addressFeature.getTypeString());
				
				if(addressFeature.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(addressFeature.getCharset().name());
				}
				
				if(addressFeature.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(addressFeature.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				if(addressFeature.hasAddressParameterTypes()) {
					tmpSb.append(";");
					Iterator<AddressParameterType> paramTypes = addressFeature.getAddressParameterTypes();
					switch(addressFeature.getParameterTypeStyle())
					{
						case PARAMETER_LIST:
						{
							while(paramTypes.hasNext()) {
								AddressParameterType addrType = paramTypes.next();
								tmpSb.append("TYPE=");
								tmpSb.append(addrType.getType());
								tmpSb.append(";");
							}

							break;
						}

						case PARAMETER_VALUE_LIST:
						{
							tmpSb.append("TYPE=");
							while(paramTypes.hasNext()) {
								AddressParameterType addrType = paramTypes.next();
								tmpSb.append(addrType.getType());
								tmpSb.append(",");
							}

							break;
						}
					}

					tmpSb.deleteCharAt(tmpSb.length()-1);
				}
				
				if(addressFeature.hasExtendedAddressParameterTypes()) {
					Iterator<XAddressParameterType> xParamTypes = addressFeature.getExtendedAddressParameterTypes();
					switch(addressFeature.getParameterTypeStyle())
					{
						case PARAMETER_LIST:
						{
							tmpSb.append(";");
							
							while(xParamTypes.hasNext()) {
								XAddressParameterType xAddrType = xParamTypes.next();
								tmpSb.append("TYPE=");
								tmpSb.append(xAddrType.getXtendedTypeName());
								if(xAddrType.hasXtendedTypeValue()) {
									tmpSb.append("=");
									tmpSb.append(xAddrType.getXtendedTypeValue());
								}
								
								tmpSb.append(";");
							}

							break;
						}

						case PARAMETER_VALUE_LIST:
						{
							if(addressFeature.hasAddressParameterTypes()) {
								//Continue from the list
								tmpSb.append(",");
							}
							else {
								//Start a new
								tmpSb.append(";TYPE=");
							}
							
							while(xParamTypes.hasNext()) {
								XAddressParameterType xAddrType = xParamTypes.next();
								tmpSb.append(xAddrType.getXtendedTypeName());
								if(xAddrType.hasXtendedTypeValue()) {
									tmpSb.append("=");
									tmpSb.append(xAddrType.getXtendedTypeValue());
								}
								
								tmpSb.append(",");
							}

							break;
						}
					}

					tmpSb.deleteCharAt(tmpSb.length()-1);
				}
				
				tmpSb.append(":");
				if(addressFeature.hasPostOfficebox()) {
					tmpSb.append(escapeAndEncode(addressFeature.getPostOfficeBox(), isQuotedPrintable, addressFeature.getCharset()));
				}

				tmpSb.append(";");

				if(addressFeature.hasExtendedAddress()) {
					tmpSb.append(escapeAndEncode(addressFeature.getExtendedAddress(), isQuotedPrintable, addressFeature.getCharset()));
				}

				tmpSb.append(";");
				
				if(addressFeature.hasStreetAddress()) {
					tmpSb.append(escapeAndEncode(addressFeature.getStreetAddress(), isQuotedPrintable, addressFeature.getCharset()));
				}

				tmpSb.append(";");

				if(addressFeature.hasLocality()) {
					tmpSb.append(escapeAndEncode(addressFeature.getLocality(), isQuotedPrintable, addressFeature.getCharset()));
				}

				tmpSb.append(";");

				if(addressFeature.hasRegion()) {
					tmpSb.append(escapeAndEncode(addressFeature.getRegion(), isQuotedPrintable, addressFeature.getCharset()));
				}

				tmpSb.append(";");

				if(addressFeature.hasPostalCode()) {
					tmpSb.append(escapeAndEncode(addressFeature.getPostalCode(), isQuotedPrintable, addressFeature.getCharset()));
				}

				tmpSb.append(";");

				if(addressFeature.hasCountryName()) {
					tmpSb.append(escapeAndEncode(addressFeature.getCountryName(), isQuotedPrintable, addressFeature.getCharset()));
				}

				String tmpAddressLine = tmpSb.toString();
				String foldedAddressLine = VCardUtils.foldLine(tmpAddressLine, eol, foldingScheme);
				sb.append(foldedAddressLine);
				sb.append(eol);
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("AddressFeature ("+VCardType.ADR.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the label feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param labelFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the label feature is null
	 */
	private void buildLabelFeature(StringBuilder sb, LabelFeature labelFeature) throws VCardBuildException {
		try {
			if(labelFeature != null) {
				if(labelFeature.hasLabel()) {
					boolean isQuotedPrintable = labelFeature.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(labelFeature.hasGroup()) {
						tmpSb.append(labelFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(labelFeature.getTypeString());
					
					if(labelFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(labelFeature.getCharset().name());
					}
					
					if(labelFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(labelFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(labelFeature.hasLabelParameterTypes()) {
						tmpSb.append(";");
						Iterator<LabelParameterType> paramTypes = labelFeature.getLabelParameterTypes();
						switch(labelFeature.getParameterTypeStyle())
						{
							case PARAMETER_LIST:
							{
								while(paramTypes.hasNext()) {
									LabelParameterType labelType = paramTypes.next();
									tmpSb.append("TYPE=");
									tmpSb.append(labelType.getType());
									tmpSb.append(";");
								}

								break;
							}

							case PARAMETER_VALUE_LIST:
							{
								tmpSb.append("TYPE=");
								while(paramTypes.hasNext()) {
									LabelParameterType labelType = paramTypes.next();
									tmpSb.append(labelType.getType());
									tmpSb.append(",");
								}

								break;
							}
						}

						tmpSb.deleteCharAt(tmpSb.length()-1);
					}
					
					if(labelFeature.hasExtendedLabelParameterTypes()) {
						Iterator<XLabelParameterType> xParamTypes = labelFeature.getExtendedLabelParameterTypes();
						switch(labelFeature.getParameterTypeStyle())
						{
							case PARAMETER_LIST:
							{
								tmpSb.append(";");
								
								while(xParamTypes.hasNext()) {
									XLabelParameterType xLabelType = xParamTypes.next();
									tmpSb.append("TYPE=");
									tmpSb.append(xLabelType.getXtendedTypeName());
									if(xLabelType.hasXtendedTypeValue()) {
										tmpSb.append("=");
										tmpSb.append(xLabelType.getXtendedTypeValue());
									}
									
									tmpSb.append(";");
								}

								break;
							}

							case PARAMETER_VALUE_LIST:
							{
								if(labelFeature.hasLabelParameterTypes()) {
									//Continue from the list
									tmpSb.append(",");
								}
								else {
									//Start a new
									tmpSb.append(";TYPE=");
								}
								
								while(xParamTypes.hasNext()) {
									XLabelParameterType xLabelType = xParamTypes.next();
									tmpSb.append(xLabelType.getXtendedTypeName());
									if(xLabelType.hasXtendedTypeValue()) {
										tmpSb.append("=");
										tmpSb.append(xLabelType.getXtendedTypeValue());
									}
									
									tmpSb.append(",");
								}

								break;
							}
						}

						tmpSb.deleteCharAt(tmpSb.length()-1);
					}
					
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(labelFeature.getLabel(), isQuotedPrintable, labelFeature.getCharset()));
					String tmpLabelLine = tmpSb.toString();
					String foldedLabelLine = VCardUtils.foldLine(tmpLabelLine, eol, foldingScheme);
					sb.append(foldedLabelLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("LabelFeature ("+VCardType.LABEL.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("LablelFeature ("+VCardType.LABEL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the telephone feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param telephoneFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the telephone feature is null
	 */
	private void buildTelephoneFeature(StringBuilder sb, TelephoneFeature telephoneFeature) throws VCardBuildException {
		try {
			if(telephoneFeature != null) {
				if(telephoneFeature.hasTelephone()) {
					boolean isQuotedPrintable = telephoneFeature.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(telephoneFeature.hasGroup()) {
						tmpSb.append(telephoneFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(telephoneFeature.getTypeString());
					
					if(telephoneFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(telephoneFeature.getCharset().name());
					}
					
					if(telephoneFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(telephoneFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(telephoneFeature.hasTelephoneParameterTypes()) {
						tmpSb.append(";");
						Iterator<TelephoneParameterType> paramTypes = telephoneFeature.getTelephoneParameterTypes();
						switch(telephoneFeature.getParameterTypeStyle())
						{
							case PARAMETER_LIST:
							{
								while(paramTypes.hasNext()) {
									TelephoneParameterType teleType = paramTypes.next();
									tmpSb.append("TYPE=");
									tmpSb.append(teleType.getType());
									tmpSb.append(";");
								}

								break;
							}

							case PARAMETER_VALUE_LIST:
							{
								tmpSb.append("TYPE=");
								while(paramTypes.hasNext()) {
									TelephoneParameterType teleType = paramTypes.next();
									tmpSb.append(teleType.getType());
									tmpSb.append(",");
								}

								break;
							}
						}

						tmpSb.deleteCharAt(tmpSb.length()-1);
					}
					
					if(telephoneFeature.hasExtendedTelephoneParameterTypes()) {
						Iterator<XTelephoneParameterType> xParamTypes = telephoneFeature.getExtendedTelephoneParameterTypes();
						switch(telephoneFeature.getParameterTypeStyle())
						{
							case PARAMETER_LIST:
							{
								tmpSb.append(";");
								
								while(xParamTypes.hasNext()) {
									XTelephoneParameterType xTelType = xParamTypes.next();
									tmpSb.append("TYPE=");
									tmpSb.append(xTelType.getXtendedTypeName());
									if(xTelType.hasXtendedTypeValue()) {
										tmpSb.append("=");
										tmpSb.append(xTelType.getXtendedTypeValue());
									}
									
									tmpSb.append(";");
								}

								break;
							}

							case PARAMETER_VALUE_LIST:
							{
								if(telephoneFeature.hasTelephoneParameterTypes()) {
									//Continue from the list
									tmpSb.append(",");
								}
								else {
									//Start a new
									tmpSb.append(";TYPE=");
								}
								
								while(xParamTypes.hasNext()) {
									XTelephoneParameterType xTelType = xParamTypes.next();
									tmpSb.append(xTelType.getXtendedTypeName());
									if(xTelType.hasXtendedTypeValue()) {
										tmpSb.append("=");
										tmpSb.append(xTelType.getXtendedTypeValue());
									}
									
									tmpSb.append(",");
								}

								break;
							}
						}

						tmpSb.deleteCharAt(tmpSb.length()-1);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(telephoneFeature.getTelephone(), isQuotedPrintable, telephoneFeature.getCharset()));
					
					String tmpTelephoneLine = tmpSb.toString();
					String foldedTelephoneLine = VCardUtils.foldLine(tmpTelephoneLine, eol, foldingScheme);
					sb.append(foldedTelephoneLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("TelephoneFeature ("+VCardType.TEL.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("TelephoneFeature ("+VCardType.TEL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * 
	 * <p>Builds the email feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param emailFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the email feature is null
	 */
	private void buildEmailFeature(StringBuilder sb, EmailFeature emailFeature) throws VCardBuildException {
		try {
			if(emailFeature != null) {
				if(emailFeature.hasEmail()) {
					boolean isQuotedPrintable = emailFeature.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(emailFeature.hasGroup()) {
						tmpSb.append(emailFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(emailFeature.getTypeString());
					
					if(emailFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(emailFeature.getCharset().name());
					}
					
					if(emailFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(emailFeature.getLanguage().getLanguageCode());
					}
					
					if(emailFeature.hasEmailParameterTypes()) {
						tmpSb.append(";");
						Iterator<EmailParameterType> paramTypes = emailFeature.getEmailParameterTypes();
						
						switch(emailFeature.getParameterTypeStyle())
						{
							case PARAMETER_LIST:
							{
								while(paramTypes.hasNext()) {
									EmailParameterType emailParamType = paramTypes.next();
									tmpSb.append("TYPE=");
									tmpSb.append(emailParamType.getType());
									tmpSb.append(";");
								}

								break;
							}

							case PARAMETER_VALUE_LIST:
							{
								tmpSb.append("TYPE=");
								while(paramTypes.hasNext()) {
									EmailParameterType teleType = paramTypes.next();
									tmpSb.append(teleType.getType());
									tmpSb.append(",");
								}

								break;
							}
						}

						tmpSb.deleteCharAt(tmpSb.length()-1);
					}
					
					if(emailFeature.hasExtendedEmailParameterTypes()) {
						Iterator<XEmailParameterType> xParamTypes = emailFeature.getExtendedEmailParameterTypes();
						switch(emailFeature.getParameterTypeStyle())
						{
							case PARAMETER_LIST:
							{
								tmpSb.append(";");
								
								while(xParamTypes.hasNext()) {
									XEmailParameterType xEmailType = xParamTypes.next();
									tmpSb.append("TYPE=");
									tmpSb.append(xEmailType.getXtendedTypeName());
									if(xEmailType.hasXtendedTypeValue()) {
										tmpSb.append("=");
										tmpSb.append(xEmailType.getXtendedTypeValue());
									}
									
									tmpSb.append(";");
								}

								break;
							}

							case PARAMETER_VALUE_LIST:
							{
								if(emailFeature.hasEmailParameterTypes()) {
									//Continue from the list
									tmpSb.append(",");
								}
								else {
									//Start a new
									tmpSb.append(";TYPE=");
								}
								
								while(xParamTypes.hasNext()) {
									XEmailParameterType xEmailType = xParamTypes.next();
									tmpSb.append(xEmailType.getXtendedTypeName());
									if(xEmailType.hasXtendedTypeValue()) {
										tmpSb.append("=");
										tmpSb.append(xEmailType.getXtendedTypeValue());
									}
									
									tmpSb.append(",");
								}

								break;
							}
						}

						tmpSb.deleteCharAt(tmpSb.length()-1);
					}
					
					EncodingType encType = emailFeature.getEncodingType();
					switch(encType)
					{
						case BASE64:
						case BINARY:
						{
							tmpSb.append(";ENCODING=");
							tmpSb.append(encType.getType());
							tmpSb.append(":");
							
							byte[] emailBytes = null;
							if(emailFeature.hasCharset()) {
								emailBytes = emailFeature.getEmail().getBytes(emailFeature.getCharset().name());
							}
							else {
								emailBytes = emailFeature.getEmail().getBytes(Charset.defaultCharset());
							}
							
							if(emailFeature.isSetCompression()) {
								tmpSb.append(Base64Wrapper.encode(emailBytes, Base64Wrapper.OPTIONS.GZIP_COMPRESSION));
							}
							else {
								tmpSb.append(Base64Wrapper.encode(emailBytes));
							}
							
							break;
						}
						
						case QUOTED_PRINTABLE:
						{
							tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
						}
						
						default:
						{
							tmpSb.append(":");
							tmpSb.append(escapeAndEncode(emailFeature.getEmail(), isQuotedPrintable, emailFeature.getCharset()));
							break;
						}
					}
					
					String tmpEmailLine = tmpSb.toString();
					String foldedEmailLine = VCardUtils.foldLine(tmpEmailLine, eol, binaryFoldingScheme);
					sb.append(foldedEmailLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("EmailFeature ("+VCardType.EMAIL.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("EmailFeature ("+VCardType.EMAIL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a note feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param noteFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the note feature is null
	 */
	private void buildNoteFeature(StringBuilder sb, NoteFeature noteFeature) throws VCardBuildException {
		try {
			if(noteFeature != null) {
				if(noteFeature.hasNote()) {
					boolean isQuotedPrintable = noteFeature.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(noteFeature.hasGroup()) {
						tmpSb.append(noteFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(noteFeature.getTypeString());
					
					if(noteFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(noteFeature.getCharset().name());
					}
					
					if(noteFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(noteFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(noteFeature.getNote(), isQuotedPrintable, noteFeature.getCharset()));
					
					String tmpNoteLine = tmpSb.toString();
					String foldedNoteLine = VCardUtils.foldLine(tmpNoteLine, eol, foldingScheme);
					sb.append(foldedNoteLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("NoteFeature ("+VCardType.NOTE.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("NoteFeature ("+VCardType.NOTE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the nickname feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param nicknameFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the nickname feature is null
	 */
	private void buildNicknames(StringBuilder sb, NicknameFeature nicknameFeature) throws VCardBuildException {
		try {
			if(nicknameFeature != null) {
				if(nicknameFeature.hasNicknames()) {
					boolean isQuotedPrintable = nicknameFeature.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(nicknameFeature.hasGroup()) {
						tmpSb.append(nicknameFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(nicknameFeature.getTypeString());
					
					if(nicknameFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(nicknameFeature.getCharset().name());
					}
					
					if(nicknameFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(nicknameFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					
					Iterator<String> nicknames = nicknameFeature.getNicknames();
					while(nicknames.hasNext()) {
						String nickname = nicknames.next();
						tmpSb.append(escapeAndEncode(nickname, isQuotedPrintable, nicknameFeature.getCharset()));
						tmpSb.append(",");
					}
					
					tmpSb.deleteCharAt(tmpSb.length()-1);
					String tmpNicknameLine = tmpSb.toString();
					String foldedNicknameLine = VCardUtils.foldLine(tmpNicknameLine, eol, foldingScheme);
					sb.append(foldedNicknameLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("NicknameFeature ("+VCardType.NICKNAME.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("NicknameFeature ("+VCardType.NICKNAME.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the category feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param categoriesFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the categories feature is null
	 */
	private void buildCategoriesFeature(StringBuilder sb, CategoriesFeature categoriesFeature) throws VCardBuildException {
		try {
			if(categoriesFeature != null) {
				if(categoriesFeature.hasCategories()) {
					boolean isQuotedPrintable = categoriesFeature.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(categoriesFeature.hasGroup()) {
						tmpSb.append(categoriesFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(categoriesFeature.getTypeString());
					
					if(categoriesFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(categoriesFeature.getCharset().name());
					}
					
					if(categoriesFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(categoriesFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					
					Iterator<String> categories = categoriesFeature.getCategories();
					while(categories.hasNext()) {
						String category = categories.next();
						tmpSb.append(escapeAndEncode(category, isQuotedPrintable, categoriesFeature.getCharset()));
						
						switch(compatMode)
						{
							case KDE_ADDRESS_BOOK:
							{
								tmpSb.append("\\,");
								break;
							}
							
							default:
							{
								tmpSb.append(",");
								break;
							}
						}
					}
					
					tmpSb.deleteCharAt(tmpSb.length()-1);
					String tmpCategoryLine = tmpSb.toString();
					String foldedCategoryLine = VCardUtils.foldLine(tmpCategoryLine, eol, foldingScheme);
					sb.append(foldedCategoryLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("CategoriesFeature ("+VCardType.CATEGORIES.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("CategoriesFeature ("+VCardType.CATEGORIES.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the class feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param categoriesFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the categories feature is null
	 */
	private void buildClassFeature(StringBuilder sb, ClassFeature classFeature) throws VCardBuildException {
		try {
			if(classFeature != null) {
				if(classFeature.hasSecurityClass()) {
					boolean isQuotedPrintable = classFeature.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(classFeature.hasGroup()) {
						tmpSb.append(classFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(classFeature.getTypeString());
					
					if(classFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(classFeature.getCharset().name());
					}
					
					if(classFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(classFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(classFeature.getSecurityClass(), isQuotedPrintable, classFeature.getCharset()));
					
					String tmpClassLine = tmpSb.toString();
					String foldedClassLine = VCardUtils.foldLine(tmpClassLine, eol, foldingScheme);
					sb.append(foldedClassLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("ClassFeature ("+VCardType.CLASS.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("ClassFeature ("+VCardType.CLASS.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a product id feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param productIdFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the product id feature is null
	 */
	private void buildProductIdFeature(StringBuilder sb, ProductIdFeature productIdFeature) throws VCardBuildException {
		try {
			if(productIdFeature != null) {
				if(productIdFeature.hasProductId()) {
					boolean isQuotedPrintable = productIdFeature.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(productIdFeature.hasGroup()) {
						tmpSb.append(productIdFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(productIdFeature.getTypeString());
					
					if(productIdFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(productIdFeature.getCharset().name());
					}
					
					if(productIdFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(productIdFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(productIdFeature.getProductId(), isQuotedPrintable, productIdFeature.getCharset()));
					
					String tmpProductIdLine = tmpSb.toString();
					String foldedProductIdLine = VCardUtils.foldLine(tmpProductIdLine, eol, foldingScheme);
					sb.append(foldedProductIdLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("ProductIdFeature ("+VCardType.PRODID.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("ProductIdFeature ("+VCardType.PRODID.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a sort string feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param sortStringFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the sort string feature is null
	 */
	private void buildSortStringFeature(StringBuilder sb, SortStringFeature sortStringFeature) throws VCardBuildException {
		try {
			if(sortStringFeature != null) {
				if(sortStringFeature.hasSortString()) {
					boolean isQuotedPrintable = sortStringFeature.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(sortStringFeature.hasGroup()) {
						tmpSb.append(sortStringFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(sortStringFeature.getTypeString());
					
					if(sortStringFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(sortStringFeature.getCharset().name());
					}
					
					if(sortStringFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(sortStringFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(sortStringFeature.getSortString(), isQuotedPrintable, sortStringFeature.getCharset()));
					
					String tmpSortStringLine = tmpSb.toString();
					String foldedSortStringLine = VCardUtils.foldLine(tmpSortStringLine, eol, foldingScheme);
					sb.append(foldedSortStringLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("SortStringFeature ("+VCardType.SORT_STRING.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("SortStringFeature ("+VCardType.SORT_STRING.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Build the key feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param keyFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the key feature is null
	 */
	private void buildKeyFeature(StringBuilder sb, KeyFeature keyFeature) throws VCardBuildException {
		try {
			if(keyFeature != null) {
				if(keyFeature.hasKey()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(keyFeature.hasGroup()) {
						tmpSb.append(keyFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(keyFeature.getTypeString());
					
					if(keyFeature.hasCharset()) {
						tmpSb.append("CHARSET=");
						tmpSb.append(keyFeature.getCharset().name());
					}
					
					tmpSb.append(";");
					
					String foldedKeyLine = null;
					tmpSb.append("ENCODING=");
					
					switch(compatMode)
					{
						case MS_OUTLOOK:
						{
							tmpSb.append(EncodingType.BASE64.getType());
							break;
						}
						
						case MAC_ADDRESS_BOOK:
						case EVOLUTION:
						case KDE_ADDRESS_BOOK:
						case RFC2426:
						case I_PHONE:
						{
							tmpSb.append(keyFeature.getEncodingType().getType());
							break;
						}
					}
					
					if(keyFeature.hasKeyTextType()) {
						tmpSb.append(";");
						tmpSb.append("TYPE=");
						tmpSb.append(keyFeature.getKeyTextType().getTypeName());
					}
					
					tmpSb.append(":");
					
					switch(compatMode)
					{
						case MS_OUTLOOK:
						{
							String b64str = null;
							try {
								byte[] keyBytes = keyFeature.getKey();
								if(keyFeature.isSetCompression()) {
									b64str = Base64Wrapper.encode(keyBytes, Base64Wrapper.OPTIONS.GZIP_COMPRESSION);
								}
								else {
									b64str = Base64Wrapper.encode(keyBytes, Base64Wrapper.OPTIONS.NO_COMPRESSION);
								}
							}
							catch(Exception ex) {
								throw new VCardBuildException(ex.getMessage(), ex);
							}
							
							String tmpKeyLine = tmpSb.toString();
							String foldedKeyLine2 = VCardUtils.foldLine(tmpKeyLine, eol, binaryFoldingScheme);
							
							foldedKeyLine = VCardUtils.foldLine(b64str, eol, binaryFoldingScheme);
							sb.append(foldedKeyLine2);					//Type declaration with param types
							sb.append(eol);								//Distinctive line break
							sb.append(binaryFoldingScheme.getIndent());	//Indent first line
							break;
						}
						
						case MAC_ADDRESS_BOOK:
						case EVOLUTION:
						case KDE_ADDRESS_BOOK:
						case RFC2426:
						case I_PHONE:
						{
							try {
								byte[] keyBytes = keyFeature.getKey();
								if(keyFeature.isSetCompression()) {
									tmpSb.append(Base64Wrapper.encode(keyBytes, Base64Wrapper.OPTIONS.GZIP_COMPRESSION));
								}
								else {
									tmpSb.append(Base64Wrapper.encode(keyBytes, Base64Wrapper.OPTIONS.NO_COMPRESSION));
								}
							}
							catch(Exception ex) {
								throw new VCardBuildException(ex.getMessage(), ex);
							}
							
							String tmpKeyLine = tmpSb.toString();
							foldedKeyLine = VCardUtils.foldLine(tmpKeyLine, eol, binaryFoldingScheme);
							break;
						}
					}
					
					sb.append(foldedKeyLine);
					
					switch(compatMode)
					{
						case MS_OUTLOOK:
						{
							sb.append(eol);
							sb.append(eol);
							break;
						}
							
						case RFC2426:
						case EVOLUTION:
						case KDE_ADDRESS_BOOK:
						case I_PHONE:
						case MAC_ADDRESS_BOOK:
						{
							sb.append(eol);
							break;
						}
					}
				}
				else {
					throw new VCardBuildException("KeyFeature ("+VCardType.KEY.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("KeyFeature ("+VCardType.KEY.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a photo feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param photoFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the photo feature is null
	 */
	private void buildPhotoFeature(StringBuilder sb, PhotoFeature photoFeature) throws VCardBuildException {
		try {
			if(photoFeature != null) {
				if(photoFeature.hasPhoto()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(photoFeature.hasGroup()) {
						tmpSb.append(photoFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(photoFeature.getTypeString());
					tmpSb.append(";");
					
					String foldedPhotoLine = null;
					
					if(photoFeature.hasCharset()) {
						tmpSb.append("CHARSET=");
						tmpSb.append(photoFeature.getCharset().name());
						tmpSb.append(";");
					}
					
					if(photoFeature.isURI()) {
						tmpSb.append("VALUE=URI:");
						tmpSb.append(photoFeature.getPhotoURI().toString());
						
						String tmpPhotoLine = tmpSb.toString();
						foldedPhotoLine = VCardUtils.foldLine(tmpPhotoLine, eol, foldingScheme);
					}
					else if(photoFeature.isInline()) {
						tmpSb.append("ENCODING=");
						
						switch(compatMode)
						{
							case MS_OUTLOOK:
							{
								tmpSb.append(EncodingType.BASE64.getType());
								break;
							}
							
							case MAC_ADDRESS_BOOK:
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case RFC2426:
							case I_PHONE:
							{
								tmpSb.append(photoFeature.getEncodingType().getType());
								break;
							}
						}
						
						if(photoFeature.hasImageMediaType()) {
							tmpSb.append(";");
							tmpSb.append("TYPE=");
							tmpSb.append(photoFeature.getImageMediaType().getTypeName());
						}

						tmpSb.append(":");
						
						switch(compatMode)
						{
							case MS_OUTLOOK:
							{
								String b64str = null;
								try {
									byte[] photoBytes = photoFeature.getPhoto();
									if(photoFeature.isSetCompression()) {
										b64str = Base64Wrapper.encode(photoBytes, Base64Wrapper.OPTIONS.GZIP_COMPRESSION);
									}
									else {
										b64str = Base64Wrapper.encode(photoBytes, Base64Wrapper.OPTIONS.NO_COMPRESSION);
									}
								}
								catch(Exception ex) {
									throw new VCardBuildException(ex.getMessage(), ex);
								}
								
								String tmpPhotoLine = tmpSb.toString();
								String foldedPhotoLine2 = VCardUtils.foldLine(tmpPhotoLine, eol, binaryFoldingScheme);
								
								foldedPhotoLine = VCardUtils.foldLine(b64str, eol, binaryFoldingScheme);
								sb.append(foldedPhotoLine2);				//Type declaration with param types
								sb.append(eol);								//Distinctive line break
								sb.append(binaryFoldingScheme.getIndent());	//Indent first line
								break;
							}
							
							case MAC_ADDRESS_BOOK:
							case RFC2426:
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case I_PHONE:
							{
								try {
									byte[] photoBytes = photoFeature.getPhoto();
									if(photoFeature.isSetCompression()) {
										tmpSb.append(Base64Wrapper.encode(photoBytes, Base64Wrapper.OPTIONS.GZIP_COMPRESSION));
									}
									else {
										tmpSb.append(Base64Wrapper.encode(photoBytes, Base64Wrapper.OPTIONS.NO_COMPRESSION));
									}
								}
								catch(Exception ex) {
									throw new VCardBuildException(ex.getMessage(), ex);
								}
								
								String tmpPhotoLine = tmpSb.toString();
								foldedPhotoLine = VCardUtils.foldLine(tmpPhotoLine, eol, binaryFoldingScheme);
								break;
							}
						}
					}
					else {
						throw new VCardBuildException("PhotoFeature ("+VCardType.PHOTO.getType()+") is not URI and not Inline, cannot proceed, must be one or the other.");
					}
					
					sb.append(foldedPhotoLine);
					
					switch(compatMode)
					{
						case MS_OUTLOOK:
						{
							sb.append(eol);
							sb.append(eol);
							break;
						}
							
						case RFC2426:
						case EVOLUTION:
						case KDE_ADDRESS_BOOK:
						case I_PHONE:
						case MAC_ADDRESS_BOOK:
						{
							sb.append(eol);
							break;
						}
					}
				}
				else {
					throw new VCardBuildException("PhotoFeature ("+VCardType.PHOTO.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("PhotoFeature ("+VCardType.PHOTO.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a logo feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param logoFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the logo feature is null
	 */
	private void buildLogoFeature(StringBuilder sb, LogoFeature logoFeature) throws VCardBuildException {
		try {
			if(logoFeature != null) {
				if(logoFeature.hasLogo()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(logoFeature.hasGroup()) {
						tmpSb.append(logoFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(logoFeature.getTypeString());
					tmpSb.append(";");
					
					String foldedLogoLine = null;
					
					if(logoFeature.hasCharset()) {
						tmpSb.append("CHARSET=");
						tmpSb.append(logoFeature.getCharset().name());
						tmpSb.append(";");
					}
					
					if(logoFeature.isURI()) {
						tmpSb.append("VALUE=URI:");
						tmpSb.append(logoFeature.getLogoURI().getPath());
						
						String tmpLogoLine = tmpSb.toString();
						foldedLogoLine = VCardUtils.foldLine(tmpLogoLine, eol, foldingScheme);
					}
					else if(logoFeature.isInline()) {
						tmpSb.append("ENCODING=");
						
						switch(compatMode)
						{
							case MS_OUTLOOK:
							{
								tmpSb.append(EncodingType.BASE64.getType());
								break;
							}
								
							case MAC_ADDRESS_BOOK:
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case RFC2426:
							case I_PHONE:
							{
								tmpSb.append(logoFeature.getEncodingType().getType());
								break;
							}
						}
						
						if(logoFeature.hasImageMediaType()) {
							tmpSb.append(";");
							tmpSb.append("TYPE=");
							tmpSb.append(logoFeature.getImageMediaType().getTypeName());
						}

						tmpSb.append(":");
						
						switch(compatMode)
						{
							case MS_OUTLOOK:
							{
								String b64str = null;
								try {
									byte[] logoBytes = logoFeature.getLogo();
									if(logoFeature.isSetCompression()) {
										b64str = Base64Wrapper.encode(logoBytes, Base64Wrapper.OPTIONS.GZIP_COMPRESSION);
									}
									else {
										b64str = Base64Wrapper.encode(logoBytes, Base64Wrapper.OPTIONS.NO_COMPRESSION);
									}
								}
								catch(Exception ex) {
									throw new VCardBuildException(ex.getMessage(), ex);
								}
								
								String tmpLogoLine = tmpSb.toString();
								String foldedPhotoLine2 = VCardUtils.foldLine(tmpLogoLine, eol, binaryFoldingScheme);
								
								foldedLogoLine = VCardUtils.foldLine(b64str, binaryFoldingScheme);
								sb.append(foldedPhotoLine2);				//Type declaration with param types
								sb.append(eol);								//Distinctive line break
								sb.append(binaryFoldingScheme.getIndent());	//Indent first line
								break;
							}
							
							case MAC_ADDRESS_BOOK:
							case RFC2426:
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case I_PHONE:
							{
								try {
									byte[] logoBytes = logoFeature.getLogo();
									if(logoFeature.isSetCompression()) {
										tmpSb.append(Base64Wrapper.encode(logoBytes, Base64Wrapper.OPTIONS.GZIP_COMPRESSION));
									}
									else {
										tmpSb.append(Base64Wrapper.encode(logoBytes, Base64Wrapper.OPTIONS.NO_COMPRESSION));
									}
								}
								catch(Exception ex) {
									throw new VCardBuildException(ex.getMessage(), ex);
								}
								
								String tmpLogoLine = tmpSb.toString();
								foldedLogoLine = VCardUtils.foldLine(tmpLogoLine, eol, binaryFoldingScheme);
								break;
							}
						}
					}
					else {
						throw new VCardBuildException("LogoFeature ("+VCardType.LOGO.getType()+") is not URI and not Inline, cannot proceed, must be one or the other.");
					}
					
					sb.append(foldedLogoLine);

					switch(compatMode)
					{
						case MS_OUTLOOK:
						{
							sb.append(eol);
							sb.append(eol);
							break;
						}
							
						case RFC2426:
						case EVOLUTION:
						case KDE_ADDRESS_BOOK:
						case I_PHONE:
						case MAC_ADDRESS_BOOK:
						{
							sb.append(eol);
							break;
						}
					}
				}
				else {
					throw new VCardBuildException("LogoFeature ("+VCardType.LOGO.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("LogoFeature ("+VCardType.LOGO.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a sound feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param soundFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the sound feature is null
	 */
	private void buildSoundFeature(StringBuilder sb, SoundFeature soundFeature) throws VCardBuildException {
		try {
			if(soundFeature != null) {
				if(soundFeature.hasSound()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(soundFeature.hasGroup()) {
						tmpSb.append(soundFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(soundFeature.getTypeString());
					tmpSb.append(";");
					
					String foldedSoundLine = null;
					
					if(soundFeature.hasCharset()) {
						tmpSb.append("CHARSET=");
						tmpSb.append(soundFeature.getCharset().name());
						tmpSb.append(";");
					}
					
					if(soundFeature.isURI()) {
						tmpSb.append("VALUE=URI:");
						tmpSb.append(soundFeature.getSoundURI().getPath());
						
						String tmpSoundLine = tmpSb.toString();
						foldedSoundLine = VCardUtils.foldLine(tmpSoundLine, eol, foldingScheme);
					}
					else if(soundFeature.isInline()) {
						tmpSb.append("ENCODING=");
						
						switch(compatMode)
						{
							case MS_OUTLOOK:
							{
								tmpSb.append(EncodingType.BASE64.getType());
								break;
							}
							
							case MAC_ADDRESS_BOOK:
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case RFC2426:
							case I_PHONE:
							{
								tmpSb.append(soundFeature.getEncodingType().getType());
								break;
							}
						}
						
						if(soundFeature.hasAudioMediaType()) {
							tmpSb.append(";");
							tmpSb.append("TYPE=");
							tmpSb.append(soundFeature.getAudioMediaType().getTypeName());
						}

						tmpSb.append(":");
						
						switch(compatMode)
						{
							case MS_OUTLOOK:
							{
								String b64str = null;
								try {
									byte[] soundBytes = soundFeature.getSound();
									if(soundFeature.isSetCompression()) {
										b64str = Base64Wrapper.encode(soundBytes, Base64Wrapper.OPTIONS.GZIP_COMPRESSION);
									}
									else {
										b64str = Base64Wrapper.encode(soundBytes, Base64Wrapper.OPTIONS.NO_COMPRESSION);
									}
								}
								catch(Exception ex) {
									throw new VCardBuildException(ex.getMessage(), ex);
								}
								
								String tmpSoundLine = tmpSb.toString();
								String foldedSoundLine2 = VCardUtils.foldLine(tmpSoundLine, eol, binaryFoldingScheme);
								
								foldedSoundLine = VCardUtils.foldLine(b64str, eol, binaryFoldingScheme);
								sb.append(foldedSoundLine2);				//Type declaration with param types
								sb.append(eol);								//Distinctive line break
								sb.append(binaryFoldingScheme.getIndent());	//Indent first line
								break;
							}
							
							case MAC_ADDRESS_BOOK:
							case RFC2426:
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case I_PHONE:
							{
								try {
									byte[] soundBytes = soundFeature.getSound();
									if(soundFeature.isSetCompression()) {
										tmpSb.append(Base64Wrapper.encode(soundBytes, Base64Wrapper.OPTIONS.GZIP_COMPRESSION));
									}
									else {
										tmpSb.append(Base64Wrapper.encode(soundBytes, Base64Wrapper.OPTIONS.NO_COMPRESSION));
									}
								}
								catch(Exception ex) {
									throw new VCardBuildException(ex.getMessage(), ex);
								}
								
								String tmpSoundLine = tmpSb.toString();
								foldedSoundLine = VCardUtils.foldLine(tmpSoundLine, eol, binaryFoldingScheme);
								break;
							}
						}
					}
					else {
						throw new VCardBuildException("SoundFeature ("+VCardType.SOUND.getType()+") is not URI and not Inline, cannot proceed, must be one or the other.");
					}

					sb.append(foldedSoundLine);
					
					switch(compatMode)
					{
						case MS_OUTLOOK:
						{
							sb.append(eol);
							sb.append(eol);
							break;
						}
							
						case RFC2426:
						case EVOLUTION:
						case KDE_ADDRESS_BOOK:
						case I_PHONE:
						case MAC_ADDRESS_BOOK:
						{
							sb.append(eol);
							break;
						}
					}
				}
				else {
					throw new VCardBuildException("SoundFeature ("+VCardType.SOUND.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("SoundFeature ("+VCardType.SOUND.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds an agent feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param agentFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the agent feature is null
	 */
	private void buildAgentFeature(StringBuilder sb, AgentFeature agentFeature) throws VCardBuildException {
		try {
			if(agentFeature != null) {
				if(agentFeature.hasAgent()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(agentFeature.hasGroup()) {
						tmpSb.append(agentFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(agentFeature.getTypeString());
					
					if(agentFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(agentFeature.getCharset().name());
					}
					
					if(agentFeature.isURI()) {
						tmpSb.append(";");
						tmpSb.append("VALUE=URI:");
						tmpSb.append(agentFeature.getAgentURI().getPath());
					}
					else if(agentFeature.isInline()) {
						tmpSb.append(":");
						VCard agentVCard = agentFeature.getAgent();
						if(agentVCard instanceof VCardErrorHandling) {
							//Turn on error handling if available
							((VCardErrorHandling)agentVCard).setThrowExceptions(true);
						}
						
						try {
							VCardWriter writer = new VCardWriter();
							writer.setCompatibilityMode(this.compatMode);
							writer.setFoldingScheme(this.foldingScheme);
							writer.setOutputVersion(this.outputVersion);
							writer.setVCard(agentVCard);
							String agentVCardStr = writer.buildVCardString();
							
							tmpSb.append(VCardUtils.escapeString(agentVCardStr));
						}
						catch(VCardException ve) {
							throw new VCardBuildException(ve.getMessage(), ve);
						}
						catch(Exception ex) {
							throw new VCardBuildException(ex.getMessage(), ex);
						}
						
						String tmpAgentLine = tmpSb.toString();
						String foldedAgentLine = VCardUtils.foldLine(tmpAgentLine, eol, foldingScheme);
						sb.append(foldedAgentLine);
						sb.append(eol);
					}
					else {
						throw new VCardBuildException("AgentFeature ("+VCardType.AGENT.getType()+") is not URI and not Inline, cannot proceed, must be one or the other.");
					}
				}
				else {
					throw new VCardBuildException("AgentFeature ("+VCardType.AGENT.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("AgentFeature ("+VCardType.AGENT.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds an extended feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param extendedFeature
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the extended feature is null
	 */
	private void buildExtendedFeature(StringBuilder sb, ExtendedFeature extendedFeature) throws VCardBuildException {
		try {
			if(extendedFeature != null) {
				if(extendedFeature.hasExtension()) {
					boolean isQuotedPrintable = extendedFeature.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(extendedFeature.hasGroup()) {
						tmpSb.append(extendedFeature.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(extendedFeature.getExtensionName());
					
					if(extendedFeature.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(extendedFeature.getCharset().name());
					}
					
					if(extendedFeature.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(extendedFeature.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeAndEncode(extendedFeature.getExtensionData(), isQuotedPrintable, extendedFeature.getCharset()));
					
					String tmpExtendedLine = tmpSb.toString();
					String foldedExtendedLine = VCardUtils.foldLine(tmpExtendedLine, eol, foldingScheme);
					sb.append(foldedExtendedLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("ExtendedFeature ("+extendedFeature.getExtensionName()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("ExtendedFeature ("+VCardType.XTENDED.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Helper method to escape string and encode to QUOTED-PRINTABLE if needed.
	 * Here we lazy create the codec only if we actually need it. The codec needs
	 * a charset to be defined, should it be null then it will use the default charset
	 * of the system.</p>
	 *
	 * @param str
	 * @param isQuotedPrintable
	 * @return {@link String}
	 * @throws EncoderException
	 * @throws UnsupportedEncodingException 
	 */
	private String escapeAndEncode(String str, boolean isQuotedPrintable, Charset charset) throws EncoderException, UnsupportedEncodingException
	{
		String str2 = VCardUtils.escapeString(str);
		
		if(charset == null) {
			charset = Charset.defaultCharset();
		}
		
		if(isQuotedPrintable) {
			if(qpCodec == null) {
				qpCodec = new QuotedPrintableCodec();
			}
			
			return qpCodec.encode(str2, charset.name());
		}
		else {
			return str2;
		}
	}
	
	/**
	 * <p>Returns true if the underlying vcard produced errors that
	 * are storable inside the vcard itself. False if no errors were
	 * detected and if the underlying vcard does not implement the
	 * {@link VCardErrorHandling} interface.</p>
	 *
	 * @return boolean
	 */
	public boolean hasErrors()
	{
		if(vcard instanceof VCardErrorHandling) {
			return ((VCardErrorHandling)vcard).hasErrors();
		}
		else {
			return false;
		}
	}
	
	/**
	 * <p>Handles the occurrence of an error where there only
	 * exists an error message and an associated severity.</p>
	 *
	 * @param errorMessage
	 * 	- The error message
	 * @param severity
	 * 	- The severity of the error
	 */
	@SuppressWarnings("unused")
	private void handleError(String errorMessage, ErrorSeverity severity) {
		handleError(errorMessage, null, severity);
	}
	
	/**
	 * <p>Handles the occurrence of an error where an exception
	 * is thrown and needs to be tracked and associated with a severity.</p>
	 *
	 * @param errorMessage
	 * 	- The error message
	 * @param exception
	 * 	- The exception that was thrown
	 * @param severity
	 * 	- The severity of the exception
	 */
	private void handleError(String errorMessage, Throwable exception, ErrorSeverity severity) {
		VCardError vError = new VCardError();
		vError.setErrorMessage(errorMessage);
		vError.setSeverity(severity);
		
		if(exception != null) {
			vError.setError(exception);
		}
		
		((VCardErrorHandling)vcard).addError(vError);
	}
	
	/**
	 * <p>Resets the VCardWriter to its initial defaults.
	 * Clears all data and errors.</p>
	 */
	public void reset() {
		setOutputVersion(null);
		setFoldingScheme(null);
		setBinaryfoldingScheme(null);
		setCompatibilityMode(null);
		setEOL(null);
		this.vcard = null;
	}
}
