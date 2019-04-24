package de.hwr.fims_gui.sfv.roadmap;

import com.vaadin.ui.TextField;

public class AuftraggeberRMLayout extends RoadMapPart {

	public AuftraggeberRMLayout() {
		this.addComponent(new TextField());

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
