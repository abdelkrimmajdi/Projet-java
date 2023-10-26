/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.entities;

import java.sql.Date;

/**
 *
 * @author majdiabdelkrim
 */
public class Reclamation {
    private int id_reclamation;
    private utilisateur id_utilisateur;


    private String num;
    private String email;
    private String type;
    private String description;
    private String date;
    private String etat;   
    private String cin;

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
    public Reclamation() {
    }

    public Reclamation(String type, String description, String cin) {
        this.type = type;
        this.description = description;
        this.cin = cin;
    }

 
    public Reclamation(String type, String description) {
        this.type = type;
        this.description = description;
    }
    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public utilisateur getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(utilisateur id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

<<<<<<< HEAD
  
=======

>>>>>>> 80e8b0478f25e6c5ab854ad330d1b6fd5526507d

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", id_utilisateur=" + id_utilisateur + ", num=" + num + ", email=" + email + ", type=" + type + ", description=" + description + ", date=" + date + ", etat=" + etat + '}';
    }
  
    
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
        final Reclamation other = (Reclamation) obj;
        if (this.etat != other.etat) {
            return false;
        }
        return true;
    }

   

   
    
}
