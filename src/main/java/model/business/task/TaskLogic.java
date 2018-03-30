package model.business.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import model.business.ErrorMap;
import model.entity.Task;
import model.repo.TaskRepo;

public class TaskLogic extends ErrorMap {

	/**
	 * Variable TaskDTO
	 */
	private final TaskDTO dto;

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public TaskLogic(TaskDTO entity) {
		this.dto = entity;
	}

	/**
	 * Get Json Task
	 * 
	 * @return List
	 */
	public static List<Object> getJson() {
		List<Object> list = new ArrayList<>();
		for (Task p : TaskRepo.model.getAll()) {
			HashMap<String, Object> project = new HashMap<>();
			project.put("id", p.getId());
			project.put("taskCode", p.getTaskCode());
			project.put("name", p.getName());
			list.add(project);
		}
		return list;
	}

	/**
	 * Check exist Task
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isValidId(int id) {
		Optional<Task> task = TaskRepo.model.find(id);
		boolean isValid = task.isPresent();
		if (isValid)
			dto.setTask(task);
		return isValid;
	}

	/**
	 * Data Validation
	 * 
	 * @return boolean
	 */
	public boolean isValidData() {
		boolean bool = true;

		if (dto.getTaskCode() == null || dto.getTaskCode().length() != 4) {
			setError("taskCode", "Task Code length must be 4 characters.");
			bool = false;
		}

		if (dto.getName() == null || dto.getName().length() < 6) {
			setError("name", "Name length is too short (requires 6 characters).");
			bool = false;
		}

		return bool;
	}

}
