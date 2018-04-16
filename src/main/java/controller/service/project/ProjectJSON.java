package controller.service.project;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectJSON {
	private Integer id;
	private String projectCode;
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "dd/MM/yyyy")
	private Date startAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date finishAt;
}
