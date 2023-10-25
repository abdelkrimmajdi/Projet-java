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
public class RechercherOptionController implements Initializable {

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
    private void nom(ActionEvent event) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("RechercherTournoi.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Rechercher par nom");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void adresse(ActionEvent event) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("RechercherTparAdresse.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Rechercher par adresse");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void date(ActionEvent event) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("RechercherTparDate.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Rechercher par date");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Tournoi.fxml"));

            Stage stage = new Stage();
            stage.setTitle("gestion des tournois");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
