package net.sourceforge.cardme.vcard.types;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.EncodingType;
import net.sourceforge.cardme.vcard.arch.VCardBinaryType;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.SoundFeature;
import net.sourceforge.cardme.vcard.types.media.AudioMediaType;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;

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
 * Aug 9, 2012
 *
 */
public class SoundType extends AbstractVCardType implements VCardBinaryType, Comparable<SoundType>, Cloneable, SoundFeature {
	
	private static final long serialVersionUID = 4114549778577481477L;
	
	private byte[] soundBytes = null;
	private URI soundUri = null;
	private AudioMediaType soundMediaType = null;
	private boolean compressed = false;
	
	public SoundType() {
		this((byte[])null);
	}
	
	public SoundType(byte[] soundBytes) {
		super(VCardTypeName.SOUND);
		setSound(soundBytes);
	}

	public SoundType(URI soundUri) {
		super(VCardTypeName.SOUND);
		setSoundURI(soundUri);
	}

	public byte[] getSound()
	{
		return getBinaryData();
	}

	public void setSound(byte[] logo) {
		setBinaryData(logo);
	}

	public boolean hasSound()
	{
		return soundBytes != null || soundUri != null;
	}
	
	public void clearSound() {
		soundBytes = null;
		soundUri = null;
	}
	
	public URI getSoundURI()
	{
		return soundUri;
	}

	public void setSoundURI(URI soundUri) {
		if(soundUri != null) {
			this.soundUri = soundUri;
		}
		else {
			this.soundUri = null;
		}
	}

	public boolean isURI()
	{
		return soundUri != null;
	}

	public void setSoundURL(URL soundUrl) throws URISyntaxException {
		if(soundUrl != null) {
			this.soundUri = soundUrl.toURI();
		}
	}

	public URL getSoundURL() throws MalformedURLException
	{
		if(soundUri != null) {
			return soundUri.toURL();
		}
		else {
			return null;
		}
	}

	public AudioMediaType getAudioMediaType()
	{
		return soundMediaType;
	}

	public void setAudioMediaType(AudioMediaType audioMediaType) {
		if(audioMediaType != null) {
			this.soundMediaType = audioMediaType;
		}
	}

	public boolean hasAudioMediaType()
	{
		return soundMediaType != null;
	}

	public boolean isCompressed()
	{
		return compressed;
	}

	public void setCompression(boolean compressed)
	{
		this.compressed = compressed;
		
	}

	public byte[] getBinaryData()
	{
		if(soundBytes != null) {
			return soundBytes;
		}
		else {
			return null;
		}
	}

	public void setBinaryData(byte[] binaryData) {
		if(binaryData != null) {
			this.soundBytes = Arrays.copyOf(binaryData, binaryData.length);
		}
		else {
			this.soundBytes = null;
		}
	}

	public boolean isBinary()
	{
		return EncodingType.BINARY.equals(getEncodingType());
	}
	
	public boolean hasParams()
	{
		return false;
	}

	@Override
	public SoundType clone()
	{
		SoundType cloned = new SoundType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		
		if(soundBytes != null) {
			byte[] clonedBytes = Arrays.copyOf(soundBytes, soundBytes.length);
			cloned.setSound(clonedBytes);
		}
		
		if(soundUri != null) {
			cloned.setSoundURI(soundUri);
		}
		
		if(soundMediaType != null) {
			cloned.setAudioMediaType(soundMediaType);
		}
		
		cloned.setCompression(compressed);
		return cloned;
	}
	
	public int compareTo(SoundType obj)
	{
		if(obj != null) {
			String[] contents = obj.getContents();
			String[] myContents = getContents();
			if(Arrays.equals(myContents, contents)) {
				return 0;
			}
			else {
				return 1;
			}
		}
		else {
			return -1;
		}
	}

	@Override
	protected String[] getContents()
	{
		String[] contents = new String[11];
		contents[0] = getVCardTypeName().getType();
		contents[1] = getEncodingType().getType();
		contents[2] = StringUtil.getString(getGroup());
		contents[3] = (getCharset() != null ? getCharset().name() : "");
		contents[4] = (getLanguage() != null ? getLanguage().getLanguageCode() : "");
		contents[5] = getParameterTypeStyle().toString();
		
		if(hasExtendedParams()) {
			List<ExtendedParamType> xParams = getExtendedParams();
			StringBuilder sb = new StringBuilder();
			for(ExtendedParamType xParamType : xParams) {
				sb.append(xParamType.toString());
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
			contents[6] = sb.toString();
		}
		else {
			contents[6] = "";
		}
		
		if(soundBytes != null) {
			contents[7] = StringUtil.toHexString(soundBytes);
		}
		else {
			contents[7] = "";
		}
		
		if(soundUri != null) {
			contents[8] = soundUri.toString();
		}
		else {
			contents[8] = "";
		}
		
		if(soundMediaType != null) {
			contents[9] = soundMediaType.toString();
		}
		else {
			contents[9] = "";
		}
		
		contents[10] = String.valueOf(compressed);
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof SoundType) {
				return this.compareTo((SoundType)obj) == 0;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
