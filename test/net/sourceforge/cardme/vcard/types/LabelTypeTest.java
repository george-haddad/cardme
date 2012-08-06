package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.cardme.vcard.types.parameters.LabelParameterType;
import net.sourceforge.cardme.vcard.types.parameters.XLabelParameterType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
 * Oct 1, 2011
 *
 */
public class LabelTypeTest {

	private LabelType labelType = null;
	
	@Before
	public void setUp() throws Exception {
		labelType = new LabelType();
		labelType.setLabel("Cardme");
		labelType.addLabelParameterType(LabelParameterType.POSTAL);
		labelType.addLabelParameterType(LabelParameterType.PARCEL);
		labelType.addExtendedLabelParameterType(new XLabelParameterType("X-DHL"));
		labelType.addExtendedLabelParameterType(new XLabelParameterType("X-TRACKING-NO", "SADF-9123840932180941"));
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
	public void testAddLabelParameterType() {
		labelType.addLabelParameterType(LabelParameterType.HOME);
		assertTrue(labelType.containsLabelParameterType(LabelParameterType.HOME));
	}
	
	@Test
	public void testGetLabelParameterTypes() {
		Iterator<LabelParameterType> iter = labelType.getLabelParameterTypes();
		assertEquals(LabelParameterType.POSTAL, iter.next());
		assertEquals(LabelParameterType.PARCEL, iter.next());
	}
	
	@Test
	public void testGetLabelParameterTypesList() {
		List<LabelParameterType> list = labelType.getLabelParameterTypesList();
		assertEquals(LabelParameterType.POSTAL, list.get(0));
		assertEquals(LabelParameterType.PARCEL, list.get(1));
	}
	
	@Test
	public void testRemoveLabelParameterType() {
		labelType.removeLabelParameterType(LabelParameterType.POSTAL);
		assertFalse(labelType.containsLabelParameterType(LabelParameterType.POSTAL));
	}
	
	@Test
	public void testGetLabelParameterSize() {
		assertEquals(2, labelType.getLabelParameterSize());
	}
	
	@Test
	public void testContainsLabelParameterType() {
		assertTrue(labelType.containsLabelParameterType(LabelParameterType.POSTAL));
		assertTrue(labelType.containsLabelParameterType(LabelParameterType.PARCEL));
	}
	
	@Test
	public void testContainsAllLabelParameterTypes() {
		List<LabelParameterType> types = new ArrayList<LabelParameterType>(2);
		types.add(LabelParameterType.POSTAL);
		types.add(LabelParameterType.PARCEL);
		
		assertTrue(labelType.conatinsAllLabelParameterTypes(types));
	}
	
	@Test
	public void testClearLabelParameterTypes() {
		labelType.clearLabelParameterTypes();
		assertFalse(labelType.containsLabelParameterType(LabelParameterType.POSTAL));
		assertFalse(labelType.containsLabelParameterType(LabelParameterType.PARCEL));
		assertFalse(labelType.hasLabelParameterTypes());
	}
	
	@Test
	public void testHasLabelParameterTypes() {
		assertTrue(labelType.hasLabelParameterTypes());
	}
	
	@Test
	public void testGetExtendedLabelParameterTypes() {
		Iterator<XLabelParameterType> iter = labelType.getExtendedLabelParameterTypes();
		assertEquals(new XLabelParameterType("X-DHL"), iter.next());
		assertEquals(new XLabelParameterType("X-TRACKING-NO", "SADF-9123840932180941"), iter.next());
	}
	
	@Test
	public void testGetExtendedLabelParameterTypesList() {
		List<XLabelParameterType> list = labelType.getExtendedLabelParameterTypesList();
		assertEquals(new XLabelParameterType("X-DHL"), list.get(0));
		assertEquals(new XLabelParameterType("X-TRACKING-NO", "SADF-9123840932180941"), list.get(1));
	}
	
	@Test
	public void testGetExtendedLabelParameterSize() {
		assertEquals(2, labelType.getExtendedLabelParameterSize());
	}
	
	@Test
	public void testAddExtendedLabelParameterType() {
		labelType.addExtendedLabelParameterType(new XLabelParameterType("X-BLA"));
		assertTrue(labelType.containsExtendedLabelParameterType(new XLabelParameterType("X-BLA")));
	}
	
	@Test
	public void testRemoveExtendedLabelParameterType() {
		labelType.removeExtendedLabelParameterType(new XLabelParameterType("X-DHL"));
		assertFalse(labelType.containsExtendedLabelParameterType(new XLabelParameterType("X-DHL")));
	}
	
	@Test
	public void testContainsExtendedLabelParameterType() {
		assertTrue(labelType.containsExtendedLabelParameterType(new XLabelParameterType("X-DHL")));
		assertTrue(labelType.containsExtendedLabelParameterType(new XLabelParameterType("X-TRACKING-NO", "SADF-9123840932180941")));
	}
	
	@Test
	public void testContainsAllExtendedLabelParameterTypes() {
		List<XLabelParameterType> types = new ArrayList<XLabelParameterType>(2);
		types.add(new XLabelParameterType("X-DHL"));
		types.add(new XLabelParameterType("X-TRACKING-NO", "SADF-9123840932180941"));
		
		assertTrue(labelType.containsAllExtendedLabelParameterTypes(types));
	}
	
	@Test
	public void testHasExtendedLabelParameterTypes() {
		assertTrue(labelType.hasExtendedLabelParameterTypes());
	}
	
	@Test
	public void testClearExtendedLabelParameterTypes() {
		labelType.clearExtendedLabelParameterTypes();
		assertFalse(labelType.hasExtendedLabelParameterTypes());
	}
}
