package model.business.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import common.util.Format;
import model.business.RuleValidator;
import model.entity.Project;
import model.entity.Report;
import model.entity.ReportDetail;
import model.repo.ProjectRepo;

public class ProjectJson {

	/**
	 * Get Json Project
	 * 
	 * @return {@link List}
	 */
	public static List<Object> getJson() {
		List<Object> list = new ArrayList<>();
		for (Project p : ProjectRepo.model.getAll()) {
			HashMap<String, Object> project = new HashMap<>();
			project.put("id", p.getId());
			project.put("projectCode", p.getProjectCode());
			project.put("name", p.getName());
			project.put("startAt", Format.toDate(p.getStartAt()));
			project.put("finishAt", Format.toDate(p.getFinishAt()));
			list.add(project);
		}
		return list;
	}

	/**
	 * Get Json for Chart
	 * 
	 * @return List
	 */
	public static List<Object> getJsonForChart() {
		List<Object> list = new ArrayList<>();
		for (Project p : ProjectRepo.model.getAll()) {
			HashMap<String, Object> project = new HashMap<>();
			project.put("id", p.getId());
			project.put("name", p.getName());
			List<Object> reports = new ArrayList<>();
			for (Report r : p.getReports()) {
				for (ReportDetail rd : r.getReportDetails()) {
					HashMap<String, Object> reportDetail = new HashMap<>();
					reportDetail.put("taskId", rd.getTask().getId());
					reportDetail.put("taskName", rd.getTask().getName());
					reportDetail.put("timeWorked", rd.getTimeWorked());
					reports.add(reportDetail);
				}
			}
			project.put("reports", reports);
			list.add(project);
		}
		return list;
	}

	/**
	 * Get Json for validator
	 * 
	 * @return JSONArray
	 * @throws JSONException
	 */
	public static void getJsonRules() {
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
