package controller.service.project;

import model.entity.Project;

public class ProjectConverter {
	public static Project fromDtoToEntity(ProjectDTO dto) {
		Project en = new Project();
		en.setProjectCode(dto.getProjectCode());
		en.setName(dto.getName());
		en.setStartAt(dto.getStartAt());
		en.setFinishAt(dto.getFinishAt());
		return en;
	}

	public static ProjectDTO fromEntityToDto(Project en) {
		ProjectDTO dto = new ProjectDTO(en.getProjectCode(), en.getName(), en.getStartAt(), en.getFinishAt());
		return dto;
	}
}
