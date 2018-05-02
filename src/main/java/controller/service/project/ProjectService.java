package controller.service.project;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import controller.filter.JWTTokenNeeded;
import controller.service.project.logic.AddProjectDTO;
import controller.service.project.logic.ChartFullJSON;
import controller.service.project.logic.ChartJSON;
import controller.service.project.logic.EditProjectDTO;
import controller.service.project.logic.ProjectConverter;
import controller.service.project.logic.ProjectJSON;
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
	@Inject
	ProjectConverter converter;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public int insert(@Valid AddProjectDTO dto) {
		Project project = converter.fromAddDtoToEntity(dto);
		System.out.println(project);
		// handling data
		int projectId = addCommand.execute(project);
		return projectId;
	}

	@POST
	@Path("{id: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public int update(@Valid EditProjectDTO dto, @PathParam("id") int id) {
		Project project = converter.fromEditDtoToEntity(dto, id);
		System.out.println(project);
		// handling data
		int projectId = updateCommand.execute(project);
		return projectId;
	}

	@GET
	@Path("get-all")
	@JWTTokenNeeded
	public List<ProjectJSON> getAll() {
		return projectSelector.getList().stream().map(p -> {
			return converter.fromEntityToJSON(p);
		}).collect(Collectors.toList());
	}

	@GET
	@Path("get/{id}")
	@JWTTokenNeeded
	public ProjectJSON get(@PathParam("id") int id) {
		return converter.fromEntityToJSON(projectSelector.getProjectDetailById(id).get());
	}

	@GET
	@Path("get-chart")
	@JWTTokenNeeded
	public List<ChartJSON> getChart() {
		return projectSelector.getList().stream().map(p -> {
			return converter.fromEntityToChartJSON(p);
		}).collect(Collectors.toList());
	}

	@GET
	@Path("get-chart-full")
	public List<ChartFullJSON> getChartFull() {
		return projectSelector.getList().stream().map(p -> {
			return converter.fromEntityToChartFullJSON(p);
		}).collect(Collectors.toList());
	}
}
