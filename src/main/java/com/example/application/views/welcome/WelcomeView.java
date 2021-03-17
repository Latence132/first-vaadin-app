package com.example.application.views.welcome;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;

@CssImport("./views/welcome/welcome-view.css")
@Route(value = "welcome", layout = MainView.class)
@PageTitle("Welcome")
public class WelcomeView extends Div {
    
    public WelcomeView(){
        addClassName("welcome-view");
        add(new Text("Welcome"));
    }
}
