package de.hwr.fims_gui.sfv.tabsheet;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;

public class TSNumberMapping {
	
	private Component comp1;
	private Component comp2;
	private Component comp3;
	
	TSAuftragsdaten ts1 = new TSAuftragsdaten();
	TSVerstorbener ts2 = new TSVerstorbener();
	TSAuftraggeber ts3 = new TSAuftraggeber();
	
	public TSNumberMapping() {
		comp1 = ts1.init(true);
		comp2 = ts2.init(true);
		comp3 = ts3.init(true);
	}
	
	public Component init(int i) {
		
		if(i == 1)return comp1;
		if(i == 2)return comp2;
		if(i == 3)return comp3;

		return null;

	}

}
