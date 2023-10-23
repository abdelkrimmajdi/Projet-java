/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.entities;

/**
 *
 * @author hamma
 */
public class Match {
    private int id_match;
    private int id_equipe1;
    private int id_equipe2;
    
    
    private String nom_equipe1;
    private String nom_equipe2;
    private String Res;
   
    public Match() {
    }

    public Match(int id_match, String nom_equipe1, String nom_equipe2, String Res) {
        this.id_match = id_match;
        this.nom_equipe1 = nom_equipe1;
        this.nom_equipe2 = nom_equipe2;
        this.Res = Res;
    }
    
    public Match(String Res, String nom_equipe1, String nom_equipe2) {
        this.Res = Res;
        this.nom_equipe1 = nom_equipe1;
        this.nom_equipe2 = nom_equipe2;
    }

    public String getRes() {
        return Res;
    }

    public void setRes(String Res) {
        this.Res = Res;
    }

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }


    public int getId_equipe1() {
        return id_equipe1;
    }

    public void setId_equipe1(int id_equipe1) {
        this.id_equipe1 = id_equipe1;
    }

    public String getNom_equipe1() {
        return nom_equipe1;
    }

    public void setNom_equipe1(String nom_equipe1) {
        this.nom_equipe1 = nom_equipe1;
    }

    public int getId_equipe2() {
        return id_equipe2;
    }

    public void setId_equipe2(int id_equipe2) {
        this.id_equipe2 = id_equipe2;
    }

    public String getNom_equipe2() {
        return nom_equipe2;
    }

    public void setNom_equipe2(String nom_equipe2) {
        this.nom_equipe2 = nom_equipe2;
    }

    @Override
    public String toString() {
        return "Match{" + "id_match=" + id_match + ", id_equipe1=" + id_equipe1 + ", id_equipe2=" + id_equipe2 + ", nom_equipe1=" + nom_equipe1 + ", nom_equipe2=" + nom_equipe2 + ", Res=" + Res + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id_match;
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
        final Match other = (Match) obj;
        if (this.id_match != other.id_match) {
            return false;
        }
        return true;
    }

}    