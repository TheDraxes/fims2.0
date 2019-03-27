package de.hwr.fims_gui.login;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class LoginView extends VerticalLayout implements View{
	
	Navigator navigator;
	
	public LoginView(Navigator navigator) {
		
		this.navigator = navigator;
		
		this.setHeight("100%");
		this.setMargin(false);
		this.setSpacing(false);

		HeadBarLayout headbar = new HeadBarLayout();
		this.addComponent(headbar);
		this.setExpandRatio(headbar, 1);
		
		MainArea mainArea = new MainArea(navigator);
		this.addComponent(mainArea);
		this.setExpandRatio(mainArea, 8);
		
		FooterLayout footerLayout = new FooterLayout();
		this.addComponent(footerLayout);
		this.setExpandRatio(footerLayout, 2);
	}
}
