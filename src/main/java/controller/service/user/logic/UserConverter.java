package controller.service.user.logic;

import model.entity.User;

public class UserConverter {

	public User fromAddDtoToEntity(UserDTO dto) {
		User e = new User();
		e.setEmployeeCode(dto.getEmployeeCode());
		e.setPassword(dto.getPassword());
		e.setEmail(dto.getEmail());
		e.setName(dto.getName());
		e.setRights(dto.getRights());
		return e;
	}

	public User fromEditDtoToEntity(UserDTO dto, int id) {
		User e = new User();
		e.setId(id);
		e.setEmployeeCode(dto.getEmployeeCode());
		e.setPassword(dto.getPassword());
		e.setEmail(dto.getEmail());
		e.setName(dto.getName());
		e.setRights(dto.getRights());
		return e;
	}

	public UserJSON fromEntityToJSON(User e) {
		UserJSON uj = new UserJSON();
		uj.setId(e.getId());
		uj.setEmployeeCode(e.getEmployeeCode());
		uj.setEmail(e.getEmail());
		uj.setName(e.getName());
		uj.setRights(e.getRights());
		return uj;
	}
}
