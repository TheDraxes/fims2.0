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
	
	public static final String familienstand = "familienstand";
	public static final String konfession = "konfession";
	public static final String krankenkasse = "krankenkasse";
	public static final String rentenversicherung = "rentenversicherung";
	public static final String ort = "ort";
	
	DatabaseConnector connector = new DatabaseConnector();
	
	public DataController() {
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
	
}
