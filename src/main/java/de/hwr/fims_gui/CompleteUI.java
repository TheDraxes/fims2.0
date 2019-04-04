package de.hwr.fims_gui;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

import de.hwr.fims_gui.login.MainArea;

public class CompleteUI extends VerticalLayout implements View{
	
	Navigator navigator;
	
	public CompleteUI(Layout content) {
		
		this.setHeight("100%");
		this.setMargin(false);
		this.setSpacing(false);

		HeadBarLayout headbar = new HeadBarLayout();
		this.addComponent(headbar);
		this.setExpandRatio(headbar, 1);
		
		MainArea mainArea = new MainArea(content);
		this.addComponent(mainArea);
		this.setExpandRatio(mainArea, 8);
		
		FooterLayout footerLayout = new FooterLayout();
		this.addComponent(footerLayout);
		this.setExpandRatio(footerLayout, 2);
	}
}
