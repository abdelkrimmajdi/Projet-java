/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Tournoi;
import edu.esprit.pi.services.ServiceTournoi;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hamma
 */
public class SupprimerTournoiController implements Initializable {

    
    @FXML
    private TextField txtId_tournoi;
    @FXML
    private TableView<Tournoi> tableDB1;
    @FXML
    private TableColumn<Tournoi, Integer> clID_tournoi;
    @FXML
    private TableColumn<Tournoi, Integer> clID_terrain;
    @FXML
    private TableColumn<Tournoi, String> clListEquipes;
    @FXML
    private TableColumn<Tournoi, String> clAdresse;
    @FXML
    private TableColumn<Tournoi, Integer> clNbr_Equipes;
    @FXML
    private TableColumn<Tournoi, Date> clDates;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clID_tournoi.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id_tournoi"));
        clID_terrain.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id_terrain"));
        clListEquipes.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("equipes"));
        clAdresse.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("adresse"));
        clNbr_Equipes.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("nbr_equipe"));
        clDates.setCellValueFactory(new PropertyValueFactory<Tournoi,Date>("date_tournoi"));
        ServiceTournoi service = new ServiceTournoi();
        List<Tournoi> Tournoi = service.getAll();
        ObservableList<Tournoi> observableTournoi = FXCollections.observableArrayList(Tournoi);
        tableDB1.setItems(observableTournoi);
    }    
    
    @FXML
    public void update(){
        clID_tournoi.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id_tournoi"));
        clID_terrain.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id_terrain"));
        clListEquipes.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("equipes"));
        clAdresse.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("adresse"));
        clNbr_Equipes.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("nbr_equipe"));
        clDates.setCellValueFactory(new PropertyValueFactory<Tournoi,Date>("date_tournoi"));
        ServiceTournoi service = new ServiceTournoi();
        List<Tournoi> Tournoi = service.getAll();
        ObservableList<Tournoi> observableTournoi = FXCollections.observableArrayList(Tournoi);
        tableDB1.setItems(observableTournoi);
    }


    @FXML
    private void modifier(ActionEvent event) {
        ServiceTournoi tour = new ServiceTournoi();
            if (txtId_tournoi.getText().isEmpty() ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
            else {
                int id_tournoi= Integer.parseInt(txtId_tournoi.getText());
                 tour.supprimer(id_tournoi);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Supprimer AVEC SUCCES");
                alert.showAndWait();
                update();
                
            }
    }
    
}
