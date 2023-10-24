/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import java.util.List;
import edu.esprit.pi.entities.utilisateur;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import edu.esprit.pi.tools.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

;

/**
 *
 * @author aziz
 */
public class serviceUser implements IService<utilisateur> {

    Connection cnx;

    public serviceUser() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(utilisateur t) {
        try {

            String user = "INSERT INTO `utilisateur`(cin,nom,prenom,date_naissance,num,role,email,password) VALUES ('" + t.getCin() + "','" + t.getNom() + "','" + t.getPrenom() + "','" + t.getDate_naissance() + "','" + t.getNum() + "','" + t.getRole() + "','" + t.getMail() + "','" + t.getPassword() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(user);
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

            preparedStatement.setString(1, Integer.toString(t.getCin()));
            preparedStatement.setString(2, t.getPrenom());
            preparedStatement.setString(3, t.getNom());
            preparedStatement.setString(4, t.getNum());
            preparedStatement.setString(5, t.getMail());
            preparedStatement.setString(6, t.getPassword());
            preparedStatement.setString(7, Integer.toString(t.getRole()));
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
            String user = "DELETE from  utilisateur  WHERE id_user =" + id;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(user);
            System.out.println("utilisateur supprimer  avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur "
                    + "lors de la suppression des données : " + ex.getMessage());
        }
    }

    @Override
    public utilisateur getOne(int id) {
        try {
            String user = "SELECT * FROM utilisateur WHERE id_user = " + id;
            Statement stm = cnx.createStatement();
            ResultSet us = stm.executeQuery(user);

            if (us.next()) {
                utilisateur u = new utilisateur();
                u.setId_user(us.getInt("id_user"));
                u.setCin(us.getInt("cin"));
                u.setNom(us.getString("nom"));
                u.setPrenom(us.getString("prenom"));
                u.setDate_naissance(us.getDate("date_naissance"));
                u.setNum(us.getString("num"));
                u.setRole(us.getInt("role"));
                u.setMail(us.getString("email"));
                u.setPassword(us.getString("password"));

                return u;
            }
        } catch (SQLException ex) {
            System.out.println("Erreur  : " + ex.getMessage());
        }

        return null; //
    }

    @Override
    public List<utilisateur> getAll() {
        String user = "SELECT * FROM utilisateur";
        ArrayList<utilisateur> users = new ArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();

            ResultSet us = stm.executeQuery(user);
            while (us.next()) {
                utilisateur u = new utilisateur();
                u.setId_user(us.getInt(1));
                u.setCin(us.getInt(2));
                u.setNom(us.getString(3));
                u.setPrenom(us.getString(4));
                u.setNum(us.getString(6));
                u.setDate_naissance(us.getDate(5));
                u.setRole(us.getInt(7));
                u.setMail(us.getString(8));
                u.setPassword(us.getString(9));

                users.add(u);
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
        return users;
    }

    //Search userByEmail
    public utilisateur getUserByEmail(String email) {
        try {

            String selectQuery = "SELECT * FROM utilisateur WHERE email = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);

            ResultSet us = preparedStatement.executeQuery();

            if (us.next()) {
                utilisateur u = new utilisateur();
                u.setId_user(us.getInt("id_user"));
                u.setCin(us.getInt("cin"));
                u.setNom(us.getString("nom"));
                u.setPrenom(us.getString("prenom"));
                u.setNum(us.getString("num"));
                u.setDate_naissance(us.getDate("date_naissance"));
                u.setRole(us.getInt("role"));
                u.setMail(us.getString("email"));
                u.setPassword(us.getString("password"));

                return u;
            }
        } catch (SQLException ex) {
            System.out.println("Erreur  : " + ex.getMessage());
        }

        return null; //
    }

    //search userBYCin
    public utilisateur getUserByCin(String cin) {
        try {
            String user = "SELECT * FROM utilisateur WHERE cin = " + cin;
            Statement stm = cnx.createStatement();
            ResultSet us = stm.executeQuery(user);

            if (us.next()) {
                utilisateur u = new utilisateur();
                u.setId_user(us.getInt("id_user"));
                u.setCin(us.getInt("cin"));
                u.setNom(us.getString("nom"));
                u.setPrenom(us.getString("prenom"));
                u.setNum(us.getString("num"));
                u.setDate_naissance(us.getDate("date_naissance"));
                u.setRole(us.getInt("role"));
                u.setMail(us.getString("email"));
                u.setPassword(us.getString("password"));

                return u;
            }
        } catch (SQLException ex) {
            System.out.println("Erreur  : " + ex.getMessage());
        }

        return null; //
    }

}