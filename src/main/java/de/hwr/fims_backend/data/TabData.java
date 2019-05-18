package de.hwr.fims_backend.data;

import java.io.Serializable;
import java.sql.Date;

public class TabData implements Serializable {
	
	private long auftragsnummer;
	private String vorname;
	private String name;
	private Date sterbedatum;

	
	public TabData(long auftragsnummer, String vorname, String name, Date sterbedatum) {
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


	public Date getSterbedatum() {
		return sterbedatum;
	}


	public void setSterbedatum(Date sterbedatum) {
		this.sterbedatum = sterbedatum;
	}


	
	
	
}

