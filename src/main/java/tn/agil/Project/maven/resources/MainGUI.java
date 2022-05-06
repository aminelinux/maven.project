package tn.agil.Project.maven.resources;

import tn.agil.Project.maven.basequery.Authentification;
import tn.agil.Project.maven.eventHandler.Button1Handler;
import tn.agil.Project.maven.project.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGUI  {
	private StackPane root = new StackPane();
	RegistrationForm r;
	Scene main;
	private String user;
	private String pass;
	private chatGUI chat ;
	Label errorLabel ;
	/**
	 * 
	 * Constructor for the main GUI
	 */
	public MainGUI() {
		
		VBox vBox = new VBox();
    	VBox vBox1 = new VBox();
    	HBox hBox = new HBox();
    	BorderPane pane = new BorderPane();
    	Label labelApp = new Label();
        Button buttonLogin = new Button("Connect");
        Button buttonFpassword = new Button("Forget Password");
        Label labelPhoto = new Label();
        Button buttonExit = new Button("Exit");
        Button buttonSignIn = new Button("Sign Up");
        Label userNameLabel = new Label("Username");
        Label passLabel = new Label("Password");
        TextField userText = new TextField();
        PasswordField passText = new PasswordField();
        HBox hboxTop = new HBox();
        HBox boxDown = new HBox();
        
        
        //-fx-border-color: #ff0000;-fx-border-width: 1px;
        buttonExit.setStyle("-fx-background-color: #e64e4e;-fx-text-fill: #fff0f0;");
        buttonSignIn.setStyle("-fx-background-color: #e64e4e;-fx-text-fill: #fff0f0;");
        
        Label errorLabel = new Label("Error please check your email or password");
		errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
        
        InputStream stream = null;
        final ImageView selectedImage = new ImageView();
        try {
			stream = new FileInputStream("C:\\Users\\Ce-PC\\Downloads\\output-onlinepngtools.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	       
        Image image = new Image(stream);
        selectedImage.setImage(image);
        selectedImage.setFitWidth(300);
        selectedImage.setFitHeight(300);
        selectedImage.setPreserveRatio(true);
        hboxTop.setAlignment(Pos.CENTER);
        HBox.setMargin(pane, new Insets(10,10,10,10));     
        hboxTop.getChildren().addAll(selectedImage);
       // userNameLabel.setUnderline(true);
        boxDown.setAlignment(Pos.CENTER);
        Label lRegister = new Label("Still not registred in ");
        Button bRegister = new Button("Register");
        boxDown.getChildren().addAll(lRegister,bRegister);
        labelApp.setText("Sign In To app");
        //labelApp.setStyle();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10,10,10,10));
        
        vBox.setSpacing(8);
        vBox.setPadding(new Insets(10,10,10,10));
        
       
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(
        		hboxTop,
        		hBox,
        		userNameLabel,
        		userText,
                passLabel,
                passText,
                errorLabel,
                buttonLogin,
                boxDown,
                buttonExit);
        
        //root.getChildren().addAll(vBox);
        //pane.setTop(labelApp);
        //pane.getChildren().addAll(vBox);
        root.getChildren().addAll(vBox);
        buttonExit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
        });
        this.root.requestFocus();
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            
			@Override
			public void handle(KeyEvent event) {
				System.out.println(event);
				if(event.getCode().equals(KeyCode.ENTER)) {
					buttonLogin.fire();
					System.out.println("keypressed");
				}	
			}
    	});
        buttonLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				user = userText.getText();
				pass = passText.getText();
				try {
					Authentification auth = new Authentification(user,pass);
					if(auth.state()) {
//						 auth.idUT();
						//errorLabel.setVisible(false);
						 chat = new chatGUI(auth.idUT(),getUser());
					}else {
						errorAuth();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} }
        });
        bRegister.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				 r =  new RegistrationForm();
				 main = new Scene(r.getRegistration(),400,600);
				 //main.getOnContextMenuRequested();
				 App.getStage().setScene(main);
				 App.getStage().show();
				 App.getStage().setTitle("Register Form");
				 App.getStage().setAlwaysOnTop(true);			
			}	
        });
        
        
        main = new Scene(root,400,600);
		App.getStage().setScene(main);
		App.getStage().show();
		App.getStage().setTitle("Login");
		App.getStage().setAlwaysOnTop(true);
		
		
	}
	
	public String getUser() {
		return this.user;
	}
	
	/**
	 * return the StackPane
	 * 
	 * @return root
	 */
	public StackPane getStack() {
		return root;	
	}
	/**
	 * return the MainGUI 
	 * 
	 * @return MainGUI
	 */
	public MainGUI getMainGUI() {
		return this;
	}
	/**
	 * slide to the new Scene RgistrationForm
	 */
	
	public void register() {
		 r =  new RegistrationForm();
		 main = new Scene(r.getRegistration(),400,600);
		 App.getStage().setScene(main);
		 App.getStage().show();
		 App.getStage().setTitle("Register Form");
		 App.getStage().setAlwaysOnTop(true);
	}
	/**
	 * 
	 * small msg to show an error with authentification pass or login
	 */
	public void errorAuth() {
		errorLabel.setVisible(true);
	}

}
