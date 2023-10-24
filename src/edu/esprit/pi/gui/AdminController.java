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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class AdminController implements Initializable {

    @FXML
    private TableColumn<utilisateur, Integer> cin;
    @FXML
    private TableColumn<utilisateur, Integer> role;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableColumn<utilisateur, String> nom;
    @FXML
    private TableColumn<utilisateur, String> prenom;
    @FXML
    private TableColumn<utilisateur, String> num;
    @FXML
    private TableColumn<utilisateur, Date> datenaissance;
    @FXML
    private TableColumn<utilisateur, String> email;
    @FXML
    private TableColumn<utilisateur, String> password;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     *
     *
     */
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    utilisateur user = null;
    @FXML
    private TableView<utilisateur> usertable;
    @FXML
    private TextField chercher;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        datenaissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        email.setCellValueFactory(new PropertyValueFactory<>("mail"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        refreshTable();

    }

    @FXML
    private void modificationAction(ActionEvent event) {
        utilisateur selected = usertable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("select a user from the table ");
            alert.showAndWait();
            return;
        } else {
            utilisateur.edit_user = selected;
            try {
                Parent root = FXMLLoader.load(getClass().getResource("edituser.fxml"));

                Stage stage = new Stage();
                stage.setTitle("Edit User");
                stage.setScene(new Scene(root));

                stage.showAndWait();
                utilisateur.edit_user = null;
                refreshTable();

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void suppressionAction(ActionEvent event) {

        utilisateur selected = usertable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("select a user from the table ");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("confirmation dialog");
            alert.setHeaderText("delete user");
            alert.setContentText("are you sure ?");
            alert.showAndWait();
            if (alert.getResult().getText().equals("OK")) {
                new serviceUser().supprimer(selected.getId_user());
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("success");
                alert.setContentText("user deleted ");
                alert.showAndWait();
                refreshTable();

            }

        }

    }

    private void refreshTable() {
        usertable.getItems().clear();
        List<utilisateur> listuser = new serviceUser().getAll();
        for (utilisateur u : listuser) {
            usertable.getItems().add(u);
        }

    }

    private boolean checkUser(utilisateur user, String lowerCaseFilter) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Use the format method to convert the Date to a String
        String dateStr = dateFormat.format(user.getDate_naissance());
        if (String.valueOf(user.getCin()).toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (String.valueOf(user.getRole()).toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (user.getNom().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (user.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (user.getNum().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (dateStr.contains(lowerCaseFilter)) {
            return true;
        } else if (user.getMail().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (user.getPassword().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void AjouterAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("adduser.fxml"));

            Stage stage = new Stage();
            stage.setTitle("adduser");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchField(KeyEvent event) {
        String lowerCaseFilter = chercher.getText().toLowerCase();
        if (lowerCaseFilter.isEmpty()) {
            refreshTable();
            return;
        }
        List<utilisateur> listU = new serviceUser().getAll();
        List<utilisateur> filteredList = new ArrayList<utilisateur>();

        for (utilisateur user : listU) {
            if (this.checkUser(user, lowerCaseFilter)) {
                filteredList.add(user);
            }
        }
        
        usertable.getItems().clear();
        for (utilisateur u : filteredList) {
            usertable.getItems().add(u);
        }
    }

}
