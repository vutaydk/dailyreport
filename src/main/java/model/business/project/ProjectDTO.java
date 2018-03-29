package model.business.project;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectDTO {

	private Integer id;
	private String projectCode;
	private String name;
	private String startAt;
	private String finishAt;

	public ProjectLogic getLogic() {
		return new ProjectLogic(this);
	}

}
