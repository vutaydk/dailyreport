package model.business.project;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectDTO {

	private String projectCode;
	private String name;
	private String startAt;
	private String finishAt;

	/**
	 * Initialize {@link ProjectLogic} for handling data
	 * 
	 * @return {@link ProjectLogic}
	 */
	public ProjectLogic getLogic() {
		return new ProjectLogic(this);
	}

}
