package net.sourceforge.cardme.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.io.FoldingScheme;

import java.text.NumberFormat;

import org.junit.Test;


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

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Oct 22, 2011
 *
 */
public class VCardUtilsTest {
	
	@Test
	public void testNeedsEscaping() {
		
		/*
		 * Check the following
		 * 
		 * ,
		 * ;
		 * :
		 * \
		 * <EOL>
		 * \N
		 */
		
		assertTrue(VCardUtils.needsEscaping(","));
		assertTrue(VCardUtils.needsEscaping(";"));
		assertTrue(VCardUtils.needsEscaping(":"));
		assertTrue(VCardUtils.needsEscaping("\\"));
		assertTrue(VCardUtils.needsEscaping("\n"));
		assertTrue(VCardUtils.needsEscaping("\\N"));
		assertTrue(VCardUtils.needsEscaping("\n,;:\\"));
		assertTrue(VCardUtils.needsEscaping("GE,O;R:G\\E"));
	}
	
	@Test
	public void testNeedsUnescaping() {
		
		/*
		 * Check the following
		 * 
		 * \n
		 * \N
		 * \\
		 * \,
		 * \;
		 * \:
		 */
		
		assertTrue(VCardUtils.needsUnEscaping("\\n"));
		assertTrue(VCardUtils.needsUnEscaping("\\N")); 
		assertTrue(VCardUtils.needsUnEscaping("\\\\"));
		assertTrue(VCardUtils.needsUnEscaping("\\,"));
		assertTrue(VCardUtils.needsUnEscaping("\\;"));
		assertTrue(VCardUtils.needsUnEscaping("\\:"));
		assertTrue(VCardUtils.needsUnEscaping("\\n\\N\\,\\;\\:\\\\"));
		assertTrue(VCardUtils.needsUnEscaping("GE\\,O\\;R\\:GE"));
	}
	
	@Test
	public void testUnescapeString() {
		String unescaped = VCardUtils.unescapeString("This\\n string\\, needs\\; to\\: be\\N unescaped.\\\\");
		assertEquals("This\n string, needs; to: be\n unescaped.\\", unescaped);
	}
	
	@Test
	public void testEscapeString() {
		String escaped = VCardUtils.escapeString("This\n string, needs; to: be\n unescaped.\\");
		assertEquals("This\\n string\\, needs\\; to\\: be\\n unescaped.\\\\", escaped);
	}
	
	@Test
	public void testNeedsFoldingMimeDir() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < 75; i++) {
			sb.append('a');
		}
		
		assertEquals(75, sb.length());
		assertFalse(VCardUtils.needsFolding(sb.toString(), FoldingScheme.MIME_DIR));
		
		sb.append('b');
		sb.append('b');
		sb.append('b');
		
		assertEquals(78, sb.length());
		assertTrue(VCardUtils.needsFolding(sb.toString(), FoldingScheme.MIME_DIR));
		
//		VCardUtils.needsFolding(line, FoldingScheme.MS_OUTLOOK);
//		VCardUtils.needsFolding(line, FoldingScheme.NONE);
	}
	
	@Test
	public void testNeedsFoldingMacAddressBook() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < 76; i++) {
			sb.append('a');
		}
		
		assertEquals(76, sb.length());
		assertFalse(VCardUtils.needsFolding(sb.toString(), FoldingScheme.MAC_ADDRESS_BOOK));
		
		sb.append('b');
		sb.append('b');
		sb.append('b');
		
		assertEquals(79, sb.length());
		assertTrue(VCardUtils.needsFolding(sb.toString(), FoldingScheme.MAC_ADDRESS_BOOK));
	}
	
	@Test
	public void testNeedsFoldingMsOutlook() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < 72; i++) {
			sb.append('a');
		}
		
		assertEquals(72, sb.length());
		assertFalse(VCardUtils.needsFolding(sb.toString(), FoldingScheme.MS_OUTLOOK));
		
		sb.append('b');
		sb.append('b');
		sb.append('b');
		
		assertEquals(75, sb.length());
		assertTrue(VCardUtils.needsFolding(sb.toString(), FoldingScheme.MS_OUTLOOK));
	}
	
	@Test
	public void testNeedsFoldingNone() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < 80; i++) {
			sb.append('a');
		}
		
		assertEquals(80, sb.length());
		assertFalse(VCardUtils.needsFolding(sb.toString(), FoldingScheme.NONE));
	}
	
	@Test
	public void testUnfoldVCardMimeDir() {
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("PHOTO;BASE64:/9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYXBwb\n");
		sb.append(" AIgAABtbnRyUkdCIFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAAAAAAAA\n");
		sb.append(" AAAAAAAAAA9tYAAQAA3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUUUUAf/Z\n");
		sb.append("END:VCARD\n");
		String foldedVCard = sb.toString();
		
		sb = new StringBuilder();
		sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("PHOTO;BASE64:/9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYXBwbAIgAABtbnRyUkdCIFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAAAAAAAAAAAAAAAAAA9tYAAQAA3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUUUUAf/Z\n");
		sb.append("END:VCARD\n");
		String expectedUnfoledVCard = sb.toString(); 
		
		String unfoleded = VCardUtils.unfoldVCard(foldedVCard);
		
		assertEquals(expectedUnfoledVCard, unfoleded);
	}
	
	@Test
	public void testUnfoldVCardMacAddressBook() {
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("PHOTO;BASE64:\n");
		sb.append("  /9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYXBwbAIgAABtbnRyUkdC\n");
		sb.append("  IFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAAAAAAAAAAAAAAAAAA9tYAAQAA\n");
		sb.append("  AADTLWFwcGwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAtk\n");
		sb.append("  3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUUUUAf/Z\n");
		sb.append("END:VCARD\n");
		String foldedVCard = sb.toString();
		
		sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("PHOTO;BASE64:/9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYXBwbAIgAABtbnRyUkdCIFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAAAAAAAAAAAAAAAAAA9tYAAQAAAADTLWFwcGwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAtk3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUUUUAf/Z\n");
		sb.append("END:VCARD\n");
		String expectedUnfoledVCard = sb.toString(); 
		
		String unfoleded = VCardUtils.unfoldVCard(foldedVCard);
		
		assertEquals(expectedUnfoledVCard, unfoleded);
	}
	
	@Test
	public void testUnfoldVCardMsOutlook() {
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("PHOTO;BASE64:/9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYX\n");
		sb.append(" BwbAIgAABtbnRyUkdCIFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAA\n");
		sb.append(" AAAAAAAAAAAAAAAA9tYAAQAA3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUU\n");
		sb.append(" UUAf/Z\n");
		sb.append("END:VCARD\n");
		String foldedVCard = sb.toString();
		
		sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("PHOTO;BASE64:/9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYXBwbAIgAABtbnRyUkdCIFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAAAAAAAAAAAAAAAAAA9tYAAQAA3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUUUUAf/Z\n");
		sb.append("END:VCARD\n");
		String expectedUnfoledVCard = sb.toString(); 
		
		String unfoleded = VCardUtils.unfoldVCard(foldedVCard);
		
		assertEquals(expectedUnfoledVCard, unfoleded);
	}
	
	@Test
	public void testFoldLineMimeDir() {
		String unfoledLine = "PHOTO;BASE64:/9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYXBwbAIgAABtbnRyUkdCIFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAAAAAAAAAAAAAAAAAA9tYAAQAA3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUUUUAf/Z";
		
		StringBuilder sb = new StringBuilder();
		sb.append("PHOTO;BASE64:/9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYXBwbA\r\n");
		sb.append(" IgAABtbnRyUkdCIFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAAAAAAAAAAA\r\n");
		sb.append(" AAAAAAA9tYAAQAA3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUUUUAf/Z");
		String expectedFoldedLine = sb.toString();
		
		//Should be folded 3 times with 2 \n markers
		assertEquals(2, StringUtil.countInstanceOf(expectedFoldedLine, '\n'));
		
		String folded = VCardUtils.foldLine(unfoledLine, FoldingScheme.MIME_DIR);
		
		//Should be folded 3 times with 2 \n markers
		assertEquals(2, StringUtil.countInstanceOf(folded, '\n'));
		
		assertEquals(expectedFoldedLine, folded);
	}
	
	@Test
	public void testFoldLineMacAddressBook() {
		String unfoledLine = "PHOTO;BASE64:/9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYXBwbAIgAABtbnRyUkdCIFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAAAAAAAAAAAAAAAAAA9tYAAQAA3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUUUUAf/Z";
		
		StringBuilder sb = new StringBuilder();
		sb.append("PHOTO;BASE64:/9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYXBwbAI\r\n");
		sb.append("  gAABtbnRyUkdCIFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAAAAAAAAAAAAA\r\n");
		sb.append("  AAAAA9tYAAQAA3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUUUUAf/Z");
		String expectedFoldedLine = sb.toString();
		
		//Should be folded 3 times with 2 \n markers
		assertEquals(2, StringUtil.countInstanceOf(expectedFoldedLine, '\n'));

		String folded = VCardUtils.foldLine(unfoledLine, FoldingScheme.MAC_ADDRESS_BOOK);

		//Should be folded 3 times with 2 \n markers
		assertEquals(2, StringUtil.countInstanceOf(folded, '\n'));
		
		assertEquals(expectedFoldedLine, folded);
	}
	
	@Test
	public void testFoldLineMsOutlook() {
		String unfoledLine = "PHOTO;BASE64:/9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYXBwbAIgAABtbnRyUkdCIFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAAAAAAAAAAAAAAAAAA9tYAAQAA3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUUUUAf/Z";
		
		StringBuilder sb = new StringBuilder();
		sb.append("PHOTO;BASE64:/9j/4AAQSkZJRgABAQAAAQABAAD/4gVASUNDX1BST0ZJTEUAAQEAAAUwYXB\r\n");
		sb.append(" wbAIgAABtbnRyUkdCIFhZWiAH2QACABkACwAaAAthY3NwQVBQTAAAAABhcHBsAAAAAAAAAAA\r\n");
		sb.append(" AAAAAAAAAAAAA9tYAAQAA3m2pOSXLr1v1k2t76G1RRRX9EHwIUUUUAFFFFABRRRQAUUUUAf/\r\n");
		sb.append(" Z");
		String expectedFoldedLine = sb.toString();
		
		//Should be folded 3 times with 3 \n markers
		assertEquals(3, StringUtil.countInstanceOf(expectedFoldedLine, '\n'));

		String folded = VCardUtils.foldLine(unfoledLine, FoldingScheme.MS_OUTLOOK);

		//Should be folded 3 times with 3 \n markers
		assertEquals(3, StringUtil.countInstanceOf(folded, '\n'));
		
		assertEquals(expectedFoldedLine, folded);
	}
	
	@Test
	public void testGeographicPositionFormatter() {
		NumberFormat numFormat = VCardUtils.getGeographicPositionFormatter();
		String geoPos = numFormat.format(123.123456789);
		
		//Note 6 gets rounded up to 7
		assertEquals("123.123457", geoPos);
		
	}
	
	@Test
	public void testParseStringWithEscappedDelimiter() {
		String nameOnly = ";Ge\\,o\\;r\\:g\\\\e;;;";
		
		String[] name = VCardUtils.parseStringWithEscappedDelimiter(nameOnly, ';');
		StringBuilder sb = new StringBuilder();
		for (String part : name) {
			if(!part.isEmpty()) {
				sb.append(part);
				sb.append("\n");
			}
			else {
				sb.append("\n");
			}
		}
		
		String expected = "\nGe\\,o\\;r\\:g\\\\e\n\n\n\n";
		assertEquals(expected, sb.toString());
		
		// ----------------------------------------
		
		String fullName = "Mr;Ge\\,o\\;r\\:g\\\\e;Gilbert;Smith;Jr.";
		
		name = VCardUtils.parseStringWithEscappedDelimiter(fullName, ';');
		sb = new StringBuilder();
		for (String part : name) {
			if(!part.isEmpty()) {
				sb.append(part);
				sb.append("\n");
			}
			else {
				sb.append("\n");
			}
		}
		
		expected = "Mr\nGe\\,o\\;r\\:g\\\\e\nGilbert\nSmith\nJr.\n";
		assertEquals(expected, sb.toString());
	}
}
