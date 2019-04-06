package de.hwr.fims_gui.session;

import com.vaadin.server.VaadinSession;

public class SessionHandler {
	
	public static boolean isLoggedIn() {
		String username = (String)VaadinSession.getCurrent().getSession().getAttribute("user");
		
		if(username == null || username.equals("null") || username.isEmpty()){
			return false;
		} else {
			return true;
		}
	}
}
