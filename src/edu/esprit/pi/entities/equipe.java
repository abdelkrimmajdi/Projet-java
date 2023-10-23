package edu.esprit.pi.entities;


public class equipe {                   
    private int id_equipe;
    private String nom_equipe;
    private int nbjoueur_equipe;
    private String couleurmaillot;
    
    
    public equipe() {
    }
    
    public equipe(String nom_equipe, int nbjoueur_equipe, String couleurmaillot) {
        this.nom_equipe = nom_equipe;
        this.nbjoueur_equipe = nbjoueur_equipe;
        this.couleurmaillot=couleurmaillot;
    }

    public equipe(int id_equipe, String nom_equipe, int nbjoueur_equipe ,String couleurmaillot ) {
        this.id_equipe = id_equipe;
        this.nom_equipe = nom_equipe;
        this.nbjoueur_equipe = nbjoueur_equipe;
                this.couleurmaillot=couleurmaillot;

    }

    public String getCouleurmaillot() {
        return couleurmaillot;
    }

    public void setCouleurmaillot(String couleurmaillot) {
        this.couleurmaillot = couleurmaillot;
    }

    public int getId_equipe() {
        return id_equipe;
    }

    public void setId_equipe(int id_equipe) {
        this.id_equipe = id_equipe;
    }

    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public int getNbjoueur_equipe() {
        return nbjoueur_equipe;
    }

    public void setNbjoueur_equipe(int nbjoueur_equipe) {
        this.nbjoueur_equipe = nbjoueur_equipe;
        
    }

    @Override
    public String toString() {
        return "equipe{" + "id_equipe=" + id_equipe + ", nom_equipe=" + nom_equipe + ", nbjoueur_equipe=" + nbjoueur_equipe + ", couleurmaillot=" + couleurmaillot + '}';
    }

 
   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id_equipe;
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
        if (this.id_equipe != other.id_equipe) {
            return false;
        }
        return true;
    }
    
   
  
    
}
