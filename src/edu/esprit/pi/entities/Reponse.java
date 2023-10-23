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
public class Reponse {
       private int id_reponse;
        private int  id_reclamation;
        private String date_reponse;
        private String text_reponse;
         private String etat;
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Reponse() {
    }

    public Reponse(int id_reclamation, String text_reponse) {
        this.id_reclamation = id_reclamation;
        this.text_reponse = text_reponse;
    }
    public Reponse(int id_reclamation, String text_reponse, String etat) {
    this.id_reclamation = id_reclamation;
    this.text_reponse = text_reponse;
    this.etat = etat;
}


       
    public int getId_reponse() {
        return id_reponse;
    }

    public void setText_reponse(String text_reponse) {
        this.text_reponse = text_reponse;
    }

    public String getText_reponse() {
        return text_reponse;
    }
 
  
    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }
    public int getId_reclamation() {
        return id_reclamation;
    }
    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }
 
    public String getDate_reponse() {
        return date_reponse;
    }

    public void setDate_reponse(String date_reponse) {
        this.date_reponse = date_reponse;
    }


        
}
