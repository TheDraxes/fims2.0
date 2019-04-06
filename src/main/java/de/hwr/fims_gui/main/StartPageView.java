package de.hwr.fims_gui.main;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_gui.interfaces.HasName;

@Theme("mytheme")
public class StartPageView extends VerticalLayout implements View, HasName {

	private Navigator navigator;
	
	public StartPageView(Navigator navigator) {
		this.navigator = navigator;
		this.setMargin(false);
		
		HorizontalLayout buttonsContainer =  new HorizontalLayout();
		
		Button buttonSFV = new Button("Sterbefallverwaltung");
		buttonSFV.setStyleName(ValoTheme.BUTTON_LINK);
		buttonSFV.addStyleName("bigButton");
		buttonSFV.addStyleName("Button");
		buttonSFV.addStyleName("SFVButton");
		
		Button buttonWV = new Button("");
		buttonWV.setStyleName(ValoTheme.BUTTON_LINK);
		buttonWV.addStyleName("bigButton");
		buttonWV.addStyleName("Button");
		buttonSFV.addStyleName("Button");
		
		buttonsContainer.addComponents(buttonSFV, buttonWV);
		buttonsContainer.setSpacing(true);
		buttonsContainer.addStyleName("buttonContainer");
		
		this.addComponent(new ApplicationHeader());
		this.addComponent(buttonsContainer);
		this.setComponentAlignment(buttonsContainer, Alignment.MIDDLE_CENTER);
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
