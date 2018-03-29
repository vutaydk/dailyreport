package common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {

	private final static String DATE_FORMAT = "dd/MM/yyyy";
	private final static Pattern NUMBER_PATTERN = Pattern.compile("[+-]?\\d*\\.?\\d+");

	/**
	 * Check the String must be Date type
	 * 
	 * @param date
	 * @return boolean
	 */
	public static boolean isValidDate(String date) {
		try {
			if (date == null)
				throw new RuntimeException("Can't format Date with value: " + date);

			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Check the String must be a number
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isNumber(String input) {
		Matcher m = NUMBER_PATTERN.matcher(input);
		return m.matches();
	}

}
