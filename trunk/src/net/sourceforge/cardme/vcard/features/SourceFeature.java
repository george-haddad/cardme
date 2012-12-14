package net.sourceforge.cardme.vcard.features;

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Aug 7, 2012
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>2.1.4 SOURCE Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> SOURCE</li>
 * 	<li><b>Type purpose:</b> If the SOURCE type is present, then its value provides information how to find the source for the vCard.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single text value.</li>
 * </ul>
 * </p>
 */
public interface SourceFeature {
	
	/**
	 * <p>Retrieves the source.</p>
	 * 
	 * @return the source or null if not set
	 */
	public String getSource();
	
	/**
	 * <p>Sets the source.</p>
	 * 
	 * @param source - the source to set
	 */
	public void setSource(String source);
	
	/**
	 * <p>Indicates if the source has been set.</p>
	 * 
	 * @return true if the source was set or false otherwise
	 */
	public boolean hasSource();
	
	/**
	 * <p>Removes the source.</p>
	 */
	public void clearSource();
}
