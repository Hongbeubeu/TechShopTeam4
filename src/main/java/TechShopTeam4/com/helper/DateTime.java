package TechShopTeam4.com.helper;

import java.util.Date;

public class DateTime {
	
	public static Date getDate() {
		return new Date();
	}
	public static int setDateToInt() {
		return (int)(new Date().getTime()/1000);
	}
	
	public static Date setIntToDate(int date) {
		return new Date(((long)date)*1000);
	}
	
	public static int getMonth(Date date) {
		return date.getMonth() + 1;
	}
	
	public static int getYear(Date date) {
		return date.getYear() + 1900;
	}
}
