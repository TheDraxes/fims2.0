package de.hwr.fims_gui;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class HeadBarLayout extends VerticalLayout {
	
	
    String basepath = VaadinService.getCurrent()
            .getBaseDirectory().getAbsolutePath();
	
	public HeadBarLayout() {
		this.setSizeFull();
		this.addStyleName("headbar");
		this.setMargin(new MarginInfo(false, true, false, true));
		
		FileResource helpIconRessource = new FileResource(new File(basepath + "/WEB-INF/res/baseline_help_black.png"));
	    Image helpIconImage = new Image("", helpIconRessource);
	    helpIconImage.setHeight("50px");
	    helpIconImage.addClickListener(e -> {
	    	showHelp();
	    });

		this.addComponent(helpIconImage);
		this.setComponentAlignment(helpIconImage, Alignment.TOP_RIGHT);
	}
	
	private void showHelp() {
        // Create a sub-window and set the content
        Window subWindow = new Window("Hilfefenster");
        VerticalLayout subContent = new VerticalLayout();
        subWindow.setContent(subContent);
        subWindow.setHeight(70, Unit.PERCENTAGE);
        subWindow.setWidth(40, Unit.PERCENTAGE);

        // Put some components in it
        subContent.addComponent(new Label("Dies ist der Loginscreen der Webanwendung"));

        // Center it in the browser window
        subWindow.center();
        Page.getCurrent().getUI().addWindow(subWindow);
	}
}
