/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Reservation;
import edu.esprit.pi.services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sadok
 */
public class ListeReservationsController implements Initializable {

    @FXML
    private TableView<Reservation> tableReservation;
    @FXML
    private TableColumn<?, ?> idres;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> idTer;
    @FXML
    private TableColumn<?, ?> cin;
    @FXML
    private Button HoTerrain;
    @FXML
    private Button hb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }    
    
    public void afficher(){
        ServiceReservation service=new ServiceReservation();
        ObservableList<Reservation> list = service.getAll();
         idres.setCellValueFactory(new PropertyValueFactory<>("id_res"));
         date.setCellValueFactory(new PropertyValueFactory<>("Date_reservation"));
         idTer.setCellValueFactory(new PropertyValueFactory<>("id_terrain"));
         cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
         
      
         
         tableReservation.setItems(list);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Reservation r = tableReservation.getSelectionModel().getSelectedItem();
        ServiceReservation sr = new ServiceReservation();
        sr.supprimer(r.getId_res());
        afficher();
    }

    @FXML
    private void Hterrain(ActionEvent event) {
        
        
    }

    @FXML
    private void Homeb(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));

        Stage currentStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Tout les Terrain");

        currentStage.initModality(Modality.WINDOW_MODAL);


        currentStage.show();

    } catch (IOException e) {
            System.err.println(e.getMessage());
    }
    }
    
}
