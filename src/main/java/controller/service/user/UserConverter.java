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
		UserJSON uj = new UserJSON();
		uj.setEmployeeCode(e.getEmployeeCode());
		uj.setEmail(e.getEmail());
		uj.setName(e.getName());
		uj.setRights(e.getRights());
		return uj;
	}
}
