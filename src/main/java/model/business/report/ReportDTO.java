package model.business.report;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportDTO {

	private Integer id;
	private Integer projectId;
	private Integer taskId;
	private Integer timeWorked;
	private String note;

	public ReportLogic getLogic() {
		return new ReportLogic(this);
	}

}
