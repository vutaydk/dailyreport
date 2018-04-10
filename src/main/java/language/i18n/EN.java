package language.i18n;

import java.util.HashMap;
import java.util.Map;

public class EN extends Language {

	private Map<String, String> translate = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("label.project", "Project");
			put("label.project.add", "Add project");
			put("label.task", "Task");
			put("label.rights", "Rights");
			put("label.login", "Login");
			put("label.username", "Account");
			put("label.password", "Password");
			put("label.brandName", "DailyReport");
			put("label.project.manament", "Project manament");
			put("label.logout", "Logout");
			put("label.projectCode", "Project Code");
			put("label.taskCode", "Task Code");
			put("label.timeWork", "Time work");
			put("label.note", "Note");
			put("label.submit", "Submit");
			put("label.reset", "Reset");
			put("label.name", "Name");
			put("label.startAt", "Start at");
			put("label.finishAt", "Finish at");
			put("label.manager", "Employee manager");
			put("label.level", "Level");
		}
	};

	@Override
	public Map<String, String> get() {
		return translate;
	}
}
