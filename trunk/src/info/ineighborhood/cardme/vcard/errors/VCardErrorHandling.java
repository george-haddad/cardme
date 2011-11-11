package info.ineighborhood.cardme.vcard.errors;

import info.ineighborhood.cardme.vcard.ProblemSeverity;
import java.util.List;

/**
 * Copyright 2011 George El-Haddad. All rights reserved.
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
 * @author Wolfgang Fahl
 * <br/>
 * Feb 5, 2010
 *
 */
public interface VCardErrorHandling {
	
	/**
	 * <p>Returns a list of errors that were gathered.</p>
	 *
	 * @return {@link List}&lt;VCardError&gt;
	 */
	public List<VCardError> getErrors();
	
	/**
	 * <p>Returns the problem severity of the last error that occurred.</p>
	 *
	 * @return {@link ProblemSeverity}
	 */
	public ProblemSeverity getProblemSeverity();
	
//	/**
//	 * <p>Sets the validity of this vcard.</p>
//	 *
//	 * @param valid
//	 * 	- true for this vcard to be valid
//	 */
//	public void setValid(boolean valid) ;
	
	/**
	 * <p>Returns true if this vcard is valid.</p>
	 *
	 * @return boolean
	 * 	- true if this vcard is valid
	 */
	public boolean isValid();
	
	/**
	 * <p>Set this vcard to throw exception on the event of an error,
	 * or silently add them to an error list if the flag is lowered.</p>
	 *
	 * @param throwExceptions
	 * 	- true to throw exceptions, false to silently add to a list
	 */
	public void setThrowExceptions(boolean throwExceptions);
	
	/**
	 * <p>Returns true if this vcard is set to throw exceptions on
	 * the event that an error occurs.</p>
	 *
	 * @return boolean
	 * 	- true if this vcard will throw exceptions
	 */
	public boolean isThrowExceptions();
	
	/**
	 * <p>Adds an error to this vcard.</p>
	 *
	 * @param error
	 * 	- the error to add
	 */
	public void addError(VCardError error);
	
	/**
	 * <p>Adds the parameters of the error to this vcard. A {@link VCardError} object
	 * gets constructed automatically from the parameters.</p>
	 *
	 * @param errorMessage
	 * 	- the error message
	 * @param severity
	 * 	- the severity of the error
	 * @param error
	 * 	- the exception that was caught
	 */
	public void addError(String errorMessage, ErrorSeverity severity, Throwable error);
	
	/**
	 * <p>Clears the error list.</p>
	 */
	public void clearErrors();
	
	/**
	 * <p>Returns true if the error list is not empty.</p>
	 *
	 * @return boolean
	 * 	- true if this vcard has errors
	 */
	public boolean hasErrors();
}
