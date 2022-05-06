package tn.agil.Project.maven.resources;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class FriendsListViews extends VBox{
	//list of friends from friend list
	ArrayList<String> fr;
		
	//Available Friends right now
	ArrayList<Boolean> available;
	
	//head Label 
	private Label friend = new Label("Friends :");
	
	HBox h = new HBox();
	
	/**
	 * Constructor friends VBox containing user friends with their availabilities
	 */
	public FriendsListViews(String[] friends,Boolean[] disponibilite) {
		fr = new ArrayList<String>(Arrays.asList(friends));
		available = new ArrayList<Boolean>(Arrays.asList(disponibilite));
		this.setMinWidth(200.0);
		this.setBackground(new Background(new BackgroundFill(Color.web("#dae0e0"),new CornerRadii(10),new Insets(10,10,10,10))));
		friend.setStyle("-fx-font-weight: bold");
		friend.setPadding(new Insets(10,10,10,10));
		friend.setBackground(new Background(new BackgroundFill(Color.web("#f77e86"),CornerRadii.EMPTY,new Insets(10,10,10,10))));
		friend.setAlignment(Pos.CENTER);
		this.getChildren().add(friend);
		int i=0;
		for(String f : fr) {
				HBox h = new HBox();
				h.getChildren().add(frinedsAvailabilities(available.get(i)));
				h.getChildren().add(friendsLabelsCreation(f));
				this.getChildren().add(h);
				i++;
		}
	}
	/**
	 * create a label for friends
	 * @param s friend name
	 * @return a label containing a friends name  
	 */
	private Button friendsLabelsCreation(String s) {
		//new Lable creation
		
		Button x = new Button(s);
		x.setOnAction(e->{
			String id;
			id = this.getAccessibleText();
			System.out.println(s);
		});
		x.setBackground(new Background(new BackgroundFill(Color.web("#9bc2c2"),new CornerRadii(10),new Insets(10,10,10,10))));
		x.setFont(new Font("Arial", 15));
		x.setTextFill(Color.DARKGREEN);
		x.setPadding(new Insets(5,20,5,20));
		x.setAlignment(Pos.CENTER);
		return x;
	}
	/**
	 * show if a user is availabilities 
	 * @param av get the availabilities from friends list
	 * @return a label containing a label with online or offline png
	 */
	private Label frinedsAvailabilities(Boolean av) {
		ImageView icon;
		Label il;
		if(av) {
			icon = new ImageView("online.png");
			icon.setFitHeight(10);
			icon.setFitWidth(10);
			il = new Label();
			il.setPadding(new Insets(5,5,5,15));
			il.setGraphic(icon);
			
			
		} else {
			icon = new ImageView("offline.png");
			icon.setFitHeight(10);
			icon.setFitWidth(10);
			il = new Label();
			il.setPadding(new Insets(5,5,5,15));
			il.setGraphic(icon);
		}
			
		return il;
	}
	
	
}
