package tn.agil.Project.maven.eventHandler;

import tn.agil.Project.maven.basequery.Authentification;
import tn.agil.Project.maven.project.*;
import tn.agil.Project.maven.resources.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

 public class Button1Handler implements EventHandler<ActionEvent>{

	 private final Stage window; 
	 private final Scene scene;
	 private final String pass;
	 private final String user;
	 
	 
	 public Button1Handler(Stage window,Scene scene,String pass,String user) {
		 this.window = window;
		 this.scene =scene;
		 this.pass = pass;
		 this.user = user;
		 
		 
	 }
	 
	@Override
	public void handle(ActionEvent event) {
		try {
			System.out.println(App.getStage());
			
			Authentification auth = new Authentification(user,pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
}
