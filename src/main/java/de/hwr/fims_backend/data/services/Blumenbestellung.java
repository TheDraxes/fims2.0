package de.hwr.fims_backend.data.services;

/**	Zweck: Diese Klasse dient der Darstellung der Dienstleistung "Blumenbestellung" im Zusammenhang mit der 
 *         Trauerfeier. Die Blumenbestellung fasst alle gew�nschten Blumenarrangements zusammen,  
 *         beinhaltet die f�r den Gesch�ftsprozess relevanten Daten, und berechnet den Gesamtpreis, den die
 *         gew�nschte Blumenbestellung kostet.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  				   zuletzt bearbeitet am 19.08.2018
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class Blumenbestellung implements Serializable{

	private static final long serialVersionUID = 1608201808L;
	
	private boolean selbstOrg;
	private ArrayList<Blumenarrangement> bestellung;
	static int anzahl;
	private double gesPreis;
	private String blumLaden;
	private Date blumDat;
	
	
	public Blumenbestellung() {
		this.bestellung = new ArrayList<Blumenarrangement>();
		anzahl = 0;
	}
	
	public Blumenbestellung(boolean selbstOrg, double gesPreis, String blumLaden, Date blumDat) {
		this.bestellung = new ArrayList<Blumenarrangement>();
		anzahl = 0;
		this.selbstOrg = selbstOrg;
		this.gesPreis = gesPreis;
		this.blumLaden = blumLaden;
		this.blumDat = blumDat;
		
	}
	
	//Blumenarrangement der Liste hinzuf�gen
	public void addElement(Blumenarrangement arra) {
	
		bestellung.add(arra);
		anzahl++;
		
	}
	
	
	//Blumenarrangement aus der Liste holen
	public Blumenarrangement getElement(int index){
			
		return bestellung.get(index);
		
	}
	
	
	//Blumenarrangement aus der Liste entfernen
	public void deleteElement(Blumenarrangement arra) {
		
		if(bestellung.contains(arra)) {
			bestellung.remove(arra);
			anzahl--;
		}
		else {
			System.out.println("Die Zeitungsanzeige existiert nicht.");
		}
		
	}
	
	
	
	//Methode ohne R�ckgabewert (void), die die Inhalte der Liste auf der Konsole ausgeben.
	public void auslesen() {
		
		Iterator<Blumenarrangement> it = bestellung.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		System.out.print("\n\n");
	}
	
	
	
	public boolean isSelbstOrg() {
		return selbstOrg;
	}
	public void setSelbstOrg(boolean selbstOrg) {
		this.selbstOrg = selbstOrg;
	}
	public double getGesPreis() {
		return gesPreis;
	}
	public void setGesPreis(double gesPreis) {
		this.gesPreis = gesPreis;
	}
	public String getBlumLaden() {
		return blumLaden;
	}
	public void setBlumLaden(String blumLaden) {
		this.blumLaden = blumLaden;
	}
	public Date getBlumDat() {
		return blumDat;
	}
	public void setBlumDat(Date blumDat) {
		this.blumDat = blumDat;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
