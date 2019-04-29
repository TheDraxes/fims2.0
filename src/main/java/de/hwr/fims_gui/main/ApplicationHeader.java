package de.hwr.fims_gui.main;

import com.vaadin.navigator.Navigator;
import java.io.File;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.hwr.fims_gui.FimsUI;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator;

public class ApplicationHeader extends HorizontalLayout implements View {
	Navigator navigator;
	String basepath = VaadinService.getCurrent()
            .getBaseDirectory().getAbsolutePath();
	
	public ApplicationHeader(Navigator navigator) {
		this.navigator = navigator;
		this.setWidth(100,Unit.PERCENTAGE);
		
		//FimsImage
		FileResource fimsLogoRessource = new FileResource(new File(basepath + "/WEB-INF/res/MyWayBestattungenlogo.png"));
        Image fimsLogoImage = new Image("", fimsLogoRessource);
        fimsLogoImage.setHeight(80, Unit.PIXELS);
        fimsLogoImage.setResponsive(true);
        Responsive.makeResponsive(fimsLogoImage);
        
        //HomeButton
        FileResource homeImageRessource = new FileResource(new File(basepath + "/WEB-INF/res/baseline_home_black.png"));
        Image homeButton = new Image("", homeImageRessource);
        homeButton.setHeight(80, Unit.PIXELS);
        homeButton.setResponsive(true);
        Responsive.makeResponsive(homeButton);
        homeButton.addClickListener(e -> {
        	navigator.navigateTo(FimsUI.MAIN_VIEW);
	    });
        
        //ContactButton
        FileResource contactImageRessource = new FileResource(new File(basepath + "/WEB-INF/res/baseline_contact_phone_black.png"));
        Image contactButton = new Image("", contactImageRessource);
        contactButton.setHeight(80, Unit.PIXELS);
        contactButton.setResponsive(true);
        Responsive.makeResponsive(contactButton);
        contactButton.addClickListener(e -> {
        	showContact();
	    });
        
        //LogoutButton
        FileResource logoutImageRessource = new FileResource(new File(basepath + "/WEB-INF/res/baseline_input_black.png"));
        Image logoutButton = new Image("", logoutImageRessource);
        logoutButton.setHeight(80, Unit.PIXELS);
        logoutButton.setResponsive(true);
        Responsive.makeResponsive(logoutButton);
        logoutButton.addClickListener(e -> {
        	showLogout();
	    });
        
        //HelpButton
        FileResource helpImageRessource = new FileResource(new File(basepath + "/WEB-INF/res/baseline_help_black.png"));
        Image helpButton = new Image("", helpImageRessource);
        helpButton.setHeight(80, Unit.PIXELS);
        helpButton.setResponsive(true);
        Responsive.makeResponsive(helpButton);
        helpButton.addClickListener(e -> {
	    	showHelp();
	    });
        
        //HeaderButtons Layout collection
        HorizontalLayout headerButtons = new HorizontalLayout();
        HorizontalLayout logo = new HorizontalLayout();
        
        headerButtons.addComponents(homeButton, contactButton, logoutButton, helpButton);
        
        logo.addComponent(fimsLogoImage);

        this.addComponents(logo, headerButtons);
		this.setComponentAlignment(headerButtons, Alignment.MIDDLE_RIGHT);
		this.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
	}
	
	private void showContact() {
        Window subWindow = new Window("Hinweis");
        VerticalLayout subContent = new VerticalLayout();
        subWindow.setContent(subContent);
        subWindow.setHeight(70, Unit.PERCENTAGE);
        subWindow.setWidth(40, Unit.PERCENTAGE);
        subContent.addComponent(new Label("Dieses Feature ist bald erhältlich"));
        subWindow.center();
        Page.getCurrent().getUI().addWindow(subWindow);
	}	
	
	private void showLogout() {
		navigator.navigateTo(FimsUI.LOGIN_VIEW);
		UI ui = UI.getCurrent();
		VaadinSession vaadinSession = ui.getSession();
        vaadinSession.setAttribute("user", null);  
	}
	
	private void showHelp() {
        Window subWindow = new Window("Hilfefenster");
        VerticalLayout subContent = new VerticalLayout();
        subWindow.setContent(subContent);
        subWindow.setHeight(70, Unit.PERCENTAGE);
        subWindow.setWidth(40, Unit.PERCENTAGE);
        subContent.addComponent(new Label("Sie kommen nicht weiter? Dann wenden Sie sich ans Supportteam."));
        subWindow.center();
        Page.getCurrent().getUI().addWindow(subWindow);
	}	
}
