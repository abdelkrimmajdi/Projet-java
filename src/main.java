

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author aziz
 */
public class main  extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edu/esprit/pi/gui/ajouterequippee.fxml"));
        
        Parent root = loader.load();

        // Create a scene with the loaded FXML content
        Scene scene = new Scene(root);

        // Set the stage title
        primaryStage.setTitle("FXML Example");

        // Set the scene for the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }
    
    

    
    
        
        

       
    
    
    public static void main(String[] args) {

		launch(args);

	}

    
}
