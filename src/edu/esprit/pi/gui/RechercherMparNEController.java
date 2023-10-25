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
import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamma
 */
public class RechercherMparNEController implements Initializable {

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
    @FXML
    private TextField txtNom_equipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clNom_tournoi.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_tournoi"));
        clName_E1.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_equipe1"));
        clName_E2.setCellValueFactory(new PropertyValueFactory<Match, String>("nom_equipe2"));
        clResultat.setCellValueFactory(new PropertyValueFactory<Match, String>("Res"));

        clEquipe.setCellValueFactory(new PropertyValueFactory<equipe, String>("nom_equipe"));

        clTournoi.setCellValueFactory(new PropertyValueFactory<Tournoi, String>("nom_tournoi"));
        ServiceTournoi service3 = new ServiceTournoi();
        List<Tournoi> tournoi = service3.getAll();
        ObservableList<Tournoi> observabletournoi = FXCollections.observableArrayList(tournoi);
        tableNom_tournoi.setItems(observabletournoi);

        updateTableData();
    }

    public void updateTableData() {
        ServiceMatch service = new ServiceMatch();
        List<Match> match = service.getAll();
        System.out.println(match);
        ObservableList<Match> observableMatch = FXCollections.observableArrayList(match);
        tableDB.setItems(observableMatch);

        ServiceEquipe service2 = new ServiceEquipe();
        List<equipe> eq = service2.getAll();
        ObservableList<equipe> observableequipe = FXCollections.observableArrayList(eq);
        table_listEquipe.setItems(observableequipe);

        ServiceTournoi service3 = new ServiceTournoi();
        List<Tournoi> tournoi = service3.getAll();
        ObservableList<Tournoi> observabletournoi = FXCollections.observableArrayList(tournoi);
        tableNom_tournoi.setItems(observabletournoi);
    }

    @FXML
    private void selection(MouseEvent event) {
        
    }

    @FXML
    private void Chercher(ActionEvent event) {
        ServiceMatch serviceMatch = new ServiceMatch();
        String recherche = txtNom_equipe.getText();

        List<Match> listMatch = serviceMatch.getAll();
        List<Match> matchTrouves = new ArrayList();
        
        for(Match t:listMatch){
            if(t.getNom_equipe1().equals(recherche)){
                matchTrouves.add(t);
            }else if(t.getNom_equipe2().equals(recherche)){
                matchTrouves.add(t);
            }
        }
        System.out.println(matchTrouves);
        if (!matchTrouves.isEmpty()) {
            ObservableList<Match> observableResult = FXCollections.observableArrayList(matchTrouves);
            tableDB.setItems(observableResult);
        } else {
            // Aucun résultat trouvé, affichez un message d'alerte.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Aucun résultat trouvé.");
            alert.showAndWait();
        }
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RechercherMoptions.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Option");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


