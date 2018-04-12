package controller.service.project;

import java.util.Date;

import common.exception.BusinessException;
import common.exception.message.RawMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

	private int id;
	private String projectCode;
	private String name;
	private Date startAt;
	private Date finishAt;

	public void isValidData() {
		// check project code
		if (this.getProjectCode() == null || this.getProjectCode().length() != 4) {
			throw new BusinessException(new RawMessage("Project Code length must be 4 characters."));
		}
		// check name
		if (this.getName() == null || this.getName().length() < 6) {
			throw new BusinessException(new RawMessage("Name length is too short (requires 6 characters)."));
		}
	}
}
