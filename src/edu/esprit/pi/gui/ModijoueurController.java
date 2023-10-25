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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//import tn.edu.esprit.entities.joueur;
//import tn.edu.esprit.services.ServiceJoueur;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    @FXML
    private TextField textrech;
    
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
    
FilteredList<joueur> filteredJoueurs = new FilteredList<>(obbjoueurs, p -> true);

        textrech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredJoueurs.setPredicate(joueur -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return joueur.getNom_joueur().toLowerCase().contains(lowerCaseFilter) || joueur.getPosition_joueur().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<joueur> sortedJoueurs = new SortedList<>(filteredJoueurs);

        sortedJoueurs.comparatorProperty().bind(joutable.comparatorProperty());
        joutable.setItems(sortedJoueurs);
        
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
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("AJOUT AVEC SUCCES");
            alert.showAndWait();
    joutable.getItems().remove(selectedjoueur);
    }
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

   @FXML
private void savetablee(ActionEvent event) {
   FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Enregistrer le fichier");

// Définissez un filtre d'extension si nécessaire
FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt");
fileChooser.getExtensionFilters().add(extFilter);

// Affichez le FileChooser et attendez que l'utilisateur sélectionne un fichier
Stage primaryStage = new Stage(); // Vous devez avoir une instance de Stage pour afficher le FileChooser
File file = fileChooser.showSaveDialog(primaryStage);

if (file != null) { // Vérifiez si l'utilisateur a sélectionné un fichier
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        for (joueur joueur : obbjoueurs) {
            String ligne = "Nom Joueur : " + joueur.getNom_joueur() + ", Numéro Joueur : " + joueur.getNum_joueur() + ", Age Joueur : " + joueur.getAge_joueur() + ", Position Joueur : " + joueur.getPosition_joueur() + "\n";
            writer.write(ligne);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Données enregistrées avec succès.");
        alert.showAndWait();
    } catch (IOException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de l'enregistrement des données.");
        alert.showAndWait();
    }
}
}

    @FXML
    private void RETOURACC(ActionEvent event) {
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
