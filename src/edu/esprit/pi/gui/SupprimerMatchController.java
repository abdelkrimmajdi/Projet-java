/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Match;
import edu.esprit.pi.services.ServiceMatch;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamma
 */
public class SupprimerMatchController implements Initializable {

    @FXML
    private TableView<Match> tableDB;
    @FXML
    private TableColumn<Match, String> clName_E1;
    @FXML
    private TableColumn<Match, String> clResultat;
    @FXML
    private TableColumn<Match, String> clName_E2;
    @FXML
    private TableColumn<Match, String> clNom_tournoi;

    /**
     *
     * /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clNom_tournoi.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_tournoi"));
        clName_E1.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_equipe1"));
        clName_E2.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_equipe2"));
        clResultat.setCellValueFactory(new PropertyValueFactory<Match, String>("Res"));
        ServiceMatch service = new ServiceMatch();
        List<Match> match = service.getAll();
        ObservableList<Match> observableMatch = FXCollections.observableArrayList(match);
        tableDB.setItems(observableMatch);
    }

    public void update() {
        clNom_tournoi.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_tournoi"));
        clName_E1.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_equipe1"));
        clName_E2.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_equipe2"));
        clResultat.setCellValueFactory(new PropertyValueFactory<Match, String>("Res"));
        ServiceMatch service = new ServiceMatch();
        List<Match> match = service.getAll();
        ObservableList<Match> observableMatch = FXCollections.observableArrayList(match);
        tableDB.setItems(observableMatch);
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        int selectedIndex = tableDB.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Match selectedMatch = tableDB.getItems().get(selectedIndex);
            int selectedID = selectedMatch.getId_match();
            ServiceMatch mac = new ServiceMatch();
            mac.supprimer(selectedID);
            tableDB.getItems().remove(selectedIndex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("SUPPRIMER AVEC SUCCES");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une ligne à supprimer.");
            alert.showAndWait();
        }
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fares.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Option");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
