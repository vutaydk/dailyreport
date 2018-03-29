package model.business.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import common.util.DataValidation;
import common.util.Format;
import lombok.Getter;
import model.business.ErrorMap;
import model.entity.Project;
import model.entity.Report;
import model.entity.ReportDetail;
import model.repo.ProjectRepo;

public class ProjectLogic extends ErrorMap {

	/**
	 * Variable ProjectEntity
	 */
	private final ProjectDTO entity;

	@Getter
	private Optional<Project> project = Optional.empty();

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public ProjectLogic(ProjectDTO entity) {
		this.entity = entity;
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
		project = ProjectRepo.model.find(id);
		return project.isPresent();
	}

	/**
	 * Data Validation
	 * 
	 * @return boolean
	 */
	public boolean isValidData() {
		boolean bool = true;

		if (entity.getProjectCode() == null || entity.getProjectCode().length() != 4) {
			setError("projectCode", "Project Code length must be 4 characters.");
			bool = false;
		}

		if (entity.getName() == null || entity.getName().length() < 6) {
			setError("name", "Name length is too short (requires 6 characters).");
			bool = false;
		}

		if (entity.getStartAt() == null || !DataValidation.isValidDate(entity.getStartAt())) {
			setError("startAt", "Invalid Start Date");
			bool = false;
		}

		if (entity.getFinishAt() == null || !DataValidation.isValidDate(entity.getFinishAt())) {
			setError("finishAt", "Invalid Finish Date");
			bool = false;
		} else if (Format.toDate(entity.getStartAt()).after(Format.toDate(entity.getFinishAt()))) {
			setError("startAt", "Start Date can't after Finish Date");
			bool = false;
		}

		return bool;
	}

	/**
	 * Add to database
	 * 
	 * @return boolean
	 */
	public boolean add() {
		Project project = new Project();
		setData(entity, project);
		return ProjectRepo.model.insert(project);
	}

	/**
	 * Update to database
	 * 
	 * @return boolean
	 */
	public boolean update() {
		if (!project.isPresent())
			return false;

		Project project = this.project.get();
		setData(entity, project);
		return ProjectRepo.model.update(project);
	}

	/**
	 * Merge data
	 * 
	 * @param entity
	 * @param project
	 */
	private void setData(ProjectDTO entity, Project project) {
		project.setProjectCode(entity.getProjectCode());
		project.setName(entity.getName());
		project.setStartAt(Format.toDate(entity.getStartAt()));
		project.setFinishAt(Format.toDate(entity.getFinishAt()));
	}

}
