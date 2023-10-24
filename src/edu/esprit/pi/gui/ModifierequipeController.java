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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tn.edu.esprit.entities.equipe;
import tn.edu.esprit.entities.joueur;
import tn.edu.esprit.services.ServiceEquipe;
import tn.edu.esprit.services.ServiceJoueur;

/**
 * FXML Controller class
 *
 * @author benou
 */
public class ModifierequipeController implements Initializable {

    @FXML
    private Text ADD;
    @FXML
    private TextField nom_equipe;
    @FXML
    private Spinner<Integer> Spnbjoueur;
    @FXML
    private TableView<equipe> equipess;
    @FXML
    private TableColumn<equipe, String> nomeqq;
    @FXML
    private TableColumn<equipe, Integer> nbjoueur;
    @FXML
    private TableColumn<equipe, String> couleurmaillot;
    @FXML
    private ComboBox<String> clbox;
    @FXML
    private Button maj_button;
    @FXML
    private Button modifier_button;

    /**
     * Initializes the controller class.
     */
    
                ObservableList<String> list = FXCollections.observableArrayList("","blanc","bleu","rouge et blanc");
                ServiceEquipe service = new ServiceEquipe();
        List<equipe> equipes = service.getAll();
            ObservableList<equipe> obequipe =FXCollections.observableArrayList(equipes);
            

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                nomeqq.setCellValueFactory(new PropertyValueFactory<equipe,String>("nom_equipe"));
                        nbjoueur.setCellValueFactory(new PropertyValueFactory<equipe,Integer>("nbjoueur_equipe"));
                            couleurmaillot.setCellValueFactory(new PropertyValueFactory<equipe,String>("couleurmaillot"));
SpinnerValueFactory<Integer> v = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,10);
                equipess.setItems(obequipe);
                clbox.setItems(list); 
   v.setValue(1);
   Spnbjoueur.setValueFactory(v);
    }    

   

    @FXML
    private void modifier(ActionEvent event) {
                ServiceEquipe eq = new ServiceEquipe();
   equipe selectedequipe = equipess.getSelectionModel().getSelectedItem();
if (selectedequipe != null ) {
    selectedequipe.setNom_equipe(nom_equipe.getText());
    selectedequipe.setNbjoueur_equipe(Spnbjoueur.getValue());
    selectedequipe.setCouleurmaillot(clbox.getValue());
    eq.modifier(selectedequipe);
    equipess.getItems().remove(selectedequipe);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("MODIFIER AVEC SUCCES");
            alert.showAndWait();}
 else {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Aucune sélection");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner une ligne à modifier.");
    alert.showAndWait();
}
    }
          
      @FXML
 private void mettre_a_jour(ActionEvent event) {


obequipe.clear();

nomeqq.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
nbjoueur.setCellValueFactory(new PropertyValueFactory<>("nbjoueur_equipe"));
couleurmaillot.setCellValueFactory(new PropertyValueFactory<>("couleurmaillot"));

ServiceEquipe service = new ServiceEquipe();

List<equipe> equipes = service.getAll();

obequipe.addAll(equipes);

equipess.setItems(obequipe);

}
    @FXML
    private void equipeclick(MouseEvent event) {
       equipe clickedequipe = equipess.getSelectionModel().getSelectedItem();
       nom_equipe.setText(String.valueOf(clickedequipe.getNom_equipe()));
       Spnbjoueur.getValueFactory().setValue(clickedequipe.getNbjoueur_equipe());
       clbox.setValue(clickedequipe.getCouleurmaillot());
    }    
}
