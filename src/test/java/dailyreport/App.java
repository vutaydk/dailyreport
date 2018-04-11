package dailyreport;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import model.business.project.ProjectDTO;

public class App {

	public static void main(String[] args) {
		ProjectDTO dto = new ProjectDTO();
		dto.setProjectCode("");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		displayViolationsIfAny(validator.validate(dto));
	}

	public static <T> void displayViolationsIfAny(Set<ConstraintViolation<T>> violations) {
		if (violations.isEmpty()) {
			System.out.println("ProjectDTO information is valid");
			return;
		}
		System.out.println("ProjectDTO information is invalid");
		System.out.println(violations);
		for (ConstraintViolation<T> violation : violations) {
			System.out.println(" --- " + violation.getPropertyPath());
			System.out.println(" --- " + violation.getMessage());
		}
	}
}
