package de.hwr.fims_gui.sfv;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;

public class SFVView extends VerticalLayout implements View {
	
	Navigator navigator;
	DatabaseConnector connector;
	
	Button next = new Button("weiter");
	Button previous = new Button("zurück");
	ProgressBar progressBar = new ProgressBar();
	Button testButton = new Button("TestButton");
	
	public SFVView (Navigator navigator, DatabaseConnector connector) {
		this.navigator = navigator;
		this.connector = connector;
		this.setSpacing(false);
		this.setMargin(false);
		this.setHeight(100, Unit.PERCENTAGE);
		
		next.addClickListener(e -> {nextClicked();});
		previous.addClickListener(e -> {previousClicked();});
		
		progressBar.setSizeFull();
		progressBar.setValue((float)0.0);
		
		HorizontalLayout buttonGroup = new HorizontalLayout();
		buttonGroup.addComponents(previous, next);
		
		
		
		
		
		
		this.addComponents(progressBar, testButton, buttonGroup);
		this.setComponentAlignment(buttonGroup, Alignment.BOTTOM_RIGHT);
	}
	
	public void nextClicked() {
		progressBar.setValue((float)progressBar.getValue() + (float)0.2);
		//this.replaceComponent(testButton, new Button(""));
		
	}
	
	public void previousClicked() {
		progressBar.setValue((float)progressBar.getValue() - (float)0.2);
	}
}
