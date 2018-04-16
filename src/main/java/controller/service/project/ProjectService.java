package controller.service.project;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.business.project.AddProjectHandler;
import model.business.project.ProjectSelector;
import model.business.project.UpdateProjectHandler;
import model.entity.Project;

@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectService {
	@Inject
	private AddProjectHandler addCommand;
	@Inject
	private UpdateProjectHandler updateCommand;
	@Inject
	private ProjectSelector projectSelector;

	@POST
	@Path("add")
	public int insert(@Valid ProjectDTO projectDTO) {
		Project project = ProjectConverter.fromDtoToEntity(projectDTO);
		// handling data
		int projectId = addCommand.execute(project);
		return projectId;
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public int update(@Valid ProjectDTO projectDTO, @PathParam("id") int id) {
		Project project = ProjectConverter.fromDtoToEntity(projectDTO);
		// handling data
		int projectId = updateCommand.execute(project, id);
		return projectId;
	}

	@GET
	@Path("get-all")
	public List<ProjectJSON> getAllProject() {
		return projectSelector.getList().stream().map(p -> {
			return ProjectConverter.fromEntityToJSON(p);
		}).collect(Collectors.toList());
	}

	@GET
	@Path("get-chart")
	public List<ChartJSON> getChartProject() {
		return projectSelector.getList().stream().map(p -> {
			return ProjectConverter.fromEntityToChartJSON(p);
		}).collect(Collectors.toList());
	}
}
