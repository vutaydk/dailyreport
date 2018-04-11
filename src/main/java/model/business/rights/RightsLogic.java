package model.business.rights;

import java.util.Optional;

import model.business.Message;
import model.entity.Rights;
import model.repo.RightsRepo;

public class RightsLogic extends Message {

	private final RightsDTO dto;
	private Optional<Rights> rights = Optional.empty();

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
	 * Data validate
	 * 
	 * @input {@link RightsDTO}
	 * @return boolean
	 */
	public boolean isValidData() {

		boolean isProcesing = false;

		// check name
		if (dto.getName() == null || dto.getName().length() < 6) {
			setMessage("name", "Name length is too short (requires 6 characters).");
			isProcesing = true;
		}

		return !isProcesing;
	}

	/**
	 * Merge data from class {@link RightsDTO} to entity {@link Rights}
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
	 * Insert entity {@link Rights} to database
	 */
	public void insert() {

		Rights rights = megerData();
		boolean result = RightsRepo.model.persist(rights);

		if (result)
			setMessage("Add success new rights");
		else
			setMessage("Add error new rights");
	}

	/**
	 * Update entity {@link Rights} to database
	 */
	public void update() {

		Rights rights = megerData();
		boolean result = RightsRepo.model.persist(rights);

		if (result)
			setMessage("Edit success rights");
		else
			setMessage("Edit error rights");
	}

}
