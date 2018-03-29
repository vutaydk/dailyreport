package model.business.rights;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RightsDTO {

	private String name;
	private Integer level;

	public RightsLogic getLogic() {
		return new RightsLogic(this);
	}

}
