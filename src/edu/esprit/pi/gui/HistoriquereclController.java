/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.Reclamation;
import edu.esprit.pi.entities.Reponse;
import edu.esprit.pi.services.ServiceReclamation;
import edu.esprit.pi.services.ServiceReponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



/**
 * FXML Controller class
 *
 * @author majdiabdelkrim
 */
public class HistoriquereclController implements Initializable {

    @FXML
    private TextField etatad;
    @FXML
    private TextField repdescr;
    @FXML
    private Button reponse;
    @FXML
    private Button supprimerre;
    private TextField idrepon;
    @FXML
    private TableColumn<Reclamation, String> nul;
    @FXML
    private TableColumn<Reclamation, String> eml;
    @FXML   
    private TableColumn<Reclamation, String> desr;
    @FXML
    private TableColumn<Reclamation, String> eta;
    @FXML
    private TableColumn<Reclamation, String> typp;
    @FXML
    private TableColumn<Reclamation, String> datee;
    @FXML
    private TableView<Reclamation> tablerec;
    @FXML
    private TextField idrech;
    @FXML
    private Button gmr;
    @FXML
    private Button rett;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
      nul.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("num"));
       eml.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("email"));
        desr.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("description"));
         typp.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
         eta.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("etat"));
          datee.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("date"));
    ServiceReclamation service = new ServiceReclamation();
    List<Reclamation> reclamations = service.getAll();
    ObservableList<Reclamation> observableReclamations = FXCollections.observableArrayList(reclamations);
    tablerec.setItems(observableReclamations);
    }     
    @FXML
       private void reponserecl(ActionEvent event) {
       String etat = etatad.getText();
        String description = repdescr.getText();
        if(tablerec.getSelectionModel().getSelectedItem()!=null){
            if(etat.isEmpty() || description.isEmpty()){
                Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez rempli les champs ");        
        alert.show();
            }
            else{
         int id=tablerec.getSelectionModel().getSelectedItem().getId_reclamation();
         ServiceReponse service = new ServiceReponse();
        Reponse rec = new Reponse(id, description, etat);
                System.out.println(+id);
        service.ajouter(rec);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation repondue avec succés!");        
        alert.show();
        mise();
            }}
        else{
                 Alert alert = new Alert(AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veiller selectionner une reclamation!");
        alert.show();
            }}
    @FXML
    private void supprimerreclamatio(ActionEvent event) {
int selectedIndex = tablerec.getSelectionModel().getSelectedIndex();
if (selectedIndex >= 0) {
    Reclamation selectedRec = tablerec.getItems().get(selectedIndex);
    int selectedID = selectedRec.getId_reclamation();
    ServiceReclamation Reclamations = new ServiceReclamation();
    Reclamations.supprimer(selectedID);
    tablerec.getItems().remove(selectedIndex);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setContentText("SUPPRIMER AVEC SUCCES");
    alert.showAndWait();
} else {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Aucune sélection");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner une ligne à supprimer.");
    alert.showAndWait();
}}


    private void mise(){
    
      nul.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("num"));
       eml.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("email"));
        desr.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("description"));
         typp.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
         eta.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("etat"));
          datee.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("date"));
    ServiceReclamation service = new ServiceReclamation();
    List<Reclamation> reclamations = service.getAll();
    ObservableList<Reclamation> observableReclamations = FXCollections.observableArrayList(reclamations);
    tablerec.setItems(observableReclamations);
        
    }

    @FXML
    private void Rechercher(ActionEvent event) {
          try {

            OutputStream file = new FileOutputStream(new File("C:\\Users\\aziz\\OneDrive\\Bureau\\pi\\reclamation.pdf"));
         Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            ServiceReclamation sm = new ServiceReclamation();
            Reclamation rec = tablerec.getSelectionModel().getSelectedItem();
            document.add(new Paragraph("************************  Reclamation ************************\n\n\n\n\n\n\n"));
            document.add(new Paragraph(" ___________________________________________________________________________\n"));
             document.add(new Paragraph(" Type :  " + rec.getType() + "  \n"));
            document.add(new Paragraph(" Etat :  " + rec.getEtat() + "  \n"));
            document.add(new Paragraph(" Email :  " + rec.getEmail() + "  \n"));
            document.add(new Paragraph(" Numero :  " + rec.getNum() + "  \n"));
              document.add(new Paragraph(" Description  :  " + rec.getDescription() + "  \n"));
            document.add(new Paragraph(" Date :  " + rec.getDate()+ "  \n"));
            document.add(new Paragraph(" _______________________________________________________________________")); 
            document.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void Tri(ActionEvent event) {
       
    ServiceReclamation sp = new ServiceReclamation();
    List<Reclamation> Liste_rec = sp.trierReclamationsParEtat();
    ObservableList<Reclamation> obs = FXCollections.observableArrayList(Liste_rec);
    
   
    tablerec.setItems(obs);
    }
    @FXML
    private void gerermesreponse(ActionEvent event)  {
                try {
        Parent root = FXMLLoader.load(getClass().getResource("reponseadmin.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Historique des Reponses");
        currentStage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }}
    
private boolean checkUser(Reclamation r, String lowerCaseFilter) {
    // Check if any of the fields contain the filter string
    return String.valueOf(r.getEmail()).toLowerCase().contains(lowerCaseFilter) ||
           String.valueOf(r.getNum()).toLowerCase().contains(lowerCaseFilter) ||
           String.valueOf(r.getDescription()).toLowerCase().contains(lowerCaseFilter) ||
           String.valueOf(r.getEtat()).toLowerCase().contains(lowerCaseFilter) ||
           String.valueOf(r.getType()).toLowerCase().contains(lowerCaseFilter);
}

@FXML
private void selecttt(KeyEvent event) {
    ServiceReclamation serviceReclamation = new ServiceReclamation();
    String lowerCaseFilter = idrech.getText().toLowerCase();

    if (lowerCaseFilter.isEmpty()) {
        mise();
        return;
    }

    List<Reclamation> listReclamations = serviceReclamation.getAll();
    List<Reclamation> filteredList = new ArrayList<>();
    for (Reclamation reclamation : listReclamations) {
        if (checkUser(reclamation, lowerCaseFilter)) {
            filteredList.add(reclamation);
        }
    }

    // Assuming tablerec is a JavaFX TableView
    ObservableList<Reclamation> items = FXCollections.observableArrayList(filteredList);
    tablerec.setItems(items);
}

    @FXML
    private void retour(ActionEvent event) {
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
        
        
        
        
    

