package de.hwr.fims_gui.sfv.roadmap;

import java.util.Optional;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import de.hwr.fims_backend.controller.DataController;

public class AuftraggeberRMLayout extends RoadMapPart {

	Label heading =  new Label("2. Auftraggeber");
	
	TextField name = new TextField();
	TextField surname = new TextField();
	TextField plz = new TextField();
	TextField str_hnr = new TextField();
	
	ComboBox ort = new ComboBox();
	
	TextField beruf = new TextField();
	TextField telefon = new TextField();
	TextField beziehung = new TextField();
	
	DataController controller;
	
	public AuftraggeberRMLayout(DataController controller) {
		super();
		this.controller = controller;
		initTextFields();
		initComboBoxes();
		heading.addStyleName("heading");
		
		GridLayout layout = new GridLayout(5, 5);
		layout.setSpacing(true);
		
		layout.addComponent(name,      0, 0);
		layout.addComponent(surname,   1, 0);
		layout.addComponent(telefon,   2, 0);
		layout.addComponent(beziehung, 3, 0);
		
		layout.addComponent(plz,     0, 1);
		layout.addComponent(ort,     1, 1);
		layout.addComponent(str_hnr, 2, 1, 3, 1);
	
		
		this.addComponents(heading, layout);
	}
	
	private void initTextFields() {
		name.setCaption("Name");
		surname.setCaption("Vorname");
		plz.setCaption("Postleitzahl");
		beruf.setCaption("Beruf");
		str_hnr.setCaption("Straße & Hausnummer");
		str_hnr.setWidth(100, Unit.PERCENTAGE);
		telefon.setCaption("Telefonnummer");
		beziehung.setCaption("Beziehungsart");
	}
	
	@SuppressWarnings("unchecked")
	private void initComboBoxes() {
		ort.setCaption("Ort");
		ort.setItems(controller.comboBoxContent(DataController.ort));
		ort.setNewItemProvider(inputString -> {
			
		    controller.addOrt((String)inputString);
		    
		    // Update combobox content
			ort.setItems(controller.getOrtList());
			
		    return Optional.of(inputString);
		    
		});
	}
	
	@Override
	public boolean isFilled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean safeData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		name.setValue("");
		surname.setValue("");
		plz.setValue("");
		str_hnr.setValue("");
		beruf.setValue("");
		telefon.setValue("");
		beziehung.setValue("");
	}
}
