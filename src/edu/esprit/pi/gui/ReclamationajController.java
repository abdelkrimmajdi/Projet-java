package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Reclamation;
import edu.esprit.pi.services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReclamationajController {


    @FXML
    private TextField txtdescription;
    @FXML
    private Button aj;
    @FXML
    private Button btnGerer;
   
     
   
    @FXML
    private TextField txttyp;
    @FXML
    private Button ret;




    @FXML
    private void ajoteereclamation(ActionEvent event) {
        
       
        String txtype =txttyp.getText();
        String description =txtdescription.getText();
        if (txtype.isEmpty()||description.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuiller remplir les champs!");        
        alert.show();
        } 
        else{
        ServiceReclamation service = new ServiceReclamation(); 
        Reclamation rec = new Reclamation(txtype,description);
        service.ajouter(rec);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation Ajoutée avec succés!");        
        alert.show();} 
    }

    @FXML
    private void Gererrecl(ActionEvent event) {
         try {
     Parent root = FXMLLoader.load(getClass().getResource("Historiqueuser.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("Historique des reclamation");
                        stage.setScene(new Scene(root));
                        stage.show();  
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }       
    
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
      
      
    } 

    @FXML
    private void ret(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Acceuil");
        currentStage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

}