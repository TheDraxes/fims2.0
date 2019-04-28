package de.hwr.fims_gui.sfv;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.FimsUI;
import de.hwr.fims_gui.sfv.roadmap.*;

public class SFVView extends VerticalLayout implements View {
	
	Navigator navigator;
	DatabaseConnector connector;
	
	Label spacing = new Label();
	Label spacing2 = new Label();
	
	private Button next = new Button("weiter");
	private Button previous = new Button("zurück");
	private ProgressBar progressBar = new ProgressBar();
	private Button backButton = new Button("Verwaltung");
	private Button safeButton = new Button("Speichern");
	private Button clearButton = new Button("Felder Leeren");
	
	private Component activeComp;
	private int activeNumber = 1;
	
	private LayoutNumberMapping mapping = new LayoutNumberMapping();
	
	public SFVView (Navigator navigator, DatabaseConnector connector) {
		this.navigator = navigator;
		this.connector = connector;
		this.setSpacing(false);
		this.setMargin(false);
		this.setHeight(100, Unit.PERCENTAGE);
		
		next.addClickListener(      e -> {nextClicked();});
		next.setIcon(VaadinIcons.CHEVRON_RIGHT);
		next.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		
		previous.addClickListener(  e -> {previousClicked();});
		previous.setIcon(VaadinIcons.CHEVRON_LEFT);
		
		backButton.addClickListener(e -> {navigator.navigateTo(FimsUI.SFV_MAIN_VIEW);});
		backButton.setIcon(VaadinIcons.BULLETS);
		
		safeButton.setIcon(VaadinIcons.INBOX);
		safeButton.addClickListener(e -> {
			if(mapping.allDataFilled()) {
				Notification.show("Gespeichert!");
			} else {
				Notification not = new Notification(
						"Error!", 
						"Nicht alle Pflichtfelder wurden ausgefüllt. Pflichtfelder sind mit einem * markiert!", 
						Notification.Type.ERROR_MESSAGE, true
						);
				not.setPosition(Position.TOP_LEFT);
				not.show(Page.getCurrent());
			}
		});
		
		clearButton.setIcon(VaadinIcons.CLOSE);
		clearButton.addClickListener( e -> {
			mapping.removeThis(activeNumber);
		});
		
		progressBar.setWidth("80%");
		progressBar.setValue((float)0.0);
		
		spacing.setValue("");
		spacing.setWidth(200, Unit.PIXELS);
		/*
		spacing2.setValue("");
		spacing2.setWidth(100, Unit.PIXELS);
		*/
		HorizontalLayout buttonGroup = new HorizontalLayout();
		buttonGroup.addComponents(clearButton, backButton, safeButton,  spacing, previous, next);
		
		this.activeComp = mapping.getCompOnIndex(activeNumber);
		
		Label spacing3 = new Label();
		spacing3.setHeight(1, Unit.EM);
		
		this.addComponents(progressBar, spacing3,  activeComp, buttonGroup);
		this.setComponentAlignment(buttonGroup, Alignment.BOTTOM_RIGHT);
		this.setComponentAlignment(progressBar, Alignment.MIDDLE_CENTER);
		
		this.setExpandRatio(progressBar, 1);
		this.setExpandRatio(activeComp, 15);
		this.setExpandRatio(buttonGroup, 2);
	}
	
	public void nextClicked() {
		if(activeNumber == 7) {
			
			return;
		}
		activeNumber++;
		
		Component newComp = mapping.getCompOnIndex(activeNumber);
		this.replaceComponent(activeComp, newComp);
		
		activeComp = newComp;
		
		progressBar.setValue((float)progressBar.getValue() + (float)0.16666666666);	
	}
	
	public void previousClicked() {
		if(activeNumber == 1) {
			
			return;
		}
		activeNumber--;
		
		Component newComp = mapping.getCompOnIndex(activeNumber);
		this.replaceComponent(activeComp, newComp);
		
		activeComp = newComp;
	
		progressBar.setValue((float)progressBar.getValue() - (float)0.16666666666);
	}
}
