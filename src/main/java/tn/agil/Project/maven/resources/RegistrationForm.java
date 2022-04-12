package tn.agil.Project.maven.resources;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import tn.agil.Project.maven.basequery.Registration;
import tn.agil.Project.maven.project.App;

public class RegistrationForm extends StackPane{
	
	private Parent m;
	public Scene main;
	private Label sucess;
	private Registration register;
	public RegistrationForm() {
		
		VBox vBox = new VBox();
		vBox.setSpacing(8);
		vBox.setPadding(new Insets(10,10,10,10));
		Label l = new Label("Profile name");
		TextField t = new TextField();
		Label l1 = new Label("Nom");
		TextField t1 = new TextField();
		Label l2 = new Label("Prenom");
		TextField t2 = new TextField();
		Label l3 = new Label("date de naissance");
		DatePicker dt = new DatePicker(LocalDate.now());
		Label l4 = new Label("E-mail");
		TextField t3 = new TextField();
		Label l5 = new Label("Password");
		PasswordField p = new PasswordField();
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setAlignment(Pos.CENTER_RIGHT);
		Button commit = new Button("Register");
		Button back = new Button("Back");
		hBox.getChildren().addAll(back,commit);
		
		vBox.getChildren().addAll(l,t,l1,t1,l2,t2,l3,dt,l4,t3,l5,p,hBox);
		this.getChildren().addAll(vBox);
		
		
		commit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			
				register = new Registration(t1.getText(),t2.getText(),dt.getValue(),t.getText(),p.getText(),t3.getText());
				try {
					if(register.insert())
						addSucess();
						
					else
						addError();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
				returnBack();
				}catch(Exception e){
					
				}
//				main = new Scene(getparent(),400,600);
//				App.getStage().setScene(main);
//				App.getStage().show();
//				App.getStage().setTitle("Register Form");
//				App.getStage().setAlwaysOnTop(true);
				
			}
			
		});
		
	}
	
	public StackPane getRegistration() {
		return this;
		
	}
	
	public Parent getparent() {
		
		System.out.println(this.getParent());
		return this.getParent();
	}
	public void returnBack() {
		MainGUI back = new MainGUI();
		main = new Scene(back.getStack(),400,600);
		App.getStage().setScene(main);
		App.getStage().show();
		App.getStage().setTitle("Login");
		App.getStage().setAlwaysOnTop(true);
	}
	public void addSucess(){
		
		sucess = new Label("Registred Successfully");
		this.getChildren().addAll(sucess);
		this.requestFocus();
	}
	public void addError() {
		if(this.getChildren().contains(sucess)) {
			this.getChildren().remove(sucess);
		}
		
		sucess = new Label("Registration Error");
		sucess.setTextFill(Color.RED);
		
		this.getChildren().addAll(sucess);
		this.requestFocus();
	}
	
}
