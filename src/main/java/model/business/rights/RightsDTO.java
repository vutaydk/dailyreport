package model.business.rights;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.entity.Rights;
import model.repo.RightsRepo;

@Getter
@Setter
@ToString
public class RightsDTO {

	private String name;
	private Integer level;

	private Optional<Rights> rights = Optional.empty();

	public RightsLogic getLogic() {
		return new RightsLogic(this);
	}

	/**
	 * Add to database
	 * 
	 * @return boolean
	 */
	public boolean insert() {
		if (!rights.isPresent())
			return false;

		Rights rights = megerData();
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

		Rights rights = megerData();
		return RightsRepo.model.update(rights);
	}

	/**
	 * Merge data
	 * 
	 * @return Rights
	 */
	private Rights megerData() {
		Rights rights = this.rights.get();
		rights.setName(name);
		rights.setLevel(level);
		return rights;
	}

}
