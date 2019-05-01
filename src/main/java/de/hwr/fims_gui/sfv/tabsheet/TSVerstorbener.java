package de.hwr.fims_gui.sfv.tabsheet;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;

public class TSVerstorbener {
	
	public GridLayout init() {
		
		GridLayout layout = new GridLayout(4, 4);
		
		Notification.show("Hier sollten Verstorbenendaten stehen :)");
		
		return layout;
		
	}

}
