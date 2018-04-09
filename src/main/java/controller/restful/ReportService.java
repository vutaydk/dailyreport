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
import model.business.json.ReportJson;
import model.business.report.ReportDTO;

@Path("/report")
@Produces(MediaType.APPLICATION_JSON)
@Log4j
public class ReportService {

	@GET
	@Path("get-all")
	public Response getAll() {
		log.debug("get all json");
		Object json = ReportJson.getJson();
		return Response.ok(json).build();
	}

	@POST
	@Path("add")
	public Response insert(ReportDTO reportDTO) {
		log.debug("insert report");
		// initialize logic
		val val = reportDTO.getLogic();
		// handling data
		val.handleData().insert();
		return Response.ok(val.getMessage()).build();
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	public Response update(@PathParam("id") int id, ReportDTO reportDTO) {
		log.debug("update report");
		// initialize logic
		val val = reportDTO.getLogic();
		// check id exist
		if (val.isValidId(id))
			return Response.status(404).build();
		// handling data
		val.handleData().update();
		return Response.ok(val.getMessage()).build();
	}

}
