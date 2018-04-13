package controller.service.project;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
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

	@GET
	@Path("get-all")
	public List<ProjectDTO> getAllProject() {
		return projectSelector.getAllProject().stream().map(x -> {
			return ProjectConverter.fromEntityToDto(x);
		}).collect(Collectors.toList());
	}

	@POST
	@Path("add")
	public int insert(ProjectDTO projectDTO) {
		projectDTO.isValidData();
		Project project = ProjectConverter.fromDtoToEntity(projectDTO);
		// handling data
		int projectId = addCommand.execute(project);
		return projectId;
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public int update(@PathParam("id") int id, ProjectDTO projectDTO) {
		projectDTO.isValidData();
		Project project = ProjectConverter.fromDtoToEntity(projectDTO);
		project.setId(id);
		// handling data
		int projectId = updateCommand.execute(project);
		return projectId;
	}

	// @GET
	// @Path("get-chart")
	// public Response getChartJson() {
	// log.debug("get chart json");
	// Object json = ProjectJson.getJsonForChart();
	// return Response.ok(json).build();
	// }

}
