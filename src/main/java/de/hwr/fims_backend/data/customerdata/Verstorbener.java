package de.hwr.fims_backend.data.customerdata;

/**	Zweck: Diese Klasse "Verstorbener" ist das Hauptobjekt des Gesch�ftsprozesses. Alle Daten, die f�r Abmelde-
 * 	  	   prozesse relevant sind, werden hier dargestellt.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  				   zuletzt bearbeitet am 22.08.2018	
 */

import java.io.Serializable;
import java.util.Date;

public class Verstorbener extends Person implements Serializable{

	private static final long serialVersionUID = 1408201802L;
	
	private String gebName;			//Geburtsname - M�dchenname
	private Date gebDatum;			//Geburtsdatum
	private String gebOrt;			//Geburtsort
	private Date todDatum;			//Sterbedatum
	private String todOrt;			//Sterbeort	
	private String famStand;		//Familienstand
	private int anzTocht;			//Anzahl der T�chter
	private int anzSohn;			//Anzahl der S�hne
	private String konfes;			//Konfession	ev. | kath. | neuapostolisch | - | sonstiges 
	private String krankKas;		//Krankenkasse
	private String renteVers;		//Rentenversicherung
	
	
	


	public Verstorbener(boolean geschlecht, String name, String vorname, String plz, String ort, String strasse, String hausNr, String beruf,
	String gebName, Date gebDatum, String gebOrt, Date todDatum, String todOrt, String famStand, int anzTocht, int anzSohn,
	String konfes, String krankKas, String renteVers) {
	
		super(geschlecht, name, vorname, plz, ort, strasse, hausNr, beruf);
		this.gebName = gebName;
		this.gebDatum = gebDatum;
		this.gebOrt = gebOrt;
		this.todDatum = todDatum;
		this.todOrt = todOrt;
		this.famStand = famStand;
		this.anzTocht = anzTocht;
		this.anzSohn = anzSohn;
		this.konfes = konfes;
		this.krankKas = krankKas;
		this.renteVers = renteVers;
	}
	
	public Verstorbener() {
		super();
	}

	
	
	public void displayVerstKonsole() {
		
		super.displayPers();
		System.out.println("Geboren in: 	"+gebOrt+" am "); /*gebDatum.toString());
		System.out.println("Verstorben in:	"+todOrt+" am "+todDatum.toString()); */
		System.out.println("Familienstand: 	"+famStand);
		System.out.println("Anzahl der T�chter: "+anzTocht);
		System.out.println("Anzahl der  S�hne: "+anzSohn);
		System.out.println("Konfession: 	"+konfes);
		System.out.println("Krankenkasse: 	"+krankKas);
		System.out.println("Rentenversicherung: "+renteVers);
		
	}
	
	//GETTER UND SETTER
	//Spezielle?! Setter f�r Erleichterung der Mehrfachauswahl (Dropdownlisten) in der GUI
	
	
	 /* public void setFamStandGUI(int auswahl) {
		
		
		switch(auswahl) {
		case 1: this.setFamStand("verwitwet");
				break;
		case 2: this.setFamStand("verheiratet");
				break;
		case 3: this.setFamStand("geschieden");
				break;
		case 4: this.setFamStand("ledig");	
				break;
		}
		
	}
	*/
	
	public void setFamStandGUI(int auswahl) {
		
		String wahl = new String();
		switch(auswahl) {
		case 1: wahl = "verwitwet";
				break;
		case 2: wahl = "verheiratet";
				break;
		case 3: wahl = "geschieden";
				break;
		case 4: wahl = "ledig";
				break;		
		}
		this.setFamStand(wahl);
		
	}
	
	
	public void setKonfesGUI(int auswahl, String sonstiges) {
		
		String wahl = new String();
		switch(auswahl) {
		case 1: wahl = " - ";
				break;
		case 2:	wahl = "evangelisch";
				break;
		case 3: wahl = "katholisch";
				break;
		case 4: wahl = " neuapostolisch ";
				break;
		case 5: wahl = sonstiges;
				break;
		}
		
		this.setKonfes(wahl);
	}
	
	/* public void setKrankKasGUI(int auswahl) {
		
		switch(auswahl) {
		case 1: this.setKrankKas("AOK");
				break;
		case 2: this.setKrankKas("Bama");
				break;
		case 3: this.setKrankKas("BKK");
				break;
		case 4: this.setKrankKas("DAK");
				break;
		case 5: this.setKrankKas("IKK");
				break;
		case 6: this.setKrankKas("KKH");
				break;
		case 7: this.setKrankKas("KKH Allianz");
				break;
		case 8: this.setKrankKas("Knappschaft");
				break;
		case 9: this.setKrankKas("TK");
				break;
		case 10: this.setKrankKas("R+V BKK");
				break;
		}
		
	}
	*/
	
	public void setKrankKasGUI(int auswahl) {
			
		String wahl = new String();
			switch(auswahl) {
			case 1: wahl = "AOK";
					break;
			case 2: wahl = "Bama";
					break;
			case 3: wahl = "BKK";
					break;
			case 4: wahl = "DAK";
					break;
			case 5: wahl = "IKK";
					break;
			case 6: wahl = "KKH";
					break;
			case 7: wahl = "KKH Allianz";
					break;
			case 8: wahl = "Knappschaft";
					break;
			case 9: wahl = "TK";
					break;
			case 10:wahl = "R+V BKK";
					break;
			}
			
			this.setKrankKas(wahl);
	}
	
	
	
		
	/* public void setRenteVerGUI(int auswahl) {
		
		switch(auswahl) {
		case 1: this.setRenteVers("Deutsche Rentenversicherung");
				break;
		case 2: this.setRenteVers("Knappschaft Badensee");
				break;
		}
	}
	
	*/

	public void setRenteVerGUI(int auswahl) {
			
		String wahl = new String();
			switch(auswahl) {
			case 1: wahl = "Deutsche Rentenversicherung";
					break;
			case 2: wahl = "Knappschaft Badensee";
					break;
			}
			
		this.setRenteVers(wahl);
	}
	
	
	
	//"normale" Getter und Setter
	
	public String getGebName() {
		return gebName;
	}

	public void setGebName(String gebName) {
		this.gebName = gebName;
	}
	
	public Date getGebDatum() {
		return gebDatum;
	}

	public void setGebDatum(Date gebDatum) {
		this.gebDatum = gebDatum;
	}

	
	
	public String getGebOrt() {
		return gebOrt;
	}

	public void setGebOrt(String gebOrt) {
		this.gebOrt = gebOrt;
	}

	
	
	public Date getTodDatum() {
		return todDatum;
	}

	public void setTodDatum(Date todDatum) {
		this.todDatum = todDatum;
	}

	
	
	public String getTodOrt() {
		return todOrt;
	}

	public void setTodOrt(String todOrt) {
		this.todOrt = todOrt;
	}

	
	
	public String getFamStand() {
		return famStand;
	}

	public void setFamStand(String famStand) {
		this.famStand = famStand;
	}

	
	
	public int getAnzTocht() {
		return anzTocht;
	}

	public void setAnzTocht(int anzTocht) {
		this.anzTocht = anzTocht;
	}

	
	
	public int getAnzSohn() {
		return anzSohn;
	}

	public void setAnzSohn(int anzSohn) {
		this.anzSohn = anzSohn;
	}

	
	
	public String getKonfes() {
		return konfes;
	}

	public void setKonfes(String konfes) {
		this.konfes = konfes;
	}

	
	
	public String getKrankKas() {
		return krankKas;
	}

	public void setKrankKas(String krankKas) {
		this.krankKas = krankKas;
	}

	
	
	public String getRenteVers() {
		return renteVers;
	}

	public void setRenteVers(String renteVers) {
		this.renteVers = renteVers;
	}

	
	
	private static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
