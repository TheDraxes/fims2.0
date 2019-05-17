package de.hwr.fims_gui.sfv.roadmap;

import java.time.LocalDate;
import java.util.Date;

public class ConvertHelper {

	public static String extractHNR(String strass_hnr) {
		if(strass_hnr.contains(",")) {
			int index = strass_hnr.indexOf(",");
			return strass_hnr.substring(index + 1).trim();
		}
		return "";
	}
	
	public static String extractStreet(String strass_hnr) {
		if(strass_hnr.contains(",")) {
			return strass_hnr.substring(0, strass_hnr.indexOf(","));
		} else {
			return "";
		}
	}
	
	public static Date convertVaadinDate(LocalDate wann) {
		Date date = new Date();
		date.setYear(wann.getYear());
		date.setMonth(wann.getMonthValue());
		date.setDate(wann.getDayOfMonth());
		
		return date;
	}
}
