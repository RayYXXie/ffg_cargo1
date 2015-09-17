package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

	public static String getFormatNowTime(String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = new Date();
		String dateStr = sdf.format(date);
		return dateStr;

	}
	
	
	public static void main(String args []){
		
		System.out.println(DateFormatUtil.getFormatNowTime("yyyy-MM-DD hh:MM:ss"));
	}
}
