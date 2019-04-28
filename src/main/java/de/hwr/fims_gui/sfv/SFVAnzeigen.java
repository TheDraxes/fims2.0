package de.hwr.fims_gui.sfv;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

import de.hwr.fims_backend.controller.DataController;
import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.main.ApplicationHeader;

public class SFVAnzeigen extends VerticalLayout implements View {
	
	Navigator navigator;
	DatabaseConnector connector;
	
	public SFVAnzeigen(Navigator navigator, DataController dataController) {
		this.navigator = navigator;
		this.setMargin(true);
		this.addComponent(new ApplicationHeader(navigator));
		
		MenuBar menubar = new MenuBar();
		
		MenuItem auftragssdaten = menubar.addItem("Auftragsdaten", null, null);
		MenuItem verstorbener = menubar.addItem("Verstorbener", null, null);
		MenuItem auftraggeber = menubar.addItem("Auftraggeber", null, null);
		MenuItem angehoerige = menubar.addItem("Angehörige", null, null);
		MenuItem trauerfeier = menubar.addItem("Trauerfeier", null, null);
		MenuItem blumenbestellung = menubar.addItem("Blumenbestellung", null, null);
		MenuItem zeitungsauftrag = menubar.addItem("Zeitungsauftrag", null, null);
		
		this.addComponent(menubar);
		this.setComponentAlignment(menubar, Alignment.TOP_CENTER);
	}
	

}
