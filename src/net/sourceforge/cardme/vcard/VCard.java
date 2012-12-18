package net.sourceforge.cardme.vcard;

import java.util.Collection;
import java.util.List;
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
 * @author Jeff Prickett
 * <br/>
 * Feb 4, 2010
 * 
 * <p>
 * This is a VCard interface compliant with RFC-2426 and RFC-4770 and
 * implements the following sections and sub-sections of the RFC:
 * <pre>
 *    2.1 PREDEFINED TYPE USAGE ......................................5
 *     2.1.1 BEGIN and END Type ......................................5
 *     2.1.2 NAME Type ...............................................5
 *     2.1.3 PROFILE Type ............................................5
 *     2.1.4 SOURCE Type .............................................5
 *    3.1 IDENTIFICATION TYPES .......................................8
 *     3.1.1 FN Type Definition ......................................8
 *     3.1.2 N Type Definition .......................................9
 *     3.1.3 NICKNAME Type Definition ................................9
 *     3.1.4 PHOTO Type Definition ..................................10
 *     3.1.5 BDAY Type Definition ...................................11
 *    3.2 DELIVERY ADDRESSING TYPES .................................11
 *     3.2.1 ADR Type Definition ....................................11
 *     3.2.2 LABEL Type Definition ..................................13
 *    3.3 TELECOMMUNICATIONS ADDRESSING TYPES .......................13
 *     3.3.1 TEL Type Definition ....................................14
 *     3.3.2 EMAIL Type Definition ..................................15
 *     3.3.3 MAILER Type Definition .................................15
 *    3.4 GEOGRAPHICAL TYPES ........................................16
 *     3.4.1 TZ Type Definition .....................................16
 *     3.4.2 GEO Type Definition ....................................16
 *    3.5 ORGANIZATIONAL TYPES ......................................17
 *     3.5.1 TITLE Type Definition ..................................17
 *     3.5.2 ROLE Type Definition ...................................18
 *     3.5.3 LOGO Type Definition ...................................18
 *     3.5.4 AGENT Type Definition ..................................19
 *     3.5.5 ORG Type Definition ....................................20
 *    3.6 EXPLANATORY TYPES .........................................20
 *     3.6.1 CATEGORIES Type Definition .............................20
 *     3.6.2 NOTE Type Definition ...................................21
 *     3.6.3 PRODID Type Definition .................................21
 *     3.6.4 REV Type Definition ....................................22
 *     3.6.5 SORT-STRING Type Definition ............................22
 *     3.6.6 SOUND Type Definition ..................................23
 *     3.6.7 UID Type Definition ....................................24
 *     3.6.8 URL Type Definition ....................................25
 *     3.6.9 VERSION Type Definition ................................25
 *    3.7 SECURITY TYPES ............................................25
 *     3.7.1 CLASS Type Definition ..................................26
 *     3.7.2 KEY Type Definition ....................................26
 *    3.8 EXTENDED TYPES ............................................27
 * </pre>
 * </p>
 * 
 */
public interface VCard {

	/**
	 * <p>Returns the BEGIN type. The type should always exist.</p>
	 *
	 * @return the begin type - {@link BeginType}
	 */
	public BeginType getBegin();
	
	/**
	 * <p>Sets the BEGIN type. The type cannot be null and this
	 * field is automatically generated if not explicitly set.</p>
	 *
	 * @param begin - the begin type
	 * @throws NullPointerException
	 * 	Thrown if begin type is null
	 */
	public void setBegin(BeginType begin) throws NullPointerException;
	
	/**
	 * <p>Sets the END type. The type should always exist.</p>
	 *
	 * @return the end type - {@link EndType}
	 */
	public EndType getEnd();
	
	/**
	 * <p>Sets the END type. The type cannot be null and this
	 * field is automatically generated if not explicitly set.</p>
	 *
	 * @param end - the end type
	 * @throws NullPointerException
	 * 	Thrown if the end type is null
	 */
	public void setEnd(EndType end) throws NullPointerException;
	
	/**
	 * <p>Returns the NAME type or null if one is not set.</p>
	 *
	 * @return the name type {@link NameType} or null if one is not set 
	 */
	public NameType getName();
	
	/**
	 * <p>Sets the NAME type, this value can be null.</p>
	 *
	 * @param name the NAME type
	 */
	public void setName(NameType name);
	
	/**
	 * <p>Returns true if the NAME type has been set.</p>
	 *
	 * @return true if the NAME type has been set
	 */
	public boolean hasName();
	
	/**
	 * <p>Removes the NAME type from the VCard.</p>
	 *
	 */
	public void clearName();
	
	/**
	 * <p>Returns the PROFILE type or null if one is not set.</p>
	 *
	 * @return the profile type {@link ProfileType} or null if one is not set
	 */
	public ProfileType getProfile();
	
	/**
	 * <p>Sets the PROFILE type, this value can be null.</p>
	 *
	 * @param profile the PROFILE type
	 */
	public void setProfile(ProfileType profile);
	
	/**
	 * <p>Returns true if the PROFILE type has been set.</p>
	 *
	 * @return true if the PROFILE type has been set
	 */
	public boolean hasProfile();
	
	/**
	 * <p>Removes the PROFILE type from the VCard.</p>
	 *
	 */
	public void clearProfile();
	
	/**
	 * <p>Returns the SOURCE type or null if one is not set.</p>
	 *
	 * @return the SOURCE type {@link SourceType} or null if one is not set
	 */
	public SourceType getSource();
	
	/**
	 * <p>Sets the SOURCE type, this value can be null.</p>
	 *
	 * @param source the SOURCE type
	 */
	public void setSource(SourceType source);
	
	/**
	 * <p>Returns true if the SOURCE type has been set.</p>
	 *
	 * @return true if the SOURCE type has been set
	 */
	public boolean hasSource();
	
	/**
	 * <p>Removes the SOURCE type from the VCard.</p>
	 *
	 */
	public void clearSource();
	
	/**
	 * <p>Returns the FN type, this type must always exist.</p>
	 *
	 * @return the FN type {@link FNType}
	 */
	public FNType getFN();
	
	/**
	 * <p>Sets the FN type. This type must be set in the
	 * VCard and cannot be omitted, it can however be left with
	 * all empty values</p>
	 *
	 * @param formattedName - the formatted name
	 * @throws NullPointerException if setting a null formatted name
	 */
	public void setFN(FNType formattedName) throws NullPointerException;
	
	/**
	 * <p>Returns true if the FN type is set.</p>
	 *
	 * @return true if the FN type is set or false otherwise
	 */
	public boolean hasFN();
	
	/**
	 * <p>Returns the N type.</p>
	 * 
	 * @return the {@link NType}
	 */
	public NType getN();
	
	/**
	 * <p>Sets the N type. The N type must be set in the
	 * VCard and cannot be omitted, it can however be left with
	 * all empty values.</p>
	 * 
	 * @param name - the N type to set.
	 * @throws NullPointerException if setting a null N type
	 */
	public void setN(NType name);
	
	/**
	 * <p>Returns true if the N type has been set.</p>
	 * 
	 * @return true if the N type has been set or false otherwise
	 */
	public boolean hasN();
	
	/**
	 * <p>Set the Nickname type.</p>
	 * 
	 * @param nicknames - the nickname to set
	 */
	public void setNickname(NicknameType nicknames);
	
	/**
	 * <p>Returns the Nickname type.</p>
	 * 
	 * @return the Nickname type
	 */
	public NicknameType getNicknames();
	
	/**
	 * <p>Returns true if the Nickname type has been set.</p>
	 * 
	 * @return true if the Nickname type has been set or false otherwise
	 */
	public boolean hasNicknames();
	
	/**
	 * <p>Removes the Nickname type.</p>
	 */
	public void clearNickname();
	
	/**
	 * <p>Returns a list of Photo types that were added.</p> 
	 * 
	 * @return list of Photo types
	 */
	public List<PhotoType> getPhotos();
	
	/**
	 * <p>Adds a Photo type.</p>
	 * 
	 * @param photo - the Photo type to add
	 * @throws NullPointerException if adding a null Photo type
	 */
	public void addPhoto(PhotoType photo) throws NullPointerException;
	
	/**
	 * <p>Adds a list of Photo types.</p>
	 * 
	 * @param photos - the list of Photo types to add
	 * @throws NullPointerException if adding a null list of Photo types
	 */
	public void addAllPhotos(Collection<PhotoType> photos) throws NullPointerException;
	
	/**
	 * <p>Removes the specified Photo type.</p>
	 * 
	 * @param photo - the Photo type to remove
	 * @return true if the specified Photo type existed and was removed
	 * @throws NullPointerException if removing a null Photo type
	 */
	public boolean removePhoto(PhotoType photo) throws NullPointerException;
	
	/**
	 * <p>Returns true if the specified Photo type exists.</p>
	 * 
	 * @param photo - the Photo type to check
	 * @return true if the specified Photo type exists or false otherwise
	 */
	public boolean containsPhoto(PhotoType photo);
	
	/**
	 * <p>Returns true this VCard contains Photo types.</p>
	 * 
	 * @return true if this VCard contains Photo types
	 */
	public boolean hasPhotos();
	
	/**
	 * <p>Removes all Photo types.</p>
	 */
	public void clearPhotos();
	
	/**
	 * <p>Returns the BDay type.</p>
	 * 
	 * @return the BDay type
	 */
	public BDayType getBDay();
	
	/**
	 * <p>Sets the BDay type.</p>
	 * 
	 * @param bday - the BDay type to set
	 */
	public void setBDay(BDayType bday);
	
	/**
	 * <p>Returns true if this VCard has a BDay type set.</p>
	 * 
	 * @return true if the BDay type was set or false otherwise
	 */
	public boolean hasBDay();
	
	/**
	 * <p>Removes the BDay type.</p>
	 */
	public void clearBDay();
	
	/**
	 * <p>Returns a list of added ADR types.</p>
	 * 
	 * @return a list of ADR types
	 */
	public List<AdrType> getAdrs();
	
	/**
	 * <p>Adds an ADR type.</p>
	 * 
	 * @param adr - the ADR type to add
	 * @throws NullPointerException if adding a null ADR type
	 */
	public void addAdr(AdrType adr) throws NullPointerException;
	
	/**
	 * <p>Adds a list of ADR types.</p>
	 * 
	 * @param adrs - the list of ADR types to add
	 * @throws NullPointerException if adding a null list of ADR types
	 */
	public void addAllAdrs(Collection<AdrType> adrs) throws NullPointerException;
	
	/**
	 * <p>Removes the specified ADR type.</p>
	 * 
	 * @param adr - the ADR type to remove
	 * @return true if the ADR type existed and was removed
	 * @throws NullPointerException if removing a null ADR type
	 */
	public boolean removeAdr(AdrType adr) throws NullPointerException;
	
	/**
	 * <p>Returns true if the specified ADR type exists.</p>
	 * 
	 * @param address - the ADR type to check
	 * @return true if the specified ADR type exists
	 */
	public boolean containsAdr(AdrType address);
	
	/**
	 * <p>Returns true if this VCard contains ADR types.</p>
	 * 
	 * @return true if this VCard contains ADR types
	 */
	public boolean hasAdrs();
	
	/**
	 * <p>Removes all ADR types.</p>
	 */
	public void clearAdrs();
	
	/**
	 * <p>Returns a list of all LABEL types.</p>
	 * 
	 * @return a list of all LABEL types
	 */
	public List<LabelType> getLables();
	
	/**
	 * <p>Returns true if the specified LABEL type exists.</p>
	 * 
	 * @param label - the specified LABEL type to check
	 * @return true if the specified LABEL type exists or false otherwise
	 */
	public boolean containsLabel(LabelType label);
	
	/**
	 * <p>Removes all LABEL types.</p>
	 */
	public void clearLabels();
	
	/**
	 * <p>Returns a list of TEL types.</p>
	 * 
	 * @return a list of TEL types
	 */
	public List<TelType> getTels();
	
	/**
	 * <p>Adds a TEL type.</p>
	 * 
	 * @param tel - the TEL type to add
	 * @throws NullPointerException if adding a null TEL type
	 */
	public void addTel(TelType tel) throws NullPointerException;
	
	/**
	 * <p>Adds a list of TEL types.</p>
	 * 
	 * @param tels - the list of TEL types to add
	 * @throws NullPointerException if adding a null list of TEL types
	 */
	public void addAllTels(Collection<TelType> tels) throws NullPointerException;
	
	/**
	 * <p>Removes the specified TEL type.</p>
	 * 
	 * @param tel - the TEL type to remove
	 * @return true if the specified TEL type exists and was removed
	 * @throws NullPointerException if removing a null TEL type
	 */
	public boolean removeTel(TelType tel) throws NullPointerException;
	
	/**
	 * <p>Returns true if the specified TEL type exists.</p>
	 * 
	 * @param tel - the TEL type to check
	 * @return true if the specified TEL type exits or false otherwise
	 */
	public boolean containsTel(TelType tel);
	
	/**
	 * <p>Remove all TEL types.</p>
	 */
	public void clearTel();
	
	/**
	 * <p>Returns true if this VCard has TEL types.</p>
	 * 
	 * @return true if this VCard has TEL type or false otherwise
	 */
	public boolean hasTels();
	
	/**
	 * <p>Returns a list of EMAIL types.</p>
	 * 
	 * @return list of EMAIL types
	 */
	public List<EmailType> getEmails();
	
	/**
	 * <p>Adds an EMAIL type.</p>
	 * 
	 * @param email - the EMAIL type to add
	 * @throws NullPointerException if adding a null EMAIL type
	 */
	public void addEmail(EmailType email) throws NullPointerException;
	
	/**
	 * <p>Adds a list of EMAIL types.</p>
	 * 
	 * @param emailAddresses - the list of EMAIL types to add
	 * @throws NullPointerException if adding a null list of EMAIL types
	 */
	public void addAllEmails(Collection<EmailType> emailAddresses) throws NullPointerException;
	
	/**
	 * <p>Removes a specified EMAIL type.</p>
	 * 
	 * @param email - the EMAIL type to remove
	 * @return true if the specified EMAIL type exists and was removed
	 * @throws NullPointerException if removing a null EMAIL type
	 */
	public boolean removeEmail(EmailType email) throws NullPointerException;
	
	/**
	 * <p>Returns true if the specified EMAIL type exists.</p>
	 * 
	 * @param email - the EMAIL type to check
	 * @return true if the EMAIL type exists or false otherwise
	 */
	public boolean containsEmail(EmailType email);
	
	/**
	 * <p>Returns true if this VCard contains EMAIL types.</p>
	 * 
	 * @return true if this VCard contains EMAIL types
	 */
	public boolean hasEmails();
	
	/**
	 * <p>Removes all EMAIL types.</p>
	 */
	public void clearEmails();
	
	
	/**
	 * <p>Returns the MAILER type.</p>
	 * 
	 * @return the MAILER type
	 */
	public MailerType getMailer();
	
	/**
	 * <p>Sets the MAILER type.</p>
	 * 
	 * @param mailer - the MAILER type to set
	 */
	public void setMailer(MailerType mailer);
	
	/**
	 * <p>Returns true if this VCard has the MAILER type.</p>
	 * 
	 * @return true if this VCard has the MAILER type
	 */
	public boolean hasMailer();
	
	/**
	 * <p>Removes the MAILER type.</p>
	 */
	public void clearMailer();
	
	/**
	 * <p>Returns the TZ type.</p>
	 * 
	 * @return the TZ type
	 */
	public TzType getTz();
	
	/**
	 * <p>Sets the TZ type.</p>
	 * 
	 * @param timeZone - the TZ type to set
	 */
	public void setTz(TzType timeZone);
	
	/**
	 * <p>Returns true if this VCard contains the TZ type.</p>
	 * 
	 * @return true if this VCard contains the TZ type
	 */
	public boolean hasTz();
	
	/**
	 * <p>Removes the TZ type.</p>
	 */
	public void clearTz();
	
	/**
	 * <p>Returns the GEO type.</p>
	 * 
	 * @return the GEO type
	 */
	public GeoType getGeo();
	
	/**
	 * <p>Sets the GEO type.</p>
	 * 
	 * @param geo - the GEO type to set
	 */
	public void setGeo(GeoType geo);
	
	/**
	 * <p>Returns true if this VCard contains the GEO type.</p>
	 * 
	 * @return true if this VCard contains the GEO type
	 */
	public boolean hasGeo();
	
	/**
	 * <p>Removes the GEO type.</p>
	 */
	public void clearGeo();
	
	/**
	 * <p>Returns the TITLE type.</p>
	 * 
	 * @return the TITLE type
	 */
	public TitleType getTitle();
	
	/**
	 * <p>Set the TITLE type.</p>
	 * 
	 * @param title - the TITLE type to set
	 */
	public void setTitle(TitleType title);
	
	/**
	 * <p>Returns true if this VCard contains the TITLE type.</p>
	 * 
	 * @return true if this VCard contains the TITLE type
	 */
	public boolean hasTitle();
	
	/**
	 * <p>Removes the TITLE type.</p>
	 */
	public void clearTitle();
	
	/**
	 * <p>Returns the ROLE type.</p>
	 * 
	 * @return the ROLE type
	 */
	public RoleType getRole();
	
	/**
	 * <p>Sets the ROLE type.</p>
	 * 
	 * @param role - the ROLE type to set
	 */
	public void setRole(RoleType role);
	
	/**
	 * <p>Returns true if this VCard contains the ROLE type.</p>
	 * 
	 * @return true if this VCard contains the ROLE type
	 */
	public boolean hasRole();
	
	/**
	 * <p>Removes the ROLE type.</p>
	 */
	public void clearRole();
	
	/**
	 * <p>Returns a list of LOGO types.</p>
	 * 
	 * @return a list of LOGO types
	 */
	public List<LogoType> getLogos();
	
	/**
	 * <p>Adds a LOGO type.</p>
	 * 
	 * @param logo - the LOGO type to add
	 * @throws NullPointerException if adding a null LOGO type
	 */
	public void addLogo(LogoType logo) throws NullPointerException;
	
	/**
	 * <p>Adds a list of LOGO type.</p>
	 * 
	 * @param logos - the list of LOGO types to add
	 * @throws NullPointerException if adding a null list of LOGO types
	 */
	public void addAllLogos(Collection<LogoType> logos) throws NullPointerException;
	
	/**
	 * <p>Removes the specified LOGO type.</p>
	 * 
	 * @param logo - the LOGO type to remove
	 * @return true if the specified LOGO type exists and was removed
	 * @throws NullPointerException if removing a null LOGO type
	 */
	public boolean removeLogo(LogoType logo) throws NullPointerException;
	
	/**
	 * <p>Returns true if the specified LOGO type exists.</p>
	 * 
	 * @param logo - the LOGO type to check
	 * @return true if the specified LOGO type exists or false otherwise
	 */
	public boolean containsLogo(LogoType logo);
	
	/**
	 * <p>Returns true if this VCard contains LOGO types.</p>
	 * 
	 * @return true if this VCard contains LOGO types
	 */
	public boolean hasLogos();
	
	/**
	 * <p>Removes all LOGO types.</p>
	 */
	public void clearLogos();
	
	/**
	 * <p>Returns a list of AGENT types.</p>
	 * 
	 * @return a list of AGENT types
	 */
	public List<AgentType> getAgents();
	
	/**
	 * <p>Adds an AGENT type.</p>
	 * 
	 * @param agent - the AGENT type to add
	 * @throws NullPointerException if adding a null AGENT type
	 */
	public void addAgent(AgentType agent) throws NullPointerException;
	
	/**
	 * <p>Adds a list of AGENT types.</p>
	 * 
	 * @param agents - the list of AGENT types to add
	 * @throws NullPointerException if adding a null list of AGENT types
	 */
	public void addAllAgents(Collection<AgentType> agents) throws NullPointerException;
	
	/**
	 * <p>Removes the specified AGENT type.</p>
	 * 
	 * @param agent - the AGENT type to remove
	 * @return true if the specified AGENT type exists and was removed
	 * @throws NullPointerException if removing a null AGENT type
	 */
	public boolean removeAgent(AgentType agent) throws NullPointerException;
	
	/**
	 * <p>Returns true if the specified AGENT type exists.</p>
	 * 
	 * @param agent - the AGENT type to check
	 * @return true if the specified AGENT type exists or false otherwise
	 */
	public boolean containsAgent(AgentType agent);
	
	/**
	 * <p>Returns true if this VCard contains AGENT types.</p>
	 * 
	 * @return true if this VCard contains AGENT types
	 */
	public boolean hasAgents();
	
	/**
	 * <p>Removes all AGENT types.</p>
	 */
	public void clearAgents();
	
	/**
	 * <p>Returns the ORG type.</p>
	 * 
	 * @return the ORG type
	 */
	public OrgType getOrg();
	
	/**
	 * <p>Sets the ORG type.</p>
	 * 
	 * @param organization - the ORG type to set
	 */
	public void setOrg(OrgType organization);
	
	/**
	 * <p>Returns true if this VCard contains an ORG type.</p>
	 * 
	 * @return true if this VCard contains an ORG type
	 */
	public boolean hasOrg();
	
	/**
	 * <p>Removes the ORG type.</p>
	 */
	public void clearOrg();
	
	/**
	 * <p>Returns the CATEGORIES type.</p>
	 * 
	 * @return the CATEGORIES type
	 */
	public CategoriesType getCategories();
	
	/**
	 * <p>Sets the CATEGORIES type.</p>
	 * 
	 * @param categories - the CATEGORIES type to set
	 */
	public void setCategories(CategoriesType categories);
	
	/**
	 * <p>Returns true if this VCard contains a CATEGORIES type.</p>
	 * 
	 * @return true if this VCard contains a CATEGORIES type
	 */
	public boolean hasCategories();
	
	/**
	 * <p>Removes the CATEGORIES type.</p>
	 */
	public void clearCategories();
	
	/**
	 * <p>Returns a list of NOTE types.</p>
	 * 
	 * @return a list of NOTE types
	 */
	public List<NoteType> getNotes();
	
	/**
	 * <p>Adds a NOTE type.</p>
	 * 
	 * @param note - the NOTE type to add
	 * @throws NullPointerException if adding a null NOTE type
	 */
	public void addNote(NoteType note) throws NullPointerException;
	
	/**
	 * <p>Adds a list of NOTE types.</p>
	 * 
	 * @param notes - the list of NOTE types to add
	 * @throws NullPointerException if adding a null list of NOTE types
	 */
	public void addAllNotes(Collection<NoteType> notes) throws NullPointerException;
	
	/**
	 * <p>Removes the specified NOTE type.</p>
	 * 
	 * @param note - the NOTE type to remove
	 * @return true if the specified NOTE type exists and was removed
	 * @throws NullPointerException if removing a null NOTE type
	 */
	public boolean removeNote(NoteType note) throws NullPointerException;
	
	/**
	 * <p>Returns true if the specified NOTE type exists.</p>
	 * 
	 * @param note - the NOTE type to check
	 * @return true if the specified NOTE type exists
	 */
	public boolean containsNote(NoteType note);
	
	/**
	 * <p>Returns true if this VCard contains NOTE types.</p>
	 * 
	 * @return true if this VCard contains NOTE types
	 */
	public boolean hasNotes();
	
	/**
	 * <p>Removes all NOTE types.</p>
	 */
	public void clearNotes();
	
	/**
	 * <p>Returns the PRODID type.</p>
	 * 
	 * @return the PRODID type
	 */
	public ProdIdType getProdId();
	
	/**
	 * <p>Sets the PRODID type.</p>
	 * 
	 * @param productId - the PRODID type to set
	 */
	public void setProdId(ProdIdType productId);
	
	/**
	 * <p>Returns true if this VCard contains a PRODID type.</p>
	 * 
	 * @return true if this VCard contains a PRODID type
	 */
	public boolean hasProdId();
	
	/**
	 * <p>Removes the PRODID type.</p>
	 */
	public void clearProdId();
	
	/**
	 * <p>Returns the REV type.</p>
	 * 
	 * @return the REV type
	 */
	public RevType getRev();
	
	/**
	 * <p>Sets the REV type.</p>
	 * 
	 * @param revision - the REV type to set
	 */
	public void setRev(RevType revision);
	
	/**
	 * <p>Returns true if this VCard contains a REV type.</p>
	 * 
	 * @return true if this VCard contains a REV type
	 */
	public boolean hasRev();
	
	/**
	 * <p>Removes the REV type.</p>
	 */
	public void clearRev();
	
	/**
	 * <p>Returns the SORTSTRING type.</p>
	 * 
	 * @return the SORTSTRING type
	 */
	public SortStringType getSortString();
	
	/**
	 * <p>Sets the SORTSTRING type.</p>
	 * 
	 * @param sortString - the SORTSTRING type to set
	 */
	public void setSortString(SortStringType sortString);
	
	/**
	 * <p>Returns true if this VCard contains a SORTSRING type.</p>
	 * 
	 * @return true if this VCard contains a SORTSTRING type
	 */
	public boolean hasSortString();
	
	/**
	 * <p>Removes the SORTSTRING type.</p>
	 */
	public void clearSortString();
	
	/**
	 * <p>Returns a list of SOUND types.</p>
	 * 
	 * @return a list of SOUND types.</p>
	 */
	public List<SoundType> getSounds();
	
	/**
	 * <p>Adds a SOUND type.</p>
	 * 
	 * @param sound - the SOUND type to add
	 * @throws NullPointerException if adding a null SOUND type
	 */
	public void addSound(SoundType sound) throws NullPointerException;
	
	/**
	 * <p>Adds a list of SOUND types.</p>
	 * 
	 * @param sounds - the list of SOUND types to add
	 * @throws NullPointerException if adding a null list of SOUND types
	 */
	public void addAllSounds(Collection<SoundType> sounds) throws NullPointerException;
	
	/**
	 * <p>Removes the specified SOUND type.</p>
	 * 
	 * @param sound - the SOUND type to remove
	 * @return true if the specified SOUND type exists and was removed
	 * @throws NullPointerException if removing a null SOUND type
	 */
	public boolean removeSound(SoundType sound) throws NullPointerException;
	
	/**
	 * <p>Returns true if the specified SOUND type exists.</p>
	 * 
	 * @param sound - the SOUND type to check
	 * @return true if the specified SOUND type exists or false otherwise
	 */
	public boolean containsSound(SoundType sound);
	
	/**
	 * <p>Return true if this VCard contains SOUND types.</p>
	 * 
	 * @return true if this VCard contains SOUND types
	 */
	public boolean hasSounds();
	
	/**
	 * <p>Removes all SOUND types.</p>
	 */
	public void clearSounds();
	
	/**
	 * <p>Returns the UID type.</p>
	 * 
	 * @return the UID type
	 */
	public UidType getUid();
	
	/**
	 * <p>Sets the UID type.</p>
	 * 
	 * @param uid - the UID type to set
	 */
	public void setUid(UidType uid);
	
	/**
	 * <p>Returns true if this VCard contains the UID type.</p>
	 * 
	 * @return true if this VCard contains the UID type
	 */
	public boolean hasUid();
	
	/**
	 * <p>Removes the UID type.</p>
	 */
	public void clearUid();
	
	/**
	 * <p>Returns a list of URL types.</p>
	 * 
	 * @return a list of URL types
	 */
	public List<UrlType> getUrls();
	
	/**
	 * <p>Adds a URL type.</p>
	 * 
	 * @param url - the URL type to add
	 * @throws NullPointerException if adding a null URL type
	 */
	public void addUrl(UrlType url) throws NullPointerException;
	
	/**
	 * <p>Adds a list of URL types.</p>
	 * 
	 * @param urls - the list of URL types to add
	 * @throws NullPointerException if adding a null list of URL types
	 */
	public void addAllUrls(Collection<UrlType> urls) throws NullPointerException;
	
	/**
	 * <p>Removes the specified URL type.</p>
	 * 
	 * @param url - the URL type to remove
	 * @return true if the specified URL type exists and was removed
	 * @throws NullPointerException if removing a null URL type
	 */
	public boolean removeUrl(UrlType url) throws NullPointerException;
	
	/**
	 * <p>Returns true if the specified URL type exists.</p>
	 * 
	 * @param url -the URL type to check
	 * @return true if the specified URL type exists or false otherwise
	 */
	public boolean containsUrl(UrlType url);
	
	/**
	 * <p>Returns true if this VCard contains URL types.</p>
	 * 
	 * @return true if this VCard contains URL types
	 */
	public boolean hasUrls();
	
	/**
	 * <p>Removes all URL types.</p>
	 */
	public void clearUrls();
	
	/**
	 * <p>Retrieves a list of IMPP type.</p>
	 * 
	 * @return a list of IMPP types or null if not set.</p>
	 */
	public List<ImppType> getIMPPs();
	
	/**
	 * <p>Adds an IMPP type.</p>
	 * 
	 * @param impp - the IMPP type
	 * @throws NullPointerException if adding a null IMPP type
	 */
	public void addImpp(ImppType impp) throws NullPointerException;
	
	/**
	 * <p>Adds a list of IMPP types.</p>
	 * 
	 * @param impps - the list of IMPP types to add.
	 */
	public void addAllImpp(Collection<ImppType> impps);
	
	/**
	 * <p>Removes the specified IMPP type.</p>
	 * 
	 * @param impp - the IMPP type to remove
	 * @return true if the IMPP type exists and was removed
	 * @throws NullPointerException if removing a null IMPP type
	 */
	public boolean removeImpp(ImppType impp) throws NullPointerException;
	
	/**
	 * <p>Indicates if the specified IMPP type exists.</p>
	 * 
	 * @param impp - the IMPP type to check
	 * @return true if the specified IMPP type exists
	 */
	public boolean containsImpp(ImppType impp);
	
	/**
	 * <p>Indicates if this VCard has IMPP types.</p>
	 * 
	 * @return true if this VCard has IMPP types or false otherwise
	 */
	public boolean hasImpps();
	
	/**
	 * <p>Removes all IMPP types.</p>
	 */
	public void clearImpp();
	
	/**
	 * <p>Retrieves the Version type of this VCard.</p>
	 * 
	 * @return the Version type of this VCard
	 */
	public VersionType getVersion();
	
	/**
	 * <p>Sets the Version type.</p>
	 * 
	 * @param version - the Version type to set
	 * @throws NullPointerException if setting a null Version type
	 */
	public void setVersion(VersionType version) throws NullPointerException;
	
	/**
	 * <p>Retrieves the Class type.</p>
	 * 
	 * @return the Class type or null if not set
	 */
	public ClassType getSecurityClass();
	
	/**
	 * <p>Sets the Class type.</p>
	 * 
	 * @param securityClass - the Class type to set
	 */
	public void setSecurityClass(ClassType securityClass);
	
	/**
	 * <p>Indicates if this VCard has a Class type set.</p>
	 * 
	 * @return true if this VCard has a Class type set or false otherwise
	 */
	public boolean hasSecurityClass();
	
	/**
	 * <p>Removes the Class type.</p>
	 */
	public void clearSecurityClass();
	
	/**
	 * <p>Retrieves a list of Key types.</p>
	 * 
	 * @return a list of Key types or null if none set.
	 */
	public List<KeyType> getKeys();
	
	/**
	 * <p>Adds a Key type.</p>
	 * 
	 * @param key - the Key type to add
	 * @throws NullPointerException if adding a null Key type
	 */
	public void addKey(KeyType key) throws NullPointerException;
	
	/**
	 * <p>Adds a list of Key types.</p>
	 * 
	 * @param keys - the list of Key type to add
	 * @throws NullPointerException if adding a null list of Key types
	 */
	public void addAllKeys(Collection<KeyType> keys) throws NullPointerException;
	
	/**
	 * <p>Removes the specified Key type.</p>
	 * 
	 * @param key - the Key type to remove
	 * @return true if the specified Key type exists and was removed
	 * @throws NullPointerException if removing a null Key type
	 */
	public boolean removeKey(KeyType key) throws NullPointerException;
	
	/**
	 * <p>Indicates if this VCard contains the specified Key type.</p>
	 * 
	 * @param key - the Key type to check
	 * @return true if this VCard contains the specified Key type or false otherwise
	 */
	public boolean containsKey(KeyType key);
	
	/**
	 * <p>Indicates if this VCard has Key types.</p>
	 * 
	 * @return true if this VCard has Key types
	 */
	public boolean hasKeys();
	
	/**
	 * <p>Removes all Key types.</p>
	 */
	public void clearKeys();
	
	/**
	 * <p>Retrieves a list of Extended types.</p>
	 * 
	 * @return a list of Extended types or null if none set
	 */
	public List<ExtendedType> getExtendedTypes();
	
	/**
	 * <p>Adds an Extended type.</p>
	 * 
	 * @param extension - the Extended type to add
	 * @throws NullPointerException if adding a null Extended type
	 */
	public void addExtendedType(ExtendedType extension) throws NullPointerException;
	
	/**
	 * <p>Adds a list of Extended types.</p>
	 * 
	 * @param extensions - the list of Extended types to add
	 * @throws NullPointerException if adding a null list of Extended types
	 */
	public void addAllExtendedTypes(Collection<ExtendedType> extensions) throws NullPointerException;
	
	/**
	 * <p>Removes the specified Extended type.</p>
	 * 
	 * @param extension - the Extended type to remove
	 * @return true if the specified Extended type exists and was removed
	 * @throws NullPointerException if removing a null Extended type
	 */
	public boolean removeExtendedType(ExtendedType extension) throws NullPointerException;
	
	/**
	 * <p>Returns true if the specified Extended type exists.</p>
	 * 
	 * @param extension - the Extended type to check
	 * @return true if the specified extended type exists
	 */
	public boolean containsExtendedType(ExtendedType extension);
	
	/**
	 * <p>Indicates if this VCard has Extended types.</p>
	 * 
	 * @return true if this VCard has Extended types or false otherwise
	 */
	public boolean hasExtendedTypes();
	
	/**
	 * <p>Removes all Extended types.</p>
	 */
	public void clearExtendedTypes();
	
	/**
	 * <p>Removes ALL types including mandatory types BEGIN, VERSION and END</p>
	 */
	public void clear();
}