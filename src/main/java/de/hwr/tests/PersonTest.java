package de.hwr.tests;

import java.io.Serializable;

public class PersonTest implements Serializable {
	
	private long auftragsnummer;
	private String verstorbener;
	private String sterbedatum;

	
	public PersonTest(long auftragsnummer, String verstorbener, String sterbedatum) {
		this.auftragsnummer = auftragsnummer;
		this.verstorbener = verstorbener;
		this.sterbedatum = sterbedatum;
		
	}


	public long getAuftragsnummer() {
		return auftragsnummer;
	}


	public void setAuftragsnummer(long auftragsnummer) {
		this.auftragsnummer = auftragsnummer;
	}


	public String getVerstorbener() {
		return verstorbener;
	}


	public void setVerstorbener(String verstorbener) {
		this.verstorbener = verstorbener;
	}


	public String getSterbedatum() {
		return sterbedatum;
	}


	public void setSterbedatum(String sterbedatum) {
		this.sterbedatum = sterbedatum;
	}


	
	
}
