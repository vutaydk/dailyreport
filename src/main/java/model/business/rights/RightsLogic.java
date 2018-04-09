package model.business.rights;

import java.util.Optional;

import model.business.Message;
import model.entity.Rights;
import model.repo.RightsRepo;

public class RightsLogic extends Message {

	private final RightsDTO dto;
	private Optional<Rights> rights = Optional.empty();
	private boolean isProcesing = false;

	public RightsLogic(RightsDTO dto) {
		this.dto = dto;
	}

	/**
	 * Check exist {@link Rights}
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isValidId(Integer id) {
		rights = RightsRepo.model.find(id);
		return rights.isPresent();
	}

	/**
	 * Handling {@link RightsDTO}
	 * 
	 * @return {@link RightsLogic}
	 */
	public RightsLogic handleData() {
		// check name
		if (dto.getName() == null || dto.getName().length() < 6) {
			setMessage("name", "Name length is too short (requires 6 characters).");
			isProcesing = true;
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
	 */
	public void insert() {
		if (isProcesing)
			return;
		Rights rights = megerData();
		boolean result = RightsRepo.model.insert(rights);
		if (result)
			setMessage("Add success new rights");
		else
			setMessage("Add error new rights");
	}

	/**
	 * Update {@link Rights} to database
	 */
	public void update() {
		if (isProcesing)
			return;
		Rights rights = megerData();
		boolean result = RightsRepo.model.update(rights);
		if (result)
			setMessage("Edit success rights");
		else
			setMessage("Edit error rights");
	}

}
