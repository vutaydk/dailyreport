package common.i18n;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceDto {
	private Map<String, String> messages;
	private Map<String, String> labels;

	public ResourceDto() {
		messages = new HashMap<>();
		labels = new HashMap<>();
	}
}