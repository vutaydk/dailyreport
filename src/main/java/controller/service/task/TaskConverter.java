package controller.service.task;

import model.entity.Task;

public class TaskConverter {
	public static Task fromDtoToEntity(TaskDTO dto) {
		Task en = new Task();
		en.setTaskCode(dto.getTaskCode());
		en.setName(dto.getName());
		return en;
	}

	public static TaskJSON fromEntityToJSON(Task en) {
		TaskJSON dto = new TaskJSON();
		dto.setId(en.getId());
		dto.setTaskCode(en.getTaskCode());
		dto.setName(en.getName());
		return dto;
	}
}
