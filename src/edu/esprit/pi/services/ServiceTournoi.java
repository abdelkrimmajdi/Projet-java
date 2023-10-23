/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;
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
import edu.esprit.pi.entities.Tournoi;

/**
 *
 * @author hamma
 */
public class ServiceTournoi implements IService<Tournoi> {
Connection cnx ;

public ServiceTournoi(){
    this.cnx= DataSource.getInstance().getConnection();
}

    @Override
    public void ajouter(Tournoi t) {
        try {
            
        String selectInfoQuery = "SELECT id_terrain FROM terrain WHERE adresse = ?";
        
        PreparedStatement infoStmt = cnx.prepareStatement(selectInfoQuery);
        infoStmt.setString(1, t.getAdresse()); 
        
        ResultSet infoResult = infoStmt.executeQuery();
        
        int idTerrain = 0;
        
        if (infoResult.next()) {
            idTerrain = infoResult.getInt("id_terrain");
        }
        
        // Utilisez les valeurs récupérées dans la requête d'insertion
        String insertQuery = "INSERT INTO tournoi (id_terrain, nom_tournoi, equipes, adresse, nbr_equipe, date_tournoi) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement insertStmt = cnx.prepareStatement(insertQuery);
        insertStmt.setInt(1, idTerrain);
        insertStmt.setString(2, t.getNom_tournoi());
        insertStmt.setString(3, t.getEquipes());
        insertStmt.setString(4, t.getAdresse());
        insertStmt.setInt(5, t.getNbr_equipe());
        insertStmt.setDate(6, t.getDate_tournoi());
        
        

        // Exécute la requête d'insertion
        insertStmt.executeUpdate();
        System.out.println("Donnée ajoutée avec succès!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    }

    @Override
    public void modifier(Tournoi t) {
    try {
        String req = "UPDATE tournoi SET nom_tournoi='" + t.getNom_tournoi() + "',equipes='" + t.getEquipes() + "', adresse='" + t.getAdresse() +
                     "', nbr_equipe='" + t.getNbr_equipe() +  "', date_tournoi='" + t.getDate_tournoi() +  "' WHERE id_tournoi=" + t.getId_tournoi();
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
        String req = "DELETE from Tournoi  WHERE id_tournoi =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Données supprimées avec succès !");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression des données : " + ex.getMessage());
    }
    }

    @Override
    public Tournoi getOne(int id) {
        String query = "SELECT * FROM `match` WHERE id_match = ?";
        Tournoi tournoi = null;

        try (PreparedStatement pstmt = cnx.prepareStatement(query)) {
        pstmt.setInt(1, id);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                tournoi = new Tournoi();
                tournoi.setId_tournoi(rs.getInt("id_tournoi"));
                tournoi.setId_terrain(rs.getInt("id_terrain"));
                tournoi.setNom_tournoi(rs.getString("nom_tournoi"));
                tournoi.setEquipes(rs.getString("Equipe"));
                tournoi.setNbr_equipe(rs.getInt("Nombre equipe"));
                tournoi.setAdresse(rs.getString("Adresse"));
                tournoi.setDate_tournoi(rs.getDate("Date_tournoi"));
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error while fetching data: " + ex.getMessage());
    }

    return tournoi;
    }

    @Override
    public List<Tournoi> getAll() {
        String req = "SELECT * FROM `tournoi`";
        ArrayList<Tournoi> Tournoi = new ArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs=  stm.executeQuery(req);
        while (rs.next()){
            Tournoi p = new Tournoi();
            p.setId_tournoi(rs.getInt(1));
            p.setId_terrain(rs.getInt(2));
            p.setNom_tournoi(rs.getString(3));
            p.setEquipes(rs.getString(4));
            p.setAdresse(rs.getString(5));
            p.setNbr_equipe(rs.getInt(6));
            p.setDate_tournoi(rs.getDate(7));
        
        Tournoi.add(p);
        }
        
        
    } catch (SQLException ex) {
    
        System.out.println(ex.getMessage());
    
    }
    return Tournoi;
    }
    


}