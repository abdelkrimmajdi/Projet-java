package edu.esprit.pi.entities;


public class equipe {

    public static int getNbjoueur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id;
    private String nom;
    private int nbjoueur;
    
    
    public equipe(){
        
    }

    public equipe(String nom, int nbjoueur) {
        this.nom = nom;
        this.nbjoueur = nbjoueur;
    }

    public equipe(int id, String nom, int nbjoueur) {
        this.id = id;
        this.nom = nom;
        this.nbjoueur = nbjoueur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getnbjoueur() {
        return nbjoueur;
    }

    public void setnbjoueur(int nbjoueur) {
        this.nbjoueur = nbjoueur;
    }

    @Override
    public String toString() {
        return "equipe{" +"=id" + id + "nom=" + nom + ", prenom=" + nbjoueur + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
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
        final equipe other = (equipe) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
