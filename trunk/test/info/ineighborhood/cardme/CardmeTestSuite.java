package info.ineighborhood.cardme;

import info.ineighborhood.cardme.io.VCardWriterTest;
import info.ineighborhood.cardme.util.VCardUtilsTest;
import info.ineighborhood.cardme.vcard.types.AddressTypeTest;
import info.ineighborhood.cardme.vcard.types.AgentTypeTest;
import info.ineighborhood.cardme.vcard.types.BeginTypeTest;
import info.ineighborhood.cardme.vcard.types.BirthdayTypeTest;
import info.ineighborhood.cardme.vcard.types.CategoriesTypeTest;
import info.ineighborhood.cardme.vcard.types.ClassTypeTest;
import info.ineighborhood.cardme.vcard.types.DisplayableNameTypeTest;
import info.ineighborhood.cardme.vcard.types.EmailTypeTest;
import info.ineighborhood.cardme.vcard.types.EndTypeTest;
import info.ineighborhood.cardme.vcard.types.ExtendedTypeTest;
import info.ineighborhood.cardme.vcard.types.FormattedNameTypeTest;
import info.ineighborhood.cardme.vcard.types.GeographicPositionTypeTest;
import info.ineighborhood.cardme.vcard.types.KeyTypeTest;
import info.ineighborhood.cardme.vcard.types.LabelTypeTest;
import info.ineighborhood.cardme.vcard.types.LogoTypeTest;
import info.ineighborhood.cardme.vcard.types.MailerTypeTest;
import info.ineighborhood.cardme.vcard.types.NameTypeTest;
import info.ineighborhood.cardme.vcard.types.NicknameTypeTest;
import info.ineighborhood.cardme.vcard.types.NoteTypeTest;
import info.ineighborhood.cardme.vcard.types.OrganizationTypeTest;
import info.ineighborhood.cardme.vcard.types.PhotoTypeTest;
import info.ineighborhood.cardme.vcard.types.ProductIdTypeTest;
import info.ineighborhood.cardme.vcard.types.ProfileTypeTest;
import info.ineighborhood.cardme.vcard.types.RoleTypeTest;
import info.ineighborhood.cardme.vcard.types.SortStringTypeTest;
import info.ineighborhood.cardme.vcard.types.SoundTypeTest;
import info.ineighborhood.cardme.vcard.types.SourceTypeTest;
import info.ineighborhood.cardme.vcard.types.TelephoneTypeTest;
import info.ineighborhood.cardme.vcard.types.TimeZoneTypeTest;
import info.ineighborhood.cardme.vcard.types.TitleTypeTest;
import info.ineighborhood.cardme.vcard.types.UIDTypeTest;
import info.ineighborhood.cardme.vcard.types.URLTypeTest;
import info.ineighborhood.cardme.vcard.types.VersionTypeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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

@RunWith(Suite.class)
@Suite.SuiteClasses(
	{
		AddressTypeTest.class,
		AgentTypeTest.class,
		BeginTypeTest.class,
		BirthdayTypeTest.class,
		CategoriesTypeTest.class,
		ClassTypeTest.class,
		DisplayableNameTypeTest.class,
		EmailTypeTest.class,
		EndTypeTest.class,
		ExtendedTypeTest.class,
		FormattedNameTypeTest.class,
		GeographicPositionTypeTest.class,
		KeyTypeTest.class,
		LabelTypeTest.class,
		LogoTypeTest.class,
		MailerTypeTest.class,
		NameTypeTest.class,
		NicknameTypeTest.class,
		NoteTypeTest.class,
		OrganizationTypeTest.class,
		PhotoTypeTest.class,
		ProductIdTypeTest.class,
		ProfileTypeTest.class,
		RoleTypeTest.class,
		SortStringTypeTest.class,
		SoundTypeTest.class,
		SourceTypeTest.class,
		TelephoneTypeTest.class,
		TimeZoneTypeTest.class,
		TitleTypeTest.class,
		UIDTypeTest.class,
		URLTypeTest.class,
		VersionTypeTest.class,
		TestVCard.class,
		TestVcardFieldMarshalling.class,
		VCardUtilsTest.class,
		VCardWriterTest.class
	}
)

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Oct 1, 2011
 *
 */
public class CardmeTestSuite {

}
