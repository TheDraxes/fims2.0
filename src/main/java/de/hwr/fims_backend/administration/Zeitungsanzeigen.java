package de.hwr.fims_backend.administration;

/**	Zweck: Diese Klasse dient der Zusammenfassung der vom Auftraggeber gew�nschten Zeitungsanzeigen in dieser
 * 		   Verwaltungsklasse in Form einer ArrayList.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  			       zuletzt ge�ndert am 22.08.2018
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import de.hwr.fims_backend.data.advertisement.Zeitungsanzeige;


public class Zeitungsanzeigen implements Serializable{

	private static final long serialVersionUID = 1608201807L;
	
	
	private ArrayList<Zeitungsanzeige> liste;
	static int anzahl;
	
	public Zeitungsanzeigen() {
	
		this.liste = new ArrayList<Zeitungsanzeige>();
		anzahl = 0;
		
	}
	
	
	
	//Zeitungsanzeige der Liste hinzuf�gen
		public void addElement(Zeitungsanzeige anzei) {
		
			liste.add(anzei);
			anzahl++;
			
		}
		
		
		//Anzeige aus der Liste holen
		public Zeitungsanzeige getElement(int index){
				
			return liste.get(index);
			
		}
		
		
		//Anzeige aus der Liste entfernen
		public void deleteElement(Zeitungsanzeige anzei) {
			
			if(liste.contains(anzei)) {
			liste.remove(anzei);
			anzahl--;
			}
			else {
				System.out.println("Die Zeitungsanzeige existiert nicht.");
			}
			
		}
		
		
		
		//Methode ohne R�ckgabewert (void), die die Inhalte der Liste auf der Konsole ausgeben.
		public void auslesen() {
			
			Iterator<Zeitungsanzeige> it = liste.iterator();
			while(it.hasNext()) {
				System.out.println(it.next().toString());
			}
			System.out.print("\n\n");
			
		}
	
	public ArrayList<Zeitungsanzeige> getListe() {
		return liste;
	}
	public void setListe(ArrayList<Zeitungsanzeige> liste) {
		this.liste = liste;
	}
	public static int getAnzahl() {
		return anzahl;
	}
	public static void setAnzahl(int anzahl) {
		Zeitungsanzeigen.anzahl = anzahl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
