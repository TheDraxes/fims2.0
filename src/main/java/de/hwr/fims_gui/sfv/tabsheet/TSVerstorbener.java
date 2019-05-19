package de.hwr.fims_gui.sfv.tabsheet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.sfv.SFVMainPage;

/**
 * @author Nguyen Tien Dung Otten
 *
 */
public class TSVerstorbener {
		
	GridLayout layout = new GridLayout(5, 5);
	RadioButtonGroup<String> geschlGroup = new RadioButtonGroup();
	
	DateField geburt = new DateField();
	DateField tod = new DateField();
	
	ComboBox<String> ort = new ComboBox<String>();
	ComboBox<String> geburtsOrt = new ComboBox<String>();
	ComboBox<String> familienstand = new ComboBox<String>();
	ComboBox<String> todOrt = new ComboBox<String>();
	ComboBox<String> krankenkasse = new ComboBox<String>();
	ComboBox<String> rentenversicherung = new ComboBox<String>();
	ComboBox<String> konfession = new ComboBox<String>();
	
	TextField name = new TextField();
	TextField surname = new TextField();
	TextField anzahlKinder = new TextField();
	TextField plz = new TextField();
	TextField str_hnr = new TextField();
	TextField beruf = new TextField();
	
	// To retrieve data
	DatabaseConnector connector = new DatabaseConnector();
	
	public GridLayout init(boolean isReadable) {
		
		// Set properties
	
		geschlGroup.setItems("Herr", "Frau");
		geschlGroup.setCaption("Geschlecht*");
		geschlGroup.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		geschlGroup.setReadOnly(isReadable);
		
		name.setCaption("Name*");
		name.setReadOnly(isReadable);
		
		surname.setCaption("Vorname*");
		surname.setReadOnly(isReadable);
		
		anzahlKinder.setCaption("Anzahl der Kinder");
		anzahlKinder.setReadOnly(isReadable);
		
		plz.setCaption("Postleitzahl*");
		plz.setReadOnly(isReadable);
		
		ort.setCaption("Ort*");
		ort.setReadOnly(isReadable);
		// Content depends on ID
		
		str_hnr.setCaption("Straße & Hausnummer*");
		str_hnr.setReadOnly(isReadable);
		str_hnr.setWidth(100, Unit.PERCENTAGE);
		
		geburt.setCaption("Geburtsdatum*");
		geburt.setReadOnly(isReadable);
		
		geburtsOrt.setCaption("Geburtsort*");
		geburtsOrt.setReadOnly(isReadable);
		
		familienstand.setCaption("Familientstand");
		familienstand.setReadOnly(isReadable);
		
		tod.setCaption("Todesdatum*");
		tod.setReadOnly(isReadable);
		
		todOrt.setCaption("Todesort*");
		todOrt.setReadOnly(isReadable);
		
		beruf.setCaption("Beruf");
		beruf.setReadOnly(isReadable);
		
		krankenkasse.setCaption("Krankenkasse");
		krankenkasse.setReadOnly(isReadable);
		
		rentenversicherung.setCaption("Rentenversicherung");
		rentenversicherung.setReadOnly(isReadable);
		
		konfession.setCaption("Konfession");
		konfession.setReadOnly(isReadable);
		
		// Add components to layout
		
		layout.setSpacing(true);
		layout.addComponent(geschlGroup, 	   0, 0);
		layout.addComponent(name, 			   1, 0);
		layout.addComponent(surname, 		   2, 0);
		layout.addComponent(anzahlKinder, 	   3, 0);
		
		layout.addComponent(plz, 			   0, 1);
		layout.addComponent(ort, 			   1, 1);
		layout.addComponent(str_hnr, 		   2, 1, 3, 1);
		
		layout.addComponent(geburt,            0, 2);
		layout.addComponent(geburtsOrt,        1, 2);
		layout.addComponent(familienstand,     2, 2);
		
		layout.addComponent(tod,               0, 3);
		layout.addComponent(todOrt,            1, 3);
		layout.addComponent(beruf,             2, 3);
		
		layout.addComponent(krankenkasse,      0, 4);
		layout.addComponent(rentenversicherung,1, 4);
		layout.addComponent(konfession,        2, 4);
		
		// Insert default values for auftragsID = 1
		String geschlString;
		boolean isGeschlecht = connector.getAuftraegeFromDatabase().get(0).getVerstorbener().isGeschlecht();
		if(isGeschlecht == true) {
			geschlString = "Frau";
		} else {
			geschlString = "Herr";
		}
		geschlGroup.setValue(geschlString);
		
		name.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getName());
		surname.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getVorname());
		anzahlKinder.setValue(Integer.toString(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getAnzSohn()
				+ connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getAnzTocht()));
		plz.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getPlz());
		ort.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getOrt());
		str_hnr.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getStrasse()
				+ " "
				+ connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getHausNr());
		
		Date gebDatum = connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getGebDatum();
		geburt.setValue(convertToLocalDateViaMilisecond(gebDatum));
		
		geburtsOrt.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getGebOrt());
		
		Date todDatum = connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getTodDatum();
		tod.setValue(convertToLocalDateViaMilisecond(todDatum));
		
		todOrt.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getTodOrt());
		beruf.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getBeruf());
		familienstand.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getFamStand());
		krankenkasse.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getKrankKas());
		rentenversicherung.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getRenteVers());
		konfession.setValue(connector.getAuftraegeFromDatabase().get(0).getVerstorbener().getKonfes());
		
		return layout;
		
	}
	
	public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	// Doesn't work
	public void insertData(long auftragsID) {
//		name.setValue(connector.getAuftraegeFromDatabase().get((int) auftragsID).getVerstorbener().getName());
//		surname.setValue(connector.getAuftraegeFromDatabase().get((int) auftragsID).getVerstorbener().getVorname());
//		anzahlKinder.setValue(Integer.toString(connector.getAuftraegeFromDatabase().get((int) auftragsID).getVerstorbener().getAnzSohn()
//				+ connector.getAuftraegeFromDatabase().get((int) auftragsID).getVerstorbener().getAnzTocht()));
//		plz.setValue(connector.getAuftraegeFromDatabase().get((int) auftragsID).getVerstorbener().getPlz());

	}

}
