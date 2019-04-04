package de.hwr.fims_gui;

import com.vaadin.server.Responsive;
import com.vaadin.ui.VerticalLayout;

public class FooterLayout extends VerticalLayout {
	public FooterLayout() {
		this.setSizeFull();
		this.addStyleName("footer");
		setResponsive(true);
		Responsive.makeResponsive(this);
	}
}
