package tn.agil.Project.maven.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

	private StackPane root = new StackPane();
    private Stage stage;
    
    
    @Override
    public void init() {
    	VBox vBox = new VBox();
    	VBox vBox1 = new VBox();
    	HBox hBox = new HBox();
    	BorderPane pane = new BorderPane();
    	Label labelApp = new Label();
    	Label l1 = new Label("NAME :");
    	Label l2 = new Label("PRENOM :");
    	Label l3 = new Label("DATE D NAISSANCE :");
    	Label l4 = new Label("Email");
    	Label l5 = new Label("Password");
    	
        Button buttonLogin = new Button("Connect");
        Button buttonFpassword = new Button("Forget Password");
        Label labelPhoto = new Label();
        Button buttonExit = new Button("Exit");
        Button buttonSignIn = new Button("Sign Up");
        Button register = new Button("");
        Label userNameLabel = new Label("Username");
        Label passLabel = new Label("Password");
        TextField userText = new TextField();
        TextField userText1 = new TextField();
        TextField userText2 = new TextField();
        TextField userText3 = new TextField();
        TextField userText4 = new TextField();
        PasswordField passText = new PasswordField();
        HBox hboxTop = new HBox();
        HBox boxDown = new HBox();
        
        
        //-fx-border-color: #ff0000;-fx-border-width: 1px;
        buttonExit.setStyle("-fx-background-color: #e64e4e;-fx-text-fill: #fff0f0;");
        buttonSignIn.setStyle("-fx-background-color: #e64e4e;-fx-text-fill: #fff0f0;");
        
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
                buttonLogin,
                boxDown,
                buttonExit);
        
        //root.getChildren().addAll(vBox);
        //pane.setTop(labelApp);
        //pane.getChildren().addAll(vBox);
        root.getChildren().addAll(vBox);
        buttonExit.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
        	
        });
        bRegister.setOnAction(new EventHandler<ActionEvent>(){
        	
			@Override
			public void handle(ActionEvent event) {
				root.getChildren().clear();
				vBox.getChildren().clear();
				boxDown.getChildren().clear();
				
				vBox.setAlignment(Pos.TOP_LEFT);
				vBox.setSpacing(10);
				vBox.setPadding(new Insets(10,10,10,10));
				boxDown.getChildren().addAll(buttonExit);
				vBox.getChildren().addAll(l1,userText,l2,userText1,l3,userText2,l4,userText3,l5,userText4,boxDown);
				root.getChildren().addAll(vBox);
				
			}
        	
        });
        buttonLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String user = userText.getText();
				String pass = passText.getText();
				try {
					Authentification auth = new Authentification(user,pass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
        	
        });
        
        
       /*
			@Override
			public void handle(ActionEvent event) {
				
				StackPane register = new StackPane();
				VBox vBox = new VBox();
				register.getChildren().addAll(vBox);
				Button b = new Button();
				vBox.getChildren().addAll(b);
				Scene rgeisterScene = new Scene(register,400,600);
				stage.setScene(rgeisterScene);
				stage.show();
				stage.setTitle("Rgister");
				stage.setAlwaysOnTop(true);
			}
        	
        });
        
	*/
        /*button.setOnAction(actionEvent-> {
            if(stage!=null){
                stage.requestFocus();
                return;
            }
            stage = new Stage();
            StackPane stackPane = new StackPane();
            stage.setScene(new Scene(stackPane, 200,200));
            stage.show();
        }); */
    } 

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root,400,600);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Login");
        primaryStage.setAlwaysOnTop(true);
    }
    public static void main(String[] args) {
        launch();
    }

}