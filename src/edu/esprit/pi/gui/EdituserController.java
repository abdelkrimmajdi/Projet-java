/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.entities.utilisateur;
import edu.esprit.pi.services.serviceUser;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class EdituserController implements Initializable {

    @FXML
    private TextArea cinajout;
    @FXML
    private TextArea nomajout;
    @FXML
    private Button adduser;
    @FXML
    private TextArea prenomajout;
    @FXML
    private TextArea numajout;
    @FXML
    private TextArea emailajout;
    @FXML
    private TextArea passwordajout;
    @FXML
    private DatePicker dateajout;
    @FXML
    private CheckBox isAdmin;
    private int role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilisateur user = utilisateur.edit_user;
        cinajout.setText(Integer.toString(user.getCin()));
        nomajout.setText(user.getNom());
        prenomajout.setText(user.getPrenom());
        numajout.setText(user.getNum());
        emailajout.setText(user.getMail());
        passwordajout.setText(user.getPassword());
        dateajout.setValue(user.getDate_naissance().toLocalDate());
        if (user.getRole() == 1) {
            isAdmin.setSelected(true);
        } else {
            isAdmin.setSelected(false);
        }
    }

    @FXML
    private void adduserAction(ActionEvent event) {
        String cin = cinajout.getText();
        String nom = nomajout.getText();
        String prenom = prenomajout.getText();

        String num = numajout.getText();
        String email = emailajout.getText();
        String password = passwordajout.getText();

        LocalDate date_naissance = dateajout.getValue();

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
        
        if(isAdmin.isSelected()){
            role = 1;
        }else{
            role = 0;
        }
        
        //Check if the new Email or CIN is used
        utilisateur user1, user2;
        user1 = new serviceUser().getUserByEmail(email);
        user2 = new serviceUser().getUserByCin(cin);

        if ((user1 == null || user1.getId_user() == utilisateur.edit_user.getId_user()) && (user2 == null || user2.getCin() == utilisateur.edit_user.getCin())) {
            utilisateur updatedUser = new utilisateur(Integer.parseInt(cin), prenom, nom, num, email, role, password, Date.valueOf(date_naissance));
            updatedUser.setId_user(utilisateur.edit_user.getId_user());
            new serviceUser().modifier(updatedUser);
            cinajout.getScene().getWindow().hide();
            return;
        } else {
            showAlert("Email or CIN exist");
        }

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
