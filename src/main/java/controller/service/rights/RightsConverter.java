package controller.service.rights;

import model.entity.Rights;

public class RightsConverter {
	public static Rights fromDtoToEntity(RightsDTO dto) {
		Rights en = new Rights();
		en.setName(dto.getName());
		en.setLevel(dto.getLevel());
		return en;
	}

	public static RightsJSON fromEntityToJSON(Rights en) {
		RightsJSON dto = new RightsJSON();
		dto.setId(en.getId());
		dto.setName(en.getName());
		dto.setLevel(en.getLevel());
		return dto;
	}
}
