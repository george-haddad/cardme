package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
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
public class ExtendedTypeTest {

	private ExtendedType extendedType = null;
	
	@Before
	public void setUp() throws Exception {
		extendedType = new ExtendedType();
		extendedType.setExtendedName("X-COLOR");
		extendedType.setExtendedValue("RED");
	}
	
	@After
	public void tearDown() throws Exception {
		extendedType = null;
	}
	
	@Test
	public void testGetExtendedName() {
		assertEquals("X-COLOR", extendedType.getExtendedName());
	}
	
	@Test
	public void testGetExtendedValue() {
		assertEquals("RED", extendedType.getExtendedValue());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetExtensionNameIllegalArgument() {
		extendedType.setExtendedName("COLOR");
	}
	
	@Test
	public void testSetExtendedNameSecure() {
		String color = "X-COLOR";
		extendedType.setExtendedName(color);
		
		String colorCopy = extendedType.getExtendedName();
		assertFalse(color == colorCopy);
		assertTrue(color.compareTo(colorCopy) == 0);
		assertTrue(color.equals(colorCopy));
	}
	
	@Test
	public void testSetExtendedValueSecure() {
		String red = "RED";
		extendedType.setExtendedValue(red);
		
		String redCopy = extendedType.getExtendedValue();
		assertFalse(red == redCopy);
		assertTrue(red.compareTo(redCopy) == 0);
		assertTrue(red.equals(redCopy));
	}
	
	@Test
	public void testClearExtension() {
		extendedType.clearExtension();
		assertFalse(extendedType.hasExtendedValue());
	}
	
	@Test
	public void testHasExtension() {
		assertTrue(extendedType.hasExtendedValue());
	}
	
	@Test
	public void testAddExtendedParam() {
		extendedType.addExtendedParam(new ExtendedParamType("X-TEST", VCardTypeName.XTENDED));
		extendedType.addExtendedParam(new ExtendedParamType("X-CAR", "BMW", VCardTypeName.XTENDED));
		assertTrue(extendedType.containsExtendedParam(new ExtendedParamType("X-TEST", VCardTypeName.XTENDED)));
		assertTrue(extendedType.containsExtendedParam(new ExtendedParamType("X-CAR", "BMW", VCardTypeName.XTENDED)));
	}
	
	@Test
	public void testAddAllExtendedParams() {
		List<ExtendedParamType> list = new ArrayList<ExtendedParamType>(2);
		list.add(new ExtendedParamType("X-TEST", VCardTypeName.XTENDED));
		list.add(new ExtendedParamType("X-CAR", "BMW", VCardTypeName.XTENDED));
		
		assertFalse(extendedType.hasExtendedParams());
		extendedType.addAllExtendedParams(list);
		assertTrue(extendedType.hasExtendedParams());
	}
	
	@Test
	public void testRemoveExtendedParam() {
		extendedType.addExtendedParam(new ExtendedParamType("X-TEST", VCardTypeName.XTENDED));
		extendedType.addExtendedParam(new ExtendedParamType("X-CAR", "BMW", VCardTypeName.XTENDED));
		
		assertTrue(extendedType.containsExtendedParam(new ExtendedParamType("X-TEST", VCardTypeName.XTENDED)));
		assertTrue(extendedType.containsExtendedParam(new ExtendedParamType("X-CAR", "BMW", VCardTypeName.XTENDED)));
		
		extendedType.removeExtendedParam(new ExtendedParamType("X-TEST", VCardTypeName.XTENDED));
		
		assertFalse(extendedType.containsExtendedParam(new ExtendedParamType("X-TEST", VCardTypeName.XTENDED)));
	}
	
	@Test
	public void testClearExtendedParams() {
		List<ExtendedParamType> list = new ArrayList<ExtendedParamType>(2);
		list.add(new ExtendedParamType("X-TEST", VCardTypeName.XTENDED));
		list.add(new ExtendedParamType("X-CAR", "BMW", VCardTypeName.XTENDED));
		extendedType.addAllExtendedParams(list);
		assertTrue(extendedType.hasExtendedParams());
		
		extendedType.clearExtendedParams();
		assertFalse(extendedType.hasExtendedParams());
	}
	
	@Test
	public void testHasExtendedParam() {
		assertFalse(extendedType.hasExtendedParams());
	}
	
	@Test
	public void testGetExtendedParamSize() {
		assertEquals(0, extendedType.getExtendedParamSize());
		extendedType.addExtendedParam(new ExtendedParamType("X-TEST", VCardTypeName.XTENDED));
		assertEquals(1, extendedType.getExtendedParamSize());
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.XTENDED.getType(), extendedType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		ExtendedType extendedType2 = new ExtendedType();
		extendedType2.setExtendedName("X-COLOR");
		extendedType2.setExtendedValue("RED");
		assertTrue(extendedType.equals(extendedType2));
	}
	
	@Test
	public void testCompareTo() {
		ExtendedType extendedType2 = new ExtendedType();
		extendedType2.setExtendedName("X-COLOR");
		extendedType2.setExtendedValue("RED");
		assertTrue(extendedType.compareTo(extendedType2) == 0);
	}
	
	@Test
	public void testHashcode() {
		ExtendedType extendedType2 = new ExtendedType();
		extendedType2.setExtendedName("X-COLOR");
		extendedType2.setExtendedValue("RED");
		
		int hcode1 = extendedType.hashCode();
		int hcode2 = extendedType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testClone() {
		ExtendedType cloned = extendedType.clone();
		assertEquals(cloned, extendedType);
		assertTrue(extendedType.equals(cloned));
		assertTrue(extendedType.compareTo(cloned) == 0);
	}
}
