package controller.service.report.logic;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportDTO {

	@PositiveOrZero
	private Integer projectId;

	@NotEmpty
	private List<PTaskDTO> tasks;
}
