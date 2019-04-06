package de.hwr.fims_gui.main;

import java.io.File;

import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinService;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

public class ApplicationHeader extends HorizontalLayout implements View {
	
	String basepath = VaadinService.getCurrent()
            .getBaseDirectory().getAbsolutePath();
	
	public ApplicationHeader() {
		
		this.setMargin(false);
		
		FileResource fimsLogoRessource = new FileResource(new File(basepath + "/WEB-INF/res/MyWayBestattungenlogo.png"));
        Image fimsLogoImage = new Image("", fimsLogoRessource);
        fimsLogoImage.setHeight(80, Unit.PIXELS);
        fimsLogoImage.setResponsive(true);
        Responsive.makeResponsive(fimsLogoImage);
        
        Button home = new Button();
        
        this.addComponent(fimsLogoImage);
	}
}
