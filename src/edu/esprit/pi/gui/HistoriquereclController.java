/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Reclamation;
import edu.esprit.pi.entities.Reponse;
import edu.esprit.pi.services.ServiceReclamation;
import edu.esprit.pi.services.ServiceReponse;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author majdiabdelkrim
 */
public class HistoriquereclController implements Initializable {

    @FXML
    private TextField etatad;
    @FXML
    private TextField repdescr;
    @FXML
    private Button reponse;
    @FXML
    private Button supprimerre;
    private TextField idrepon;
    @FXML
    private TableColumn<Reclamation, Integer> idreclamtion;
    @FXML
    private TableColumn<Reclamation, Integer> idus;
    @FXML
    private TableColumn<Reclamation, String> nul;
    @FXML
    private TableColumn<Reclamation, String> eml;
    @FXML   
    private TableColumn<Reclamation, String> desr;
    @FXML
    private TableColumn<Reclamation, String> eta;
    @FXML
    private TableColumn<Reclamation, String> typp;
    @FXML
    private TableColumn<Reclamation, String> datee;
    @FXML
    private TableView<Reclamation> tablerec;
    @FXML
    private TextField idrech;
    @FXML
    private Button gmr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    idreclamtion.setCellValueFactory(new PropertyValueFactory <Reclamation,Integer>("id_reclamation"));
     idus.setCellValueFactory(new PropertyValueFactory <Reclamation,Integer>("id_utilisateur"));
      nul.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("num"));
       eml.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("email"));
        desr.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("description"));
         typp.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
         eta.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("etat"));
          datee.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("date"));
    ServiceReclamation service = new ServiceReclamation();
    List<Reclamation> reclamations = service.getAll();
    ObservableList<Reclamation> observableReclamations = FXCollections.observableArrayList(reclamations);
    tablerec.setItems(observableReclamations);
       
    }    

    @FXML
    private void reponserecl(ActionEvent event) {
    
         String etat = etatad.getText();
        String description = repdescr.getText();
        if(tablerec.getSelectionModel().getSelectedItem()!=null){
            if(etat.isEmpty() || description.isEmpty()){
                Alert alert = new Alert(AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez rempli les champs ");        
        alert.show();
            }
            
              
            else{
         int id=tablerec.getSelectionModel().getSelectedItem().getId_reclamation();
         ServiceReponse service = new ServiceReponse();
        Reponse rec = new Reponse(id, description, etat);
        service.ajouter(rec);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation repondue avec succés!");        
        alert.show();
        mise();
            }}
        else{
                 Alert alert = new Alert(AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veiller selectionner une reclamation!");
        alert.show();
            }}
            
           

    @FXML
    private void supprimerreclamatio(ActionEvent event) {
       

  
        if(tablerec.getSelectionModel().getSelectedItem()==null){
             Alert alert = new Alert(AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veiller selectionner une reclamation!");  
        alert.show();
        }
        
       if(tablerec.getSelectionModel().getSelectedItem()!=null){
            int id=tablerec.getSelectionModel().getSelectedItem().getId_reclamation();
        ServiceReclamation service = new ServiceReclamation();
        service.supprimer(id);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation supprimer avec succés!");        
        alert.show();
        mise();           
       }
      
    } 
    
    

    private void mise(){
              idreclamtion.setCellValueFactory(new PropertyValueFactory <Reclamation,Integer>("id_reclamation"));
     idus.setCellValueFactory(new PropertyValueFactory <Reclamation,Integer>("id_utilisateur"));
      nul.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("num"));
       eml.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("email"));
        desr.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("description"));
         typp.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
         eta.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("etat"));
          datee.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("date"));
    ServiceReclamation service = new ServiceReclamation();
    List<Reclamation> reclamations = service.getAll();
    ObservableList<Reclamation> observableReclamations = FXCollections.observableArrayList(reclamations);
    tablerec.setItems(observableReclamations);
        
    }

    @FXML
    private void Rechercher(ActionEvent event) {
    }

    @FXML
    private void Tri(ActionEvent event) {
    }

    @FXML
    private void gerermesreponse(ActionEvent event)  {
            try {
        Parent root = FXMLLoader.load(getClass().getResource("reponseadmin.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Historique des reponses");
        currentStage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        
        
    }


}
        
        
        
        
    

