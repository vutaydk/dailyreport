package controller.service.user;

import model.entity.User;

public class UserConverter {

	public User fromDtoToEntity(UserDTO dto) {
		User e = new User();
		e.setEmployeeCode(dto.getEmployeeCode());
		e.setPassword(dto.getPassword());
		e.setEmail(dto.getEmail());
		e.setName(dto.getName());
		e.setRights(dto.getRights());
		return e;
	}

	public UserJSON fromEntityToJSON(User e) {
		UserJSON json = new UserJSON();
		json.setEmployeeCode(e.getEmployeeCode());
		json.setEmail(e.getEmail());
		json.setName(e.getName());
		json.setRights(e.getRights());
		return json;
	}
}
