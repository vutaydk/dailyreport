package controller.restful;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import common.util.Format;
import model.business.report.ReportDTO;
import model.business.report.ReportLogic;

@Path("/report")
public class ReportService {

	@GET
	@Path("get-all")
	@Produces("application/json")
	public String getAll() {
		return Format.toJson(ReportLogic.getJson());
	}

	@POST
	@Path("add")
	@Produces("application/json")
	public String insert(@FormParam("txt_projectCode") Integer projectId, @FormParam("txt_name") Integer taskId,
			@FormParam("txt_startAt") Integer timeWorked, @FormParam("txt_finishAt") String note) {

		// builder a new ReportLogic
		ReportLogic reportLogic = ReportDTO.builder().projectId(projectId).taskId(taskId).timeWorked(timeWorked)
				.note(note).build().getLogic();

		// validation form
		if (reportLogic.isValidData()) {

			// add to database
			reportLogic.add();

			return Format.toJson(reportLogic.toString());
		}

		return Format.toJson(reportLogic.getErrorMap());
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	@Produces("application/json")
	public String update(@PathParam("id") int id, @FormParam("txt_projectCode") Integer projectId,
			@FormParam("txt_name") Integer taskId, @FormParam("txt_startAt") Integer timeWorked,
			@FormParam("txt_finishAt") String note) {

		// builder a new ReportLogic
		ReportLogic reportLogic = ReportDTO.builder().id(id).projectId(projectId).taskId(taskId).timeWorked(timeWorked)
				.note(note).build().getLogic();

		// check id exist
		if (reportLogic.isValidId())
			return "";

		// validation form
		if (reportLogic.isValidData()) {

			// update to database
			reportLogic.update();

			return Format.toJson(reportLogic.toString());
		}

		return Format.toJson(reportLogic.getErrorMap());
	}

}
