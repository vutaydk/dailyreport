package model.business.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.util.Format;
import model.business.RuleValidator;
import model.entity.Project;
import model.entity.Report;
import model.entity.ReportDetail;
import model.repo.ProjectRepo;

public class ProjectJson {

	/**
	 * Convert entity {@link Project} to JSON file
	 * 
	 * @return List
	 */
	public static List<Object> getJSON() {

		List<Object> list = new ArrayList<>();

		for (Project p : ProjectRepo.model.getList()) {

			HashMap<String, Object> project = new HashMap<String, Object>() {
				private static final long serialVersionUID = 1L;
				{
					put("id", p.getId());
					put("projectCode", p.getProjectCode());
					put("name", p.getName());
					put("startAt", Format.toDate(p.getStartAt()));
					put("finishAt", Format.toDate(p.getFinishAt()));
				}
			};

			list.add(project);

		}

		return list;
	}

	/**
	 * Convert entity {@link Project} to JSON file for Chart page
	 * 
	 * @return List
	 */
	public static List<Object> getJSONForChart() {

		List<Object> list = new ArrayList<>();

		for (Project p : ProjectRepo.model.getList()) {

			HashMap<String, Object> project = new HashMap<String, Object>() {
				private static final long serialVersionUID = 1L;
				{

					List<Object> reports = new ArrayList<>();

					for (Report r : p.getReports()) {
						for (ReportDetail rd : r.getReportDetails()) {

							HashMap<String, Object> task = new HashMap<>();

							task.put("taskId", rd.getTask().getId());
							task.put("taskName", rd.getTask().getName());
							task.put("timeWorked", rd.getTimeWorked());
							reports.add(task);
						}
					}

					put("id", p.getId());
					put("name", p.getName());
					put("reports", reports);

				}
			};

			list.add(project);

		}

		return list;
	}

	/**
	 * Get JSON for validator
	 * 
	 * @return JSONArray
	 */
	public static void getJSONRules() {
		RuleValidator validator = new RuleValidator();
		validator.builder("projectCode").min(9, "Khong duoc vuot qua 9 ki tu").max(1, "Khong duoc nho hon 1 ki tu")
				.build();
		validator.builder("name").max(9, "Khong duoc vuot qua 9 ki tu").min(1, "Khong duoc nho hon 1 ki tu")
				.min(1, "Khong duoc nho hon 1 ki tu").build();
		validator.builder("startAt").max(9, "Khong duoc vuot qua 9 ki tu").min(1, "Khong duoc nho hon 1 ki tu").build();
		validator.builder("finishAt").max(9, "Khong duoc vuot qua 9 ki tu").min(1, "Khong duoc nho hon 1 ki tu")
				.build();
	}

}
