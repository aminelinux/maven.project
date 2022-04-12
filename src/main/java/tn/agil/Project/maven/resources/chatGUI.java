package tn.agil.Project.maven.resources;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import tn.agil.Project.maven.project.App;
import tn.agil.Project.maven.chat.*;

public class chatGUI  {
	private StackPane root = new StackPane();
	private StackPane centrePane;
	BorderPane bPane;
	private Scene main;
	private PrimaryController pr;
	//boxes
	HBox hBox ;
	VBox vbox;
	VBox vboxM;
	
	Button buttonChat = new Button("chat");
	//items
	MenuItem item1 ;
	MenuItem item2 ;
	MenuItem item3 ;
	MenuItem item4 ;
	MenuItem item5 ;
	MenuItem item6 ;
	MenuItem item7 ;
	MenuItem item8 ;
	MenuItem item9 ;
	MenuItem item10 ;
	//Menus
	Menu m ;
	Menu m1 ;
	Menu m2 ;
	Menu m3 ;
	
	MenuBar mb;
	
	public chatGUI(int id) {
		centrePane = new StackPane();
		bPane = new BorderPane();
		hBox = new HBox();
		m = new Menu("File");
		m.setStyle("-fx-font-weight: bold");
		item1 = new MenuItem("Add friends");
		item1.setStyle("-fx-font-weight: bold;-fx-font-family: Arial, Helvetica, sans-serif ");
		item2 = new MenuItem("Block friends");
		item2.setStyle("-fx-font-weight: bold");
		item3 = new MenuItem("Exit");
		item3.setStyle("-fx-font-weight: bold");
		item4 = new MenuItem("App Style");
		item4.setStyle("-fx-font-weight: bold");
		item5 = new MenuItem("Background Style");
		item5.setStyle("-fx-font-weight: bold");
		item6 = new MenuItem("Disponibilité");
		item6.setStyle("-fx-font-weight: bold");
		item7 = new MenuItem("settings");
		item7.setStyle("-fx-font-weight: bold");
		item8 = new MenuItem("Update");
		item8.setStyle("-fx-font-weight: bold");
		item9 = new MenuItem("News");
		item9.setStyle("-fx-font-weight: bold");
		item10 = new MenuItem("About App");
		item10.setStyle("-fx-font-weight: bold");
		
		m1 = new Menu("Option");
		m1.setStyle("-fx-font-weight: bold");
		m2 = new Menu("Accounts");
		m2.setStyle("-fx-font-weight: bold");
		m3 = new Menu("Help");
		m3.setStyle("-fx-font-weight: bold");
		
		mb = new MenuBar();
		m.getItems().addAll(item1,item2,item3);
		m1.getItems().addAll(item4,item5);
		m2.getItems().addAll(item6,item7);
		m3.getItems().addAll(item8,item9,item10);
		
		mb.getMenus().addAll(m,m1,m2,m3);
		Label la = new Label("Message");
		
		vboxM = new VBox();
		vboxM.setMaxHeight(30);
		vboxM.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
		vboxM.setAlignment(Pos.TOP_CENTER);
		vboxM.getChildren().addAll(la);
		hBox.getChildren().addAll(mb);
		bPane.setTop(hBox);
		centrePane.setAlignment(Pos.TOP_CENTER);
		centrePane.getChildren().addAll(vboxM);
		bPane.setCenter(centrePane);
		bPane.setLeft(buttonChat);
		vbox = new VBox();
		vbox.setMinWidth(200.0);
		vbox.setBackground(new Background(new BackgroundFill(Color.web("#dae0e0"),new CornerRadii(10),new Insets(10,10,10,10))));
		Label friend = new Label("Friends :");
		friend.setStyle("-fx-font-weight: bold");
		friend.setPadding(new Insets(10,10,10,10));
		friend.setBackground(new Background(new BackgroundFill(Color.web("#f77e86"),CornerRadii.EMPTY,new Insets(10,10,10,10))));
		friend.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(friend);
		bPane.setRight(vbox);
		root.getChildren().addAll(bPane);
		item1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				
			}
			
		});
		
		main = new Scene(root,400,600);
		App.getStage().setScene(main);
		App.getStage().show();
		App.getStage().setTitle("chat");
		App.getStage().setAlwaysOnTop(true);
	}
	
	
	

}
