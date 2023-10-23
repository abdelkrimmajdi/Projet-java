/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.entities.Reclamation;
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
        //Add SQL prepared statement to add a new user
    }

    @Override
    public void modifier(utilisateur t) {
        try {
        String user = "UPDATE 'utilisateur' SET cin='" + t.getCin() + "' prenom='" + t.getPrenom() + "', nom='" + t.getNom() + "', num='" + t.getNum() +
                     "', email='" + t.getMail() +  "', password='" + t.getPassword()+"', role='" + t.getRole()+"', date_naissance='" + t.getDate_naissance()+
                     "' WHERE id_user =" + t.getId_user();
        Statement stm = cnx.createStatement();
        stm.executeUpdate(user);
        System.out.println("Donnée modifiée avec succès !");
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
        System.out.println("Erreur lors de la suppression des données : " + ex.getMessage());
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
            u.setId_user(us.getInt("id_utilisateur"));
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

    @Override
    public List<utilisateur> getAll() {
        String user = "SELECT * FROM `utilisateur`";
      ArrayList<utilisateur> reclamation = new ArrayList();

    return reclamation; 
    }

}
