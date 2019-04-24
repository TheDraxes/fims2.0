package de.hwr.fims_gui.main;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;

import de.hwr.fims_gui.interfaces.HasName;

@Theme("mytheme")
public class SFVCreate extends VerticalLayout implements View, HasName {
	
	private Navigator navigator;
	
	public SFVCreate(Navigator navigator) {
		this.navigator = navigator;
		this.setMargin(false);
		
		HorizontalLayout layout =  new HorizontalLayout();
		
		ProgressBar bar = new ProgressBar(0.0f);
		
		layout.addComponents(bar);
		layout.addComponents(new Button("Increase", click -> {
		    float current = bar.getValue();
		    if (current < 1.0f)
		        bar.setValue(current + 0.10f);
		}));
		
		this.addComponent(new ApplicationHeader());
		this.addComponent(layout);
		this.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
