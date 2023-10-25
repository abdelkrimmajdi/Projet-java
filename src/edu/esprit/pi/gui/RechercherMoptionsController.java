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
public class RechercherMoptionsController implements Initializable {

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
    private void nomTournoi(ActionEvent event) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("RechercherMparNT.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Rechercher par nom du tournoi");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void nomEquipe(ActionEvent event) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("RechercherMparNE.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Rechercher par nom de l'equipe");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Fares.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Gestion des Match");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    

