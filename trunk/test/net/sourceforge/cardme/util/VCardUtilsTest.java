package net.sourceforge.cardme.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.StringReader;
import java.text.NumberFormat;

import net.sourceforge.cardme.io.FoldingScheme;

import org.apache.commons.codec.net.QuotedPrintableCodec;
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
		 * \r
		 */
		
		assertTrue(VCardUtils.needsEscaping(","));
		assertTrue(VCardUtils.needsEscaping(";"));
		assertTrue(VCardUtils.needsEscaping(":"));
		assertTrue(VCardUtils.needsEscaping("\\"));
		assertTrue(VCardUtils.needsEscaping("\r"));
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
		
		assertTrue(VCardUtils.needsUnEscaping("\\r"));
		assertTrue(VCardUtils.needsUnEscaping("\\n"));
		assertTrue(VCardUtils.needsUnEscaping("\\N")); 
		assertTrue(VCardUtils.needsUnEscaping("\\\\"));
		assertTrue(VCardUtils.needsUnEscaping("\\,"));
		assertTrue(VCardUtils.needsUnEscaping("\\;"));
		assertTrue(VCardUtils.needsUnEscaping("\\:"));
		assertTrue(VCardUtils.needsUnEscaping("\\n\\N\\r\\,\\;\\:\\\\"));
		assertTrue(VCardUtils.needsUnEscaping("GE\\,O\\;R\\:GE"));
	}
	
	@Test
	public void testUnescapeString() {
		String unescaped = VCardUtils.unescapeString("This\\r\\n string\\, needs\\; to\\: be\\N unescaped.\\\\");
		assertEquals("This\r\n string, needs; to: be\n unescaped.\\", unescaped);
	}
	
	@Test
	public void testEscapeString() {
		String escaped = VCardUtils.escapeString("This\r\n string, needs; to: be\n unescaped.\\");
		assertEquals("This\\r\\n string\\, needs\\; to\\: be\\n unescaped.\\\\", escaped);
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
	public void testUnfoldVCardMsOutlookRFC822Violation_1() {
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("ADR;HOME:;;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=\n");
		sb.append("=41=41=41=41=41=41=41;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=\n");
		sb.append("=41=41=41=41=41;;\n");
		sb.append("END:VCARD\n");
		String foldedVCard = sb.toString();
		
		sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("ADR;HOME:;;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41;;\n");
		sb.append("END:VCARD\n");
		String expectedUnfoledVCard = sb.toString();
		
		String unfolded = VCardUtils.unfoldVCard(foldedVCard);
		
		assertEquals(expectedUnfoledVCard, unfolded);
	}
	
	@Test
	public void testUnfoldVCardMsOutlookRFC822Violation_2() {
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("ADR;HOME:;;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=\n");
		sb.append("=41=41=41=41=41=41=41;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=\n");
		sb.append("=41=41=41=41=41;;\n");
		sb.append("LABEL:BLA BLA BLA\n");
		sb.append("END:VCARD\n");
		String foldedVCard = sb.toString();
		
		sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("ADR;HOME:;;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41;;\n");
		sb.append("LABEL:BLA BLA BLA\n");
		sb.append("END:VCARD\n");
		String expectedUnfoldedVCard = sb.toString();
		
		String unfolded = VCardUtils.unfoldVCard(foldedVCard);
		
		assertEquals(expectedUnfoldedVCard, unfolded);
	}
	
	@Test
	public void testUnfoldVCardMsOutlookRFC822Violation_3() {
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("ADR;HOME:;;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=\n");
		sb.append("=41=41=41=41=41=41=41;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=\n");
		sb.append("=41=41=41=41=41;;\n");
		sb.append("LABEL:BLA BLA BLA\n");
		sb.append("PHOTO;ENCODING=b;TYPE=JPEG:/9j/4AAQSkZJRgABAQAAAQABAAD/4QBARXhpZgAATU0AKgAA\n");
		sb.append(" AAgAAYdpAAQAAAABAAAAGgAAAAAAAqACAAQAAAABAAAAyKADAAQAAAABAAAAyAAAAAD/2wBDAA\n");
		sb.append(" Xw5rH/ACGE/H/0EUUV8zifiPocB/DPeP2Xv+RvsPw/9HpX3v4C/wCQbH/1zH8loor18u2PPzH4\n");
		sb.append(" joovv1NRRX0FHY8mQ6L/AFq/UVeoor7Hh7+HP1Oer0CiiivoTE//2Q==\n");
		sb.append("UID:0e7602cc-443e-4b82-b4b1-90f62f99a199\n");
		sb.append("END:VCARD\n");
		String foldedVCard = sb.toString();
		
		sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("ADR;HOME:;;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41;=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41=41;;\n");
		sb.append("LABEL:BLA BLA BLA\n");
		sb.append("PHOTO;ENCODING=b;TYPE=JPEG:/9j/4AAQSkZJRgABAQAAAQABAAD/4QBARXhpZgAATU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAAqACAAQAAAABAAAAyKADAAQAAAABAAAAyAAAAAD/2wBDAAXw5rH/ACGE/H/0EUUV8zifiPocB/DPeP2Xv+RvsPw/9HpX3v4C/wCQbH/1zH8loor18u2PPzH4joovv1NRRX0FHY8mQ6L/AFq/UVeoor7Hh7+HP1Oer0CiiivoTE//2Q==\n");
		sb.append("UID:0e7602cc-443e-4b82-b4b1-90f62f99a199\n");
		sb.append("END:VCARD\n");
		String expectedUnfoldedVCard = sb.toString();
		
		String unfolded = VCardUtils.unfoldVCard(foldedVCard);
		
		assertEquals(expectedUnfoldedVCard, unfolded);
	}
	
	/**
	 * Tests to make sure it can read lines that look like this
	 * (the folded lines do not start with whitespace):
	 * 
	 * LABEL;HOME;ENCODING=QUOTED-PRINTABLE:Silicon Alley 5,=0D=0A=
	 * New York, New York  12345=0D=0A=
	 * USA
	 *
	 */
	@Test
	public void testUnfoldVCardMsOutlookRFC822Violation_4() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		
		//one line
		sb.append("LABEL;HOME;ENCODING=QUOTED-PRINTABLE:Silicon Alley 5\n");
		
		//two lines
		//without "ENCODING" subtype name
		sb.append("LABEL;HOME;QUOTED-PRINTABLE:Silicon Alley 5,=0D=0A=\n");
		sb.append("New York, New York  12345\n");
		
		//three lines
		//"quoted-printable" in lower-case
		sb.append("LABEL;HOME;ENCODING=quoted-printable:Silicon Alley 5,=0D=0A=\n");
		sb.append("New York, New York  12345=0D=0A=\n");
		sb.append("USA\n");
		
		//it should recognize when the string "QUOTED-PRINTABLE" is not to the left of the colon
		sb.append("LABEL;HOME:Some text QUOTED-PRINTABLE more text=\n");
		
		//four lines
		sb.append("LABEL;HOME;ENCODING=QUOTED-PRINTABLE:Silicon Alley 5,=0D=0A=\n");
		sb.append("New York, New York  12345=0D=0A=\n");
		sb.append("USA=0D=0A=\n");
		sb.append("4th line\n");
		
		//a quoted-printable line whose additional lines *are* folded
		sb.append("LABEL;HOME;ENCODING=QUOTED-PRINTABLE:Silicon Alley 5,=0D=0A=\n");
		sb.append(" New York, New York  12345=0D=0A=\n");
		sb.append(" USA\n");
		
		sb.append("PHOTO;ENCODING=b;TYPE=JPEG:/9j/4AAQSkZJRgABAQAAAQABAAD/4QBARXhpZgAATU0AKgAA\n");
		sb.append(" AAgAAYdpAAQAAAABAAAAGgAAAAAAAqACAAQAAAABAAAAyKADAAQAAAABAAAAyAAAAAD/2wBDAA\n");
		sb.append(" Xw5rH/ACGE/H/0EUUV8zifiPocB/DPeP2Xv+RvsPw/9HpX3v4C/wCQbH/1zH8loor18u2PPzH4\n");
		sb.append(" joovv1NRRX0FHY8mQ6L/AFq/UVeoor7Hh7+HP1Oer0CiiivoTE//2Q==\n");
		sb.append("UID:0e7602cc-443e-4b82-b4b1-90f62f99a199\n");
		sb.append("END:VCARD\n");
		String foldedVCard = sb.toString();
		
		sb = new StringBuilder();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		sb.append("N:Jost;John;;;\n");
		sb.append("FN:John Doe\n");
		sb.append("LABEL;HOME;ENCODING=QUOTED-PRINTABLE:Silicon Alley 5\n");
		sb.append("LABEL;HOME;QUOTED-PRINTABLE:Silicon Alley 5,=0D=0ANew York, New York  12345\n");
		sb.append("LABEL;HOME;ENCODING=quoted-printable:Silicon Alley 5,=0D=0ANew York, New York  12345=0D=0AUSA\n");
		sb.append("LABEL;HOME:Some text QUOTED-PRINTABLE more text=\n");
		sb.append("LABEL;HOME;ENCODING=QUOTED-PRINTABLE:Silicon Alley 5,=0D=0ANew York, New York  12345=0D=0AUSA=0D=0A4th line\n");
		sb.append("LABEL;HOME;ENCODING=QUOTED-PRINTABLE:Silicon Alley 5,=0D=0ANew York, New York  12345=0D=0AUSA\n");
		sb.append("PHOTO;ENCODING=b;TYPE=JPEG:/9j/4AAQSkZJRgABAQAAAQABAAD/4QBARXhpZgAATU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAAqACAAQAAAABAAAAyKADAAQAAAABAAAAyAAAAAD/2wBDAAXw5rH/ACGE/H/0EUUV8zifiPocB/DPeP2Xv+RvsPw/9HpX3v4C/wCQbH/1zH8loor18u2PPzH4joovv1NRRX0FHY8mQ6L/AFq/UVeoor7Hh7+HP1Oer0CiiivoTE//2Q==\n");
		sb.append("UID:0e7602cc-443e-4b82-b4b1-90f62f99a199\n");
		sb.append("END:VCARD\n");
		String expectedUnfoldedVCard = sb.toString();
		
		String unfolded = VCardUtils.unfoldVCard(foldedVCard);
		
		assertEquals(expectedUnfoldedVCard, unfolded);
		
		//make sure the quoted-printable lines can be properly decoded
		BufferedReader r = new BufferedReader(new StringReader(unfolded));
		String line;
		QuotedPrintableCodec codec = new QuotedPrintableCodec();
		while ((line = r.readLine()) != null){
			String split[] = line.split(":", 2);
			if (split[0].toUpperCase().contains("QUOTED-PRINTABLE")){
				codec.decode(split[1]); //it will throw an exception and fail the test if it can't be decoded
			}
		}
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
	
	/**
	 * @author Michael Angstadt
	 * <br/>
	 * Jul 06, 2012
	 */
	@Test
	public void testParseStringWithEscappedDelimiter() {
		String nameOnly = ";Ge\\,o\\;r\\:g\\\\e;;;";
		
		String[] name = VCardUtils.parseStringWithEscappedDelimiter(nameOnly, ';');
		String[] expected = new String[]{"", "Ge\\,o\\;r\\:g\\\\e", "", "", ""};
		assertArrayEquals(expected, name);
		
		// ----------------------------------------
		
		String fullName = "Mr;Ge\\,o\\;r\\:g\\\\e;Gilbert;Smith;Jr.";
		
		name = VCardUtils.parseStringWithEscappedDelimiter(fullName, ';');
		expected = new String[]{"Mr", "Ge\\,o\\;r\\:g\\\\e", "Gilbert", "Smith", "Jr."};
		assertArrayEquals(expected, name);
	}
}
