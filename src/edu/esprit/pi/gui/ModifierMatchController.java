/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Match;
import edu.esprit.pi.services.ServiceMatch;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML
    private TextField txtId_match;
    ObservableList<String> list= FXCollections.observableArrayList("1","2");
    @FXML
    private TableView<Match> tableDB;
    @FXML
    private TableColumn<Match, Integer> clID_match;
    @FXML
    private TableColumn<Match, Integer> clID_equipe1;
    @FXML
    private TableColumn<Match, Integer> clID_equipe2;
    @FXML
    private TableColumn<Match, String> clName_E1;
    @FXML
    private TableColumn<Match, String> clResultat;
    @FXML
    private TableColumn<Match, String> clName_E2;
    @FXML
    private TextField txtnom_tournoi;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clID_match.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_match"));
        clID_equipe1.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_equipe1"));
        clID_equipe2.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_equipe2"));
        clName_E1.setCellValueFactory(new PropertyValueFactory<Match,String>("nom_equipe1"));
        clName_E2.setCellValueFactory(new PropertyValueFactory<Match,String>("nom_equipe2"));
        clResultat.setCellValueFactory(new PropertyValueFactory<Match,String>("Res"));
        cbRes.setItems(list);
        ServiceMatch service = new ServiceMatch();
        List<Match> match = service.getAll();
        ObservableList<Match> observableMatch = FXCollections.observableArrayList(match);
        tableDB.setItems(observableMatch);
    }    
    
    public void update(){
        clID_match.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_match"));
        clID_equipe1.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_equipe1"));
        clID_equipe2.setCellValueFactory(new PropertyValueFactory<Match,Integer>("id_equipe2"));
        clName_E1.setCellValueFactory(new PropertyValueFactory<Match,String>("nom_equipe1"));
        clName_E2.setCellValueFactory(new PropertyValueFactory<Match,String>("nom_equipe2"));
        clResultat.setCellValueFactory(new PropertyValueFactory<Match,String>("Res"));
        cbRes.setItems(list);
        ServiceMatch service = new ServiceMatch();
        List<Match> match = service.getAll();
        ObservableList<Match> observableMatch = FXCollections.observableArrayList(match);
        tableDB.setItems(observableMatch);
    }

    @FXML
    private void modifier(ActionEvent event) {
            ServiceMatch mac = new ServiceMatch();
            if (TxtNom_equipe2.getText().isEmpty() ||txtnom_tournoi.getText().isEmpty() || cbRes.getValue().isEmpty() || txtNom_equipe1.getText().isEmpty() || txtId_match.getText().isEmpty() ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
            else {
                String nom_equipe1=txtNom_equipe1.getText();
                String nom_equipe2=TxtNom_equipe2.getText();
                String res=cbRes.getValue();
                String nom_tournoi=TxtNom_equipe2.getText();
                int id_match= Integer.parseInt(txtId_match.getText());
                Match e = new Match(id_match,nom_tournoi,nom_equipe1,nom_equipe2,res);
                mac.modifier(e);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Modifier AVEC SUCCES");
                alert.showAndWait();
                update();
                tableDB.refresh();
    }}
        
}
