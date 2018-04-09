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
public class Message {

	private final static String ISO = "vi";
	private final static Map<String, Language> LANG;
	private static HttpSession session;
	static {
		LANG = new HashMap<String, Language>();
		LANG.put("en", new EN());
		LANG.put("vi", new VI());
	}

	/**
	 * Set a session
	 * 
	 * @param session
	 */
	public static void setSession(HttpSession httpSession) {
		// set a session
		if (session == null)
			session = httpSession;
	}

	/**
	 * Get a locale represents a specific geographical
	 * 
	 * @return String
	 */
	public String getLocale() {
		// get locale from session
		String locale = null;
		if (session != null)
			locale = (String) session.getAttribute("locale");
		log.debug("session locale: " + locale);
		if (!LANG.containsKey(locale))
			return ISO;
		return locale;
	}

	/**
	 * Get language by keyword
	 * 
	 * @param keyword
	 * @return String
	 */
	public String getWord(String keyword) {
		Map<String, String> translation = LANG.get(getLocale()).get();
		if (translation.containsKey(keyword))
			return translation.get(keyword);
		log.debug("Not translated: " + keyword);
		return keyword;
	}

	/**
	 * Initialize Constructor, get language communication
	 * 
	 * @param key
	 * @return String
	 */
	public static String getText(String key) {
		Message message = new Message();
		return message.getWord(key);
	}

}
