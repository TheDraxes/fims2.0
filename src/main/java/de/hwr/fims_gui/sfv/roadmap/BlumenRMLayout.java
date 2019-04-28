package de.hwr.fims_gui.sfv.roadmap;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import de.hwr.fims_backend.controller.DataController;

public class BlumenRMLayout extends RoadMapPart {

	Label heading =  new Label("6. Blumenarangement");
	DataController controller;
	
	public BlumenRMLayout(DataController controller) {
		super();
		this.controller = controller;
		this.heading.addStyleName("heading");
		
		GridLayout layout = new GridLayout(5, 5);
		layout.setSpacing(true);
		
		this.addComponents(heading, layout);
	}
	
	private void initTextFields() {
		
	}
	
	private void initComboBoxes() {
		
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
