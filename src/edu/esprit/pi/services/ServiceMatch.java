/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;
import edu.esprit.pi.entities.equipe;
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
import tn.edu.esprit.entities.Match;
import tn.edu.esprit.entities.Match.Resultat;


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
            String req = "INSERT INTO `match` (Resultat, equipe1, equipe2) VALUES ('" + t.getResultat() + "', " 
                    + t.getEquipe1() + ", " + t.getEquipe2() + ")";
            
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Données ajoutées avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Match t) {
    try {
        String req = "UPDATE `match` SET Resultat='" + t.getResultat() + "', equipe1='" 
                + t.getEquipe1()+ "', equipe2='" + t.getEquipe2() + 
                "' WHERE id_match=" + t.getId_match();
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
    
    public int getEquipeId(equipe e) {
        return e.getId();
    }

    @Override
    public Match getOne(int id ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Match> getAll(Match t) {
        String req = "SELECT * FROM `match`";
        ArrayList<Match> Match = new ArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs=  stm.executeQuery(req);
        while (rs.next()){
            Match p = new Match();
            p.setId_match(rs.getInt(1));
            p.setResultat(Resultat.values()[rs.getInt(2) - 1]);
            int equipe1Id = rs.getInt(3);
            int equipe2Id = rs.getInt(4);

            equipe equipe1 = new equipe(); // Create an equipe object
            equipe1.setId(equipe1Id); // Set its ID

            equipe equipe2 = new equipe(); // Create another equipe object
            equipe2.setId(equipe2Id); // Set its ID

            p.setEquipe1(equipe1); // Set equipe1 with the equipe object
            p.setEquipe2(equipe2); // Set equipe2 with the equipe object
        
            Match.add(p);
        }
        
        
    } catch (SQLException ex) {
    
        System.out.println(ex.getMessage());
    
    }
    return Match;
    }

}
