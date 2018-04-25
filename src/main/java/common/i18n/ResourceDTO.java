package common.i18n;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, String> messages;
	private Map<String, String> labels;

	public ResourceDTO() {
		messages = new HashMap<>();
		labels = new HashMap<>();
	}
}