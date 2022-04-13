package tn.agil.Project.maven.resources;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	ArrayList<String> fr = new ArrayList<String>(){
		{add("amine");add("akrem");add("oussema");}
		};
		
	//Available Friends right now
	ArrayList<Boolean> available = new ArrayList<Boolean>(){
		{add(true);add(false);add(true);}
		};
	
	//head Label 
	private Label friend = new Label("Friends :");
	
	HBox h = new HBox();
	
	/**
	 * Constructor friends VBox containing user friends with their availabilities
	 */
	public FriendsListViews() {
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
				h.getChildren().add(frindsLabelsCreation(f));
				this.getChildren().add(h);
				i++;
		}
	}
	/**
	 * create a label for friends
	 * @param s friend name
	 * @return a label containing a friends name  
	 */
	private Label frindsLabelsCreation(String s) {
		//new Lable creation
		Label x = new Label(s);
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
