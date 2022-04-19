package tn.agil.Project.maven.chat;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.agil.Project.maven.resources.ClientChat;

public class PrimaryController extends VBox{

    private ChatGateway gateway;
    
    private TextArea textArea;
    
    private TextField comment;

	private Label label;
	private String text;
	private TextField txt;
	private Button b;
	private HBox hb;
           
    
    public PrimaryController() {
    	
    	txt = new TextField();
    	gateway.sendHandle("amine");
		 hb = new HBox();
		 b = new Button();
		 b.setText("Send");
		 hb.getChildren().addAll(txt,b);
		 this.getChildren().addAll(hb);
		 txt.getText();
		 b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				text = txt.getText();
				gateway.sendComment(text);
				//gateway.sendHandle("amine");
				
			}
			 
		 });

    	this.getChildren().addAll(txt,b);

        // Start the transcript check thread
        new Thread(new TranscriptCheck(gateway,textArea)).start();
    }
    
    //private  nameUser()
}

/**
 * 
 * @author Ce-PC
 *
 */
class TranscriptCheck implements Runnable, ChatConstants {
    private ChatGateway gateway; // Gateway to the server
    private TextArea textArea; // Where to display comments
    private int N; // How many comments we have read
    
    
    /**
     * Construct a thread
     * @param gateway
     * @param textArea
     */
    public TranscriptCheck(ChatGateway gateway,TextArea textArea) {
      this.gateway = gateway;
      this.textArea = textArea;
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