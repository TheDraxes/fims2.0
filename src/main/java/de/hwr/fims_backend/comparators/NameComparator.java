package de.hwr.fims_backend.comparators;

/**	Zweck: Diese Klasse dient dem Sortieren des Auftragsarrays in der Auftragsverwaltung nach Namen der Verstorbenen 
 *  Person in alphabetischer Reihenfolge.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  			       zuletzt bearbeitet am 16.08.2018
 */


import java.util.Comparator;

import de.hwr.fims_backend.data.customerdata.Auftrag;

public class NameComparator implements Comparator<Auftrag>{

	@Override
	public int compare(Auftrag a, Auftrag b) {
		
		int pruefen = a.getVerstorbener().getName().compareTo(b.getVerstorbener().getName());
		if(pruefen !=0) {
			return pruefen;
		}
		//Falls der gleiche Nachname besteht: Nach dem Vornamen pruefen!
		else {
			return a.getVerstorbener().getVorname().compareTo(b.getVerstorbener().getVorname());
		}
		
	}
	
}
