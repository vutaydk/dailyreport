package controller.service.rights.business;

import model.entity.Rights;

public class RightsConverter {

	public Rights fromDtoToEntity(RightsDTO dto) {
		Rights e = new Rights();
		e.setName(dto.getName());
		e.setLevel(dto.getLevel());
		return e;
	}

	public RightsJSON fromEntityToJSON(Rights e) {
		RightsJSON rj = new RightsJSON();
		rj.setId(e.getId());
		rj.setName(e.getName());
		rj.setLevel(e.getLevel());
		return rj;
	}
}
