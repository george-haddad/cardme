package net.sourceforge.cardme.vcard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.arch.VCardType;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.arch.VCardVersion;
import net.sourceforge.cardme.vcard.errors.ErrorSeverity;
import net.sourceforge.cardme.vcard.errors.VCardError;
import net.sourceforge.cardme.vcard.errors.VCardErrorHandler;
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
 * Copyright 2012 George El-Haddad. All rights reserved.
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
 */
public class VCardImpl implements VCard, VCardErrorHandler, Comparable<VCardImpl>, Cloneable, Serializable {

	private static final long serialVersionUID = 8271692650283111532L;
	
	private final Map<VCardTypeName, VCardType> TYPES_TABLE = new Hashtable<VCardTypeName, VCardType>();
	private final Map<VCardTypeName, List<VCardType>> TYPES_LIST_TABLE = new Hashtable<VCardTypeName, List<VCardType>>();
	private final List<VCardError> VCARD_ERRORS = new ArrayList<VCardError>();
	private ErrorSeverity errorSeverity = ErrorSeverity.NONE;
	private boolean throwsExceptions = false;
	
	public VCardImpl() {
		setBegin(new BeginType());
		setVersion(new VersionType(VCardVersion.V3_0));
		setEnd(new EndType());
	}
	
	public VCardImpl(VersionType version) {
		setBegin(new BeginType());
		setVersion(version);
		setEnd(new EndType());
	}
	
	public VCardImpl(NType n, FNType fn) {
		setBegin(new BeginType());
		setVersion(new VersionType(VCardVersion.V3_0));
		setN(n);
		setFN(fn);
		setEnd(new EndType());
	}

	@Override
	public BeginType getBegin()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.BEGIN)) {
			return (BeginType)TYPES_TABLE.get(VCardTypeName.BEGIN);
		}
		else {
			return null;
		}
	}

	@Override
	public void setBegin(BeginType begin) throws NullPointerException {
		if(begin == null) {
			throw new NullPointerException("VCard cannot have a null Begin type.");
		}
		
		TYPES_TABLE.put(VCardTypeName.BEGIN, begin);
		
	}

	@Override
	public EndType getEnd()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.END)) {
			return (EndType)TYPES_TABLE.get(VCardTypeName.END);
		}
		else {
			return null;
		}
	}

	@Override
	public void setEnd(EndType end) throws NullPointerException {
		if(end == null) {
			throw new NullPointerException("VCard cannot have a null End type.");
		}
		
		TYPES_TABLE.put(VCardTypeName.END, end);
	}

	@Override
	public NameType getName()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.NAME)) {
			return (NameType)TYPES_TABLE.get(VCardTypeName.NAME);
		}
		else {
			return null;
		}
	}

	@Override
	public void setName(NameType name) {
		if(name != null) {
			TYPES_TABLE.put(VCardTypeName.NAME, name);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.NAME)) {
				TYPES_TABLE.remove(VCardTypeName.NAME);
			}
		}
	}

	@Override
	public boolean hasName()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.NAME);
	}

	@Override
	public void clearName() {
		if(TYPES_TABLE.containsKey(VCardTypeName.NAME)) {
			TYPES_TABLE.remove(VCardTypeName.NAME);
		}
	}

	@Override
	public ProfileType getProfile()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.PROFILE)) {
			return (ProfileType)TYPES_TABLE.get(VCardTypeName.PROFILE);
		}
		else {
			return null;
		}
	}

	@Override
	public void setProfile(ProfileType profile) {
		if(profile != null) {
			TYPES_TABLE.put(VCardTypeName.PROFILE, profile);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.PROFILE)) {
				TYPES_TABLE.remove(VCardTypeName.PROFILE);
			}
		}
	}

	@Override
	public boolean hasProfile()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.PROFILE);
	}
	
	@Override
	public void clearProfile() {
		if(TYPES_TABLE.containsKey(VCardTypeName.PROFILE)) {
			TYPES_TABLE.remove(VCardTypeName.PROFILE);
		}
	}
	
	@Override
	public SourceType getSource()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.SOURCE)) {
			return (SourceType)TYPES_TABLE.get(VCardTypeName.SOURCE);
		}
		else {
			return null;
		}
	}

	@Override
	public void setSource(SourceType source) {
		if(source != null) {
			TYPES_TABLE.put(VCardTypeName.SOURCE, source);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.SOURCE)) {
				TYPES_TABLE.remove(VCardTypeName.SOURCE);
			}
		}
	}

	@Override
	public boolean hasSource()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.SOURCE);
	}
	
	@Override
	public void clearSource() {
		if(TYPES_TABLE.containsKey(VCardTypeName.SOURCE)) {
			TYPES_TABLE.remove(VCardTypeName.SOURCE);
		}
	}

	@Override
	public FNType getFN()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.FN)) {
			return (FNType)TYPES_TABLE.get(VCardTypeName.FN);
		}
		else {
			return null;
		}
	}

	@Override
	public void setFN(FNType fn) throws NullPointerException {
		if(fn != null) {
			TYPES_TABLE.put(VCardTypeName.FN, fn);
		}
		else {
			throw new NullPointerException("VCard must have a FN type and cannot be set to null.");
		}
	}

	@Override
	public boolean hasFN()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.FN);
	}

	@Override
	public NType getN()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.N)) {
			return (NType)TYPES_TABLE.get(VCardTypeName.N);
		}
		else {
			return null;
		}
	}

	@Override
	public void setN(NType n) throws NullPointerException {
		if(n != null) {
			TYPES_TABLE.put(VCardTypeName.N, n);
		}
		else {
			throw new NullPointerException("VCard must have a N type and cannot be set to null.");
		}
	}

	@Override
	public boolean hasN()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.N);
	}

	@Override
	public NicknameType getNicknames()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.NICKNAME)) {
			return (NicknameType)TYPES_TABLE.get(VCardTypeName.NICKNAME);
		}
		else {
			return null;
		}
	}
	
	@Override
	public void setNickname(NicknameType nicknames) {
		if(nicknames != null) {
			TYPES_TABLE.put(VCardTypeName.NICKNAME, nicknames);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.NICKNAME)) {
				TYPES_TABLE.remove(VCardTypeName.NICKNAME);
			}
		}
	}

	@Override
	public boolean hasNicknames()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.NICKNAME);
	}

	@Override
	public void clearNickname() {
		if(TYPES_TABLE.containsKey(VCardTypeName.NICKNAME)) {
			TYPES_TABLE.remove(VCardTypeName.NICKNAME);
		}
	}

	@Override
	public List<PhotoType> getPhotos()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.PHOTO)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.PHOTO);
			List<PhotoType> photoList = new ArrayList<PhotoType>(list.size());
			
			for(VCardType vCardType : list) {
				photoList.add((PhotoType)vCardType);
			}
			
			return photoList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addPhoto(PhotoType photo) throws NullPointerException {
		if(photo != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.PHOTO)) {
				TYPES_LIST_TABLE.get(VCardTypeName.PHOTO).add(photo);
			}
			else {
				List<VCardType> newPhotoList = new ArrayList<VCardType>();
				newPhotoList.add(photo);
				TYPES_LIST_TABLE.put(VCardTypeName.PHOTO, newPhotoList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null photo type.");
		}
	}

	@Override
	public void addAllPhotos(Collection<PhotoType> photos) throws NullPointerException {
		if(photos != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.PHOTO)) {
				TYPES_LIST_TABLE.get(VCardTypeName.PHOTO).addAll(photos);
			}
			else {
				List<VCardType> newPhotoList = new ArrayList<VCardType>();
				newPhotoList.addAll(photos);
				TYPES_LIST_TABLE.put(VCardTypeName.PHOTO, newPhotoList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of photo types.");
		}
	}

	@Override
	public boolean removePhoto(PhotoType photo) throws NullPointerException {
		if(photo != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.PHOTO)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.PHOTO).remove(photo);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsPhoto(PhotoType photo) {
		if(photo != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.PHOTO)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.PHOTO).contains(photo);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasPhotos()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.PHOTO)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.PHOTO).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public void clearPhotos() {
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.PHOTO)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.PHOTO);
		}
	}

	@Override
	public BDayType getBDay()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.BDAY)) {
			return (BDayType)TYPES_TABLE.get(VCardTypeName.BDAY);
		}
		else {
			return null;
		}
	}

	@Override
	public void setBDay(BDayType bday) {
		if(bday != null) {
			TYPES_TABLE.put(VCardTypeName.BDAY, bday);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.BDAY)) {
				TYPES_TABLE.remove(VCardTypeName.BDAY);
			}
		}
	}

	@Override
	public boolean hasBDay()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.BDAY);
	}

	@Override
	public void clearBDay()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.BDAY)) {
			TYPES_TABLE.remove(VCardTypeName.BDAY);
		}
	}

	@Override
	public List<AdrType> getAdrs()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.ADR)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.ADR);
			List<AdrType> adrList = new ArrayList<AdrType>(list.size());
			
			for(VCardType vCardType : list) {
				adrList.add((AdrType)vCardType);
			}
			
			return adrList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addAdr(AdrType adr) throws NullPointerException {
		if(adr != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.ADR)) {
				TYPES_LIST_TABLE.get(VCardTypeName.ADR).add(adr);
			}
			else {
				List<VCardType> newAdrList = new ArrayList<VCardType>();
				newAdrList.add(adr);
				TYPES_LIST_TABLE.put(VCardTypeName.ADR, newAdrList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null adr type.");
		}
	}

	@Override
	public void addAllAdrs(Collection<AdrType> adrs) throws NullPointerException {
		if(adrs != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.ADR)) {
				TYPES_LIST_TABLE.get(VCardTypeName.ADR).addAll(adrs);
			}
			else {
				List<VCardType> newAdrList = new ArrayList<VCardType>();
				newAdrList.addAll(adrs);
				TYPES_LIST_TABLE.put(VCardTypeName.ADR, newAdrList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of adr types.");
		}
	}

	@Override
	public boolean removeAdr(AdrType adr) throws NullPointerException
	{
		if(adr != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.ADR)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.ADR).remove(adr);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsAdr(AdrType adr)
	{
		if(adr != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.ADR)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.ADR).contains(adr);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasAdrs()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.ADR)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.ADR).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public void clearAdrs()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.ADR)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.ADR);
		}
	}

	@Override
	public List<LabelType> getLables()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.ADR)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.ADR);
			List<LabelType> labelList = new ArrayList<LabelType>(list.size());
			
			for(VCardType vCardType : list) {
				AdrType adr = (AdrType)vCardType;
				if(adr.hasLabel()) {
					labelList.add(adr.getLabel());
				}
			}
			
			return labelList;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean containsLabel(LabelType label)
	{
		boolean found = false;
		
		if(label != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.ADR)) {
				List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.ADR);
				for(VCardType vCardType : list) {
					AdrType adr = (AdrType)vCardType;
					if(adr.hasLabel()) {
						if(label.equals(adr.getLabel())) {
							found = true;
							break;
						}
					}
				}
				
				return found;
			}
			else {
				return found;
			}
		}
		else {
			return found;
		}
	}

	@Override
	public void clearLabels()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.ADR)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.ADR);
			for(VCardType vCardType : list) {
				AdrType adr = (AdrType)vCardType;
				adr.clearLabel();
			}
		}
	}

	@Override
	public List<TelType> getTels()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.TEL)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.TEL);
			List<TelType> telList = new ArrayList<TelType>(list.size());
			
			for(VCardType vCardType : list) {
				telList.add((TelType)vCardType);
			}
			
			return telList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addTel(TelType tel) throws NullPointerException {
		if(tel != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.TEL)) {
				TYPES_LIST_TABLE.get(VCardTypeName.TEL).add(tel);
			}
			else {
				List<VCardType> newTelList = new ArrayList<VCardType>();
				newTelList.add(tel);
				TYPES_LIST_TABLE.put(VCardTypeName.TEL, newTelList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null tel type.");
		}
	}

	@Override
	public void addAllTels(Collection<TelType> tels) throws NullPointerException {
		if(tels != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.TEL)) {
				TYPES_LIST_TABLE.get(VCardTypeName.TEL).addAll(tels);
			}
			else {
				List<VCardType> newTelList = new ArrayList<VCardType>();
				newTelList.addAll(tels);
				TYPES_LIST_TABLE.put(VCardTypeName.TEL, newTelList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of tel types.");
		}
	}

	@Override
	public boolean removeTel(TelType tel) throws NullPointerException
	{
		if(tel != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.TEL)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.TEL).remove(tel);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsTel(TelType tel)
	{
		if(tel != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.TEL)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.TEL).contains(tel);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public void clearTel() {
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.TEL)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.TEL);
		}
	}

	@Override
	public boolean hasTels()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.TEL)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.TEL).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public List<EmailType> getEmails()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.EMAIL)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.EMAIL);
			List<EmailType> emailList = new ArrayList<EmailType>(list.size());
			
			for(VCardType vCardType : list) {
				emailList.add((EmailType)vCardType);
			}
			
			return emailList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addEmail(EmailType email) {
		if(email != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.EMAIL)) {
				TYPES_LIST_TABLE.get(VCardTypeName.EMAIL).add(email);
			}
			else {
				List<VCardType> newEmailList = new ArrayList<VCardType>();
				newEmailList.add(email);
				TYPES_LIST_TABLE.put(VCardTypeName.EMAIL, newEmailList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null email type.");
		}
	}

	@Override
	public void addAllEmails(Collection<EmailType> emails) {
		if(emails != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.EMAIL)) {
				TYPES_LIST_TABLE.get(VCardTypeName.EMAIL).addAll(emails);
			}
			else {
				List<VCardType> newEmailList = new ArrayList<VCardType>();
				newEmailList.addAll(emails);
				TYPES_LIST_TABLE.put(VCardTypeName.EMAIL, newEmailList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of email types.");
		}
	}

	@Override
	public boolean removeEmail(EmailType email)
	{
		if(email != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.EMAIL)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.EMAIL).remove(email);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsEmail(EmailType email)
	{
		if(email != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.EMAIL)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.EMAIL).contains(email);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasEmails()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.EMAIL)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.EMAIL).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public void clearEmails() {
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.EMAIL)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.EMAIL);
		}
		
	}

	@Override
	public MailerType getMailer()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.MAILER)) {
			return (MailerType)TYPES_TABLE.get(VCardTypeName.MAILER);
		}
		else {
			return null;
		}
	}

	@Override
	public void setMailer(MailerType mailer) {
		if(mailer != null) {
			TYPES_TABLE.put(VCardTypeName.MAILER, mailer);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.MAILER)) {
				TYPES_TABLE.remove(VCardTypeName.MAILER);
			}
		}
	}

	@Override
	public boolean hasMailer()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.MAILER);
	}

	@Override
	public void clearMailer() {
		if(TYPES_TABLE.containsKey(VCardTypeName.MAILER)) {
			TYPES_TABLE.remove(VCardTypeName.MAILER);
		}
	}

	@Override
	public TzType getTz()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.TZ)) {
			return (TzType)TYPES_TABLE.get(VCardTypeName.TZ);
		}
		else {
			return null;
		}
	}

	@Override
	public void setTz(TzType timeZone) {
		if(timeZone != null) {
			TYPES_TABLE.put(VCardTypeName.TZ, timeZone);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.TZ)) {
				TYPES_TABLE.remove(VCardTypeName.TZ);
			}
		}
	}

	@Override
	public boolean hasTz()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.TZ);
	}

	@Override
	public void clearTz() {
		if(TYPES_TABLE.containsKey(VCardTypeName.TZ)) {
			TYPES_TABLE.remove(VCardTypeName.TZ);
		}
	}

	@Override
	public GeoType getGeo()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.GEO)) {
			return (GeoType)TYPES_TABLE.get(VCardTypeName.GEO);
		}
		else {
			return null;
		}
	}

	@Override
	public void setGeo(GeoType geo) {
		if(geo != null) {
			TYPES_TABLE.put(VCardTypeName.GEO, geo);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.GEO)) {
				TYPES_TABLE.remove(VCardTypeName.GEO);
			}
		}
	}

	@Override
	public boolean hasGeo()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.GEO);
	}

	@Override
	public void clearGeo() {
		if(TYPES_TABLE.containsKey(VCardTypeName.GEO)) {
			TYPES_TABLE.remove(VCardTypeName.GEO);
		}
	}

	@Override
	public TitleType getTitle()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.TITLE)) {
			return (TitleType)TYPES_TABLE.get(VCardTypeName.TITLE);
		}
		else {
			return null;
		}
	}

	@Override
	public void setTitle(TitleType title) {
		if(title != null) {
			TYPES_TABLE.put(VCardTypeName.TITLE, title);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.TITLE)) {
				TYPES_TABLE.remove(VCardTypeName.TITLE);
			}
		}
	}

	@Override
	public boolean hasTitle()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.TITLE);
	}

	@Override
	public void clearTitle() {
		if(TYPES_TABLE.containsKey(VCardTypeName.TITLE)) {
			TYPES_TABLE.remove(VCardTypeName.TITLE);
		}
	}

	@Override
	public RoleType getRole()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.ROLE)) {
			return (RoleType)TYPES_TABLE.get(VCardTypeName.ROLE);
		}
		else {
			return null;
		}
	}

	@Override
	public void setRole(RoleType role) {
		if(role != null) {
			TYPES_TABLE.put(VCardTypeName.ROLE, role);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.ROLE)) {
				TYPES_TABLE.remove(VCardTypeName.ROLE);
			}
		}
	}

	@Override
	public boolean hasRole()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.ROLE);
	}

	@Override
	public void clearRole() {
		if(TYPES_TABLE.containsKey(VCardTypeName.ROLE)) {
			TYPES_TABLE.remove(VCardTypeName.ROLE);
		}
	}

	@Override
	public List<LogoType> getLogos()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.LOGO)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.LOGO);
			List<LogoType> logoList = new ArrayList<LogoType>(list.size());
			
			for(VCardType vCardType : list) {
				logoList.add((LogoType)vCardType);
			}
			
			return logoList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addLogo(LogoType logo) throws NullPointerException {
		if(logo != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.LOGO)) {
				TYPES_LIST_TABLE.get(VCardTypeName.LOGO).add(logo);
			}
			else {
				List<VCardType> newLogoList = new ArrayList<VCardType>();
				newLogoList.add(logo);
				TYPES_LIST_TABLE.put(VCardTypeName.LOGO, newLogoList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null logo type.");
		}
	}

	@Override
	public void addAllLogos(Collection<LogoType> logos) throws NullPointerException {
		if(logos != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.LOGO)) {
				TYPES_LIST_TABLE.get(VCardTypeName.LOGO).addAll(logos);
			}
			else {
				List<VCardType> newLogoList = new ArrayList<VCardType>();
				newLogoList.addAll(logos);
				TYPES_LIST_TABLE.put(VCardTypeName.LOGO, newLogoList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of logo types.");
		}
	}

	@Override
	public boolean removeLogo(LogoType logo) throws NullPointerException {
		if(logo != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.LOGO)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.LOGO).remove(logo);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsLogo(LogoType logo)
	{
		if(logo != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.LOGO)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.LOGO).contains(logo);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasLogos()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.LOGO)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.LOGO).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public void clearLogos() {
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.LOGO)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.LOGO);
		}
	}

	@Override
	public List<AgentType> getAgents()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.AGENT)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.AGENT);
			List<AgentType> agentList = new ArrayList<AgentType>(list.size());
			
			for(VCardType vCardType : list) {
				agentList.add((AgentType)vCardType);
			}
			
			return agentList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addAgent(AgentType agent) throws NullPointerException {
		if(agent != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.AGENT)) {
				TYPES_LIST_TABLE.get(VCardTypeName.AGENT).add(agent);
			}
			else {
				List<VCardType> newAgentList = new ArrayList<VCardType>();
				newAgentList.add(agent);
				TYPES_LIST_TABLE.put(VCardTypeName.AGENT, newAgentList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null agent type.");
		}
	}
	
	@Override
	public void addAllAgents(Collection<AgentType> agents) throws NullPointerException {
		if(agents != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.AGENT)) {
				TYPES_LIST_TABLE.get(VCardTypeName.AGENT).addAll(agents);
			}
			else {
				List<VCardType> newAgentList = new ArrayList<VCardType>();
				newAgentList.addAll(agents);
				TYPES_LIST_TABLE.put(VCardTypeName.AGENT, newAgentList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of agent types.");
		}
	}

	@Override
	public boolean removeAgent(AgentType agent) throws NullPointerException {
		if(agent != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.AGENT)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.AGENT).remove(agent);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsAgent(AgentType agent)
	{
		if(agent != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.AGENT)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.AGENT).contains(agent);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasAgents()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.AGENT)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.AGENT).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public void clearAgents() {
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.AGENT)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.AGENT);
		}
	}

	@Override
	public OrgType getOrg()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.ORG)) {
			return (OrgType)TYPES_TABLE.get(VCardTypeName.ORG);
		}
		else {
			return null;
		}
	}

	@Override
	public void setOrg(OrgType organization) {
		if(organization != null) {
			TYPES_TABLE.put(VCardTypeName.ORG, organization);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.ORG)) {
				TYPES_TABLE.remove(VCardTypeName.ORG);
			}
		}
	}

	@Override
	public boolean hasOrg()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.ORG);
	}

	@Override
	public void clearOrg() {
		if(TYPES_TABLE.containsKey(VCardTypeName.ORG)) {
			TYPES_TABLE.remove(VCardTypeName.ORG);
		}
	}

	@Override
	public CategoriesType getCategories()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.CATEGORIES)) {
			return (CategoriesType)TYPES_TABLE.get(VCardTypeName.CATEGORIES);
		}
		else {
			return null;
		}
	}

	@Override
	public void setCategories(CategoriesType categories) {
		if(categories != null) {
			TYPES_TABLE.put(VCardTypeName.CATEGORIES, categories);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.CATEGORIES)) {
				TYPES_TABLE.remove(VCardTypeName.CATEGORIES);
			}
		}
		
	}

	@Override
	public boolean hasCategories()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.CATEGORIES);
	}

	@Override
	public void clearCategories() {
		if(TYPES_TABLE.containsKey(VCardTypeName.CATEGORIES)) {
			TYPES_TABLE.remove(VCardTypeName.CATEGORIES);
		}
	}

	@Override
	public List<NoteType> getNotes()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.NOTE)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.NOTE);
			List<NoteType> noteList = new ArrayList<NoteType>(list.size());
			
			for(VCardType vCardType : list) {
				noteList.add((NoteType)vCardType);
			}
			
			return noteList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addNote(NoteType note) throws NullPointerException {
		if(note != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.NOTE)) {
				TYPES_LIST_TABLE.get(VCardTypeName.NOTE).add(note);
			}
			else {
				List<VCardType> newNoteList = new ArrayList<VCardType>();
				newNoteList.add(note);
				TYPES_LIST_TABLE.put(VCardTypeName.NOTE, newNoteList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null note type.");
		}
	}

	@Override
	public void addAllNotes(Collection<NoteType> notes) throws NullPointerException {
		if(notes != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.NOTE)) {
				TYPES_LIST_TABLE.get(VCardTypeName.NOTE).addAll(notes);
			}
			else {
				List<VCardType> newNoteList = new ArrayList<VCardType>();
				newNoteList.addAll(notes);
				TYPES_LIST_TABLE.put(VCardTypeName.NOTE, newNoteList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of note types.");
		}
	}

	@Override
	public boolean removeNote(NoteType note) throws NullPointerException
	{
		if(note != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.NOTE)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.NOTE).remove(note);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsNote(NoteType note)
	{
		if(note != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.NOTE)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.NOTE).contains(note);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasNotes()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.NOTE)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.NOTE).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public void clearNotes() {
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.NOTE)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.NOTE);
		}
	}

	@Override
	public ProdIdType getProdId()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.PRODID)) {
			return (ProdIdType)TYPES_TABLE.get(VCardTypeName.PRODID);
		}
		else {
			return null;
		}
	}

	@Override
	public void setProdId(ProdIdType productId) {
		if(productId != null) {
			TYPES_TABLE.put(VCardTypeName.PRODID, productId);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.PRODID)) {
				TYPES_TABLE.remove(VCardTypeName.PRODID);
			}
		}
	}

	@Override
	public boolean hasProdId()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.PRODID);
	}

	@Override
	public void clearProdId() {
		if(TYPES_TABLE.containsKey(VCardTypeName.PRODID)) {
			TYPES_TABLE.remove(VCardTypeName.PRODID);
		}
	}

	@Override
	public RevType getRev()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.REV)) {
			return (RevType)TYPES_TABLE.get(VCardTypeName.REV);
		}
		else {
			return null;
		}
	}

	@Override
	public void setRev(RevType revision) {
		if(revision != null) {
			TYPES_TABLE.put(VCardTypeName.REV, revision);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.REV)) {
				TYPES_TABLE.remove(VCardTypeName.REV);
			}
		}
	}

	@Override
	public boolean hasRev()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.REV);
	}

	@Override
	public void clearRev() {
		if(TYPES_TABLE.containsKey(VCardTypeName.REV)) {
			TYPES_TABLE.remove(VCardTypeName.REV);
		}
	}

	@Override
	public SortStringType getSortString()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.SORT_STRING)) {
			return (SortStringType)TYPES_TABLE.get(VCardTypeName.SORT_STRING);
		}
		else {
			return null;
		}
	}

	@Override
	public void setSortString(SortStringType sortString) {
		if(sortString != null) {
			TYPES_TABLE.put(VCardTypeName.SORT_STRING, sortString);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.SORT_STRING)) {
				TYPES_TABLE.remove(VCardTypeName.SORT_STRING);
			}
		}
	}

	@Override
	public boolean hasSortString()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.SORT_STRING);
	}

	@Override
	public void clearSortString() {
		if(TYPES_TABLE.containsKey(VCardTypeName.SORT_STRING)) {
			TYPES_TABLE.remove(VCardTypeName.SORT_STRING);
		}
	}

	@Override
	public List<SoundType> getSounds()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.SOUND)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.SOUND);
			List<SoundType> soundList = new ArrayList<SoundType>(list.size());
			
			for(VCardType vCardType : list) {
				soundList.add((SoundType)vCardType);
			}
			
			return soundList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addSound(SoundType sound) throws NullPointerException {
		if(sound != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.SOUND)) {
				TYPES_LIST_TABLE.get(VCardTypeName.SOUND).add(sound);
			}
			else {
				List<VCardType> newSoundList = new ArrayList<VCardType>();
				newSoundList.add(sound);
				TYPES_LIST_TABLE.put(VCardTypeName.SOUND, newSoundList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null sound type.");
		}
	}

	@Override
	public void addAllSounds(Collection<SoundType> sounds) throws NullPointerException {
		if(sounds != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.SOUND)) {
				TYPES_LIST_TABLE.get(VCardTypeName.SOUND).addAll(sounds);
			}
			else {
				List<VCardType> newSoundList = new ArrayList<VCardType>();
				newSoundList.addAll(sounds);
				TYPES_LIST_TABLE.put(VCardTypeName.SOUND, newSoundList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of sound types.");
		}
	}

	@Override
	public boolean removeSound(SoundType sound) throws NullPointerException
	{
		if(sound != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.SOUND)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.SOUND).remove(sound);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsSound(SoundType sound)
	{
		if(sound != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.SOUND)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.SOUND).contains(sound);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasSounds()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.SOUND)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.SOUND).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public void clearSounds() {
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.SOUND)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.SOUND);
		}
	}

	@Override
	public UidType getUid()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.UID)) {
			return (UidType)TYPES_TABLE.get(VCardTypeName.UID);
		}
		else {
			return null;
		}
	}

	@Override
	public void setUid(UidType uid) {
		if(uid != null) {
			TYPES_TABLE.put(VCardTypeName.UID, uid);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.UID)) {
				TYPES_TABLE.remove(VCardTypeName.UID);
			}
		}
	}

	@Override
	public boolean hasUid()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.UID);
	}

	@Override
	public void clearUid()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.UID)) {
			TYPES_TABLE.remove(VCardTypeName.UID);
		}
	}

	@Override
	public List<UrlType> getUrls()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.URL)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.URL);
			List<UrlType> urlList = new ArrayList<UrlType>(list.size());
			
			for(VCardType vCardType : list) {
				urlList.add((UrlType)vCardType);
			}
			
			return urlList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addUrl(UrlType url) throws NullPointerException {
		if(url != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.URL)) {
				TYPES_LIST_TABLE.get(VCardTypeName.URL).add(url);
			}
			else {
				List<VCardType> newUrlList = new ArrayList<VCardType>();
				newUrlList.add(url);
				TYPES_LIST_TABLE.put(VCardTypeName.URL, newUrlList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null url type.");
		}
	}

	@Override
	public void addAllUrls(Collection<UrlType> urls) throws NullPointerException {
		if(urls != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.URL)) {
				TYPES_LIST_TABLE.get(VCardTypeName.URL).addAll(urls);
			}
			else {
				List<VCardType> newUrlList = new ArrayList<VCardType>();
				newUrlList.addAll(urls);
				TYPES_LIST_TABLE.put(VCardTypeName.URL, newUrlList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of url types.");
		}
	}

	@Override
	public boolean removeUrl(UrlType url) throws NullPointerException
	{
		if(url != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.URL)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.URL).remove(url);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsUrl(UrlType url)
	{
		if(url != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.URL)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.URL).contains(url);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasUrls()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.URL)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.URL).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public void clearUrls() {
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.URL)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.URL);
		}
	}

	@Override
	public List<ImppType> getIMPPs()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.IMPP)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.IMPP);
			List<ImppType> imppList = new ArrayList<ImppType>(list.size());
			
			for(VCardType vCardType : list) {
				imppList.add((ImppType)vCardType);
			}
			
			return imppList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addImpp(ImppType impp) throws NullPointerException {
		if(impp != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.IMPP)) {
				TYPES_LIST_TABLE.get(VCardTypeName.IMPP).add(impp);
			}
			else {
				List<VCardType> newImppList = new ArrayList<VCardType>();
				newImppList.add(impp);
				TYPES_LIST_TABLE.put(VCardTypeName.IMPP, newImppList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null impp type.");
		}
	}
	
	@Override
	public void addAllImpp(Collection<ImppType> impps) throws NullPointerException {
		if(impps != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.IMPP)) {
				TYPES_LIST_TABLE.get(VCardTypeName.IMPP).addAll(impps);
			}
			else {
				List<VCardType> newImppList = new ArrayList<VCardType>();
				newImppList.addAll(impps);
				TYPES_LIST_TABLE.put(VCardTypeName.IMPP, newImppList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of impp types.");
		}
	}

	@Override
	public boolean removeImpp(ImppType impp) throws NullPointerException
	{
		if(impp != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.IMPP)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.IMPP).remove(impp);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsImpp(ImppType impp)
	{
		if(impp != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.IMPP)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.IMPP).contains(impp);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasImpps()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.IMPP)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.IMPP).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public void clearImpp() {
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.IMPP)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.IMPP);
		}
	}

	@Override
	public VersionType getVersion()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.VERSION)) {
			return (VersionType)TYPES_TABLE.get(VCardTypeName.VERSION);
		}
		else {
			return null;
		}
	}

	@Override
	public void setVersion(VersionType version) throws NullPointerException {
		if(version != null) {
			TYPES_TABLE.put(VCardTypeName.VERSION, version);
		}
		else {
			throw new NullPointerException("VCard must have a version type.");
		}
	}

	@Override
	public ClassType getSecurityClass()
	{
		if(TYPES_TABLE.containsKey(VCardTypeName.CLASS)) {
			return (ClassType)TYPES_TABLE.get(VCardTypeName.CLASS);
		}
		else {
			return null;
		}
	}

	@Override
	public void setSecurityClass(ClassType securityClass) {
		if(securityClass != null) {
			TYPES_TABLE.put(VCardTypeName.CLASS, securityClass);
		}
		else {
			if(TYPES_TABLE.containsKey(VCardTypeName.CLASS)) {
				TYPES_TABLE.remove(VCardTypeName.CLASS);
			}
		}
	}

	@Override
	public boolean hasSecurityClass()
	{
		return TYPES_TABLE.containsKey(VCardTypeName.CLASS);
	}

	@Override
	public void clearSecurityClass() {
		if(TYPES_TABLE.containsKey(VCardTypeName.CLASS)) {
			TYPES_TABLE.remove(VCardTypeName.CLASS);
		}
	}

	@Override
	public List<KeyType> getKeys()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.KEY)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.KEY);
			List<KeyType> keyList = new ArrayList<KeyType>(list.size());
			
			for(VCardType vCardType : list) {
				keyList.add((KeyType)vCardType);
			}
			
			return keyList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addKey(KeyType key) throws NullPointerException {
		if(key != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.KEY)) {
				TYPES_LIST_TABLE.get(VCardTypeName.KEY).add(key);
			}
			else {
				List<VCardType> newKeyList = new ArrayList<VCardType>();
				newKeyList.add(key);
				TYPES_LIST_TABLE.put(VCardTypeName.KEY, newKeyList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null key type.");
		}
	}

	@Override
	public void addAllKeys(Collection<KeyType> keys) throws NullPointerException {
		if(keys != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.KEY)) {
				TYPES_LIST_TABLE.get(VCardTypeName.KEY).addAll(keys);
			}
			else {
				List<VCardType> newKeyList = new ArrayList<VCardType>();
				newKeyList.addAll(keys);
				TYPES_LIST_TABLE.put(VCardTypeName.KEY, newKeyList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of key types.");
		}
	}

	@Override
	public boolean removeKey(KeyType key) throws NullPointerException
	{
		if(key != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.KEY)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.KEY).remove(key);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsKey(KeyType key)
	{
		if(key != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.KEY)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.KEY).contains(key);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasKeys()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.KEY)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.KEY).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public void clearKeys() {
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.KEY)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.KEY);
		}
	}

	@Override
	public List<ExtendedType> getExtendedTypes()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.XTENDED)) {
			List<VCardType> list = TYPES_LIST_TABLE.get(VCardTypeName.XTENDED);
			List<ExtendedType> xtendedList = new ArrayList<ExtendedType>(list.size());
			
			for(VCardType vCardType : list) {
				xtendedList.add((ExtendedType)vCardType);
			}
			
			return xtendedList;
		}
		else {
			return null;
		}
	}

	@Override
	public void addExtendedType(ExtendedType extension) throws NullPointerException {
		if(extension != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.XTENDED)) {
				TYPES_LIST_TABLE.get(VCardTypeName.XTENDED).add(extension);
			}
			else {
				List<VCardType> newXtendedList = new ArrayList<VCardType>();
				newXtendedList.add(extension);
				TYPES_LIST_TABLE.put(VCardTypeName.XTENDED, newXtendedList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null extended type.");
		}
	}

	@Override
	public void addAllExtendedTypes(Collection<ExtendedType> extensions) throws NullPointerException {
		if(extensions != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.XTENDED)) {
				TYPES_LIST_TABLE.get(VCardTypeName.XTENDED).addAll(extensions);
			}
			else {
				List<VCardType> newXtendedList = new ArrayList<VCardType>();
				newXtendedList.addAll(extensions);
				TYPES_LIST_TABLE.put(VCardTypeName.XTENDED, newXtendedList);
			}
		}
		else {
			throw new NullPointerException("Cannot add a null list of extended types.");
		}
	}

	@Override
	public boolean removeExtendedType(ExtendedType extension) throws NullPointerException
	{
		if(extension != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.XTENDED)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.XTENDED).remove(extension);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean containsExtendedType(ExtendedType extension)
	{
		if(extension != null) {
			if(TYPES_LIST_TABLE.containsKey(VCardTypeName.XTENDED)) {
				return TYPES_LIST_TABLE.get(VCardTypeName.XTENDED).contains(extension);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasExtendedTypes()
	{
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.XTENDED)) {
			return !TYPES_LIST_TABLE.get(VCardTypeName.XTENDED).isEmpty();
		}
		else {
			return false;
		}
	}

	@Override
	public void clearExtendedTypes() {
		if(TYPES_LIST_TABLE.containsKey(VCardTypeName.XTENDED)) {
			TYPES_LIST_TABLE.remove(VCardTypeName.XTENDED);
		}
	}

	@Override
	public void clear() {
		TYPES_TABLE.clear();
		TYPES_LIST_TABLE.clear();
	}

	// ----- VCardErrorHandler Interface Methods ---------------------------
	
	@Override
	public List<VCardError> getErrors()
	{
		return Collections.unmodifiableList(VCARD_ERRORS);
	}

	@Override
	public void addError(VCardError error) throws NullPointerException {
		if(error == null) {
			throw new NullPointerException("Cannot add a null VCardError.");
		}
		
		VCARD_ERRORS.add(error);
	}

	@Override
	public void addError(String errorMessage, ErrorSeverity severity, Throwable error) throws NullPointerException {
		if(errorMessage == null) {
			throw new NullPointerException("Cannot add a null errorMessage.");
		}
		
		VCARD_ERRORS.add(new VCardError(errorMessage, error, severity));
	}

	@Override
	public ErrorSeverity getErrorSeverity()
	{
		return errorSeverity;
	}

	@Override
	public void setThrowExceptions(boolean throwExceptions) {
		this.throwsExceptions = throwExceptions;
	}

	@Override
	public boolean isThrowExceptions()
	{
		return throwsExceptions;
	}

	@Override
	public boolean hasErrors()
	{
		return !VCARD_ERRORS.isEmpty();
	}

	@Override
	public void clearErrors() {
		VCARD_ERRORS.clear();
	}
	
	//---------------------------------------------------------
	
	@Override
	public VCardImpl clone()
	{
		VCardImpl cloned = new VCardImpl();
		cloned.setBegin(getBegin());
		cloned.setEnd(getEnd());
		cloned.setVersion(getVersion());
		cloned.setN(getN());
		cloned.setFN(getFN());
		
		cloned.setBDay(getBDay());
		cloned.setCategories(getCategories());
		cloned.setGeo(getGeo());
		cloned.setMailer(getMailer());
		cloned.setName(getName());
		cloned.setNickname(getNicknames());
		cloned.setOrg(getOrg());
		cloned.setProdId(getProdId());
		cloned.setProfile(getProfile());
		cloned.setRev(getRev());
		cloned.setRole(getRole());
		cloned.setSecurityClass(getSecurityClass());
		cloned.setSortString(getSortString());
		cloned.setSource(getSource());
		cloned.setTitle(getTitle());
		cloned.setTz(getTz());
		cloned.setUid(getUid());
		
		if(hasAdrs()) {
			cloned.addAllAdrs(getAdrs());
		}
		
		if(hasAgents()) {
			cloned.addAllAgents(getAgents());
		}
		
		if(hasEmails()) {
			cloned.addAllEmails(getEmails());
		}
		
		if(hasExtendedTypes()) {
			cloned.addAllExtendedTypes(getExtendedTypes());
		}
		
		if(hasImpps()) {
			cloned.addAllImpp(getIMPPs());
		}
		
		if(hasKeys()) {
			cloned.addAllKeys(getKeys());
		}
		
		if(hasLogos()) {
			cloned.addAllLogos(getLogos());
		}
		
		if(hasNotes()) {
			cloned.addAllNotes(getNotes());
		}
		
		if(hasPhotos()) {
			cloned.addAllPhotos(getPhotos());
		}
		
		if(hasSounds()) {
			cloned.addAllSounds(getSounds());
		}
		
		if(hasTels()) {
			cloned.addAllTels(getTels());
		}
		
		if(hasUrls()) {
			cloned.addAllUrls(getUrls());
		}
		
		cloned.throwsExceptions = throwsExceptions;
		cloned.errorSeverity = errorSeverity;
		for(VCardError vcardError: VCARD_ERRORS) {
			cloned.addError(vcardError);
		}
		
		return cloned;
	}
	
	private String[] getContents()
	{
		String[] contents = new String[37];
		contents[0] = (getBegin() != null ? getBegin().toString() : "");
		contents[1] = (getEnd() != null ? getEnd().toString() : "");
		contents[2] = (getVersion() != null ? getVersion().toString() : "");
		contents[3] = (getN() != null ? getN().toString() : "");
		contents[4] = (getFN() != null ?  getFN().toString() : "");
		contents[5] = (getBDay() != null ? getBDay().toString() : "");
		contents[6] = (getCategories() != null ? getCategories().toString() : "");
		contents[7] = (getGeo() != null ? getGeo().toString() : "");
		contents[8] = (getMailer() != null ? getMailer().toString() : "");
		contents[9] = (getName() != null ? getName().toString() : "");
		contents[10] = (getNicknames() != null ?  getNicknames().toString() : "");
		contents[11] = (getOrg() != null ? getOrg().toString() : "");
		contents[12] = (getProdId() != null ? getProdId().toString() : "");
		contents[13] = (getProfile() != null ? getProfile().toString() : "");
		contents[14] = (getRev() != null ? getRev().toString() : "");
		contents[15] = (getRole() != null ? getRole().toString() : "");
		contents[16] = (getSecurityClass() != null ? getSecurityClass().toString() : "");
		contents[17] = (getSortString() != null ? getSortString().toString() : "");
		contents[18] = (getSource() != null ? getSource().toString() : "");
		contents[19] = (getTitle() != null ? getTitle().toString() : "");
		contents[20] = (getTz() != null ? getTz().toString() : "");
		contents[21] = (getUid() != null ? getUid().toString() : "");
		
		if(hasAdrs()) {
			contents[22] = getAdrs().toString();
		}
		else {
			contents[22] = "";
		}
		
		if(hasAgents()) {
			contents[23] = getAgents().toString();
		}
		else {
			contents[23] = "";
		}
		
		
		if(hasEmails()) {
			contents[24] = getEmails().toString();
		}
		else {
			contents[24] = "";
		}
		
		if(hasExtendedTypes()) {
			contents[25] = getExtendedTypes().toString();
		}
		else {
			contents[25] = "";
		}
		
		if(hasImpps()) {
			contents[26] = getIMPPs().toString();
		}
		else {
			contents[26] = "";
		}
		
		if(hasKeys()) {
			contents[27] = getKeys().toString();
		}
		else {
			contents[27] = "";
		}
		
		if(hasLogos()) {
			contents[28] = getLogos().toString();
		}
		else {
			contents[28] = "";
		}
		
		if(hasNotes()) {
			contents[29] = getNotes().toString();
		}
		else {
			contents[29] = "";
		}
		
		if(hasPhotos()) {
			contents[30] = getPhotos().toString();
		}
		else {
			contents[30] = "";
		}
		
		if(hasSounds()) {
			contents[31] = getSounds().toString();
		}
		else {
			contents[31] = "";
		}
		
		if(hasTels()) {
			contents[32] = getTels().toString();
		}
		else {
			contents[32] = "";
		}
		
		if(hasUrls()) {
			contents[33] = getUrls().toString();
		}
		else {
			contents[33] = "";
		}
		
		contents[34] = String.valueOf(throwsExceptions);
		contents[35] = errorSeverity.toString();
		
		if(!VCARD_ERRORS.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(VCardError vcardError : VCARD_ERRORS) {
				sb.append(vcardError.toString());
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[36] = sb.toString();
		}
		else {
			contents[36] = "";
		}
		
		
		
		return contents;
	}
	
	@Override
	public int compareTo(VCardImpl obj)
	{
		if(obj != null) {
			String[] contents = obj.getContents();
			String[] myContents = getContents();
			if(Arrays.equals(myContents, contents)) {
				return 0;
			}
			else {
				return 1;
			}
		}
		else {
			return -1;
		}
	}
	
	@Override
	public int hashCode()
	{
		return Util.generateHashCode(getContents());
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof VCardImpl) {
				return this.compareTo((VCardImpl)obj) == 0;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString()
	{
		String[] contents = getContents();
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName());
		sb.append(" [");
		
		for(int i = 0; i < contents.length; i++) {
			sb.append(contents[i]);
			sb.append(",");
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
}
