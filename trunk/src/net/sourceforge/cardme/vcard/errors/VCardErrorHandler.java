package net.sourceforge.cardme.vcard.errors;

import java.util.List;

/*
 * Copyright 2012 George El-Haddad. All rights reserved.
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
 * <p>Interface to define functions for the handling of errors
 * during the VCard parsing and building stages.</p>
 */
public interface VCardErrorHandler {
	
	/**
	 * <p>Retrieve a list of errors that occurred, empty list
	 * is returned if there are none.</p>
	 * 
	 * @return list of errors or empty list if there are none
	 */
	public List<VCardError> getErrors();
	
	/**
	 * <p>Add an error.</p>
	 * 
	 * @param error - the error to add
	 * @throws NullPointerException if a null error object is added
	 */
	public void addError(VCardError error) throws NullPointerException;
	
	/**
	 * <p>Add an error by specifying the errorMessage, severity and
	 * {@link Throwable} object.</p>
	 * 
	 * @param errorMessage - the textual error message
	 * @param severity - the severity level
	 * @param error - the {@link Throwable} object representing the error
	 * @throws NullPointerException if adding a null errorMessage
	 */
	public void addError(String errorMessage, ErrorSeverity severity, Throwable error) throws NullPointerException;
	
	/**
	 * <p>Retrieve the severity level of errors on the VCard,
	 * by default this is {@link ErrorSeverity}.NONE</p>
	 * 
	 * @return the severity level of errors on the VCard
	 */
	public ErrorSeverity getErrorSeverity();
	
	/**
	 * <p>Sets whether exceptions should be thrown and propagated
	 * upwards or caught and logged internally. Internally caught
	 * errors can be accessed using this interface. The default
	 * is to catch and log internally.</p>
	 * 
	 * @param throwExceptions - true to throw and propagate thrown exceptions
	 */
	public void setThrowExceptions(boolean throwExceptions);
	
	/**
	 * <p>Indicates if exceptions will be thrown and propagated upwards
	 * or caught and logged internally.</p>
	 * 
	 * @return true if exceptions will be thrown and propagated
	 */
	public boolean isThrowExceptions();
	
	/**
	 * <p>Indicates if there is at least one error that
	 * was caught and logged internally.</p>
	 * 
	 * @return true if at least one or more errors got caught and logged internally
	 */
	public boolean hasErrors();
	
	/**
	 * <p>Removes all caught and logged errors.</p>
	 */
	public void clearErrors();
}
