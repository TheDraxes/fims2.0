package de.hwr.fims_gui.sfv;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.sfv.roadmap.*;

public class SFVView extends VerticalLayout implements View {
	
	Navigator navigator;
	DatabaseConnector connector;
	
	Label lab = new Label();
	
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
		
		progressBar.setWidth("80%");
		progressBar.setValue((float)0.0);
		
		lab.setValue(""+activeNumber);
		
		HorizontalLayout buttonGroup = new HorizontalLayout();
		buttonGroup.addComponents(lab, previous, next);
		
		
		
		this.activeComp = mapping.getCompOnIndex(activeNumber);
		
		Label spacing = new Label();
		spacing.setHeight(1, Unit.EM);
		
		this.addComponents(progressBar, spacing,  activeComp, buttonGroup);
		this.setComponentAlignment(buttonGroup, Alignment.BOTTOM_RIGHT);
		this.setComponentAlignment(progressBar, Alignment.MIDDLE_CENTER);
		
		this.setExpandRatio(progressBar, 1);
		this.setExpandRatio(activeComp, 15);
		this.setExpandRatio(buttonGroup, 2);
	}
	
	public void nextClicked() {
		activeNumber++;
		this.lab.setValue(activeNumber+"");
		Component newComp = mapping.getCompOnIndex(activeNumber);
		this.replaceComponent(activeComp, newComp);
		
		activeComp = newComp;
		
		progressBar.setValue((float)progressBar.getValue() + (float)0.2);	
	}
	
	public void previousClicked() {
		this.lab.setValue(activeNumber+"");
		progressBar.setValue((float)progressBar.getValue() - (float)0.2);
		activeNumber--;
		
		Component newComp = mapping.getCompOnIndex(activeNumber);
		this.replaceComponent(activeComp, newComp);
		
		activeComp = newComp;
	}
}
