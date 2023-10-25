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
public class AjouterController implements Initializable {

    @FXML
    private TextField txtNom_equipe1;
    @FXML
    private ComboBox<String> cbRes;
    @FXML
    private TextField TxtNom_equipe2;
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
    private TextField txtnom_tournoi;
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

        ServiceMatch service = new ServiceMatch();
        List<Match> match = service.getAll();
        System.out.println(match);
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

        cbRes.setItems(list);

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
        
        cbRes.setItems(list);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        ServiceMatch mac = new ServiceMatch();
        if (TxtNom_equipe2.getText().isEmpty() || txtnom_tournoi.getText().isEmpty() || cbRes.getValue().isEmpty() || txtNom_equipe1.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();

        } else {
            String nom_equipe1 = txtNom_equipe1.getText();
            String nom_equipe2 = TxtNom_equipe2.getText();
            String res = cbRes.getValue();
            String nom_tournoi = txtnom_tournoi.getText();
            Match m = new Match(res, nom_tournoi, nom_equipe1, nom_equipe2);
            mac.ajouter(m);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Ajouter AVEC SUCCES");
            alert.showAndWait();
            update();
        }
    }

    @FXML
    private void selection(MouseEvent event) {
        Match clickedMatch = tableDB.getSelectionModel().getSelectedItem();
        txtNom_equipe1.setText(String.valueOf(clickedMatch.getNom_equipe1()));
        TxtNom_equipe2.setText(String.valueOf(clickedMatch.getNom_equipe2()));
        txtnom_tournoi.setText(String.valueOf(clickedMatch.getNom_tournoi()));
        cbRes.setValue(clickedMatch.getRes());
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fares.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Gestion des Match");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


