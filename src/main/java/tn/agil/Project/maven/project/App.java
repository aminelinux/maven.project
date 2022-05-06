package tn.agil.Project.maven.project;

import tn.agil.Project.maven.resources.MainGUI;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    private static Stage guiStage;
    
    

    public static Stage getStage() {
        return guiStage;
    }
    

    @Override
    public void start(Stage primaryStage) {
    	guiStage = primaryStage;
    	MainGUI main = new MainGUI();
    	primaryStage.getIcons().add(new Image("logoApp.png"));
        //Scene scene = new Scene(root,400,600);
        //primaryStage.setScene(scene);
        //primaryStage.show();
        //primaryStage.setTitle("Login");
        //primaryStage.setAlwaysOnTop(true);
    }


    public static void main(String[] args) {
        launch(args);
    }

}