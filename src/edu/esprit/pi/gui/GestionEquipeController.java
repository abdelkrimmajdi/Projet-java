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
public class GestionEquipeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AJOUTEREQUIPE(ActionEvent event) {
         try {
            Parent root =  FXMLLoader.load(getClass().getResource("Ajouterequippee.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Supprimer equipe");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(SuppeqController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void MODIFIEREQUIPE(ActionEvent event) {
         try {
            Parent root =  FXMLLoader.load(getClass().getResource("modifierequipe.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Supprimer equipe");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(SuppeqController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SUPPRIMEREQUIPE(ActionEvent event) {
         try {
            Parent root =  FXMLLoader.load(getClass().getResource("suppeq.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Supprimer equipe");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(SuppeqController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EQACCEUILRET(ActionEvent event) {
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
