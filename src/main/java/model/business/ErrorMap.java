package model.business;

import java.util.HashMap;

import lombok.Getter;

public class ErrorMap {

	/**
	 * Variable error value
	 */
	@Getter
	private HashMap<String, String> errorMap = new HashMap<>();

	/**
	 * Get value by key
	 * 
	 * @param key
	 * @return Object
	 */
	public Object getError(String key) {
		return errorMap.get(key);
	}

	/**
	 * Set value by key
	 * 
	 * @param key
	 * @param value
	 */
	public void setError(String key, String value) {
		errorMap.put(key, value);
	}

}
