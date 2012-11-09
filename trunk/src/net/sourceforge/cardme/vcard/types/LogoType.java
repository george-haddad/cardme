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
import net.sourceforge.cardme.vcard.features.LogoFeature;
import net.sourceforge.cardme.vcard.types.media.ImageMediaType;
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
 * Aug 7, 2012
 *
 */
public class LogoType extends AbstractVCardType implements VCardBinaryType, Comparable<LogoType>, Cloneable, LogoFeature {
	
	private static final long serialVersionUID = -8670562134417464424L;
	
	private byte[] logoBytes = null;
	private URI logoUri = null;
	private ImageMediaType imageMediaType = null;
	private boolean compressed = false;
	
	public LogoType() {
		this((byte[])null);
	}
	
	public LogoType(byte[] logoBytes) {
		super(VCardTypeName.LOGO);
		setLogo(logoBytes);
	}

	public LogoType(URI photoUri) {
		super(VCardTypeName.LOGO);
		setLogoURI(photoUri);
	}

	@Override
	public byte[] getLogo()
	{
		return getBinaryData();
	}

	@Override
	public void setLogo(byte[] logo) {
		setBinaryData(logo);
	}

	@Override
	public boolean hasLogo()
	{
		return logoBytes != null || logoUri != null;
	}
	
	@Override
	public void clearLogo() {
		logoBytes = null;
		logoUri = null;
	}
	
	@Override
	public URI getLogoURI()
	{
		return logoUri;
	}

	@Override
	public void setLogoURI(URI logoUri) {
		if(logoUri != null) {
			this.logoUri = logoUri;
		}
		else {
			this.logoUri = null;
		}
	}

	@Override
	public boolean isURI()
	{
		return logoUri != null;
	}

	@Override
	public void setLogoURL(URL logoUrl) throws URISyntaxException {
		if(logoUrl != null) {
			this.logoUri = logoUrl.toURI();
		}
	}

	@Override
	public URL getLogoURL() throws MalformedURLException
	{
		if(logoUri != null) {
			return logoUri.toURL();
		}
		else {
			return null;
		}
	}

	@Override
	public ImageMediaType getImageMediaType()
	{
		return imageMediaType;
	}

	@Override
	public void setImageMediaType(ImageMediaType imageMediaType) {
		this.imageMediaType = imageMediaType;
	}

	@Override
	public boolean hasImageMediaType()
	{
		return imageMediaType != null;
	}

	@Override
	public boolean isCompressed()
	{
		return compressed;
	}

	@Override
	public void setCompression(boolean compressed)
	{
		this.compressed = compressed;
		
	}

	@Override
	public byte[] getBinaryData()
	{
		if(logoBytes != null) {
			return logoBytes;
		}
		else {
			return null;
		}
	}

	@Override
	public void setBinaryData(byte[] binaryData) {
		if(binaryData != null) {
			this.logoBytes = Arrays.copyOf(binaryData, binaryData.length);
		}
		else {
			this.logoBytes = null;
		}
	}

	@Override
	public boolean isBinary()
	{
		return EncodingType.BINARY.equals(getEncodingType());
	}
	
	@Override
	public boolean hasParams()
	{
		return false;
	}
	
	@Override
	public LogoType clone()
	{
		LogoType cloned = new LogoType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		
		if(logoBytes != null) {
			byte[] clonedBytes = Arrays.copyOf(logoBytes, logoBytes.length);
			cloned.setLogo(clonedBytes);
		}
		
		if(logoUri != null) {
			cloned.setLogoURI(logoUri);
		}
		
		if(imageMediaType != null) {
			cloned.setImageMediaType(imageMediaType);
		}
		
		cloned.setCompression(compressed);
		return cloned;
	}
	
	@Override
	public int compareTo(LogoType obj)
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
		
		if(logoBytes != null) {
			contents[7] = StringUtil.toHexString(logoBytes);
		}
		else {
			contents[7] = "";
		}
		
		if(logoUri != null) {
			contents[8] = logoUri.toString();
		}
		else {
			contents[8] = "";
		}
		
		if(imageMediaType != null) {
			contents[9] = imageMediaType.toString();
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
			if(obj instanceof LogoType) {
				return this.compareTo((LogoType)obj) == 0;
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
