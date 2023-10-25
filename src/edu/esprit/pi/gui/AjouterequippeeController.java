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
import javafx.print.PrinterJob;
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
//import tn.edu.esprit.entities.equipe;
//import tn.edu.esprit.services.ServiceEquipe;


/**
 * FXML Controller class
 *
 * @author benou
 */
public class AjouterequippeeController implements Initializable {

    @FXML
    private TableColumn<equipe, String> nomequipea;
    @FXML
    private TableColumn<equipe, String> couleurmaillota;
    @FXML
    private TableColumn<equipe, Integer> nbjoueureqa;
    @FXML
    private TextField nomequipppe;
    @FXML
    private Spinner<Integer> nombrejequipppe;
    @FXML
    private ComboBox<String> couleurrmaillotequi;
     @FXML
    private TableView<equipe> equipesaa;
     ObservableList<String> list = FXCollections.observableArrayList("","blanc","bleu","rouge et blanc","vert");
                ServiceEquipe service = new ServiceEquipe();
        List<equipe> equipes = service.getAll();
            ObservableList<equipe> obbequipe =FXCollections.observableArrayList(equipes);
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 nomequipea.setCellValueFactory(new PropertyValueFactory<equipe,String>("nom_equipe"));
                        nbjoueureqa.setCellValueFactory(new PropertyValueFactory<equipe,Integer>("nbjoueur_equipe"));
                            couleurmaillota.setCellValueFactory(new PropertyValueFactory<equipe,String>("couleurmaillot"));
SpinnerValueFactory<Integer> v = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,10);
                equipesaa.setItems(obbequipe);
                couleurrmaillotequi.setItems(list); 
   v.setValue(1);
  nombrejequipppe.setValueFactory(v);    }    

   
     public void  update ()
    {
                   obbequipe.clear();
        nomequipea.setCellValueFactory(new PropertyValueFactory<equipe,String>("nom_equipe"));
        nbjoueureqa.setCellValueFactory(new PropertyValueFactory<equipe,Integer>("nbjoueur_equipe"));
        couleurmaillota.setCellValueFactory(new PropertyValueFactory<equipe,String>("couleurmaillot"));
        ServiceEquipe service = new ServiceEquipe();
        List<equipe> equipes = service.getAll();
        ObservableList<equipe> obbequipe =FXCollections.observableArrayList(equipes);
        equipesaa.setItems(obbequipe);
    }

    @FXML
    private void ajoutereqqa(ActionEvent event) {
         ServiceEquipe q = new ServiceEquipe();
                 if (nomequipppe.getText().equals("") || couleurrmaillotequi.getValue().equals("") ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
           
        }
                 else {  String nom_equipe = nomequipppe.getText();
        int nbm = nombrejequipppe.getValue();
        String clmaillot = couleurrmaillotequi.getValue();
                        equipe e = new equipe(nom_equipe, nbm, clmaillot);
              q.ajouter(e);
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("AJOUT AVEC SUCCES");
            alert.showAndWait();
                 update(); }
    }


    @FXML
    private void mettereajourtable(ActionEvent event) {
           obbequipe.clear();


nomequipea.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
nbjoueureqa.setCellValueFactory(new PropertyValueFactory<>("nbjoueur_equipe"));
couleurmaillota.setCellValueFactory(new PropertyValueFactory<>("couleurmaillot"));

ServiceEquipe service = new ServiceEquipe();

List<equipe> equipes = service.getAll();
obbequipe.addAll(equipes);

equipesaa.setItems(obbequipe);
    }

    @FXML
    private void equipemoda(MouseEvent event) {
          equipe clickedequipe = equipesaa.getSelectionModel().getSelectedItem();
        couleurrmaillotequi.setValue(clickedequipe.getCouleurmaillot());
       nomequipppe.setText(String.valueOf(clickedequipe.getNom_equipe()));
       nombrejequipppe.getValueFactory().setValue(clickedequipe.getNbjoueur_equipe());
    }

    @FXML
    private void imprimer_tableau(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null ) 
        {        boolean success = job.printPage(equipesaa) ;
        if (success) { job.endJob();
        }
}
}

    @FXML
    private void RETOURACC(ActionEvent event) {
        
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


