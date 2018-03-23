package model.repo;

import java.util.List;

public interface IRepository<T> {

	List<T> getAll();

	boolean insert(T t);

	boolean update(T t);

	boolean delete(T t);

}
