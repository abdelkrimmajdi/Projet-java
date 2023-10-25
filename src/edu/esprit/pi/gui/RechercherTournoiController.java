package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Tournoi;
import edu.esprit.pi.services.ServiceTournoi;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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

public class RechercherTournoiController implements Initializable {

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
    private TableColumn<Tournoi, String> clNom_tournoi;

    @FXML
    private TextField txtnom_tournoi;

    private final ServiceTournoi service = new ServiceTournoi();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clNom_tournoi.setCellValueFactory(new PropertyValueFactory<>("nom_tournoi"));
        clListEquipes.setCellValueFactory(new PropertyValueFactory<>("equipes"));
        clAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        clNbr_Equipes.setCellValueFactory(new PropertyValueFactory<>("nbr_equipe"));
        clDates.setCellValueFactory(new PropertyValueFactory<>("date_tournoi"));

        updateTableData();
    }

    public void updateTableData() {
        List<Tournoi> tournois = service.getAll();
        ObservableList<Tournoi> observableTournoi = FXCollections.observableArrayList(tournois);
        tableDB1.setItems(observableTournoi);
    }

    @FXML
    private void selection(MouseEvent event) {
        Tournoi clickedTournoi = tableDB1.getSelectionModel().getSelectedItem();
        if (clickedTournoi != null) {
            txtnom_tournoi.setText(clickedTournoi.getNom_tournoi());

        }
    }

    @FXML
    private void Chercher(ActionEvent event) {
        ServiceTournoi serviceTournoi = new ServiceTournoi();
        String recherche = txtnom_tournoi.getText();

        List<Tournoi> listTournoi = serviceTournoi.getAll();
        List<Tournoi> tournoisTrouves = new ArrayList();
        
        for(Tournoi t:listTournoi){
            if(t.getNom_tournoi().equals(recherche)){
                tournoisTrouves.add(t);
            }
        }

        if (!tournoisTrouves.isEmpty()) {
            ObservableList<Tournoi> observableResult = FXCollections.observableArrayList(tournoisTrouves);
            tableDB1.setItems(observableResult);
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
            Parent root = FXMLLoader.load(getClass().getResource("RechercherOption.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Option");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
