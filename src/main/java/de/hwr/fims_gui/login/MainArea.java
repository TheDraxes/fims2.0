package de.hwr.fims_gui.login;

import java.io.File;

import javax.swing.GroupLayout.Alignment;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
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
		this.setSpacing(false);
		this.setMargin(false);
		
		FileResource fimsLogoRessource = new FileResource(new File(basepath + "/WEB-INF/res/LOGO FIMS.png"));
        Image fimsLogoImage = new Image("", fimsLogoRessource);
        fimsLogoImage.setHeight(250, Unit.PIXELS);
        fimsLogoImage.setResponsive(true);
        Responsive.makeResponsive(fimsLogoImage);
		
        FileResource textLogoRessource = new FileResource(new File(basepath + "/WEB-INF/res/MyWayBestattungenlogo.png"));
        Image textLogoImage = new Image("", textLogoRessource);
        textLogoImage.setHeight(150, Unit.PIXELS);
        textLogoImage.setResponsive(true);
        Responsive.makeResponsive(textLogoImage);
        
        HorizontalLayout logoHeader = new HorizontalLayout();
        logoHeader.addComponents(textLogoImage, fimsLogoImage);
        logoHeader.setComponentAlignment(textLogoImage, com.vaadin.ui.Alignment.MIDDLE_CENTER);
        logoHeader.setComponentAlignment(fimsLogoImage, com.vaadin.ui.Alignment.BOTTOM_CENTER);
        logoHeader.setResponsive(true);
        logoHeader.setSpacing(false);
        logoHeader.setMargin(false);
        Responsive.makeResponsive(logoHeader);
        
		username.setPlaceholder("Nutzername");
		username.setWidth(400, Unit.PIXELS);
		
		password.setPlaceholder("Passwort");
		password.setWidth(400, Unit.PIXELS);
		
		loginButton.addClickListener(e -> {
			authorize(username.getValue(), password.getValue());
		});
		
		loginButton.addShortcutListener(new ShortcutListener("Shortcut Name", ShortcutAction.KeyCode.ENTER, null) {
			@Override
			public void handleAction(Object sender, Object target) {
				authorize(username.getValue(), password.getValue());
			}
		});
		
		loginButton.setIcon(VaadinIcons.FORWARD);
		loginButton.setStyleName("loginButton");
	
		loginForm.addComponents(logoHeader, username, password, loginButton);
		loginForm.setComponentAlignment(logoHeader,  com.vaadin.ui.Alignment.MIDDLE_CENTER);
		loginForm.setComponentAlignment(username,    com.vaadin.ui.Alignment.MIDDLE_CENTER);
		loginForm.setComponentAlignment(password,    com.vaadin.ui.Alignment.MIDDLE_CENTER);
		loginForm.setComponentAlignment(loginButton, com.vaadin.ui.Alignment.MIDDLE_CENTER);
		
		Responsive.makeResponsive(loginForm);
		
		this.addComponent(loginForm);
		Responsive.makeResponsive(this);
	}
	
	private void authorize(String username, String password) {
		
		String error = "Loginsystem außer Betrieb";
		
		if(username.equals("") || password.equals("")) {
			error = "Username und Passwort müssen eingegeben werden";
		}
		
		Notification notif = new Notification(
			    "Warnung",
			    error,
			    Notification.TYPE_WARNING_MESSAGE);

			// Customize it
			notif.setDelayMsec(3000);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.setStyleName("warning");

			// Show it in the page
			notif.show(Page.getCurrent());
	}
}
