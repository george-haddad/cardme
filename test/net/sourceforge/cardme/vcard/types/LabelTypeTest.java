package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;
import net.sourceforge.cardme.vcard.types.params.LabelParamType;
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
 * Aug 27, 2012
 *
 */
public class LabelTypeTest {

	private LabelType labelType = null;
	
	@Before
	public void setUp() throws Exception {
		labelType = new LabelType();
		labelType.setLabel("Cardme");
		labelType.addParam(LabelParamType.POSTAL);
		labelType.addParam(LabelParamType.PARCEL);
		labelType.addExtendedParam(new ExtendedParamType("X-DHL", VCardTypeName.LABEL));
		labelType.addExtendedParam(new ExtendedParamType("X-TRACKING-NO", "SADF-9123840932180941", VCardTypeName.LABEL));
	}
	
	@After
	public void tearDown() throws Exception {
		labelType = null;
	}
	
	@Test
	public void testGetLabel() {
		assertEquals("Cardme", labelType.getLabel());
	}
	
	@Test
	public void testAddParam() {
		labelType.addParam(LabelParamType.HOME);
		assertTrue(labelType.containsParam(LabelParamType.HOME));
	}
	
	@Test
	public void testGetParams() {
		List<LabelParamType> list = labelType.getParams();
		assertEquals(LabelParamType.POSTAL, list.get(0));
		assertEquals(LabelParamType.PARCEL, list.get(1));
	}
	
	@Test
	public void testRemoveParam() {
		labelType.removeParam(LabelParamType.POSTAL);
		assertFalse(labelType.containsParam(LabelParamType.POSTAL));
	}
	
	@Test
	public void testGetParamSize() {
		assertEquals(2, labelType.getParamSize());
	}
	
	@Test
	public void testContainsParam() {
		assertTrue(labelType.containsParam(LabelParamType.POSTAL));
		assertTrue(labelType.containsParam(LabelParamType.PARCEL));
	}
	
	@Test
	public void testContainsAllParams() {
		List<LabelParamType> list = new ArrayList<LabelParamType>(2);
		list.add(LabelParamType.POSTAL);
		list.add(LabelParamType.PARCEL);
		
		assertTrue(labelType.containsAllParams(list));
	}
	
	@Test
	public void testClearParams() {
		labelType.clearParams();
		assertFalse(labelType.containsParam(LabelParamType.POSTAL));
		assertFalse(labelType.containsParam(LabelParamType.PARCEL));
		assertFalse(labelType.hasParams());
	}
	
	@Test
	public void testHasParameters() {
		assertTrue(labelType.hasParams());
	}
	
	@Test
	public void testGetExtendedParams() {
		List<ExtendedParamType> list = labelType.getExtendedParams();
		assertEquals(new ExtendedParamType("X-DHL", VCardTypeName.LABEL), list.get(0));
		assertEquals(new ExtendedParamType("X-TRACKING-NO", "SADF-9123840932180941", VCardTypeName.LABEL), list.get(1));
	}
	
	@Test
	public void testGetExtendedParamSize() {
		assertEquals(2, labelType.getExtendedParamSize());
	}
	
	@Test
	public void testAddExtendedParam() {
		labelType.addExtendedParam(new ExtendedParamType("X-BLA", VCardTypeName.LABEL));
		assertTrue(labelType.containsExtendedParam(new ExtendedParamType("X-BLA", VCardTypeName.LABEL)));
	}
	
	@Test
	public void testRemoveExtendedParam() {
		labelType.removeExtendedParam(new ExtendedParamType("X-DHL", VCardTypeName.LABEL));
		assertFalse(labelType.containsExtendedParam(new ExtendedParamType("X-DHL", VCardTypeName.LABEL)));
	}
	
	@Test
	public void testContainsExtendedParam() {
		assertTrue(labelType.containsExtendedParam(new ExtendedParamType("X-DHL", VCardTypeName.LABEL)));
		assertTrue(labelType.containsExtendedParam(new ExtendedParamType("X-TRACKING-NO", "SADF-9123840932180941", VCardTypeName.LABEL)));
	}
	
	@Test
	public void testContainsAllExtendedParam() {
		List<ExtendedParamType> types = new ArrayList<ExtendedParamType>(2);
		types.add(new ExtendedParamType("X-DHL", VCardTypeName.LABEL));
		types.add(new ExtendedParamType("X-TRACKING-NO", "SADF-9123840932180941", VCardTypeName.LABEL));
		
		assertTrue(labelType.containsAllExtendedParams(types));
	}
	
	@Test
	public void testHasExtendedLabelParameterTypes() {
		assertTrue(labelType.hasExtendedParams());
	}
	
	@Test
	public void testClearExtendedParams() {
		labelType.clearExtendedParams();
		assertFalse(labelType.hasExtendedParams());
	}
}
