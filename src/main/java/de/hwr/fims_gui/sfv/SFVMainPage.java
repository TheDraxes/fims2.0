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
import com.vaadin.ui.Notification;
import com.vaadin.ui.SingleSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.FimsUI;
import de.hwr.fims_gui.interfaces.HasName;
import de.hwr.fims_gui.main.ApplicationHeader;
import de.hwr.tests.PersonTest;

@Theme("mytheme")
public class SFVMainPage extends VerticalLayout implements View, HasName {

	private Navigator navigator;
	String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();


	
	public SFVMainPage(Navigator navigator, DatabaseConnector connector) {
		this.navigator = navigator;
		this.setMargin(false);
		
		TextField searchTF = new TextField();
		
		Button createButton = new Button("Neuen Sterbefall erstellen");
		createButton.addStyleName("createSFVButton");
		createButton.setIcon(VaadinIcons.PLUS_CIRCLE_O);
		createButton.addClickListener(e -> {
			navigator.navigateTo(FimsUI.SFV_VIEW);
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
		
		List<PersonTest> sfvList = new ArrayList<>();
		sfvList.add(new PersonTest(1, "Nguyen Tien Dung", "Otten", "23.04.2011"));
		sfvList.add(new PersonTest(2, "Daniel", "Schützler", "24.04.2020"));
		
		Grid<PersonTest> sfvGrid = new Grid<>(PersonTest.class);
		sfvGrid.setItems(sfvList);
		sfvGrid.setWidth(70, Unit.PERCENTAGE);
		sfvGrid.setColumns("auftragsnummer", "vorname", "name", "sterbedatum");
		sfvGrid.addSelectionListener(listener -> {
		    SingleSelect<PersonTest> selection = sfvGrid.asSingleSelect();
		    Notification.show(selection.getValue().getVorname() + " " + selection.getValue().getName()+ " ausgewählt");
		    searchTF.setValue(selection.getValue().getName());
		});
		sfvGrid.addItemClickListener(listener -> {
			if (listener.getMouseEventDetails().isDoubleClick()) {
				navigator.navigateTo(FimsUI.SFV_DISPLAY_VIEW); //interne Übergabe der Auftragsnummer
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
