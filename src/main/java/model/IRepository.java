package model;

import java.util.List;

public interface IRepository<T> {

	List<T> get();
	
	T find();
	
	
	
	
}
