package controller.service.report;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDTO {

	@PositiveOrZero
	private Integer projectId;

	@NotEmpty
	private List<PTaskDTO> tasks;
}
