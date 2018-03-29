package model.business.rights;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RightsDTO {

	private Integer id;
	private String name;
	private Integer level;

	public RightsLogic getLogic() {
		return new RightsLogic(this);
	}

}
