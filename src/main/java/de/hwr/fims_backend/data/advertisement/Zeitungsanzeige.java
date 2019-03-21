package de.hwr.fims_backend.data.advertisement;

/**	Zweck: Diese Klasse dient der Darstellung der allgemeinen Daten zur Zeitungsanzeige. Der Inhalt wird aufgrund
 * 		   der hohen Zeichenl�nge in diesem Programm nicht dargestellt. Dieser wird vom Unternehmen lediglich
 * 		   als Kopie der tats�chlichen Anzeige der Zeitung aufbewahrt.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  				   zuletzt ge�ndert am 23.08.2018
 */

import java.io.Serializable;

public class Zeitungsanzeige implements Serializable{

	private static final long serialVersionUID = 1608201806L;
	
	
	private double preis;
	private String art;
	private String groesse;
	private String zeitung;
	
	
	public Zeitungsanzeige() {
		
	}
	
	public Zeitungsanzeige(double preis, String art, String groesse, String zeitung) {
		
		this.preis = preis;
		this.art = art;
		this.groesse = groesse;
		this.zeitung = zeitung;
		
	}
	
	
	//GETTER UND SETTER
	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public void setArtGUI(int auswahl) {
			
			String wahl = new String();
			switch(auswahl) {
			case 1: wahl = "Traueranzeige";
					break;
			case 2: wahl = "Danksagung";
					break;	
			}
			this.setArt(wahl);
			
	}
	
	
	public String getGroesse() {
		return groesse;
	}

	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}

	public String getZeitung() {
		return zeitung;
	}

	public void setZeitung(String zeitung) {
		this.zeitung = zeitung;
	}

	
	public void setZeitungGUI(int auswahl) {
			//SK = Saalekreis, BLK = Burgenlandkreis
			
			String wahl = new String();
			switch(auswahl) {
			case 1: wahl = "Mitteldeutsche Zeitung SK";
					break;
			case 2: wahl = "Mitteldeutsche Zeitung + Supersonntag SK";
					break;
			case 3: wahl = "Mitteldeutsche Zeitung + Wochenspiegel SK";
					break;
			case 4:	wahl = "Mitteldeutsche Zeitung BLK";
					break;	
			case 5:	wahl = "Mitteldeutsche Zeitung + Supersonntag BLK";
					break;
			case 6: wahl = "Mitteldeutsche Zeitung + Wochenspiegel BLK";
					break;
			}
			this.setZeitung(wahl);
		
	}
	
	
	private static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
