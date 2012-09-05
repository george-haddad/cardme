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
import net.sourceforge.cardme.vcard.features.PhotoFeature;
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
public class PhotoType extends AbstractVCardType implements VCardBinaryType, Comparable<PhotoType>, Cloneable, PhotoFeature {
	
	private static final long serialVersionUID = -3985587669475414344L;
	
	private byte[] photoBytes = null;
	private URI photoUri = null;
	private ImageMediaType imageMediaType = null;
	private boolean compressed = false;
	
	public PhotoType() {
		this((byte[])null);
	}
	
	public PhotoType(byte[] photoBytes) {
		super(VCardTypeName.PHOTO);
		setPhoto(photoBytes);
	}

	public PhotoType(URI photoUri) {
		super(VCardTypeName.PHOTO);
		setPhotoURI(photoUri);
	}

	public byte[] getPhoto()
	{
		return getBinaryData();
	}

	public void setPhoto(byte[] photo) {
		setBinaryData(photo);
	}

	public boolean hasPhoto()
	{
		return photoBytes != null || photoUri != null;
	}
	
	public void clearPhoto() {
		photoBytes = null;
		photoUri = null;
	}

	public URI getPhotoURI()
	{
		return photoUri;
	}

	public void setPhotoURI(URI photoUri) {
		if(photoUri != null) {
			this.photoUri = photoUri;
		}
		else {
			this.photoUri = null;
		}
	}

	public boolean isURI()
	{
		return photoUri != null;
	}

	public void setPhotoURL(URL photoUrl) throws URISyntaxException {
		if(photoUrl != null) {
			this.photoUri = photoUrl.toURI();
		}
	}

	public URL getPhotoURL() throws MalformedURLException
	{
		if(photoUri != null) {
			return photoUri.toURL();
		}
		else {
			return null;
		}
	}

	public ImageMediaType getImageMediaType()
	{
		return imageMediaType;
	}

	public void setImageMediaType(ImageMediaType imageMediaType) {
		if(imageMediaType != null) {
			this.imageMediaType = imageMediaType;
		}
	}

	public boolean hasImageMediaType()
	{
		return imageMediaType != null;
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
		if(photoBytes != null) {
			return photoBytes;
		}
		else {
			return null;
		}
	}

	public void setBinaryData(byte[] binaryData) {
		if(binaryData != null) {
			this.photoBytes = Arrays.copyOf(binaryData, binaryData.length);
		}
		else {
			this.photoBytes = null;
		}
	}

	public boolean isBinary()
	{
		EncodingType t = getEncodingType();
		return EncodingType.BINARY.equals(t) || EncodingType.BASE64.equals(t);
	}
	
	public boolean hasParams()
	{
		return false;
	}

	@Override
	public PhotoType clone()
	{
		PhotoType cloned = new PhotoType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		
		if(photoBytes != null) {
			byte[] clonedBytes = Arrays.copyOf(photoBytes, photoBytes.length);
			cloned.setPhoto(clonedBytes);
		}
		
		if(photoUri != null) {
			cloned.setPhotoURI(photoUri);
		}
		
		if(imageMediaType != null) {
			cloned.setImageMediaType(imageMediaType);
		}
		
		cloned.setCompression(compressed);
		return cloned;
	}
	
	public int compareTo(PhotoType obj)
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
		
		if(photoBytes != null) {
			contents[7] = StringUtil.toHexString(photoBytes);
		}
		else {
			contents[7] = "";
		}
		
		if(photoUri != null) {
			contents[8] = photoUri.toString();
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
			if(obj instanceof PhotoType) {
				return this.compareTo((PhotoType)obj) == 0;
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
