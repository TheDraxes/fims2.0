package de.hwr.fims_gui.main;

import java.io.File;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.FimsUI;
import de.hwr.fims_gui.interfaces.*;
import de.hwr.fims_gui.main.ApplicationHeader;

@Theme("mytheme")
public class StartPageView extends VerticalLayout implements View, HasName {

	private Navigator navigator;
	
	public StartPageView(Navigator navigator, DatabaseConnector connector) {
		this.navigator = navigator;
		this.setMargin(true);
		
		HorizontalLayout layout =  new HorizontalLayout();
		
		Button buttonSFV = new Button("Sterbefallverwaltung");
		buttonSFV.setStyleName(ValoTheme.BUTTON_LINK);
		buttonSFV.addStyleName("bigButton");
		buttonSFV.addStyleName("Button");
		buttonSFV.addStyleName("SFVButton");
		buttonSFV.addClickListener(e -> {
			navigator.navigateTo(FimsUI.SFV_MAIN_VIEW);
		});
		
		Button buttonWV = new Button("Warenverwaltung");
		buttonWV.setStyleName(ValoTheme.BUTTON_LINK);
		buttonWV.addStyleName("bigButton");
		buttonWV.addStyleName("Button");
		buttonWV.addStyleName("WVButton");
		buttonWV.addClickListener(e -> {
			Notification.show("Currently not available", Notification.TYPE_WARNING_MESSAGE);
		});
		
		layout.addComponents(buttonSFV, buttonWV);
		layout.setSpacing(true);
		layout.addStyleName("layout");
		
		
		this.addComponent(new ApplicationHeader(navigator));
		this.addComponent(layout);
		this.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
