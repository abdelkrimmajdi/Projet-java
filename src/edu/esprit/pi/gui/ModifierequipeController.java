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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
//import tn.edu.esprit.entities.equipe;
//import tn.edu.esprit.entities.joueur;
//import tn.edu.esprit.services.ServiceEquipe;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author benou
 */
public class ModifierequipeController implements Initializable {

    @FXML
    private TextField nom_equipe;
    @FXML
    private Spinner<Integer> Spnbjoueur;
    @FXML
    private TableView<equipe> equipess;
    @FXML
    private TableColumn<equipe, String> nomeqq;
    @FXML
    private TableColumn<equipe, Integer> nbjoueur;
    @FXML
    private TableColumn<equipe, String> couleurmaillot;
    @FXML
    private ComboBox<String> clbox;
    @FXML
    private Button maj_button;
    @FXML
    private Button modifier_button;
        @FXML
    private TextField textrech;

    /**
     * Initializes the controller class.
     */
    
                ObservableList<String> list = FXCollections.observableArrayList("","blanc","bleu","rouge et blanc");
                ServiceEquipe service = new ServiceEquipe();
        List<equipe> equipes = service.getAll();
            ObservableList<equipe> obequipe =FXCollections.observableArrayList(equipes);
    
            

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                nomeqq.setCellValueFactory(new PropertyValueFactory<equipe,String>("nom_equipe"));
                        nbjoueur.setCellValueFactory(new PropertyValueFactory<equipe,Integer>("nbjoueur_equipe"));
                            couleurmaillot.setCellValueFactory(new PropertyValueFactory<equipe,String>("couleurmaillot"));
SpinnerValueFactory<Integer> v = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,10);
                equipess.setItems(obequipe);
                clbox.setItems(list); 
   v.setValue(1);
   Spnbjoueur.setValueFactory(v);
   FilteredList<equipe> filtredequipe = new FilteredList<>(obequipe, p -> true);

        textrech.textProperty().addListener((observable, oldValue, newValue) -> {
            filtredequipe.setPredicate(equipe -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return equipe.getNom_equipe().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<equipe> sortedequipe = new SortedList<>(filtredequipe);

        sortedequipe.comparatorProperty().bind(equipess.comparatorProperty());
        equipess.setItems(sortedequipe);
    }    

   

    @FXML
    private void modifier(ActionEvent event) {
                ServiceEquipe eq = new ServiceEquipe();
   equipe selectedequipe = equipess.getSelectionModel().getSelectedItem();
if (selectedequipe != null ) {
    selectedequipe.setNom_equipe(nom_equipe.getText());
    selectedequipe.setNbjoueur_equipe(Spnbjoueur.getValue());
    selectedequipe.setCouleurmaillot(clbox.getValue());
    eq.modifier(selectedequipe);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("AJOUT AVEC SUCCES");
            alert.showAndWait();
    equipess.getItems().remove(selectedequipe);}
 else {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Aucune sélection");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner une ligne à modifier.");
    alert.showAndWait();
}
    }
          
      @FXML
 private void mettre_a_jour(ActionEvent event) {


obequipe.clear();

nomeqq.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
nbjoueur.setCellValueFactory(new PropertyValueFactory<>("nbjoueur_equipe"));
couleurmaillot.setCellValueFactory(new PropertyValueFactory<>("couleurmaillot"));

ServiceEquipe service = new ServiceEquipe();

List<equipe> equipes = service.getAll();

obequipe.addAll(equipes);

equipess.setItems(obequipe);

}
    @FXML
    private void equipeclick(MouseEvent event) {
       equipe clickedequipe = equipess.getSelectionModel().getSelectedItem();
       nom_equipe.setText(String.valueOf(clickedequipe.getNom_equipe()));
       Spnbjoueur.getValueFactory().setValue(clickedequipe.getNbjoueur_equipe());
       clbox.setValue(clickedequipe.getCouleurmaillot());
    }    

    @FXML
    private void savetablee(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Enregistrer le fichier");
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt");
    fileChooser.getExtensionFilters().add(extFilter);
    Stage primaryStage = new Stage(); 
    File file = fileChooser.showSaveDialog(primaryStage);
    if (file != null) { 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (equipe equipe : obequipe) {
                String ligne = "Nom Equipe : " + equipe.getNom_equipe() + ", Nombre de joueurs : " + equipe.getNbjoueur_equipe() + ", Couleur Maillot : " + equipe.getCouleurmaillot() + "\n";
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
    private void EQUIPEACCEUIL(ActionEvent event) {
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
