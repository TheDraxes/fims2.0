package de.hwr.fims_backend.data.services;

/**	Zweck: Diese Klasse dient der Darstellung der Kerndienstleistung eines Bestattungsunternehmens. Die Trauerfeier
 * 		   beinhaltet alle f�r den Gesch�ftsprozess relevanten Daten und stellt gleichzeitig eine Verbindung
 * 		   zwischen Blumenbestellung und (Bestattungs)auftrag her.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  				   zuletzt am 20.08.2018
 */

import java.io.Serializable;
import java.util.Date;

public class Trauerfeier implements Serializable{

	private boolean bestArt;				//Bestattungsart: true = Erdbestattung, false = Feuerbestattung
	private String grabArt;					//Art des Grabs
	private String beisetzOrt;				//Beisetzungsort
	private Date tfDatum;					//Datum der Trauerfeier
	private String redner;					//Redner	
	private String musik;					//Musik
	private boolean aufgebahrt;				//Soll der Verstorbene aufgebahrt werden?
	private String aufbahBem;				//Bemerkung zur Aufbahrung
	private String sarg;					//Artikelnummer des Sargs
	private String urne;					//Artikelnummer der Urne
	private String decke;					//Artikelnummer der Decke
	private String talar;					//Artikelnummer des Talars
	private Blumenbestellung blumen;		//Blumenbestellung
	private String sonstiges;				//Sonstige Bemerkungen
	
	
	private static final long serialVersionUID = 1806201810L;
		
	
	public Trauerfeier(boolean bestArt,String grabArt,String beisetzOrt,Date tfDatum,String redner, String musik,
			boolean aufgebahrt, String aufbahBem, String sarg, String urne, String decke, String talar, Blumenbestellung blumen,
			String sonstiges) {
		
		this.bestArt = bestArt;
		this.grabArt = grabArt;
		this.beisetzOrt = beisetzOrt;
		this.tfDatum = tfDatum;
		this.redner = redner;
		this.musik = musik;
		this.aufgebahrt = aufgebahrt;
		this.aufbahBem = aufbahBem;
		this.sarg = sarg;
		this.urne = urne;
		this.decke = decke;
		this.talar = talar;
		this.blumen = blumen;
		this.sonstiges = sonstiges;
		
		
	}
	
	public Trauerfeier() {
		
	}
	
	

	//Getter & Setter
	
	
		//Bestattungsart ( Erde = true / Feuer = false)
		public boolean isBestArt() {
			return bestArt;
		}
	
		public void setBestArt(boolean bestArt) {
			this.bestArt = bestArt;
		}
	
		
		//Art der Grabst�tte
		public String getGrabArt() {
			return grabArt;
		}
	
		public void setGrabArt(String grabArt) {
			this.grabArt = grabArt;
		}
	
		public void setGrabArtGUIErde(int auswahl) {
			
			String wahl = new String();
			switch(auswahl) {
			case 1: wahl = "Erdgrab";
					break;
			case 2: wahl = "Doppelerdengrab";
					break;		
			}
			this.setGrabArt(wahl);
		}
		
		
	public void setGrabArtGUIFeuer(int auswahl) {
			
			String wahl = new String();
			switch(auswahl) {
			case 1: wahl = "Urnengrab";
					break;
			case 2: wahl = "Doppelurnengrab";
					break;	
			case 3: wahl = "Erdgrab";
					break;
			case 4: wahl = "Doppelerdengrab";
					break;
			case 5: wahl = "Urnengemeinschaftsanlage";
					break;
			case 6: wahl = "Friedwald";
					break;
			case 7: wahl = "Oase der Ewigkeit";
					break;
			case 8: wahl = "Seebestattung";		
					break;
			case 9: wahl = "Diamant";
					break;
			}
			this.setGrabArt(wahl);
		}
		
		
		//Beisetzungsort
		public String getBeisetzOrt() {
			return beisetzOrt;
		}
	
		public void setBeisetzOrt(String beisetzOrt) {
			this.beisetzOrt = beisetzOrt;
		}
	
		private void setBeisetzOrtGUIWSF(int auswahl, String sonstiges) {
			
			String wahl = new String();
			switch(auswahl) {
			case 1: wahl = "Wei�enfels";
					break;
			case 2: wahl = "Langendorf";
					break;
			case 3: wahl = "Burgwerben";
					break;
			case 4: wahl = "Uichteritz";
					break;
			case 5: wahl = "Wengelsdorf";
					break;
			case 6: wahl = "Gro�korbetha";
					break;
			case 7: wahl = "Dehlitz";
					break;
			case 8: wahl = "Markwerben";
					break;
			case 9: wahl = "Lei�ling";
					break;
			case 10: wahl = sonstiges;
					break;
				
			}
			this.setBeisetzOrt(wahl);
		}
			
		
	public void setBeisetzOrtGUIBD(int auswahl, String sonstiges) {
				
				String wahl = new String();
				switch(auswahl) {
				case 1: wahl = "Neuer Friedhof Bad D�rrenberg";
						break;
				case 2: wahl = "Alter Friedhof Bad D�rrenberg";
						break;
				case 3: wahl = "Festa";
						break;
				case 4: wahl = "Tollwitz";
						break;
				case 5: wahl = "Schladebach";
						break;
				case 6: wahl = sonstiges;
						break;
					
				}
				this.setBeisetzOrt(wahl);
			}
	
	
		
		//Datum der Trauerfeier
		public Date getTfDatum() {
			return tfDatum;
		}
	
		public void setTfDatum(Date tfDatum) {
			this.tfDatum = tfDatum;
		}
	
		
		//Name des gew�nschten Redners
		public String getRedner() {
			return redner;
		}
	
		public void setRedner(String redner) {
			this.redner = redner;
		}
	
		
		public void setRednerGUI(int auswahl, String sonstiges) {
			
			String wahl = new String();
			switch(auswahl) {
			case 1: wahl = "Mario";
					break;
			case 2: wahl = "Riemann";
					break;
			case 3: wahl = "Fischer";
					break;
			case 4: wahl = "Wawrzetz";
					break;
			case 5: wahl = "Walter";
					break;
			case 6: wahl = "Gei�ler";
					break;
			case 7: wahl = "Rothe";
					break;
			case 8: wahl = "Nietzschke";
					break;
			case 9: wahl = "Morbitz";
					break;
			case 10: wahl = "Hoff";
					break;
			case 11: wahl = "Zander";
					break;
			case 12: wahl = sonstiges;
					break;
				
			}
			this.setRedner(wahl);
			
		}
		
		
		//Anmerkungen zur gew�nschten Musik
		public String getMusik() {
			return musik;
		}
	
		public void setMusik(String musik) {
			this.musik = musik;
		}
	
		
		//Soll der Verstorbene aufgebahrt werden?
		public boolean isAufgebahrt() {
			return aufgebahrt;
		}
	
		public void setAufbahren(boolean aufbahren) {
			this.aufgebahrt = aufbahren;
		}
	
		
		
		public String getAufbahBem() {
			return aufbahBem;
		}
	
		public void setAufbahBem(String aufbahBem) {
			this.aufbahBem = aufbahBem;
		}
	
		
		
		public String getSarg() {
			return sarg;
		}
	
		public void setSarg(String sarg) {
			this.sarg = sarg;
		}
	
		public String getUrne() {
			return urne;
		}
	
		public void setUrne(String urne) {
			this.urne = urne;
		}
	
		public String getDecke() {
			return decke;
		}
	
		public void setDecke(String decke) {
			this.decke = decke;
		}
	
		
		public void setDeckeGUI(int auswahl) {
			
			String wahl = new String();
			switch(auswahl) {
			case 1: wahl = "DE";
					break;
			case 2: wahl = "3-CH-ZZ-V";
					break;
			case 3: wahl = "3-CHS-V";
					break;
			case 4: wahl = "3-ZZ-V";
					break;
			case 5: wahl = "3-W-V";
					break;
			case 6: wahl = "3-NP-V";
					break;
				
			}
			this.setDecke(wahl);
			
		}
		
		
		
		
		public String getTalar() {
			return talar;
		}
	
		public void setTalar(String talar) {
			this.talar = talar;
		}
	
		
		public void setTalarGUIM(int auswahl) {
			
			String wahl = new String();
			switch(auswahl) {
			case 1: wahl = "eigene Kleidung";
					break;
			case 2: wahl = "Herrentalar einfach (HT)";
					break;
			case 3: wahl = "Herrentalar gehoben (GHT)";
					break;				
			}
			
			this.setTalar(wahl);
		}
		
		public void setTalarGUIW(int auswahl) {
			
			String wahl = new String();
			switch(auswahl) {
			case 1: wahl = "eigene Kleidung";
					break;
			case 2: wahl = "Damentalar einfach (DT)";
					break;
			case 3: wahl = "Damentalar gehoben (GDT)";
					break;				
			}
			
			this.setTalar(wahl);
		}
		
		
		
		public String getSonstiges() {
			return sonstiges;
		}
	
		public void setSonstiges(String sonstiges) {
			this.sonstiges = sonstiges;
		}
	
		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Blumenbestellung getBlumen() {
			return blumen;
		}

		public void setBlumen(Blumenbestellung blumen) {
			this.blumen = blumen;
		}

		public void setAufgebahrt(boolean aufgebahrt) {
			this.aufgebahrt = aufgebahrt;
		}
		
	
	
}
