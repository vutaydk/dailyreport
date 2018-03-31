package model.business.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import common.util.DataValidation;
import common.util.Format;
import model.business.ErrorMap;
import model.entity.Project;
import model.entity.Report;
import model.entity.ReportDetail;
import model.repo.ProjectRepo;

public class ProjectLogic extends ErrorMap {

	/**
	 * Variable ProjectDTO
	 */
	private final ProjectDTO dto;

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public ProjectLogic(ProjectDTO entity) {
		this.dto = entity;
	}

	/**
	 * Get Json Project
	 * 
	 * @return List
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
	 * Check exist Project
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isValidId(int id) {
		Optional<Project> project = ProjectRepo.model.find(id);
		boolean isValid = project.isPresent();
		if (isValid)
			dto.setProject(project);
		return isValid;
	}

	/**
	 * Data Validation
	 * 
	 * @return boolean
	 */
	public boolean isValidData() {
		boolean bool = true;

		if (dto.getProjectCode() == null || dto.getProjectCode().length() != 4) {
			setError("projectCode", "Project Code length must be 4 characters.");
			bool = false;
		}

		if (dto.getName() == null || dto.getName().length() < 6) {
			setError("name", "Name length is too short (requires 6 characters).");
			bool = false;
		}

		if (dto.getStartAt() == null || !DataValidation.isValidDate(dto.getStartAt())) {
			setError("startAt", "Invalid Start Date");
			bool = false;
		}

		if (dto.getFinishAt() == null || !DataValidation.isValidDate(dto.getFinishAt())) {
			setError("finishAt", "Invalid Finish Date");
			bool = false;
		} else if (Format.toDate(dto.getStartAt()).after(Format.toDate(dto.getFinishAt()))) {
			setError("startAt", "Start Date can't after Finish Date");
			bool = false;
		}

		return bool;
	}

}
