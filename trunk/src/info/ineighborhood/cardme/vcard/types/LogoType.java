package info.ineighborhood.cardme.vcard.types;

import info.ineighborhood.cardme.util.StringUtil;
import info.ineighborhood.cardme.util.Util;
import info.ineighborhood.cardme.vcard.EncodingType;
import info.ineighborhood.cardme.vcard.VCardType;
import info.ineighborhood.cardme.vcard.features.LogoFeature;
import info.ineighborhood.cardme.vcard.types.media.ImageMediaType;
import info.ineighborhood.cardme.vcard.types.parameters.LogoParameterType;
import info.ineighborhood.cardme.vcard.types.parameters.ParameterTypeStyle;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

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
 * Feb 4, 2010
 *
 */
public class LogoType extends Type implements LogoFeature {

	private byte[] logoBytes = null;
	private URI logoUri = null;
	private LogoParameterType logoParameterType = null;
	private ImageMediaType imageMediaType = null;
	private boolean isSetCompression = false;
	
	public LogoType() {
		super(EncodingType.BINARY, ParameterTypeStyle.PARAMETER_VALUE_LIST);
	}
	
	public LogoType(URI logoUri, EncodingType encodingType, LogoParameterType logoParameterType) {
		super(encodingType, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		setLogoURI(logoUri);
		setLogoParameterType(logoParameterType);
	}
	
	public LogoType(byte[] logoBytes, EncodingType encodingType, LogoParameterType logoParameterType) {
		super(encodingType, ParameterTypeStyle.PARAMETER_VALUE_LIST);
		setLogo(logoBytes);
		setLogoParameterType(logoParameterType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public byte[] getLogo()
	{
		return logoBytes;
	}

	/**
	 * {@inheritDoc}
	 */
	public URI getLogoURI()
	{
		return logoUri;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public URL getLogoURL() throws MalformedURLException
	{
		return logoUri.toURL();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasLogo()
	{
		return logoBytes != null || logoUri != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isURI()
	{
		return logoUri != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isInline()
	{
		return logoBytes != null;
	}

	/**
	 * {@inheritDoc}
	 */
	public LogoParameterType getLogoParameterType()
	{
		return logoParameterType;
	}

	/**
	 * {@inheritDoc}
	 */
	public ImageMediaType getImageMediaType()
	{
		return imageMediaType;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLogoParameterType(LogoParameterType logoParameterType) {
		this.logoParameterType = logoParameterType;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setImageMediaType(ImageMediaType imageMediaType) {
		this.imageMediaType = imageMediaType;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLogo(byte[] logoBytes) {
		this.logoBytes = logoBytes;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLogoURI(URI logoUri) {
		this.logoUri = logoUri;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setLogoURL(URL logoUrl) throws URISyntaxException {
		this.logoUri = logoUrl.toURI();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean hasLogoParameterType()
	{
		return logoParameterType != null;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasImageMediaType()
	{
		return imageMediaType != null;
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
	@Override
	public String getTypeString()
	{
		return VCardType.LOGO.getType();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof LogoType) {
				if(this == obj || ((LogoType)obj).hashCode() == this.hashCode()) {
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
		
		if(logoBytes != null) {
			sb.append(StringUtil.toHexString(logoBytes));
			sb.append(",");
		}
		
		if(logoUri != null) {
			sb.append(logoUri.getPath());
			sb.append(",");
		}
		
		if(logoParameterType != null) {
			sb.append(logoParameterType.getTypeName());
			sb.append(",");
		}
		
		if(imageMediaType != null) {
			sb.append(imageMediaType.getTypeName());
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
	public LogoFeature clone()
	{
		LogoType cloned = new LogoType();
		
		if(logoBytes != null) {
			byte[] clonedBytes = Arrays.copyOf(logoBytes, logoBytes.length);
			cloned.setLogo(clonedBytes);
		}
		
		if(logoUri != null) {
			try {
				cloned.setLogoURI(new URI(logoUri.getPath()));
			}
			catch(URISyntaxException e) {
				cloned.setLogoURI(null);
			}
		}
		
		if(logoParameterType != null) {
			cloned.setLogoParameterType(logoParameterType);
		}
		
		if(imageMediaType != null) {
			cloned.setImageMediaType(imageMediaType);
		}
		
		cloned.setCompression(isSetCompression);
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.setEncodingType(getEncodingType());
		cloned.setID(getID());
		return cloned;
	}
}
