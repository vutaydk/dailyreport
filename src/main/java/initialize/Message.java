package initialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import initialize.i18n.VI;

public class Message {

	private final String ISO = "vi";
	private final static List<String> locale;
	private Map<String, String> translation;
	static {
		locale = new ArrayList<>();
		locale.add("en");
		locale.add("vi");
	}

	public Message(String langIso) {
		if (!locale.contains(langIso))
			langIso = ISO;

		switch (langIso) {
		case "vi":
			translation = VI.get();
			break;
		default:
			translation = new HashMap<>();
			break;
		}
	}

	/**
	 * Set A Locale object represents a specific geographical
	 * 
	 * @param iso
	 */
	public void setLocale(String iso) {
		// set locale to session
	}

	/**
	 * Get Work by keyword
	 * 
	 * @param keyword
	 * @return String
	 */
	public String getWord(String keyword) {
		if (translation.containsKey(keyword)) {
			return translation.get(keyword);
		}
		return keyword;
	}

	/**
	 * Initialize Constructor, get language communication
	 * 
	 * @param key
	 * @return String
	 */
	public static String getText(String key) {
		// get locale from session
		Message message = new Message("vi");
		return message.getWord(key);
	}

}
