package net.sourceforge.cardme.vcard.features;

import java.net.URL;
import java.util.List;
import net.sourceforge.cardme.vcard.types.params.UrlParamType;

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
 * Aug 9, 2012
 * 
 * <p><b>RFC 2426</b><br/>
 * <b>3.6.8 URL Type Definition</b>
 * <ul>
 * 	<li><b>Type name:</b> URL</li>
 * 	<li><b>Type purpose:</b> To specify a uniform resource locator associated with the object that the vCard refers to.</li>
 * 	<li><b>Type encoding:</b> 8bit</li>
 * 	<li><b>Type value:</b> A single uri value.</li>
 * 	<li><b>Type special note:</b> None</li>
 * </ul>
 * </p>
 */
public interface UrlFeature {

	public URL getUrl();
	
	public String getRawUrl();
	
	public void setUrl(URL url);
	
	public void setRawUrl(String rawUrl);
	
	public void clearUrl();
	
	public boolean hasUrl();
	
	public boolean hasRawUrl();
	
	public List<UrlParamType> getParams();
	public int getParamSize();
	public UrlFeature addParam(UrlParamType urlParamType) throws NullPointerException;
	public UrlFeature addAllParams(List<UrlParamType> urlParamTypes) throws NullPointerException;
	public UrlFeature removeParam(UrlParamType urlParamType) throws NullPointerException;
	public boolean containsParam(UrlParamType urlParamType);
	public boolean containsAllParams(List<UrlParamType> urlParamTypes);
	public boolean hasParams();
	public void clearParams();
}
