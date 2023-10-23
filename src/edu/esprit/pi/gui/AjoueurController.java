/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.esprit.pi.entities.equipe;

/**
 * FXML Controller class
 *
 * @author benou
 */
public class AjoueurController implements Initializable {

    @FXML
    private TextField eqnom;
    @FXML
    private ComboBox<String> eqclm;
    @FXML
    private Spinner<Integer> eqnb;
    @FXML
    private TableView<equipe> equipees;
    @FXML
    private TableColumn<equipe, Integer> ideqqq;
    @FXML
    private TableColumn<equipe, Integer> nbj;
    @FXML
    private TableColumn<equipe, String> coulrm;
    @FXML
    private TableColumn<equipe, String> noeq;
   
    
       ObservableList<String> list = FXCollections.observableArrayList("","blanc","bleu","rouge et blanc","vert","gris");
                ServiceEquipe service = new ServiceEquipe();
        List<equipe> equipes = service.getAll();
            ObservableList<equipe> obbequipe =FXCollections.observableArrayList(equipes);


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
ideqqq.setCellValueFactory(new PropertyValueFactory<equipe,Integer>("id_equipe"));
                noeq.setCellValueFactory(new PropertyValueFactory<equipe,String>("nom_equipe"));
                        nbj.setCellValueFactory(new PropertyValueFactory<equipe,Integer>("nbjoueur_equipe"));
                            coulrm.setCellValueFactory(new PropertyValueFactory<equipe,String>("couleurmaillot"));
SpinnerValueFactory<Integer> v = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,10);
                equipees.setItems(obbequipe);
                eqclm.setItems(list); 
   v.setValue(1);
  eqnb.setValueFactory(v);
    }    

    @FXML
    private void eqajouterr(ActionEvent event) {
        ServiceEquipe q = new ServiceEquipe();
                 if (eqnom.getText().equals("") || eqclm.getValue().equals("") ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
           
        }
                 else {  String nom_equipe = eqnom.getText();
        int nbm = eqnb.getValue();
        String clmaillot = eqclm.getValue();
                        equipe e = new equipe(nom_equipe, nbm, clmaillot);
              q.ajouter(e);
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("AJOUT AVEC SUCCES");
            alert.showAndWait();}
}

    

    @FXML
    private void eqajour(ActionEvent event) {
        obbequipe.clear();

ideqqq.setCellValueFactory(new PropertyValueFactory<>("id_equipe"));
noeq.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
nbj.setCellValueFactory(new PropertyValueFactory<>("nbjoueur_equipe"));
coulrm.setCellValueFactory(new PropertyValueFactory<>("couleurmaillot"));

ServiceEquipe service = new ServiceEquipe();

List<equipe> equipes = service.getAll();
obbequipe.addAll(equipes);

equipees.setItems(obbequipe);

}
    
}
