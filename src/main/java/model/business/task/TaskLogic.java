package model.business.task;

import java.util.Optional;

import model.business.Message;
import model.entity.Task;
import model.repo.TaskRepo;

public class TaskLogic extends Message {

	private final TaskDTO dto;
	private Optional<Task> task = Optional.empty();

	public TaskLogic(TaskDTO dto) {
		this.dto = dto;
	}

	/**
	 * Check exist {@link Task}
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isValidId(Integer id) {
		task = TaskRepo.model.find(id);
		return task.isPresent();
	}

	/**
	 * Data validate
	 * 
	 * @input {@link TaskDTO}
	 * @return boolean
	 */
	public boolean isValidData() {
		boolean isProcesing = false;
		// check task code
		if (dto.getTaskCode() == null || dto.getTaskCode().length() != 4) {
			setMessage("taskCode", "Task Code length must be 4 characters.");
			isProcesing = true;
		}
		// check name
		if (dto.getName() == null || dto.getName().length() < 6) {
			setMessage("name", "Name length is too short (requires 6 characters).");
			isProcesing = true;
		}

		return !isProcesing;
	}

	/**
	 * Merge data from {@link TaskDTO} to {@link Task}
	 * 
	 * @return {@link Task}
	 */
	private Task megerData() {
		if (!task.isPresent())
			task = Optional.of(new Task());
		task.get().setTaskCode(dto.getTaskCode());
		task.get().setName(dto.getName());
		return task.get();
	}

	/**
	 * Insert {@link Task} to database
	 */
	public void insert() {
		Task task = megerData();
		boolean result = TaskRepo.model.insert(task);
		if (result)
			setMessage("Add success new task");
		else
			setMessage("Add error new task");
	}

	/**
	 * Update {@link Task} to database
	 */
	public void update() {
		Task task = megerData();
		boolean result = TaskRepo.model.update(task);
		if (result)
			setMessage("Edit success task");
		else
			setMessage("Edit error task");
	}

}
