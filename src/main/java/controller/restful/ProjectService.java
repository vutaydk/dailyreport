package controller.restful;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.val;
import lombok.extern.log4j.Log4j;
import model.business.json.ProjectJson;
import model.business.project.ProjectDTO;

@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
@Log4j
public class ProjectService {

	@GET
	@Path("get-all")
	public Response getAllJson() {
		log.debug("get all json");
		Object entity = ProjectJson.getJson();
		return Response.ok(entity).build();
	}

	@GET
	@Path("get-chart")
	public Response getChartJson() {
		log.debug("get chart json");
		Object entity = ProjectJson.getJsonForChart();
		return Response.ok(entity).build();
	}

	@POST
	@Path("add")
	public Response insert(ProjectDTO projectDTO) {
		log.debug("insert project");
		// initialize logic
		val handling = projectDTO.getLogic();
		// handling data
		handling.isValidData().insert();
		return Response.ok(handling.getMessage()).build();

	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public Response update(@PathParam("id") int id, ProjectDTO projectDTO) {
		log.debug("update project");
		// initialize logic
		val handling = projectDTO.getLogic();
		// check exist object
		if (!handling.isValidId(id))
			return Response.status(404).build();
		// handling data
		handling.isValidData().update();
		return Response.ok(handling.getMessage()).build();
	}

}
