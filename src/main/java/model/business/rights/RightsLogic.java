package model.business.rights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import model.business.ErrorMap;
import model.entity.Rights;
import model.repo.RightsRepo;

public class RightsLogic extends ErrorMap {

	/**
	 * Variable RightsDTO
	 */
	private final RightsDTO dto;

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public RightsLogic(RightsDTO entity) {
		this.dto = entity;
	}

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

	/**
	 * Check exist Rights
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isValidId(int id) {
		Optional<Rights> rights = RightsRepo.model.find(id);
		boolean isValid = rights.isPresent();
		if (isValid)
			dto.setRights(rights);
		return isValid;
	}

	/**
	 * Data Validation
	 * 
	 * @return boolean
	 */
	public boolean isValidData() {
		boolean bool = true;

		if (dto.getName() == null || dto.getName().length() < 6) {
			setError("name", "Name length is too short (requires 6 characters).");
			bool = false;
		}

		return bool;
	}

}
