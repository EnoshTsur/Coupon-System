package sidekicks;

import java.util.Calendar;
import java.util.Date;

public class DateMaker {

	public static Date setDate(int year, int month, int day)
	{
		Calendar cal = Calendar.getInstance();
	
		Date date = new Date();
		
		cal.set(year, month, day);
		
		date = cal.getTime();
		
		return date;
	}
}
