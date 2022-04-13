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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrimaryController extends Application{

    private ChatGateway gateway;
    
    private TextArea textArea;
    
    private TextField comment;

	private Label label;

           
    
    public void PrimaryController() {
    	gateway = new ChatGateway(textArea);

        // Put up a dialog to get a handle from the user
        TextInputDialog dialog = new TextInputDialog("you");
        dialog.setTitle("Start Chat");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter a handle:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> gateway.sendHandle(name));
        
       
        // Start the transcript check thread
        new Thread(new TranscriptCheck(gateway,textArea)).start();
    }
    private void sendComment(ActionEvent event) {
        String text = comment.getText();
        gateway.sendComment(text);
    }
	@Override
	public void start(Stage stage) {
		 VBox root = new VBox();
	        root.setPadding(new Insets(10));
	        root.setSpacing(10);

	        this.label = new Label();

	        Button button = new Button("Enter your name");

	        button.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent event) {
	            	PrimaryController();
	            }
	        });

	        root.getChildren().addAll(button, label);

	        Scene scene = new Scene(root, 450, 250);
	        stage.setTitle("TextInputDialog");
	        stage.setScene(scene);

	        stage.show();

		
	}
    
   
    
}

class TranscriptCheck implements Runnable, ChatConstants {
    private ChatGateway gateway; // Gateway to the server
    private TextArea textArea; // Where to display comments
    private int N; // How many comments we have read
    
    /** Construct a thread */
    public TranscriptCheck(ChatGateway gateway,TextArea textArea) {
      this.gateway = gateway;
      this.textArea = textArea;
      this.N = 0;
    }

    /** Run a thread */
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