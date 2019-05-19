package de.hwr.fims_gui.sfv.roadmap;

import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.controller.DataController;
import de.hwr.fims_backend.data.services.Blumenarrangement;

public class BlumenRMLayout extends RoadMapPart {

	Label heading =  new Label("6. Blumenarangement");
	DataController controller;
	
	RadioButtonGroup<String> selbstOrga = new RadioButtonGroup();
	
	TextField preis = new TextField();
	TextField laden = new TextField();
	
	DateField abholung = new DateField();
	
	TextArea bemerkung = new TextArea();
	
	public BlumenRMLayout(DataController controller) {
		super();
		this.controller = controller;
		this.heading.addStyleName("heading");
		init();
		
		GridLayout layout = new GridLayout(5, 5);
		layout.setSpacing(true);
		
		layout.addComponent(selbstOrga, 0, 0);
		layout.addComponent(preis,      1, 0);
		layout.addComponent(laden,      2, 0);
		
		layout.addComponent(abholung,   0, 1);
		
		layout.addComponent(bemerkung,  0, 2, 2, 3);

		this.addComponents(heading, layout);
	}
	
	private void init() {
		selbstOrga.setItems("Ja", "Nein");
		selbstOrga.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		selbstOrga.setCaption("Selbstorganisiert?");
		
		selbstOrga.addValueChangeListener(e -> {
			if(selbstOrga.getValue().equals("Ja")) {
				preis.setEnabled(false);
				laden.setEnabled(false);
				abholung.setEnabled(false);
			} else {
				preis.setEnabled(true);
				laden.setEnabled(true);
				abholung.setEnabled(true);
			}
		});
		
		preis.setCaption("Preis");
		laden.setCaption("Blumenladen");
		abholung.setCaption("Datum der Abholung");
		bemerkung.setCaption("Bemerkung");
		bemerkung.setWidth(100, Unit.PERCENTAGE);
	}
	
	
	
	@Override
	public boolean isFilled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Blumenarrangement safeData() {
		double preisd = 0;
		try {
			preisd = Double.parseDouble(preis.getValue());
		} catch(Exception e) {
			Notification.show("Fehler", "Preis wurde nicht richtig eingegeben! Komma muss als Punkt geschrieben werden", Notification.TYPE_WARNING_MESSAGE);
			return null;
		}
		
		Blumenarrangement blumenargangement = new Blumenarrangement(bemerkung.getValue(), preisd);
		return blumenargangement;
	}

	@Override
	public void clear() {
		selbstOrga.setValue("");
		preis.setValue("");
		laden.setValue("");
		bemerkung.setValue("");
	}
}
