package language;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import language.i18n.EN;
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
	private final static List<String> LOCALE;
	private static HttpSession session;
	private Map<String, String> translation;
	static {
		LOCALE = Arrays.asList("en", "vi");
	}

	public Map<String, String> getTranslation() {
		String locale = getLocale();
		log.debug("locale=" + locale);
		switch (locale) {
		case "vi":
			translation = VI.get();
			break;
		case "en":
			translation = EN.get();
			break;
		default:
			translation = new HashMap<>();
			break;
		}
		return translation;
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
		log.debug("locale from session: " + locale);
		if (!LOCALE.contains(locale))
			locale = ISO;
		return locale;
	}

	/**
	 * Get language by keyword
	 * 
	 * @param keyword
	 * @return String
	 */
	public String getWord(String keyword) {
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
		message.getTranslation();
		return message.getWord(key);
	}

}
