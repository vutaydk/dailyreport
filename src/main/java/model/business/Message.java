package model.business;

import java.util.HashMap;
import java.util.Map;

public class Message {

	private HashMap<String, Object> errorMap = new HashMap<>();
	private HashMap<String, Object> messageMap = new HashMap<>();

	public Map<String, Object> getMessage() {

		Map<String, Object> map = new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("messages", messageMap);
				put("errors", errorMap);
			}
		};

		return map;
	}

	/**
	 * Set the message requied validate for client
	 * 
	 * @param key
	 * @param value
	 */
	protected void setMessage(String key, Object value) {
		errorMap.put(key, value);
	}

	/**
	 * Set the message when done
	 * 
	 * @param value
	 */
	protected void setMessage(Object value) {
		messageMap.put("done", value);
	}
}
