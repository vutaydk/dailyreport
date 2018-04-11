package language;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import language.i18n.EN;
import language.i18n.Language;
import language.i18n.VI;
import lombok.extern.log4j.Log4j;

/**
 * Multi language
 * 
 * @author "Mr.Hanh"
 *
 */
@Log4j
public class Translator {

	private final static String ISO = "vi";
	private final static Map<String, Language> LANG;
	private static HttpSession session;
	static {
		LANG = new HashMap<String, Language>() {
			private static final long serialVersionUID = 1L;
			{
				put("en", new EN());
				put("vi", new VI());
			}
		};
	}

	/**
	 * Get the session and initialize the compiler for the session
	 * 
	 * @param session
	 */
	public static void setSession(HttpSession httpSession) {
		if (session == null) // set a session
			session = httpSession;
		Translator message = new Translator();
		httpSession.setAttribute("lang", message.getTranslator());
	}

	/**
	 * Get a value attribute from session
	 * 
	 * @param name
	 * @return Object
	 */
	public Object getAttribute(String name) {
		if (session != null) // get a value attribute
			return session.getAttribute(name);
		return null;
	}

	/**
	 * Get common language translation
	 * 
	 * @return Language
	 */
	public Language getTranslator() {
		String locale = (String) getAttribute("locale"); // get locale from session
		log.debug("session locale: " + locale);
		return LANG.containsKey(locale) ? LANG.get(locale) : LANG.get(ISO);
	}

	/**
	 * Get a translated word with keyword
	 * 
	 * @param keyword
	 * @return String
	 */
	public String getWord(String keyword) {
		Language translate = getTranslator();
		return translate.get(keyword);
	}

}
