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
import model.repo.ProjectRepo;

public class ProjectLogic extends ErrorMap {

	/**
	 * Variable ProjectEntity
	 */
	private final ProjectEntity entity;

	@Getter
	private Optional<Project> project = Optional.empty();

	/**
	 * Contructer
	 * 
	 * @param entity
	 */
	public ProjectLogic(ProjectEntity entity) {
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
			project.put("startAt", p.getStartAt());
			project.put("finishAt", p.getFinishAt());
			list.add(project);
		}
		return list;
	}

	/**
	 * Check exist Project
	 * 
	 * @return boolean
	 */
	public boolean isValidId() {
		project = ProjectRepo.model.find(entity.getId());
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
			setError("txt_projectCode", "Project Code length must be 4 characters.");
			bool = false;
		}

		if (entity.getName() == null || entity.getName().length() < 6) {
			setError("txt_name", "Name length is too short (requires 6 characters).");
			bool = false;
		}

		if (entity.getStartAt() == null || !DataValidation.isValidDate(entity.getStartAt())) {
			setError("txt_startAt", "Invalid Start Date");
			bool = false;
		}

		if (entity.getFinishAt() == null || !DataValidation.isValidDate(entity.getFinishAt())) {
			setError("txt_finishAt", "Invalid Finish Date");
			bool = false;
		} else if (Format.toDate(entity.getStartAt()).after(Format.toDate(entity.getFinishAt()))) {
			setError("txt_startAt", "Start Date can't after Finish Date");
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
	private void setData(ProjectEntity entity, Project project) {
		project.setProjectCode(entity.getProjectCode());
		project.setName(entity.getName());
		project.setStartAt(Format.toDate(entity.getStartAt()));
		project.setFinishAt(Format.toDate(entity.getFinishAt()));
	}

}
