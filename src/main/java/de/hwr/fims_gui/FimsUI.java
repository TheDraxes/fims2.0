package de.hwr.fims_gui;

import java.util.concurrent.TimeUnit;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import de.hwr.fims_backend.dbconnector.DatabaseConnector;
import de.hwr.fims_gui.listener.ViewChangedListener;
import de.hwr.fims_gui.login.LoginView;
import de.hwr.fims_gui.main.StartPageView;
import de.hwr.fims_gui.session.SessionHandler;

@Theme("mytheme")
public class FimsUI extends UI {

	Navigator navigator;
	DatabaseConnector connector;
	
    public static final String LOGIN_VIEW = "Login";
    public static final String MAIN_VIEW = "Start";
	
	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("FIMS: Login");
		
        // Create a navigator to control the views
        navigator = new Navigator(this, this);
        navigator.addViewChangeListener(new ViewChangedListener());
        
        //Create db connector to control databaseconnection
        connector = new DatabaseConnector();
        
        // Create and register the views
        navigator.addView(LOGIN_VIEW, new CompleteUI(new LoginView(navigator, connector)));
        navigator.addView(MAIN_VIEW, new CompleteUI(new StartPageView(navigator, connector)));
        
        
        
        
        
        if(SessionHandler.isLoggedIn()) {
        	VaadinSession.getCurrent().getSession().setMaxInactiveInterval( ( int ) TimeUnit.MINUTES.toSeconds( 30 ) );
        	navigator.navigateTo(MAIN_VIEW);
        } else {
        	VaadinSession.getCurrent().getSession().setMaxInactiveInterval( ( int ) TimeUnit.MINUTES.toSeconds( 240 ) );
        	navigator.navigateTo(LOGIN_VIEW);
        }
	}
	
	@WebServlet(urlPatterns = "/*", name = "UIServlet")
    @VaadinServletConfiguration(ui = FimsUI.class, productionMode = false)
    public static class UIServlet extends VaadinServlet {
		
    }

}
