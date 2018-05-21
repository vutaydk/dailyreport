package controller.service.report.logic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PTaskDTO {
	private Integer taskId;
	private Float timeWork;
	private String note;
}
