package net.sourceforge.cardme.vcard.types;

import java.util.Arrays;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.EncodingType;
import net.sourceforge.cardme.vcard.arch.VCardBinaryType;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.KeyFeature;
import net.sourceforge.cardme.vcard.types.media.KeyTextType;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Aug 11, 2012
 * <p>
 * @author Michael Angstadt
 * <br/>
 * Aug 8, 2012
 */
public class KeyType  extends AbstractVCardType implements VCardBinaryType, Comparable<KeyType>, Cloneable, KeyFeature {
	
	private static final long serialVersionUID = -7591977789942710446L;
	
	private byte[] keyBytes = null;
	private KeyTextType keyTextType = null;
	private boolean compressed = false;
	
	public KeyType() {
		this((String)null, KeyTextType.B);
	}
	
	public KeyType(String keyText, KeyTextType keyTextType) {
		super(VCardTypeName.KEY);
		setKey(keyText);
		setKeyTextType(keyTextType);
	}
	
	public KeyType(byte[] keyBytes, KeyTextType keyTextType) {
		super(VCardTypeName.KEY);
		setKey(keyBytes);
		setKeyTextType(keyTextType);
	}

	public byte[] getKey()
	{
		return getBinaryData();
	}

	public void setKey(byte[] keyBytes) {
		setBinaryData(keyBytes);
		setEncodingType(EncodingType.BINARY);
	}

	public void setKey(String keyText) {
		if(keyText != null) {
			setBinaryData(keyText.getBytes());
			setEncodingType(EncodingType.EIGHT_BIT);
		}
		else {
			setBinaryData(null);
			setEncodingType(EncodingType.EIGHT_BIT);
		}
	}

	public boolean hasKey()
	{
		return keyBytes != null;
	}

	public void clearKey() {
		keyBytes = null;
	}

	public KeyTextType getKeyTextType()
	{
		return keyTextType;
	}

	public void setKeyTextType(KeyTextType keyTextType) {
		this.keyTextType = keyTextType;
	}

	public boolean hasKeyTextType()
	{
		return keyTextType != null;
	}

	public boolean isCompressed()
	{
		return compressed;
	}

	public void setCompression(boolean compressed) {
		this.compressed = compressed;
	}

	public byte[] getBinaryData()
	{
		if(keyBytes != null) {
			return keyBytes;
		}
		else {
			return null;
		}
	}

	public void setBinaryData(byte[] binaryData) {
		if(binaryData != null) {
			this.keyBytes = Arrays.copyOf(binaryData, binaryData.length);
		}
		else {
			this.keyBytes = null;
		}
	}

	public boolean isBinary()
	{
		switch(getEncodingType())
		{
			case BASE64:
			case BINARY:
			{
				return true;
			}
			
			default:
			{
				return false;
			}
		}
	}
	
	public boolean hasParams()
	{
		return false;
	}
	
	@Override
	public KeyType clone()
	{
		KeyType cloned = new KeyType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		
		if(keyBytes != null) {
			byte[] clonedBytes = Arrays.copyOf(keyBytes, keyBytes.length);
			cloned.setKey(clonedBytes);
		}
		
		if(keyTextType != null) {
			cloned.setKeyTextType(keyTextType);
		}
		
		cloned.setCompression(compressed);
		
		return cloned;
	}
	
	public int compareTo(KeyType obj)
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
		String[] contents = new String[10];
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
		
		if(keyBytes != null) {
			contents[7] = StringUtil.toHexString(keyBytes);
		}
		else {
			contents[7] = "";
		}
		
		if(keyTextType != null) {
			contents[8] = keyTextType.toString();
		}
		else {
			contents[8] = "";
		}
		
		contents[9] = String.valueOf(compressed);
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof KeyType) {
				return this.compareTo((KeyType)obj) == 0;
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
