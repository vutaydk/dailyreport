package controller.service.rights.logic;

import model.entity.Rights;

public class RightsConverter {

	public Rights fromAddDtoToEntity(RightsDTO dto) {
		Rights e = new Rights();
		e.setName(dto.getName());
		e.setLevel(dto.getLevel());
		return e;
	}

	public Rights fromEditDtoToEntity(RightsDTO dto, int id) {
		Rights e = new Rights();
		e.setId(id);
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
