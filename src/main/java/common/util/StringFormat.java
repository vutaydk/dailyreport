package common.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormat {

	public static String formatErrMessage(String message, List<String> params) {
		Pattern pattern = Pattern.compile("(\\{\\d+\\})");
		Matcher matcher = pattern.matcher(message);
		while (matcher.find()) {
			String stringIndex = matcher.group();
			int index = getParamIndex(stringIndex);
			// if index is greater than parameter size then move on
			if (index < params.size()) {
				message = message.replace(stringIndex, params.get(index));
			}
		}
		return message;
	}

	private static int getParamIndex(String input) {
		// remove bracket
		String index = input.substring(1, input.length() - 1);
		return Integer.parseInt(index);
	}
}
