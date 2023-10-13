/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;
import java.util.List;
import edu.esprit.pi.entities.Reclamation;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import edu.esprit.pi.tools. DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
;
/**
 *
 * @author majdiabdelkrim
 */
public class ServiceReclamation implements IService <Reclamation> {
Connection cnx ;
public ServiceReclamation(){
    this.cnx= DataSource.getInstance().getConnection();
}

     @Override
    public void ajouter(Reclamation t) {
        try {
          String numero = String.valueOf(t.getNum());
        if (numero.matches("^[259]\\d{7}$")) {
            String req = "INSERT INTO reclamation (prenom, nom, num, email, type, description) VALUES ('"
                    + t.getPrenom() + "', '" + t.getNom() + "', " + t.getNum() + ", '" + t.getEmail() + "', '" + t.getType()
                    + "', '" + t.getDescription() + "')";

            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Donnée ajoutée avec succès !");
        } else {
            System.out.println("Le numéro doit commencer par 2, 5 ou 9 et contenir exactement 8 chiffres.");
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

@Override
    public void modifier(Reclamation t) {
    try {
        String req = "UPDATE 'reclamation' SET prenom='" + t.getPrenom() + "', nom='" + t.getNom() + "', num='" + t.getNum() +
                     "', email='" + t.getEmail() + "', type='" + t.getType() + "', description='" + t.getDescription() +
                     "' WHERE id_reclamation =" + t.getId_reclamation();
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
        String req = "DELETE from  reclamation  WHERE id_reclamation =" + id;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Donnée supprimer  avec succès !");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression des données : " + ex.getMessage());
    }  
}
    

    @Override
    public Reclamation getOne(int id) {
      try {
        String req = "SELECT * FROM reclamation WHERE id_reclamation = " + id;
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        if (rs.next()) {
            Reclamation r = new Reclamation();
            r.setId_reclamation(rs.getInt("id_reclamation"));
            r.setNom(rs.getString("nom"));
            r.setPrenom(rs.getString("prenom"));
            r.setNum(rs.getString("num"));
            r.setEmail(rs.getString("email"));
            r.setType(rs.getString("type"));
            r.setDescription(rs.getString("description"));

            return r;
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de la réclamation : " + ex.getMessage());
    }

    return null; //
}
      
    
    

    @Override
    public List<Reclamation> getAll(Reclamation t) {
        String req = "SELECT * FROM `reclamation`";
      ArrayList<Reclamation> reclamation = new ArrayList();
    Statement stm;
    try {
        stm = this.cnx.createStatement();
    
    
        ResultSet rs=  stm.executeQuery(req);
    while (rs.next()){
        Reclamation r = new Reclamation();
        r.setId_reclamation(rs.getInt(1));
        r.setNom(rs.getString(2));
        r.setPrenom(rs.getString(3));
        r.setNum(rs.getString(4));
        r.setEmail(rs.getString(5));
        r.setType(rs.getString(6));
        r.setDescription(rs.getString(7));
        
        reclamation.add(r);
    }
        
        
    } catch (SQLException ex) {
    
        System.out.println(ex.getMessage());
    
    }
    return reclamation;
    }
    }

    
    

