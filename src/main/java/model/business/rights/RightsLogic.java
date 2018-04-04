package model.business.rights;

import java.util.Optional;

import model.business.Message;
import model.entity.Rights;
import model.repo.RightsRepo;

public class RightsLogic extends Message {

	private final RightsDTO dto;
	private Optional<Rights> rights = Optional.empty();
	private boolean isProcesing = true;

	public RightsLogic(RightsDTO dto) {
		this.dto = dto;
	}

	/**
	 * Check exist {@link Rights}
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isValidId(int id) {
		rights = RightsRepo.model.find(id);
		return rights.isPresent();
	}

	/**
	 * Handling {@link RightsDTO}
	 * 
	 * @return {@link RightsLogic}
	 */
	public RightsLogic isValidData() {
		// check name
		if (dto.getName() == null || dto.getName().length() < 6) {
			setError("name", "Name length is too short (requires 6 characters).");
			isProcesing = false;
		}

		return this;
	}

	/**
	 * Merge {@link RightsDTO} to {@link Rights}
	 * 
	 * @return {@link Rights}
	 */
	private Rights megerData() {
		if (!rights.isPresent())
			rights = Optional.of(new Rights());
		rights.get().setName(dto.getName());
		rights.get().setLevel(dto.getLevel());
		return rights.get();
	}

	/**
	 * Insert {@link Rights} to database
	 * 
	 * @return boolean
	 */
	public boolean insert() {
		if (!isProcesing)
			return false;
		Rights rights = megerData();
		return RightsRepo.model.insert(rights);
	}

	/**
	 * Update {@link Rights} to database
	 * 
	 * @return boolean
	 */
	public boolean update() {
		if (!isProcesing)
			return false;
		Rights rights = megerData();
		return RightsRepo.model.update(rights);
	}

}
