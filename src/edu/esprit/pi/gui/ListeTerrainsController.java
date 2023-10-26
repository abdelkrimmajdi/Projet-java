/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Terrain;
import edu.esprit.pi.services.ServiceTerrain;
import java.io.IOException;
import java.net.URL;
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
public class ListeTerrainsController implements Initializable {

    @FXML
    private AnchorPane tableViewTerrain;
    @FXML
    private TableColumn<Terrain,Integer> id;
    @FXML
    private TableColumn<Terrain, Integer> prix;
    @FXML
    private TableColumn<Terrain,String> adresse;
    @FXML
    private TableView<Terrain> terrainTable;
    @FXML
    private Button buttonSUPPRIMER;
    @FXML
    private Button homeliteterrain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }    
    
    public void afficher(){
       
        ServiceTerrain service=new ServiceTerrain();
        ObservableList<Terrain> list = service.getAll();
        list.clear();
         id.setCellValueFactory(new PropertyValueFactory<Terrain,Integer>("id_terrain"));
         prix.setCellValueFactory(new PropertyValueFactory<Terrain, Integer>("prix"));
         adresse.setCellValueFactory(new PropertyValueFactory<Terrain,String>("adress"));
         terrainTable.setItems(list);
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        Terrain t = terrainTable.getSelectionModel().getSelectedItem();
        ServiceTerrain st = new ServiceTerrain();
        st.supprimer(t.getId_terrain());
        afficher();
    }

    @FXML
    private void reserver(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationFXML.fxml"));
        Parent root = loader.load();
        ReservationFXMLController reservationController = loader.getController();
        int id = terrainTable.getSelectionModel().getSelectedItem().getId_terrain();
        reservationController.setDataToDisplay(id);


        Stage currentStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Tout les Terrain");

        currentStage.initModality(Modality.WINDOW_MODAL);


        currentStage.show();

    } catch (IOException e) {
            System.err.println(e.getMessage());
    }
    }

    @FXML
    private void HOMEliste(ActionEvent event) {
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
