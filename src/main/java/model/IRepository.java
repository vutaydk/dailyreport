package model;

import java.util.List;

public interface IRepository<T> {

	List<T> get();
	
	boolean insert(T t);
	
	boolean update(T t);
	
	boolean delete(T t);
	
}
