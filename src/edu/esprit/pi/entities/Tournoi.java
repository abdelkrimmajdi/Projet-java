/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hamma
 */
public class Tournoi {
    private int id_tournoi;
    private String equipes;
    private Terrain terrain;
    private int nbr_equipe;
    private Date date_tournoi;

    public Tournoi() {
    }

    public Tournoi(String equipes, Terrain terrain, int nbr_equipe, Date date_tournoi) {
        this.equipes = equipes;
        this.terrain = terrain;
        this.nbr_equipe = nbr_equipe;
        this.date_tournoi = date_tournoi;
    }

    public String getEquipes() {
        return equipes;
    }

    public void setEquipes(String equipes) {
        this.equipes = equipes;
    }
    
    
    
    public Date getDate_tournoi() {
        return date_tournoi;
    }

    public void setDate_tournoi(Date date_tournoi) {
        this.date_tournoi = date_tournoi;
    }

    

    
    public int getId_tournoi() {
        return id_tournoi;
    }

    public void setId_tournoi(int id_tournoi) {
        this.id_tournoi = id_tournoi;
    }

    public int getNbr_equipe() {
        return nbr_equipe;
    }

    public void setNbr_equipe(int nbr_equipe) {
        this.nbr_equipe = nbr_equipe;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id_tournoi=" + id_tournoi + ", equipes=" + equipes + ", terrain=" + terrain + ", nbr_equipe=" + nbr_equipe + ", date_tournoi=" + date_tournoi + '}';
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id_tournoi;
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
        final Tournoi other = (Tournoi) obj;
        if (this.id_tournoi != other.id_tournoi) {
            return false;
        }
        return true;
    }
    
    
}
