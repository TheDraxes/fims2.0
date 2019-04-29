package de.hwr.fims_gui.sfv;

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
	
	public boolean allDataFilled() {
		if(comp1.isFilled()) {
			System.out.println("Comp1 sieht gut aus!");
		}
		if(comp2.isFilled()) {
			System.out.println("Comp2 sieht gut aus!");
		}
		if(comp3.isFilled()) {
			System.out.println("Comp3 sieht gut aus!");
		}
		if(comp4.isFilled()) {
			System.out.println("Comp4 sieht gut aus!");
		}
		if(comp5.isFilled()) {
			System.out.println("Comp5 sieht gut aus!");
		}
		if(comp6.isFilled()) {
			System.out.println("Comp6 sieht gut aus!");
		}
		if(comp7.isFilled()) {
			System.out.println("Comp7 sieht gut aus!");
		}

		if(comp1.isFilled() && comp2.isFilled() && comp3.isFilled() && comp4.isFilled() && comp5.isFilled() && comp6.isFilled() && comp7.isFilled()) {
			System.out.println("passt");
			return true;
		} else {
			System.out.println("passt nicht");
			return false;
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
