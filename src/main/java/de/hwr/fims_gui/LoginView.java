package de.hwr.fims_gui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.hwr.fims_gui.login.FooterLayout;
import de.hwr.fims_gui.login.HeadBarLayout;
import de.hwr.fims_gui.login.MainArea;


@Theme("mytheme")
public class LoginView extends UI{

	@Override
	protected void init(VaadinRequest request) {
		
		VerticalLayout layout = new VerticalLayout();
		layout.setHeight("100%");
		layout.setMargin(false);
		layout.setSpacing(false);
		
		
		
		HeadBarLayout headbar = new HeadBarLayout();
		layout.addComponent(headbar);
		layout.setExpandRatio(headbar, 1);
		
		MainArea mainArea = new MainArea();
		layout.addComponent(mainArea);
		layout.setExpandRatio(mainArea, 6);
		
		FooterLayout footerLayout = new FooterLayout();
		layout.addComponent(footerLayout);
		layout.setExpandRatio(footerLayout, 2);
		
		setContent(layout);
		
	}
	
	
	@WebServlet(urlPatterns = "/*", name = "LoginServlet")
    @VaadinServletConfiguration(ui = LoginView.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
		
    }

}
