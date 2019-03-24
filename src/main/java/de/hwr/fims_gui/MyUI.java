package de.hwr.fims_gui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setHeight("100px");
        layout.addStyleName("header");
        
        
        /*
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", Test12345!"));
        });

        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();
        
        FileResource res = new FileResource(new File(basepath + "/WEB-INF/res/LOGO FIMS.png"));
        
        Image logo = new Image("", res);
        
        layout.addComponents(name, button, logo);
        */
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/example", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
