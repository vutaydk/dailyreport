package controller.service.task;

import model.entity.Task;

public class TaskConverter {

	public Task fromDtoToEntity(TaskDTO dto) {
		Task e = new Task();
		e.setTaskCode(dto.getTaskCode());
		e.setName(dto.getName());
		return e;
	}

	public TaskJSON fromEntityToJSON(Task e) {
		TaskJSON json = new TaskJSON();
		json.setId(e.getId());
		json.setTaskCode(e.getTaskCode());
		json.setName(e.getName());
		return json;
	}
}
