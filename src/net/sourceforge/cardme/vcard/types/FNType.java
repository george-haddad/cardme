package net.sourceforge.cardme.vcard.types;

import java.util.Arrays;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.FNFeature;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Aug 7, 2012
 *
 */
public class FNType extends AbstractVCardType implements Comparable<FNType>, Cloneable, FNFeature {
	
	private static final long serialVersionUID = -1004137768301063932L;
	
	private String formattedName = null;
	
	public FNType() {
		super(VCardTypeName.FN);
	}
	
	public FNType(String fn) {
		super(VCardTypeName.FN);
		setFormattedName(fn);
	}

	public String getFormattedName()
	{
		if(formattedName != null) {
			return new String(formattedName);
		}
		else {
			return null;
		}
	}

	public void setFormattedName(String formattedName) throws NullPointerException {
		if(formattedName == null) {
			throw new NullPointerException("formattedName cannot be set to null.");
		}
		
		this.formattedName = new String(formattedName);
	}
	
	public boolean hasParams()
	{
		return false;
	}
	
	@Override
	public FNType clone()
	{
		FNType cloned = new FNType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		cloned.setFormattedName(formattedName);
		return cloned;
	}

	public int compareTo(FNType obj)
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
		String[] contents = new String[8];
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
		
		contents[7] = StringUtil.getString(formattedName);
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof FNType) {
				return this.compareTo((FNType)obj) == 0;
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
