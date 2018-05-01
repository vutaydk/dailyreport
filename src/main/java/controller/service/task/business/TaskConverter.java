package controller.service.task.business;

import model.entity.Task;

public class TaskConverter {

	public Task fromDtoToEntity(TaskDTO dto) {
		Task e = new Task();
		e.setTaskCode(dto.getTaskCode());
		e.setName(dto.getName());
		return e;
	}

	public TaskJSON fromEntityToJSON(Task e) {
		TaskJSON tj = new TaskJSON();
		tj.setId(e.getId());
		tj.setTaskCode(e.getTaskCode());
		tj.setName(e.getName());
		return tj;
	}
}
