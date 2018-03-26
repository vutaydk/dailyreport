package controller.restful;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import common.util.Format;
import model.business.rights.RightsEntity;
import model.business.rights.RightsLogic;

@Path("/rights")
public class RightsService {

	@GET
	@Path("get-all")
	@Produces("application/json")
	public String getAll() {
		return Format.toJson(RightsLogic.getJson());
	}

	@POST
	@Path("add")
	@Produces("application/json")
	public String insert(@FormParam("txt_name") String name, @FormParam("txt_level") Integer level) {

		// builder a new RightsLogic
		RightsLogic rightsLogic = RightsEntity.builder().name(name).level(level).build().getLogic();

		// validation form
		if (rightsLogic.isValidData()) {

			// add to database
			rightsLogic.add();

			return Format.toJson(rightsLogic.toString());
		}

		return Format.toJson(rightsLogic.getErrorMap());
	}

	@POST
	@Path("edit/{id: [0-9]+}")
	@Produces("application/json")
	public String update(@PathParam("id") int id, @FormParam("txt_name") String name,
			@FormParam("txt_level") Integer level) {

		// builder a new RightsLogic
		RightsLogic rightsLogic = RightsEntity.builder().id(id).name(name).level(level).build().getLogic();

		// check id exist
		if (!rightsLogic.isValidId())
			return "";

		// validation form
		if (rightsLogic.isValidData()) {

			// update to database
			rightsLogic.update();

			return Format.toJson(rightsLogic.toString());
		}

		return Format.toJson(rightsLogic.getErrorMap());
	}

}
