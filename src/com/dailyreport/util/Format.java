package com.dailyreport.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Format {

	public static void main(String[] args) {

	}

	public static String timeFb(Date date) {

		Calendar calCurrent = Calendar.getInstance();
		calCurrent.setTime(new Date());

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		long min = (calCurrent.getTimeInMillis() - cal.getTimeInMillis()) / 1000 / 60;

		if (min < 1) {
			return "Vừa xong";
		}

		if (min >= 1 && min < 60) {
			return min + " phút trước";
		}

		if (min >= 60 && min < 1440) {
			return (min / 60) + " giờ trước";
		}

		if (min >= 1440 && min < 2880) {
			return "Hôm qua";
		}

		if (min >= 2880 && min < 10080) {
			return (min / 1440) + " ngày trước";
		}

		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}

}
