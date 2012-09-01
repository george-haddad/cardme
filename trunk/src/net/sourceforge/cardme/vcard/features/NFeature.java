package net.sourceforge.cardme.vcard.features;

import java.util.List;

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Aug 7, 2012
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.1.2 N Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> N</li>
 * 	<li><b>Type purpose:</b> To specify the components of the name of the object the vCard represents.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single structured text value. Each component can have multiple values.</li>
 * 	<li><b>Type special note:</b> The structured type value corresponds, in sequence, to the Family Name, Given Name, Additional Names, Honorific Prefixes, and Honorific Suffixes. The text components are separated by the SEMI-COLON character (ASCII decimal 59). Individual text components can include multiple text values (e.g., multiple Additional Names) separated by the COMMA character (ASCII decimal 44). This type is based on the semantics of the X.520 individual name attributes. The property MUST be present in the vCard object.</li>
 * </ul>
 * </p>
 */
public interface NFeature {
	
	public String getFamilyName();
	
	public void setFamilyName(String familyName);
	
	public boolean hasFamilyName();
	
	public String getGivenName();
	
	public void setGivenName(String givenName);
	
	public boolean hasGivenName();
	
	public List<String> getAdditionalNames();
	
	public void addAdditionalName(String additionalName) throws NullPointerException;
	
	public void addAllAdditionalNames(List<String> additionalNames) throws NullPointerException;
	
	public void removeAdditionalName(String additionalName) throws NullPointerException;
	
	public boolean containsAdditionalName(String additionalName);
	
	public boolean hasAdditionalNames();
	
	public void clearAdditionalNames();
	
	public List<String> getHonorificPrefixes();
	
	public void addHonorificPrefix(String honorificPrefix) throws NullPointerException;
	
	public void addAllHonorificPrefixes(List<String> honorificPrefixes) throws NullPointerException;
	
	public void removeHonorificPrefix(String honorificPrefix) throws NullPointerException;
	
	public boolean containsHonorificPrefix(String honorificPrefix);
	
	public boolean hasHonorificPrefixes();
	
	public void clearHonorificPrefixes();
	
	public List<String> getHonorificSuffixes();
	
	public void addHonorificSuffix(String honorificSuffix) throws NullPointerException;
	
	public void addAllHonorificSuffixes(List<String> honorificSuffixes) throws NullPointerException;
	
	public void removeHonorificSuffix(String honorificSuffix) throws NullPointerException;
	
	public boolean containsHonorificSuffix(String honorificSuffix);
	
	public boolean hasHonorificSuffixes();
	
	public void clearHonorificSuffixes();
}
