package de.hwr.fims_gui.sfv.tabsheet;

import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class TSBlumenbestellung {
	
	GridLayout layout = new GridLayout(4, 4);
	
	RadioButtonGroup<String> selbstOrga = new RadioButtonGroup();
	
	TextField preis = new TextField();
	TextField laden = new TextField();
	
	DateField dateAbholung = new DateField();
	
	TextArea bemerkung = new TextArea();
	
	public GridLayout init(boolean isReadable) {
		initSpecials();
		
		// Set properties
		preis.setCaption("Preis");
		preis.setReadOnly(isReadable);
		
		laden.setCaption("Blumenladen");
		laden.setReadOnly(isReadable);
		
		dateAbholung.setCaption("Datum Abholung");
		dateAbholung.setReadOnly(isReadable);
		
		bemerkung.setCaption("Bemerkungen");
		bemerkung.setReadOnly(isReadable);
		
		// Add components to layout
		layout.setSpacing(true);
		
		//1st line
		layout.addComponent(selbstOrga,	   0, 0);
		layout.addComponent(preis,		   1, 0);
		layout.addComponent(laden,		   2, 0);
		
		//2nd line
		layout.addComponent(dateAbholung,  0, 1);
		
		//3rd line
		layout.addComponent(bemerkung,     0, 2, 2, 3);
				
		return layout;
		
	}
	private void initSpecials() {
		bemerkung.setCaption("Bemerkungen");
		bemerkung.setSizeFull();
		
		selbstOrga.setItems("Ja", "Nein");
		selbstOrga.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		selbstOrga.setCaption("Selbstorganisiert?");
		
		selbstOrga.addValueChangeListener(e -> {
			if(selbstOrga.getValue().equals("Ja")) {
				preis.setEnabled(false);
				laden.setEnabled(false);
				dateAbholung.setEnabled(false);
			} else {
				preis.setEnabled(true);
				laden.setEnabled(true);
				dateAbholung.setEnabled(true);
			}
		});
	}

}
