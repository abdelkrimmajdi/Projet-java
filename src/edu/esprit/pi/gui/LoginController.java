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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class LoginController implements Initializable {

    @FXML
    private ImageView imglogo;
    @FXML
    private TextArea email;
    @FXML
    private TextArea password;
    @FXML
    private Button login;
    @FXML
    private Button signup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void togglePasswordVisibility() {
        boolean isPasswordVisible = false;
        if (isPasswordVisible) {
            password.setVisible(true);
            password.setManaged(true);
            password.setVisible(false);
            password.setManaged(false);
            isPasswordVisible = false;

        } else {
            password.setVisible(false);
            password.setManaged(false);
            password.setVisible(true);
            password.setManaged(true);
            isPasswordVisible = true;

        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void forget(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/forgetpw.fxml"));
            Parent root = loader.load();
            ForgetPW ForgetPW = loader.getController();

            Stage currentStage = (Stage) root.getScene().getWindow();

            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.setTitle("forget");
            currentStage.show();
        } catch (IOException e) {
            showAlert("Failed to load forgetpw.fxml");
        }
    }

    @FXML
    private void loginform(ActionEvent event) throws IOException {

        String userEmail = email.getText();
        String userPassword = password.getText();

        if (userEmail.trim().isEmpty()) {
            showAlert("Field 'email' is Empty.");
            return;
        }

        if (userPassword.trim().isEmpty()) {
            showAlert("Field 'email' is Empty.");
            return;
        }

        utilisateur u = new serviceUser().getUserByEmail(userEmail);
        if (u != null) {
            if (u.getPassword().equals(userPassword)) {
                utilisateur.setCurrent_user(u);
                if (utilisateur.current_user.getRole() == 1) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("sign Up");
                        stage.setScene(new Scene(root));

                        stage.show();

                        login.getScene().getWindow().hide();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("sign Up");
                        stage.setScene(new Scene(root));

                        stage.show();

                        login.getScene().getWindow().hide();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                showAlert("Error Failed Check Email");
            }

        }
    }

    @FXML
    private void signupform(ActionEvent event
    ) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sign.fxml"));

            Stage stage = new Stage();
            stage.setTitle("sign Up");
            stage.setScene(new Scene(root));

            stage.show();

            login.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
