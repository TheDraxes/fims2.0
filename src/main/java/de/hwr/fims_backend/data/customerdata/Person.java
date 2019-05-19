package de.hwr.fims_backend.data.customerdata;

/**	Zweck: Diese Klasse wird anders als alle anderen Klassen im Programm nicht direkt als Objekt erzeugt.
 * 		   Sie dient lediglich als abstrakte Klasse, die die Darstellung der verschiedenen gesch�ftsfall-
 * 		   relevanten Personendaten deutlich zusammenfasst und somit erleichtert.
 *  @autor Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  				   zuletzt bearbeitet am 20.08.2018
 */

import java.io.Serializable;

public abstract class Person implements Serializable{



	private static final long serialVersionUID = 1408201801L;
	
	
	private boolean geschlecht;		//Geschlecht --> false = m�nnl. | true= weibl.
	private String name;			//Name	*
	private String vorname;			//Vorname *
	private String plz;				//Postleitzahl
	private String ort;				//Ort
	private String strasse;			//Stra�e
	private String hausNr;			//Hausnummer
	private String beruf;			//Beruf

	
	
	//Konstruktor
	
	
			public Person(boolean geschlecht, String name, String vorname, String plz, String ort, String strasse, String hausNr, String beruf) {
				this.geschlecht = geschlecht;
				this.name = name;
				this.vorname = vorname;
				this.plz = plz;
				this.ort = ort;
				this.strasse = strasse;
				this.hausNr = hausNr;
				this.beruf = beruf;
			}
	
	
			public Person() {
			}


	//Display-Methode
			
		public void displayPers() {
			if(this.geschlecht == true) {
				System.out.print("Frau ");
			}
			else {
				System.out.print("Herr ");
			}
			
			System.out.println(vorname+" "+name);
			System.out.println("Anschrift: 	"+strasse+" "+hausNr);
			System.out.println("           	"+plz+" "+ort);
			System.out.println("Beruf: 		"+beruf);
		}
			
	
		
		
	//Getter & Setter
		
		//NAME
		public String getName() {
			return name;
		}
	
	
		public void setName(String name) {
			this.name = name;
		}
	
	
		
		//VORNAME
		public String getVorname() {
			return vorname;
		}
	
	
		public void setVorname(String vorname) {
			this.vorname = vorname;
		}
	
	
		
		//POSTLEITZAHL
		public String getPlz() {
			return plz;
		}
	
	
		public void setPlz(String plz) {
			this.plz = plz;
		}
	
	
		
		//ORT
		public String getOrt() {
			return ort;
		}
	
	
		public void setOrt(String ort) {
			this.ort = ort;
		}
	
	
		
		//STRASSE
		public String getStrasse() {
			return strasse;
		}
	
	
		public void setStrasse(String strasse) {
			this.strasse = strasse;
		}
	
	
		
		//HAUSNUMMER
		public String getHausNr() {
			return hausNr;
		}
	
	
		public void setHausNr(String hausNr) {
			this.hausNr = hausNr;
		}
	
	
		
		//BERUF
		public String getBeruf() {
			return beruf;
		}
	
	
		public void setBeruf(String beruf) {
			this.beruf = beruf;
		}
	
	
		
		//SERIALVERSIONUID
		private static long getSerialversionuid() {
			return serialVersionUID;
		}


		public boolean isGeschlecht() {
			return geschlecht;
		}


		public void setGeschlecht(boolean geschlecht) {
			this.geschlecht = geschlecht;
		}
		
		
		
	
}
