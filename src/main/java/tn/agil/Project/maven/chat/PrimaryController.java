package tn.agil.Project.maven.chat;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.agil.Project.maven.resources.ClientChat;

public class PrimaryController extends VBox{

    private ChatGateway gateway;
    
    private TextArea textArea;
    
    private TextField comment;
    
    private String ut_name;
    
	private Button b;
	private HBox hb;
    public PrimaryController(String name) {
    	
    	ut_name = name;
    	//comment = new TextField();
    	//b = new Button("send");
    	//b.setAlignment(Pos.BASELINE_CENTER);
		//hb = new HBox();
		 
		
		 //hb.getChildren().addAll(comment,b);
		 //this.getChildren().addAll(hb);
    	gateway = new ChatGateway(textArea);
    	gateway.sendHandle(name);
    	msgAppend();

        // Start the transcript check thread
       // new Thread(new TranscriptCheck(gateway,textArea)).start();
    }
    
    public void msgAppend() {
    	comment= new TextField();
    	textArea = new TextArea();
    	textArea.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
    	textArea.setStyle("-fx-control-inner-background:#30c4e6; -fx-font-family: Consolas; -fx-highlight-fill: #00ff00; -fx-highlight-text-fill: #000000; -fx-text-fill: #00ff00; ");
    	Button chatButton = new Button("send");
    	this.getChildren().addAll(textArea);
    	HBox hbcomm = new HBox(comment,chatButton);
    	this.getChildren().add(hbcomm);
    	chatButton.setFocusTraversable(true);
    	this.requestFocus();
    	hbcomm.setOnKeyPressed(new EventHandler<KeyEvent>() {
    
			@Override
			public void handle(KeyEvent event) {
				System.out.println(event);
				if(event.getCode().equals(KeyCode.ENTER)) {
					chatButton.fire();
					System.out.println("keypressed");
				}
			}
    	});
    	
    	chatButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				gateway.sendComment(comment.getText());
				comment.setText("");
			}
    		
    	});
    	
    	new Thread(new TranscriptCheck(gateway,textArea)).start();
    	
    	
    }
}
/**
 * @author Ce-PC
 */
class TranscriptCheck implements Runnable, ChatConstants {
    private ChatGateway gateway; // Gateway to the server
    private TextArea textArea; // Where to display comments
    private String name;//User name 
    private int N; // How many comments we have read
    /**
     * Construct a thread
     * @param gateway
     * @param textArea
     */
    public TranscriptCheck(ChatGateway gateway,TextArea textArea) {
      this.gateway = gateway;
      this.textArea = textArea;
      this.name = name;
      this.N = 0;
    }
    /**
     * 
     * Run a thread 
     */
    public void run() {
      while(true) {
          if(gateway.getCommentCount() > N) {
              String newComment = gateway.getComment(N);
              Platform.runLater(()->textArea.appendText(newComment + "\n"));
              N++;
          } else {
              try {
                  Thread.sleep(250);
              } catch(InterruptedException ex) {}
          }
      }
    }
  }