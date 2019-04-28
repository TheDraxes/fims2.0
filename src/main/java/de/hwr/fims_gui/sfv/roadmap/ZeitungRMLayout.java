package de.hwr.fims_gui.sfv.roadmap;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import de.hwr.fims_backend.controller.DataController;

public class ZeitungRMLayout extends RoadMapPart {

	Label heading =  new Label("7. Zeitungsanzeige");
	DataController controller;
	
	public ZeitungRMLayout(DataController controller) {
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
