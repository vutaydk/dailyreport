package model.business;

import java.util.HashMap;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class TaskModel {

	private Integer id;
	private String taskCode;
	private String name;

	@Setter
	@Builder.Default
	private HashMap<String, String> errorMap = new HashMap<>();

	public static boolean checkId(int id) {
		return true;
	}

	public boolean validate() {
		boolean bool = true;

		if (taskCode == null || taskCode.length() != 4) {
			errorMap.put("txt_projectCode", "Project Code length must be 5 characters.");
			bool = false;
		}

		if (name == null || name.length() < 6) {
			errorMap.put("txt_name", "Name length is too short (requires 6 characters).");
			bool = false;
		}

		return bool;
	}

	public boolean push() {
		return true;
	}

}
