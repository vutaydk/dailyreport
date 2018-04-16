package controller.service.i18n;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.i18n.IResourceInitializer;
import common.i18n.ResourceDto;

@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
public class SystemResourceService {

	@Inject
	private IResourceInitializer systemResource;

	@GET
	@Path("/get-all")
	public String getAll() {
		ObjectMapper mapper = new ObjectMapper();
		String resourceBundle;
		try {
			resourceBundle = mapper.writeValueAsString(systemResource.getResource());
		} catch (JsonProcessingException e) {
			resourceBundle = "{}";
		}
		return "var resources =" + resourceBundle;
	}
}
