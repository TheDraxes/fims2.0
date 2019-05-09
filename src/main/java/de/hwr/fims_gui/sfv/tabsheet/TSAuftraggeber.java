package de.hwr.fims_gui.sfv.tabsheet;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;

public class TSAuftraggeber {
	
	GridLayout layout = new GridLayout(5, 5);
	//Pflichtfelder
	ComboBox ort = new ComboBox();
	
	TextField name = new TextField();
	TextField surname = new TextField();
	TextField plz = new TextField();
	TextField str_hnr = new TextField();
	
	//Felder optional
	TextField telefon = new TextField();
	TextField beziehung = new TextField();
	
	public GridLayout init(boolean isReadable) {
	
	// Set properties
	name.setCaption("Name*");
	name.setReadOnly(isReadable);
	
	surname.setCaption("Vorname*");
	surname.setReadOnly(isReadable);
	
	plz.setCaption("Postleitzahl*");
	plz.setReadOnly(isReadable);
	
	ort.setCaption("Ort*");
	ort.setReadOnly(isReadable);
	
	// Content depends on ID	
	str_hnr.setCaption("Straße & Hausnummer*");
	str_hnr.setReadOnly(isReadable);
	str_hnr.setWidth(100, Unit.PERCENTAGE);
	
	telefon.setCaption("Telefonnummer");
	telefon.setReadOnly(isReadable);
	
	beziehung.setCaption("Beziehungsart");
	beziehung.setReadOnly(isReadable);
	
	// Add components to layout
	//1st line
	layout.setSpacing(true);
	layout.addComponent(name, 	   0, 0);
	layout.addComponent(surname,   1, 0);
	layout.addComponent(telefon,   2, 0);
	layout.addComponent(beziehung, 3, 0);
	
	//2nd line
	layout.addComponent(plz, 	   0, 1);
	layout.addComponent(ort,	   1, 1);
	layout.addComponent(str_hnr,   2, 1,3,1);
	Notification.show("Hier sollten Auftraggeberdaten stehen :)");
		
	return layout;
		
	}

}
