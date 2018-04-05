package model.business.task;

import java.util.Optional;

import model.business.Message;
import model.entity.Task;
import model.repo.TaskRepo;

public class TaskLogic extends Message {

	private final TaskDTO dto;
	private Optional<Task> task = Optional.empty();
	private boolean isProcesing = true;

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
	 * Handling {@link TaskDTO}
	 * 
	 * @return {@link TaskLogic}
	 */
	public TaskLogic isValidData() {
		// check task code
		if (dto.getTaskCode() == null || dto.getTaskCode().length() != 4) {
			setError("taskCode", "Task Code length must be 4 characters.");
			isProcesing = false;
		}
		// check name
		if (dto.getName() == null || dto.getName().length() < 6) {
			setError("name", "Name length is too short (requires 6 characters).");
			isProcesing = false;
		}

		return this;
	}

	/**
	 * Merge {@link TaskDTO} to {@link Task}
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
		if (!isProcesing)
			return;
		Task task = megerData();
		boolean result = TaskRepo.model.insert(task);
		if (result)
			setMessage("success", "Add success new task");
		else
			setMessage("errror", "Add error new task");
	}

	/**
	 * Update {@link Task} to database
	 */
	public void update() {
		if (!isProcesing)
			return;
		Task task = megerData();
		boolean result = TaskRepo.model.update(task);
		if (result)
			setMessage("success", "Edit success task");
		else
			setMessage("errror", "Edit error task");
	}

}
