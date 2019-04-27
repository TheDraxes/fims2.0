package de.hwr.fims_gui.sfv.roadmap;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class VerstorbenerRMLayout extends RoadMapPart {

	RadioButtonGroup<String> group = new RadioButtonGroup();
	
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
	
	public VerstorbenerRMLayout() {
		super();
		initSpecials();
		initTextFields();
		initComboBoxes();
		
		//HorizontalLayout layout1 = new HorizontalLayout(group, name, surname, geburt, geburtsOrt);
		//HorizontalLayout layout2 = new HorizontalLayout(plz, ort, str_hnr);
		
		GridLayout layout = new GridLayout(5, 5);
		layout.setSpacing(true);
		
		layout.addComponent(group,      0, 0);
		layout.addComponent(name,       1, 0);
		layout.addComponent(surname,    2, 0);
		
		layout.addComponent(plz,     0, 1);
		layout.addComponent(ort,     1, 1);
		layout.addComponent(str_hnr, 2, 1);
		
		layout.addComponent(geburt,     0, 2);
		layout.addComponent(geburtsOrt, 1, 2);
		
		
		this.addComponents(layout);
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

	
	private void initTextFields() {
		name.setCaption("Name");
		surname.setCaption("Vorname");
		plz.setCaption("Postleitzahl");
		
		str_hnr.setCaption("Straße & Hausnummer");
	}
	
	private void initComboBoxes() {
		geburtsOrt.setCaption("Geburtsort");
		ort.setCaption("Ort");
	}
	
	private void initSpecials() {
		group.setItems("Herr", "Frau");
		group.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		group.setCaption("Gechlecht");
		geburt.setCaption("Geburtsdatum");
	}
}
