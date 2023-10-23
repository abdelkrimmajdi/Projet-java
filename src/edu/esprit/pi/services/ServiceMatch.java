/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;
import edu.esprit.pi.entities.equipe;
import edu.esprit.pi.services.IService;
import edu.esprit.pi.tools.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.esprit.pi.entities.Match;



/**
 *
 * @author hamma
 */
public class ServiceMatch implements IService<Match> {
Connection cnx ;

public ServiceMatch(){
    this.cnx= DataSource.getInstance().getConnection();
}   
    @Override
    public void ajouter(Match t) {
    try {
        String selectInfoQuery1 = "SELECT id_tournoi FROM tournoi WHERE nom_tournoi = ?";
        
        PreparedStatement infoStmt1 = cnx.prepareStatement(selectInfoQuery1);
        infoStmt1.setString(1, t.getNom_tournoi()); 
        
        ResultSet infoResult1 = infoStmt1.executeQuery();
        
        int idTournoi = 0;
        
        if (infoResult1.next()) {
            idTournoi = infoResult1.getInt("id_tournoi");
        }
        
        String selectInfoQuery2 = "SELECT id_equipe FROM equipe WHERE nom_equipe = ?";
        
        PreparedStatement infoStmt2 = cnx.prepareStatement(selectInfoQuery2);
        infoStmt2.setString(1, t.getNom_equipe1()); 
        
        ResultSet infoResult2 = infoStmt1.executeQuery();
        
        int idEquipe1 = 0;
        
        if (infoResult2.next()) {
            idEquipe1 = infoResult1.getInt("id_equipe");
        }
        
        String selectInfoQuery3 = "SELECT id_equipe FROM equipe WHERE nom_equipe = ?";
        
        PreparedStatement infoStmt3 = cnx.prepareStatement(selectInfoQuery3);
        infoStmt3.setString(1, t.getNom_equipe2()); 
        
        ResultSet infoResult3 = infoStmt3.executeQuery();
        
        int idEquipe2 = 0;
        
        if (infoResult3.next()) {
            idEquipe2 = infoResult3.getInt("id_equipe");
        }

        // Utilisez les valeurs récupérées dans la requête d'insertion
        String insertQuery = "INSERT INTO `match` (id_tournoi, nom_tournoi, Resultat, nom_equipe1, id_equipe1, nom_equipe2, id_equipe2) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";

        
        PreparedStatement insertStmt = cnx.prepareStatement(insertQuery);
        // Map the Resultat enum to the integer value
 

        // Set the mapped value in the prepared statement
        insertStmt.setInt(1, idTournoi);
        insertStmt.setString(2, t.getNom_tournoi());
        insertStmt.setString(2, t.getRes());
        insertStmt.setString(3, t.getNom_equipe1());
        insertStmt.setInt(4, idEquipe1);
        insertStmt.setString(5, t.getNom_equipe2());
        insertStmt.setInt(6, idEquipe2);
        

        // Exécute la requête d'insertion
        insertStmt.executeUpdate();
        System.out.println("Donnée ajoutée avec succès!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    

    @Override
    public void modifier(Match t) {
    try {
           String req = "UPDATE `match` SET  nom_tournoi='" + t.getNom_tournoi()+ "',Resultat='" + t.getRes() + "', nom_equipe1='" + t.getNom_equipe1() + 
           "', nom_equipe2='" + t.getNom_equipe2() +  "' WHERE id_match = " + t.getId_match();
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Données modifiées avec succès !");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la modification des données : " + ex.getMessage());
    }
}

    @Override
    public void supprimer(int id) {
        try {
        String req = "DELETE from `match` WHERE id_match =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Données supprimées avec succès !");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression des données : " + ex.getMessage());
    }
    }
    

    @Override
    public Match getOne(int id) {
    String query = "SELECT * FROM `match` WHERE id_match = ?";
    Match match = null;

    try (PreparedStatement pstmt = cnx.prepareStatement(query)) {
        pstmt.setInt(1, id);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                match = new Match();
                match.setId_match(rs.getInt("id_match"));
                match.setId_match(rs.getInt("id_tournoi"));
                match.setNom_tournoi(rs.getString("nom_tournoi"));
                match.setId_equipe1(rs.getInt("id_equipe1"));
                match.setId_equipe2(rs.getInt("id_equipe2"));
                match.setRes(rs.getString("Res"));
                match.setNom_equipe1(rs.getString("nom_equipe1"));
                match.setNom_equipe2(rs.getString("nom_equipe2"));
                
                
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error while fetching data: " + ex.getMessage());
    }

    return match;
 }
    @Override
    public List<Match> getAll() {
        String req = "SELECT * FROM `match`";
        ArrayList<Match> matches = new ArrayList<>();
        Statement stm;

        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                Match match = new Match();
                match.setId_match(rs.getInt(1));
                match.setId_tournoi(rs.getInt(2));
                match.setNom_tournoi(rs.getString(2));
                match.setId_equipe1(rs.getInt(3));
                match.setId_equipe2(rs.getInt(4));
                match.setRes(rs.getString(5));
                match.setNom_equipe1(rs.getString(6));
                match.setNom_equipe2(rs.getString(7));
                

                matches.add(match);
            }

        } catch (SQLException ex) {
            System.out.println("Error while fetching data: " + ex.getMessage());
        }

        return matches;
    }
}
