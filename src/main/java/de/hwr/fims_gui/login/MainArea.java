package de.hwr.fims_gui.login;

import java.io.File;

import javax.swing.GroupLayout.Alignment;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MainArea extends VerticalLayout {
	
	TextField username = new TextField();
	PasswordField password = new PasswordField();
	
	VerticalLayout loginForm = new VerticalLayout();
	
	Button loginButton = new Button("Anmelden");
	
	String basepath = VaadinService.getCurrent()
            .getBaseDirectory().getAbsolutePath();
	
	public MainArea() {
		this.setSizeFull();
		this.addStyleName("mainarea");
		
		FileResource fimsLogoRessource = new FileResource(new File(basepath + "/WEB-INF/res/LOGO FIMS.png"));
        Image fimsLogoImage = new Image("", fimsLogoRessource);
        fimsLogoImage.setHeight(250, Unit.PIXELS);
        fimsLogoImage.setResponsive(true);
		
        FileResource textLogoRessource = new FileResource(new File(basepath + "/WEB-INF/res/MyWayBestattungenlogo.png"));
        Image textLogoImage = new Image("", textLogoRessource);
        textLogoImage.setHeight(150, Unit.PIXELS);
        textLogoImage.setResponsive(true);
        
        HorizontalLayout logoHeader = new HorizontalLayout();
        logoHeader.addComponents(textLogoImage, fimsLogoImage);
        logoHeader.setComponentAlignment(textLogoImage, com.vaadin.ui.Alignment.MIDDLE_CENTER);
        logoHeader.setComponentAlignment(fimsLogoImage, com.vaadin.ui.Alignment.BOTTOM_CENTER);
        logoHeader.setResponsive(true);
        
		username.setPlaceholder("Nutzername");
		username.setWidth(200, Unit.PIXELS);
		
		password.setPlaceholder("Passwort");
		password.setWidth(200, Unit.PIXELS);
		
		loginButton.addClickListener(e -> {
			authorize(username.getValue(), password.getValue());
		});
	
		loginForm.addComponents(logoHeader, username, password, loginButton);
		loginForm.setComponentAlignment(logoHeader,  com.vaadin.ui.Alignment.MIDDLE_CENTER);
		loginForm.setComponentAlignment(username,    com.vaadin.ui.Alignment.MIDDLE_CENTER);
		loginForm.setComponentAlignment(password,    com.vaadin.ui.Alignment.MIDDLE_CENTER);
		loginForm.setComponentAlignment(loginButton, com.vaadin.ui.Alignment.MIDDLE_CENTER);
		
		this.addComponent(loginForm);
	}
	
	private void authorize(String username, String password) {
		Notification notif = new Notification(
			    "Warnung",
			    "Loginsystem außer Betrieb",
			    Notification.TYPE_WARNING_MESSAGE);

			// Customize it
			notif.setDelayMsec(2000);
			notif.setPosition(Position.BOTTOM_RIGHT);

			// Show it in the page
			notif.show(Page.getCurrent());
	}
}
