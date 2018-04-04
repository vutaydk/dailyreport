package model.business.project;

import java.util.Optional;

import common.util.DataValidation;
import common.util.Format;
import model.business.Message;
import model.entity.Project;
import model.repo.ProjectRepo;

public class ProjectLogic extends Message {

	private final ProjectDTO dto;
	private Optional<Project> project = Optional.empty();
	private boolean isProcessing = true;

	public ProjectLogic(ProjectDTO dto) {
		this.dto = dto;
	}

	/**
	 * Check exist {@link Project}
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isValidId(int id) {
		project = ProjectRepo.model.find(id);
		return project.isPresent();
	}

	/**
	 * Handling {@link ProjectDTO}
	 * 
	 * @return {@link ProjectLogic}
	 */
	public ProjectLogic isValidData() {
		// check project code
		if (dto.getProjectCode() == null || dto.getProjectCode().length() != 4) {
			setError("projectCode", "Project Code length must be 4 characters.");
			isProcessing = false;
		}
		// check name
		if (dto.getName() == null || dto.getName().length() < 6) {
			setError("name", "Name length is too short (requires 6 characters).");
			isProcessing = false;
		}
		// check start at
		if (dto.getStartAt() == null || !DataValidation.isValidDate(dto.getStartAt())) {
			setError("startAt", "Invalid Start Date");
			isProcessing = false;
		}
		// check finish at
		if (dto.getFinishAt() == null || !DataValidation.isValidDate(dto.getFinishAt())) {
			setError("finishAt", "Invalid Finish Date");
			isProcessing = false;
		} else if (Format.toDate(dto.getStartAt()).after(Format.toDate(dto.getFinishAt()))) {
			setError("startAt", "Start Date can't after Finish Date");
			isProcessing = false;
		}

		return this;
	}

	/**
	 * Merge {@link ProjectDTO} to {@link Project}
	 * 
	 * @return {@link Project}
	 */
	private Project megerData() {
		if (!project.isPresent())
			project = Optional.of(new Project());
		project.get().setProjectCode(dto.getProjectCode());
		project.get().setName(dto.getName());
		project.get().setStartAt(Format.toDate(dto.getStartAt()));
		project.get().setFinishAt(Format.toDate(dto.getFinishAt()));
		return project.get();
	}

	/**
	 * Insert {@link Project} to database
	 * 
	 * @return boolean
	 */
	public boolean insert() {
		if (isProcessing)
			return false;
		Project enity = megerData();
		return ProjectRepo.model.insert(enity);
	}

	/**
	 * Update {@link Project} to database
	 * 
	 * @return boolean
	 */
	public boolean update() {
		if (isProcessing)
			return false;
		Project enity = megerData();
		return ProjectRepo.model.update(enity);
	}

}
