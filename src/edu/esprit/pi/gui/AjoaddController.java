/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.edu.esprit.entities.joueur;
import tn.edu.esprit.services.ServiceJoueur;

/**
 * FXML Controller class
 *
 * @author benou
 */
public class AjoaddController implements Initializable {

    @FXML
    private TextField joueurnom;
    @FXML
    private ComboBox<String> joueurposition;
    @FXML
    private Spinner<Integer> joueurnum;
    @FXML
    private Spinner<Integer> joueurage;
    @FXML
    private TableView<joueur> joueurrs;
    @FXML
    private TableColumn<joueur, Integer> nmr;
    @FXML
    private TableColumn<joueur, String> nomj;
    @FXML
    private TableColumn<joueur, Integer> agej;
    @FXML
    private TableColumn<joueur, String> posj;
    
     ObservableList<String> listp = FXCollections.observableArrayList("","guardien","defenseur","milieu", "attaquant");
                ServiceJoueur service = new ServiceJoueur();
        List<joueur> joueurs = service.getAll();
            ObservableList<joueur> obbjoueurs =FXCollections.observableArrayList(joueurs);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                nomj.setCellValueFactory(new PropertyValueFactory<joueur,String>("nom_joueur"));
                        agej.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("age_joueur"));
                            posj.setCellValueFactory(new PropertyValueFactory<joueur,String>("Position_joueur"));
                                                    nmr.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("num_joueur"));

SpinnerValueFactory<Integer> v = new SpinnerValueFactory.IntegerSpinnerValueFactory(10,100);
                joueurrs.setItems(obbjoueurs);
                joueurposition.setItems(listp); 
   v.setValue(1);
  joueurnum.setValueFactory(v);
  SpinnerValueFactory<Integer> j = new SpinnerValueFactory.IntegerSpinnerValueFactory(10,100);

  joueurage.setValueFactory(j);
  
  
        // TODO
    }    

    @FXML
    private void joadd(ActionEvent event) {
        ServiceJoueur j = new ServiceJoueur();
                 if (joueurnom.getText().equals("") || joueurposition.getValue().equals("")|| joueurnum.getValue().equals("")|| joueurage.getValue().equals("") ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
                 else {

        String nomjoueur = joueurnom.getText();
        int numj = joueurnum.getValue();
        int agej=joueurage.getValue();
        String positionj = joueurposition.getValue();
            joueur jo = new joueur(numj,nomjoueur,agej,positionj);
            j.ajouter(jo);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("AJOUT AVEC SUCCES");
            alert.showAndWait();}
        
    }

    @FXML
    private void tmaj(ActionEvent event) {
        obbjoueurs.clear();                nomj.setCellValueFactory(new PropertyValueFactory<joueur,String>("nom_joueur"));
                        agej.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("age_joueur"));
                            posj.setCellValueFactory(new PropertyValueFactory<joueur,String>("Position_joueur"));
                                                    nmr.setCellValueFactory(new PropertyValueFactory<joueur,Integer>("num_joueur"));
                                                            ServiceJoueur j = new ServiceJoueur();
                                                                    List<joueur> joueurs = service.getAll();
obbjoueurs.addAll(joueurs);
joueurrs.setItems(obbjoueurs);


        
    }

    @FXML
    private void Modijoue(ActionEvent event) {
         try {
            Parent root =  FXMLLoader.load(getClass().getResource("modijoueur.fxml"));

            Stage stage = new Stage();
            stage.setTitle("MODIFIER JOUEUR");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(AjoueurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Suppjoue(ActionEvent event) {
         try {
            Parent root =  FXMLLoader.load(getClass().getResource("suppjou.fxml"));

            Stage stage = new Stage();
            stage.setTitle("SUPPRIMER JOUEUR");
            stage.setScene(new Scene(root));
            
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(AjoueurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void jouaddt(MouseEvent event) {
        joueur clickedjoueur = joueurrs.getSelectionModel().getSelectedItem();
       joueurnom.setText(String.valueOf(clickedjoueur.getNom_joueur()));
       joueurage.getValueFactory().setValue(clickedjoueur.getAge_joueur());
       joueurnum.getValueFactory().setValue(clickedjoueur.getNum_joueur());   
       joueurposition.setValue(clickedjoueur.getPosition_joueur());
    }
    
}
