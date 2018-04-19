package model.repo.project;

import java.util.Optional;
import model.entity.Project;
import model.repo.IRepository;

public interface IProjectRepo extends IRepository<Project> {

	Optional<Project> findByProjectCode(String code);

	Optional<Project> findById(int id);
}
