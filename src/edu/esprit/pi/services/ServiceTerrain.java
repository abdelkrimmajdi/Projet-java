/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.entities.Terrain;
import edu.esprit.pi.tools.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Sadok
 */
public class ServiceTerrain implements IService<Terrain> {

    Connection cnx;

    public ServiceTerrain() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void modifier(Terrain t) {
        try {
            String req = "update terrain set prix=?,adress=? where id_terrain= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, (int) t.getPrix());
            ps.setString(2, t.getAdress());
            ps.executeUpdate();
            System.out.println("Terrain modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id_Terrain) {
        try {
            Statement st = cnx.createStatement();
            String req = "DELETE FROM terrain WHERE id_Terrain = " + id_Terrain + "";
            st.executeUpdate(req);
            System.out.println(" terrain supprimere avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Terrain getOne(Terrain t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Terrain> getAll() {
        ObservableList<Terrain> terrains = FXCollections.observableArrayList();
        try {
            String req = "select * from terrain";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Terrain t = new Terrain();
                t.setId_terrain(rs.getInt(1));
                t.setPrix(rs.getInt(2));
                t.setAdress(rs.getString(3));
                t.setStatus(rs.getInt(4));
                terrains.add(t);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return terrains;
    }

    @Override
    public void ajouter(Terrain t) {
        try {
            String req = "INSERT INTO terrain (id_terrain, prix, adresse, status) "
                    + "VALUES ('" + t.getId_terrain() + "', '" + t.getPrix() + "', '" + t.getAdress() + "', '" + t.getStatus() + "')";

            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Terrain getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
