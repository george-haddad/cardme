package net.sourceforge.cardme.vcard;

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
 * Dec 20, 2011
 *
 */
public enum LanguageType {

	AF("af", "Afrikaans"),
	SQ("sq", "Albanian"),
	AR("ar", "Arabic"),
	AR_SA("ar-sa", "Arabic (Saudi Arabia)"),
	AR_IQ("ar-iq", "Arabic (Iraq)"),
	AR_EG("ar-eg", "Arabic (Egypt)"),
	AR_LY("ar-ly", "Arabic (Libya)"),
	AR_DZ("ar-dz", "Arabic (Algeria)"),
	AR_MA("ar-ma", "Arabic (Morocco)"),
	AR_TN("ar-tn", "Arabic (Tunisia)"),
	AR_OM("ar-om", "Arabic (Oman)"),
	AR_YE("ar-ye", "Arabic (Yemen)"),
	AR_SY("ar-sy", "Arabic (Syria)"),
	AR_JO("ar-jo", "Arabic (Jordan)"),
	AR_LB("ar-lb", "Arabic (Lebanon)"),
	AR_KW("ar-kw", "Arabic (Kuwait)"),
	AR_AE("ar-ae", "Arabic (U.A.E.)"),
	AR_BH("ar-bh", "Arabic (Bahrain)"),
	AR_QA("ar-qa", "Arabic (Qatar)"),
	EU("eu", "Basque"),
	BG("bg", "Bulgarian"),
	BE("be", "Belarusian"),
	CA("ca", "Catalan"),
	ZH("zh", "Chinese"),
	ZH_TW("zh-tw", "Chinese (Taiwan)"),
	ZH_CN("zh-cn", "Chinese (China)"),
	ZH_HK("zh-hk", "Chinese (Hong Kong SAR)"),
	ZH_SG("zh-sg", "Chinese (Singapore)"),
	HR("hr", "Croatian"),
	CS("cs", "Czech"),
	DA("da", "Danish"),
	NL("nl", "Dutch (Netherlands)"),
	NL_BE("nl-be", "Dutch (Belgium)"),
	EN("en", "English"),
	EN_US("en-us", "English (United States)"),
	EN_GB("en-gb", "English (United Kingdom)"),
	EN_AU("en-au", "English (Australia)"),
	EN_CA("en-ca", "English (Canada)"),
	EN_NZ("en-nz", "English (New Zealand)"),
	EN_IE("en-ie", "English (Ireland)"),
	EN_ZA("en-za", "English (South Africa)"),
	EN_JM("en-jm", "English (Jamaica)"),
	EN_BZ("en-bz", "English (Belize)"),
	EN_TT("en-tt", "English (Trinidad)"),
	ET("et", "Estonian"),
	FO("fo", "Faeroese"),
	FA("fa", "Farsi"),
	FI("fi", "Finnish"),
	FR("fr", "French (France)"),
	FR_BE("fr-be", "French (Belgium)"),
	FR_CA("fr-ca", "French (Canada)"),
	FR_CH("fr-ch", "French (Switzerland)"),
	FR_LU("fr-lu", "French (Luxembourg)"),
	GD("gd", "Gaelic"),
	DE("de", "German (Germany)"),
	DE_CH("de-ch", "German (Switzerland)"),
	DE_AT("de-at", "German (Austria)"),
	DE_LU("de-lu", "German (Luxembourg)"),
	DE_LI("de-li", "German (Liechtenstein)"),
	EL("el", "Greek"),
	HE("he", "Hebrew"),
	HI("hi", "Hindi"),
	HU("hu", "Hungarian"),
	IS("is", "Icelandic"),
	IN("in", "Indonesian"),
	IT("it", "Italian (Italy)"),
	IT_CH("it-ch", "Italian (Switzerland)"),
	JA("ja", "Japanese"),
	KO("ko", "Korean"),
	LV("lv", "Latvian"),
	LT("lt", "Lithuanian"),
	MK("mk", "FYRO Macedonian"),
	MS("ms", "Malay (Malaysia)"),
	MT("mt", "Maltese"),
	NO("no", "Norwegian"),
	PL("pl", "Polish"),
	PT_BR("pt-br", "Portuguese (Brazil)"),
	PT("pt", "Portuguese (Portugal)"),
	RM("rm", "Rhaeto-Romanic"),
	RO("ro", "Romanian"),
	RO_MO("ro-mo", "Romanian (Moldova)"),
	RU("ru", "Russian"),
	RU_MO("ru-mo", "Russian (Moldova)"),
	SR("sr", "Serbian (Cyrillic)"),
	SK("sk", "Slovak"),
	SL("sl", "Slovenian"),
	SB("sb", "Sorbian"),
	ES("es", "Spanish (Traditional Sort)"),
	ES_MX("es-mx", "Spanish (Mexico)"),
	ES_GT("es-gt", "Spanish (Guatemala)"),
	ES_CR("es-cr", "Spanish (Costa Rica)"),
	ES_PA("es-pa", "Spanish (Panama)"),
	ES_DO("es-do", "Spanish (Dominican Republic)"),
	ES_VE("es-ve", "Spanish (Venezuela)"),
	ES_CO("es-co", "Spanish (Colombia)"),
	ES_PE("es-pe", "Spanish (Peru)"),
	ES_AR("es-ar", "Spanish (Argentina)"),
	ES_EC("es-ec", "Spanish (Ecuador)"),
	ES_CL("es-cl", "Spanish (Chile)"),
	ES_UY("es-uy", "Spanish (Uruguay)"),
	ES_PY("es-py", "Spanish (Paraguay)"),
	ES_BO("es-bo", "Spanish (Bolivia)"),
	ES_SV("es-sv", "Spanish (El Salvador)"),
	ES_HN("es-hn", "Spanish (Honduras)"),
	ES_NI("es-ni", "Spanish (Nicaragua)"),
	ES_PR("es-pr", "Spanish (Puerto Rico)"),
	SX("sx", "Sutu"),
	SV("sv", "Swedish"),
	SV_FI("sv-fi", "Swedish (Finland)"),
	TH("th", "Thai"),
	TS("ts", "Tsonga"),
	TN("tn", "Tswana"),
	TR("tr", "Turkish"),
	UK("uk", "Ukrainian"),
	UR("ur", "Urdu"),
	VI("vi", "Vietnamese"),
	XH("xh", "Xhosa"),
	JI("ji", "Yiddish"),
	ZU("zu", "Zulu");
	
	private String _lang;
	private String _langDesc;
	LanguageType(String lang, String langDesc) {
		_lang = lang;
		_langDesc = langDesc;
	}
	
	public String getLanguageDesc()
	{
		return _langDesc;
	}
	
	public String getLanguageCode()
	{
		return _lang;
	}
	
	@Override
	public String toString() {

		return _lang;
	}
}
