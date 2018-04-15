package controller.service.report;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDTO {

	private Integer projectId;
	private List<PTaskDTO> tasks;

	public void isValidData() {

	}
}
