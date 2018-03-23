package model.business;

import java.util.HashMap;

import common.util.DataValidation;
import common.util.Format;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ProjectModel {

	private Integer id;
	private String projectCode;
	private String name;
	private String startAt;
	private String finishAt;

	@Setter
	@Builder.Default
	private HashMap<String, String> errorMap = new HashMap<>();

	public static boolean checkId(int id) {
		return true;
	}

	public boolean validate() {
		boolean bool = true;

		if (projectCode == null || projectCode.length() != 4) {
			errorMap.put("txt_projectCode", "Project Code length must be 5 characters.");
			bool = false;
		}

		if (name == null || name.length() < 6) {
			errorMap.put("txt_name", "Name length is too short (requires 6 characters).");
			bool = false;
		}

		if (startAt == null || !DataValidation.isValidDate(startAt)) {
			errorMap.put("txt_startAt", "Invalid Start Date");
			bool = false;
		}

		if (finishAt == null || !DataValidation.isValidDate(finishAt)) {
			errorMap.put("txt_finishAt", "Invalid Finish Date");
			bool = false;
		} else if (Format.toDate(startAt).after(Format.toDate(finishAt))) {
			errorMap.put("txt_startAt", "Start Date can't after Finish Date");
			bool = false;
		}

		return bool;
	}

	public boolean push() {
		return true;
	}

}
