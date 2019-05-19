package de.hwr.fims_gui.sfv.tabsheet;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

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
		
		familienstand.setCaption("Familientsand");
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
		
		
		Notification.show("Hier sollten Verstorbenendaten stehen :)");
		
		return layout;
		
	}

}
