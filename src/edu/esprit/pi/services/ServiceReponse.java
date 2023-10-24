/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.entities.Reclamation;
import edu.esprit.pi.entities.Reponse;
import edu.esprit.pi.tools.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author majdiabdelkrim
 */
public class ServiceReponse  implements IService <Reponse> {
    Connection cnx ;
    public ServiceReponse(){
 this.cnx= DataSource.getInstance().getConnection();}
    @Override
    public void ajouter(Reponse t) {
        try{
String req = "INSERT INTO reponse ( id_reclamation,texte_reponse,etat) VALUES ('"    + t.getId_reclamation()+ "', '" + t.getText_reponse() + "', '" + t.getEtat() + "')";
 
    Statement stm = cnx.createStatement();
     stm.executeUpdate(req);        
        System.out.println("Réponse ajoutée avec succès");
        String updateReclamation = "UPDATE recl SET etat = '"+ t.getEtat() +"'WHERE id_reclamation = "+t.getId_reclamation();
        stm.executeUpdate(updateReclamation);  
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout de la réponse : " + ex.getMessage());
    }

    }

    @Override
   
    public void modifier(Reponse t) {
    try {
        String req = "UPDATE reponse SET texte_reponse = '" + t.getText_reponse() + "', etat = '" + t.getEtat()+ "' WHERE id_reponse = " + t.getId_reponse();
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Réponse modifiée avec succès.");

        String r = "SELECT id_reclamation FROM reponse WHERE id_reponse = " + t.getId_reponse();
        ResultSet rs = stm.executeQuery(r);

        if (rs.next()) {
            String idReclamation = rs.getString("id_reclamation");
            String updateReclamation = "UPDATE recl SET etat = '" + t.getEtat() + "' WHERE id_reclamation = " + idReclamation;
            stm.executeUpdate(updateReclamation);
            System.out.println("Table des réclamations mise à jour avec succès.");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la modification de la réponse : " + ex.getMessage());
    }
}


    

    @Override
    public void supprimer(int idReponse) {
    try {
        String req = "DELETE FROM reponse WHERE id_reponse = " + idReponse;
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Réponse supprimée avec succès.");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression de la réponse : " + ex.getMessage());
    }
}
    @Override
    
  public Reponse getOne(int id) {
      

   try {
        String req = "SELECT * FROM reponse WHERE id_reponse = " + id;
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        if (rs.next()) {
            Reponse r = new Reponse();
            r.setId_reponse(rs.getInt("id_reponse")); 
            r.setId_reclamation(rs.getInt("id_reclamation"));
                r.setDate_reponse(rs.getString("Date_reponse"));
                    r.setText_reponse(rs.getString("texte_reponse"));
                        r.setEtat(rs.getString("etat"));
          
            return r;
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de la réclamation : " + ex.getMessage());
    }

    return null;
}

    public List<Reponse> getAll() {
    String req = "SELECT * FROM reponse";
    ArrayList<Reponse> reponses = new ArrayList<>();
    Statement stm;

    try {
        stm = this.cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Reponse r = new Reponse();
            r.setId_reponse(rs.getInt(1));
            r.setId_reclamation(rs.getInt(2));
            r.setDate_reponse(rs.getString(3));
            r.setText_reponse(rs.getString(4));
            r.setEtat(rs.getString(5));
            reponses.add(r);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return reponses;
}
    public List<Reponse> afficher (String cin){
         ArrayList<Reponse> reponse = new ArrayList<>();
    try {
        String req = "SELECT re.* FROM reponse re JOIN recl r ON re.id_reclamation = r.id_reclamation JOI utilisateur u ON r.id_utilisateur = u.id_user WHERE u.cin =?" ;
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setString(1, cin);
        ResultSet rs = preparedStatement.executeQuery();
        
        while (rs.next()) {
            Reponse r = new Reponse();
             r.setId_reponse(rs.getInt("id_reponse"));
            r.setId_reclamation(rs.getInt("id_reclamation"));
            r.setDate_reponse(rs.getString("date_reponse"));
            r.setText_reponse(rs.getString("texte_reponse"));
            r.setEtat(rs.getString("etat"));
            
            
            reponse.add(r);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des réclamations : " + ex.getMessage());
    }
    return reponse;
    }
  
 public List<Reponse> getOnee(int id) {
    ArrayList<Reponse> reponse = new ArrayList<>();
    try {
       
        String req = "SELECT * FROM reponse WHERE id_reclamation = ?";
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Reponse r = new Reponse();
            r.setId_reponse(rs.getInt("id_reponse"));
            r.setId_reclamation(rs.getInt("id_reclamation"));
            r.setDate_reponse(rs.getString("date_reponse"));
            r.setText_reponse(rs.getString("texte_reponse"));
            r.setEtat(rs.getString("etat"));
            reponse.add(r);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des réponses : " + ex.getMessage());
    }
    
    return reponse; 
}}
    
    
    

