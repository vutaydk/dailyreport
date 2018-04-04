package model.business.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.entity.Task;
import model.repo.TaskRepo;

public class TaskJson {

	/**
	 * Get all Json
	 * 
	 * @return List
	 */
	public static List<Object> getJson() {
		List<Object> list = new ArrayList<>();
		for (Task p : TaskRepo.model.getAll()) {
			HashMap<String, Object> project = new HashMap<>();
			project.put("id", p.getId());
			project.put("taskCode", p.getTaskCode());
			project.put("name", p.getName());
			list.add(project);
		}
		return list;
	}

}
