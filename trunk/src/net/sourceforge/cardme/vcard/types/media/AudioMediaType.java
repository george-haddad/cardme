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
public class AudioMediaType {

	/*
	 * IANA Registered Sound Types.
	 * Some may not have registered an extension.
	 */
	
	public static final AudioMediaType GPP3 = new AudioMediaType("GPP3", "audio/3gpp", "");
	public static final AudioMediaType GPP2 = new AudioMediaType("GPP2", "audio/3gpp2", "");
	public static final AudioMediaType AC3 = new AudioMediaType("AC3", "audio/ac3", "ac3");
	public static final AudioMediaType AMR = new AudioMediaType("AMR", "audio/amr", "amr");
	public static final AudioMediaType AMR_WB = new AudioMediaType("AMR_WB", "audio/amr-wb", "amr-wb");
	public static final AudioMediaType AMR_WB_PLUS = new AudioMediaType("AMR_WB_PLUS", "audio/amr-wb+", "amr-wb+");
	public static final AudioMediaType ASC = new AudioMediaType("ASC", "audio/asc", "");
	public static final AudioMediaType ATRAC_ADVANCED_LOSSLESS = new AudioMediaType("ATRAC_ADVANCED_LOSSLESS", "audio/atrac-advanced-lossless", "");
	public static final AudioMediaType ATRAC_X = new AudioMediaType("ATRAC_X", "audio/atrac-x", "");
	public static final AudioMediaType ATRAC3 = new AudioMediaType("ATRAC3", "audio/atrac3", "");
	public static final AudioMediaType BASIC = new AudioMediaType("BASIC", "audio/basic", "");
	public static final AudioMediaType BV16 = new AudioMediaType("BV16", "audio/bv16", "");
	public static final AudioMediaType BV32 = new AudioMediaType("BV32", "audio/bv32", "");
	public static final AudioMediaType CLEARMODE = new AudioMediaType("CLEARMODE", "audio/clearmode", "");
	public static final AudioMediaType CN = new AudioMediaType("CN", "audio/cn", "");
	public static final AudioMediaType DAT12 = new AudioMediaType("DAT12", "audio/dat12", "");
	public static final AudioMediaType DLS = new AudioMediaType("DLS", "audio/dls", "");
	public static final AudioMediaType DSR_ES201108 = new AudioMediaType("DSR_ES201108", "audio/dsr-es201108", "");
	public static final AudioMediaType DSR_ES202050 = new AudioMediaType("DSR_ES202050", "audio/dsr-es202050", "");
	public static final AudioMediaType DSR_ES202211 = new AudioMediaType("DSR_ES202211", "audio/dsr-es202211", "");
	public static final AudioMediaType DSR_ES202212 = new AudioMediaType("DSR_ES202212", "audio/dsr-es202212", "");
	public static final AudioMediaType EAC3 = new AudioMediaType("EAC3", "audio/eac3", "eac3");
	public static final AudioMediaType DVI4 = new AudioMediaType("DVI4", "audio/dvi4", "");
	public static final AudioMediaType EVRC = new AudioMediaType("EVRC", "audio/evrc", "");
	public static final AudioMediaType EVRC0 = new AudioMediaType("EVRC0", "audio/evrc0", "");
	public static final AudioMediaType EVRC1 = new AudioMediaType("EVRC1", "audio/evrc1", "");
	public static final AudioMediaType EVRCB = new AudioMediaType("EVRCB", "audio/evrcb", "");
	public static final AudioMediaType EVRCB0 = new AudioMediaType("EVRCB0", "audio/evrcb0", "");
	public static final AudioMediaType EVRCB1 = new AudioMediaType("EVRCB1", "audio/evrcb1", "");
	public static final AudioMediaType EVRC_QCP = new AudioMediaType("EVRC_QCP", "audio/evrc-qcp", "");
	public static final AudioMediaType EVRCWB = new AudioMediaType("EVRCWB", "audio/evrcwb", "");
	public static final AudioMediaType EVRCWB0 = new AudioMediaType("EVRCWB0", "audio/evrcwb0", "");
	public static final AudioMediaType EVRCWB1 = new AudioMediaType("EVRCWB1", "audio/evrcwb1", "");
	public static final AudioMediaType G719 = new AudioMediaType("G719", "audio/g719", "");
	public static final AudioMediaType G722 = new AudioMediaType("G722", "audio/g722", "");
	public static final AudioMediaType G7221 = new AudioMediaType("G7221", "audio/g7221", "");
	public static final AudioMediaType G723 = new AudioMediaType("G723", "audio/g723", "");
	public static final AudioMediaType G726_16 = new AudioMediaType("G726_16", "audio/g726-16", "");
	public static final AudioMediaType G726_24 = new AudioMediaType("G726_24", "audio/g726-24", "");
	public static final AudioMediaType G726_32 = new AudioMediaType("G726_32", "audio/g726-32", "");
	public static final AudioMediaType G726_40 = new AudioMediaType("G726_40", "audio/g726-40", "");
	public static final AudioMediaType G728 = new AudioMediaType("G728", "audio/g728", "");
	public static final AudioMediaType G729 = new AudioMediaType("G729", "audio/g729", "");
	public static final AudioMediaType G7291 = new AudioMediaType("G7291", "audio/g7291", "");
	public static final AudioMediaType G729D = new AudioMediaType("G729D", "audio/g729d", "");
	public static final AudioMediaType G729E = new AudioMediaType("G729E", "audio/g729e", "");
	public static final AudioMediaType GSM = new AudioMediaType("GSM", "audio/gsm", "");
	public static final AudioMediaType GSM_EFR = new AudioMediaType("GSM_EFR", "audio/gsm-efr", "");
	public static final AudioMediaType ILBC = new AudioMediaType("ILBC", "audio/ilbc", "");
	public static final AudioMediaType L8 = new AudioMediaType("L8", "audio/l8", "");
	public static final AudioMediaType L16 = new AudioMediaType("L16", "audio/l16", "");
	public static final AudioMediaType L20 = new AudioMediaType("L20", "audio/l20", "");
	public static final AudioMediaType L24 = new AudioMediaType("L24", "audio/l24", "");
	public static final AudioMediaType LPC = new AudioMediaType("LPC", "audio/lpc", "");
	public static final AudioMediaType MOBILE_XMF = new AudioMediaType("MOBILE_XMF", "audio/mobile-xmf", "");
	public static final AudioMediaType MPA = new AudioMediaType("MPA", "audio/mpa", "mpa");
	public static final AudioMediaType MP4 = new AudioMediaType("MP4", "audio/mp4", "mp4");
	public static final AudioMediaType MP4A_LATM = new AudioMediaType("MP$_LATM", "audio/mp4-latm", "");
	public static final AudioMediaType MPA_ROBUST = new AudioMediaType("MPA_ROBUST", "audio/mpa-robust", "");
	public static final AudioMediaType MPEG = new AudioMediaType("MPEG", "audio/mpeg", "mpeg");
	public static final AudioMediaType MPEG4_GENERIC = new AudioMediaType("MPEG4_GENERIC", "audio/mpeg4-generic", "mpeg");
	public static final AudioMediaType OGG = new AudioMediaType("OGG", "audio/ogg", "ogg");
	public static final AudioMediaType PARITYFEC_1D_INT = new AudioMediaType("PARITYFEC_1D_INT", "audio/1d-interleaved-parityfec", "");
	public static final AudioMediaType PARITYFEC = new AudioMediaType("PARITYFEC", "audio/parityfec", "");
	public static final AudioMediaType PCMA = new AudioMediaType("PCMA", "audio/pcma", "");
	public static final AudioMediaType PCMA_WB = new AudioMediaType("PCMA_WB", "audio/pcma-wb", "");
	public static final AudioMediaType PCMU = new AudioMediaType("PCMU", "audio/pcmu", "");
	public static final AudioMediaType PCMU_WB = new AudioMediaType("PCMU_WB", "audio/pcmu-wb", "");
	public static final AudioMediaType PRS_SID = new AudioMediaType("PRS_SID", "audio/prs.sid", "sid");
	public static final AudioMediaType QCELP = new AudioMediaType("QCELP", "audio/qcelp", "");
	public static final AudioMediaType RED = new AudioMediaType("RED", "audio/red", "");
	public static final AudioMediaType RTP_MIDI = new AudioMediaType("RTP_MIDI", "audio/rtp-midi", "");
	public static final AudioMediaType RTX = new AudioMediaType("RTX", "audio/rtx", "");
	public static final AudioMediaType SMV = new AudioMediaType("SMV", "audio/smv", "");
	public static final AudioMediaType SMV0 = new AudioMediaType("SMV0", "audio/smv0", "");
	public static final AudioMediaType SMV_QCP = new AudioMediaType("SMV_QCP", "audio/smv-qcp", "");
	public static final AudioMediaType SPEEX = new AudioMediaType("SPEEX", "audio/speex", "");
	public static final AudioMediaType T140C = new AudioMediaType("T140C", "audio/t140c", "");
	public static final AudioMediaType T38 = new AudioMediaType("T38", "audio/t38", "");
	public static final AudioMediaType TELEPHONE_EVENT = new AudioMediaType("TELEPHONE_EVENT", "audio/telephone-event", "");
	public static final AudioMediaType TONE = new AudioMediaType("TONE", "audio/tone", "");
	public static final AudioMediaType UEMCLIP = new AudioMediaType("UEMCLIP", "audio/uemclip", "");
	public static final AudioMediaType ULPFEC = new AudioMediaType("ULPFEC", "audio/ulpfec", "");
	public static final AudioMediaType VDVI = new AudioMediaType("VDVI", "audio/vdvi", "");
	public static final AudioMediaType VMR_WB = new AudioMediaType("VMR_WB", "audio/vmr-wb", "");
	public static final AudioMediaType VORBIS = new AudioMediaType("VORBIS", "audio/vorbis", "");
	public static final AudioMediaType VORBIS_CONFIG = new AudioMediaType("VORBIS_CONFIG", "audio/vorbis-config", "");
	public static final AudioMediaType RTP_ENC_AESCM128 = new AudioMediaType("RTP_ENC_AESCM128", "audio/rtp-enc-aescm128", "");
	public static final AudioMediaType SP_MIDI = new AudioMediaType("SP_MIDI", "audio/sp-midi ", "mid");
	public static final AudioMediaType GPP3_IUFP = new AudioMediaType("GPP3_IUFP", "audio/vnd.3gpp.iufp", "");
	public static final AudioMediaType SB4 = new AudioMediaType("SB4", "audio/vnd.4sb", "");
	public static final AudioMediaType AUDIOKOZ = new AudioMediaType("AUDIOKOZ", "audio/vnd.audiokoz", "koz");
	public static final AudioMediaType CELP = new AudioMediaType("CELP", "audio/vnd.CELP", "");
	public static final AudioMediaType NSE = new AudioMediaType("NSE", "audio/vnd.cisco.com", "");
	public static final AudioMediaType CMLES_RADIO_EVENTS = new AudioMediaType("CMLES_RADIO_EVENTS", "audio/vnd.cmles.radio-events", "");
	public static final AudioMediaType CNS_ANP1 = new AudioMediaType("CNS_ANP1", "audio/vnd.cns.anp1", "");
	public static final AudioMediaType CND_INF1 = new AudioMediaType("CNS_INF1", "audio/vnd.cns.inf1", "");
	public static final AudioMediaType EOL = new AudioMediaType("EOL", "audio/vnd.digital-winds", "eol");
	public static final AudioMediaType DLNA_ADTS = new AudioMediaType("DLNA_ADTS", "audio/vnd.dlna.adts", "");
	public static final AudioMediaType HEAAC1 = new AudioMediaType("HEAAC1", "audio/vnd.dolby.heaac.1", "");
	public static final AudioMediaType HEAAC2 = new AudioMediaType("HEAAC2", "audio/vnd.dolby.heaac.2", "");
	public static final AudioMediaType MPL = new AudioMediaType("MPL", "audio/vnd.dolby.mlp", "mpl");
	public static final AudioMediaType MPS = new AudioMediaType("MPS", "audio/vnd.dolby.mps", "");
	public static final AudioMediaType PL2 = new AudioMediaType("PL2", "audio/vnd.dolby.pl2", "");
	public static final AudioMediaType PL2X = new AudioMediaType("PL2X", "audio/vnd.dolby.pl2x", "");
	public static final AudioMediaType PL2Z = new AudioMediaType("PL2Z", "audio/vnd.dolby.pl2z", "");
	public static final AudioMediaType PULSE_1 = new AudioMediaType("PULSE_1", "audio/vnd.dolby.pulse.1", "");
	public static final AudioMediaType DRA = new AudioMediaType("DRA", "audio/vnd.dra", "");
	public static final AudioMediaType DTS = new AudioMediaType("DTS", "audio/vnd.dts", "WAV");				//wav, cpt, dts
	public static final AudioMediaType DTSHD = new AudioMediaType("DTSHD", "audio/vnd.dts.hd", "dtshd");
	public static final AudioMediaType PLJ = new AudioMediaType("PLJ", "audio/vnd.everad.plj", "plj");
	public static final AudioMediaType AUDIO = new AudioMediaType("AUDIO", "audio/vnd.hns.audio", "rm");
	public static final AudioMediaType VOICE = new AudioMediaType("LVP", "audio/vnd.lucent.voice", "lvp");
	public static final AudioMediaType PYA = new AudioMediaType("PYA", "audio/vnd.ms-playready.media.pya", "pya");
	public static final AudioMediaType MXMF = new AudioMediaType("MXMF", "audio/vnd.nokia.mobile-xmf", "mxmf");
	public static final AudioMediaType VBK = new AudioMediaType("VBK", "audio/vnd.nortel.vbk", "vbk");
	public static final AudioMediaType ECELP4800 = new AudioMediaType("ECELP4800", "audio/vnd.nuera.ecelp4800", "ecelp4800");
	public static final AudioMediaType ECELP7470 = new AudioMediaType("ECELP7470", "audio/vnd.nuera.ecelp7470", "ecelp7470");
	public static final AudioMediaType ECELP9600 = new AudioMediaType("ECELP9600", "audio/vnd.nuera.ecelp9600", "ecelp9600");
	public static final AudioMediaType SBC = new AudioMediaType("SBC", "audio/vnd.octel.sbc", "");
	public static final AudioMediaType KADPCM32 = new AudioMediaType("KADPCM32", "audio/vnd.rhetorex.32kadpcm", "");
	public static final AudioMediaType SMP3 = new AudioMediaType("SMP3", "audio/vnd.sealedmedia.softseal.mpeg", "smp3");	//smp3, smp, s1m
	public static final AudioMediaType CVSD = new AudioMediaType("CVSD", "audio/vnd.vmx.cvsd", "");
	
	private final String typeName;
	private final String ianaRegisteredName;
	private final String extension;
	
	/**
	 * <p>Use of this constructor is discouraged. Please use one of the predefined
	 * static objects.</p>
	 * 
	 * @param _typeName the type name (e.g. "MPEG")
	 * @param _ianaRegisteredName the IANA registered name (e.g. "audio/mpeg")
	 * @param _extension the file extension used for this type (e.g. "mpeg")
	 */
	public AudioMediaType(String _typeName, String _ianaRegisteredName, String _extension) {
		typeName = _typeName;
		ianaRegisteredName = _ianaRegisteredName;
		extension = _extension;
	}
	
	/**
	 * <p>Retrieves the IANA audio media type name.</p>
	 * 
	 * @return the IANA audio media type name
	 */
	public String getTypeName()
	{
		return typeName;
	}
	
	/**
	 * <p>Retrieves the IANA audio media registered name.</p>
	 * 
	 * @return the IANA audio media registered name
	 */
	public String getIanaRegisteredName()
	{
		return ianaRegisteredName;
	}
	
	/**
	 * <p>Retrieves the IANA audio media extension.</p>
	 * 
	 * @return the IANA audio media extension
	 */
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
	 * <p>Retrieves one of the static objects in this class by name.</p>
	 * 
	 * @param typeName the type name (e.g. "MPEG")
	 * @return the object associated with the given type name or null if none was found
	 */
	public static AudioMediaType valueOf(String typeName)
	{
		typeName = typeName.replaceAll("-", "_").toUpperCase();
		try {
			Field f = AudioMediaType.class.getField(typeName);
			Object obj = f.get(null);
			if (obj instanceof AudioMediaType) {
				return (AudioMediaType)obj;
			}
		}
		catch (Exception ex) {
			//static field not found
		}
		
		return null;
	}
}
