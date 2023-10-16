/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import edu.esprit.pi.entities.equipe;

/**
 *
 * @author hamma
 */
public class Match {
    private int id_match;
    private Resultat Resultat;
    private equipe equipe1;
    private equipe equipe2;
    
    public enum Resultat {
        EQUIPE_1_GAGNE,  // Résultat 1
        EQUIPE_2_GAGNE   // Résultat 2
    }
    public Match() {
    }

    public Match(int id_match, Resultat Resultat, equipe equipe1, equipe equipe2) {
        this.Resultat = Resultat;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
    }

    public Resultat getResultat() {
        return Resultat;
    }

    public void setResultat(Resultat Resultat) {
        this.Resultat = Resultat;
    }

    public equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(equipe equipe2) {
        this.equipe2 = equipe2;
    }
    
   

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    @Override
    public String toString() {
        return "Match{" + "id_match=" + id_match + ", Resultat=" + Resultat + ", equipe1=" + equipe1 + ", id_equipe2=" + equipe2 + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_match;
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
