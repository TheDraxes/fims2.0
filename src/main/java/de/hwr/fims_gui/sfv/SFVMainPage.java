package de.hwr.fims_gui.sfv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinService;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.data.customerdata.Auftrag;
import de.hwr.fims_backend.data.customerdata.Verstorbener;
import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.FimsUI;
import de.hwr.fims_gui.interfaces.HasName;
import de.hwr.fims_gui.main.ApplicationHeader;
import de.hwr.tests.PersonTest;

@Theme("mytheme")
public class SFVMainPage extends VerticalLayout implements View, HasName {

	private Navigator navigator;
	String basepath = VaadinService.getCurrent()
            .getBaseDirectory().getAbsolutePath();
	
	public SFVMainPage(Navigator navigator, DatabaseConnector connector) {
		this.navigator = navigator;
		this.setMargin(false);
		
		FileResource fimsSearchRessource = new FileResource(new File(basepath + "/WEB-INF/res/baseline_search_black_48dp.png"));
        Image fimsSearchImage = new Image("", fimsSearchRessource);
        fimsSearchImage.setResponsive(true);
        Responsive.makeResponsive(fimsSearchImage);
		
		TextField searchContent = new TextField();
		
		Button createButton = new Button("Neuen Sterbefall erstellen");
		createButton.addStyleName("createSFVButton");
		createButton.addClickListener(e -> {
			navigator.navigateTo(FimsUI.SFV_VIEW);
		});
		
		Button searchButton = new Button();
		searchButton.addStyleName("seachButton");
		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addClickListener(e -> {
			new Notification("Warnung");
		});
		
		List<PersonTest> sfvList = new ArrayList<>();
		sfvList.add(new PersonTest(1, "Mustermann", "23.04.2011"));
		
		Grid<PersonTest> sfvGrid = new Grid<>(PersonTest.class);
		sfvGrid.setItems(sfvList);
		sfvGrid.setColumns("auftragsnummer", "verstorbener", "sterbedatum");
		
		HorizontalLayout topGroup = new HorizontalLayout();
		topGroup.addComponent(createButton);
		topGroup.addComponent(searchContent);
		topGroup.addComponent(searchButton);
		
		this.addComponent(new ApplicationHeader());
		this.addComponent(topGroup);
		this.addComponent(sfvGrid);
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
