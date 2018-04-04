package model.business;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import lombok.Getter;

@Getter
public class RuleValidator {

	private Map<String, Object> rules = new HashMap<>();
	private Map<String, Object> messages = new HashMap<>();

	public RuleValidator.RuleBuilder builder(String name) {
		return new RuleValidator.RuleBuilder(name);
	}

	public JSONArray getJson() {
		Map<String, Object> map = new HashMap<>();
		map.put("rules", rules);
		map.put("message", messages);
		return new JSONArray();
	}

	@Getter
	public static class RuleBuilder {
		private String name;
		private Map<String, Object> rules = new HashMap<>();
		private Map<String, Object> messages = new HashMap<>();

		public RuleBuilder(String name) {
			this.name = name;
		}

		public void build() {
			if (name != null) {
				rules.put(name, rules);
				messages.put(name, messages);
			}
		}

		public RuleBuilder minLength(int length, String message) {
			rules.put("minlength", length);
			messages.put("minlength", message);
			return this;
		}

		public RuleBuilder maxLength(int length, String message) {
			rules.put("maxlength", length);
			messages.put("maxlength", message);
			return this;
		}

		public RuleBuilder min(int length, String message) {
			rules.put("min", length);
			messages.put("min", message);
			return this;
		}

		public RuleBuilder max(int length, String message) {
			rules.put("max", length);
			messages.put("max", message);
			return this;
		}
	}

}
