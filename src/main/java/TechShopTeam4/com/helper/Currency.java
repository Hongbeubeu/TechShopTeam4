package TechShopTeam4.com.helper;

import java.text.NumberFormat;
import java.util.Locale;

public class Currency {
	public static String formatcurrency(int cur) {
		Locale localeVN = new Locale("vi", "VN");
	    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	    String vnd = currencyVN.format(cur);
	    return vnd;
	}
}
