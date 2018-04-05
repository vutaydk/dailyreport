package model.business.report;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PTaskDTO {
	private Integer taskId;
	private Integer timework;
	private String note;
}
