package net.sourceforge.cardme.vcard.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardImpl;
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
public class AgentTypeTest {

	private AgentType agentType = null;
	private VCard agentVCard = null;
	
	@Before
	public void setUp() throws Exception {
		agentVCard = new VCardImpl();
		agentVCard.setN(new NType("Doe", "John"));
		agentVCard.setFN(new FNType("John Doe"));
		
		agentType = new AgentType();
		agentType.setAgent(agentVCard);
	}
	
	@After
	public void tearDown() throws Exception {
		agentType = null;
		agentVCard = null;
	}
	
	@Test
	public void testGetTypeString() {
		assertEquals(VCardTypeName.AGENT.getType(), agentType.getVCardTypeName().getType());
	}
	
	@Test
	public void testEquals() {
		VCard agentVCard2 = new VCardImpl();
		agentVCard2.setN(new NType("Doe", "John"));
		agentVCard2.setFN(new FNType("John Doe"));
		
		AgentType agentType2 = new AgentType();
		agentType2.setAgent(agentVCard2);
		
		assertTrue(agentType.equals(agentType2));
	}
	
	@Test
	public void testHashcode() {
		VCard agentVCard2 = new VCardImpl();
		agentVCard2.setN(new NType("Doe", "John"));
		agentVCard2.setFN(new FNType("John Doe"));
		
		AgentType agentType2 = new AgentType();
		agentType2.setAgent(agentVCard2);
		
		int hcode1 = agentType.hashCode();
		int hcode2 = agentType2.hashCode();
		assertEquals(hcode1, hcode2);
	}
	
	@Test
	public void testCompareTo() {
		VCard agentVCard2 = new VCardImpl();
		agentVCard2.setN(new NType("Doe", "John"));
		agentVCard2.setFN(new FNType("John Doe"));
		
		AgentType agentType2 = new AgentType();
		agentType2.setAgent(agentVCard2);
		
		assertTrue(agentType.compareTo(agentType2) == 0);
	}
	
	@Test
	public void testClone() {
		AgentType cloned = agentType.clone();
		assertEquals(cloned, agentType);
		assertTrue(agentType.equals(cloned));
		assertTrue(agentType.compareTo(cloned) == 0);
	}
}
