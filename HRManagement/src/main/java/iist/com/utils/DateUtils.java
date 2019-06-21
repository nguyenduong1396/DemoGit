package iist.com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	static SimpleDateFormat dateformatter = new SimpleDateFormat(Constrant.DATE);
	
	static SimpleDateFormat datetimeformatter = new SimpleDateFormat(Constrant.DATE_TIME);
	
	public static Date parseToDate(String dateString) throws ParseException {
		Date date = dateformatter.parse(dateString);
		return date;
	}
	
	public static Date parseToDateTime(String dateString) throws ParseException {
		Date date = datetimeformatter.parse(dateString);
		return date;
	}
	
	public static String convertDateToString(Date date) {
		String dateString = dateformatter.format(date);
		return dateString;
	}
	
	public static String convertDateTimeToString(Date date) {
		String dateString = datetimeformatter.format(date);
		return dateString;
	}
}
