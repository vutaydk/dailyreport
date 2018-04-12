package controller.service.rights;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RightsDTO {

	private String name;
	private Integer level;

	public void isValidData() {

	}
}
