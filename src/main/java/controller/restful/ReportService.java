package controller.restful;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.val;
import model.business.report.ReportDTO;
import model.business.report.ReportLogic;

@Path("/report")
@Produces(MediaType.APPLICATION_JSON)
public class ReportService {

	@GET
	@Path("get-all")
	public Response getAll() {
		Object entity = ReportLogic.getJson();
		return Response.ok(entity).build();
	}

	@POST
	@Path("add")
	public Response insert(ReportDTO reportDTO) {

		// builder a new ReportLogic
		val val = reportDTO.getLogic();

		// validation form
		if (val.isValidData()) {

			// add to database
			val.add();

			return Response.ok().build();
		}

		return Response.ok(val.getErrorMap()).build();
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public Response update(@PathParam("id") int id, ReportDTO reportDTO) {

		// builder a new ReportLogic
		val val = reportDTO.getLogic();

		// check id exist
		if (val.isValidId(id))
			return Response.status(404).build();

		// validation form
		if (val.isValidData()) {

			// update to database
			val.update();

			return Response.ok().build();
		}

		return Response.ok(val.getErrorMap()).build();
	}

}
