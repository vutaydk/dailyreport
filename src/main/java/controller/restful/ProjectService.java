package controller.restful;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.val;
import model.business.project.ProjectDTO;
import model.business.project.ProjectLogic;

@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectService {

	@GET
	@Path("get-all")
	public Response getAll() {
		Object entity = ProjectLogic.getJson();
		return Response.ok(entity).build();
	}

	@GET
	@Path("get-chart")
	public Response getForChart() {
		Object entity = ProjectLogic.getJsonForChart();
		return Response.ok(entity).build();
	}

	@POST
	@Path("add")
	public Response insert(ProjectDTO projectDTO) {

		// builder a new ProjectLogic
		val val = projectDTO.getLogic();

		// validation form
		if (val.isValidData()) {

			// add to database
			projectDTO.insert();

			return Response.ok().build();
		}

		return Response.ok(val.getErrorMap()).build();

	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public Response update(@PathParam("id") int id, ProjectDTO projectDTO) {

		// builder a new ProjectLogic
		val val = projectDTO.getLogic();

		// check id exist
		if (!val.isValidId(id))
			return Response.status(404).build();

		// validation form
		if (val.isValidData()) {

			// update to database
			projectDTO.update();

			return Response.ok().build();
		}

		return Response.ok(val.getErrorMap()).build();
	}

}
