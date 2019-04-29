package de.hwr.fims_gui.sfv.roadmap;

import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.controller.DataController;

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

	public boolean safeData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		selbstOrga.setValue("");
		preis.setValue("");
		laden.setValue("");
		bemerkung.setValue("");
	}
}
