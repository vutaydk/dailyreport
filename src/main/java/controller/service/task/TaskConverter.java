package controller.service.task;

import model.entity.Task;

public class TaskConverter {
	public static Task fromDtoToEntity(TaskDTO dto) {
		Task en = new Task();
		en.setTaskCode(dto.getTaskCode());
		en.setName(dto.getName());
		return en;
	}

	public static TaskDTO fromEntityToDto(Task en) {
		TaskDTO dto = new TaskDTO(en.getTaskCode(), en.getName());
		return dto;
	}
}
