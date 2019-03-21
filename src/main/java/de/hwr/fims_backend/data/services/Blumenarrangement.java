package de.hwr.fims_backend.data.services;

/**	Zweck: Diese Klasse dient der Darstellung der Dienstleistung "Blumenbestellung", die im Zusammenhang mit der 
 * 		   stattfindenden Trauerfeier gew�nscht ist. Blumenarrangements sind in diesem Fall die verschiedenen 
 * 		   "Elemente" (Str�u�e, Kr�nze, etc.), die die gew�nschte Blumenbestellung beinhaltet und deren f�r den
 * 		   Gesch�ftsfall relevanten Daten.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  				   zuletzt bearbeitet am 20.08.2018
 */

import java.io.Serializable;

public class Blumenarrangement implements Serializable{

	private static final long serialVersionUID = 1608201809L;
	
	private String bemBlumen;
	private double preis;
	
	public Blumenarrangement() {
		
	}
	
	public Blumenarrangement(String bemBlumen, double preis) {
		this.bemBlumen = bemBlumen;
		this.preis = preis;
		
	}

	
	
	public String getBemBlumen() {
		return bemBlumen;
	}

	public void setBemBlumen(String bemBlumen) {
		this.bemBlumen = bemBlumen;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
