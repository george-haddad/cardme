package net.sourceforge.cardme.io;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import net.sourceforge.cardme.util.Base64Wrapper;
import net.sourceforge.cardme.util.ISOFormat;
import net.sourceforge.cardme.util.ISOUtils;
import net.sourceforge.cardme.util.VCardUtils;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.arch.EncodingType;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.arch.VCardVersion;
import net.sourceforge.cardme.vcard.errors.ErrorSeverity;
import net.sourceforge.cardme.vcard.errors.VCardError;
import net.sourceforge.cardme.vcard.errors.VCardErrorHandler;
import net.sourceforge.cardme.vcard.exceptions.VCardBuildException;
import net.sourceforge.cardme.vcard.exceptions.VCardException;
import net.sourceforge.cardme.vcard.features.NameFeature;
import net.sourceforge.cardme.vcard.types.AbstractVCardType;
import net.sourceforge.cardme.vcard.types.AdrType;
import net.sourceforge.cardme.vcard.types.AgentType;
import net.sourceforge.cardme.vcard.types.BDayType;
import net.sourceforge.cardme.vcard.types.BeginType;
import net.sourceforge.cardme.vcard.types.CategoriesType;
import net.sourceforge.cardme.vcard.types.ClassType;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.EndType;
import net.sourceforge.cardme.vcard.types.ExtendedType;
import net.sourceforge.cardme.vcard.types.FNType;
import net.sourceforge.cardme.vcard.types.GeoType;
import net.sourceforge.cardme.vcard.types.ImppType;
import net.sourceforge.cardme.vcard.types.KeyType;
import net.sourceforge.cardme.vcard.types.LabelType;
import net.sourceforge.cardme.vcard.types.LogoType;
import net.sourceforge.cardme.vcard.types.MailerType;
import net.sourceforge.cardme.vcard.types.NType;
import net.sourceforge.cardme.vcard.types.NameType;
import net.sourceforge.cardme.vcard.types.NicknameType;
import net.sourceforge.cardme.vcard.types.NoteType;
import net.sourceforge.cardme.vcard.types.OrgType;
import net.sourceforge.cardme.vcard.types.PhotoType;
import net.sourceforge.cardme.vcard.types.ProdIdType;
import net.sourceforge.cardme.vcard.types.ProfileType;
import net.sourceforge.cardme.vcard.types.RevType;
import net.sourceforge.cardme.vcard.types.RoleType;
import net.sourceforge.cardme.vcard.types.SortStringType;
import net.sourceforge.cardme.vcard.types.SoundType;
import net.sourceforge.cardme.vcard.types.SourceType;
import net.sourceforge.cardme.vcard.types.TelType;
import net.sourceforge.cardme.vcard.types.TitleType;
import net.sourceforge.cardme.vcard.types.TzType;
import net.sourceforge.cardme.vcard.types.UidType;
import net.sourceforge.cardme.vcard.types.UrlType;
import net.sourceforge.cardme.vcard.types.VersionType;
import net.sourceforge.cardme.vcard.types.params.AdrParamType;
import net.sourceforge.cardme.vcard.types.params.BDayParamType;
import net.sourceforge.cardme.vcard.types.params.EmailParamType;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.ImppParamType;
import net.sourceforge.cardme.vcard.types.params.LabelParamType;
import net.sourceforge.cardme.vcard.types.params.TelParamType;
import net.sourceforge.cardme.vcard.types.params.TzParamType;
import net.sourceforge.cardme.vcard.types.params.UrlParamType;
import org.apache.commons.codec.EncoderException;
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
	 * to a textual .VCF formatted string.</p>
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
	 * <p>This forces cardme to make one extra pass on
	 * a quoted-printable string converting all space
	 * characters to =20.</p>
	 */
	private boolean forceEncodeQuotedPrintableSpaces = false;
	
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
		
		if(vcard instanceof VCardErrorHandler) {
			isThrowsExceptions = ((VCardErrorHandler)vcard).isThrowExceptions();
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
				//TODO add in support for 2.1
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
	 * 	<li><strong>LF or <code>\n</code></strong>: Unix, GNU/Linux, AIX, Xenix, Mac OS X, FreeBSD, BeOS, Amiga, and RISC OS</li>
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
	 * <p>Forces cardme to make one extra pass on a quoted-printable
	 * string encoding all spaces to =20.</p>
	 *
	 * @param force
	 */
	public void setForceEncodeQuotedPrintableSpaces(boolean force) {
		this.forceEncodeQuotedPrintableSpaces = force;
	}
	
	/**
	 * <p>Constructs a String in a suitable format to be written out
	 * as a .VCF file. This method will throw {@link VCardException}s only
	 * if the given VCard is set to throw exceptions. If not then exceptions
	 * are silently collected.</p>
	 *
	 * @throws VCardBuildException
	 * @return {@link String}
	 */
	public String buildVCardString() throws VCardBuildException
	{
		if(vcard == null) {
			throw new VCardBuildException("Cannot build a null VCard.");
		}
		
		StringBuilder sb = new StringBuilder();
		
		/*
		 * Begin
		 * Must be present.
		 */
		try {
			buildBeginType(sb, vcard.getBegin());
		}
		catch(VCardBuildException vbe) {
			if(isThrowsExceptions) {
				throw new VCardBuildException(vbe.getMessage(), vbe);
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
			buildVersionType(sb, vcard.getVersion());
		}
		catch(VCardBuildException vbe) {
			if(isThrowsExceptions) {
				throw new VCardBuildException(vbe.getMessage(), vbe);
			}
			else {
				handleError(vbe.getMessage(), vbe, ErrorSeverity.FATAL);
			}
		}
		
		/*
		 * N
		 * Must be present.
		 */
		try {
			buildNType(sb, vcard.getN());
		}
		catch(VCardBuildException vbe) {
			if(isThrowsExceptions) {
				throw new VCardBuildException(vbe.getMessage(), vbe);
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
			buildFNType(sb, vcard.getFN());
		}
		catch(VCardBuildException vbe) {
			if(isThrowsExceptions) {
				throw new VCardBuildException(vbe.getMessage(), vbe);
			}
			else {
				handleError(vbe.getMessage(), vbe, ErrorSeverity.FATAL);
			}
		}
		
		/*
		 * Name
		 */
		if(vcard.hasName()) {
			try {
				buildNameType(sb, vcard.getName());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
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
				buildProfileType(sb, vcard.getProfile());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
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
				buildSourceType(sb, vcard.getSource());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
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
				buildNicknameType(sb, vcard.getNicknames());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
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
				buildTitleType(sb, vcard.getTitle());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
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
				buildRoleType(sb, vcard.getRole());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Geographic Position
		 */
		if(vcard.hasGeo()) {
			try {
				buildGeoType(sb, vcard.getGeo());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Organizations
		 */
		if(vcard.hasOrg()) {
			try {
				buildOrgType(sb, vcard.getOrg());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
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
				buildMailerType(sb, vcard.getMailer());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Time Zone
		 */
		if(vcard.hasTz()) {
			try {
				buildTzType(sb, vcard.getTz());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * URL
		 */
		if(vcard.hasUrls()) {
			List<UrlType> urls = vcard.getUrls();
			for(UrlType urlType : urls) {
				try {
					buildUrlType(sb, urlType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
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
		if(vcard.hasRev()) {
			try {
				buildRevType(sb, vcard.getRev());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * UID
		 */
		if(vcard.hasUid()) {
			try {
				buildUidType(sb, vcard.getUid());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Birthday
		 */
		if(vcard.hasBDay()) {
			try {
				buildBDayType(sb, vcard.getBDay());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Addresses & Labels
		 */
		if(vcard.hasAdrs()) {
			List<AdrType> addresses = vcard.getAdrs();
			for(AdrType adrType : addresses) {
				try {
					buildAdrType(sb, adrType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
				
				if(adrType.hasLabel()) {
					LabelType label = adrType.getLabel();
					
					try {
						buildLabelType(sb, label);
					}
					catch(VCardBuildException vbe) {
						if(isThrowsExceptions) {
							throw new VCardBuildException(vbe.getMessage(), vbe);
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
		if(vcard.hasTels()) {
			List<TelType> telephones = vcard.getTels();
			for(TelType telType : telephones) {
				try {
					buildTelType(sb, telType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
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
			List<EmailType> emails = vcard.getEmails();
			for(EmailType emailType : emails) {
				try {
					buildEmailType(sb, emailType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
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
			List<NoteType> notes = vcard.getNotes();
			for(NoteType noteType : notes) {
				try {
					buildNoteType(sb, noteType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
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
				buildCategoriesType(sb, vcard.getCategories());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
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
				buildClassType(sb, vcard.getSecurityClass());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
				}
				else {
					handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
				}
			}
		}
		
		/*
		 * Product ID
		 */
		if(vcard.hasProdId()) {
			try {
				buildProdIdType(sb, vcard.getProdId());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
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
				buildSortStringType(sb, vcard.getSortString());
			}
			catch(VCardBuildException vbe) {
				if(isThrowsExceptions) {
					throw new VCardBuildException(vbe.getMessage(), vbe);
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
			List<KeyType> keys = vcard.getKeys();
			for(KeyType keyType : keys) {
				try {
					buildKeyType(sb, keyType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
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
			List<PhotoType> photos = vcard.getPhotos();
			for(PhotoType photoType : photos) {
				try {
					buildPhotoType(sb, photoType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
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
			List<LogoType> logos = vcard.getLogos();
			for(LogoType logoType : logos) {
				try {
					buildLogoType(sb, logoType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
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
			List<SoundType> sounds = vcard.getSounds();
			for(SoundType soundType : sounds) {
				try {
					buildSoundType(sb, soundType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
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
			List<AgentType> agents = vcard.getAgents();
			for(AgentType agentType : agents) {
				try {
					buildAgentType(sb, agentType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
					}
					else {
						handleError(vbe.getMessage(), vbe, ErrorSeverity.WARNING);
					}
				}
			}
		}
		
		/*
		 * IMPP. RFC 4770 extension
		 */
		if(vcard.hasImpps()) {
			List<ImppType> impps = vcard.getIMPPs();
			for(ImppType imppType : impps) {
				try {
					buildImppType(sb, imppType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
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
			List<ExtendedType> extensions = vcard.getExtendedTypes();
			for(ExtendedType extendedType : extensions) {
				try {
					buildExtendedType(sb, extendedType);
				}
				catch(VCardBuildException vbe) {
					if(isThrowsExceptions) {
						throw new VCardBuildException(vbe.getMessage(), vbe);
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
			buildEndType(sb, vcard.getEnd());
		}
		catch(VCardBuildException vbe) {
			if(isThrowsExceptions) {
				throw new VCardBuildException(vbe.getMessage(), vbe);
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
	 * @param beginType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when begin feature is null
	 */
	private void buildBeginType(StringBuilder sb, BeginType beginType) throws VCardBuildException {
		try {
			if(beginType != null) {
				if(beginType.hasGroup()) {
					sb.append(beginType.getGroup());
					sb.append(".");
				}
				
				sb.append(beginType.getVCardTypeName().getType());
				sb.append(":VCARD");
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("Cannot continue because BeginType ("+VCardTypeName.BEGIN.getType()+") is null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("BeginType ("+VCardTypeName.BEGIN.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
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
	private void buildEndType(StringBuilder sb, EndType endType) throws VCardBuildException {
		try {
			if(endType != null) {
				if(endType.hasGroup()) {
					sb.append(endType.getGroup());
					sb.append(".");
				}
				
				sb.append(endType.getVCardTypeName().getType());
				sb.append(":VCARD");
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("Cannot continue because EndType ("+VCardTypeName.END.getType()+") is null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("EndType ("+VCardTypeName.END.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the version feature as a String.</p>
	 *
	 * @param sb
	 * 	- StringBuilder to append to
	 * @param versionType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when version feature is null
	 */
	private void buildVersionType(StringBuilder sb, VersionType versionType) throws VCardBuildException {
		try {
			if(versionType != null) {
				if(versionType.hasGroup()) {
					sb.append(versionType.getGroup());
					sb.append(".");
				}
				
				if(versionType.getVersion() == VCardVersion.V2_1) {
					//We do not support writing in v2.1, so if it has
					//been set before, force it to v3.0
					versionType.setVersion(VCardVersion.V3_0);
				}
				
				sb.append(versionType.getVCardTypeName().getType());
				sb.append(":");
				sb.append(versionType.getVersion().getVersion());
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("Cannot continue because VersionType ("+VCardTypeName.VERSION.getType()+") is null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("VersionType ("+VCardTypeName.VERSION.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the name feature as a String. The order in which the name feature is built
	 * is as follows: Family Name, Given Name, Additional Names, Honorific Prefixes, and Honorific Suffixes.
	 * More details of the exact syntax are described in the {@link NameFeature} class itself.</p>
	 *
	 * @param sb
	 * 	- StringBuilder to append to
	 * @param nType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the name feature is null
	 */
	private void buildNType(StringBuilder sb, NType nType) throws VCardBuildException {
		try {
			if(nType != null) {
				boolean isQuotedPrintable = nType.isQuotedPrintable();
				StringBuilder tmpSb = new StringBuilder();
				
				if(nType.hasGroup()) {
					tmpSb.append(nType.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(nType.getVCardTypeName().getType());
				
				if(nType.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(nType.getCharset().name());
				}
				
				if(nType.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(nType.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				if(nType.hasExtendedParams()) {
					buildExtendParameters(nType, tmpSb);
				}
				
				tmpSb.append(":");
				
				if(nType.hasFamilyName()) {
					tmpSb.append(escapeOrEncode(nType.getFamilyName(), isQuotedPrintable, nType.getCharset()));
				}

				tmpSb.append(";");

				if(nType.hasGivenName()) {
					tmpSb.append(escapeOrEncode(nType.getGivenName(), isQuotedPrintable, nType.getCharset()));
				}

				tmpSb.append(";");

				if(nType.hasAdditionalNames()) {
					List<String> additionalNames = nType.getAdditionalNames();
					for(String addName : additionalNames) {
						tmpSb.append(escapeOrEncode(addName, isQuotedPrintable, nType.getCharset()));
						tmpSb.append(",");
					}

					tmpSb.deleteCharAt(tmpSb.length()-1);	// remove trailing comma
				}

				tmpSb.append(";");

				if(nType.hasHonorificPrefixes()) {
					List<String> prefixes = nType.getHonorificPrefixes();
					for(String prefix : prefixes) {
						tmpSb.append(escapeOrEncode(prefix, isQuotedPrintable, nType.getCharset()));
						tmpSb.append(",");
					}

					tmpSb.deleteCharAt(tmpSb.length()-1);
				}

				tmpSb.append(";");

				if(nType.hasHonorificSuffixes()) {
					List<String> suffixes = nType.getHonorificSuffixes();
					for(String suffix : suffixes) {
						tmpSb.append(escapeOrEncode(suffix, isQuotedPrintable, nType.getCharset()));
						tmpSb.append(",");
					}

					tmpSb.deleteCharAt(tmpSb.length()-1);
				}

				String tmpNameLine = tmpSb.toString();
				String foldedNameLine = null;
				
				if(nType.isQuotedPrintable()) {
					foldedNameLine = VCardUtils.foldQuotedPrintableLine(tmpNameLine, foldingScheme);
				}
				else {
					foldedNameLine = VCardUtils.foldLine(tmpNameLine, eol, foldingScheme);
				}
				
				sb.append(foldedNameLine);
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("NType ("+VCardTypeName.N.getType()+") cannot be left null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("NType ("+VCardTypeName.N.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the formatted name feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param fnType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the formatted name feature is null
	 */
	private void buildFNType(StringBuilder sb, FNType fnType) throws VCardBuildException {
		try {
			if(fnType != null) {
				boolean isQuotedPrintable = fnType.isQuotedPrintable();
				String formattedName = fnType.getFormattedName();
				StringBuilder tmpSb = new StringBuilder();
				
				if(fnType.hasGroup()) {
					tmpSb.append(fnType.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(fnType.getVCardTypeName().getType());
				
				if(fnType.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(fnType.getCharset().name());
				}
				
				if(fnType.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(fnType.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				if(fnType.hasExtendedParams()) {
					buildExtendParameters(fnType, tmpSb);
				}
				
				tmpSb.append(":");
				tmpSb.append(escapeOrEncode(formattedName, isQuotedPrintable, fnType.getCharset()));
				
				String tmpFormattedNameLine = tmpSb.toString();
				String foldedFormattedNameLine = null;
				
				if(fnType.isQuotedPrintable()) {
					foldedFormattedNameLine = VCardUtils.foldQuotedPrintableLine(tmpFormattedNameLine, foldingScheme);
				}
				else {
					foldedFormattedNameLine = VCardUtils.foldLine(tmpFormattedNameLine, eol, foldingScheme);
				}
				
				sb.append(foldedFormattedNameLine);
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("FNType ("+VCardTypeName.FN.getType()+") cannot be left null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("FNType ("+VCardTypeName.FN.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the name as a String</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param nameType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the name feature is null
	 */
	private void buildNameType(StringBuilder sb, NameType nameType) throws VCardBuildException {
		try {
			if(nameType != null) {
				boolean isQuotedPrintable = nameType.isQuotedPrintable();
				String displayableName = nameType.getName();
				StringBuilder tmpSb = new StringBuilder();
				
				if(nameType.hasGroup()) {
					tmpSb.append(nameType.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(nameType.getVCardTypeName().getType());
				
				if(nameType.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(nameType.getCharset().name());
				}
				
				if(nameType.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(nameType.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				if(nameType.hasExtendedParams()) {
					buildExtendParameters(nameType, tmpSb);
				}
				
				tmpSb.append(":");
				tmpSb.append(escapeOrEncode(displayableName, isQuotedPrintable, nameType.getCharset()));
				
				String tmpDisplayableNameLine = tmpSb.toString();
				String foldedDisplayableNameLine = null;
				
				if(nameType.isQuotedPrintable()) {
					foldedDisplayableNameLine = VCardUtils.foldQuotedPrintableLine(tmpDisplayableNameLine, foldingScheme);
				}
				else {
					foldedDisplayableNameLine = VCardUtils.foldLine(tmpDisplayableNameLine, eol, foldingScheme);
				}
				
				sb.append(foldedDisplayableNameLine);
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("NameType ("+VCardTypeName.NAME.getType()+") cannot be left null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("NameType ("+VCardTypeName.NAME.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the profile as a String</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param profileType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the profile feature is null
	 */
	private void buildProfileType(StringBuilder sb, ProfileType profileType) throws VCardBuildException {
		try {
			if(profileType != null) {
				boolean isQuotedPrintable = profileType.isQuotedPrintable();
				String profile = profileType.getProfile();
				StringBuilder tmpSb = new StringBuilder();
				
				if(profileType.hasGroup()) {
					tmpSb.append(profileType.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(profileType.getVCardTypeName().getType());
				
				if(profileType.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(profileType.getCharset().name());
				}
				
				if(profileType.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(profileType.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				if(profileType.hasExtendedParams()) {
					buildExtendParameters(profileType, tmpSb);
				}
				
				tmpSb.append(":");
				tmpSb.append(escapeOrEncode(profile, isQuotedPrintable, profileType.getCharset()));
				
				String tmpProfileLine = tmpSb.toString();
				String foldedProfileLine = null;
				
				if(profileType.isQuotedPrintable()) {
					foldedProfileLine = VCardUtils.foldQuotedPrintableLine(tmpProfileLine, foldingScheme);
				}
				else {
					foldedProfileLine = VCardUtils.foldLine(tmpProfileLine, eol, foldingScheme);
				}
				
				sb.append(foldedProfileLine);
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("ProfileType ("+VCardTypeName.PROFILE.getType()+") cannot be left null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("ProfileType ("+VCardTypeName.PROFILE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the source as a String</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param sourceType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the source feature is null
	 */
	private void buildSourceType(StringBuilder sb, SourceType sourceType) throws VCardBuildException {
		try {
			if(sourceType != null) {
				boolean isQuotedPrintable = sourceType.isQuotedPrintable();
				String source = sourceType.getSource();
				StringBuilder tmpSb = new StringBuilder();
				
				if(sourceType.hasGroup()) {
					tmpSb.append(sourceType.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(sourceType.getVCardTypeName().getType());
				
				if(sourceType.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(sourceType.getCharset().name());
				}
				
				if(sourceType.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(sourceType.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				if(sourceType.hasExtendedParams()) {
					buildExtendParameters(sourceType, tmpSb);
				}
				
				tmpSb.append(":");
				tmpSb.append(escapeOrEncode(source, isQuotedPrintable, sourceType.getCharset()));
				
				String tmpSourceLine = tmpSb.toString();
				String foldedSourceLine = null;
				
				if(sourceType.isQuotedPrintable()) {
					foldedSourceLine = VCardUtils.foldQuotedPrintableLine(tmpSourceLine, foldingScheme);
				}
				else {
					foldedSourceLine = VCardUtils.foldLine(tmpSourceLine, eol, foldingScheme);
				}
				
				sb.append(foldedSourceLine);
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("SourceType ("+VCardTypeName.SOURCE.getType()+") cannot be left null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("SourceType ("+VCardTypeName.SOURCE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the title feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param titleType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the title feature is null
	 */
	private void buildTitleType(StringBuilder sb, TitleType titleType) throws VCardBuildException {
		try {
			if(titleType != null) {
				if(titleType.hasTitle()) {
					boolean isQuotedPrintable = titleType.isQuotedPrintable();
					String title = titleType.getTitle();
					StringBuilder tmpSb = new StringBuilder();
					
					if(titleType.hasGroup()) {
						tmpSb.append(titleType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(titleType.getVCardTypeName().getType());
					
					if(titleType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(titleType.getCharset().name());
					}
					
					if(titleType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(titleType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(titleType.hasExtendedParams()) {
						buildExtendParameters(titleType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeOrEncode(title, isQuotedPrintable, titleType.getCharset()));
					
					String tmpTitleLine = tmpSb.toString();
					String foldedTitleLine = null;
					
					if(titleType.isQuotedPrintable()) {
						foldedTitleLine = VCardUtils.foldQuotedPrintableLine(tmpTitleLine, foldingScheme);
					}
					else {
						foldedTitleLine = VCardUtils.foldLine(tmpTitleLine, eol, foldingScheme);
					}
					
					sb.append(foldedTitleLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("TitleType ("+VCardTypeName.TITLE.getType()+") exists but is emtpy.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("TitleType ("+VCardTypeName.TITLE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the role feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param roleType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the role feature is null
	 */
	private void buildRoleType(StringBuilder sb, RoleType roleType) throws VCardBuildException {
		try {
			if(roleType != null) {
				if(roleType.hasRole()) {
					boolean isQuotedPrintable = roleType.isQuotedPrintable();
					String role = roleType.getRole();
					StringBuilder tmpSb = new StringBuilder();
					
					if(roleType.hasGroup()) {
						tmpSb.append(roleType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(roleType.getVCardTypeName().getType());
					
					if(roleType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(roleType.getCharset().name());
					}
					
					if(roleType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(roleType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(roleType.hasExtendedParams()) {
						buildExtendParameters(roleType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeOrEncode(role, isQuotedPrintable, roleType.getCharset()));
					
					String tmpRoleLine = tmpSb.toString();
					String foldedRoleLine = null;
					
					if(roleType.isQuotedPrintable()) {
						foldedRoleLine = VCardUtils.foldQuotedPrintableLine(tmpRoleLine, foldingScheme);
					}
					else {
						foldedRoleLine = VCardUtils.foldLine(tmpRoleLine, eol, foldingScheme);
					}
					
					sb.append(foldedRoleLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("RoleType ("+VCardTypeName.ROLE.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("RoleType ("+VCardTypeName.ROLE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the geographic position feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param geoType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the geographic position feature is null
	 */
	private void buildGeoType(StringBuilder sb, GeoType geoType) throws VCardBuildException {
		try {
			if(geoType != null) {
				StringBuilder tmpSb = new StringBuilder();
				
				if(geoType.hasGroup()) {
					tmpSb.append(geoType.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(geoType.getVCardTypeName().getType());
				
				if(geoType.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(geoType.getCharset().name());
				}
				
				if(geoType.hasExtendedParams()) {
					buildExtendParameters(geoType, tmpSb);
				}
				
				tmpSb.append(":");
				tmpSb.append(VCardUtils.getGeographicPositionFormatter().format(geoType.getLatitude()));
				tmpSb.append(";");
				tmpSb.append(VCardUtils.getGeographicPositionFormatter().format(geoType.getLongitude()));
				
				String tmpGeoLine = tmpSb.toString();
				String foldedGeoLine = VCardUtils.foldLine(tmpGeoLine, eol, foldingScheme);
				sb.append(foldedGeoLine);
				sb.append(eol);
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("GeoType ("+VCardTypeName.GEO.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the organization feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param orgType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the organization feature is null
	 */
	private void buildOrgType(StringBuilder sb, OrgType orgType) throws VCardBuildException {
		try {
			if(orgType != null) {
				if(orgType.hasOrgName()) {
					boolean isQuotedPrintable = orgType.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(orgType.hasGroup()) {
						tmpSb.append(orgType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(orgType.getVCardTypeName().getType());
					
					if(orgType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(orgType.getCharset().name());
					}
					
					if(orgType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(orgType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(orgType.hasExtendedParams()) {
						buildExtendParameters(orgType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeOrEncode(orgType.getOrgName(), isQuotedPrintable, orgType.getCharset()));
					
					if(orgType.hasOrgUnits()) {
						tmpSb.append(";");
						List<String> orgs = orgType.getOrgUnits();
						for(String orgUnit : orgs) {
							tmpSb.append(escapeOrEncode(orgUnit, isQuotedPrintable, orgType.getCharset()));
							tmpSb.append(";");
						}
						
						tmpSb.deleteCharAt(tmpSb.length()-1);
					}
					
					String tmpOrgLine = tmpSb.toString();
					String foldedOrgLine = null;
					
					if(orgType.isQuotedPrintable()) {
						foldedOrgLine = VCardUtils.foldQuotedPrintableLine(tmpOrgLine, foldingScheme);
					}
					else {
						foldedOrgLine = VCardUtils.foldLine(tmpOrgLine, eol, foldingScheme);
					}
					
					sb.append(foldedOrgLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("OrgType ("+VCardTypeName.ORG.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("OrgType ("+VCardTypeName.ORG.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the mailer feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param mailerType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the mailer feature is null
	 */
	private void buildMailerType(StringBuilder sb, MailerType mailerType) throws VCardBuildException {
		try {
			if(mailerType != null) {
				if(mailerType.hasMailer()) {
					boolean isQuotedPrintable = mailerType.isQuotedPrintable();
					String mailer = mailerType.getMailer();
					StringBuilder tmpSb = new StringBuilder();
					
					if(mailerType.hasGroup()) {
						tmpSb.append(mailerType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(mailerType.getVCardTypeName());
					
					if(mailerType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(mailerType.getCharset().name());
					}
					
					if(mailerType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(mailerType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(mailerType.hasExtendedParams()) {
						buildExtendParameters(mailerType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeOrEncode(mailer, isQuotedPrintable, mailerType.getCharset()));
					
					String tmpMailerLine = tmpSb.toString();
					String foldedMailerLine = null;
					
					if(mailerType.isQuotedPrintable()) {
						foldedMailerLine = VCardUtils.foldQuotedPrintableLine(tmpMailerLine, foldingScheme);
					}
					else {
						foldedMailerLine = VCardUtils.foldLine(tmpMailerLine, eol, foldingScheme);
					}
					
					sb.append(foldedMailerLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("MailerType ("+VCardTypeName.MAILER.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("MailerType ("+VCardTypeName.MAILER.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the time zone feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param tzType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the time zone feature is null
	 */
	private void buildTzType(StringBuilder sb, TzType tzType) throws VCardBuildException {
		try {
			if(tzType != null) {
				StringBuilder tmpSb = new StringBuilder();
				
				if(tzType.hasGroup()) {
					tmpSb.append(tzType.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(tzType.getVCardTypeName().getType());
				
				if(tzType.hasParamType()) {
					TzParamType paramType = tzType.getParamType();
					switch(paramType)
					{
						case TEXT:
						{
							tmpSb.append(";");
							tmpSb.append("VALUE=TEXT:");
							tmpSb.append(tzType.getIso8601Offset());
							tmpSb.append(';');
							
							if (tzType.getShortText() != null){
								tmpSb.append(tzType.getShortText());
							}
							
							tmpSb.append(';');
							
							if (tzType.getLongText() != null){
								tmpSb.append(tzType.getLongText());
							}
							
							break;
						}
						
						case UTC_OFFSET:
						{
							tmpSb.append(":");
							tmpSb.append(tzType.getIso8601Offset());
							break;
						}
					}
				}
				else {
					if(tzType.getShortText() != null || tzType.getLongText() != null) {
						tmpSb.append(";");
						tmpSb.append("VALUE=TEXT:");
						tmpSb.append(tzType.getIso8601Offset());
						tmpSb.append(';');
						
						if (tzType.getShortText() != null){
							tmpSb.append(tzType.getShortText());
						}
						
						tmpSb.append(';');
						
						if (tzType.getLongText() != null){
							tmpSb.append(tzType.getLongText());
						}
					}
					else {
						tmpSb.append(":");
						tmpSb.append(tzType.getIso8601Offset());
					}
				}
				

				String tmpTimeZoneLine = tmpSb.toString();
				String foldedTimeZoneLine = VCardUtils.foldLine(tmpTimeZoneLine, eol, foldingScheme);
				sb.append(foldedTimeZoneLine);
				sb.append(eol);
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("TzType ("+VCardTypeName.TZ.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the URL feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param urlType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the URL feature is null
	 */
	private void buildUrlType(StringBuilder sb, UrlType urlType) throws VCardBuildException {
		try {
			if(urlType != null) {
				if(urlType.hasRawUrl()) {
					boolean isQuotedPrintable = urlType.isQuotedPrintable();
					String url = urlType.getRawUrl();
					StringBuilder tmpSb = new StringBuilder();
					
					if(urlType.hasGroup()) {
						tmpSb.append(urlType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(urlType.getVCardTypeName().getType());
					
					switch(compatMode)
					{
						case MS_OUTLOOK:
						case I_PHONE:
						case GMAIL:
						case MAC_ADDRESS_BOOK:
						{
							if(urlType.hasParams()) {
								tmpSb.append(";");
								switch(urlType.getParameterTypeStyle())
								{
									case PARAMETER_LIST:
									{
										List<UrlParamType> paramTypes = urlType.getParams();
										for(UrlParamType urlParamType : paramTypes) {
											tmpSb.append("TYPE=");
											tmpSb.append(urlParamType.getType());
											tmpSb.append(";");
										}

										break;
									}

									case PARAMETER_VALUE_LIST:
									{
										tmpSb.append("TYPE=");
										List<UrlParamType> paramTypes = urlType.getParams();
										for(UrlParamType urlParamType : paramTypes) {
											tmpSb.append(urlParamType.getType());
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
					
					if(urlType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(urlType.getCharset().name());
					}
					
					if(urlType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(urlType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(urlType.hasExtendedParams()) {
						buildExtendParameters(urlType, tmpSb);
					}
					
					tmpSb.append(":");
					
					switch(compatMode)
					{
						case GMAIL:
						case I_PHONE:
						case IOS_EXPORTER:
						{
							tmpSb.append(VCardUtils.escapeString(url));
							break;
						}
						
						default:
						{
							tmpSb.append(url);
							break;
						}
					}
					
					String tmpUrlLine = tmpSb.toString();
					String foldedUrlLine = null;
					
					if(urlType.isQuotedPrintable()) {
						foldedUrlLine = VCardUtils.foldQuotedPrintableLine(tmpUrlLine, foldingScheme);
					}
					else {
						foldedUrlLine = VCardUtils.foldLine(tmpUrlLine, eol, foldingScheme);
					}
					
					sb.append(foldedUrlLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("UrlType ("+VCardTypeName.URL.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("UrlType ("+VCardTypeName.URL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the revision feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param revType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the revision feature is null
	 */
	private void buildRevType(StringBuilder sb, RevType revType) throws VCardBuildException {
		try {
			if(revType != null) {
				if(revType.hasRevision()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(revType.hasGroup()) {
						tmpSb.append(revType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(revType.getVCardTypeName().getType());
					
					if(revType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(revType.getCharset().name());
					}
					
					if(revType.hasExtendedParams()) {
						buildExtendParameters(revType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(ISOUtils.formatISO8601Date(revType.getRevision(), ISOFormat.ISO8601_UTC_TIME_EXTENDED));
					
					String tmpRevisionLine = tmpSb.toString();
					String foldedRevisionLine = VCardUtils.foldLine(tmpRevisionLine, eol, foldingScheme);
					
					sb.append(foldedRevisionLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("RevType ("+VCardTypeName.REV.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("RevType ("+VCardTypeName.REV.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the UID feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param uidType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the uid feature is null
	 */
	private void buildUidType(StringBuilder sb, UidType uidType) throws VCardBuildException {
		try {
			if(uidType != null) {
				if(uidType.hasUid()) {
					boolean isQuotedPrintable = uidType.isQuotedPrintable();
					String uid = uidType.getUid();
					StringBuilder tmpSb = new StringBuilder();
					
					if(uidType.hasGroup()) {
						tmpSb.append(uidType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(uidType.getVCardTypeName().getType());
					
					if(uidType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(uidType.getCharset().name());
					}
					
					if(uidType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(uidType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(uidType.hasExtendedParams()) {
						buildExtendParameters(uidType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeOrEncode(uid, isQuotedPrintable, uidType.getCharset()));
					
					String tmpUidLine = tmpSb.toString();
					String foldedUidLine = null;
					
					if(uidType.isQuotedPrintable()) {
						foldedUidLine = VCardUtils.foldQuotedPrintableLine(tmpUidLine, foldingScheme);
					}
					else {
						foldedUidLine = VCardUtils.foldLine(tmpUidLine, eol, foldingScheme);
					}
					
					sb.append(foldedUidLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("UidType ("+VCardTypeName.UID.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("UidType ("+VCardTypeName.UID.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the birthday feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param bdayType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the birthday feature is null
	 */
	private void buildBDayType(StringBuilder sb, BDayType bdayType) throws VCardBuildException {
		try {
			if(bdayType != null) {
				StringBuilder tmpSb = new StringBuilder();
				
				if(bdayType.hasGroup()) {
					tmpSb.append(bdayType.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(bdayType.getVCardTypeName().getType());
				
				if(bdayType.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(bdayType.getCharset().name());
				}
				
				if(bdayType.hasParam()) {
					BDayParamType ptype = bdayType.getParam();
					tmpSb.append(";VALUE=");
					tmpSb.append(ptype.getTypeName());
				}
				
				if(bdayType.hasExtendedParams()) {
					buildExtendParameters(bdayType, tmpSb);
				}
				
				ISOFormat isoFormat = bdayType.getISO8601Format();
				tmpSb.append(":");
				tmpSb.append(ISOUtils.formatISO8601Date(bdayType.getBirthday(), isoFormat));
				
				String tmpBdayLine = tmpSb.toString();
				String foldedBdayLine = VCardUtils.foldLine(tmpBdayLine, eol, foldingScheme);
				sb.append(foldedBdayLine);
				sb.append(eol);
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("BDayType ("+VCardTypeName.BDAY.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}

	/**
	 * <p>Builds the address feature as a String. The order of the address is built as follows:
	 * post office box; the extended address; the street address; the locality; the region; the postal code; the country name.
	 * The street address can contain multiple values separated by a comma.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param adrType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the address feature is null
	 */
	private void buildAdrType(StringBuilder sb, AdrType adrType) throws VCardBuildException {
		try {
			if(adrType != null) {
				boolean isQuotedPrintable = adrType.isQuotedPrintable();
				StringBuilder tmpSb = new StringBuilder();
				
				if(adrType.hasGroup()) {
					tmpSb.append(adrType.getGroup());
					tmpSb.append(".");
				}
				
				tmpSb.append(adrType.getVCardTypeName().getType());
				
				if(adrType.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(adrType.getCharset().name());
				}
				
				if(adrType.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(adrType.getLanguage().getLanguageCode());
				}
				
				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}
				
				if(adrType.hasParams()) {
					tmpSb.append(";");
					switch(adrType.getParameterTypeStyle())
					{
						case PARAMETER_LIST:
						{
							List<AdrParamType> paramTypes = adrType.getParams();
							for(AdrParamType adrParamType : paramTypes) {
								tmpSb.append("TYPE=");
								tmpSb.append(adrParamType.getType());
								tmpSb.append(";");
							}

							break;
						}

						case PARAMETER_VALUE_LIST:
						{
							tmpSb.append("TYPE=");
							List<AdrParamType> paramTypes = adrType.getParams();
							for(AdrParamType adrParamType : paramTypes) {
								tmpSb.append(adrParamType.getType());
								tmpSb.append(",");
							}

							break;
						}
					}

					tmpSb.deleteCharAt(tmpSb.length()-1);
				}
				
				if(adrType.hasExtendedParams()) {
					buildExtendParameters(adrType, tmpSb);
				}
				
				tmpSb.append(":");
				
				if(adrType.hasPostOfficebox()) {
					tmpSb.append(escapeOrEncode(adrType.getPostOfficeBox(), isQuotedPrintable, adrType.getCharset()));
				}

				tmpSb.append(";");

				if(adrType.hasExtendedAddress()) {
					tmpSb.append(escapeOrEncode(adrType.getExtendedAddress(), isQuotedPrintable, adrType.getCharset()));
				}

				tmpSb.append(";");
				
				if(adrType.hasStreetAddress()) {
					tmpSb.append(escapeOrEncode(adrType.getStreetAddress(), isQuotedPrintable, adrType.getCharset()));
				}

				tmpSb.append(";");

				if(adrType.hasLocality()) {
					tmpSb.append(escapeOrEncode(adrType.getLocality(), isQuotedPrintable, adrType.getCharset()));
				}

				tmpSb.append(";");

				if(adrType.hasRegion()) {
					tmpSb.append(escapeOrEncode(adrType.getRegion(), isQuotedPrintable, adrType.getCharset()));
				}

				tmpSb.append(";");

				if(adrType.hasPostalCode()) {
					tmpSb.append(escapeOrEncode(adrType.getPostalCode(), isQuotedPrintable, adrType.getCharset()));
				}

				tmpSb.append(";");

				if(adrType.hasCountryName()) {
					tmpSb.append(escapeOrEncode(adrType.getCountryName(), isQuotedPrintable, adrType.getCharset()));
				}

				String tmpAddressLine = tmpSb.toString();
				String foldedAddressLine = null;
				
				if(adrType.isQuotedPrintable()) {
					foldedAddressLine = VCardUtils.foldQuotedPrintableLine(tmpAddressLine, foldingScheme);
				}
				else {
					foldedAddressLine = VCardUtils.foldLine(tmpAddressLine, eol, foldingScheme);
				}
				
				sb.append(foldedAddressLine);
				sb.append(eol);
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("AdrType ("+VCardTypeName.ADR.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the label feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param labelType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the label feature is null
	 */
	private void buildLabelType(StringBuilder sb, LabelType labelType) throws VCardBuildException {
		try {
			if(labelType != null) {
				if(labelType.hasLabel()) {
					boolean isQuotedPrintable = labelType.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(labelType.hasGroup()) {
						tmpSb.append(labelType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(labelType.getVCardTypeName().getType());
					
					if(labelType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(labelType.getCharset().name());
					}
					
					if(labelType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(labelType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(labelType.hasParams()) {
						tmpSb.append(";");
						
						switch(labelType.getParameterTypeStyle())
						{
							case PARAMETER_LIST:
							{
								List<LabelParamType> paramTypes = labelType.getParams();
								for(LabelParamType labelParamType : paramTypes) {
									tmpSb.append("TYPE=");
									tmpSb.append(labelParamType.getType());
									tmpSb.append(";");
								}

								break;
							}

							case PARAMETER_VALUE_LIST:
							{
								tmpSb.append("TYPE=");
								
								List<LabelParamType> paramTypes = labelType.getParams();
								for(LabelParamType labelParamType : paramTypes) {
									tmpSb.append(labelParamType.getType());
									tmpSb.append(",");
								}

								break;
							}
						}

						tmpSb.deleteCharAt(tmpSb.length()-1);
					}
					
					if(labelType.hasExtendedParams()) {
						buildExtendParameters(labelType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeOrEncode(labelType.getLabel(), isQuotedPrintable, labelType.getCharset()));
					
					String tmpLabelLine = tmpSb.toString();
					String foldedLabelLine = null;
					
					if(labelType.isQuotedPrintable()) {
						foldedLabelLine = VCardUtils.foldQuotedPrintableLine(tmpLabelLine, foldingScheme);
					}
					else {
						foldedLabelLine = VCardUtils.foldLine(tmpLabelLine, foldingScheme);
					}
					
					sb.append(foldedLabelLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("LabelType ("+VCardTypeName.LABEL.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("LabelType ("+VCardTypeName.LABEL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the telephone feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param telType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the telephone feature is null
	 */
	private void buildTelType(StringBuilder sb, TelType telType) throws VCardBuildException {
		try {
			if(telType != null) {
				if(telType.hasTelephone()) {
					boolean isQuotedPrintable = telType.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(telType.hasGroup()) {
						tmpSb.append(telType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(telType.getVCardTypeName().getType());
					
					if(telType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(telType.getCharset().name());
					}
					
					if(telType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(telType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(telType.hasParams()) {
						tmpSb.append(";");
						switch(telType.getParameterTypeStyle())
						{
							case PARAMETER_LIST:
							{
								List<TelParamType> paramTypes = telType.getParams();
								for(TelParamType telParamType : paramTypes) {
									tmpSb.append("TYPE=");
									tmpSb.append(telParamType.getType());
									tmpSb.append(";");
								}

								break;
							}

							case PARAMETER_VALUE_LIST:
							{
								tmpSb.append("TYPE=");
								List<TelParamType> paramTypes = telType.getParams();
								for(TelParamType telParamType : paramTypes) {
									tmpSb.append(telParamType.getType());
									tmpSb.append(",");
								}

								break;
							}
						}

						tmpSb.deleteCharAt(tmpSb.length()-1);
					}
					
					if(telType.hasExtendedParams()) {
						buildExtendParameters(telType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeOrEncode(telType.getTelephone(), isQuotedPrintable, telType.getCharset()));
					
					String tmpTelephoneLine = tmpSb.toString();
					String foldedTelephoneLine = null;
					
					if(telType.isQuotedPrintable()) {
						foldedTelephoneLine = VCardUtils.foldQuotedPrintableLine(tmpTelephoneLine, foldingScheme);
					}
					else {
						foldedTelephoneLine = VCardUtils.foldLine(tmpTelephoneLine, eol, foldingScheme);
					}
					
					sb.append(foldedTelephoneLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("TelType ("+VCardTypeName.TEL.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("TelType ("+VCardTypeName.TEL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * 
	 * <p>Builds the email feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param emailType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the email feature is null
	 */
	private void buildEmailType(StringBuilder sb, EmailType emailType) throws VCardBuildException {
		try {
			if(emailType != null) {
				if(emailType.hasEmail()) {
					boolean isQuotedPrintable = emailType.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(emailType.hasGroup()) {
						tmpSb.append(emailType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(emailType.getVCardTypeName().getType());
					
					if(emailType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(emailType.getCharset().name());
					}
					
					if(emailType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(emailType.getLanguage().getLanguageCode());
					}
					
					if(emailType.hasParams()) {
						tmpSb.append(";");
						
						switch(emailType.getParameterTypeStyle())
						{
							case PARAMETER_LIST:
							{
								List<EmailParamType> paramTypes = emailType.getParams();
								for(EmailParamType emailParamType : paramTypes) {
									tmpSb.append("TYPE=");
									tmpSb.append(emailParamType.getType());
									tmpSb.append(";");
								}

								break;
							}

							case PARAMETER_VALUE_LIST:
							{
								tmpSb.append("TYPE=");
								List<EmailParamType> paramTypes = emailType.getParams();
								for(EmailParamType emailParamType : paramTypes) {
									tmpSb.append(emailParamType.getType());
									tmpSb.append(",");
								}

								break;
							}
						}

						tmpSb.deleteCharAt(tmpSb.length()-1);
					}
					
					if(emailType.hasExtendedParams()) {
						buildExtendParameters(emailType, tmpSb);
					}
					
					EncodingType encType = emailType.getEncodingType();
					switch(encType)
					{
						case BASE64:
						case BINARY:
						{
							tmpSb.append(";ENCODING=");
							tmpSb.append(encType.getType());
							tmpSb.append(":");
							
							byte[] emailBytes = null;
							if(emailType.hasCharset()) {
								emailBytes = emailType.getEmail().getBytes(emailType.getCharset().name());
							}
							else {
								emailBytes = emailType.getEmail().getBytes(Charset.defaultCharset());
							}
							
							tmpSb.append(Base64Wrapper.encode(emailBytes));
							
							break;
						}
						
						case QUOTED_PRINTABLE:
						{
							tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
						}
						
						default:
						{
							tmpSb.append(":");
							tmpSb.append(escapeOrEncode(emailType.getEmail(), isQuotedPrintable, emailType.getCharset()));
							break;
						}
					}
					
					String tmpEmailLine = tmpSb.toString();
					String foldedEmailLine = null;
					
					if(emailType.isQuotedPrintable()) {
						foldedEmailLine = VCardUtils.foldQuotedPrintableLine(tmpEmailLine, foldingScheme);
					}
					else {
						foldedEmailLine = VCardUtils.foldLine(tmpEmailLine, eol, binaryFoldingScheme);
					}
					
					sb.append(foldedEmailLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("EmailType ("+VCardTypeName.EMAIL.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("EmailType ("+VCardTypeName.EMAIL.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a note feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param noteType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the note feature is null
	 */
	private void buildNoteType(StringBuilder sb, NoteType noteType) throws VCardBuildException {
		try {
			if(noteType != null) {
				if(noteType.hasNote()) {
					boolean isQuotedPrintable = noteType.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(noteType.hasGroup()) {
						tmpSb.append(noteType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(noteType.getVCardTypeName().getType());
					
					if(noteType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(noteType.getCharset().name());
					}
					
					if(noteType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(noteType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(noteType.hasExtendedParams()) {
						buildExtendParameters(noteType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeOrEncode(noteType.getNote(), isQuotedPrintable, noteType.getCharset()));
					
					String tmpNoteLine = tmpSb.toString();
					String foldedNoteLine = null;
					
					if(noteType.isQuotedPrintable()) {
						foldedNoteLine = VCardUtils.foldQuotedPrintableLine(tmpNoteLine, foldingScheme);
					}
					else {
						foldedNoteLine = VCardUtils.foldLine(tmpNoteLine, eol, foldingScheme);
					}
					
					sb.append(foldedNoteLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("NoteType ("+VCardTypeName.NOTE.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("NoteType ("+VCardTypeName.NOTE.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the nickname feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param nicknameType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the nickname feature is null
	 */
	private void buildNicknameType(StringBuilder sb, NicknameType nicknameType) throws VCardBuildException {
		try {
			if(nicknameType != null) {
				if(nicknameType.hasNicknames()) {
					boolean isQuotedPrintable = nicknameType.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(nicknameType.hasGroup()) {
						tmpSb.append(nicknameType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(nicknameType.getVCardTypeName().getType());
					
					if(nicknameType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(nicknameType.getCharset().name());
					}
					
					if(nicknameType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(nicknameType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(nicknameType.hasExtendedParams()) {
						buildExtendParameters(nicknameType, tmpSb);
					}
					
					tmpSb.append(":");
					
					List<String> nicknames = nicknameType.getNicknames();
					for(String nickname : nicknames) {
						tmpSb.append(escapeOrEncode(nickname, isQuotedPrintable, nicknameType.getCharset()));
						tmpSb.append(",");
					}
					
					tmpSb.deleteCharAt(tmpSb.length()-1);
					String tmpNicknameLine = tmpSb.toString();
					String foldedNicknameLine = null;
					
					if(nicknameType.isQuotedPrintable()) {
						foldedNicknameLine = VCardUtils.foldQuotedPrintableLine(tmpNicknameLine, foldingScheme);
					}
					else {
						foldedNicknameLine = VCardUtils.foldLine(tmpNicknameLine, eol, foldingScheme);
					}
					
					sb.append(foldedNicknameLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("NicknameType ("+VCardTypeName.NICKNAME.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("NicknameType ("+VCardTypeName.NICKNAME.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the category feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param categoriesType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the categories feature is null
	 */
	private void buildCategoriesType(StringBuilder sb, CategoriesType categoriesType) throws VCardBuildException {
		try {
			if(categoriesType != null) {
				if(categoriesType.hasCategories()) {
					boolean isQuotedPrintable = categoriesType.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(categoriesType.hasGroup()) {
						tmpSb.append(categoriesType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(categoriesType.getVCardTypeName().getType());
					
					if(categoriesType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(categoriesType.getCharset().name());
					}
					
					if(categoriesType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(categoriesType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(categoriesType.hasExtendedParams()) {
						buildExtendParameters(categoriesType, tmpSb);
					}
					
					tmpSb.append(":");
					
					List<String> categories = categoriesType.getCategories();
					for(String category : categories) {
						tmpSb.append(escapeOrEncode(category, isQuotedPrintable, categoriesType.getCharset()));
						
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
					String foldedCategoryLine = null;
					
					if(categoriesType.isQuotedPrintable()) {
						foldedCategoryLine = VCardUtils.foldQuotedPrintableLine(tmpCategoryLine, foldingScheme);
					}
					else {
						foldedCategoryLine = VCardUtils.foldLine(tmpCategoryLine, eol, foldingScheme);
					}
					
					sb.append(foldedCategoryLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("CategoriesType ("+VCardTypeName.CATEGORIES.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("CategoriesType ("+VCardTypeName.CATEGORIES.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
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
	private void buildClassType(StringBuilder sb, ClassType classType) throws VCardBuildException {
		try {
			if(classType != null) {
				if(classType.hasSecurityClass()) {
					boolean isQuotedPrintable = classType.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(classType.hasGroup()) {
						tmpSb.append(classType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(classType.getVCardTypeName().getType());
					
					if(classType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(classType.getCharset().name());
					}
					
					if(classType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(classType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(classType.hasExtendedParams()) {
						buildExtendParameters(classType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeOrEncode(classType.getSecurityClass(), isQuotedPrintable, classType.getCharset()));
					
					String tmpClassLine = tmpSb.toString();
					String foldedClassLine = null;
					
					if(classType.isQuotedPrintable()) {
						foldedClassLine = VCardUtils.foldQuotedPrintableLine(tmpClassLine, foldingScheme);
					}
					else {
						foldedClassLine = VCardUtils.foldLine(tmpClassLine, eol, foldingScheme);
					}
					
					sb.append(foldedClassLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("ClassType ("+VCardTypeName.CLASS.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("ClassType ("+VCardTypeName.CLASS.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a product id feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param prodIdType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the product id feature is null
	 */
	private void buildProdIdType(StringBuilder sb, ProdIdType prodIdType) throws VCardBuildException {
		try {
			if(prodIdType != null) {
				if(prodIdType.hasProdId()) {
					boolean isQuotedPrintable = prodIdType.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(prodIdType.hasGroup()) {
						tmpSb.append(prodIdType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(prodIdType.getVCardTypeName().getType());
					
					if(prodIdType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(prodIdType.getCharset().name());
					}
					
					if(prodIdType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(prodIdType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(prodIdType.hasExtendedParams()) {
						buildExtendParameters(prodIdType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeOrEncode(prodIdType.getProdId(), isQuotedPrintable, prodIdType.getCharset()));
					
					String tmpProductIdLine = tmpSb.toString();
					String foldedProductIdLine = null;
					
					if(prodIdType.isQuotedPrintable()) {
						foldedProductIdLine = VCardUtils.foldQuotedPrintableLine(tmpProductIdLine, foldingScheme);
					}
					else {
						foldedProductIdLine = VCardUtils.foldLine(tmpProductIdLine, eol, foldingScheme);
					}
					
					sb.append(foldedProductIdLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("ProdIdType ("+VCardTypeName.PRODID.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("ProdIdType ("+VCardTypeName.PRODID.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a sort string feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param sortStringType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the sort string feature is null
	 */
	private void buildSortStringType(StringBuilder sb, SortStringType sortStringType) throws VCardBuildException {
		try {
			if(sortStringType != null) {
				if(sortStringType.hasSortString()) {
					boolean isQuotedPrintable = sortStringType.isQuotedPrintable();
					StringBuilder tmpSb = new StringBuilder();
					
					if(sortStringType.hasGroup()) {
						tmpSb.append(sortStringType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(sortStringType.getVCardTypeName().getType());
					
					if(sortStringType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(sortStringType.getCharset().name());
					}
					
					if(sortStringType.hasLanguage()) {
						tmpSb.append(";LANGUAGE=");
						tmpSb.append(sortStringType.getLanguage().getLanguageCode());
					}
					
					if(isQuotedPrintable) {
						tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
					}
					
					if(sortStringType.hasExtendedParams()) {
						buildExtendParameters(sortStringType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(escapeOrEncode(sortStringType.getSortString(), isQuotedPrintable, sortStringType.getCharset()));
					
					String tmpSortStringLine = tmpSb.toString();
					String foldedSortStringLine = null;
					
					if(sortStringType.isQuotedPrintable()) {
						foldedSortStringLine = VCardUtils.foldQuotedPrintableLine(tmpSortStringLine, foldingScheme);
					}
					else {
						foldedSortStringLine = VCardUtils.foldLine(tmpSortStringLine, eol, foldingScheme);
					}
					
					sb.append(foldedSortStringLine);
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("SortStringType ("+VCardTypeName.SORT_STRING.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("SortStringType ("+VCardTypeName.SORT_STRING.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Build the key feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param keyType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the key feature is null
	 */
	private void buildKeyType(StringBuilder sb, KeyType keyType) throws VCardBuildException {
		try {
			if(keyType != null) {
				if(keyType.hasKey()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(keyType.hasGroup()) {
						tmpSb.append(keyType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(keyType.getVCardTypeName().getType());

					if(keyType.hasCharset()) {
						tmpSb.append(";");
						tmpSb.append("CHARSET=");
						tmpSb.append(keyType.getCharset().name());
					}
					
					// determine if the key contains binary data or text data
					boolean binary = keyType.getEncodingType() == EncodingType.BINARY || keyType.getEncodingType() == EncodingType.BASE64;

					// output the ENCODING parameter
					// only do so if the key contains binary data
					if(binary) {
						tmpSb.append(";");
						switch(compatMode)
						{
							case MS_OUTLOOK:
							{
								tmpSb.append("ENCODING=");
								tmpSb.append(EncodingType.BASE64.getType());
								break;
							}

							case MAC_ADDRESS_BOOK:
							{
								tmpSb.append(EncodingType.BASE64.getType());
								break;
							}

							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case RFC2426:
							case I_PHONE:
							{
								tmpSb.append("ENCODING=");
								tmpSb.append(keyType.getEncodingType().getType());
								break;
							}
						}
					}
					
					if(keyType.hasKeyTextType()) {
						tmpSb.append(";");
						tmpSb.append("TYPE=");
						tmpSb.append(keyType.getKeyTextType().getTypeName());
					}
					
					if(keyType.hasExtendedParams()) {
						buildExtendParameters(keyType, tmpSb);
					}
					
					tmpSb.append(":");
					
					String foldedKeyLine = null;
					
					switch(compatMode)
					{
						case MAC_ADDRESS_BOOK:
						case MS_OUTLOOK:
						{
							String b64str = null;
							byte[] keyBytes = keyType.getKey();
							if(binary) {
								try {
									if(keyType.isCompressed()) {
										b64str = Base64Wrapper.encode(keyBytes, Base64Wrapper.OPTIONS.GZIP_COMPRESSION);
									}
									else {
										b64str = Base64Wrapper.encode(keyBytes, Base64Wrapper.OPTIONS.NO_COMPRESSION);
									}
								}
								catch(Exception ex) {
									throw new VCardBuildException(ex.getMessage(), ex);
								}
							}
							else {
								b64str = new String(keyBytes);
							}
							
							String tmpKeyLine = tmpSb.toString();
							String foldedKeyLine2 = VCardUtils.foldLine(tmpKeyLine, eol, binaryFoldingScheme);
							
							foldedKeyLine = VCardUtils.foldLine(b64str, eol, binaryFoldingScheme);
							sb.append(foldedKeyLine2);			//Type declaration with param types
							sb.append(eol);					//Distinctive line break
							sb.append(binaryFoldingScheme.getIndent());	//Indent first line
							break;
						}
						
						case EVOLUTION:
						case KDE_ADDRESS_BOOK:
						case RFC2426:
						case I_PHONE:
						{
							byte[] keyBytes = keyType.getKey();
							if(binary) {
								try {
									if(keyType.isCompressed()) {
										tmpSb.append(Base64Wrapper.encode(keyBytes, Base64Wrapper.OPTIONS.GZIP_COMPRESSION));
									}
									else {
										tmpSb.append(Base64Wrapper.encode(keyBytes, Base64Wrapper.OPTIONS.NO_COMPRESSION));
									}
								}
								catch(Exception ex) {
									throw new VCardBuildException(ex.getMessage(), ex);
								}
							}
							else {
								tmpSb.append(new String(keyBytes));
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
					throw new VCardBuildException("KeyType ("+VCardTypeName.KEY.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("KeyType ("+VCardTypeName.KEY.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a photo feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param photoType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the photo feature is null
	 */
	private void buildPhotoType(StringBuilder sb, PhotoType photoType) throws VCardBuildException {
		try {
			if(photoType != null) {
				if(photoType.hasPhoto()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(photoType.hasGroup()) {
						tmpSb.append(photoType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(photoType.getVCardTypeName().getType());
					tmpSb.append(";");
					
					String foldedPhotoLine = null;
					
					if(photoType.hasCharset()) {
						tmpSb.append("CHARSET=");
						tmpSb.append(photoType.getCharset().name());
						tmpSb.append(";");
					}
					
					if(photoType.isURI()) {
						tmpSb.append("VALUE=URI:");
						tmpSb.append(photoType.getPhotoURI().toString());
						
						String tmpPhotoLine = tmpSb.toString();
						foldedPhotoLine = VCardUtils.foldLine(tmpPhotoLine, eol, foldingScheme);
					}
					else if(photoType.isBinary()) {
						switch(compatMode)
						{
							case MS_OUTLOOK:
							{
								tmpSb.append("ENCODING=");
								tmpSb.append(EncodingType.BASE64.getType());
								break;
							}
							
							case MAC_ADDRESS_BOOK:
							{
								tmpSb.append(EncodingType.BASE64.getType());
								break;
							}
							
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case RFC2426:
							case I_PHONE:
							default:
							{
								tmpSb.append("ENCODING=");
								tmpSb.append(photoType.getEncodingType().getType());
								break;
							}
						}
						
						if(photoType.hasImageMediaType()) {
							tmpSb.append(";");
							tmpSb.append("TYPE=");
							tmpSb.append(photoType.getImageMediaType().getTypeName());
						}
						
						if(photoType.hasExtendedParams()) {
							buildExtendParameters(photoType, tmpSb);
						}
						
						tmpSb.append(":");
						
						switch(compatMode)
						{
							case MS_OUTLOOK:
							case MAC_ADDRESS_BOOK:
							{
								String b64str = null;
								try {
									byte[] photoBytes = photoType.getPhoto();
									if(photoType.isCompressed()) {
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
							
							case RFC2426:
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case I_PHONE:
							default:
							{
								try {
									byte[] photoBytes = photoType.getPhoto();
									if(photoType.isCompressed()) {
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
						throw new VCardBuildException("PhotoType ("+VCardTypeName.PHOTO.getType()+") is not URI and not Inline, cannot proceed, must be one or the other.");
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
						default:
						{
							sb.append(eol);
							break;
						}
					}
				}
				else {
					throw new VCardBuildException("PhotoType ("+VCardTypeName.PHOTO.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("PhotoType ("+VCardTypeName.PHOTO.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a logo feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param logoType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the logo feature is null
	 */
	private void buildLogoType(StringBuilder sb, LogoType logoType) throws VCardBuildException {
		try {
			if(logoType != null) {
				if(logoType.hasLogo()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(logoType.hasGroup()) {
						tmpSb.append(logoType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(logoType.getVCardTypeName().getType());
					tmpSb.append(";");
					
					String foldedLogoLine = null;
					
					if(logoType.hasCharset()) {
						tmpSb.append("CHARSET=");
						tmpSb.append(logoType.getCharset().name());
						tmpSb.append(";");
					}
					
					if(logoType.isURI()) {
						tmpSb.append("VALUE=URI:");
						tmpSb.append(logoType.getLogoURI().toString());
						
						String tmpLogoLine = tmpSb.toString();
						foldedLogoLine = VCardUtils.foldLine(tmpLogoLine, eol, foldingScheme);
					}
					else if(logoType.isBinary()) {
						switch(compatMode)
						{
							case MS_OUTLOOK:
							{
								tmpSb.append("ENCODING=");
								tmpSb.append(EncodingType.BASE64.getType());
								break;
							}
								
							case MAC_ADDRESS_BOOK:
							{
								tmpSb.append(EncodingType.BASE64.getType());
								break;
							}
							
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case RFC2426:
							case I_PHONE:
							{
								tmpSb.append("ENCODING=");
								tmpSb.append(logoType.getEncodingType().getType());
								break;
							}
						}
						
						if(logoType.hasImageMediaType()) {
							tmpSb.append(";");
							tmpSb.append("TYPE=");
							tmpSb.append(logoType.getImageMediaType().getTypeName());
						}
						
						if(logoType.hasExtendedParams()) {
							buildExtendParameters(logoType, tmpSb);
						}

						tmpSb.append(":");
						
						switch(compatMode)
						{
							case MAC_ADDRESS_BOOK:
							case MS_OUTLOOK:
							{
								String b64str = null;
								try {
									byte[] logoBytes = logoType.getLogo();
									if(logoType.isCompressed()) {
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
							
							case RFC2426:
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case I_PHONE:
							{
								try {
									byte[] logoBytes = logoType.getLogo();
									if(logoType.isCompressed()) {
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
						throw new VCardBuildException("LogoType ("+VCardTypeName.LOGO.getType()+") is not URI and not Inline, cannot proceed, must be one or the other.");
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
					throw new VCardBuildException("LogoType ("+VCardTypeName.LOGO.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("LogoType ("+VCardTypeName.LOGO.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds a sound feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param soundType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the sound feature is null
	 */
	private void buildSoundType(StringBuilder sb, SoundType soundType) throws VCardBuildException {
		try {
			if(soundType != null) {
				if(soundType.hasSound()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(soundType.hasGroup()) {
						tmpSb.append(soundType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(soundType.getVCardTypeName().getType());
					tmpSb.append(";");
					
					String foldedSoundLine = null;
					
					if(soundType.hasCharset()) {
						tmpSb.append("CHARSET=");
						tmpSb.append(soundType.getCharset().name());
						tmpSb.append(";");
					}
					
					if(soundType.isURI()) {
						tmpSb.append("VALUE=URI:");
						tmpSb.append(soundType.getSoundURI().getPath());
						
						String tmpSoundLine = tmpSb.toString();
						foldedSoundLine = VCardUtils.foldLine(tmpSoundLine, eol, foldingScheme);
					}
					else if(soundType.isBinary()) {
						switch(compatMode)
						{
							case MS_OUTLOOK:
							{
								tmpSb.append("ENCODING=");
								tmpSb.append(EncodingType.BASE64.getType());
								break;
							}
							
							case MAC_ADDRESS_BOOK:
							{
								tmpSb.append(EncodingType.BASE64.getType());
								break;
							}
							
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case RFC2426:
							case I_PHONE:
							{
								tmpSb.append("ENCODING=");
								tmpSb.append(soundType.getEncodingType().getType());
								break;
							}
						}
						
						if(soundType.hasAudioMediaType()) {
							tmpSb.append(";");
							tmpSb.append("TYPE=");
							tmpSb.append(soundType.getAudioMediaType().getTypeName());
						}
						
						if(soundType.hasExtendedParams()) {
							buildExtendParameters(soundType, tmpSb);
						}
						
						tmpSb.append(":");
						
						switch(compatMode)
						{
							case MAC_ADDRESS_BOOK:
							case MS_OUTLOOK:
							{
								String b64str = null;
								try {
									byte[] soundBytes = soundType.getSound();
									if(soundType.isCompressed()) {
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
							
							case RFC2426:
							case EVOLUTION:
							case KDE_ADDRESS_BOOK:
							case I_PHONE:
							{
								try {
									byte[] soundBytes = soundType.getSound();
									if(soundType.isCompressed()) {
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
						throw new VCardBuildException("SoundType ("+VCardTypeName.SOUND.getType()+") is not URI and not Inline, cannot proceed, must be one or the other.");
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
					throw new VCardBuildException("SoundType ("+VCardTypeName.SOUND.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("SoundType ("+VCardTypeName.SOUND.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds an agent feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param agentType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the agent feature is null
	 */
	private void buildAgentType(StringBuilder sb, AgentType agentType) throws VCardBuildException {
		try {
			if(agentType != null) {
				if(agentType.hasAgent()) {
					StringBuilder tmpSb = new StringBuilder();
					
					if(agentType.hasGroup()) {
						tmpSb.append(agentType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(agentType.getVCardTypeName().getType());
					
					if(agentType.hasCharset()) {
						tmpSb.append(";CHARSET=");
						tmpSb.append(agentType.getCharset().name());
					}
					
					if(agentType.isURI()) {
						tmpSb.append(";");
						tmpSb.append("VALUE=URI:");
						tmpSb.append(agentType.getAgentURI().getPath());
					}
					else if(EncodingType.BINARY.equals(agentType.getEncodingType())) {
						tmpSb.append(":");
						VCard agentVCard = agentType.getAgent();
						if(agentVCard instanceof VCardErrorHandler) {
							//Turn on error handling if available
							((VCardErrorHandler)agentVCard).setThrowExceptions(true);
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
						throw new VCardBuildException("AgentType ("+VCardTypeName.AGENT.getType()+") is not URI and not Inline, cannot proceed, must be one or the other.");
					}
				}
				else {
					throw new VCardBuildException("AgentType ("+VCardTypeName.AGENT.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("AgentType ("+VCardTypeName.AGENT.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the IMPP feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param imppType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the IMPP feature is null
	 */
	private void buildImppType(StringBuilder sb, ImppType imppType) throws VCardBuildException {
		try {
			if(imppType != null) {
				if(imppType.hasUri()) {
					String uri = imppType.getUri().toString();
					StringBuilder tmpSb = new StringBuilder();
					
					if(imppType.hasGroup()) {
						tmpSb.append(imppType.getGroup());
						tmpSb.append(".");
					}
					
					tmpSb.append(imppType.getVCardTypeName().getType());
					
					if(imppType.hasParams()) {
						tmpSb.append(";");
						switch(imppType.getParameterTypeStyle())
						{
							case PARAMETER_LIST:
							{
								List<ImppParamType> paramTypes = imppType.getParams();
								for(ImppParamType imppParamType : paramTypes) {
									tmpSb.append("TYPE=");
									tmpSb.append(imppParamType.getType());
									tmpSb.append(";");
								}

								break;
							}

							case PARAMETER_VALUE_LIST:
							{
								tmpSb.append("TYPE=");
								List<ImppParamType> paramTypes = imppType.getParams();
								for(ImppParamType imppParamType : paramTypes) {
									tmpSb.append(imppParamType.getType());
									tmpSb.append(",");
								}

								break;
							}
						}

						tmpSb.deleteCharAt(tmpSb.length()-1);
					}
					
					if(imppType.hasExtendedParams()) {
						buildExtendParameters(imppType, tmpSb);
					}
					
					tmpSb.append(":");
					tmpSb.append(uri);
					
					String tmpUrlLine = tmpSb.toString();
					String foldedUrlLine = VCardUtils.foldLine(tmpUrlLine, eol, foldingScheme);
					sb.append(foldedUrlLine);
					
					sb.append(eol);
				}
				else {
					throw new VCardBuildException("ImppType ("+VCardTypeName.IMPP.getType()+") exists but is empty.");
				}
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("ImppType ("+VCardTypeName.IMPP.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds an extended feature as a String.</p>
	 *
	 * @param sb
	 * 	- The StringBuilder to append to
	 * @param extendedType
	 * 	- The feature to output as a String
	 * @throws VCardBuildException
	 * 	Thrown when the extended feature is null
	 */
	private void buildExtendedType(StringBuilder sb, ExtendedType extendedType) throws VCardBuildException {
		try {
			if(extendedType != null) {
				boolean isQuotedPrintable = extendedType.isQuotedPrintable();
				StringBuilder tmpSb = new StringBuilder();

				if(extendedType.hasGroup()) {
					tmpSb.append(extendedType.getGroup());
					tmpSb.append(".");
				}

				tmpSb.append(extendedType.getExtendedName());

				if(extendedType.hasCharset()) {
					tmpSb.append(";CHARSET=");
					tmpSb.append(extendedType.getCharset().name());
				}

				if(extendedType.hasLanguage()) {
					tmpSb.append(";LANGUAGE=");
					tmpSb.append(extendedType.getLanguage().getLanguageCode());
				}

				if(isQuotedPrintable) {
					tmpSb.append(";ENCODING=QUOTED-PRINTABLE");
				}

				if(extendedType.hasExtendedParams()) {
					buildExtendParameters(extendedType, tmpSb);
				}

				tmpSb.append(":");
				tmpSb.append(escapeOrEncode(extendedType.getExtendedValue(), isQuotedPrintable, extendedType.getCharset()));

				String tmpExtendedLine = tmpSb.toString();
				String foldedExtendedLine = null;
				
				if(extendedType.isQuotedPrintable()) {
					foldedExtendedLine = VCardUtils.foldQuotedPrintableLine(tmpExtendedLine, foldingScheme);
				}
				else {
					foldedExtendedLine = VCardUtils.foldLine(tmpExtendedLine, eol, foldingScheme);
				}
				
				sb.append(foldedExtendedLine);
				sb.append(eol);
			}
			else {
				throw new VCardBuildException("ExtendedType is null.");
			}
		}
		catch(Exception ex) {
			throw new VCardBuildException("ExtendedType ("+VCardTypeName.XTENDED.getType()+") ["+ex.getClass().getName()+"] "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * <p>Builds the extended parameter types given a string builder and the
	 * specified feature that is guaranteed to have extended parameters.</p>
	 *
	 * @param vcardType
	 * @param sb
	 */
	private void buildExtendParameters(AbstractVCardType vcardType, StringBuilder sb)
	{
		switch(vcardType.getParameterTypeStyle())
		{
			case PARAMETER_LIST:
			{
				sb.append(";");
				List<ExtendedParamType> xParamTypes = vcardType.getExtendedParams();
				for(ExtendedParamType xParam : xParamTypes) {
					sb.append(xParam.getTypeName());
					if(xParam.hasTypeValue()) {
						sb.append("=");
						sb.append(xParam.getTypeValue());
					}
	
					sb.append(";");
				}
	
				break;
			}

			case PARAMETER_VALUE_LIST:
			{
				if(vcardType.hasParams()) {
					//Continue from the list
					sb.append(",");
				}
				else {
					//Start a new
					sb.append(";");
				}
				
				List<ExtendedParamType> xParamTypes = vcardType.getExtendedParams();
				for(ExtendedParamType xParam : xParamTypes) {
					sb.append(xParam.getTypeName());
					if(xParam.hasTypeValue()) {
						sb.append("=");
						sb.append(xParam.getTypeValue());
					}
	
					sb.append(",");
				}
	
				break;
			}
		}

		sb.deleteCharAt(sb.length()-1);
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
	private String escapeOrEncode(String str, boolean isQuotedPrintable, Charset charset) throws EncoderException, UnsupportedEncodingException
	{
		if(isQuotedPrintable) {
			String str1 = null;
			if(VCardUtils.needsEscaping(str)) {
				str1 = VCardUtils.escapeString(str);
			}
			else {
				str1 = str;
			}
			
			if(charset == null) {
				charset = Charset.defaultCharset();
			}
			
			if(qpCodec == null) {
				qpCodec = new QuotedPrintableCodec();
			}
			
			String str2 = qpCodec.encode(str1, charset.name());
			
			if(forceEncodeQuotedPrintableSpaces) {
				str2 = str2.replaceAll(" ", "=20");
			}
			
			return str2;
		}
		else {
			if(VCardUtils.needsEscaping(str)) {
				return VCardUtils.escapeString(str);
			}
			else {
				return str;
			}
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
		if(vcard instanceof VCardErrorHandler) {
			return ((VCardErrorHandler)vcard).hasErrors();
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
		
		((VCardErrorHandler)vcard).addError(vError);
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
