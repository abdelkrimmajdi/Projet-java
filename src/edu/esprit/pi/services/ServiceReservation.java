package edu.esprit.pi.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.esprit.pi.entities.Reservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.esprit.pi.services.IService;
import edu.esprit.pi.tools.DataSource;

/**
 *
 * @author Sadok
 */
public class ServiceReservation implements IService <Reservation> {
Connection cnx ;

public ServiceReservation(){
    this.cnx= DataSource.getInstance().getConnection();
}

     @Override
    public void ajouter(Reservation R) {
    try {
        String req = "INSERT INTO reservation (id_res, id_terrain, cin, date_reservation) VALUES ('" + R.getId_res() + "','" + R.getId_terrain() + "','" + R.getCin() + "','" + R.getDate_reservation() + "')";
        
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    @Override
    public void modifier(Reservation R) {
          try {
            String req = "update Reservation set cin=?,id_terrain=?,id_res=?,Date_reservation=? where id_res= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, (int) R.getCin());
            ps.setInt(2, (int) R.getId_terrain());
            ps.setInt(3, (int) R.getId_res());
            ZoneId defaultZoneId = ZoneId.systemDefault();
            java.util.Date date = java.util.Date.from(R.getDate_reservation().atStartOfDay(defaultZoneId).toInstant());
            ps.setDate(2, (java.sql.Date)date);
            ps.executeUpdate();
            System.out.println("Reservation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try
    { 
      Statement st = cnx.createStatement();
      String req = "DELETE FROM reservation WHERE id_res = "+id+"";
                st.executeUpdate(req);      
      System.out.println(" reservation supprimere avec succès...");
    } catch (SQLException ex) {
                System.out.println(ex.getMessage());        
              }
    }
    public Reservation getOne(Reservation R) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
  public ObservableList<Reservation> getAll() {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        try {
            String req = "select * from reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Reservation R = new Reservation();
                R.setCin(rs.getInt(3));
                R.setId_terrain(rs.getInt(2));
                R.setId_res(rs.getInt(1));
                R.setDate_reservation(rs.getDate(4).toLocalDate());
                reservations.add(R);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reservations;
    }
  
  public List<LocalDate> dates(int id_terrain){
      List<LocalDate> reservedDates = new ArrayList<>();
      try{
          String req = "SELECT date_reservation FROM reservation WHERE id_terrain = "+id_terrain+"";
          Statement st = cnx.createStatement();
          ResultSet rs = st.executeQuery(req);
          
           while (rs.next()) {
               System.out.println(rs.getDate(1).toLocalDate().toString());
               LocalDate d = rs.getDate(1).toLocalDate();
               reservedDates.add(d);
               System.out.println(d.toString());
           }
      } catch(SQLException ex) {
          System.out.println(ex.getMessage());
      }
      return reservedDates;
  }

    @Override
    public Reservation getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}