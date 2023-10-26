/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.pi.entities.Reclamation;
import edu.esprit.pi.services.ServiceReclamation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author majdiabdelkrim
 */
public class HistoriqueuserController implements Initializable {

    @FXML
    private TextField textdehra;
    @FXML
    private TextField txttyhra;
    @FXML
    private TableView<Reclamation  > tableuserrec;
    @FXML
    private TableColumn<Reclamation,String> numhra;
    @FXML
    private TableColumn<Reclamation,String> emailhra;
    @FXML
    private TableColumn<Reclamation,String> etathru;
    @FXML
    private TableColumn<Reclamation,String> hra;
    @FXML
    private TableColumn<Reclamation,String> deschra;
    @FXML
    private TableColumn<Reclamation,String> datehru;
    @FXML
    private TextField txtidhra;

    int index = -1;
    @FXML
    private AnchorPane iddd;

    /**
     * Initializes the controller class.
     */
    


    public void initialize(URL url, ResourceBundle rb) {

       numhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("num"));
      emailhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("email"));
     deschra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("description")); 
     etathru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("etat"));
     hra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
     datehru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("date"));
    ServiceReclamation service = new ServiceReclamation();
    List<Reclamation>  rec = service.afficher();   
     
        ObservableList<Reclamation> observableReclamation = FXCollections.observableArrayList(rec);
        tableuserrec.setItems(observableReclamation); 
   }   
    
    @FXML  
    private void Select(javafx.scene.input.MouseEvent event) {
         index = tableuserrec.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return ;
        }
        txttyhra.setText(hra.getCellData(index).toString());
       textdehra.setText(deschra.getCellData(index).toString());   
   
    }

    @FXML
    
         private void supphra(ActionEvent event) {
    ServiceReclamation service = new ServiceReclamation();
    Reclamation selectedReclamation = tableuserrec.getSelectionModel().getSelectedItem();
    int selectedIndex = tableuserrec.getSelectionModel().getSelectedIndex();

    if (selectedReclamation != null) {
        int selectedID = selectedReclamation.getId_reclamation();
        System.out.println(selectedID);

        service.supprimer(selectedID); // You don't need to create a new ServiceReclamation instance

        tableuserrec.getItems().remove(selectedIndex);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("SUPPRIMÉ AVEC SUCCÈS");
        alert.showAndWait();
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune sélection");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une ligne à supprimer.");
        alert.showAndWait();
    }
}

    @FXML
    private void modihra(ActionEvent event) {         
            ServiceReclamation service =new ServiceReclamation();
        Reclamation selectedReclamation = tableuserrec.getSelectionModel().getSelectedItem();
    if (selectedReclamation != null) {
         selectedReclamation.setDescription(textdehra.getText());
        selectedReclamation.setType(txttyhra.getText());   
        if(txttyhra.getText().isEmpty()||textdehra.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir les champs!");        
        alert.show();
        }
        else{
             service.modifier(selectedReclamation);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation modifier avec succés!");        
        alert.show();
            tableuserrec.refresh();
                    }
    }
        else{
             Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuilez selectionnée!");        
        alert.show();   
               
    }
    }

    private void Rechercher(ActionEvent event) {
   
       numhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("num"));
      emailhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("email"));
     deschra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("description")); 
     etathru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("etat"));
     hra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
     datehru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("date"));
    
     if(!txtidhra.getText().isEmpty()){
          int idReclamation = Integer.parseInt(txtidhra.getText());
    ServiceReclamation service = new ServiceReclamation();
    Reclamation  rec = service.getOne(idReclamation);
    ObservableList<Reclamation> observableReclamation = FXCollections.observableArrayList(rec);
    tableuserrec.setItems(observableReclamation);
    }
     else{
         Alert alert = new Alert(Alert.AlertType.INFORMATION.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir  champs de id!");        
        alert.show();         
    }
    }
    

    @FXML
    private void Tri(ActionEvent event) {
         ServiceReclamation sp = new ServiceReclamation();
    List<Reclamation> Liste_rec = sp.trierReclamationsParEtat();
    ObservableList<Reclamation> obs = FXCollections.observableArrayList(Liste_rec);
    tableuserrec.setItems(obs);

    }

    @FXML
    private void Reponseuser(ActionEvent event) {  
         try {
        Parent root = FXMLLoader.load(getClass().getResource("HistoriquerepUser.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Historique des Repo");
        currentStage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }                   
    }
    public void msaj(){
        numhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("num"));
      emailhra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("email"));
     deschra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("description")); 
     etathru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("etat"));
     hra.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("type"));
     datehru.setCellValueFactory(new PropertyValueFactory <Reclamation,String>("date")); 
    ServiceReclamation service = new ServiceReclamation();
    List<Reclamation>  rec = service.afficher();   
    ObservableList<Reclamation> observableReclamation = FXCollections.observableArrayList(rec);
    tableuserrec.setItems(observableReclamation);
     }
    @FXML
    private void Retour(ActionEvent event) {
         try {
        Parent root = FXMLLoader.load(getClass().getResource("Reclamationaj.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Historique des Reponses");
        currentStage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }         
    }
    
    @FXML
    private void Pdff(ActionEvent event) {
                 try {
            OutputStream file = new FileOutputStream(new File("C:\\Users\\aziz\\OneDrive\\Bureau\\pi\\reclamationuser.pdf"));
         Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            ServiceReclamation sm = new ServiceReclamation();
            Reclamation rec = tableuserrec.getSelectionModel().getSelectedItem();
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
      
   private boolean checkUser(Reclamation r, String lowerCaseFilter) {
    // Check if any of the fields contain the filter string
    return String.valueOf(r.getEmail()).toLowerCase().contains(lowerCaseFilter) ||
           String.valueOf(r.getNum()).toLowerCase().contains(lowerCaseFilter) ||
           String.valueOf(r.getDescription()).toLowerCase().contains(lowerCaseFilter) ||
           String.valueOf(r.getEtat()).toLowerCase().contains(lowerCaseFilter) ||
           String.valueOf(r.getType()).toLowerCase().contains(lowerCaseFilter);}


    @FXML
    private void Selectttee(KeyEvent event) { 
 ServiceReclamation serviceReclamation = new ServiceReclamation();
    String lowerCaseFilter = txtidhra.getText().toLowerCase();

    if (lowerCaseFilter.isEmpty()) {
        msaj();
        return;
    }

    List<Reclamation> listReclamations = serviceReclamation.getAll();
    List<Reclamation> filteredList = new ArrayList<>();
    for (Reclamation reclamation : listReclamations) {
        if (this.checkUser(reclamation, lowerCaseFilter)) {
            filteredList.add(reclamation);
        }
    }

    // Assuming tablerec is a JavaFX TableView
    ObservableList<Reclamation> items = FXCollections.observableArrayList(filteredList);
    tableuserrec.setItems(items);
    }

    }
    

