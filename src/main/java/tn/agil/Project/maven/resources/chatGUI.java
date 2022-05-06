package tn.agil.Project.maven.resources;

import java.sql.SQLException;

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
import javafx.stage.WindowEvent;
import tn.agil.Project.maven.project.Amis;
import tn.agil.Project.maven.project.App;
import tn.agil.Project.maven.basequery.Authentification;
import tn.agil.Project.maven.chat.*;

public class chatGUI  {
	private StackPane root = new StackPane();
	private StackPane centrePane;
	private Amis a;
	private int id;
	private String name;
	
	/**
	 * friendsListViews to share
	 */
	FriendsListViews friends ;
	
	/**
	 * main Border left right top center and bottom
	 */
	BorderPane bPane;
	
	private Scene main;
	private PrimaryController pr;
	/**
	 * Horizontal box for the menu bar
	 */
	HBox hBox ;
	/**
	 * Vertical Box For Friends List Background + friends
	 */
	VBox vbox;
	
	/**
	 * Vertical Box For Message
	 */
	VBox vboxM;
	/**
	 * 
	 * Center vertical Box
	 */
	VBox vboxChat;
	
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
	
	/**
	 * Menu called File
	 * integration of the item 1, item 2,item 3
	 */
	Menu m ;
	/**
	 * Menu called Option
	 * integration of the item 4, item 5
	 */
	Menu m1 ;
	/**
	 * Menu called Option
	 * integration of item 6,item 7
	 */
	Menu m2 ;
	/**
	 * Menu Called Help
	 * integration o item 8,item 9, item 10
	 */
	Menu m3 ;
	/**
	 * MenuBar 
	 */
	MenuBar mb;
	/**
	 * Constructor for main ChatGUI Interface App
	 * @param id give the id user that is already connected
	 * @throws SQLException 
	 */
	public chatGUI(int id,String user) throws SQLException {
		this.id = id;
		this.name = user;
		a = new Amis(id);
		
		friends = new FriendsListViews(a.getFriends(),a.getDispo());
		centrePane = new StackPane();
		bPane = new BorderPane();
		hBox = new HBox();
		vboxChat = new VBox();
		//pr = new PrimaryController(user);
		
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
		
		m = new Menu("File");
		m.setStyle("-fx-font-weight: bold");
		m1 = new Menu("Option");
		m1.setStyle("-fx-font-weight: bold");
		m2 = new Menu("Accounts");
		m2.setStyle("-fx-font-weight: bold");
		m3 = new Menu("Help");
		m3.setStyle("-fx-font-weight: bold");
		
		m.getItems().addAll(item1,item2,item3);
		m1.getItems().addAll(item4,item5);
		m2.getItems().addAll(item6,item7);
		m3.getItems().addAll(item8,item9,item10);
		
		mb = new MenuBar();
		mb.getMenus().addAll(m,m1,m2,m3);
		
		Label la = new Label("Message");
		
		vboxM = new VBox();
		vboxM.setMaxHeight(30);
		vboxM.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
		vboxM.setAlignment(Pos.TOP_CENTER);
		vboxM.getChildren().addAll(la);
		hBox.getChildren().addAll(mb);
		
		centrePane.setAlignment(Pos.TOP_CENTER);
		centrePane.getChildren().addAll(vboxM);
		
		//vbox background for friendsList
		vbox = new VBox();
		vbox.setMinWidth(200.0);
		vbox.setBackground(new Background(new BackgroundFill(Color.web("#dae0e0"),new CornerRadii(10),new Insets(10,10,10,10))));
		vbox.getChildren().addAll(friends);
		
		buttonChat.setAlignment(Pos.CENTER);
		
		
		//border pane creation
		bPane.setTop(hBox);
		
		vboxChat.getChildren().addAll(buttonChat);
		bPane.setCenter(vboxChat);
		bPane.setLeft(centrePane);
		bPane.setRight(vbox);
		
		root.getChildren().addAll(bPane);
		buttonChat.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				pr = new PrimaryController(user);
				//bPane.setLeft(centrePane);
				//bPane.setCenter(pr);
				vboxChat.getChildren().add(pr);
				buttonChat.setVisible(false);
				
			}
		});
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
		App.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				try {
					Authentification x = new Authentification(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				System.exit(0);
			}
		});
	}
}
