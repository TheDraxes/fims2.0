package de.hwr.fims_gui.sfv;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.sfv.roadmap.*;

public class SFVView extends VerticalLayout implements View {
	
	Navigator navigator;
	DatabaseConnector connector;
	
	private Button next = new Button("weiter");
	private Button previous = new Button("zurück");
	private ProgressBar progressBar = new ProgressBar();
	private Button testButton = new Button("TestButton");
	
	private Component activeComp;
	private int activeNumber = 1;
	
	private LayoutNumberMapping mapping = new LayoutNumberMapping();
	
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
		
		
		
		this.activeComp = mapping.getCompOnIndex(activeNumber);
		
		
		
		this.addComponents(progressBar, activeComp, buttonGroup);
		this.setComponentAlignment(buttonGroup, Alignment.BOTTOM_RIGHT);
	}
	
	public void nextClicked() {
		activeNumber++;
		
		Component newComp = mapping.getCompOnIndex(activeNumber);
		this.replaceComponent(activeComp, newComp);
		
		activeComp = newComp;
		
		progressBar.setValue((float)progressBar.getValue() + (float)0.2);	
	}
	
	public void previousClicked() {
		progressBar.setValue((float)progressBar.getValue() - (float)0.2);
		activeNumber--;
		
		Component newComp = mapping.getCompOnIndex(activeNumber);
		this.replaceComponent(activeComp, newComp);
		
		activeComp = newComp;
	}
}
