package net.sourceforge.cardme.vcard.types;

import java.util.Arrays;
import java.util.List;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;
import net.sourceforge.cardme.vcard.features.EndFeature;
import net.sourceforge.cardme.vcard.types.params.ExtendedParamType;

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Aug 7, 2012
 *
 */
public class EndType extends AbstractVCardType implements Comparable<EndType>, Cloneable, EndFeature {

	private static final long serialVersionUID = -3947252068355663179L;

	public EndType() {
		super(VCardTypeName.END);
	}
	
	public boolean hasParams()
	{
		return false;
	}
	
	@Override
	public EndType clone()
	{
		EndType cloned = new EndType();
		cloned.setEncodingType(getEncodingType());
		cloned.setVCardTypeName(getVCardTypeName());

		if(hasCharset()) {
			cloned.setCharset(getCharset());
		}
		
		cloned.setGroup(getGroup());
		cloned.setLanguage(getLanguage());
		cloned.setParameterTypeStyle(getParameterTypeStyle());
		cloned.addAllExtendedParams(getExtendedParams());
		return cloned;
	}

	public int compareTo(EndType obj)
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
		String[] contents = new String[7];
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
		
		return contents;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof EndType) {
				return this.compareTo((EndType)obj) == 0;
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
