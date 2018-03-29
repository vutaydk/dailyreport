package model.business.rights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import model.business.ErrorMap;
import model.entity.Rights;
import model.repo.RightsRepo;

public class RightsLogic extends ErrorMap {

	/**
	 * Variable RightsEntity
	 */
	private final RightsDTO entity;

	@Getter
	private Optional<Rights> rights = Optional.empty();

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public RightsLogic(RightsDTO entity) {
		this.entity = entity;
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
	 * @return boolean
	 */
	public boolean isValidId() {
		rights = RightsRepo.model.find(entity.getId());
		return rights.isPresent();
	}

	/**
	 * Data Validation
	 * 
	 * @return boolean
	 */
	public boolean isValidData() {
		boolean bool = true;

		if (entity.getName() == null || entity.getName().length() < 6) {
			setError("txt_name", "Name length is too short (requires 6 characters).");
			bool = false;
		}

		return bool;
	}

	/**
	 * Add to database
	 * 
	 * @return boolean
	 */
	public boolean add() {
		Rights rights = new Rights();
		setData(entity, rights);
		return RightsRepo.model.insert(rights);
	}

	/**
	 * Update to database
	 * 
	 * @return boolean
	 */
	public boolean update() {
		if (!rights.isPresent())
			return false;

		Rights rights = this.rights.get();
		setData(entity, rights);
		return RightsRepo.model.update(rights);
	}

	/**
	 * Merge data
	 * 
	 * @param entity
	 * @param rights
	 */
	private void setData(RightsDTO entity, Rights rights) {
		rights.setName(entity.getName());
		rights.setLevel(entity.getLevel());
	}

}
