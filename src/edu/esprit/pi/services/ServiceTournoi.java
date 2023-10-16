/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;
import edu.esprit.pi.services.IService;
import edu.esprit.pi.tools.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.edu.esprit.entities.Tournoi;

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
            String req = "INSERT INTO `tournoi`(id_equipe,id_terrain,nbr_equipe,date_tournoi) VALUES ('"  + t.getEquipes() + "','" + t.getTerrain() + "','" + t.getNbr_equipe() + "','" + t.getDate_tournoi() + "')";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Données ajoutées avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Tournoi t) {
    try {
        String req = "UPDATE tournoi SET id_equipe='" + t.getEquipes() + "', id_terrain='" + t.getTerrain() +
                     "', nbr_equipe='" + t.getNbr_equipe() + "', date_tournoi='" + t.getDate_tournoi() + "' WHERE id_tournoi=" + t.getId_tournoi();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tournoi> getAll(Tournoi t) {
        String req = "SELECT * FROM `tournoi`";
        ArrayList<Tournoi> Tournoi = new ArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs=  stm.executeQuery(req);
        while (rs.next()){
            Tournoi p = new Tournoi();
            p.setId_tournoi(rs.getInt(1));
            p.setEquipes(rs.getString(2));
            p.setTerrain(rs.getInt(3));
            p.setNbr_equipe(rs.getInt(4));
            p.setDate_tournoi(rs.getDate(5));
        
        Tournoi.add(p);
        }
        
        
    } catch (SQLException ex) {
    
        System.out.println(ex.getMessage());
    
    }
    return Tournoi;
    }
    


}