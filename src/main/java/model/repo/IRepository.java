package model.repo;

import java.util.List;

public interface IRepository<T> {

	List<T> getList();

	boolean persist(T t);

	boolean remove(T t);

}
