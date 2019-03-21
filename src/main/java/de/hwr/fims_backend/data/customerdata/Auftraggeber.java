package de.hwr.fims_backend.data.customerdata;

/**	Zweck: Diese Klasse dient der Darstellung des Auftraggebers eines (Bestattungs)auftrags. Bei dieser Person
 * 		   handelt es sich um die einzige bevollm�chtigte Person im Zusammenhang mit der Trauerfeier, ist somit
 * 		   f�r den Anwender die vorstehende Ansprechperson, die ebenfalls am Auftragsende die Rechnung 
 * 		   entgegennimmt.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *                                  zuletzt bearbeitet am 20.08.2018
 */

import java.io.Serializable;

public class Auftraggeber extends Angehoeriger implements Serializable{

	
	private static final long serialVersionUID = 1408201804L;
	
	private String telefonNr;	//Telefonnummer
	
	
	
	public Auftraggeber(boolean geschlecht, String name, String vorname, String plz, String ort, String strasse, String hausNr,
			String beruf, String bezArt, String telefonNr) {
		
		super(geschlecht, name, vorname, plz, ort, strasse, hausNr, beruf, bezArt);
		this.telefonNr = telefonNr;
	}

	
	public Auftraggeber() {
		super();
	}
	
	
	
	
	public String getTelefonNr() {
		return telefonNr;
	}

	public void setTelefonNr(String telefonNr) {
		this.telefonNr = telefonNr;
	}
	
	
	
}
