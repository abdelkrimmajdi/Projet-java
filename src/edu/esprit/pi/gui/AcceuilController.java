/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.utilisateur;
import edu.esprit.pi.services.serviceUser;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import static sun.security.jgss.GSSUtil.login;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button gestionres;
    @FXML
    private Button gestionrtour;
    @FXML
    private Button gestionrrecl;
    @FXML
    private Button gestionrequi;
    @FXML
    private Button gestionrematch;
    @FXML
    private Button gestionreterr;
    @FXML
    private Button profile;
    @FXML
    private Button dash;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gestionres(ActionEvent event) {
    }

    @FXML
    private void gestionrtour(ActionEvent event) {
    }

    @FXML
    private void gestionrrecl(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("Reclamationaj.fxml"));

            Stage stage = new Stage();
            stage.setTitle("adduser");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void gestionrequi(ActionEvent event) {
    }

    @FXML
    private void gestionrematch(ActionEvent event) {
        
        
    }

    @FXML
    private void gestionreterr(ActionEvent event) {
    }

    @FXML
    private void profile(ActionEvent event) {
        
           try {
                        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("sign Up");
                        stage.setScene(new Scene(root));

                        stage.show();

                     
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void dash(ActionEvent event) {
        
      if (utilisateur.current_user.getRole() == 1) {
         try {
                        
                        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("sign Up");
                        stage.setScene(new Scene(root));

                        stage.show();

                        dash.getScene().getWindow().hide();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
         
           
        }
}
    
}



