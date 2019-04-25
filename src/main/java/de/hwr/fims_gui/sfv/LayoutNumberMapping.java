package de.hwr.fims_gui.sfv;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import de.hwr.fims_gui.sfv.roadmap.*;

public class LayoutNumberMapping {

	Component comp1 = new VerstorbenerRMLayout();
	Component comp2 = new AuftraggeberRMLayout();
	Component comp3 = new VerticalLayout();
	Component comp4 = new VerticalLayout();
	Component comp5 = new VerticalLayout();
	Component comp6 = new VerticalLayout();
	
	public Component getCompOnIndex(int i) {
		
		if(i == 1)return comp1;
		if(i == 2)return comp2;
		if(i == 3)return comp3;
		if(i == 4)return comp4;
		if(i == 5)return comp5;
		if(i == 6)return comp6;
		
		return null;
		
	}
	public Component getComp1() {
		return comp1;
	}
	public void setComp1(Component comp1) {
		this.comp1 = comp1;
	}
	public Component getComp2() {
		return comp2;
	}
	public void setComp2(Component comp2) {
		this.comp2 = comp2;
	}
	public Component getComp3() {
		return comp3;
	}
	public void setComp3(Component comp3) {
		this.comp3 = comp3;
	}
	public Component getComp4() {
		return comp4;
	}
	public void setComp4(Component comp4) {
		this.comp4 = comp4;
	}
	public Component getComp5() {
		return comp5;
	}
	public void setComp5(Component comp5) {
		this.comp5 = comp5;
	}
	public Component getComp6() {
		return comp6;
	}
	public void setComp6(Component comp6) {
		this.comp6 = comp6;
	}
	
	
	
}
