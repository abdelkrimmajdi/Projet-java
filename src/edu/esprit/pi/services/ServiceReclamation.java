/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;
import java.util.List;
import edu.esprit.pi.entities.Reclamation;
import edu.esprit.pi.entities.utilisateur;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import edu.esprit.pi.tools. DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
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
        String selectQuery = "SELECT num, email FROM utilisateur WHERE id_user = ?";
        PreparedStatement selectStmt = cnx.prepareStatement(selectQuery);
        selectStmt.setInt(1, utilisateur.current_user.getId_user());
        ResultSet resultSet = selectStmt.executeQuery();
        if (resultSet.next()) {
            String insertQuery = "INSERT INTO recl (numero, email, id_utilisateur, type, description, etat) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = cnx.prepareStatement(insertQuery);
            insertStmt.setString(1, resultSet.getString("num"));
            insertStmt.setString(2, resultSet.getString("email"));
            insertStmt.setInt(3, utilisateur.current_user.getId_user());
            insertStmt.setString(4, t.getType());
            insertStmt.setString(5, t.getDescription());
            insertStmt.setString(6, "en attente");
            insertStmt.executeUpdate();
            System.out.println("Donnée ajoutée avec succès!");
        } else {
            System.out.println("L'utilisateur n'a pas été trouvé");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    @Override
  public void modifier(Reclamation t) {
    try {
        String req = "UPDATE recl SET type='" + t.getType() + "', description='" + t.getDescription() + "' WHERE id_reclamation = " + t.getId_reclamation();
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
        String req = "DELETE FROM recl WHERE id_reclamation="+ id;
          Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Réclamation supprimée avec succès !");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression de la réclamation : " + ex.getMessage());
    }
}
    
    @Override
public Reclamation getOne(int id) {
    try {
        String req = "SELECT * FROM recl WHERE id_reclamation = " + id;
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        if (rs.next()) {
            Reclamation r = new Reclamation();
            r.setId_reclamation(rs.getInt("id_reclamation")); 
            r.setNum(rs.getString("numero"));
            r.setEmail(rs.getString("email"));
            r.setType(rs.getString("type"));
            r.setDescription(rs.getString("description"));
            r.setEtat(rs.getString("etat"));
              r.setDate(rs.getString("date_recl"));
            return r;
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de la réclamation : " + ex.getMessage());
    }

    return null;
}
    @Override   
        public List<Reclamation> getAll() {
        String req = "SELECT * FROM recl";
      ArrayList<Reclamation> reclamation = new ArrayList();
    Statement stm;
    try {
        stm = this.cnx.createStatement();
    
    
        ResultSet rs=  stm.executeQuery(req);
    while (rs.next()){
        Reclamation r = new Reclamation();
<<<<<<< HEAD
        r.setNum(rs.getString(3));
        r.setEmail(rs.getString(4));
=======
        r.setId_reclamation(rs.getInt(1));
        r.setEmail(rs.getString(3));
        r.setNum(rs.getString(8));
>>>>>>> 80e8b0478f25e6c5ab854ad330d1b6fd5526507d
        r.setType(rs.getString(5));
        r.setDescription(rs.getString(6));
        r.setEtat(rs.getString(7));
          r.setDate(rs.getString(4));
        reclamation.add(r);
    }
        
        
    } catch (SQLException ex) {
    
        System.out.println(ex.getMessage());
    
    }
    return reclamation;
    }
      
    public List<Reclamation> afficher() {
     ArrayList<Reclamation> reclamation = new ArrayList<>();
    try {
        String req = "SELECT * FROM recl WHERE id_utilisateur = ?";
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setInt(1, utilisateur.current_user.getId_user());
        ResultSet rs = preparedStatement.executeQuery();
        
        while (rs.next()) {
            Reclamation r = new Reclamation();
             r.setId_reclamation(rs.getInt(1));
            r.setNum(rs.getString("numero"));
            r.setEmail(rs.getString("email"));
            r.setType(rs.getString("type"));
            r.setDescription(rs.getString("description"));
            r.setEtat(rs.getString("etat"));
            r.setDate(rs.getString("date_recl"));
            reclamation.add(r);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des réclamations : " + ex.getMessage());
    }

    return reclamation;
}
   public List<Reclamation> trierReclamationsParEtat() {
        List<Reclamation> reclamations = getAll();
        Collections.sort(reclamations, (r1, r2) -> {
            String etat1 = r1.getEtat();
            String etat2 = r2.getEtat();
            if (etat1.equals("en attente")) {
                if (etat2.equals("en attente")) {
                    return 0;
                } else if (etat2.equals("en cours")) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (etat1.equals("en cours")) {
                if (etat2.equals("en attente")) {
                    return 1;
                } else if (etat2.equals("en cours")) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                if (etat2.equals("en attente")) {
                    return -1;
                } else if (etat2.equals("en cours")) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        return reclamations;
    }

public boolean checkUser(Reclamation user, String lowerCaseFilter) {
     

     Reclamation r = new Reclamation();
        if (String.valueOf(r.getEmail()).toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else  if (String.valueOf(r.getNum()).toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else  if (String.valueOf(r.getDescription()).toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else  if (String.valueOf(r.getEtat()).toLowerCase().contains(lowerCaseFilter)) {
            return true;
      
        } else  if (String.valueOf(r.getType()).toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else {
            return false;
        }
    }
}
      
   
    

  

   
    


 

    
    

