package de.hwr.fims_gui.sfv;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import de.hwr.fims_backend.controller.DataController;
import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.main.ApplicationHeader;
import de.hwr.fims_gui.sfv.tabsheet.TSAngehoerige;
import de.hwr.fims_gui.sfv.tabsheet.TSAuftraggeber;
import de.hwr.fims_gui.sfv.tabsheet.TSAuftragsdaten;
import de.hwr.fims_gui.sfv.tabsheet.TSBlumenbestellung;
import de.hwr.fims_gui.sfv.tabsheet.TSTrauerfeier;
import de.hwr.fims_gui.sfv.tabsheet.TSVerstorbener;
import de.hwr.fims_gui.sfv.tabsheet.TSZeitungsauftrag;

public class SFVAnzeigen extends VerticalLayout implements View {
	
	Navigator navigator;
	DatabaseConnector connector;
	
	TSAuftragsdaten tsAuftragsdaten = new TSAuftragsdaten();
	TSVerstorbener tsVerstorbener = new TSVerstorbener();
	TSAuftraggeber tsAuftraggeber = new TSAuftraggeber();
	TSAngehoerige tsAngehoerige = new TSAngehoerige();
	TSTrauerfeier tsTrauerfeier = new TSTrauerfeier();
	TSBlumenbestellung tsBlumenbestellung = new TSBlumenbestellung();
	TSZeitungsauftrag tsZeitungsauftrag = new TSZeitungsauftrag();
	
	VerticalLayout tab1 = new VerticalLayout();
	VerticalLayout tab2 = new VerticalLayout();
	VerticalLayout tab3 = new VerticalLayout();
	VerticalLayout tab4 = new VerticalLayout();
	VerticalLayout tab5 = new VerticalLayout();
	VerticalLayout tab6 = new VerticalLayout();
	VerticalLayout tab7 = new VerticalLayout();
	
	public SFVAnzeigen(Navigator navigator, DataController dataController) {
		this.navigator = navigator;
		this.setMargin(true);
		this.addComponent(new ApplicationHeader(navigator));
		
		TabSheet tabsheet = new TabSheet();
		
		tabsheet.addTab(tab1).setCaption("Auftragsdaten");
		tabsheet.addTab(tab2).setCaption("Verstorbener");
		tabsheet.addTab(tab3).setCaption("Auftraggeber");
		tabsheet.addTab(tab4).setCaption("Angehörige");
		tabsheet.addTab(tab5).setCaption("Trauerfeier");
		tabsheet.addTab(tab6).setCaption("Blumenbestellung");
		tabsheet.addTab(tab7).setCaption("Zeitungsauftrag");
		
				//Create tab content dynamically when tab is selected
				tabsheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {

					@Override
					public void selectedTabChange(SelectedTabChangeEvent event) {
						TabSheet tabsheet = event.getTabSheet();
						Layout tab = (Layout) tabsheet.getSelectedTab();
						
						//Get caption from tab to call appropriate classes
						String tabCaption = tabsheet.getTab(tab).getCaption();
						
						
						switch(tabCaption) {
							case "Auftragsdaten":
								tsAuftragsdaten.init();
								break;
							case "Verstorbener":
								tsVerstorbener.init(true);
								break;
							case "Auftraggeber":
								tsAuftraggeber.init();
								break;
							case "Angehörige":
								tsAngehoerige.init();
								break;
							case "Trauerfeier":
								tsTrauerfeier.init();
								break;
							case "Blumenbestellung":
								tsBlumenbestellung.init();
								break;
							case "Zeitungsauftrag":
								tsZeitungsauftrag.init();
							default:
								Notification.show("What the hell are you doing???");
						}

					}
				});
		
		this.addComponents(tabsheet, tsVerstorbener.init(true));
	}
	

}
