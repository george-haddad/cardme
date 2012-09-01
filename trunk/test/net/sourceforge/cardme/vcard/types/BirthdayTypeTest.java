package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Calendar;
import net.sourceforge.cardme.util.ISOFormat;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
 * @author George El-Haddad
 * <br/>
 * Aug 24, 2012
 *
 */
public class BirthdayTypeTest {
	
	private BDayType bdayType = null;
	
	@Before
	public void setUp() throws Exception {
		bdayType = new BDayType();
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, 1980);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 10);
		
		bdayType.setBirthday(cal);
		bdayType.setISO8601Format(ISOFormat.ISO8601_DATE_EXTENDED);
	}
	
	@After
	public void tearDown() throws Exception {
		bdayType = null;
	}
	
	@Test
	public void testGetBirthday() {
		Calendar birthday = bdayType.getBirthday();
		assertEquals(1980, birthday.get(Calendar.YEAR));
		assertEquals(Calendar.MARCH, birthday.get(Calendar.MONTH));
		assertEquals(10, birthday.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.BDAY.getType(), bdayType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		BDayType bdayType2 = new BDayType();
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, 1980);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 10);
		
		bdayType2.setBirthday(cal);
		
		assertTrue(bdayType.equals(bdayType2));
	}
	
	@Test
	public void testCompareTo() {
		BDayType bdayType2 = new BDayType();
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, 1980);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 10);
		
		bdayType2.setBirthday(cal);
		
		assertTrue(bdayType.compareTo(bdayType2) == 0);
	}
	
	@Test
	public void testHashcode() {
		BDayType bdayType2 = new BDayType();
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, 1980);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 10);
		
		bdayType2.setBirthday(cal);
		
		int hcode1 = bdayType.hashCode();
		int hcode2 = bdayType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		BDayType cloned = bdayType.clone();
		assertEquals(cloned, bdayType);
		assertTrue(bdayType.equals(cloned));
		assertTrue(bdayType.compareTo(cloned) == 0);
	}
}
