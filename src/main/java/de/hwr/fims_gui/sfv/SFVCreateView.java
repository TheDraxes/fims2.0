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

import de.hwr.fims_backend.controller.DataController;
import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.FimsUI;
import de.hwr.fims_gui.sfv.roadmap.*;

public class SFVCreateView extends VerticalLayout implements View {
	
	Navigator navigator;
	DataController controller;
	
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
	
	private LayoutNumberMapping mapping;
	
	public SFVCreateView (Navigator navigator, DataController controller) {
		this.navigator = navigator;
		this.controller = controller;
		this.mapping = new LayoutNumberMapping(controller);
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
			
			String[] arr = mapping.allDataFilled().getRoadsNotFilled();
			boolean allFilled = mapping.allDataFilled().getAllFilled();
			
			if(allFilled) {
				controller.insertAuftrag(mapping.safeAuftrag());
				Notification not = new Notification(
						"Geschafft!", 
						"Eintrag wurde gespeichert!", 
						Notification.Type.HUMANIZED_MESSAGE, true
						);
				not.setPosition(Position.TOP_LEFT);
				not.show(Page.getCurrent());
				
			} else {
				
				String str = "Nicht alle Pflichtfelder wurden ausgefüllt. Pflichtfelder sind mit einem * markiert! <br> Unvollständige Seiten: <br> <ul>";
				for(int i = 0; i < arr.length - 1; i ++) {
					str += "<li>" + arr[i] + "</li>";
				}
			
				str+="</ul>";
				
				Notification not = new Notification(
						"Error!", 
						str, 
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
		
		if(activeNumber == 7 || activeNumber == 3) {
			clearButton.setEnabled(false);
		} else {
			clearButton.setEnabled(true);
		}
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
