package model.repo;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {

	Optional<List<T>> getAll();

	boolean insert(T t);

	boolean update(T t);

	boolean delete(T t);

}
