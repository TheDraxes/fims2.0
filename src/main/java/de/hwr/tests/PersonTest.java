package de.hwr.tests;

import java.io.Serializable;

public class PersonTest implements Serializable {
	
	private long auftragsnummer;
	private String vorname;
	private String name;
	private String sterbedatum;

	
	public PersonTest(long auftragsnummer, String vorname, String name, String sterbedatum) {
		this.auftragsnummer = auftragsnummer;
		this.name = name;
		this.vorname = vorname;
		this.sterbedatum = sterbedatum;
		
	}


	public long getAuftragsnummer() {
		return auftragsnummer;
	}


	public void setAuftragsnummer(long auftragsnummer) {
		this.auftragsnummer = auftragsnummer;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getVorname() {
		return vorname;
	}


	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	public String getSterbedatum() {
		return sterbedatum;
	}


	public void setSterbedatum(String sterbedatum) {
		this.sterbedatum = sterbedatum;
	}


	
	
	
}
