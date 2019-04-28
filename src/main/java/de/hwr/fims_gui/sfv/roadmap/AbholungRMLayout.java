package de.hwr.fims_gui.sfv.roadmap;

import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.controller.DataController;

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
		woabholen.setCaption("Wo soll der Verstorbene abgeholt werden?");
		woabholen.setWidth(100, Unit.PERCENTAGE);
		wann.setCaption("Wann soll der Verstorbene abgeholt werden?");
		
		group.setItems("Ja", "Nein");
		group.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		group.setCaption("In den Geschäftszeiten?");
	}
	
	
	
	@Override
	public boolean isFilled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean safeData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
