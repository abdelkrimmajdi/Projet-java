/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.edu.esprit.entities.joueur;
import tn.edu.esprit.services.ServiceJoueur;

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
  
       

}
