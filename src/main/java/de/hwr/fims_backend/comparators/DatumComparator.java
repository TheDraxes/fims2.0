package de.hwr.fims_backend.comparators;

/**	Zweck: Diese Klasse dient dem Sortieren des Auftragsarrays in der Auftragsverwaltung nach Datum 
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  				   zuletzt bearbeitet am 16.08.2018
 */

import java.util.Comparator;
import java.util.Date;

import de.hwr.fims_backend.data.customerdata.Auftrag;




public class DatumComparator implements Comparator<Auftrag>{

	
	
	public int compare(Auftrag a, Auftrag b) {
		
		Date dateA = a.getVerstorbener().getTodDatum();
		Date dateB = b.getVerstorbener().getTodDatum();
		
		if(dateA.getYear()==dateB.getYear() && dateA.getMonth() == dateB.getMonth() && dateA.getDay() == dateB.getDay()) {
			return 0;
		}
		if(dateA.getYear() < dateB.getYear()) {
			return -1;
		}
		if(dateA.getYear() > dateB.getYear()) {
			return 1;
		}
		if(dateA.getMonth() < dateB.getMonth()) {
			return -1;
		}
		if(dateA.getMonth() > dateB.getMonth()) {
			return 1;
		}
		if(dateA.getDay() < dateB.getDay()) {
			return -1;
		}
		else {
			return 0;
		}
		
	}
	
}
