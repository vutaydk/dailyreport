package model.business.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import model.business.ErrorMap;
import model.entity.Report;
import model.entity.TaskDetail;
import model.repo.ReportRepo;

public class ReportLogic extends ErrorMap {

	/**
	 * Variable ReportEntity
	 */
	private final ReportEntity entity;

	@Getter
	private Optional<Report> report = Optional.empty();

	/**
	 * Contructer
	 * 
	 * @param entity
	 */
	public ReportLogic(ReportEntity entity) {
		this.entity = entity;
	}

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
			for (TaskDetail td : r.getTaskDetails()) {
				HashMap<String, Object> task = new HashMap<>();
				task.put("code", td.getTask().getTaskCode());
				task.put("name", td.getTask().getName());
				task.put("timeWorked", td.getTimeWorked());
				task.put("note", td.getNote());
				tasks.add(task);
			}
			report.put("tasks", tasks);
			report.put("date", r.getCreatedAt());
			list.add(report);
		}
		return list;
	}

	/**
	 * Check exist Report
	 * 
	 * @return boolean
	 */
	public boolean isValidId() {
		report = ReportRepo.model.find(entity.getId());
		return report.isPresent();
	}

	/**
	 * Data Validation
	 * 
	 * @return boolean
	 */
	public boolean isValidData() {
		boolean bool = true;

		return bool;
	}

	/**
	 * Add to database
	 * 
	 * @return boolean
	 */
	public boolean add() {
		Report report = new Report();
		setData(entity, report);
		return ReportRepo.model.insert(report);
	}

	/**
	 * Update to database
	 * 
	 * @return boolean
	 */
	public boolean update() {
		if (!report.isPresent())
			return false;

		Report report = this.report.get();
		setData(entity, report);
		return ReportRepo.model.update(report);
	}

	/**
	 * Merge data
	 * 
	 * @param entity
	 * @param report
	 */
	private void setData(ReportEntity entity, Report report) {
	}

}
