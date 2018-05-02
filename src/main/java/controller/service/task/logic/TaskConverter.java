package controller.service.task.logic;

import model.entity.Task;

public class TaskConverter {

	public Task fromAddDtoToEntity(AddTaskDTO dto) {
		Task e = new Task();
		e.setTaskCode(dto.getTaskCode());
		e.setName(dto.getName());
		return e;
	}

	public Task fromEditDtoToEntity(EditTaskDTO dto, int id) {
		Task e = new Task();
		e.setId(id);
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
