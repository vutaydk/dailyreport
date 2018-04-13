package controller.service.rights;

import model.entity.Rights;

public class RightsConverter {
	public static Rights fromDtoToEntity(RightsDTO dto) {
		Rights en = new Rights();
		en.setName(dto.getName());
		en.setLevel(dto.getLevel());
		return en;
	}

	public static RightsDTO fromEntityToDto(Rights en) {
		RightsDTO dto = new RightsDTO(en.getName(), en.getLevel());
		return dto;
	}
}
