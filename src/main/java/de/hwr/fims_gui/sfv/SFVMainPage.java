package de.hwr.fims_gui.sfv;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.FimsUI;
import de.hwr.fims_gui.interfaces.HasName;
import de.hwr.fims_gui.main.ApplicationHeader;

@Theme("mytheme")
public class SFVMainPage extends VerticalLayout implements View, HasName {

	private Navigator navigator;
	
	public SFVMainPage(Navigator navigator, DatabaseConnector connector) {
		this.navigator = navigator;
		this.setMargin(false);
		
		VerticalLayout layout =  new VerticalLayout();
		
		Button createSFV = new Button("Neuen Sterbefall erstellen");
		createSFV.addStyleName("createSFVButton");
		createSFV.addClickListener(e -> {
			navigator.navigateTo(FimsUI.SFV_CREATE_VIEW);
		});
		
		layout.addComponent(createSFV);
		layout.setSpacing(true);
		
		
		this.addComponent(new ApplicationHeader());
		this.addComponent(layout);
		this.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
