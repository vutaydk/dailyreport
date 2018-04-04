package model.business;

import java.util.HashMap;
import java.util.Map;

public class Message {

	private HashMap<String, String> errorMap = new HashMap<>();
	private HashMap<String, String> messageMap = new HashMap<>();

	public Map<String, Object> getMessage() {
		Map<String, Object> map = new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("messages", errorMap);
				put("errors", messageMap);
			}
		};
		return map;
	}

	/**
	 * Set the error for user
	 * 
	 * @param key
	 * @param value
	 */
	protected void setError(String key, String value) {
		errorMap.put(key, value);
	}

	/**
	 * Set the message for user
	 * 
	 * @param key
	 * @param value
	 */
	protected void setMessage(String key, String value) {
		messageMap.put(key, value);
	}
}
