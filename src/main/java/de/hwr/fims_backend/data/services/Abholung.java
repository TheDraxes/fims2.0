package de.hwr.fims_backend.data.services;

/**	Zweck: Diese Klasse dient der Darstellung der Abholungsdienstleistung im Bestattungsunternehmen und ihrer f�r
 * 		   den Gesch�ftsprozess relevanten Daten.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *       		       zuletzt bearbeitet am 21.08.2018
 */

import java.io.Serializable;
import java.util.Date;


public class Abholung implements Serializable{

	private static final long serialVersionUID = 1708201810L;
	
	private String ort;
	private Date abhDat;
	boolean geschZeit;
	
	
	public Abholung(String ort, Date abhDat, boolean geschZeit) {
		this.ort = ort;
		this.abhDat = abhDat;
		this.geschZeit = geschZeit;
	}
	
	public Abholung() {
		
	}

	//GETTER UND SETTER
	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public Date getAbhDat() {
		return abhDat;
	}

	public void setAbhDat(Date abhDat) {
		this.abhDat = abhDat;
	}

	public boolean isGeschZeit() {
		return geschZeit;
	}

	public void setGeschZeit(boolean geschZeit) {
		this.geschZeit = geschZeit;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
