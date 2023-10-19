/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Reponse;
import edu.esprit.pi.services.ServiceReponse;
import java.io.IOException;
import java.net.URL;
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
public class HistoriquerepUserController implements Initializable {

    @FXML
    private TableView<Reponse> tablerecluser;
    @FXML
    private TableColumn<Reponse,Integer> idrepu;
    @FXML
    private TableColumn<Reponse,Integer> idrec;
    @FXML
    private TableColumn<Reponse,String> txtrep;
    @FXML
    private TableColumn<Reponse,String> etat;
    @FXML
    private TableColumn<Reponse,String> daterep;
    @FXML
    private TextField txtrepc;
    @FXML
    private TextField txtcin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retourhistoriquereclamationuser(ActionEvent event) {
       
    try {
        Parent root = FXMLLoader.load(getClass().getResource("Historiqueuser.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Takwira+");
        currentStage.show();
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }        
    }

    @FXML
    private void trirepuser(ActionEvent event) {
    }

    @FXML
    private void rechrepuser(ActionEvent event) {
         idrepu.setCellValueFactory(new PropertyValueFactory <Reponse,Integer>("id_reponse"));
 idrec.setCellValueFactory(new PropertyValueFactory <Reponse,Integer>("id_reclamation"));
  etat.setCellValueFactory(new PropertyValueFactory <Reponse,String>("etat"));
  daterep.setCellValueFactory(new PropertyValueFactory <Reponse,String>("date_reponse"));
    txtrep.setCellValueFactory(new PropertyValueFactory <Reponse,String>("text_reponse"));
      if(txtcin.getText().isEmpty()){
     Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir  champs de id!");        
        alert.show();}
     else{
          int idReponse = Integer.parseInt(txtcin.getText());
    ServiceReponse service = new ServiceReponse();
   List <Reponse>  rec = service.getOnee(idReponse);   
     if (rec != null) {
        ObservableList<Reponse> observableReponse = FXCollections.observableArrayList(rec);
        tablerecluser.setItems(observableReponse);
    } 
     }
        
    }

    @FXML
    private void majj(ActionEvent event) {
     
 idrepu.setCellValueFactory(new PropertyValueFactory <Reponse,Integer>("id_reponse"));
 idrec.setCellValueFactory(new PropertyValueFactory <Reponse,Integer>("id_reclamation"));
  etat.setCellValueFactory(new PropertyValueFactory <Reponse,String>("etat"));
  daterep.setCellValueFactory(new PropertyValueFactory <Reponse,String>("date_reponse"));
    txtrep.setCellValueFactory(new PropertyValueFactory <Reponse,String>("text_reponse"));
  if(txtrepc.getText().isEmpty()){
     Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir  champs de cin!");        
        alert.show();}
     else{
    ServiceReponse service = new ServiceReponse();
    List<Reponse>  rec = service.afficher(txtrepc.getText());   
     if (rec != null) {
        ObservableList<Reponse> observableReponse = FXCollections.observableArrayList(rec);
        tablerecluser.setItems(observableReponse);
    } 
     }
    }
    
}
