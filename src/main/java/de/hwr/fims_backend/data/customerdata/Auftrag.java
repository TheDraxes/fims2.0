package de.hwr.fims_backend.data.customerdata;

/**	Zweck: Diese Klasse "Auftrag" ist das Kernobjekt der Bestattungsdienstleistung. Sie dient als oberstes,
 * 		   zusammenfassendes Glied. Alle angegebenen Klassen und Daten werden hier zu einem (Bestattungs)auftrag
 * 		   zusammengefasst und somit miteinander in Verbindung gesetzt.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  			       zuletzt bearbeitet am 18.08.2018
 */

import java.io.Serializable;
import java.util.Date;

import de.hwr.fims_backend.administration.Angehoerige;
import de.hwr.fims_backend.administration.Zeitungsanzeigen;
import de.hwr.fims_backend.data.customerdata.*;
import de.hwr.fims_backend.data.services.*;

/*
 * nachtr�glich erg�nzt von Nguyen Tien Dung Otten
 */

public class Auftrag implements Serializable{
	
	private static final long serialVersionUID = 99915062018L;
	
	private long auftragNr;					//Auftragsnummer *
	private String niederL;					//Niederlassung
	private Date rechnDatum;				//Datum der Rechnungslegung
	private Date zahlDatum;					//Datum des Zahlungseingangs
	
	private Trauerfeier trauerfeier;
	private Abholung abholung;
	private Auftraggeber auftraggeber;
	private Angehoerige angehoerige;
	private Verstorbener verstorbener;
	private Zeitungsanzeigen anzeigen;
	
	/*
	 * nachtr�glich erg�nzt von Nguyen Tien Dung Otten
	 */
	
	private Angehoeriger angehoeriger;
	
	
	public Auftrag(long auftragNr, String niederL, Date rechnDatum, Date zahlDatum, Trauerfeier trauerfeier, Abholung abholung, Auftraggeber auftraggeber,
					Angehoerige angehoerige, Verstorbener verstorbener, Zeitungsanzeigen anzeigen) {
		
		this.auftragNr = auftragNr;
		this.niederL = niederL;
		this.rechnDatum = rechnDatum;
		this.zahlDatum = zahlDatum;
		this.trauerfeier = trauerfeier;
		this.abholung = abholung;
		this.auftraggeber = auftraggeber;
		this.angehoerige = angehoerige;
		this.verstorbener = verstorbener;
		this.anzeigen = anzeigen;
	}
	
	public Auftrag() {
		
	}
	
	
	//Getter & Setter
	public long getAuftragNr() {
		return auftragNr;
	}
	public void setAuftragNr(long auftragNr) {
		this.auftragNr = auftragNr;
	}
	public Date getRechnDatum() {
		return rechnDatum;
	}
	public void setRechnDatum(Date rechnDatum) {
		this.rechnDatum = rechnDatum;
	}
	public Date getZahlDatum() {
		return zahlDatum;
	}
	public void setZahlDatum(Date zahlDatum) {
		this.zahlDatum = zahlDatum;
	}
	public Trauerfeier getTrauerfeier() {
		return trauerfeier;
	}
	public void setTrauerfeier(Trauerfeier trauerfeier) {
		this.trauerfeier = trauerfeier;
	}
	public Abholung getAbholung() {
		return abholung;
	}
	public void setAbholung(Abholung abholung) {
		this.abholung = abholung;
	}
	public Auftraggeber getAuftraggeber() {
		return auftraggeber;
	}
	public void setAuftraggeber(Auftraggeber auftraggeber) {
		this.auftraggeber = auftraggeber;
	}
	public Angehoerige getAngehoerige() {
		return angehoerige;
	}
	public void setAngehoerige(Angehoerige angehoerige) {
		this.angehoerige = angehoerige;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Verstorbener getVerstorbener() {
		return verstorbener;
	}
	public void setVerstorbener(Verstorbener verstorbener) {
		this.verstorbener = verstorbener;
	}

	public Zeitungsanzeigen getAnzeigen() {
		return anzeigen;
	}

	public void setAnzeigen(Zeitungsanzeigen anzeigen) {
		this.anzeigen = anzeigen;
	}

	public String getNiederL() {
		return niederL;
	}

	public void setNiederL(String niederL) {
		this.niederL = niederL;
	}

	public Angehoeriger getAngehoeriger() {
		return angehoeriger;
	}

	public void setAngehoeriger(Angehoeriger angehoeriger) {
		this.angehoeriger = angehoeriger;
	}

}


