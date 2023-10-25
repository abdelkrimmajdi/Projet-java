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
//import tn.edu.esprit.services.ServiceEquipe;

/**
 * FXML Controller class
 *
 * @author benou
 */
public class SuppeqController implements Initializable {

    @FXML
    private TableView<equipe> equips;
    @FXML
    private TableColumn<equipe, Integer> nbjc;
    @FXML
    private TableColumn<equipe, String> clmc;
    @FXML
    private TableColumn<equipe, String> nmqc;

    ServiceEquipe service = new ServiceEquipe();
        List<equipe> equipes = service.getAll();
            ObservableList<equipe> obbequipe =FXCollections.observableArrayList(equipes);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                nmqc.setCellValueFactory(new PropertyValueFactory<equipe,String>("nom_equipe"));
                        nbjc.setCellValueFactory(new PropertyValueFactory<equipe,Integer>("nbjoueur_equipe"));
                            clmc.setCellValueFactory(new PropertyValueFactory<equipe,String>("couleurmaillot"));
                equips.setItems(obbequipe);}

    @FXML
    private void eqajourrr(ActionEvent event) {
                obbequipe.clear();
nmqc.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
nbjc.setCellValueFactory(new PropertyValueFactory<>("nbjoueur_equipe"));
clmc.setCellValueFactory(new PropertyValueFactory<>("couleurmaillot"));

ServiceEquipe service = new ServiceEquipe();

List<equipe> equipes = service.getAll();
obbequipe.addAll(equipes);

equips.setItems(obbequipe);
    }

    @FXML
    private void suppeqqq(ActionEvent event) {
        
         int selectedIndex = equips.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        equipe selectedEquipe = equips.getItems().get(selectedIndex);
        int selectedID = selectedEquipe.getId_equipe(); 
        ServiceEquipe eq = new ServiceEquipe();
        eq.supprimer(selectedID);
        equips.getItems().remove(selectedIndex);
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
    private void RETOURRACCEUILL(ActionEvent event) {
          try {
                        
                        Parent root = FXMLLoader.load(getClass().getResource("GestionEquipe.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("sign Up");
                        stage.setScene(new Scene(root));

                        stage.show();

                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
}
