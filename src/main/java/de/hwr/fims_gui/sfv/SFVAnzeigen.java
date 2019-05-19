package de.hwr.fims_gui.sfv;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
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
import de.hwr.fims_gui.sfv.tabsheet.TransferAuftragsID;

/**
 * @author Nguyen Tien Dung Otten
 *
 */
public class SFVAnzeigen extends VerticalLayout implements View {
	
	Navigator navigator;
	DatabaseConnector connector;
	static Component activeComp;
	static Layout tab;
	Component newComp;
	
	long auftragsnummer;
	TransferAuftragsID tfAuftragsID = new TransferAuftragsID();
	
	TSAuftragsdaten tsAuftragsdaten = new TSAuftragsdaten();
	TSVerstorbener tsVerstorbener = new TSVerstorbener();
	TSAuftraggeber tsAuftraggeber = new TSAuftraggeber();
	TSAngehoerige tsAngehoerige = new TSAngehoerige();
	TSTrauerfeier tsTrauerfeier = new TSTrauerfeier();
	TSBlumenbestellung tsBlumenbestellung = new TSBlumenbestellung();
	TSZeitungsauftrag tsZeitungsauftrag = new TSZeitungsauftrag();
	
	TabSheet tabsheet = new TabSheet();
	
	VerticalLayout tab1 = new VerticalLayout();
	VerticalLayout tab2 = new VerticalLayout();
	VerticalLayout tab3 = new VerticalLayout();
	VerticalLayout tab4 = new VerticalLayout();
	VerticalLayout tab5 = new VerticalLayout();
	VerticalLayout tab6 = new VerticalLayout();
	VerticalLayout tab7 = new VerticalLayout();
	
	public SFVAnzeigen(Navigator navigator) {
		this.navigator = navigator;
		this.setMargin(true);
		this.addComponent(new ApplicationHeader(navigator));
		SFVAnzeigen.activeComp = tab1;
		
		tabsheet.addTab(tab1).setCaption("Auftragsdaten");
		tabsheet.addTab(tab2).setCaption("Verstorbener");
		tabsheet.addTab(tab3).setCaption("Auftraggeber");
		tabsheet.addTab(tab4).setCaption("Angehörige");
		tabsheet.addTab(tab5).setCaption("Trauerfeier");
		tabsheet.addTab(tab6).setCaption("Blumenbestellung");
		tabsheet.addTab(tab7).setCaption("Zeitungsauftrag");
		
		tab1.addComponent(tsAuftragsdaten.init(true));
		tab2.addComponent(tsVerstorbener.init(true));
		tab3.addComponent(tsAuftraggeber.init(true));
		tab4.addComponent(tsAngehoerige.init(true));
		tab5.addComponent(tsTrauerfeier.init(true));
		tab6.addComponent(tsBlumenbestellung.init(true));
		tab7.addComponent(tsZeitungsauftrag.init(true));
		
				//Create tab content dynamically when tab is selected
				tabsheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {

					@Override
					public void selectedTabChange(SelectedTabChangeEvent event) {
						TabSheet tabsheet = event.getTabSheet();
						tab = (Layout) tabsheet.getSelectedTab();
						
						//Get caption from tab to call appropriate classes
						String tabCaption = tabsheet.getTab(tab).getCaption();
						
						switch(tabCaption) {
							case "Auftragsdaten":
								newComp = tab1;
								tabsheet.replaceComponent(activeComp, newComp);
								activeComp = newComp;
								break;
							case "Verstorbener":
								newComp = tab2;
								tabsheet.replaceComponent(activeComp, newComp);
								activeComp = newComp;
								break;
							case "Auftraggeber":
								newComp = tab3;
								tabsheet.replaceComponent(activeComp, newComp);
								activeComp = newComp;
								break;
							case "Angehörige":
								newComp = tab4;
								tabsheet.replaceComponent(activeComp, newComp);
								activeComp = newComp;
								break;
							case "Trauerfeier":
								newComp = tab5;
								tabsheet.replaceComponent(activeComp, newComp);
								activeComp = newComp;
								break;
							case "Blumenbestellung":
								newComp = tab6;
								tabsheet.replaceComponent(activeComp, newComp);
								activeComp = newComp;
								break;
							case "Zeitungsauftrag":
								newComp = tab7;
								tabsheet.replaceComponent(activeComp, newComp);
								activeComp = newComp;
						}

					}
				});
			
//			tabsheet.addComponent(activeComp);
			this.addComponents(tabsheet);
	}

}
