package model.business.report;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportDTO {

	private List<ReportDTO2> datas;

	/**
	 * Initialize {@link ReportLogic} for handling data
	 * 
	 * @return {@link ReportLogic}
	 */
	public ReportLogic getLogic() {
		return new ReportLogic(this);
	}

}
