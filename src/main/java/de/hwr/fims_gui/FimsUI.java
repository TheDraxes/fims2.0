package de.hwr.fims_gui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import de.hwr.fims_gui.listener.ViewChangedListener;
import de.hwr.fims_gui.login.LoginView;
import de.hwr.fims_gui.main.StartPageView;

@Theme("mytheme")
public class FimsUI extends UI {

	Navigator navigator;
	
    public static final String LOGIN_VIEW = "Login";
    public static final String MAIN_VIEW = "Main";
	
	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("FIMS: Login");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView(LOGIN_VIEW, new CompleteUI(new LoginView(navigator)));
        navigator.addView(MAIN_VIEW, new CompleteUI(new StartPageView(navigator)));
        navigator.navigateTo(LOGIN_VIEW);
        navigator.addViewChangeListener(new ViewChangedListener());
		
	}
	
	@WebServlet(urlPatterns = "/*", name = "UIServlet")
    @VaadinServletConfiguration(ui = FimsUI.class, productionMode = false)
    public static class UIServlet extends VaadinServlet {
		
    }

}
