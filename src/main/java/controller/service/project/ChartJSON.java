package controller.service.project;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChartJSON {
	private Integer id;
	private String name;
	private List<PChartJSON> tasks;
}
