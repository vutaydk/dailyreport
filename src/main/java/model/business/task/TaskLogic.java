package model.business.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import model.business.ErrorMap;
import model.entity.Task;
import model.repo.TaskRepo;

public class TaskLogic extends ErrorMap {

	/**
	 * Variable TaskEntity
	 */
	private final TaskEntity entity;

	@Getter
	private Optional<Task> task = Optional.empty();

	/**
	 * Contructer
	 * 
	 * @param entity
	 */
	public TaskLogic(TaskEntity entity) {
		this.entity = entity;
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
	 * @return boolean
	 */
	public boolean isValidId() {
		task = TaskRepo.model.find(entity.getId());
		return task.isPresent();
	}

	/**
	 * Data Validation
	 * 
	 * @return boolean
	 */
	public boolean isValidData() {
		boolean bool = true;
		
		if (entity.getTaskCode() == null || entity.getTaskCode().length() != 4) {
			setError("txt_taskCode", "Task Code length must be 4 characters.");
			bool = false;
		}

		if (entity.getName() == null || entity.getName().length() < 6) {
			setError("txt_name", "Name length is too short (requires 6 characters).");
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
		Task task = new Task();
		setData(entity, task);
		return TaskRepo.model.insert(task);
	}

	/**
	 * Update to database
	 * 
	 * @return boolean
	 */
	public boolean update() {
		if (!task.isPresent())
			return false;

		Task task = this.task.get();
		setData(entity, task);
		return TaskRepo.model.update(task);
	}

	/**
	 * Merge data
	 * 
	 * @param entity
	 * @param project
	 */
	private void setData(TaskEntity entity, Task project) {
		project.setTaskCode(entity.getTaskCode());
		project.setName(entity.getName());
	}

}
