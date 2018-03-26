package controller.restful;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import common.util.Format;
import lombok.val;
import model.business.project.ProjectEntity;
import model.business.project.ProjectLogic;

@Path("/project")
public class ProjectService {

	@GET
	@Path("get-all")
	@Produces("application/json")
	public String getAll() {
		return Format.toJson(ProjectLogic.getJson());
	}

	@POST
	@Path("add")
	@Produces("application/json")
	public String insert(@FormParam("txt_projectCode") String projectCode, @FormParam("txt_name") String name,
			@FormParam("txt_startAt") String startAt, @FormParam("txt_finishAt") String finishAt) {

		// builder a new ProjectLogic
		ProjectLogic projectLogic = ProjectEntity.builder().projectCode(projectCode).name(name).startAt(startAt)
				.finishAt(finishAt).build().getLogic();

		// validation form
		if (projectLogic.isValidData()) {

			// add to database
			projectLogic.add();

			return Format.toJson(projectLogic.toString());
		}

		return Format.toJson(projectLogic.getErrorMap());
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	@Produces("application/json")
	public String update(@PathParam("id") int id, @FormParam("txt_projectCode") String projectCode,
			@FormParam("txt_name") String name, @FormParam("txt_startAt") String startAt,
			@FormParam("txt_finishAt") String finishAt) {

		// builder a new ProjectLogic
		val projectLogic = ProjectEntity.builder().id(id).projectCode(projectCode).name(name).startAt(startAt)
				.finishAt(finishAt).build().getLogic();

		// check id exist
		if (!projectLogic.isValidId())
			return "";

		// validation form
		if (projectLogic.isValidData()) {

			// update to database
			projectLogic.update();

			return Format.toJson(projectLogic.toString());
		}

		return Format.toJson(projectLogic.getErrorMap());
	}

}
