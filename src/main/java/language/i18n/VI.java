package language.i18n;

import java.util.HashMap;
import java.util.Map;

public class VI {

	private final static Map<String, String> language = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("label.project", "Dự án");
			put("label.project.add", "Thêm dự án");
			put("label.task", "Công việc");
			put("label.rights", "Quyền");
		}
	};

	public static Map<String, String> get() {
		return language;
	}
}