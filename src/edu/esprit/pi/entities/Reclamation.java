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
    private int id_utilisateur;
    private String prenom;
    private String nom;
    private String num;
    private String email;
    private String type;
    private String description;
    private Date date;
    
    

    public Reclamation() {
    }

    public Reclamation(String prenom, String nom, String num, String email, String type, String description) {
        this.prenom = prenom;
        this.nom = nom;
        this.num = num;
        this.email = email;
        this.type = type;
        this.description = description;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getNum() {
        return num;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  

   
    public String toString() {
        return "Reclamation{" +
                "id_reclamation=" + id_reclamation +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", nom='" + num + '\'' +
                ", nom='" + email + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
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
        if (this.id_reclamation != other.id_reclamation) {
            return false;
        }
        return true;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

   
    
}
