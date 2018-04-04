package model.business.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.entity.Rights;
import model.repo.RightsRepo;

public class RightsJson {

	/**
	 * Get Json Rights
	 * 
	 * @return List
	 */
	public static List<Object> getJson() {
		List<Object> list = new ArrayList<>();
		for (Rights p : RightsRepo.model.getAll()) {
			HashMap<String, Object> rights = new HashMap<>();
			rights.put("id", p.getId());
			rights.put("name", p.getName());
			rights.put("level", p.getLevel());
			list.add(rights);
		}
		return list;
	}

}
