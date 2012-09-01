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

	public BeginType getBegin();
	public void setBegin(BeginType begin) throws NullPointerException;
	
	public EndType getEnd();
	public void setEnd(EndType end) throws NullPointerException;
	
	public NameType getName();
	public void setName(NameType name);
	public boolean hasName();
	public void clearName();
	
	public ProfileType getProfile();
	public void setProfile(ProfileType profile);
	public boolean hasProfile();
	public void clearProfile();
	
	public SourceType getSource();
	public void setSource(SourceType source);
	public boolean hasSource();
	public void clearSource();
	
	public FNType getFN();
	public void setFN(FNType formattedName) throws NullPointerException;
	public boolean hasFN();
	
	public NType getN();
	public void setN(NType name) throws NullPointerException;
	public boolean hasN();
	
	public void setNickname(NicknameType nicknames);
	public NicknameType getNicknames();
	public boolean hasNicknames();
	public void clearNickname();
	
	public List<PhotoType> getPhotos();
	public void addPhoto(PhotoType photo) throws NullPointerException;
	public void addAllPhotos(Collection<PhotoType> photos) throws NullPointerException;
	public boolean removePhoto(PhotoType photo) throws NullPointerException;
	public boolean containsPhoto(PhotoType photo);
	public boolean hasPhotos();
	public void clearPhotos();
	
	public BDayType getBDay();
	public void setBDay(BDayType bday);
	public boolean hasBDay();
	public void clearBDay();
	
	public List<AdrType> getAdrs();
	public void addAdr(AdrType adr) throws NullPointerException;
	public void addAllAdrs(Collection<AdrType> adrs) throws NullPointerException;
	public boolean removeAdr(AdrType adr) throws NullPointerException;
	public boolean containsAdr(AdrType address);
	public boolean hasAdrs();
	public void clearAdrs();
	
	public List<LabelType> getLables();
	public boolean containsLabel(LabelType label);
	public void clearLabels();
	
	public List<TelType> getTels();
	public void addTel(TelType tel) throws NullPointerException;
	public void addAllTels(Collection<TelType> tels) throws NullPointerException;
	public boolean removeTel(TelType tel) throws NullPointerException;
	public boolean containsTel(TelType tel);
	public void clearTel();
	public boolean hasTels();
	
	public List<EmailType> getEmails();
	public void addEmail(EmailType email) throws NullPointerException;
	public void addAllEmails(Collection<EmailType> emailAddresses) throws NullPointerException;
	public boolean removeEmail(EmailType email) throws NullPointerException;
	public boolean containsEmail(EmailType email);
	public boolean hasEmails();
	public void clearEmails();
	
	public MailerType getMailer();
	public void setMailer(MailerType mailer);
	public boolean hasMailer();
	public void clearMailer();
	
	public TzType getTz();
	public void setTz(TzType timeZone);
	public boolean hasTz();
	public void clearTz();
	
	public GeoType getGeo();
	public void setGeo(GeoType geo);
	public boolean hasGeo();
	public void clearGeo();
	
	public TitleType getTitle();
	public void setTitle(TitleType title);
	public boolean hasTitle();
	public void clearTitle();
	
	public RoleType getRole();
	public void setRole(RoleType role);
	public boolean hasRole();
	public void clearRole();
	
	public List<LogoType> getLogos();
	public void addLogo(LogoType logo) throws NullPointerException;
	public void addAllLogos(Collection<LogoType> logos) throws NullPointerException;
	public boolean removeLogo(LogoType logo) throws NullPointerException;
	public boolean containsLogo(LogoType logo);
	public boolean hasLogos();
	public void clearLogos();
	
	public List<AgentType> getAgents();
	public void addAgent(AgentType agent) throws NullPointerException;
	public void addAllAgents(Collection<AgentType> agents) throws NullPointerException;
	public boolean removeAgent(AgentType agent) throws NullPointerException;
	public boolean containsAgent(AgentType agent);
	public boolean hasAgents();
	public void clearAgents();
	
	public OrgType getOrg();
	public void setOrg(OrgType organization);
	public boolean hasOrg();
	public void clearOrg();
	
	public CategoriesType getCategories();
	public void setCategories(CategoriesType categories);
	public boolean hasCategories();
	public void clearCategories();
	
	public List<NoteType> getNotes();
	public void addNote(NoteType note) throws NullPointerException;
	public void addAllNotes(Collection<NoteType> notes) throws NullPointerException;
	public boolean removeNote(NoteType note) throws NullPointerException;
	public boolean containsNote(NoteType note);
	public boolean hasNotes();
	public void clearNotes();
	
	public ProdIdType getProdId();
	public void setProdId(ProdIdType productId);
	public boolean hasProdId();
	public void clearProdId();
	
	public RevType getRev();
	public void setRev(RevType revision);
	public boolean hasRev();
	public void clearRev();
	
	public SortStringType getSortString();
	public void setSortString(SortStringType sortString);
	public boolean hasSortString();
	public void clearSortString();
	
	public List<SoundType> getSounds();
	public void addSound(SoundType sound) throws NullPointerException;
	public void addAllSounds(Collection<SoundType> sounds) throws NullPointerException;
	public boolean removeSound(SoundType sound) throws NullPointerException;
	public boolean containsSound(SoundType sound);
	public boolean hasSounds();
	public void clearSounds();
	
	public UidType getUid();
	public void setUid(UidType uid);
	public boolean hasUid();
	public void clearUid();
	
	public List<UrlType> getUrls();
	public void addUrl(UrlType url) throws NullPointerException;
	public void addAllUrls(Collection<UrlType> urls) throws NullPointerException;
	public boolean removeUrl(UrlType url) throws NullPointerException;
	public boolean containsUrl(UrlType url);
	public boolean hasUrls();
	public void clearUrls();
	
	public List<ImppType> getIMPPs();
	public void addImpp(ImppType impp) throws NullPointerException;
	public void addAllImpp(Collection<ImppType> impps);
	public boolean removeImpp(ImppType impp) throws NullPointerException;
	public boolean containsImpp(ImppType impp) throws NullPointerException;
	public boolean hasImpps();
	public void clearImpp();
	
	public VersionType getVersion();
	public void setVersion(VersionType version) throws NullPointerException;
	
	public ClassType getSecurityClass();
	public void setSecurityClass(ClassType securityClass);
	public boolean hasSecurityClass();
	public void clearSecurityClass();
	
	public List<KeyType> getKeys();
	public void addKey(KeyType key) throws NullPointerException;
	public void addAllKeys(Collection<KeyType> keys) throws NullPointerException;
	public boolean removeKey(KeyType key) throws NullPointerException;
	public boolean containsKey(KeyType key);
	public boolean hasKeys();
	public void clearKeys();
	
	public List<ExtendedType> getExtendedTypes();
	public void addExtendedType(ExtendedType extension) throws NullPointerException;
	public void addAllExtendedTypes(Collection<ExtendedType> extensions) throws NullPointerException;
	public boolean removeExtendedType(ExtendedType extension) throws NullPointerException;
	public boolean containsExtendedType(ExtendedType extension);
	public boolean hasExtendedTypes();
	public void clearExtendedTypes();
	
	public void clear();
}