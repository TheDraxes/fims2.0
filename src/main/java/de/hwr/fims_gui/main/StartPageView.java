package de.hwr.fims_gui.main;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class StartPageView extends VerticalLayout implements View {

	private Navigator navigator;
	
	public StartPageView(Navigator navigator) {
		this.navigator = navigator;
		
		Button a = new Button("test");
		a.addClickListener(e -> {
			this.addComponent(new Label("Username: " + (String)VaadinSession.getCurrent().getSession().getAttribute("user")));
		});
		
		this.addComponent(a);
	}

}
