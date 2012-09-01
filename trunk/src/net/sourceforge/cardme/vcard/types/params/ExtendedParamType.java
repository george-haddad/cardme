package net.sourceforge.cardme.vcard.types.params;

import java.io.Serializable;
import java.util.Arrays;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.util.Util;
import net.sourceforge.cardme.vcard.arch.VCardParamType;
import net.sourceforge.cardme.vcard.arch.VCardTypeName;

/**
 * 
 * @author renatofilho
 * <br/>
 * Apr 30, 2012
 * <p>
 * @author George El-Haddad
 * <br/>
 * Aug 7, 2012
 *
 */
public class ExtendedParamType implements VCardParamType, Comparable<ExtendedParamType>, Cloneable, Serializable {
	
	private static final long serialVersionUID = 7002251398647663749L;
	
	private String typeName = null;
	private String typeValue = null;
	private VCardTypeName parentVCardTypeName = null;
	
	public ExtendedParamType(String typeName, VCardTypeName parentVCardTypeName) {
		this(typeName, null, parentVCardTypeName);
	}
	
	public ExtendedParamType(String typeName, String typeValue, VCardTypeName parentVCardTypeName) {
		setTypeName(typeName);
		setTypeValue(typeValue);
		setParentVCardTypeName(parentVCardTypeName);
	}
	
	public String getTypeName()
	{
		if(typeName != null) {
			return new String(typeName);
		}
		else {
			return null;
		}
	}

	public void setTypeName(String typeName) throws NullPointerException {
		if(typeName == null) {
			throw new NullPointerException("Parameter typeName cannot be set to null.");
		}
		
		this.typeName = new String(typeName);
	}

	public String getTypeValue()
	{
		if(typeValue != null) {
			return new String(typeValue);
		}
		else {
			return null;
		}
	}

	public void setTypeValue(String typeValue) {
		if(typeValue != null) {
			this.typeValue = new String(typeValue);
		}
		else {
			this.typeValue = null;
		}
	}

	public boolean hasTypeValue()
	{
		return typeValue != null;
	}
	
	public void setParentVCardTypeName(VCardTypeName parentVCardTypeName) throws NullPointerException {
		if(parentVCardTypeName == null) {
			throw new NullPointerException("parentVCardTypeName cannot be set to null.");
		}
		
		this.parentVCardTypeName = parentVCardTypeName;
	}

	public VCardTypeName getParentVCardTypeName()
	{
		return parentVCardTypeName;
	}
	
	
	//--------------------------------------------
	
	private String[] getContents()
	{
		String[] contents = new String[3];
		contents[0] = StringUtil.getString(typeName);
		contents[1] = StringUtil.getString(typeName);
		contents[2] = (parentVCardTypeName != null ? parentVCardTypeName.getType() : "");
		return contents;
	}
	
	public int compareTo(ExtendedParamType obj)
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
	public ExtendedParamType clone()
	{
		return new ExtendedParamType(typeName, typeValue, parentVCardTypeName);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof ExtendedParamType) {
				return this.compareTo((ExtendedParamType)obj) == 0;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode()
	{
		return Util.generateHashCode(getContents());
	}
	
	@Override
	public String toString()
	{
		String[] args = getContents();
		StringBuilder sb = new StringBuilder();
		sb.append(ExtendedParamType.class.getName());
		sb.append(" [");
		
		for(int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			sb.append(",");
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
}
