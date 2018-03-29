package model.business.report;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportDTO {

	private Integer projectId;
	private Integer taskId;
	private Integer timeWorked;
	private String note;

	public ReportLogic getLogic() {
		return new ReportLogic(this);
	}

}
