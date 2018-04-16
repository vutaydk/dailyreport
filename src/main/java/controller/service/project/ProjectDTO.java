package controller.service.project;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDTO {
	@NotNull(message = "Project code must not be blank.")
	@Size(min = 4, max = 4, message = "Project code must be exactly 4 characters.")
	private String projectCode;
	@NotNull(message = "Name must not be blank.")
	@Size(min = 6, message = "Name must be at least 6 characters.")
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date startAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date finishAt;
}
