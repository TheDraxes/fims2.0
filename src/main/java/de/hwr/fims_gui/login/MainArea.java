package de.hwr.fims_gui.login;

import javax.swing.GroupLayout.Alignment;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MainArea extends VerticalLayout {
	
	TextField username = new TextField();
	TextField password = new TextField();
	
	VerticalLayout loginForm = new VerticalLayout();
	
	Button loginButton = new Button("login");
	
	public MainArea() {
		this.setSizeFull();
		this.addStyleName("mainarea");
		
		username.setPlaceholder("Username");
		password.setPlaceholder("Password");
	
		loginForm.addComponents(username, password, loginButton);
		loginForm.setComponentAlignment(username, com.vaadin.ui.Alignment.MIDDLE_CENTER);
		loginForm.setComponentAlignment(password, com.vaadin.ui.Alignment.MIDDLE_CENTER);
		loginForm.setComponentAlignment(loginButton, com.vaadin.ui.Alignment.MIDDLE_CENTER);
		
		this.addComponent(loginForm);
	}
}
