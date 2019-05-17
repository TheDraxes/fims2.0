package de.hwr.fims_gui.sfv.roadmap;

import java.util.Date;

import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.controller.DataController;
import de.hwr.fims_backend.data.services.Abholung;

public class AbholungRMLayout extends RoadMapPart {

	Label heading =  new Label("4. Abholung");
	DataController controller;
	
	TextField woabholen = new TextField();
	DateField wann = new DateField();
	RadioButtonGroup<String> group = new RadioButtonGroup();
	
	public AbholungRMLayout(DataController controller) {
		super();
		this.controller = controller;
		this.heading.addStyleName("heading");
		
		init();
		
		GridLayout layout = new GridLayout(1, 3);
		layout.setSpacing(true);
		layout.addComponents(woabholen, wann, group);
		layout.setWidth(40, Unit.PERCENTAGE);
		this.addComponents(heading, layout);
	}
	
	private void init() {
		woabholen.setCaption("Wo soll der Verstorbene abgeholt werden?*");
		woabholen.setWidth(100, Unit.PERCENTAGE);
		wann.setCaption("Wann soll der Verstorbene abgeholt werden?*");
		
		group.setItems("Ja", "Nein");
		group.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		group.setCaption("In den Geschäftszeiten?*");
	}
	
	
	
	@Override
	public boolean isFilled() {
		if(woabholen.isEmpty() || wann.isEmpty() || group.isEmpty()) {
			System.out.println("[fehler] Abholung nicht ausgefüllt!");
			return false;
		}
		return true;
	}

	public Abholung safeData() {
		boolean gesch = false;
		if(group.getValue().equals("Ja")) {
			gesch = true;
		}
		
		
		return new Abholung(woabholen.getValue(), ConvertHelper.convertVaadinDate(wann.getValue()), gesch);
	}

	@Override
	public void clear() {
		woabholen.setValue("");
		group.setValue("");
	}

}
