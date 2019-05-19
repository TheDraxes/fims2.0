package de.hwr.fims_gui.sfv.tabsheet;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;

public class TSAuftragsdaten {
	
	DatabaseConnector connector = new DatabaseConnector();
	
	GridLayout layout = new GridLayout(1, 1);
	
	TextField auftragsnummer = new TextField();
	
	public GridLayout init(boolean isReadable) {
	
		auftragsnummer.setCaption("Auftragsnummer");
		auftragsnummer.setReadOnly(isReadable);
		
		layout.setSpacing(true);
		layout.addComponent(auftragsnummer, 0, 0);
		layout.setWidth(40, Unit.PERCENTAGE);

		// Insert default values for auftragsID = 1
		auftragsnummer.setValue(Long.toString(connector.getAuftraegeFromDatabase().get(0).getAuftragNr()));
		
		return layout;
	}
	
	public void insertData(long auftragsID) {
		auftragsnummer.setValue(Long.toString(connector.getAuftraegeFromDatabase().get((int) auftragsID).getAuftragNr()));
	}
	
}
