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
    private TextField txtcin;

    @FXML
    private TextField txtdescription;
    @FXML
    private Button aj;
    @FXML
    private Button btnGerer;
   
     
   
    @FXML
    private TextField txttyp;

    public  void getCinnn(String cin){

    this.txtcin.setText(cin);
            }

    @FXML
    private void ajoteereclamation(ActionEvent event) {
        
        String cin =txtcin.getText();
        String txtype =txttyp.getText();
        String description =txtdescription.getText();
        if (cin.isEmpty()||txtype.isEmpty()||description.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuiller remplir les champs!");        
        alert.show();
        } 
        else{
        ServiceReclamation service = new ServiceReclamation(); 
        Reclamation rec = new Reclamation(txtype,description,cin);
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
              String cin=txtcin.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Historiqueuser.fxml"));
        Parent root =loader.load();
        HistoriqueuserController dc= loader.getController();
        dc.getCinnn(cin);
        txtcin.getScene().setRoot(root);
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

}