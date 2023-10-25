/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Match;
import edu.esprit.pi.entities.Tournoi;
import edu.esprit.pi.entities.equipe;
import edu.esprit.pi.services.ServiceEquipe;
import edu.esprit.pi.services.ServiceMatch;
import edu.esprit.pi.services.ServiceTournoi;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamma
 */
public class ModifierMatchController implements Initializable {

    @FXML
    private TextField TxtNom_equipe2;
    @FXML
    private ComboBox<String> cbRes;
    @FXML
    private TextField txtNom_equipe1;
    ObservableList<String> list = FXCollections.observableArrayList("1", "2");
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
    @FXML
    private TableColumn<equipe, String> clEquipe;
    @FXML
    private TableView<equipe> table_listEquipe;
    @FXML
    private TableColumn<Tournoi, String> clTournoi;
    @FXML
    private TableView<Tournoi> tableNom_tournoi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clNom_tournoi.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_tournoi"));
        clName_E1.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_equipe1"));
        clName_E2.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_equipe2"));
        clResultat.setCellValueFactory(new PropertyValueFactory<Match, String>("Res"));
        cbRes.setItems(list);
        ServiceMatch service = new ServiceMatch();
        List<Match> match = service.getAll();
        ObservableList<Match> observableMatch = FXCollections.observableArrayList(match);
        tableDB.setItems(observableMatch);
        clEquipe.setCellValueFactory(new PropertyValueFactory<equipe, String>("nom_equipe"));
        ServiceEquipe service2 = new ServiceEquipe();
        List<equipe> eq = service2.getAll();
        ObservableList<equipe> observableequipe = FXCollections.observableArrayList(eq);
        table_listEquipe.setItems(observableequipe);
        clTournoi.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("nom_tournoi"));
        ServiceTournoi service3 = new ServiceTournoi();
        List<Tournoi> tournoi = service3.getAll();
        ObservableList<Tournoi> observabletournoi = FXCollections.observableArrayList(tournoi);
        tableNom_tournoi.setItems(observabletournoi);
    }

    public void update() {
        clNom_tournoi.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_tournoi"));
        clName_E1.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_equipe1"));
        clName_E2.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_equipe2"));
        clResultat.setCellValueFactory(new PropertyValueFactory<Match, String>("Res"));
        cbRes.setItems(list);
        ServiceMatch service = new ServiceMatch();
        List<Match> match = service.getAll();
        ObservableList<Match> observableMatch = FXCollections.observableArrayList(match);
        tableDB.setItems(observableMatch);
        clEquipe.setCellValueFactory(new PropertyValueFactory<equipe, String>("nom_equipe"));
        ServiceEquipe service2 = new ServiceEquipe();
        List<equipe> equipe = service2.getAll();
        ObservableList<equipe> observableequipe = FXCollections.observableArrayList(equipe);
        table_listEquipe.setItems(observableequipe);
        clTournoi.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("nom_tournoi"));
        ServiceTournoi service3 = new ServiceTournoi();
        List<Tournoi> tournoi = service3.getAll();
        ObservableList<Tournoi> observabletournoi = FXCollections.observableArrayList(tournoi);
        tableNom_tournoi.setItems(observabletournoi);
    }

    @FXML
    private void modifier(ActionEvent event) {
        ServiceMatch mac = new ServiceMatch();
        Match selectedMatch = tableDB.getSelectionModel().getSelectedItem();
        if (selectedMatch != null) {
            selectedMatch.setNom_equipe1(txtNom_equipe1.getText());
            selectedMatch.setNom_equipe2(TxtNom_equipe2.getText());
            selectedMatch.setRes(cbRes.getValue());
            mac.modifier(selectedMatch);
            tableDB.getItems().remove(selectedMatch);
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
        tableDB.refresh();
    }

    @FXML
    private void selection(MouseEvent event) {
        Match clickedMatch = tableDB.getSelectionModel().getSelectedItem();
        txtNom_equipe1.setText(String.valueOf(clickedMatch.getNom_equipe1()));
        TxtNom_equipe2.setText(String.valueOf(clickedMatch.getNom_equipe2()));
        cbRes.setValue(clickedMatch.getRes());
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fares.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Gestion des matchs");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


        

