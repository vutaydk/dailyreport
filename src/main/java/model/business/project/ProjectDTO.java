package model.business.project;

import java.util.Optional;

import common.util.Format;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.entity.Project;
import model.repo.ProjectRepo;

@Getter
@Setter
@ToString
public class ProjectDTO {

	private String projectCode;
	private String name;
	private String startAt;
	private String finishAt;

	private Optional<Project> project = Optional.of(new Project());

	public ProjectLogic getLogic() {
		return new ProjectLogic(this);
	}

	/**
	 * Add to database
	 * 
	 * @return boolean
	 */
	public boolean insert() {
		if (project.isPresent())
			return false;

		Project enity = megerData();
		return ProjectRepo.model.insert(enity);
	}

	/**
	 * Update to database
	 * 
	 * @return boolean
	 */
	public boolean update() {
		if (!project.isPresent())
			return false;

		Project enity = megerData();
		return ProjectRepo.model.update(enity);
	}

	/**
	 * Merge data
	 * 
	 * @return Project
	 */
	private Project megerData() {
		Project project = this.project.get();
		project.setProjectCode(projectCode);
		project.setName(name);
		project.setStartAt(Format.toDate(startAt));
		project.setFinishAt(Format.toDate(finishAt));
		return project;
	}

}
