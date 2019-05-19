package de.hwr.fims_gui.sfv.roadmap;

import java.util.Optional;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

import de.hwr.fims_backend.controller.DataController;
import de.hwr.fims_backend.data.customerdata.Verstorbener;
import de.hwr.fims_backend.dbconnector.DatabaseConnector;

public class VerstorbenerRMLayout extends RoadMapPart {

	RadioButtonGroup<String> group = new RadioButtonGroup();
	
	Label heading =  new Label("1. Verstorbener");
	
	TextField name = new TextField();
	TextField surname = new TextField();
	TextField plz = new TextField();
	ComboBox ort = new ComboBox();
	TextField str_hnr = new TextField();
	
	DateField geburt = new DateField();
	ComboBox geburtsOrt = new ComboBox();
	
	DateField tod = new DateField();
	ComboBox todOrt = new ComboBox();
	
	ComboBox familienstand = new ComboBox<>();
	TextField anzahlKinder = new TextField();
	TextField beruf = new TextField();
	ComboBox konfession = new ComboBox<>();
	ComboBox krankenkasse = new ComboBox<>();
	ComboBox rentenversicherung = new ComboBox<>();
	
	DataController controller;
	
	public VerstorbenerRMLayout(DataController controller) {
		super();
		this.controller = controller;
		initSpecials();
		initTextFields();
		initComboBoxes();
		
		heading.addStyleName("heading");
		
		GridLayout layout = new GridLayout(5, 5);
		layout.setSpacing(true);
		
		layout.addComponent(group,       0, 0);
		layout.addComponent(name,        1, 0);
		layout.addComponent(surname,     2, 0);
		layout.addComponent(anzahlKinder,3, 0);
		
		layout.addComponent(plz,     0, 1);
		layout.addComponent(ort,     1, 1);
		layout.addComponent(str_hnr, 2, 1, 3, 1);
		//layout.addComp
		//layout.add
		
		layout.addComponent(geburt,        0, 2);
		layout.addComponent(geburtsOrt,    1, 2);
		layout.addComponent(familienstand, 2, 2);
		
		layout.addComponent(tod,     0, 3);
		layout.addComponent(todOrt,  1, 3);
		layout.addComponent(beruf,   2, 3);
		
		layout.addComponent(krankenkasse,      0, 4);
		layout.addComponent(rentenversicherung,1, 4);
		layout.addComponent(konfession,        2, 4);
		
		this.addComponents(heading, layout);
	}
	
	@Override
	public boolean isFilled() {
		
		if(
				group.isEmpty() || 
				name.isEmpty() || 
				surname.isEmpty() || 
				plz.isEmpty() || 
				ort.isEmpty() || 
				str_hnr.isEmpty() || 
				geburt.isEmpty() || 
				geburtsOrt.isEmpty() ||
				tod.isEmpty() ||
				todOrt.isEmpty() 
			) {
				System.out.println("[fehler] Verstorbener nicht ausgefüllt!");
				return false;
			} else {
				return true;
			}
	}

	public Verstorbener safeData() {
		String strasse = ConvertHelper.extractStreet(str_hnr.getValue());
		String hNR = ConvertHelper.extractHNR(str_hnr.getValue());
		if(strasse.equals("") || hNR.equals("")) {
			Notification.show("Fehler", "Geben sie Straße und Hausnummer mit ',' getrennt ein!", Type.ASSISTIVE_NOTIFICATION.WARNING_MESSAGE);
			return null;
		}
		
		int anzahlKinderInt = 0;
		
		if(!(anzahlKinder.getValue() == null && anzahlKinder.getValue().equals(""))) {
			try {
				anzahlKinderInt = Integer.parseInt(anzahlKinder.getValue());
			} catch (Exception e){
				Notification.show("Fehler", "anzahl der Kinder nicht richtig eingegeben", Type.ASSISTIVE_NOTIFICATION.WARNING_MESSAGE);
				return null;
			}
		}
		Verstorbener verst = new Verstorbener(false, name.getValue(), surname.getValue(), plz.getValue(), (String) ort.getValue(), strasse, hNR, beruf.getValue(), "", ConvertHelper.convertVaadinDate(geburt.getValue()), (String)geburtsOrt.getValue(), ConvertHelper.convertVaadinDate(tod.getValue()), (String) todOrt.getValue(), (String)familienstand.getValue(), anzahlKinderInt, 0, (String)konfession.getValue(),(String) krankenkasse.getValue(), (String)rentenversicherung.getValue());
		return verst;
	}

	
	private void initTextFields() {
		name.setCaption("Name*");
		surname.setCaption("Vorname*");
		plz.setCaption("Postleitzahl*");
		beruf.setCaption("Beruf");
		str_hnr.setCaption("Straße & Hausnummer*");
		str_hnr.setWidth(100, Unit.PERCENTAGE);
		
		anzahlKinder.setCaption("Anzahl der Kinder");
	}
	
	@SuppressWarnings("unchecked")
	private void initComboBoxes() {
		geburtsOrt.setCaption("Geburtsort*");
		geburtsOrt.setItems(controller.getOrtList());
		geburtsOrt.setNewItemProvider(inputString -> {
			
		    controller.addOrt((String)inputString);
		    
		    // Update combobox content
			geburtsOrt.setItems(controller.getOrtList());
			ort.setItems(controller.getOrtList());
			todOrt.setItems(controller.getOrtList());
			
		    return Optional.of(inputString);
		    
		});
		
		ort.setCaption("Ort*");
		ort.setItems(controller.comboBoxContent(DataController.ort));
		ort.setNewItemProvider(inputString -> {
			
		    controller.addOrt((String)inputString);
		    
		    // Update combobox content
		    geburtsOrt.setItems(controller.getOrtList());
			ort.setItems(controller.getOrtList());
			todOrt.setItems(controller.getOrtList());
			
		    return Optional.of(inputString);
		    
		});
		
		familienstand.setCaption("Familienstand");
		familienstand.setItems(controller.getFamilienstandList());
		familienstand.setNewItemProvider(inputString -> {
		    controller.addFamilienstand((String)inputString);
		    // Update combobox content
		    familienstand.setItems(controller.getFamilienstandList());
		    return Optional.of(inputString);
		});
		
		todOrt.setCaption("Todesort*");
		todOrt.setItems(controller.getOrtList());
		todOrt.setNewItemProvider(inputString -> {
		    controller.addOrt((String)inputString);
		    
		    // Update combobox content
		    geburtsOrt.setItems(controller.getOrtList());
			ort.setItems(controller.getOrtList());
			todOrt.setItems(controller.getOrtList());
			
		    return Optional.of(inputString);
		});
		
		krankenkasse.setCaption("Krankenkasse");
		krankenkasse.setItems(controller.getKrankenkasseList());
		krankenkasse.setNewItemProvider(inputString -> {
		    controller.addKrankenkasse((String)inputString);
		    // Update combobox content
		    krankenkasse.setItems(controller.getKrankenkasseList());
		    return Optional.of(inputString);
		});
		
		rentenversicherung.setCaption("Rentenversicherung");
		rentenversicherung.setItems(controller.getRentenversicherungList());
		rentenversicherung.setNewItemProvider(inputString -> {
		    controller.addRentenversicherung((String)inputString);
		    // Update combobox content
		    rentenversicherung.setItems(controller.getRentenversicherungList());
		    return Optional.of(inputString);
		});
		
		konfession.setCaption("Konfession");
		konfession.setItems(controller.getKonfessionList());
		konfession.setNewItemProvider(inputString -> {
		    controller.addKonfession((String)inputString);
		    // Update combobox content
		    konfession.setItems(controller.getKonfessionList());
		    return Optional.of(inputString);
		});
	}
	
	private void initSpecials() {
		
		group.setItems("Herr", "Frau");
		group.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		group.setCaption("Gechlecht*");
		geburt.setCaption("Geburtsdatum*");
		tod.setCaption("Todesdatum*");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		familienstand.setValue("");
		anzahlKinder.setValue("");
		name.setValue("");
		surname.setValue("");
		plz.setValue("");
		ort.setValue("");
		str_hnr.setValue("");
		geburtsOrt.setValue("");
		todOrt.setValue("");
		beruf.setValue("");
		konfession.setValue("");
		krankenkasse.setValue("");
		rentenversicherung.setValue("");
	}
}
