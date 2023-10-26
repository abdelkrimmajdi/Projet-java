/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamma
 */
public class FaresController implements Initializable {

    @FXML
    private ImageView imglogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("Ajouter.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Ajouter Match");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("ModifierMatch.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Modifier Match");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void supprimer(ActionEvent event) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("SupprimerMatch.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Supprimer Match");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Rechercher(ActionEvent event) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("RechercherMoptions.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Option");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Gestion");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
