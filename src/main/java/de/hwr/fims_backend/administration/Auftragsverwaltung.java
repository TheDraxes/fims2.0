package de.hwr.fims_backend.administration;

/**	Zweck: Diese Klasse dient der Zusammenfassung und Verwaltung der (Bestattungsauftr�ge). Sie werden hiermit in
 * 		   einer ArrayList zusammengefasst. Hier werden Verwaltungsaufgaben, wie: Speichern und Laden, Sortieren
 * 		   und Suchfunktionen verwirklicht.
 *  @autor: Rebecca Held
 *  @version: 1.0
 *  �nderungshistorie: Version 1.0, erstellt von Rebecca Held am 16.08.2018
 *  				   zuletzt bearbeitet am 23.08.2018
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import de.hwr.fims_backend.comparators.DatumComparator;
import de.hwr.fims_backend.comparators.NameComparator;
import de.hwr.fims_backend.comparators.NameComparatorRev;
import de.hwr.fims_backend.data.customerdata.Auftrag;



public class Auftragsverwaltung implements Serializable{

	private ArrayList<Auftrag> liste;
	static int anzahl;
	
	
	public Auftragsverwaltung() {
		this.liste = new ArrayList<Auftrag>();
	}
	
		
	
	//Der Arrayliste einen Auftrag hinzuf�gen
	public void addAuftrag(Auftrag auftrag) {
		
			liste.add(auftrag);
			anzahl++;
		}
		
	
	
	
	//Auftrag aus der Auftragsverwaltung entfernen
	public boolean deleteAuftrag(Auftrag auftrag) {
		
		if(liste.isEmpty()) {
			return false;
		}
		if(!liste.contains(auftrag)) {
			return false;
		}
		else {
			liste.remove(auftrag);
			return true;
		}
		
	}
	
	
	//Sortiermethoden
	public void sortVerstName() {
		
		Collections.sort(liste, new NameComparator());
	}
	
	
	public void sortVerstNameRev() {
		
		Collections.sort(liste, new NameComparatorRev());
	}
	
	
	public void sortTodDatum() {
		
		Collections.sort(liste, new DatumComparator());
	}
	
	
	public boolean save() {
			
			String userprofile = System.getenv("USERPROFILE");
		
			String path = userprofile+"\\desktop\\MyWay-Bestattungsprogramm\\AuftragSave.txt";
			
			try {
				FileOutputStream fos = new FileOutputStream(new File(path));
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this.liste);
				oos.close();
				return true;
			}
			catch (FileNotFoundException f) {
				
				File fi = new File(userprofile+"\\desktop\\MyWay-Bestattungsprogramm");
				if(!fi.exists()) {
					fi.mkdir();
					this.save();
					return true;
				}
				else {
					System.err.println(f.toString());
				return false;
				}
			}
			
			catch (IOException e){
					System.err.println(e.toString());
				return false;
			}
			
		}
	
	
		public ArrayList<Auftrag> load() {
			
			String userprofile = System.getenv("USERPROFILE");
			String path = userprofile+"\\desktop\\Myway-Bestattungsprogramm\\AuftragSave.txt";
			
			ArrayList<Auftrag> temp = new ArrayList<Auftrag>();
			
			try {
				
				FileInputStream fis = new FileInputStream(new File (path));
				ObjectInputStream ois = new ObjectInputStream(fis);
				temp = (ArrayList<Auftrag>) ois.readObject();
				ois.close();
				
				
			}
			catch (ClassNotFoundException c) {
				System.err.println(c.toString());
			}
			catch (FileNotFoundException f) {
				System.err.println(f.toString());
			}
			
			catch (IOException e){
				System.err.println(e.toString());
			}
			
			this.setListe(temp);
			
			return temp;
			
		}
	
	
	
	public ArrayList<Auftrag> getListe() {
		return liste;
	}
	public void setListe(ArrayList<Auftrag> liste) {
		this.liste = liste;
	}
	public static int getAnzahl() {
		return anzahl;
	}
	public static void setAnzahl(int anzahl) {
		Auftragsverwaltung.anzahl = anzahl;
	}
	
	
	
	public void ausgabeVerst() {
		
		Iterator<Auftrag> it = this.liste.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().getAuftragNr());
			System.out.println("-------------------------------------------------------------");
		}
		
	}
	
}
