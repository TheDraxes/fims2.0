package de.hwr.fims_gui.sfv.roadmap;

import java.util.Optional;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.controller.DataController;

public class TrauerfeierRMLayout extends RoadMapPart {

	Label heading =  new Label("5. Trauerfeier");
	DataController controller;
	
	ComboBox<String> bestArt = new ComboBox<String>();
	ComboBox<String> grabstaette = new ComboBox<String>();
	ComboBox<String> redner = new ComboBox<String>();
	ComboBox<String> decke = new ComboBox<String>();
	ComboBox<String> tarlar = new ComboBox<String>();
	
	TextField ortBest = new TextField();
	TextField musik = new TextField();
	TextField sonstiges = new TextField();
	
	DateField datumBest = new DateField();
	
	RadioButtonGroup<String> aufbewahrung = new RadioButtonGroup<String>();
	
	TextArea bemerkung = new TextArea();
	
	public TrauerfeierRMLayout(DataController controller) {
		super();
		this.controller = controller;
		this.heading.addStyleName("heading");
		initTextFields();
		initComboBoxes();
		initSpecials();
		
		GridLayout layout = new GridLayout(5, 5);
		layout.setSpacing(true);
		//								  c  r  c  r					
		layout.addComponent(bestArt,      0, 0);
		layout.addComponent(grabstaette,  1, 0);
		layout.addComponent(ortBest,      2, 0);
		layout.addComponent(datumBest,    3, 0);
		
		layout.addComponent(aufbewahrung, 0, 1);
		layout.addComponent(musik,        1, 1);
		layout.addComponent(redner,       2, 1);
		
		
		layout.addComponent(decke,        0, 2);
		layout.addComponent(tarlar,       1, 2);
		layout.addComponent(sonstiges,    2, 2);
		
		layout.addComponent(bemerkung,    0, 3, 3, 4);
		
		
		this.addComponents(heading, layout);
	}
	
	private void initTextFields() {
		ortBest.setCaption("Ort*");
		musik.setCaption("Musik");
		sonstiges.setCaption("Sonstiges");
	}
	
	private void initComboBoxes() {
		bestArt.setCaption("Art der Bestattung*");
		bestArt.setItems(controller.getBestArtList());
		bestArt.setNewItemProvider(inputString -> {
		    controller.addArt((String)inputString);
		    // Update combobox content
		    bestArt.setItems(controller.getBestArtList());
		    return Optional.of(inputString);
		});
		
		grabstaette.setCaption("Grabstätte*"); 
		grabstaette.setItems(controller.getGrabstaetteList());
		grabstaette.setNewItemProvider(inputString -> {
		    controller.addGrabst((String)inputString);
		    // Update combobox content
		    grabstaette.setItems(controller.getGrabstaetteList());
		    return Optional.of(inputString);
		});
		
		redner.setCaption("Redner");
		redner.setItems(controller.getRednerList());
		redner.setNewItemProvider(inputString -> {
		    controller.addRedner((String)inputString);
		    // Update combobox content
		    redner.setItems(controller.getRednerList());
		    return Optional.of(inputString);
		});
		
		decke.setCaption("Decke");
		decke.setItems(controller.getDeckeList());
		decke.setNewItemProvider(inputString -> {
		    controller.addDecke((String)inputString);
		    // Update combobox content
		    decke.setItems(controller.getDeckeList());
		    return Optional.of(inputString);
		});
		
		tarlar.setCaption("Tarlar");
		tarlar.setItems(controller.getTarlarList());
		tarlar.setNewItemProvider(inputString -> {
		    controller.addTarlar((String)inputString);
		    // Update combobox content
		    tarlar.setItems(controller.getTarlarList());
		    return Optional.of(inputString);
		});
	}
	
	private void initSpecials() {
		bemerkung.setCaption("Bemerkungen");
		bemerkung.setSizeFull();
		
		aufbewahrung.setCaption("Aufbewahrung?*");
		aufbewahrung.setItems("Ja", "Nein");
		aufbewahrung.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		
		datumBest.setCaption("Datum der Bestattung");
	}
	
	@Override
	public boolean isFilled() {
		if(bestArt.isEmpty() || grabstaette.isEmpty() || ortBest.isEmpty() || aufbewahrung.isEmpty()) {
			System.out.println("[fehler] Trauerfeier nicht ausgefüllt!");
			return false;
		}
		return true;
	}

	public boolean safeData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		bestArt.setValue("");
		grabstaette.setValue("");
		redner.setValue("");
		decke.setValue("");
		tarlar.setValue("");
		ortBest.setValue("");
		musik.setValue("");
		sonstiges.setValue("");
		aufbewahrung.setValue("");
		bemerkung.setValue("");
	}
}
