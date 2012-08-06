package net.sourceforge.cardme.vcard.types.media;

import java.lang.reflect.Field;

/*
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
 * @author George El-Haddad
 * <br/>
 * Mar 10, 2010
 * <p>
 * @author Michael Angstadt
 * <br/>
 * Jul 06, 2012
 *
 */
public class ImageMediaType {

	/*
	 * IANA Registered Media Types.
	 * Some may not have registered an extension.
	 */
	
	public static final ImageMediaType CGM = new ImageMediaType("CGM", "image/cgm", "cgm");
	public static final ImageMediaType JP2 = new ImageMediaType("JP2", "image/jp2", "jp2");
	public static final ImageMediaType JPM = new ImageMediaType("JPM", "image/jpm", "jpm");
	public static final ImageMediaType JPX = new ImageMediaType("JPX", "image/jpx", "jpf");
	public static final ImageMediaType NAPLPS = new ImageMediaType("NAPLPS", "image/naplps", "");
	public static final ImageMediaType PNG = new ImageMediaType("PNG", "image/png", "png");
	public static final ImageMediaType BTIF = new ImageMediaType("BTIF", ".image/prs.btif", "btif");
	public static final ImageMediaType PTI = new ImageMediaType("PTI", "image/prs.pti", "pti");
	public static final ImageMediaType DJVU = new ImageMediaType("DJVU", "image/vnd.djvu", "djvu");
	public static final ImageMediaType SVF = new ImageMediaType("SVF", "image/vnd.svf", "svf");
	public static final ImageMediaType WBMP = new ImageMediaType("WBMP", "image/vnd.wap.wbmp", "wbmp");
	public static final ImageMediaType PSD = new ImageMediaType("PSD", "image/vnd.adobe.photoshop", "psd");
	public static final ImageMediaType INF2 = new ImageMediaType("INF2", "image/vnd.cns.inf2", "");
	public static final ImageMediaType DWG = new ImageMediaType("DWG", "image/vnd.dwg", "dwg");
	public static final ImageMediaType DXF = new ImageMediaType("DXF", "image/vnd.dxf", "dxf");
	public static final ImageMediaType FBS = new ImageMediaType("FBS", "image/vnd.fastbidsheet", "fbs");
	public static final ImageMediaType FPX = new ImageMediaType("FPX", "image/vnd.fpx", "fpx");
	public static final ImageMediaType FST = new ImageMediaType("FST", "image/vnd.fst", "fst");
	public static final ImageMediaType MMR = new ImageMediaType("MMR", "image/vnd.fujixerox.edmics-mmr", "mmr");
	public static final ImageMediaType RLC = new ImageMediaType("RLC", "image/vnd.fujixerox.edmics-rlc", "rlc");
	public static final ImageMediaType PGB = new ImageMediaType("PGB", "image/vnd.globalgraphics.pgb", "pgb");
	public static final ImageMediaType ICO = new ImageMediaType("ICO", "image/vnd.microsoft.icon", "ico");
	public static final ImageMediaType MIX = new ImageMediaType("MIX", "image/vnd.mix", "");
	public static final ImageMediaType MDI = new ImageMediaType("MDI", "image/vnd.ms-modi", "mdi");
	public static final ImageMediaType PIC = new ImageMediaType("PIC", "image/vnd.radiance", "pic");
	public static final ImageMediaType SPNG = new ImageMediaType("SPNG", "image/vnd.sealed.png", "spng");
	public static final ImageMediaType SGIF = new ImageMediaType("SGIF", "image/vnd.sealedmedia.softseal.gif", "sgif");
	public static final ImageMediaType SJPG = new ImageMediaType("SJPG", "image/vnd.sealedmedia.softseal.jpg", "sjpg");
	public static final ImageMediaType XIF = new ImageMediaType("XIF", "image/vnd.xiff", "xif");
	public static final ImageMediaType JPEG = new ImageMediaType("JPEG", "image/jpeg", "jpg");
	
	private final String typeName;
	private final String ianaRegisteredName;
	private final String extension;

	/**
	 * Use of this constructor is discouraged. Please use one of the predefined
	 * static objects.
	 * @param _typeName the type name (e.g. "JPEG")
	 * @param _ianaRegisteredName the IANA registered name (e.g. "image/jpeg")
	 * @param _extension the file extension used for this type (e.g. "jpg")
	 */
	public ImageMediaType(String _typeName, String _ianaRegisteredName, String _extension) {
		typeName = _typeName;
		ianaRegisteredName = _ianaRegisteredName;
		extension = _extension;
	}
	
	public String getTypeName()
	{
		return typeName;
	}
	
	public String getIanaRegisteredName()
	{
		return ianaRegisteredName;
	}
	
	public String getExtension()
	{
		return extension;
	}
	
	@Override
	public String toString()
	{

		return typeName;
	}
	
	/**
	 * Retrieves one of the static objects in this class by name.
	 * @param typeName the type name (e.g. "PNG")
	 * @return the object associated with the given type name or null if none was found
	 */
	public static ImageMediaType valueOf(String typeName)
	{
		typeName = typeName.replaceAll("-", "_").toUpperCase();
		try {
			Field f = ImageMediaType.class.getField(typeName);
			Object obj = f.get(null);
			if (obj instanceof ImageMediaType) {
				return (ImageMediaType)obj;
			}
		}
		catch (Exception ex) {
			//static field not found
		}
		
		return null;
	}
}
