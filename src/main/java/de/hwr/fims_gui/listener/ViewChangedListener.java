package de.hwr.fims_gui.listener;

import com.vaadin.server.Page;

public class ViewChangedListener implements com.vaadin.navigator.ViewChangeListener {

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		
		Page.getCurrent().setTitle("FIMS: " + event.getViewName());
		
		return true;
	}

}
