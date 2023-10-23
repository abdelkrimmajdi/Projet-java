/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Match;
import edu.esprit.pi.services.ServiceMatch;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hamma
 */
public class SupprimerMatchController implements Initializable {

    @FXML
    private TextField txtId_match;
    @FXML
    private TableView<Match> tableDB;
    @FXML
    private TableColumn<Match, Integer> clID_match;
    @FXML
    private TableColumn<Match, Integer> clID_equipe1;
    @FXML
    private TableColumn<Match, Integer> clID_equipe2;
    @FXML
    private TableColumn<Match, String> clName_E1;
    @FXML
    private TableColumn<Match, String> clResultat;
    @FXML
    private TableColumn<Match, String> clName_E2;
    /**

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clID_match.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_match"));
        clID_equipe1.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_equipe1"));
        clID_equipe2.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_equipe2"));
        clName_E1.setCellValueFactory(new PropertyValueFactory<Match,String>("nom_equipe1"));
        clName_E2.setCellValueFactory(new PropertyValueFactory<Match,String>("nom_equipe2"));
        clResultat.setCellValueFactory(new PropertyValueFactory<Match,String>("Res"));
        ServiceMatch service = new ServiceMatch();
        List<Match> match = service.getAll();
        ObservableList<Match> observableMatch = FXCollections.observableArrayList(match);
        tableDB.setItems(observableMatch);
    }    
    
    public void update(){
        clID_match.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_match"));
        clID_equipe1.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_equipe1"));
        clID_equipe2.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_equipe2"));
        clName_E1.setCellValueFactory(new PropertyValueFactory<Match,String>("nom_equipe1"));
        clName_E2.setCellValueFactory(new PropertyValueFactory<Match,String>("nom_equipe2"));
        clResultat.setCellValueFactory(new PropertyValueFactory<Match,String>("Res"));
        ServiceMatch service = new ServiceMatch();
        List<Match> match = service.getAll();
        ObservableList<Match> observableMatch = FXCollections.observableArrayList(match);
        tableDB.setItems(observableMatch);
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        ServiceMatch mac = new ServiceMatch();
            if (txtId_match.getText().isEmpty() ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
            else {
                int id_match= Integer.parseInt(txtId_match.getText());
                mac.supprimer(id_match);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Suppimer AVEC SUCCES");
                alert.showAndWait();
                update();
                
                        
                
        }}
    }
    

