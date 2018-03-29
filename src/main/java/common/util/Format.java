package common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;

public class Format {

	/**
	 * Convert an Object to Json type
	 * 
	 * @param object
	 * @return String
	 */
	public static String toJson(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	/**
	 * Convert the String to Date type
	 * 
	 * @param str
	 * @return Date
	 */
	public static Date toDate(String str) {

		if (str == null)
			return null;

		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = dateFormat.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Convert an Date to String type
	 * 
	 * @param date
	 * @return String
	 */
	public static String toDate(Date date) {

		if (date == null)
			return null;

		String str = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			str = dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * Convert an Date to the nearest date type
	 * 
	 * @param date
	 * @return String
	 */
	public static String timeFb(Date date) {

		Calendar calCurrent = Calendar.getInstance();
		calCurrent.setTime(new Date());

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		long min = (calCurrent.getTimeInMillis() - cal.getTimeInMillis()) / 1000 / 60;

		if (min < 1)
			return "Vừa xong";
		else if (min >= 1 && min < 60)
			return min + " phút trước";
		else if (min >= 60 && min < 1440)
			return (min / 60) + " giờ trước";
		else if (min >= 1440 && min < 2880)
			return "Hôm qua";
		else if (min >= 2880 && min < 10080)
			return (min / 1440) + " ngày trước";

		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}

}
