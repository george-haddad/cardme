package net.sourceforge.cardme.vcard.types;

import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.EncodingType;
import net.sourceforge.cardme.vcard.VCardType;
import net.sourceforge.cardme.vcard.features.KeyFeature;
import net.sourceforge.cardme.vcard.types.media.KeyTextType;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import java.util.Arrays;

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
 * Feb 4, 2010
 * <p>
 * @author Michael Angstadt
 * <br/>
 * Aug 8, 2012
 */
public class KeyType extends Type implements KeyFeature {

	private static final long serialVersionUID = 4106254442611479828L;
	
	private byte[] keyBytes = null;
	private KeyTextType keyTextType = null;
	private boolean isSetCompression = false;

	public KeyType() {
		super(EncodingType.BINARY, ParameterTypeStyle.PARAMETER_VALUE_LIST);
	}
	
	/**
	 * @param keyBytes
	 *                the binary data of the key
	 * @param keyTextType
	 *                the type of key (e.g. PGP)
	 */
	public KeyType(byte[] keyBytes, KeyTextType keyTextType) {
		super(EncodingType.BINARY, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		setKey(keyBytes);
		setKeyTextType(keyTextType);
	}
	
	/**
	 * @param keyText
	 *                a plain text representation of the key
	 * @param keyTextType
	 *                the type of key (e.g. PGP)
	 */
	public KeyType(String keyText, KeyTextType keyTextType) {
		super(EncodingType.EIGHT_BIT, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		setKey(keyText);
		setKeyTextType(keyTextType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public byte[] getKey()
	{
		return keyBytes;
	}

	/**
	 * {@inheritDoc}
	 */
	public KeyTextType getKeyTextType()
	{
		return keyTextType;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setKeyTextType(KeyTextType keyTextType) {
		this.keyTextType = keyTextType;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setKey(byte[] keyBytes) {
		this.keyBytes = keyBytes;
		setEncodingType(EncodingType.BINARY);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setKey(String keyText) {
		this.keyBytes = keyText.getBytes();
		setEncodingType(EncodingType.EIGHT_BIT);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasKeyTextType()
	{
		return keyTextType != null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void clearKey() {
		keyBytes = null;
		keyTextType = null;
		encodingType = null;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasKey()
	{
		return keyBytes != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setCompression(boolean compression) {
		isSetCompression = compression;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isSetCompression()
	{
		return isSetCompression;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isInline()
	{
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeString()
	{
		return VCardType.KEY.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof KeyType) {
				if(this == obj || ((KeyType)obj).hashCode() == this.hashCode()) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return Util.generateHashCode(toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName());
		sb.append("[ ");
		if(encodingType != null) {
			sb.append(encodingType.getType());
			sb.append(",");
		}
		
		if(keyTextType != null) {
			sb.append(keyTextType.getTypeName());
			sb.append(",");
		}
		
		if(keyBytes != null) {
			sb.append(StringUtil.toHexString(keyBytes));
			sb.append(",");
		}

		if(super.id != null) {
			sb.append(super.id);
			sb.append(",");
		}
		
		sb.deleteCharAt(sb.length()-1);	//Remove last comma.
		sb.append(" ]");
		return sb.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public KeyFeature clone()
	{
		KeyType cloned = new KeyType();
		
		if(keyBytes != null) {
			byte[] clonedBytes = Arrays.copyOf(keyBytes, keyBytes.length);
			cloned.setKey(clonedBytes);
		}
		
		if(keyTextType != null) {
			cloned.setKeyTextType(keyTextType);
		}
		
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
