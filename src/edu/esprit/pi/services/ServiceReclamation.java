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
import java.sql.PreparedStatement;
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
    String selectQuery = "SELECT num, email, id_utilisateur FROM user WHERE cin = ?";
    PreparedStatement selectStmt = cnx.prepareStatement(selectQuery);
    selectStmt.setString(1, t.getCin());

    ResultSet resultSet = selectStmt.executeQuery();

    if (resultSet.next()) {
         // Il y a une correspondance dans la table "user", vous pouvez maintenant effectuer l'insertion.
        String insertQuery = "INSERT INTO recl (numero, email, id_utilisateur, type, description, etat) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStmt = cnx.prepareStatement(insertQuery);
        insertStmt.setInt(1, resultSet.getInt("num"));
        insertStmt.setString(2, resultSet.getString("email"));
        insertStmt.setInt(3, resultSet.getInt("id_utilisateur"));
        insertStmt.setString(4, t.getType());
        insertStmt.setString(5, t.getDescription());
        insertStmt.setString(6, "en attente");
        insertStmt.executeUpdate();
        System.out.println("Donnée ajoutée avec succès!");
    } else {
        System.out.println("Cin n'est pas trouvée");
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
              r.setDate(rs.getString("date"));
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
        r.setId_reclamation(rs.getInt(1));
        r.setId_utilisateur(rs.getInt(2));
        r.setNum(rs.getString(3));
        r.setEmail(rs.getString(4));
        r.setType(rs.getString(5));
        r.setDescription(rs.getString(6));
        r.setEtat(rs.getString(7));
          r.setDate(rs.getString(8));
        reclamation.add(r);
    }
        
        
    } catch (SQLException ex) {
    
        System.out.println(ex.getMessage());
    
    }
    return reclamation;
    }
      
    public List<Reclamation> afficher(String cin) {
    ArrayList<Reclamation> reclamation = new ArrayList<>();
    try {
        // Requête SQL pour récupérer les réclamations associées à l'utilisateur ayant le CIN donné
        String req = "SELECT * FROM recl WHERE id_utilisateur = (SELECT id_utilisateur FROM user WHERE cin = ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setString(1, cin);
        ResultSet rs = preparedStatement.executeQuery();
        
        while (rs.next()) {
            Reclamation r = new Reclamation();
            r.setId_reclamation(rs.getInt("id_reclamation"));
            r.setNum(rs.getString("numero"));
            r.setEmail(rs.getString("email"));
            r.setType(rs.getString("type"));
            r.setDescription(rs.getString("description"));
            r.setEtat(rs.getString("etat"));
            r.setDate(rs.getString("date"));
            reclamation.add(r);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des réclamations : " + ex.getMessage());
    }

    return reclamation;
}





            
        }
      
   
    

  

   
    


 

    
    

