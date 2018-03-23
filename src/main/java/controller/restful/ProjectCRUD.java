package controller.restful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import common.util.Format;
import model.business.ProjectModel;
import model.entity.Project;
import model.repo.ProjectRepo;

@Path("/project")
public class ProjectCRUD {

	private ProjectRepo projectRepo;

	public ProjectCRUD() {
		projectRepo = new ProjectRepo();
	}

	@GET
	@Path("get-all")
	@Produces("application/json")
	public String getAll() {
		List<Object> list = new ArrayList<>();
		for (Project p : projectRepo.getAll()) {
			HashMap<String, Object> project = new HashMap<>();
			project.put("id", p.getId());
			project.put("code", p.getProjectCode());
			project.put("name", p.getName());
			project.put("startAt", p.getStartAt());
			project.put("finishAt", p.getFinishAt());
			list.add(project);
		}
		return Format.toJson(list);
	}

	@POST
	@Path("add")
	@Produces("application/json")
	public String insert(@FormParam("txt_projectCode") String projectCode, @FormParam("txt_name") String name,
			@FormParam("txt_startAt") String startAt, @FormParam("txt_finishAt") String finishAt) {

		// builder a new ProjectModel
		ProjectModel projectModel = ProjectModel.builder().projectCode(projectCode).name(name).startAt(startAt)
				.finishAt(finishAt).build();

		// validate form
		if (projectModel.validate())
			return Format.toJson(
					"projectCode=" + projectCode + ",name=" + name + ",startAt=" + startAt + ",finishAt=" + finishAt);

		return Format.toJson(projectModel.getErrorMap());
	}

	@POST
	@Path("edit/{id: [0-9]}")
	@Produces("application/json")
	public String update(@PathParam("id") int id, @FormParam("txt_projectCode") String projectCode,
			@FormParam("txt_name") String name, @FormParam("txt_startAt") String startAt,
			@FormParam("txt_finishAt") String finishAt) {

		// check id exist
		if (ProjectModel.checkId(id))
			return "";

		// builder a new ProjectModel
		ProjectModel projectModel = ProjectModel.builder().id(id).projectCode(projectCode).name(name).startAt(startAt)
				.finishAt(finishAt).build();

		// validate form
		if (projectModel.validate()) {

			// push to database
			projectModel.push();

			return Format.toJson(
					"projectCode=" + projectCode + ",name=" + name + ",startAt=" + startAt + ",finishAt=" + finishAt);
		}

		return Format.toJson(projectModel.getErrorMap());
	}

	@GET
	@Path("del/{id: [0-9]}")
	@Produces("text/html")
	public String delete(@PathParam("id") int id) {

		return "id=" + id;
	}

}
