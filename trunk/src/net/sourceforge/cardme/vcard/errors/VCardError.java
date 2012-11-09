package net.sourceforge.cardme.vcard.errors;

import java.io.Serializable;
import java.util.Arrays;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.util.Util;

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
 * @author George El-Haddad
 * <br/>
 * Aug 11, 2012
 *
 */
public class VCardError implements Comparable<VCardError>, Cloneable, Serializable {
	
	private static final long serialVersionUID = 8746546867439397068L;
	
	private String errorMessage = null;
	private ErrorSeverity severity = ErrorSeverity.NONE;
	private Throwable error = null;
	
	public VCardError() {
		
	}
	
	public VCardError(Throwable error, ErrorSeverity severity) {
		this(null, error, severity);
	}
	
	public VCardError(String errorMessage, Throwable error, ErrorSeverity severity) {
		setErrorMessage(errorMessage);
		setError(error);
		setSeverity(severity);
	}
	
	public String getErrorMessage()
	{
		if(errorMessage != null) {
			return errorMessage;
		}
		else if(error != null) {
			return error.getMessage();
		}
		else {
			return null;
		}
	}
	
	public ErrorSeverity getSeverity()
	{
		return severity;
	}
	
	public Throwable getError()
	{
		return error;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void setError(Throwable error) {
		this.error = error;
	}
	
	public void setSeverity(ErrorSeverity severity) {
		this.severity = severity;
	}
	
	@Override
	public VCardError clone()
	{
		VCardError cloned = new VCardError();
		cloned.setErrorMessage(errorMessage);
		cloned.setError(error);
		cloned.setSeverity(severity);
		return cloned;
	}
	
	@Override
	public int compareTo(VCardError obj)
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
	
	private String[] getContents()
	{
		String[] contents = new String[4];
		contents[0] = VCardError.class.getName();
		contents[1] = StringUtil.getString(errorMessage);
		contents[2] = (error != null ? error.getMessage() : "");
		contents[3] = (severity != null ? ""+severity.getLevel() : "");
		return contents;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null) {
			if(obj instanceof VCardError) {
				return this.compareTo((VCardError)obj) == 0;
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
		sb.append(VCardError.class.getName());
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
