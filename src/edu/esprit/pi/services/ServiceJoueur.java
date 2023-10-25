/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;
//package tn.edu.esprit.services;
import edu.esprit.pi.entities.equipe;
import edu.esprit.pi.entities.joueur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import tn.edu.esprit.entities.joueur;
import edu.esprit.pi.tools.DataSource;

/**
 *
 * @author benou
 */
public class ServiceJoueur implements  IService <joueur> {
Connection cnx;

public ServiceJoueur(){
    this.cnx= DataSource.getInstance().getConnection();
} 


    @Override
    public void ajouter(joueur t) {
       try {
         String req = "INSERT INTO joueur ( num_joueur , nom_joueur , age_joueur , Position_joueur ) VALUES ('" + t.getNum_joueur() + "' , '" + t.getNom_joueur() + "', '" + t.getAge_joueur() + "', '" + t.getPosition_joueur()+ "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Donnée ajoutée avec succès !");
        } catch (SQLException ex) {
System.out.println("Erreur lors de l'ajout des données : " + ex.getMessage());            
    }}
    
    
    @Override
    public void modifier(joueur t) {
       try {
        String req = "UPDATE joueur SET num_joueur= '" + t.getNum_joueur() +  "',  nom_joueur = '" + t.getNom_joueur() + "',age_joueur = '" + t.getAge_joueur() + "',Position_joueur = '" + t.getPosition_joueur()+ "' WHERE id_joueur = '" + t.getId_joueur() + "'";
       Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Donnée modifiée avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification des données : " + ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(int id) {
   try {
            String req = "DELETE FROM joueur WHERE id_joueur = " + id;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Donnée supprimée avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression des données : " + ex.getMessage());
        }
    }

    @Override
    public joueur getOne(int id) {
     try {
        String req = "SELECT * FROM joueur WHERE id_joueur = " + id;
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        if (rs.next()) {
            joueur j = new joueur();
            j.setId_joueur(rs.getInt("id_joueur"));
            j.setNum_joueur(rs.getInt("num_joueur"));
            j.setNom_joueur(rs.getString("nom_joueur"));
            j.setAge_joueur(rs.getInt("age_joueur"));
            j.setPosition_joueur(rs.getString("position_joueur"));
            

            return j;
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de joueur : " + ex.getMessage());
    }

    return null;    }

    @Override
   public List<joueur> getAll() {
    String req = "SELECT * FROM `joueur`";
    List<joueur> joueurs = new ArrayList<>();
    Statement stm = null;
    ResultSet rs = null;
    
    try {
        stm = this.cnx.createStatement();
        rs = stm.executeQuery(req);
        
        while (rs.next()) {
            joueur j = new joueur();
            j.setId_joueur(rs.getInt("id_joueur"));
            j.setNum_joueur(rs.getInt("num_joueur"));
            j.setNom_joueur(rs.getString("nom_joueur"));
            j.setAge_joueur(rs.getInt("age_joueur"));
            j.setPosition_joueur(rs.getString("Position_joueur"));

            
            joueurs.add(j);
        }
    } catch (SQLException ex) {
        System.out.println("An error occurred: " + ex.getMessage());
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("An error occurred while closing the ResultSet: " + e.getMessage());
            }
        }
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException e) {
                System.out.println("An error occurred while closing the Statement: " + e.getMessage());
            }
        }
    }
    return joueurs;
}
}
