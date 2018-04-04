package model.business.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.util.Format;
import model.entity.Report;
import model.entity.ReportDetail;
import model.repo.ReportRepo;

public class ReportJson {

	/**
	 * Get Json Report
	 * 
	 * @return List
	 */
	public static List<Object> getJson() {
		List<Object> list = new ArrayList<>();
		for (Report r : ReportRepo.model.getAll()) {
			HashMap<String, Object> report = new HashMap<>();
			report.put("id", r.getId());
			report.put("employeeCode", r.getUser().getEmployeeCode());
			report.put("employeeName", r.getUser().getName());
			List<Object> tasks = new ArrayList<>();
			for (ReportDetail td : r.getReportDetails()) {
				HashMap<String, Object> task = new HashMap<>();
				task.put("code", td.getTask().getTaskCode());
				task.put("name", td.getTask().getName());
				task.put("timeWorked", td.getTimeWorked());
				task.put("note", td.getNote());
				tasks.add(task);
			}
			report.put("tasks", tasks);
			report.put("date", Format.toDate(r.getCreatedAt()));
			list.add(report);
		}
		return list;
	}

}
