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
//import tn.edu.esprit.entities.equipe;
import edu.esprit.pi.tools.DataSource;

/**
 *
 * @author benou
 */
public class ServiceEquipe implements  IService <equipe> {
Connection cnx;

public ServiceEquipe(){
    this.cnx= DataSource.getInstance().getConnection();
}

    @Override
    public void ajouter(equipe t) {
try {
        String req = "INSERT INTO equipe (nom_equipe, nbjoueur_equipe, couleurmaillot) VALUES ('" + t.getNom_equipe()+ "', " + t.getNbjoueur_equipe() + ", '" + t.getCouleurmaillot() + "')";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Donnée ajoutée avec succès !");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout des données : " + ex.getMessage());
    }

    }
    @Override

    public void modifier(equipe t) {
    try {
        String req = "UPDATE equipe SET nom_equipe = '" + t.getNom_equipe() + "', nbjoueur_equipe = " + t.getNbjoueur_equipe() + ", couleurmaillot='" + t.getCouleurmaillot() + "' WHERE id_equipe = '" + t.getId_equipe() + "'";
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
            String req = "DELETE FROM equipe WHERE id_equipe = " + id;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Donnée supprimée avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression des données : " + ex.getMessage());
        }    }

    @Override
    public equipe getOne(int id) {
     try {
        String req = "SELECT * FROM equipe WHERE id_equipe = " + id;
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

    if (rs.next()) {
            equipe eq = new equipe();
           eq.setId_equipe(rs.getInt("id_equipe"));
           eq.setNom_equipe(rs.getString("nom_equipe"));
           eq.setNbjoueur_equipe(rs.getInt("nbjoueur_equipe"));
            return eq;
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de l'equipe : " + ex.getMessage());
    }

    return null;    }

    public List<equipe> getAll() {
    String req = "SELECT * FROM `equipe`";
    List<equipe> equipes = new ArrayList<>();
    Statement stm = null;
    ResultSet rs = null;
    
    try {
        stm = this.cnx.createStatement();
        rs = stm.executeQuery(req);
        
        while (rs.next()) {
            equipe eq = new equipe();
            eq.setId_equipe(rs.getInt("id_equipe"));
            eq.setNom_equipe(rs.getString("nom_equipe"));
            eq.setNbjoueur_equipe(rs.getInt("nbjoueur_equipe"));
            eq.setCouleurmaillot(rs.getString("couleurmaillot"));
            equipes.add(eq);
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
    return equipes;
}

}