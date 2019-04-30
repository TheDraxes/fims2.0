package de.hwr.fims_gui.sfv;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TabSheet;
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
		
		TabSheet tabsheet = new TabSheet();
		
		VerticalLayout tab1 = new VerticalLayout();
		tabsheet.addTab(tab1).setCaption("Auftragsdaten");
		
		VerticalLayout tab2 = new VerticalLayout();
		tabsheet.addTab(tab2).setCaption("Verstorbener");
		
		VerticalLayout tab3 = new VerticalLayout();
		tabsheet.addTab(tab3).setCaption("Auftraggeber");
		
		VerticalLayout tab4 = new VerticalLayout();
		tabsheet.addTab(tab4).setCaption("Angehörige");
		
		VerticalLayout tab5 = new VerticalLayout();
		tabsheet.addTab(tab5).setCaption("Trauerfeier");
		
		VerticalLayout tab6 = new VerticalLayout();
		tabsheet.addTab(tab6).setCaption("Blumenbestellung");
		
		VerticalLayout tab7 = new VerticalLayout();
		tabsheet.addTab(tab7).setCaption("Zeitungsauftrag");
		
		this.addComponent(tabsheet);
	}
	

}
