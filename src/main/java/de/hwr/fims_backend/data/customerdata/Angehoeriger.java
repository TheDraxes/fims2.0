package de.hwr.fims_backend.data.customerdata;

/**	Zweck: Diese Klasse dient der Darstellung der Familienangeh�rigen der verstorbenen Person. Diese Daten sind
 * 		   besonders f�r Abmeldungsprozesse, sowie Angaben f�r das Standesamt relevant.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *                     zuletzt bearbeitet am 20.08.2018
 */

import java.io.Serializable;

public class Angehoeriger extends Person implements Serializable{

	private static final long serialVersionUID = 1408201803L;
	private String bezArt;
	
	
	
	public Angehoeriger(boolean geschlecht, String name, String vorname, String plz, String ort, String strasse, String hausNr, String beruf, String bezArt) {
		
		super(geschlecht, name, vorname, plz, ort, strasse, hausNr, beruf);
		this. bezArt = bezArt;
	
	}
	
	public Angehoeriger() {
		super();
	}


	
	//M�glichkeit f�r die GUI, Beziehungsart auszuw�hlen
	
	public void setBezArtGUI(int auswahl, String sonstiges) {
		
		boolean geschlecht = this.isGeschlecht();
		
		String wahl = new String();
		
		switch(auswahl) {
		case 1: if(geschlecht == true) {
					wahl ="Ehefrau";
				}
				else {
					wahl = "Ehemann";
				}
			break;
		case 2:	if(geschlecht == true) {
					wahl = "Tochter";
				}
		
				else {
					wahl = "Sohn";
				}	
			break;
		case 3:	if(geschlecht == true) {
					wahl = "Mutter";
				}
		
				else {
					wahl = "Vater";
				}
			break;
		case 4: if(geschlecht == true) {
					wahl = "Schwester";
				}
		
				else {
					wahl = "Bruder";
				}
			break;
		case 5: wahl = sonstiges;
			break;
		}
		
		this.setBezArt(wahl);
	}
	
	
	
	
	public String getBezArt() {
		return bezArt;
	}


	public void setBezArt(String bezArt) {
		this.bezArt = bezArt;
	}
	
	
	
}
