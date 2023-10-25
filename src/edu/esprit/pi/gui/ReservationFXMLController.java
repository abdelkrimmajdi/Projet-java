/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import edu.esprit.pi.entities.Reservation;
import edu.esprit.pi.services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import static java.sql.JDBCType.DATE;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sadok
 */
public class ReservationFXMLController implements Initializable {

    @FXML
    private AnchorPane ButtonAjouter;
    @FXML
    private Button ButtonReservation;
    @FXML
    private TextField CIN;
    private DatePicker DATE_RES;
    @FXML
    private MenuButton temps;

    private int dataToDisplay;

    private LocalDate dateSelected = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML

    private void Ajouter(MouseEvent event) {
        ServiceReservation Res = new ServiceReservation();

        if (CIN.getText().isEmpty() || DATE_RES.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs");
            a.setHeaderText(null);
            a.showAndWait();
        } else {
            String CINN = CIN.getText();
            LocalDate local = DATE_RES.getValue();
            Date Date_Reservation = Date.valueOf(local);

            // Create an instance of Reservation with valid values for cin and id_terrain
            Reservation R = new Reservation(Integer.parseInt(CINN), local);
            Res.ajouter(R);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Ajouté AVEC SUCCÈS");
            alert.showAndWait();

        }
    }

    @FXML
    private void Reservation(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ListeReservations.fxml"));

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Tout les Terrain");

            currentStage.initModality(Modality.WINDOW_MODAL);

            currentStage.show();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void afficherTemps(ActionEvent event) {

    }

    public void setDataToDisplay(int data) {
        dataToDisplay = data;
    }

    @FXML
    private void aff(MouseEvent event) {
        afficherLesTemps();
    }

    public void afficherLesTemps() {
        temps.getItems().clear();
        List<MenuItem> dateItems = new ArrayList<>();

        ServiceReservation sr = new ServiceReservation();
        List<LocalDate> notAvailableDates = sr.dates(dataToDisplay);

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(10);

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            MenuItem item = new MenuItem(date.toString());
            final LocalDate finalDate = date;
            item.setOnAction((ActionEvent event1) -> {
                dateSelected = finalDate;
            });

            if (!notAvailableDates.contains(date)) {
                dateItems.add(item);
            }

        }

        temps.getItems().addAll(dateItems);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        ServiceReservation sr = new ServiceReservation();
        int cin = Integer.parseInt(CIN.getText());
        if (dateSelected == null) {
            // Show a warning message if no item is selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item!");
            alert.showAndWait();
        } else {
            Reservation r = new Reservation(cin, dateSelected, dataToDisplay);
            sr.ajouter(r);
            afficherLesTemps();
        }

    }

}
