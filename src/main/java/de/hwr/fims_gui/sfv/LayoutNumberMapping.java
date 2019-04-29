package de.hwr.fims_gui.sfv;

import java.util.Arrays;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import de.hwr.fims_backend.controller.DataController;
import de.hwr.fims_gui.sfv.roadmap.*;

public class LayoutNumberMapping {

	DataController controller;
	
	RoadMapPart comp1;
	RoadMapPart comp2;
	RoadMapPart comp3;
	RoadMapPart comp4;
	RoadMapPart comp5;
	RoadMapPart comp6;
	RoadMapPart comp7;
	
	public LayoutNumberMapping(DataController controller) {
		this.controller = controller;
		
		comp1 = new VerstorbenerRMLayout(controller);
		comp2 = new AuftraggeberRMLayout(controller);
		comp3 = new AngehörigeRMLayout(controller);
		comp4 = new AbholungRMLayout(controller);
		comp5 = new TrauerfeierRMLayout(controller);
		comp6 = new BlumenRMLayout(controller);
		comp7 = new ZeitungRMLayout(controller);
	}
	
	public Component getCompOnIndex(int i) {
		
		if(i == 1)return comp1;
		if(i == 2)return comp2;
		if(i == 3)return comp3;
		if(i == 4)return comp4;
		if(i == 5)return comp5;
		if(i == 6)return comp6;
		if(i == 7)return comp7;
		
		return null;
		
	}
	
	public FilledCheckResult allDataFilled() {
		
		String[] arr = new String[1];
		
		if(!comp1.isFilled()) {
			arr = extendArray(arr, "Verstorbener");
		}
		if(!comp2.isFilled()) {
			arr = extendArray(arr, "Auftraggeber");
		}
		if(!comp3.isFilled()) {
			arr = extendArray(arr, "Angehöriger");
		}
		if(!comp4.isFilled()) {
			arr = extendArray(arr, "Abholung");
		}
		if(!comp5.isFilled()) {
			arr = extendArray(arr, "Trauerfeier");
		}
		if(!comp6.isFilled()) {
			arr = extendArray(arr, "Blumen");
		}
		if(!comp7.isFilled()) {
			arr = extendArray(arr, "Zeitungsanzeigen");
		}

		System.out.println(Arrays.asList(arr).toString());
		
		if(comp1.isFilled() && comp2.isFilled() && comp3.isFilled() && comp4.isFilled() && comp5.isFilled() && comp6.isFilled() && comp7.isFilled()) {
			System.out.println("passt");
			return new FilledCheckResult(null, true);
		} else {
			System.out.println("passt nicht");
			return new FilledCheckResult(arr, false);
		}
	}
	
	public void removeAll() {
		comp1.clear();
		comp2.clear();
		comp3.clear();
		comp4.clear();
		comp5.clear();
		comp6.clear();
		comp7.clear();
	}
	
	public void removeThis(int number) {
		if(number == 1)comp1.clear();
		if(number == 2)comp2.clear();
		if(number == 3)comp3.clear();
		if(number == 4)comp4.clear();
		if(number == 5)comp5.clear();
		if(number == 6)comp6.clear();
		if(number == 6)comp7.clear();
	}
	
	private String[] extendArray(String[] arr, String newEnt) {
		String[] newArr = new String[arr.length+1];
		
		for(int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
		}
		
		newArr[arr.length-1] = newEnt;
		
		return newArr;
	}
	
	public Component getComp1() {
		return comp1;
	}
	public void setComp1(Component comp1) {
		this.comp1 = (RoadMapPart) comp1;
	}
	public Component getComp2() {
		return comp2;
	}
	public void setComp2(Component comp2) {
		this.comp2 = (RoadMapPart) comp2;
	}
	public Component getComp3() {
		return comp3;
	}
	public void setComp3(Component comp3) {
		this.comp3 = (RoadMapPart) comp3;
	}
	public Component getComp4() {
		return comp4;
	}
	public void setComp4(Component comp4) {
		this.comp4 = (RoadMapPart) comp4;
	}
	public Component getComp5() {
		return comp5;
	}
	public void setComp5(Component comp5) {
		this.comp5 = (RoadMapPart) comp5;
	}
	public Component getComp6() {
		return comp6;
	}
	public void setComp6(Component comp6) {
		this.comp6 = (RoadMapPart) comp6;
	}
	
	
	
}
