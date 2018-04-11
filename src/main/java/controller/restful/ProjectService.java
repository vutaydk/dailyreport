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
		Object json = ProjectJson.getJSON();
		return Response.ok(json).build();
	}

	@GET
	@Path("get-chart")
	public Response getChartJson() {
		log.debug("get chart json");
		Object json = ProjectJson.getJSONForChart();
		return Response.ok(json).build();
	}

	@POST
	@Path("add")
	public Response insert(ProjectDTO projectDTO) {
		log.debug("insert project");

		val handling = projectDTO.getLogic(); // initialize logic

		if (handling.isValidData()) // handling data
			handling.insert();

		return Response.ok(handling.getMessage()).build();
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public Response update(@PathParam("id") int id, ProjectDTO projectDTO) {
		log.debug("update project");

		val handling = projectDTO.getLogic(); // initialize logic

		if (!handling.isValidId(id)) // check exist object
			return Response.status(404).build();

		if (handling.isValidData()) // handling data
			handling.update();

		return Response.ok(handling.getMessage()).build();
	}

}
