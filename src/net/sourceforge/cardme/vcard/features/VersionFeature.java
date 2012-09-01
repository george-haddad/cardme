package net.sourceforge.cardme.vcard.features;

import net.sourceforge.cardme.vcard.arch.VCardVersion;

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Aug 7, 2012
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.6.9 VERSION Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> VERSION</li>
 * 	<li><b>Type purpose:</b> To specify the version of the vCard specification used to format this vCard.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single text value.</li>
 * 	<li><b>Type special note:</b> The property MUST be present in the vCard object. The value MUST be "3.0" if the vCard corresponds to this specification.</li>
 * </ul>
 * </p>
 */
public interface VersionFeature {
	
	public VCardVersion getVersion();
	
	public void setVersion(VCardVersion version);
}
