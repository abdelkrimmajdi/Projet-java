/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.utilisateur;
import edu.esprit.pi.services.serviceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class SignController implements Initializable {

    @FXML
    private ImageView imglogo;
    @FXML
    private AnchorPane s;
    @FXML
    private TextArea cinField;
    @FXML
    private TextArea nomField;
    @FXML
    private Button signupBtn;
    @FXML
    private TextArea prenomField;
    @FXML
    private TextArea numField;
    @FXML
    private TextArea emailField;
    @FXML
    private TextArea passwordField;
    @FXML
    private Text loginBtn;
    @FXML
    private DatePicker dateField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void signUp(ActionEvent event) {
        int cin = Integer.parseInt(cinField.getText());
        String nom = nomField.getText();
        String prenom = prenomField.getText();

        String num = numField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date_naissance = Date.valueOf(dateField.getValue());
        
        //Check fields
        //....
        
        
        

        utilisateur newutilisateur = new utilisateur(cin, prenom, nom, num, email,0, password,date_naissance);

        new serviceUser().ajouter(newutilisateur);
        
        //Tlawej 3al id mta3 el user bech t7outha static
        //...
        utilisateur.setCurrent_user(newutilisateur);
        
        //Continue to Home screen
        
        
                
    }

    private void backToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();

            Stage currentStage = (Stage) s.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Login");
            currentStage.show();
        } catch (IOException e) {
            showAlert("Failed to load login.fxml", Alert.AlertType.ERROR);
            System.out.println("Failed to load login.fxml");
        }
    }

    private void showAlert(String failed_to_load_loginfxml, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText("Error");//set message text
        alert.showAndWait();
    }
}
