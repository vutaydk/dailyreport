package controller.service.rights;

import model.entity.Rights;

public class RightsConverter {

	public Rights fromDtoToEntity(RightsDTO dto) {
		Rights e = new Rights();
		e.setName(dto.getName());
		e.setLevel(dto.getLevel());
		return e;
	}

	public RightsJSON fromEntityToJSON(Rights e) {
		RightsJSON json = new RightsJSON();
		json.setId(e.getId());
		json.setName(e.getName());
		json.setLevel(e.getLevel());
		return json;
	}
}
