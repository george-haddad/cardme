package net.sourceforge.cardme.vcard.features;

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Aug 7, 2012
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.1.1 FN Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> FN</li>
 * 	<li><b>Type purpose:</b> To specify the formatted text corresponding to the name of the object the vCard represents.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single text value.</li>
 * 	<li><b>Type special notes:</b> This type is based on the semantics of the X.520 Common Name attribute. The property MUST be present in the vCard object.</li>
 * </ul>
 * </p>
 */
public interface FNFeature {
	
	/**
	 * <p>Retrieves the formatted name.</p>
	 * 
	 * @return the formatted name or null if not set
	 */
	public String getFormattedName();
	
	/**
	 * <p>Sets the formatted name. Setting to null will clear it.</p>
	 * 
	 * @param formattedName - the formatted name to set
	 */
	public void setFormattedName(String formattedName);
}
