package de.hwr.fims_backend.administration;

/**	Zweck: Diese Klasse dient der Darstellung und Zusammenfassung der f�r den Gesch�ftsprozess relevanten
 * 		   Familienmitglieder in einer ArrayList.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  				   zuletzt bearbeitet am 20.08.2018
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import de.hwr.fims_backend.data.customerdata.Angehoeriger;


public class Angehoerige implements Serializable{

	private static final long serialVersionUID = 1408201805L;

	private ArrayList<Angehoeriger> familie;
	static int anzahl;
	
	
	public Angehoerige() {
		this.familie = new ArrayList<Angehoeriger>();
		this.anzahl = 0;
	}
	
	
	//Angeh�rigen der Familie hinzuf�gen
	public void addElement(Angehoeriger angeh) {
	
		familie.add(angeh);
		this.anzahl++;
	}
	
	
	//Angehoerigen aus der Familie holen
	public Angehoeriger getElement(Angehoeriger angeh){
			
		int index = familie.indexOf(angeh);
		return familie.get(index);
		
	}
	
	
	//Angeh�rigen aus der Familie entfernen
	public void deleteElement(Angehoeriger angeh) {
		
		if(familie.contains(angeh)) {
			
			familie.remove(angeh);
			this.anzahl--;
		}
		else {
			System.out.println("Der Angehoerige existiert nicht.");
		}
		
	}
	
	
	
	//Methode ohne R�ckgabewert (void), die die Inhalte der Liste auf der Konsole ausgeben.
	public void auslesen() {
		
		Iterator<Angehoeriger> it = familie.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		System.out.print("\n\n");
		
	}


	public ArrayList<Angehoeriger> getFamilie() {
		return familie;
	}


	public void setFamilie(ArrayList<Angehoeriger> familie) {
		this.familie = familie;
	}


	public int getAnzAngeh() {
		return anzahl;
	}

	private static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
