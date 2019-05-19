package de.hwr.fims_gui.sfv;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.MultiSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.SingleSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.data.TabData;
import de.hwr.fims_backend.data.customerdata.Auftrag;
import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.FimsUI;
import de.hwr.fims_gui.interfaces.HasName;
import de.hwr.fims_gui.main.ApplicationHeader;
import de.hwr.fims_gui.sfv.tabsheet.TSAuftraggeber;
import de.hwr.fims_gui.sfv.tabsheet.TSAuftragsdaten;
import de.hwr.fims_gui.sfv.tabsheet.TSVerstorbener;
import de.hwr.fims_gui.sfv.tabsheet.TransferAuftragsID;
import de.hwr.tests.PersonTest;

@Theme("mytheme")
public class SFVMainPage extends VerticalLayout implements View, HasName {

	private Navigator navigator;
	String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	
	// Displayed content on TabSheet depends on this ID
	public long auftragsnummer; 
	TSAuftragsdaten tsAuftragsdaten = new TSAuftragsdaten();
	TSVerstorbener tsVerstorbener = new TSVerstorbener();
	TSAuftraggeber tsAuftraggeber = new TSAuftraggeber();
	
	public SFVMainPage(Navigator navigator) {
		this.navigator = navigator;
		this.setMargin(false);
		
		TextField searchTF = new TextField();
		
		Button createButton = new Button("Neuen Sterbefall erstellen");
		createButton.addStyleName("createSFVButton");
		createButton.setIcon(VaadinIcons.PLUS_CIRCLE_O);
		createButton.addClickListener(e -> {
			navigator.navigateTo(FimsUI.SFV_CREATE_VIEW);
		});
		

		Button searchButton = new Button();
		searchButton.addStyleName("seachButton");
		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addClickListener(e -> {
			navigator.navigateTo(FimsUI.SFV_DISPLAY_VIEW);
		});
		
		HorizontalLayout topGroup = new HorizontalLayout();
		topGroup.addComponent(createButton);
		topGroup.addComponent(searchTF);
		topGroup.addComponent(searchButton);
		
		DatabaseConnector dbConnector = new DatabaseConnector();
		ArrayList<TabData> sfvList = new ArrayList<TabData>();
		sfvList.addAll(dbConnector.getDataForTable());
		
		Grid<TabData> sfvGrid = new Grid<>(TabData.class);
		sfvGrid.setItems(sfvList);
		sfvGrid.setWidth(70, Unit.PERCENTAGE);
		sfvGrid.setColumns("auftragsnummer", "vorname", "name", "sterbedatum");
		sfvGrid.addSelectionListener(listener -> {
		    SingleSelect<TabData> selection = sfvGrid.asSingleSelect();
		    Notification.show(selection.getValue().getVorname() + " " + selection.getValue().getName()+ " ausgewählt");   
		    searchTF.setValue(selection.getValue().getName());
		    this.auftragsnummer = selection.getValue().getAuftragsnummer();
		    
		});
		sfvGrid.addItemClickListener(listener -> {
			if (listener.getMouseEventDetails().isDoubleClick()) {
			    
				// Doesn't work due to how Vaadin works but this is how it should work if it were possible
				/*
				 
				tsAuftragsdaten.insertData(this.auftragsnummer);
				tsVerstorbener.insertData(this.auftragsnummer);
				tsAuftraggeber.insertData(this.auftragsnummer);
				
				*/
				
				navigator.navigateTo(FimsUI.SFV_DISPLAY_VIEW); 
			}
		});
		
		this.addComponent(new ApplicationHeader(navigator));
		this.addComponent(topGroup);
		this.addComponent(sfvGrid);
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
