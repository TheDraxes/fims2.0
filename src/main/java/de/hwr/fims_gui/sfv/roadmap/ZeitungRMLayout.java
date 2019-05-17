package de.hwr.fims_gui.sfv.roadmap;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;

import com.vaadin.icons.VaadinIcons;
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
import de.hwr.fims_backend.data.advertisement.Zeitungsanzeige;
import de.hwr.fims_backend.data.customerdata.Angehoeriger;

public class ZeitungRMLayout extends RoadMapPart {

	Label heading =  new Label("7. Zeitungsanzeige");
	DataController controller;
	
	ArrayList<Zeitungsanzeige> zeitungsanzeigen = new ArrayList<Zeitungsanzeige>();
	
	Button newEntry = new Button("Neuen Hinzufügen");
	
	Button safeEntry = new Button("Speichern");
	Button clear = new Button("Leeren");
	
	Grid<Zeitungsanzeige> grid = new Grid<Zeitungsanzeige>(Zeitungsanzeige.class);
	
	ComboBox anzeigenart = new ComboBox();
	ComboBox zeitung = new ComboBox();
	
	TextField preis = new TextField();
	
	TextField groesse = new TextField();
	
	Label gesamtpreis = new Label();
	
	DecimalFormat format = new DecimalFormat("#######.##"); 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ZeitungRMLayout(DataController controller) {
		super();
		this.controller = controller;
		this.heading.addStyleName("heading");
		init();
		
		grid.setColumns("art", "zeitung", "groesse", "preis");
		
		newEntry.setIcon(VaadinIcons.PLUS_CIRCLE_O);
		newEntry.addClickListener(e -> {
			schowSubWindow();
		});
		
		grid.setItems(zeitungsanzeigen);
		grid.setWidth(70, Unit.PERCENTAGE);
		grid.addColumn(angeh -> "Löschen" , new ButtonRenderer(clickEvent ->  {
			
			zeitungsanzeigen.remove(clickEvent.getItem());
			grid.setItems(zeitungsanzeigen);
			
			double price = 0;
			for(Zeitungsanzeige anz : zeitungsanzeigen) {
				price += anz.getPreis();
			}
			gesamtpreis.setValue("Gesamtpreis: " + format.format(price) + "€");
		}));
		
		safeEntry.addClickListener(e -> {
			
			zeitungsanzeigen.add(new Zeitungsanzeige(Double.parseDouble(preis.getValue()), (String)anzeigenart.getValue(), (String)groesse.getValue(), (String)zeitung.getValue()));
			grid.setItems(zeitungsanzeigen);
			
			double price = 0;
			for(Zeitungsanzeige anz : zeitungsanzeigen) {
				price += anz.getPreis();
			}
			gesamtpreis.setValue("Gesamtpreis: " + format.format(price) + "€");
			
		});
		clear.addClickListener(e -> {
			anzeigenart.setValue("");
			zeitung.setValue("");
			groesse.setValue("");
			preis.setValue("");
		});
		
		this.addComponents(heading, newEntry, grid, gesamtpreis);
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		anzeigenart.setCaption("Art der Anzeige");
		anzeigenart.setItems(controller.getAnzeigenart());
		anzeigenart.setNewItemProvider(inputString -> {
			
		    controller.addAnzeigenart((String)inputString);
		    
		    // Update combobox content
		    anzeigenart.setItems(controller.getZeitungenList());
			
		    return Optional.of(inputString);
		    
		});
		
		zeitung.setCaption("Zeitung");
		zeitung.setItems(controller.getZeitungenList());
		zeitung.setNewItemProvider(inputString -> {
			
		    controller.addOrt((String)inputString);
		    
		    // Update combobox content
		    zeitung.setItems(controller.getZeitungenList());
			
		    return Optional.of(inputString);
		    
		});
		
		groesse.setCaption("Größe");
		preis.setCaption("Preis");
	}
	
	public void schowSubWindow() {
		Window subWindow = new Window("Anzeige erstellen");
		VerticalLayout layoutVert = new VerticalLayout();
		
		GridLayout layout = new GridLayout(5,5);
		
		layoutVert.addComponent(layout);
		layoutVert.setSizeFull();
		layoutVert.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
		
		layout.setSpacing(true);
		
        subWindow.setContent(layoutVert);
        
        layout.addComponent(anzeigenart,      0, 0);
        layout.addComponent(zeitung,   1, 0);
        
        layout.addComponent(groesse,     0, 1);
        layout.addComponent(preis,     1, 1);
        
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
	public boolean isFilled() {
		return true;
	}

	public ArrayList safeData() {
		if(zeitungsanzeigen.isEmpty()) {
			return null;
		} else {
			return zeitungsanzeigen;
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}

}
