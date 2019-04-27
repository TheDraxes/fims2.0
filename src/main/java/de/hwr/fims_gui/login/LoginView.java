package de.hwr.fims_gui.login;

import java.io.File;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Alignment;

import de.hwr.fims_backend.controller.LoginController;
import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_backend.dbconnector.ResultBoolean;
import de.hwr.fims_gui.FimsUI;
import de.hwr.fims_gui.FooterLayout;
import de.hwr.fims_gui.HeadBarLayout;
import de.hwr.fims_gui.interfaces.HasName;

@Theme("mytheme")
public class LoginView extends VerticalLayout implements View, HasName {
	
	Navigator navigator;
	
	TextField usernameField = new TextField();
	PasswordField passwordField = new PasswordField();
	Button loginButton = new Button("Anmelden");
	
	VerticalLayout loginForm = new VerticalLayout();
	
	String basepath = VaadinService.getCurrent()
            .getBaseDirectory().getAbsolutePath();
	
	LoginController loginController;
	
	public LoginView(Navigator navigator, LoginController loginController) {
		
		this.navigator = navigator;
		this.loginController = loginController;
		
		this.setMargin(false);
		this.setSpacing(false);
		
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
        
		usernameField.setPlaceholder("Nutzername");
		usernameField.setWidth(400, Unit.PIXELS);
		usernameField.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		usernameField.addStyleName("loginTextField");
		
		passwordField.setPlaceholder("Passwort");
		passwordField.setWidth(400, Unit.PIXELS);
		passwordField.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		passwordField.addStyleName("loginTextField");
		
		loginButton.addClickListener(e -> {
			authorize();
		});
		
		loginButton.addShortcutListener(new ShortcutListener("Shortcut Name", ShortcutAction.KeyCode.ENTER, null) {
			@Override
			public void handleAction(Object sender, Object target) {
				authorize();
			}
		});
		
		loginButton.setIcon(VaadinIcons.PAPERPLANE);
		loginButton.setStyleName(ValoTheme.BUTTON_LINK);
		loginButton.addStyleName("Button");
	
		loginForm.addComponents(logoHeader, usernameField, passwordField, loginButton);
		loginForm.setComponentAlignment(logoHeader,  	Alignment.MIDDLE_CENTER);
		loginForm.setComponentAlignment(usernameField,  Alignment.MIDDLE_CENTER);
		loginForm.setComponentAlignment(passwordField,  Alignment.MIDDLE_CENTER);
		loginForm.setComponentAlignment(loginButton, 	Alignment.MIDDLE_CENTER);
		
		Responsive.makeResponsive(loginForm);
		
		this.addComponent(loginForm);
		Responsive.makeResponsive(this);

	}
	
	private void authorize() {
		String username = usernameField.getValue();
		
		ResultBoolean login = loginController.pruefeLogin(username, passwordField.getValue());
		
		if(login.isSuccessful()) {
			VaadinSession.getCurrent().getSession().setAttribute("user", username);
			navigator.navigateTo(FimsUI.MAIN_VIEW);
		} else {
			blockLogin(login.getMessage());
		}
	}
	
	private void blockLogin(String errorMessage) {
		Notification notif = new Notification(
			    "Warnung",
			    errorMessage,
			    Notification.TYPE_WARNING_MESSAGE);

			// Customize it
			notif.setDelayMsec(3000);
			notif.setPosition(Position.TOP_LEFT);
			notif.setStyleName("warning");

			// Show it in the page
			notif.show(Page.getCurrent());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
