package model.repo.right;

import java.util.Optional;
import model.entity.Rights;
import model.repo.IRepository;

public interface IRightRepo extends IRepository<Rights> {
	Optional<Rights> findById(int id);
}
