package language.i18n;

import java.util.HashMap;
import java.util.Map;

public class EN {

	private final static Map<String, String> language = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("label.project", "Project");
			put("label.project.add", "Add project");
			put("label.task", "Task");
			put("label.rights", "Rights");
		}
	};

	public static Map<String, String> get() {
		return language;
	}
}
