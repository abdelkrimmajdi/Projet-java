/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Match;
import edu.esprit.pi.entities.Tournoi;
import edu.esprit.pi.services.ServiceMatch;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hamma
 */
public class ModifierTournoiController implements Initializable {

    @FXML
    private TextField txtAdresse;
    @FXML
    private Spinner<Integer> SpNbrEquipe;
    @FXML
    private TextArea TextListEquipe;
    @FXML
    private DatePicker DDate_tournoi;
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
    @FXML
    private TextField txtnom_tournoi;

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
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2,8);
        valueFactory.setValue(2);
        SpNbrEquipe.setValueFactory(valueFactory);
        ServiceTournoi service = new ServiceTournoi();
        List<Tournoi> Tournoi = service.getAll();
        ObservableList<Tournoi> observableTournoi = FXCollections.observableArrayList(Tournoi);
        tableDB1.setItems(observableTournoi);
        
    }    
    
    public void update(){
        clID_tournoi.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id_tournoi"));
        clID_terrain.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id_terrain"));
        clListEquipes.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("equipes"));
        clAdresse.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("adresse"));
        clNbr_Equipes.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("nbr_equipe"));
        clDates.setCellValueFactory(new PropertyValueFactory<Tournoi,Date>("date_tournoi"));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2,8);
        valueFactory.setValue(2);
        SpNbrEquipe.setValueFactory(valueFactory);
        ServiceTournoi service = new ServiceTournoi();
        List<Tournoi> Tournoi = service.getAll();
        ObservableList<Tournoi> observableTournoi = FXCollections.observableArrayList(Tournoi);
        tableDB1.setItems(observableTournoi);
    }

    @FXML
    private void modifier(ActionEvent event) {
        ServiceTournoi tour = new ServiceTournoi();
            if (txtAdresse.getText().isEmpty() ||txtnom_tournoi.getText().isEmpty() || TextListEquipe.getText().isEmpty() || txtId_tournoi.getText().isEmpty() ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
            else {
                String adresse=txtAdresse.getText();
                String equipes=TextListEquipe.getText();
                int nbr_equipe=SpNbrEquipe.getValue();
                String nom_tournoi=txtnom_tournoi.getText();
                LocalDate date = DDate_tournoi.getValue();
                Date date_tournoi = Date.valueOf(date);
                int id_tournoi= Integer.parseInt(txtId_tournoi.getText());
                Tournoi t = new Tournoi(id_tournoi,nom_tournoi,equipes,adresse,nbr_equipe,date_tournoi);
                tour.modifier(t);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Modifier AVEC SUCCES");
                alert.showAndWait();
                update();
                tableDB1.refresh();
                
            }
    }
}
    

