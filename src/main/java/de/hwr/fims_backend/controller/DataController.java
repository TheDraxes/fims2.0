package de.hwr.fims_backend.controller;

import java.util.ArrayList;
import java.util.Arrays;

import de.hwr.fims_backend.data.customerdata.*;
import de.hwr.fims_backend.data.advertisement.*;
import de.hwr.fims_backend.data.services.*;
import de.hwr.fims_backend.dbconnector.DatabaseConnector;

public class DataController {
	
	ArrayList<Auftrag> auftraege= new ArrayList();
	
	ArrayList<Verstorbener> Verstorbene = new ArrayList<Verstorbener>();
	ArrayList<Auftraggeber> Auftraggeber = new ArrayList<Auftraggeber>();
	ArrayList<Angehoeriger> Angehörige = new ArrayList<Angehoeriger>();
	ArrayList<Abholung> Abholung = new ArrayList<Abholung>();
	ArrayList<Trauerfeier> Trauerfeiern = new ArrayList<Trauerfeier>();
	ArrayList<Zeitungsanzeige> Zeitungsanzeigen = new ArrayList<Zeitungsanzeige>();
	
	ArrayList<String> familienstandList = new ArrayList<String>();
	ArrayList<String> konfessionList = new ArrayList<String>();
	ArrayList<String> krankenkasseList = new ArrayList<String>();
	ArrayList<String> rentenversicherungList = new ArrayList<String>();
	ArrayList<String> ortList = new ArrayList<String>();
	
	ArrayList<String> bestArtList = new ArrayList<String>();
	ArrayList<String> grabstaetteList = new ArrayList<String>();
	ArrayList<String> rednerList = new ArrayList<String>();
	ArrayList<String> deckeList = new ArrayList<String>();
	ArrayList<String> tarlarList = new ArrayList<String>();
	
	public static final String familienstand = "familienstand";
	public static final String konfession = "konfession";
	public static final String krankenkasse = "krankenkasse";
	public static final String rentenversicherung = "rentenversicherung";
	public static final String ort = "ort";
	
	DatabaseConnector connector = new DatabaseConnector();
	
	public DataController() {
		
		init();
		
		familienstandList = comboBoxContent(familienstand);
		konfessionList = comboBoxContent(konfession);
		krankenkasseList = comboBoxContent(krankenkasse);
		rentenversicherungList = comboBoxContent(rentenversicherung);
		ortList = comboBoxContent(ort);
		
		auftraege =  connector.getAuftraegeFromDatabase();
		if(auftraege == null) {
			return;
		}
		
		if(!auftraege.isEmpty()) {
			for(Auftrag auftrag : auftraege) {
				Verstorbene.add(auftrag.getVerstorbener());
				Auftraggeber.add(auftrag.getAuftraggeber());
				Angehörige.add(auftrag.getAngehoeriger());
				Abholung.add(auftrag.getAbholung());
				Trauerfeiern.add(auftrag.getTrauerfeier());
				Zeitungsanzeigen.addAll(auftrag.getAnzeigen().getListe());
			}
		}
	}
	
	public ArrayList<String> comboBoxContent(String listToFill){
		
		ArrayList<String> _return = new ArrayList<String>();
		
		switch(listToFill) {
			case familienstand:	
				_return.addAll(Arrays.asList(connector.getUniqueFamilienstaende()));
				System.out.println("[INFO] gewählt: " + familienstand);
				break;
			case konfession:
				_return.addAll(Arrays.asList(connector.getUniqueKonfessionen()));
				System.out.println("[INFO] gewählt: " + konfession);
				break;
			case krankenkasse:
				_return.addAll(Arrays.asList(connector.getUniqueKrankenkassen()));
				System.out.println("[INFO] gewählt: " + krankenkasse);
				break;
			case rentenversicherung:
				_return.addAll(Arrays.asList(connector.getUniqueRentenverischerungen()));
				System.out.println("[INFO] gewählt: " + rentenversicherung);
				break;
			case ort:
				_return.addAll(Arrays.asList(connector.getUniqueOrte()));
				System.out.println("[INFO] gewählt: " + ort);
				break;
		}
		return _return;
	}

	private void init() {
		String[] arr1 = {"Erdbestattung", "Feuerbestattung"};
		bestArtList.addAll(Arrays.asList(arr1));
		
		String[] arr2 = {"Urnengrab", "Doppelurnengrab", "Erdgrab", "Friedwald"};
		grabstaetteList.addAll(Arrays.asList(arr2));
		
		String[] arr3 = {"Mario", "Riemann", "Fischer"};
		rednerList.addAll(Arrays.asList(arr3));
		
		String[] arr4 = {"DE", "3-CH-ZZ-V"};
		deckeList.addAll(Arrays.asList(arr4));
		
		String[] arr5 = {"eigene Kleidung", "Herrentalar", "Damentalar"};
		tarlarList.addAll(Arrays.asList(arr5));
	}
	
	public ArrayList<String> getFamilienstandList() {
		return familienstandList;
	}

	public void setFamilienstandList(ArrayList<String> familienstandList) {
		this.familienstandList = familienstandList;
	}
	
	public void addFamilienstand(String familienstand) {
		familienstandList.add(familienstand);
	}

	public ArrayList<String> getKonfessionList() {
		return konfessionList;
	}

	public void setKonfessionList(ArrayList<String> konfessionList) {
		this.konfessionList = konfessionList;
	}
	
	public void addKonfession(String Konfession) {
		konfessionList.add(Konfession);
	}

	public ArrayList<String> getKrankenkasseList() {
		return krankenkasseList;
	}

	public void setKrankenkasseList(ArrayList<String> krankenkasseList) {
		this.krankenkasseList = krankenkasseList;
	}

	public void addKrankenkasse(String krankenkasse) {
		krankenkasseList.add(krankenkasse);
	}
	
	public ArrayList<String> getRentenversicherungList() {
		return rentenversicherungList;
	}

	public void setRentenversicherungList(ArrayList<String> rentenversicherungList) {
		this.rentenversicherungList = rentenversicherungList;
	}

	public void addRentenversicherung(String rentenversicherung) {
		rentenversicherungList.add(rentenversicherung);
	}
	
	public ArrayList<String> getOrtList() {
		return ortList;
	}

	public void setOrtList(ArrayList<String> ortList) {
		this.ortList = ortList;
	}
	
	public void addOrt(String ort) {
		ortList.add(ort);
		System.out.println("[INFO] neuer stand: " + ortList.toString());
	}

	public ArrayList<String> getBestArtList() {
		return bestArtList;
	}
	public void addArt(String art) {
		bestArtList.add(art);
	}

	public void setBestArtList(ArrayList<String> bestArtList) {
		this.bestArtList = bestArtList;
	}

	public ArrayList<String> getGrabstaetteList() {
		return grabstaetteList;
	}
	public void addGrabst(String Grabst) {
		grabstaetteList.add(Grabst);
	}

	public void setGrabstaetteList(ArrayList<String> grabstaetteList) {
		this.grabstaetteList = grabstaetteList;
	}

	public ArrayList<String> getRednerList() {
		return rednerList;
	}
	public void addRedner(String Redner) {
		rednerList.add(Redner);
	}
	public void setRednerList(ArrayList<String> rednerList) {
		this.rednerList = rednerList;
	}

	public ArrayList<String> getDeckeList() {
		return deckeList;
	}
	public void addDecke(String Redner) {
		deckeList.add(Redner);
	}
	public void setDeckeList(ArrayList<String> deckeList) {
		this.deckeList = deckeList;
	}

	public ArrayList<String> getTarlarList() {
		return tarlarList;
	}
	public void addTarlar(String tarlar) {
		tarlarList.add(tarlar);
	}
	public void setTarlarList(ArrayList<String> tarlarList) {
		this.tarlarList = tarlarList;
	}
	
}
