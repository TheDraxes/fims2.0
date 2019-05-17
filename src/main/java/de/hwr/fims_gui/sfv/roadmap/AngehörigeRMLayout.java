package de.hwr.fims_gui.sfv.roadmap;

import java.util.ArrayList;
import java.util.Optional;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.ButtonRenderer;

import de.hwr.fims_backend.controller.DataController;
import de.hwr.fims_backend.data.customerdata.Angehoeriger;

public class AngehörigeRMLayout extends RoadMapPart {

	Label heading =  new Label("3. Angehörige");
	
	DataController controller;
	
	ArrayList<Angehoeriger> angehoerige = new ArrayList<Angehoeriger>();
	
	Button newEntry = new Button("Neuen Hinzufügen");
	
	Button safeEntry = new Button("Speichern");
	Button clear = new Button("Leeren");
	
	Grid<Angehoeriger> grid = new Grid<Angehoeriger>(Angehoeriger.class);
	
	TextField name = new TextField();
	TextField surname = new TextField();
	TextField plz = new TextField();
	TextField str_hnr = new TextField();
	TextField beziehung = new TextField();
	
	ComboBox ort = new ComboBox();
	
	@SuppressWarnings("unchecked")
	public AngehörigeRMLayout(DataController controller) {
		this.controller = controller;
		heading.addStyleName("heading");
		init();
		
		angehoerige.add(new Angehoeriger(true, "Vinzing", "Tim", "19077", "Rastow", "Straße", "", "", "Cousin"));
		
		newEntry.setIcon(VaadinIcons.PLUS_CIRCLE_O);
		newEntry.addClickListener(e -> {
			schowSubWindow();
		});
		
		
		grid.setColumns("name", "vorname", "bezArt");
		grid.setItems(angehoerige);
		grid.setWidth(70, Unit.PERCENTAGE);
		grid.addColumn(angeh -> "Löschen" , new ButtonRenderer(clickEvent ->  {
			
			angehoerige.remove(clickEvent.getItem());
			grid.setItems(angehoerige);
			
		}));
		
		this.addComponents(heading, newEntry, grid);
		this.setComponentAlignment(newEntry, Alignment.MIDDLE_LEFT);
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		name.setCaption("Name");
		surname.setCaption("Vorname");
		plz.setCaption("Postleitzahl");
		str_hnr.setCaption("Straße & Hausnummer");
		str_hnr.setWidth(100, Unit.PERCENTAGE);
		beziehung.setCaption("Beziehungsart");
		
		ort.setCaption("Ort");
		ort.setItems(controller.comboBoxContent(DataController.ort));
		ort.setNewItemProvider(inputString -> {
			
		    controller.addOrt((String)inputString);
		    
		    // Update combobox content
			ort.setItems(controller.getOrtList());
			
		    return Optional.of(inputString);
		    
		});
		
		safeEntry.addClickListener(e -> {
			Angehoeriger angehoeriger = new Angehoeriger();
			angehoeriger.setName(name.getValue());
			angehoeriger.setVorname(surname.getValue());
			angehoeriger.setPlz(plz.getValue());
			angehoeriger.setOrt((String)ort.getValue());
			angehoeriger.setStrasse(ConvertHelper.extractStreet(str_hnr.getValue()));
			//angehoeriger.setHausNr(hausNr);
			angehoeriger.setBezArt(beziehung.getValue());
			
			angehoerige.add(angehoeriger);
			
			grid.setItems(angehoerige);
			
		});
		clear.addClickListener(e -> {
			name.setValue("");
			surname.setValue("");
			plz.setValue("");
			str_hnr.setValue("");
			beziehung.setValue("");
			ort.setValue("");
		});
	}
	
	@Override
	public boolean isFilled() {
		if(angehoerige.isEmpty()) {
			System.out.println("[fehler] Angehörige nicht ausgefüllt!");
			return false;
		} else {
			return true;
		}
	}

	public Angehoeriger safeData() {
		
		String strasse = ConvertHelper.extractStreet(str_hnr.getValue());
		String hNR = ConvertHelper.extractHNR(str_hnr.getValue());
		
		return new Angehoeriger(false, name.getValue(), surname.getValue(), plz.getValue(), (String) ort.getValue(), strasse, hNR, "", beziehung.getValue());
	}

	private void schowSubWindow() {
		Window subWindow = new Window("Angehörigen erstellen");
		VerticalLayout layoutVert = new VerticalLayout();
		
		GridLayout layout = new GridLayout(5,5);
		
		layoutVert.addComponent(layout);
		layoutVert.setSizeFull();
		layoutVert.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
		
		layout.setSpacing(true);
		
        subWindow.setContent(layoutVert);
        
        layout.addComponent(name,      0, 0);
        layout.addComponent(surname,   1, 0);
        layout.addComponent(beziehung, 2, 0);
        
        layout.addComponent(plz,     0, 1);
        layout.addComponent(ort,     1, 1);
        layout.addComponent(str_hnr, 2, 1, 3, 1);
        
        layout.addComponent(clear,     0, 2);
        layout.addComponent(safeEntry, 1, 2);

        // Center it in the browser window
        subWindow.center();
        subWindow.setWidth(40, Unit.PERCENTAGE);
        subWindow.setHeight(40, Unit.PERCENTAGE);
       

        // Open it in the UI
        UI.getCurrent().addWindow(subWindow);
	}
	
	@Override
	public void clear() {
		angehoerige.removeAll(angehoerige);
		grid.setItems(angehoerige);
	}
}
