/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Terrain;
import edu.esprit.pi.services.ServiceTerrain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sadok
 */
public class TerrainFXMLController implements Initializable {

    @FXML
    private Button buttonAjouter;
    @FXML
    private TextField prix;
    @FXML
    private TextField adress;
    @FXML
    private Button afficherTerrain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        String p = prix.getText();
        String a = adress.getText();
       
     
    if (p.isEmpty()||a.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuiller remplir les champs!");        
        alert.show();
        } 
        else{
         int pp = Integer.parseInt(prix.getText());
        String aa = adress.getText();
      
         Terrain ter = new Terrain(pp, aa, 0);
         ServiceTerrain st = new ServiceTerrain();
         st.ajouter(ter);
      
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Terrain Ajoutée avec succés!");        
        alert.show();}
        
    }

    @FXML
    private void afficherTerrain(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("ListeTerrains.fxml"));

        Stage currentStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Tout les Terrain");

        currentStage.initModality(Modality.WINDOW_MODAL);


        currentStage.show();

    } catch (IOException e) {
            System.err.println(e.getMessage());
    }
    }

}
