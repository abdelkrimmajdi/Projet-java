package edu.esprit.pi.services;

import edu.esprit.pi.entities.utilisateur;
import edu.esprit.pi.tools.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class serviceUser implements IService<utilisateur> {

    Connection cnx;

    public serviceUser() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(utilisateur t) {
        try {
            String user = "INSERT INTO utilisateur(cin, nom, prenom, date_naissance, num, role, email, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(user);
            preparedStatement.setInt(1, t.getCin());
            preparedStatement.setString(2, t.getNom());
            preparedStatement.setString(3, t.getPrenom());
            preparedStatement.setDate(4, t.getDate_naissance());
            preparedStatement.setString(5, t.getNum());
            preparedStatement.setInt(6, t.getRole());
            preparedStatement.setString(7, t.getMail());
            preparedStatement.setString(8, t.getPassword());

            preparedStatement.executeUpdate();
            System.out.println("Données ajoutées avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(utilisateur t) {
        try {
            String updateQuery = "UPDATE utilisateur SET cin=?, prenom=?, nom=?, num=?, email=?, password=?, role=?, date_naissance=? WHERE id_user=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(updateQuery);

            preparedStatement.setInt(1, t.getCin());
            preparedStatement.setString(2, t.getPrenom());
            preparedStatement.setString(3, t.getNom());
            preparedStatement.setString(4, t.getNum());
            preparedStatement.setString(5, t.getMail());
            preparedStatement.setString(6, t.getPassword());
            preparedStatement.setInt(7, t.getRole());
            preparedStatement.setDate(8, t.getDate_naissance());
            preparedStatement.setInt(9, t.getId_user());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Donnée modifiée avec succès !");
            } else {
                System.out.println("Aucune donnée n'a été modifiée.");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification des données : " + ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String user = "DELETE FROM utilisateur WHERE id_user=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(user);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Utilisateur supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression des données : " + ex.getMessage());
        }
    }

    // Les autres méthodes restent inchangées
    // ...
}