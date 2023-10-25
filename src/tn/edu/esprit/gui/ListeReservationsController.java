/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class ListeReservationsController implements Initializable {

    @FXML
    private TableView<?> tableReservation;
    @FXML
    private TableColumn<?, ?> idres;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> cin;
    @FXML
    private TableColumn<?, ?> idTer;
    @FXML
    private Button HoTerrain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void Hterrain(ActionEvent event) {
    }
    
}
