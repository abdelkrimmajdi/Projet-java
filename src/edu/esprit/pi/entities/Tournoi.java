/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.entities;

import java.sql.Date;

/**
 *
 * @author hamma
 */
public class Tournoi {
    private int id_tournoi;
    private int id_terrain;
    private String nom_tournoi;
    private String equipes;
    private String adresse;
    private int nbr_equipe;
    private Date date_tournoi;

    public Tournoi() {
    }

    public Tournoi(String nom_tournoi, String equipes, String adresse, int nbr_equipe, Date date_tournoi) {
        this.nom_tournoi = nom_tournoi;
        this.equipes = equipes;
        this.adresse = adresse;
        this.nbr_equipe = nbr_equipe;
        this.date_tournoi = date_tournoi;
    }

    public Tournoi(int id_terrain, String nom_tournoi, String equipes, String adresse, int nbr_equipe, Date date_tournoi) {
        this.id_terrain = id_terrain;
        this.nom_tournoi = nom_tournoi;
        this.equipes = equipes;
        this.adresse = adresse;
        this.nbr_equipe = nbr_equipe;
        this.date_tournoi = date_tournoi;
    }

    public Tournoi(String nom_tournoi, String equipes, String adresse, int nbr_equipe, Date date_tournoi,int id_tournoi) {
        this.id_tournoi = id_tournoi;
        this.nom_tournoi = nom_tournoi;
        this.equipes = equipes;
        this.adresse = adresse;
        this.nbr_equipe = nbr_equipe;
        this.date_tournoi = date_tournoi;
    }

    public Tournoi(String nom_tournoi, String equipes, String adresse, Date date_tournoi) {
        this.nom_tournoi = nom_tournoi;
        this.equipes = equipes;
        this.adresse = adresse;
        this.date_tournoi = date_tournoi;
    }

    
    
    public String getNom_tournoi() {
        return nom_tournoi;
    }

    public void setNom_tournoi(String nom_tournoi) {
        this.nom_tournoi = nom_tournoi;
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

    public int getId_terrain() {
        return id_terrain;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    @Override
    public String toString() {
        return "Tournoi{" + "id_tournoi=" + id_tournoi + ", id_terrain=" + id_terrain + ", nom_tournoi=" + nom_tournoi + ", equipes=" + equipes + ", adresse=" + adresse + ", nbr_equipe=" + nbr_equipe + ", date_tournoi=" + date_tournoi + '}';
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
