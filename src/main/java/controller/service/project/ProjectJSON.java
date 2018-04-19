package controller.service.project;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectJSON {
	private Integer id;
	private String projectCode;
	private String name;
	private Date startAt;
	private Date finishAt;
}
