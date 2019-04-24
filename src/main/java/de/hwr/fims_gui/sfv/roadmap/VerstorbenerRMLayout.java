package de.hwr.fims_gui.sfv.roadmap;

import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;

public class VerstorbenerRMLayout extends RoadMapPart {

	public VerstorbenerRMLayout() {
		super();
		
		RadioButtonGroup<String> group = new RadioButtonGroup();
		group.setItems("Herr", "Frau");
		
		this.addComponent(group);
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

}
