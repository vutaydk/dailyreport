package model.repo.task;

import java.util.Optional;

import model.entity.Task;
import model.repo.IRepository;

public interface ITaskRepo extends IRepository<Task> {
	Optional<Task> findById(int id);
}
