package de.hwr.fims_gui.sfv.tabsheet;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class TSAuftragsdaten {
	
	GridLayout layout = new GridLayout(1, 1);
	
	TextField auftragsnummer = new TextField();
	
	public GridLayout init() {
	
		auftragsnummer.setCaption("Auftragsnummer");
		
		layout.setSpacing(true);
		layout.addComponent(auftragsnummer, 0, 0);
		layout.setWidth(40, Unit.PERCENTAGE);
		
		Notification.show("Hier sollten Auftragsdaten stehen :)");
		return layout;
	}
}
