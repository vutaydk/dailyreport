package model.business.project;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectDTO {

	@NotNull(message = "Không để trống.")
	@Length(min = 3, message = "Không nhỏ quá 3")
	private String projectCode;
	private String name;
	private String startAt;
	private String finishAt;

	/**
	 * Initialize {@link ProjectLogic} class for handling data
	 * 
	 * @return {@link ProjectLogic}
	 */
	public ProjectLogic getLogic() {
		return new ProjectLogic(this);
	}

}
