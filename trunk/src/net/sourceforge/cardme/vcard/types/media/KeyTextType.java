package net.sourceforge.cardme.vcard.types.media;

import java.lang.reflect.Field;

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
 * Mar 10, 2010
 * <p>
 * @author Michael Angstadt
 * <br/>
 * Jul 06, 2012
 *
 */
public class KeyTextType {

	public static final KeyTextType PGP = new KeyTextType("PGP", "PGP", "pgp");
	public static final KeyTextType GPG = new KeyTextType("GPG", "GPG", "gpg");
	public static final KeyTextType X509 = new KeyTextType("X509", "X509", "");
	public static final KeyTextType B = new KeyTextType("B", "B", "");

	private final String typeName;
	private final String ianaRegisteredName;
	private final String extension;
	
	/**
	 * <p>Use of this constructor is discouraged. Please use one of the predefined
	 * static objects.</p>
	 * 
	 * @param _typeName the type name (e.g. "PGP")
	 * @param _ianaRegisteredName the IANA registered name (e.g. "PGP")
	 * @param _extension the file extension used for this type (e.g. "pgp")
	 */
	public KeyTextType(String _typeName, String _ianaRegisteredName, String _extension)
	{
		typeName = _typeName;
		ianaRegisteredName = _ianaRegisteredName;
		extension = _extension;
	}
	
	/**
	 * <p>Retrieves the IANA key type name.</p>
	 * 
	 * @return the IANA key type name
	 */
	public String getTypeName()
	{
		return typeName;
	}
	
	/**
	 * <p>Retrieves the IANA key registered name.</p>
	 * 
	 * @return the IANA key registered name
	 */
	public String getIanaRegisteredName()
	{
		return ianaRegisteredName;
	}
	
	/**
	 * <p>Retrieves the IANA key extension.</p>
	 * 
	 * @return the IANA key extension
	 */
	public String getExtension()
	{
		return extension;
	}
	
	@Override
	public String toString()
	{
		return typeName;
	}
	
	/**
	 * <p>Retrieves one of the static objects in this class by name.</p>
	 * 
	 * @param typeName the type name (e.g. "PGP")
	 * @return the object associated with the given type name or null if none was found
	 */
	public static KeyTextType valueOf(String typeName)
	{
		typeName = typeName.replaceAll("-", "_").toUpperCase();
		try {
			Field f = KeyTextType.class.getField(typeName);
			Object obj = f.get(null);
			if (obj instanceof KeyTextType) {
				return (KeyTextType)obj;
			}
		}
		catch (Exception ex) {
			//static field not found
		}
		
		return null;
	}
}
