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
import java.sql.Date;
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
public class ReponseAdminController implements Initializable {

    @FXML
    private TableColumn<Reponse, Integer> idreponse;
    @FXML
    private TableColumn<Reponse, Integer> idrecrep;
    @FXML
    private TableColumn<Reponse, String> daterep;
    @FXML
    private TableColumn<Reponse,String> texterep;
    @FXML
    private Button modad;
    @FXML
    private TableView<Reponse> tablerepadmin;
    @FXML
    private TextField Etatrepp;
    @FXML
    private TextField dsecrrecl;
    @FXML
    private TableColumn<Reponse,String> ett;
    @FXML
    private TextField idrech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  idreponse.setCellValueFactory(new PropertyValueFactory <Reponse,Integer>("id_reponse"));
     idrecrep.setCellValueFactory(new PropertyValueFactory <Reponse,Integer>("id_reclamation"));
       daterep.setCellValueFactory(new PropertyValueFactory <Reponse,String>("date_reponse"));
      texterep.setCellValueFactory(new PropertyValueFactory <Reponse,String>("text_reponse"));
     ett.setCellValueFactory(new PropertyValueFactory <Reponse,String>("etat")); 
    
    ServiceReponse service = new ServiceReponse();
    List<Reponse> reponses = service.getAll();
    ObservableList<Reponse> observableReponse = FXCollections.observableArrayList(reponses);
    tablerepadmin.setItems(observableReponse);    }    

    @FXML
    private void modifieradrep(ActionEvent event) {
          try {ServiceReponse service =new ServiceReponse();
        Reponse selectedReclamation = tablerepadmin.getSelectionModel().getSelectedItem();
    if (selectedReclamation != null) {
         selectedReclamation.setEtat(Etatrepp.getText());
        selectedReclamation.setText_reponse(dsecrrecl.getText());
        service.modifier(selectedReclamation);
        if(Etatrepp.getText().isEmpty()||dsecrrecl.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir les champs!");        
        alert.show();
       
            
        }
        else{
        Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reponse modifier avec succés!");        
        alert.show();
        tablerepadmin.refresh();}}
        else{
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuilez selectionnée!");        
        alert.show();   
                
    }}
           catch (NumberFormatException e) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de la modification!");        
        alert.show();
       
    }   
    
}

    @FXML
    private void supprimerr(ActionEvent event) {
        
       if(tablerepadmin.getSelectionModel().getSelectedItem()!=null){
            int id=tablerepadmin.getSelectionModel().getSelectedItem().getId_reponse();
        ServiceReponse service = new ServiceReponse();
        service.supprimer(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reponse supprimer avec succés!");        
        alert.show();
        update();}
       else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez selectionnée!");        
        alert.show();   
       }     
    }

    private void update() {
        idreponse.setCellValueFactory(new PropertyValueFactory <Reponse,Integer>("id_reponse"));
     idrecrep.setCellValueFactory(new PropertyValueFactory <Reponse,Integer>("id_reclamation"));
       daterep.setCellValueFactory(new PropertyValueFactory <Reponse,String>("date_reponse"));
      texterep.setCellValueFactory(new PropertyValueFactory <Reponse,String>("text_reponse"));
     ett.setCellValueFactory(new PropertyValueFactory <Reponse,String>("etat")); 
    
    ServiceReponse service = new ServiceReponse();
    List<Reponse> reponses = service.getAll();
    ObservableList<Reponse> observableReponse = FXCollections.observableArrayList(reponses);
    tablerepadmin.setItems(observableReponse);
    }

    @FXML
    private void retourner(ActionEvent event) {
          try {
        Parent root = FXMLLoader.load(getClass().getResource("Historiquerecl.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Historique des Reclamation");
        currentStage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }        
    }

    @FXML
    private void Rechercher(ActionEvent event) {
            idreponse.setCellValueFactory(new PropertyValueFactory <Reponse,Integer>("id_reponse"));
     idrecrep.setCellValueFactory(new PropertyValueFactory <Reponse,Integer>("id_reclamation"));
       daterep.setCellValueFactory(new PropertyValueFactory <Reponse,String>("date_reponse"));
      texterep.setCellValueFactory(new PropertyValueFactory <Reponse,String>("text_reponse"));
     ett.setCellValueFactory(new PropertyValueFactory <Reponse,String>("etat")); 
    
      if(idrech.getText().isEmpty()){
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir  champs de id!");        
        alert.show();}
     else{
          int idReponse = Integer.parseInt(idrech.getText());
    ServiceReponse service = new ServiceReponse();
   List <Reponse>  rec = service.getOnee(idReponse);   
     if (rec != null) {
        ObservableList<Reponse> observableReponse = FXCollections.observableArrayList(rec);
        tablerepadmin.setItems(observableReponse);
    } 
    }}

    @FXML
    private void Tri(ActionEvent event) {
    }
    
}
