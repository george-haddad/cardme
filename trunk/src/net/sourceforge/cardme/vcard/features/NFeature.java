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
	
	/**
	 * <p>Retrieves the family name or null if not set.</p>
	 * 
	 * @return the family name or null if not set
	 */
	public String getFamilyName();
	
	/**
	 * <p>Sets the family name.</p>
	 * 
	 * @param familyName - the family name to set
	 */
	public void setFamilyName(String familyName);
	
	/**
	 * <p>Indicates if a family name was set or not.</p>
	 * 
	 * @return true if a family name was set or false otherwise
	 */
	public boolean hasFamilyName();
	
	/**
	 * <p>Retrieves the given name or null if not set.</p>
	 * 
	 * @return the given name or null if not set
	 */
	public String getGivenName();
	
	/**
	 * <p>Sets the given name.</p>
	 * 
	 * @param givenName - the given name to set
	 */
	public void setGivenName(String givenName);
	
	/**
	 * <p>Indicates if a given name was set or not.</p>
	 * 
	 * @return true if a given name was set or false otherwise
	 */
	public boolean hasGivenName();
	
	/**
	 * <p>Retrieves a list of additional names.</p>
	 * 
	 * @return a list of additional names
	 */
	public List<String> getAdditionalNames();
	
	/**
	 * <p>Adds an additional name.</p>
	 * 
	 * @param additionalName - the additional name to add
	 * @throws NullPointerException if adding a null additional name
	 */
	public void addAdditionalName(String additionalName) throws NullPointerException;
	
	/**
	 * <p>Adds a list of additional names.</p>
	 * 
	 * @param additionalNames - the list of additional names to add
	 * @throws NullPointerException if adding a null list of additional names
	 */
	public void addAllAdditionalNames(List<String> additionalNames) throws NullPointerException;
	
	/**
	 * <p>Removes the specified additional name.</p>
	 * 
	 * @param additionalName - the additional name to remove
	 * @throws NullPointerException if removing a null additional name
	 */
	public void removeAdditionalName(String additionalName) throws NullPointerException;
	
	/**
	 * <p>Indicates if the specified additional name exists.</p>
	 * 
	 * @param additionalName - the additional name to check
	 * @return true if the additional name exists
	 */
	public boolean containsAdditionalName(String additionalName);
	
	/**
	 * <p>Indicates whether additional names have been added or not.</p>
	 * 
	 * @return true if additional names have been added
	 */
	public boolean hasAdditionalNames();
	
	/**
	 * <p>Removes all additional names.</p>
	 */
	public void clearAdditionalNames();
	
	/**
	 * <p>Retrieves a list of honorific prefixes.</p>
	 * 
	 * @return a list of honorific prefixes
	 */
	public List<String> getHonorificPrefixes();
	
	/**
	 * <p>Adds a honorific prefix.</p>
	 * 
	 * @param honorificPrefix - the honorific prefix to add
	 * @throws NullPointerException if adding a null honorific prefix
	 */
	public void addHonorificPrefix(String honorificPrefix) throws NullPointerException;
	
	/**
	 * <p>Adds a list of honorifc prefixes.</p>
	 * 
	 * @param honorificPrefixes - the list of honorific prefixes to add
	 * @throws NullPointerException if adding a null list of honorific prefixes
	 */
	public void addAllHonorificPrefixes(List<String> honorificPrefixes) throws NullPointerException;
	
	/**
	 * <p>Removes the specified honorific prefix.</p>
	 * 
	 * @param honorificPrefix - the honorific prefix to remove
	 * @throws NullPointerException if removing a null honorific prefix
	 */
	public void removeHonorificPrefix(String honorificPrefix) throws NullPointerException;
	
	/**
	 * <p>Indicates whether the specified honorific prefix exists.</p>
	 * 
	 * @param honorificPrefix - the honorific prefix to check
	 * @return true if the specified honorific prefix exists
	 */
	public boolean containsHonorificPrefix(String honorificPrefix);
	
	/**
	 * <p>Indicates if honorific prefixes have been added or not.</p>
	 * 
	 * @return true if honorific prefixes have been added
	 */
	public boolean hasHonorificPrefixes();
	
	/**
	 * <p>Removes all honorific prefixes.</p>
	 */
	public void clearHonorificPrefixes();
	
	/**
	 * <p>Retrieves a list of honorific suffixes.</p>
	 * 
	 * @return a list of honorific suffixes
	 */
	public List<String> getHonorificSuffixes();
	
	/**
	 * <p>Adds a honorific suffix.</p>
	 * 
	 * @param honorificSuffix - the honorific suffix to add
	 * @throws NullPointerException if adding a null honorific suffix
	 */
	public void addHonorificSuffix(String honorificSuffix) throws NullPointerException;
	
	/**
	 * <p>Adds a list of honorific suffixes.</p>
	 * 
	 * @param honorificSuffixes - the list of honorific suffixes to add
	 * @throws NullPointerException if adding a null list of honorific suffixes
	 */
	public void addAllHonorificSuffixes(List<String> honorificSuffixes) throws NullPointerException;
	
	/**
	 * <p>Removes the specified honorific suffix.</p>
	 * 
	 * @param honorificSuffix - the honorific suffix to remove
	 * @throws NullPointerException if removing a null honorific suffix
	 */
	public void removeHonorificSuffix(String honorificSuffix) throws NullPointerException;
	
	/**
	 * <p>Indicates if the specified honorific suffix has been added or not.</p>
	 * 
	 * @param honorificSuffix - the honorific suffix to check
	 * @return true if the specified honorific suffix has been added or false otherwise
	 */
	public boolean containsHonorificSuffix(String honorificSuffix);
	
	/**
	 * <p>Indicates whether honorific suffixes have been added or not.</p>
	 * 
	 * @return true if honorific suffixes have been added or false otherwise
	 */
	public boolean hasHonorificSuffixes();
	
	/**
	 * <p>Removes all honorific suffixes.</p>
	 */
	public void clearHonorificSuffixes();
}
