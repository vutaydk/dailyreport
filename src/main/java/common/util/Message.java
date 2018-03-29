package common.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Message {

	private final static Map<String, Locale> locale;
	private ResourceBundle translation;
	private String langIso = "en";
	static {
		locale = new HashMap<>();
		locale.put("en", Locale.ENGLISH);
		locale.put("vi", new Locale("vi", "VN"));
	}

	public Message(String langIso) {
		if (!locale.containsKey(langIso))
			langIso = this.langIso;

		translation = ResourceBundle.getBundle("language", locale.get(langIso));
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
		return translation.getString(keyword);
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
