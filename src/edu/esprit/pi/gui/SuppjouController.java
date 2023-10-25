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
import java.io.IOException;
//package tn.edu.esprit.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
//import tn.edu.esprit.entities.equipe;
//import tn.edu.esprit.entities.joueur;
//import tn.edu.esprit.services.ServiceEquipe;
//import tn.edu.esprit.services.ServiceJoueur;

/**
 * FXML Controller class
 *
 * @author benou
 */
public class SuppjouController implements Initializable {

    @FXML
    private TableView<joueur> joutab;
    @FXML
    private TableColumn<joueur, Integer> numerojoueurr;
    @FXML
    private TableColumn<joueur,String > nomjoueurr;
    @FXML
    private TableColumn<joueur, Integer> agejoueurr;
    @FXML
    private TableColumn<joueur, String> positionjoueurr;
    
    ServiceJoueur service = new ServiceJoueur();
        List<joueur> joueurs = service.getAll();
            ObservableList<joueur> obbjoueurr =FXCollections.observableArrayList(joueurs);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                numerojoueurr.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("num_joueur"));
                        nomjoueurr.setCellValueFactory(new PropertyValueFactory<joueur,String>("nom_joueur"));
                            agejoueurr.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("age_joueur"));
                                            positionjoueurr.setCellValueFactory(new PropertyValueFactory<joueur,String>("Position_joueur"));

                joutab.setItems(obbjoueurr);}
    
     public void  update ()
    {
                   obbjoueurr.clear();
        nomjoueurr.setCellValueFactory(new PropertyValueFactory<joueur,String>("nom_joueur"));
        numerojoueurr.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("num_joueur"));
                agejoueurr.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("age_joueur"));

        positionjoueurr.setCellValueFactory(new PropertyValueFactory<joueur,String>("Position_joueur"));
        ServiceJoueur service = new ServiceJoueur();
        List<joueur> joueurs = service.getAll();
            ObservableList<joueur> obbjoueurr =FXCollections.observableArrayList(joueurs);
        joutab.setItems(obbjoueurr);
    }
    

    @FXML
    private void suppjou(ActionEvent event) {
  int selectedIndex = joutab.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        joueur selectedJoueur = joutab.getItems().get(selectedIndex);
        int selectedID = selectedJoueur.getId_joueur(); 
        ServiceJoueur jo = new ServiceJoueur();
        jo.supprimer(selectedID);
        joutab.getItems().remove(selectedIndex);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("SUPPRIMER AVEC SUCCES");
            alert.showAndWait();
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune sélection");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une ligne à supprimer.");
        alert.showAndWait();
           update();
    }
    
    }
    
    @FXML
    private void refresh(ActionEvent event) {
        obbjoueurr.clear();
                numerojoueurr.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("num_joueur"));
                        nomjoueurr.setCellValueFactory(new PropertyValueFactory<joueur,String>("nom_joueur"));
                            agejoueurr.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("age_joueur"));
                                            positionjoueurr.setCellValueFactory(new PropertyValueFactory<joueur,String>("Position_joueur"));
    ServiceJoueur j = new ServiceJoueur();
        List<joueur> joueurs = j.getAll();
        obbjoueurr.addAll(joueurs);
            
joutab.setItems(obbjoueurr);
    }

    @FXML
    private void RETOURRACCeuilll(ActionEvent event) {
          try {
                        
                        Parent root = FXMLLoader.load(getClass().getResource("GestionJoueur.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("sign Up");
                        stage.setScene(new Scene(root));

                        stage.show();

                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
  
       

}
