/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Reclamation;
import edu.esprit.pi.services.ServiceReclamation;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import edu.esprit.pi.gui.ReclamationajController;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author majdiabdelkrim
 */
public class HistoriqueuserController implements Initializable {

    @FXML
    private TableColumn<Reclamation, Integer> idhru;
    @FXML
    private TableColumn<Reclamation, String> typehra;
    @FXML
    private TableColumn<Reclamation, String> numhra;
    @FXML
    private TableColumn<Reclamation, String> emailhra;
    @FXML
    private TableColumn<Reclamation, String> deschra;
    @FXML
    private TextField textdehra;
    @FXML
    private TextField txttyhra;
    @FXML
    private TextField txtidhra;
    @FXML
    private TableView<Reclamation> tableuserrec;
    @FXML
    private TableColumn<Reclamation, String> etathru;
    @FXML
    private TableColumn<Reclamation, String> hra;
    @FXML
    private TableColumn<Reclamation, String> datehru;
    @FXML
    private TextField txtcin;

    /**
     * Initializes the controller class.
     */
    
     public  void getCinnn(String cin){

    this.txtcin.setText(cin);
            }

    public void initialize(URL url, ResourceBundle rb) {
         idhru.setCellValueFactory(new PropertyValueFactory <Reclamation,Integer>("id_reclamation"));
     typehra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
       numhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("num"));
      emailhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("email"));
     deschra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("description")); 
     etathru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("etat"));
     hra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
     datehru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("date"));
   
    ServiceReclamation service = new ServiceReclamation();
    List<Reclamation>  rec = service.afficher(txtcin.getText());   
     
        ObservableList<Reclamation> observableReclamation = FXCollections.observableArrayList(rec);
        tableuserrec.setItems(observableReclamation);
    
        
    } 
    

    @FXML
    private void supphra(ActionEvent event) {
        if(tableuserrec.getSelectionModel().getSelectedItem()!=null){
            int id=tableuserrec.getSelectionModel().getSelectedItem().getId_reclamation();
        ServiceReclamation service = new ServiceReclamation();
        service.supprimer(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation supprimer avec succés!");        
        alert.show();
        tableuserrec.refresh();}
       else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez selectionnée!");        
        alert.show();   
       }     
        
    }

    @FXML
    private void modihra(ActionEvent event) {
          
            ServiceReclamation service =new ServiceReclamation();
        Reclamation selectedReclamation = tableuserrec.getSelectionModel().getSelectedItem();
    if (selectedReclamation != null) {
         selectedReclamation.setEtat(textdehra.getText());
        selectedReclamation.setType(txttyhra.getText());
        service.modifier(selectedReclamation);
        if(txttyhra.getText().isEmpty()||textdehra.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir les champs!");        
        alert.show();
       
            
        }
        else{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation modifier avec succés!");        
        alert.show();
            tableuserrec.refresh();
                    }
    }
        else{
             Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuilez selectionnée!");        
        alert.show();   
                
    
    }}

    @FXML
    private void Rechercher(ActionEvent event) {
          idhru.setCellValueFactory(new PropertyValueFactory <Reclamation,Integer>("id_reclamation"));
     typehra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
       numhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("num"));
      emailhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("email"));
     deschra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("description")); 
     etathru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("etat"));
     hra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
     datehru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("date"));
    
     if(!txtidhra.getText().isEmpty()){
          int idReclamation = Integer.parseInt(txtidhra.getText());
    ServiceReclamation service = new ServiceReclamation();
    Reclamation  rec = service.getOne(idReclamation);
    ObservableList<Reclamation> observableReclamation = FXCollections.observableArrayList(rec);
    tableuserrec.setItems(observableReclamation);
    }
     else{
         Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir  champs de id!");        
        alert.show();
         
     }
    }
    

    @FXML
    private void Tri(ActionEvent event) {
    }

    @FXML
    private void Reponseuser(ActionEvent event) {
                 try {
        Parent root = FXMLLoader.load(getClass().getResource("HistoriquerepUser.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Historique des Reponses");
        currentStage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }        
    }

    @FXML
    private void Mettreaj(ActionEvent event) {
        idhru.setCellValueFactory(new PropertyValueFactory <Reclamation,Integer>("id_reclamation"));
     typehra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
       numhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("num"));
      emailhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("email"));
     deschra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("description")); 
     etathru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("etat"));
     hra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
     datehru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("date"));
     if(txtcin.getText().isEmpty()){
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir  champs de cin!");        
        alert.show();}
     else{
    ServiceReclamation service = new ServiceReclamation();
    List<Reclamation>  rec = service.afficher(txtcin.getText());   
     if (rec != null) {
        ObservableList<Reclamation> observableReclamation = FXCollections.observableArrayList(rec);
        tableuserrec.setItems(observableReclamation);
    } 
     }
    }

    @FXML
    private void Retour(ActionEvent event) {
         try {
        Parent root = FXMLLoader.load(getClass().getResource("Reclamationaj.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Historique des Reponses");
        currentStage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }        
    }
    }
    

