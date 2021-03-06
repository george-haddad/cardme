package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
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
public class CategoriesTypeTest {

	private CategoriesType categoriesType = null;
	
	@Before
	public void setUp() throws Exception {
		categoriesType = new CategoriesType();
		categoriesType.addCategory("Test 1").addCategory("Test 2").addCategory("Test 3");
	}
	
	@After
	public void tearDown() throws Exception {
		categoriesType = null;
	}
	
	@Test
	public void testAddCategory() {
		categoriesType.addCategory("Newly Added");
		assertTrue(categoriesType.containsCategory("Newly Added"));
	}
	
	@Test
	public void testRemoveCategory() {
		assertTrue(categoriesType.containsCategory("Test 1"));
		categoriesType.removeCategory("Test 1");
		assertFalse(categoriesType.containsCategory("Test 1"));
	}
	
	@Test
	public void testContainsCategory() {
		assertTrue(categoriesType.containsCategory("Test 1"));
		assertTrue(categoriesType.containsCategory("Test 2"));
		assertTrue(categoriesType.containsCategory("Test 3"));
		assertFalse(categoriesType.containsCategory("ABC"));
		assertFalse(categoriesType.containsCategory(null));
	}
	
	@Test
	public void testGetCategories() {
		List<String> list = categoriesType.getCategories();
		assertEquals("Test 1", list.get(0));
		assertEquals("Test 2", list.get(1));
		assertEquals("Test 3", list.get(2));
	}
	
	@Test
	public void testClearCategories() {
		assertTrue(categoriesType.containsCategory("Test 1"));
		assertTrue(categoriesType.containsCategory("Test 2"));
		assertTrue(categoriesType.containsCategory("Test 3"));
		
		categoriesType.clearCategories();
		
		assertFalse(categoriesType.containsCategory("Test 1"));
		assertFalse(categoriesType.containsCategory("Test 2"));
		assertFalse(categoriesType.containsCategory("Test 3"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddCategoryNullPointer() {
		categoriesType.addCategory(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveCategoryNullPointer() {
		categoriesType.removeCategory(null);
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.CATEGORIES.getType(), categoriesType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		CategoriesType categoriesType2 = new CategoriesType();
		categoriesType2.addCategory("Test 1").addCategory("Test 2").addCategory("Test 3");
		assertTrue(categoriesType.equals(categoriesType2));
	}
	
	@Test
	public void testHashcode() {
		CategoriesType categoriesType2 = new CategoriesType();
		categoriesType2.addCategory("Test 1").addCategory("Test 2").addCategory("Test 3");
		
		int hcode1 = categoriesType.hashCode();
		int hcode2 = categoriesType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		CategoriesType categoriesType2 = new CategoriesType();
		categoriesType2.addCategory("Test 1").addCategory("Test 2").addCategory("Test 3");
		
		assertTrue(categoriesType.compareTo(categoriesType2) == 0);
	}
	
	@Test
	public void testClone() {
		CategoriesType cloned = categoriesType.clone();
		assertEquals(cloned, categoriesType);
		assertTrue(categoriesType.equals(cloned));
		assertTrue(categoriesType.compareTo(cloned) == 0);
	}
}
