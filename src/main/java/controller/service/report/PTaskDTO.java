package controller.service.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PTaskDTO {
	private Integer taskId;
	private Integer timework;
	private String note;
}
