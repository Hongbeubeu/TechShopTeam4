package TechShopTeam4.com.helper;

import java.util.Date;

public class DateTime {
	public static int setDateToInt() {
		return (int)(new Date().getTime()/1000);
	}
	
	public static Date setIntToDate(int date) {
		return new Date(((long)date)*1000);
	}
}
