/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.entities;


import java.time.LocalDate;    
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Sadok
 */
public class Reservation {
    private int cin ;
    private int id_terrain;
    private int id_res;
    private LocalDate Date_reservation;  
    

  
    public Reservation() {
    }

    public Reservation(int cin, int id_terrain, int id_res, LocalDate Date_reservation) {
        this.cin = cin;
        this.id_terrain = id_terrain;
        this.id_res = id_res;
        this.Date_reservation = Date_reservation;
    }
    
    public Reservation(int cin, LocalDate Date_reservation) {
        this.cin = cin;
        this.Date_reservation = Date_reservation;
    }
    
    public Reservation(int cin, LocalDate Date_reservation, int id_Terrain) {
        this.cin = cin;
        this.Date_reservation = Date_reservation;
        this.id_terrain = id_Terrain;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public LocalDate getDate_reservation() {
        return Date_reservation;
    }

    public void setDate_reservation(LocalDate Date_reservation) {
        this.Date_reservation = Date_reservation;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res( int id_res) {
        this.id_res = id_res;
    }

    public int getId_terrain() {
        return id_terrain;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    @Override
    public String toString() {
        return "Reservation{" + "cin=" + cin + ", id_terrain=" + id_terrain + ", id_res=" + id_res + ", Date_reservation=" + Date_reservation + '}';
    } 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id_res);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.id_res, other.id_res)) {
            return false;
        }
        return true;
    }

  }

