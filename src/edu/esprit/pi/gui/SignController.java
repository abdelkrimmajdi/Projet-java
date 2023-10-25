/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.utilisateur;
import edu.esprit.pi.services.serviceUser;
import edu.esprit.pi.tools.Mailer;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.internet.AddressException;

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

    private void backToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();

            Stage currentStage = (Stage) s.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Login");
            cinField.getScene().getWindow().hide();
            currentStage.show();
        } catch (IOException e) {
            showAlert("Failed to load login.fxm");
            System.out.println("Failed to load login.fxml");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(message);//set message text
        alert.showAndWait();
    }

    private utilisateur user;

    @FXML
    private void signupAction(ActionEvent event) {
        String cin = cinField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();

        String num = numField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        LocalDate date_naissance = dateField.getValue();

        //Check fields
        if (nom == null || nom.trim().isEmpty()) {
            showAlert("Field 'nom' should not contain a null value.");
            return;
        }

        if (!nom.matches("^[a-zA-Z]+$")) {
            showAlert("Field 'nom' should contain only alphabets.");
            return;
        }

        if (nom.length() > 30) {
            showAlert("Field 'nom' should be of 30 characters or less.");
            return;
        }

        if (prenom == null || prenom.trim()
                .isEmpty()) {
            showAlert("Field 'prenom' should not contain a null value.");
            return;
        }

        if (!prenom.matches(
                "^[a-zA-Z]+$")) {
            showAlert("Field 'prenom' should contain only alphabets.");
            return;
        }

        if (prenom.length()
                > 30) {
            showAlert("Field 'prenom' should be of 30 characters or less.");
            return;
        }

        if (cin.trim().isEmpty()) {
            showAlert("Field 'cin'is Empty.");
            return;
        }

        if (!cin.matches("^\\d+$")) {
            showAlert("Field 'cin' should contain only numbers.");
            return;
        }

        if (cin.length() != 8) {
            showAlert("Field 'cin' should be exactly 8 digits.");
            return;
        }

        if (num.trim().isEmpty()) {
            showAlert("Field 'num'is Empty.");
            return;
        }

        if (!num.matches("^\\d+$")) {
            showAlert("Field 'num' should contain only numbers.");
            return;
        }

        if (num.length() != 8) {
            showAlert("Field 'num' should be exactly 8 digits.");
            return;
        }

        int atIndex = email.indexOf('@');
        if (atIndex == -1 || atIndex != email.lastIndexOf('@')) {
            showAlert("Field 'email' should contain '@'.");
            return;
        }

        int dotIndex = email.lastIndexOf('.');
        if (dotIndex < atIndex) {
            showAlert("Field 'email' should contain '.' after '@'.");
            return;
        }

        if (atIndex == 0 || dotIndex == email.length() - 1) {
            showAlert("Field 'email' should a text before '@'.");
            return;
        }

        if (password.trim().isEmpty()) {
            showAlert("Le champ 'password' should not br empty.");
            return;
        }

        boolean containsAlphabets = false;
        boolean containsNumber = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                containsAlphabets = true;
            } else if (Character.isDigit(c)) {
                containsNumber = true;
            }
        }
        if (!containsAlphabets || !containsNumber) {
            showAlert("Le champ 'password' doit contenir des lettres et au moins un chiffre.");
            return;
        }

        boolean containsUppercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUppercase = true;
                break;
            }
        }
        if (!containsUppercase) {
            showAlert("Le champ 'password' doit contenir au moins une lettre majuscule.");
            return;
        }

        if (password.length() > 20) {
            showAlert("Le champ 'password' ne doit pas depasser comporter  8 caractères ou moins.");
            return;
        }

        utilisateur newutilisateur = new utilisateur(Integer.parseInt(cin), prenom, nom, num, email, 0, password, Date.valueOf(date_naissance));

        //tlawej 3ala user bel mail w el pass ken matl9ahech a3mel signup sinon alert +return
        //...
        if (new serviceUser().getUserByEmail(email) == null && new serviceUser().getUserByCin(cin) == null) {
            utilisateur.current_user = newutilisateur;
            new serviceUser().ajouter(newutilisateur);

            try {
                //read welcome.html file
                String html = new String(Files.readAllBytes(Paths.get("src/images/welcome.html")));
                html = html.replace("{{user_email}}", newutilisateur.getMail());
                html = html.replace("{{user_password}}", newutilisateur.getPassword());
                //send welcome email
                new Mailer().sendMailAsync(newutilisateur.getMail(), "Welcome to our website", html);

                Parent root = FXMLLoader.load(getClass().getResource("acceuil.fxml"));

                Stage stage = new Stage();
                stage.setTitle("login");
                stage.setScene(new Scene(root));
                cinField.getScene().getWindow().hide();
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AddressException ex) {
                Logger.getLogger(SignController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            showAlert("email et cin existe deja ");
        }
    }

    @FXML
    private void log(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

            Stage stage = new Stage();
            stage.setTitle("login");
            stage.setScene(new Scene(root));
            cinField.getScene().getWindow().hide();
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
