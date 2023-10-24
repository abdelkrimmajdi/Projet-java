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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.edu.esprit.entities.joueur;
import tn.edu.esprit.services.ServiceJoueur;

/**
 * FXML Controller class
 *
 * @author benou
 */
public class ModijoueurController implements Initializable {

    @FXML
    private TextField extnom;
    @FXML
    private ComboBox<String> compos;
    @FXML
    private Spinner<Integer> spage;
    @FXML
    private TableView<joueur> joutable;
    @FXML
    private TableColumn<joueur, Integer> numj;
    @FXML
    private TableColumn<joueur, String> nomj;
    @FXML
    private TableColumn<joueur, Integer> agej;
    @FXML
    private TableColumn<joueur, String> posj;
    @FXML
    private Spinner<Integer> spnumero;
ObservableList<String> list = FXCollections.observableArrayList("","gardien","defenseur","milieu", "attaquant");
                ServiceJoueur service = new ServiceJoueur();
        List<joueur> joueurs = service.getAll();
            ObservableList<joueur> obbjoueurs =FXCollections.observableArrayList(joueurs);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                numj.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("num_joueur"));
                        nomj.setCellValueFactory(new PropertyValueFactory<joueur,String>("nom_joueur"));
                            agej.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("age_joueur"));
                                            posj.setCellValueFactory(new PropertyValueFactory<joueur,String>("Position_joueur"));
                                                    ServiceJoueur service = new ServiceJoueur();
                                                    SpinnerValueFactory<Integer> v = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
                joutable.setItems(obbjoueurs);
                compos.setItems(list); 
   v.setValue(1);
  spage.setValueFactory(v);
  SpinnerValueFactory<Integer> j = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);

  spnumero.setValueFactory(j);
        
    }    

    @FXML
    private void modj(ActionEvent event) {
            ServiceJoueur jo = new ServiceJoueur();
   joueur selectedjoueur = joutable.getSelectionModel().getSelectedItem();
if (selectedjoueur != null ) {
    selectedjoueur.setAge_joueur(spage.getValue());
    selectedjoueur.setNom_joueur(extnom.getText());
    selectedjoueur.setNum_joueur(spnumero.getValue());
    selectedjoueur.setPosition_joueur(compos.getValue());
    jo.modifier(selectedjoueur);
    joutable.getItems().remove(selectedjoueur);
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("MODIFIER AVEC SUCCES");
            alert.showAndWait();}
 else {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Aucune sélection");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner une ligne à modifier.");
    alert.showAndWait();
}

    }

    @FXML
    private void majj(ActionEvent event) {
                obbjoueurs.clear();
                numj.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("num_joueur"));
                        nomj.setCellValueFactory(new PropertyValueFactory<joueur,String>("nom_joueur"));
                            agej.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("age_joueur"));
                                            posj.setCellValueFactory(new PropertyValueFactory<joueur,String>("Position_joueur"));
                                                    ServiceJoueur service = new ServiceJoueur();
        List<joueur> joueurs = service.getAll();
        obbjoueurs.addAll(joueurs);
joutable.setItems(obbjoueurs);

    }
    
    @FXML
    private void modijour(MouseEvent event) {
        joueur clickedjoueur = joutable.getSelectionModel().getSelectedItem();
        compos.setValue(clickedjoueur.getPosition_joueur());
       extnom.setText(String.valueOf(clickedjoueur.getNom_joueur()));
       spage.getValueFactory().setValue(clickedjoueur.getAge_joueur());
       spnumero.getValueFactory().setValue(clickedjoueur.getNum_joueur());        
    }
    
}
