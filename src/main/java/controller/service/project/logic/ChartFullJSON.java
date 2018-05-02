package controller.service.project.logic;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChartFullJSON {
	private Integer id;
	private String projectCode;
	private String name;
	private List<PChartFullJSON> reports;
}
