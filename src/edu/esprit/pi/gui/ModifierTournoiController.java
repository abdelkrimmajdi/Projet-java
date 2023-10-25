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
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private TableView<Tournoi> tableDB1;
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
    @FXML
    private TableColumn<Tournoi, String> clNom_tournoi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clNom_tournoi.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("nom_tournoi"));
        clListEquipes.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("equipes"));
        clAdresse.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("adresse"));
        clNbr_Equipes.setCellValueFactory(new PropertyValueFactory<Tournoi, Integer>("nbr_equipe"));
        clDates.setCellValueFactory(new PropertyValueFactory<Tournoi, Date>("date_tournoi"));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 8);
        valueFactory.setValue(2);
        SpNbrEquipe.setValueFactory(valueFactory);
        ServiceTournoi service = new ServiceTournoi();
        List<Tournoi> Tournoi = service.getAll();
        ObservableList<Tournoi> observableTournoi = FXCollections.observableArrayList(Tournoi);
        tableDB1.setItems(observableTournoi);

    }

    public void update() {
        clNom_tournoi.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("nom_tournoi"));
        clListEquipes.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("equipes"));
        clAdresse.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("adresse"));
        clNbr_Equipes.setCellValueFactory(new PropertyValueFactory<Tournoi, Integer>("nbr_equipe"));
        clDates.setCellValueFactory(new PropertyValueFactory<Tournoi, Date>("date_tournoi"));
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 8);
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
        Tournoi selectedTournoi = tableDB1.getSelectionModel().getSelectedItem();
        if (selectedTournoi != null) {
            selectedTournoi.setAdresse(txtAdresse.getText());
            selectedTournoi.setEquipes(TextListEquipe.getText());
            selectedTournoi.setNom_tournoi(txtnom_tournoi.getText());
            selectedTournoi.setNbr_equipe(SpNbrEquipe.getValue());
            selectedTournoi.setDate_tournoi(java.sql.Date.valueOf(DDate_tournoi.getValue()));
            tour.modifier(selectedTournoi);
            tableDB1.getItems().remove(selectedTournoi);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("MODIFIER AVEC SUCCES");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une ligne à modifier.");
            alert.showAndWait();
        }

        update();
        tableDB1.refresh();

    }

    @FXML
    private void selection(MouseEvent event) {
        Tournoi clickedTournoi = tableDB1.getSelectionModel().getSelectedItem();
        txtnom_tournoi.setText(String.valueOf(clickedTournoi.getNom_tournoi()));
        txtAdresse.setText(String.valueOf(clickedTournoi.getAdresse()));
        TextListEquipe.setText(String.valueOf(clickedTournoi.getEquipes()));
        SpNbrEquipe.getValueFactory().setValue(clickedTournoi.getNbr_equipe());
        LocalDate date_tournoi = clickedTournoi.getDate_tournoi().toLocalDate();
        DDate_tournoi.setValue(date_tournoi);
    }

    @FXML
    private void Retour(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("Tournoi.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Gestion des tournois");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
