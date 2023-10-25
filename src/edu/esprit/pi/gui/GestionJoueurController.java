/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.gui;
import edu.esprit.pi.entities.equipe;
import edu.esprit.pi.entities.joueur;
import edu.esprit.pi.services.ServiceJoueur;
import edu.esprit.pi.services.ServiceEquipe;
//package tn.edu.esprit.gui;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author benou
 */
public class GestionJoueurController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AJOUTERJOUEUR(ActionEvent event) {
         try {
            Parent root =  FXMLLoader.load(getClass().getResource("ajoadd.fxml"));

            Stage stage = new Stage();
            stage.setTitle("MODIFIER JOUEUR");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(ModijoueurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void MODIFIERJOUEUR(ActionEvent event) {
         try {
            Parent root =  FXMLLoader.load(getClass().getResource("modijoueur.fxml"));

            Stage stage = new Stage();
            stage.setTitle("MODIFIER JOUEUR");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(ModijoueurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SUPPRIMERJOUEUR(ActionEvent event) {
         try {
            Parent root =  FXMLLoader.load(getClass().getResource("suppjou.fxml"));

            Stage stage = new Stage();
            stage.setTitle("MODIFIER JOUEUR");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(ModijoueurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void JOUEURACCEUIL(ActionEvent event) {
          try {
                        
                        Parent root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("sign Up");
                        stage.setScene(new Scene(root));

                        stage.show();

                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    
}
