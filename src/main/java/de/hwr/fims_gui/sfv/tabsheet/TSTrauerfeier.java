package de.hwr.fims_gui.sfv.tabsheet;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class TSTrauerfeier {
	
	GridLayout layout = new GridLayout(5, 5);
	
	ComboBox<String> bestArt = new ComboBox<String>();
	ComboBox<String> grabstaette = new ComboBox<String>();
	ComboBox<String> redner = new ComboBox<String>();
	ComboBox<String> decke = new ComboBox<String>();
	ComboBox<String> talar = new ComboBox<String>();
	
	TextField ortBest = new TextField();
	TextField musik = new TextField();
	TextField sonstiges = new TextField();
	
	DateField datumBest = new DateField();
	
	RadioButtonGroup<String> aufbewahrung = new RadioButtonGroup<String>();
	
	TextArea bemerkung = new TextArea();
	
	public GridLayout init(boolean isReadable) {
		initSpecials();
		
		// Set properties
		bestArt.setCaption("Art der Bestattung*");
		bestArt.setReadOnly(isReadable);
		
		grabstaette.setCaption("Grabstätte*");
		grabstaette.setReadOnly(isReadable);
		
		redner.setCaption("Redner");
		redner.setReadOnly(isReadable);
		
		decke.setCaption("Decke");
		decke.setReadOnly(isReadable);
		
		talar.setCaption("Talar");
		talar.setReadOnly(isReadable);
		
		ortBest.setCaption("Ort*");
		ortBest.setReadOnly(isReadable);
		
		musik.setCaption("Musik");
		musik.setReadOnly(isReadable);
		
		sonstiges.setCaption("Sonstiges");
		sonstiges.setReadOnly(isReadable);
		
		datumBest.setCaption("Datum der Bestattung");
		datumBest.setReadOnly(isReadable);
		
		aufbewahrung.setItems("Ja", "Nein");
		aufbewahrung.setCaption("Aufbewahrung? *");
		aufbewahrung.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		aufbewahrung.setReadOnly(isReadable);
		
		bemerkung.setCaption("Bemerkungen");
		bemerkung.setReadOnly(isReadable);
		
		// Add components to layout
		layout.setSpacing(true);
		
		//1st line
		layout.addComponent(bestArt, 	   0, 0);
		layout.addComponent(grabstaette,   1, 0);
		layout.addComponent(ortBest,	   2, 0);
		layout.addComponent(datumBest, 	   3, 0);
		
		//2nd line
		layout.addComponent(aufbewahrung,  0, 1);
		layout.addComponent(musik,		   1, 1);
		layout.addComponent(redner,		   2, 1);
		
		//3rd line
		layout.addComponent(decke,			0, 2);
		layout.addComponent(talar,		   	1, 2);
		layout.addComponent(sonstiges,		2, 2);
	
		//4th line
		layout.addComponent(bemerkung,		0, 3, 3, 4);
		//Notification.show("Hier sollten Trauerfeierdaten stehen :)");
		
		return layout;
		
	}
	private void initSpecials() {
		bemerkung.setCaption("Bemerkungen");
		bemerkung.setSizeFull();
		
		aufbewahrung.setCaption("Aufbewahrung?*");
		aufbewahrung.setItems("Ja", "Nein");
		aufbewahrung.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		
		datumBest.setCaption("Datum der Bestattung");
	}

}
