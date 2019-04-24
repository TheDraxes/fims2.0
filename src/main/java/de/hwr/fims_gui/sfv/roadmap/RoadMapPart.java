package de.hwr.fims_gui.sfv.roadmap;

import com.vaadin.ui.VerticalLayout;

public abstract class RoadMapPart extends VerticalLayout {

	public abstract boolean isFilled();
	public abstract boolean safeData();
	
	public RoadMapPart(){
		this.setMargin(false);
		this.addStyleName("formContainer");
		//this.setSpacing(false);
		
	}
	
}
